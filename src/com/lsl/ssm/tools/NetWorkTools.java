package com.lsl.ssm.tools;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import javax.servlet.http.HttpServletRequest;

/**
 * 获取访问者IP地址
 * @author LSL
 *
 */
public class NetWorkTools {
	
	/**
     * 获取一个请求的发起IP地址
     * @param request HttpServletRequest
     * @return String类型的ip
     */
    public static String getRemoteIp(HttpServletRequest request){
        String ip;
        ip = request.getHeader("x-forwarded-for");
        if (isNullIp(ip)){
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (isNullIp(ip)){
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (isNullIp(ip)){
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (isNullIp(ip)){
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (isNullIp(ip)){
            ip = request.getRemoteAddr();
        }
        if(ip.contains(",")){
            ip=ip.split(",")[0];
        }
        if ("0.0.0.0.0.0.0.1".equals(ip) || "0.0.0.0.0.0.0.1%0".equals(ip)){
            ip = "127.0.0.1";
        }
        return ip;
    }

    private static boolean isNullIp(final String ip){
        return ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip);
    }
    
    /**
     * 获取IP地址相关信息
     * @param ip
     * @return
     */
    public static String getIpInfo(String ip) {
    	String info = "";
    	try {
    		URL url = new URL("http://ip.taobao.com/service/getIpInfo.php?ip=" + ip);
    		URLConnection conn = url.openConnection();
    		BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
    		String line = null;
    		StringBuffer sb = new StringBuffer();
    		while((line = reader.readLine()) != null) {
    			sb.append(line);
    		}
    		reader.close();
    		String code = "{\"code\":";
			if ("0".equals(String.valueOf(sb.toString().charAt(sb.toString().indexOf(code) + code.length())))) {
				String country = ",\"country\":\"";
				info += getDetail(sb.toString(), country);
 
				String area = ",\"area\":\"";
				info += getDetail(sb.toString(), area);
 
				String region = ",\"region\":\"";
				info += getDetail(sb.toString(), region);
 
				String city = ",\"city\":\"";
				info += getDetail(sb.toString(), city);
				
				String isp = ",\"isp\":\"";
				info += getDetail(sb.toString(), isp);
				
			} else {
				info = "无效查询";
			}

    		
    	} catch (Exception e) {
			System.out.println("getIpInfo==================" + e);
		}
    	return info;
    }
    
	/**
	 * 获取相应信息
	 * @param str
	 * @param substr
	 * @return
	 */
	private static String getDetail(String str, String substr) {
		String result = " ";
		int substr_index = str.indexOf(substr) + substr.length();
		while (!"\"".equals(String.valueOf(str.charAt(substr_index)))) {
			result += String.valueOf(str.charAt(substr_index));
			substr_index++;
		}
		return result;
	}

}

