package com.lsl.ssm.tools;

import java.util.regex.Pattern;

/**
 * 个人解密工具
 * @author LSL
 *
 */
public class MyDecode {

	public static int decode(String key) {
		
		char[] arr = key.toCharArray();
		int x = 0;
		int j;
		int i;
		String patt=".*[a-i]+.*";
		try {
			for (i = arr.length - 1,j=0; i > 0; i--,j++) {
				if (Pattern.matches( patt,arr[i]+"")) {
					break;
				}
				x+=Integer.parseInt(arr[i]+"") * (Math.pow(10, j));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		x=x>>Integer.parseInt(arr[0]+"");
		
		return x;
	}
}
