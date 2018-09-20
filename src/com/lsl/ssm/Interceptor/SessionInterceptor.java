package com.lsl.ssm.Interceptor;

import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.lsl.ssm.pojo.User;
import com.lsl.ssm.service.user.UserService;
import com.lsl.ssm.tools.MD5Utils;
import com.lsl.ssm.tools.NetWorkTools;

public class SessionInterceptor extends HandlerInterceptorAdapter {

	private Logger logger = Logger.getLogger(SessionInterceptor.class);

	@Resource
	private UserService userService;

	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		HttpSession session = request.getSession();
		logger.info("Sessiong Interceptor preHandle =============================== ");

		User user = userService.getUserByIP(NetWorkTools.getRemoteIp(request));

		if (session != null
				&& user != null
				&& MD5Utils.validate(NetWorkTools.getRemoteIp(request), "" + user.getUserKey())) {
			// logger.info("preHandle result =============================== true");
			return true;
		} else {
			// logger.info("preHandle result =============================== false");
			response.setContentType("text/html; charset=utf-8");
			PrintWriter writer = response.getWriter();
			writer.print("<script>alert('页面已过期,请刷新重试!');</script>");
			writer.flush();
			writer.close();
			return false;
		}
	}

}
