package com.lsl.ssm.tools;

import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.lsl.ssm.annotation.Token;

public class TokenUtils {

	private static Logger logger = Logger.getLogger(TokenUtils.class);
	
	/**
	 * 验证token是否有效
	 * @param request
	 * @param obj
	 * @return
	 */
	public static boolean TokenIsValid(HttpServletRequest request, Object obj) {
		logger.info("TokenIsValid ================================= " );
		if (obj.getClass().isAnnotationPresent(Token.class)) { // 检查obj是否有@Token注解
			Token token = obj.getClass().getAnnotation(Token.class); // 获取token对象
			if (token != null) {
				boolean saveSession = token.save(); // 得到save()方法的值
				if (saveSession) {
					request.getSession(false).setAttribute(Contants.TOKEN, getToken()); // 随机生成新token
				}
				boolean removeSession = token.remove();
				if (removeSession) {
					if (isRepeatSubmit(request)) {
						return false;
					}
					request.getSession(false).removeAttribute(Contants.TOKEN); // 移除原token
				}
			}
			return true;
		}
		
		return false;
	}
	
	/**
	 * 判断是否重复请求
	 * @param request
	 * @return
	 */
	private static boolean isRepeatSubmit(HttpServletRequest request) {
		String serverToken = (String) request.getSession(false).getAttribute(Contants.TOKEN);
		if (serverToken == null) {
			return true;
		}
		String clientToken = request.getParameter(Contants.TOKEN);
		if (clientToken == null) {
			return true;
		}
		if (!StringUtils.equals(serverToken, clientToken)) {
			return true;
		}
		return false;
	}
	
	/**
	 * 随机生成Token
	 * @return
	 */
	public static String getToken() {
		Random r = new Random(50);
		return MD5Utils.encode2hex(r.toString());
	}
}
