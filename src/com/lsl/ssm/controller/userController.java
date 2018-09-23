package com.lsl.ssm.controller;

import java.util.Date;
import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.AbstractController;

import com.alibaba.fastjson.JSONArray;
import com.lsl.ssm.annotation.Token;
import com.lsl.ssm.pojo.User;
import com.lsl.ssm.service.user.UserService;
import com.lsl.ssm.tools.Contants;
import com.lsl.ssm.tools.MD5Utils;
import com.lsl.ssm.tools.MyDecode;
import com.lsl.ssm.tools.NetWorkTools;
import com.lsl.ssm.tools.StrTool;
import com.lsl.ssm.tools.TokenUtils;

/**
 * 控制器
 * 
 * @author LSL
 * 
 */
@Controller
@Token(save = true)
public class userController {

	private Logger logger = Logger.getLogger(AbstractController.class);

	@Resource
	private UserService userService;

	@RequestMapping("/main")
	public String main(HttpServletRequest request, HttpSession session)
			throws Exception {
		logger.info("SESSION ======================== "
				+ session.getAttribute(Contants.USER_SESSION));

		TokenUtils.TokenIsValid(request, this); // 新建token并保存

		String userIp = NetWorkTools.getRemoteIp(request); // 获取访问者IP地址
		String userKey = MD5Utils.encode2hex(userIp);
		session.setAttribute(Contants.USER_SESSION, userKey);
		User user = userService.getUserByIP(userIp); // 通过IP获得地址
		if (user == null) {
			user = new User();
			user.setIp(userIp);
			user.setSuCount(0);
			user.setCount(0);
			user.setAddress(NetWorkTools.getIpInfo(userIp));
			user.setCreationDate(new Date());
			user.setUserKey(userKey);
			userService.addUser(user); // 保存新来访者
		} else {
			user.setUserKey(userKey);
			user.setModifyDate(new Date());
			userService.update(user); // 更新再次访问者信息
		}

		return "main";
	}

	@RequestMapping(value = "/r.json", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	@Token(remove = true)
	public Object Result(@RequestParam("n1") String num1,
			@RequestParam("n2") String num2, @RequestParam("n3") String num3,
			HttpServletRequest request) throws Exception {
		logger.info("session =========================== "
				+ request.getSession().getAttribute(Contants.USER_SESSION));
		HashMap<String, String> resultMap = new HashMap<String, String>();
		if (TokenUtils.TokenIsValid(request, this)) { // 检查token是否有效
			String ip = NetWorkTools.getRemoteIp(request); // 获取来访者IP
			User user = userService.getUserByIP(ip); // 根据IP查询获取用户信息

			if (MyDecode.decode(num3) == 10 && MyDecode.decode(num2) == 0
					&& MyDecode.decode(num1) == 1) {
				if (user != null) {
					user.setBest("10:00:00");
					user.setSuCount(user.getSuCount() + 1); // 成功次数+1
					user.setCount(user.getCount() + 1); // 计数+1
					user.setModifyDate(new Date());
					userService.update(user);
				}
				resultMap.put("result", "true");
			} else {
				if (user != null) {
					String best = user.getBest() + "";
					if (best.equals("null") || best.equals("")) { // 还没有成绩
						user.setBest(StrTool.getBest(MyDecode.decode(num3),
								MyDecode.decode(num2), MyDecode.decode(num1),
								""));
					} else { // 已有成绩
						if (user.getSuCount() < 1) { // 如果没有挑战成功记录才保存当前最好成绩
							user.setBest(StrTool.getBest(MyDecode.decode(num3),
									MyDecode.decode(num2), MyDecode
											.decode(num1), user.getBest()));
						}
					}
					user.setCount(user.getCount() + 1); // 计数+1
					user.setModifyDate(new Date());
					userService.update(user);
				}
				resultMap.put("result", "false");
			}
		} else {
			resultMap.put("result", "false");
		}

		return JSONArray.toJSONString(resultMap); // 转为JSON格式
	}
}
