package com.lsl.ssm.Interceptor;

import java.lang.reflect.Method;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.lsl.ssm.annotation.Token;
import com.lsl.ssm.tools.Contants;
import com.lsl.ssm.tools.MD5Utils;

/**
 * token 拦截器，验证token是否有效
 * @author LSL
 *
 */
public class TokenInterceptor extends HandlerInterceptorAdapter {

	private Logger logger = Logger.getLogger(TokenInterceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		logger.info("Token Interceptor preHandle =========================== ");
		if (handler instanceof HandlerMethod) {			
			HandlerMethod handlerMethod = (HandlerMethod) handler;
			Method method = handlerMethod.getMethod();
			Token annotation = method.getAnnotation(Token.class); // 得到注解对象
			if (annotation != null) {
				boolean saveSession = annotation.save(); // 得到save()方法的值
				if (saveSession) {
					request.getSession(false).setAttribute(Contants.TOKEN, getToken()); // 随机生成新token
				}
				boolean removeSession = annotation.remove();
				if (removeSession) {
					if (isRepeatSubmit(request)) {
//						response.setStatus(403);
						return false;
					}
					request.getSession(false).removeAttribute(Contants.TOKEN); // 移除原token
				}
			}
			return true;
		} else {
//			response.setStatus(403);
			return super.preHandle(request, response, handler);
		}
	}
	
	/**
	 * 判断是否重复请求
	 * @param request
	 * @return
	 */
	private boolean isRepeatSubmit(HttpServletRequest request) {
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
