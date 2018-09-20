package com.lsl.ssm.tools;

public class StrTool {
	/**
	 * 字符串拼接
	 * 
	 * @param n1
	 * @param n2
	 * @param n3
	 * @param org
	 * @return
	 */
	public static String getBest(int m1, int m2, int m3, String org) {
		String bestStr = "";
		m3=m3-1;
		if (org.equals("")) {
			bestStr = getFStr(m1, m2, m3);
		} else {
			String[] orgs = org.split(":"); // 原值
			int x1 = getInt(orgs[0]);
			int x2 = getInt(orgs[1]);
			int x3 = getInt(orgs[2]);
			boolean flag = false;
			if (Math.abs(10 - m1) < Math.abs(10 - x1)) {
				flag = true;
			}
			if (m1 == x1 && Math.abs(10 - m2) < Math.abs(10 - x2)) {
				flag = true;
			}
			if (m1 == x1 && m2 == x2 && Math.abs(10 - m3) < Math.abs(10 - x3)) {
				flag = true;
			}
			if (flag) {
				bestStr = getFStr(m1, m2, m3);// m1+":"+m2+":"+(m3-1);
			} else {
				bestStr = getFStr(x1, x2, x3);
			}
		}
		return bestStr;
	}

	private static String getFStr(int c1, int c2, int c3) {
		String res = "";
		if (c1 < 10) {
			res += "0" + c1 + ":";
		} else {
			res += c1 + ":";
		}

		if (c2 < 10) {
			res += "0" + c2 + ":";
		} else {
			res += c2 + ":";
		}

		if (c3 < 10) {
			res += "0" + c3;
		} else {
			res += c3;
		}

		return res;
	}

	private static int getInt(String str) {
		return Integer.parseInt(str);
	}
}
