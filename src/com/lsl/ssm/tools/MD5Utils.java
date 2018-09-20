package com.lsl.ssm.tools;

import java.security.MessageDigest;

/**
 * md5加密解密工具
 * @author LSL
 *
 */
public class MD5Utils {
		
		/**
		 * 将字符串使用md5加密为字节数组
		 * @param source
		 * @return
		 */
		public static byte[] encode2byte(String source) {
			byte[] result = null;
			try {
				MessageDigest md = MessageDigest.getInstance("MD5");
				md.reset();
				md.update(source.getBytes("utf-8"));
				result = md.digest();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return result;
		}
		
		/**
		 * 将源字符串使用md5加密为32位的16进制数
		 * @param source
		 * @return
		 */
		public static String encode2hex(String source) {
			byte[] data = encode2byte(source);
			
			StringBuffer hexString = new StringBuffer();
			for (int i = 0; i < data.length; i++) {
				String hex = Integer.toHexString(0xff & data[i]);
				
				if (hex.length() == 1) {
					hexString.append("0");
				}
				
				hexString.append(hex);
			}
			
			return hexString.toString();
		}
		
		/**
		 * 验证字符串是否匹配
		 * @param unknow 待验证字符串
		 * @param okHex 使用md5加密后的16进制字符串
		 * @return
		 */
		public static boolean validate(String unknow, String okHex) {
			return okHex.equals(encode2hex(unknow));
		}
//
//		/**
//		 * @param args
//		 */
//		public static void main(String[] args) {
//			// TODO Auto-generated method stub
//			String s = "192.168.1.101";
//			String str = MD5Utils.encode2hex(s);
//			System.out.println("加密后：" + str);
//			System.out.println("是否匹配原字符串？ " + MD5Utils.validate(s, str));
//		}
	
}
