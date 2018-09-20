package com.lsl.ssm.test;

import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.lsl.ssm.Interceptor.TokenInterceptor;
import com.lsl.ssm.pojo.User;
import com.lsl.ssm.service.user.UserService;
import com.lsl.ssm.tools.MyDecode;
import com.lsl.ssm.tools.RSAUtils;
import com.lsl.ssm.tools.StrTool;


public class UserServiceTest {

	private Logger log = Logger.getLogger(UserServiceTest.class);
	
	
//	@Test
//	public void getNullValueTest() throws Exception {
//		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext-mybatis.xml");
//		UserService userService = (UserService) ctx.getBean("userService");
//		
//		User user = userService.getUserByIP("0:0:0:0:0:0:0:1");
//		if (user != null) {
//			String count = user.getBest()+"";
//			if (count.equals("null")) {
//				System.out.println("kong");				
//			}
//		}
//	}
//	
//	@Test
//	public void StrToolTest() {
//		System.out.println(Integer.parseInt("01"));
//		System.out.println(StrTool.getBest("010", "50", "2", "8:50:1"));
//	}
//	
//	@Test
//	public void resTest() {
//		log.info(RSAUtils.generateBase64PublicKey());
//		
//		String s = "lsl";
//		log.info(RSAUtils.decryptBase64(s));
//		
//	}
	
	@Test
	public void decode() throws Exception {
		String n1="1b2h192c180";
		String n2="6e6080j5824c6080";
		String n3="3e720c768h8";
//		char[] arr = n1.toCharArray();
//		

//		int x = 0;
//		int j;
//		int i;
//		try {
//			for (i = arr.length - 1,j=0; i > 0; i--,j++) {
//				x+=Integer.parseInt(arr[i]+"") * (Math.pow(10, j));
////				log.info((10^j));
//			}
//		} catch (Exception e) {
//			log.info(x>>Integer.parseInt(arr[0]+""));
//		}
		
		log.info(MyDecode.decode(n1));
		log.info(MyDecode.decode(n2));
		log.info(MyDecode.decode(n3));
		
		System.out.println(Pattern.matches(".*[a-i]+.*","1"));
//		Pattern.m
		
	}
	
	@Test
	public void getTokenTest() {
		TokenInterceptor token = new TokenInterceptor();
		log.info(token.getToken());
	}
	
	
	
}
