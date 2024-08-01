package com.oim.stores.framework.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.PageContext;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * 页面工具类
 * @author zhuangjf
 *
 */
public class JspUtils {
    public JspUtils() {
    }

    /**
     * 取basepath
     * @param request
     * @return
     * Author zhuangjf
     * Create On 2013-3-14 上午11:05:34
     */
    public static String getBasePath(HttpServletRequest request) {
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName()
		+ ":" + request.getServerPort() + path + "/";
	return basePath;
    }

    /**
     * 字符串转utf8
     * @param s
     * @return
     * Author zhuangjf
     * Create On 2013-3-14 上午11:05:44
     */
    public static String toUtf8String(String s) {
	StringBuffer sb = new StringBuffer();
	for (int i = 0; i < s.length(); i++) {
	    char c = s.charAt(i);
	    if (c >= 0 && c <= 255) {
		sb.append(c);
	    } else {
		byte[] b;
		try {
		    b = Character.toString(c).getBytes("utf-8");
		} catch (Exception ex) {
		    System.out.println(ex);
		    b = new byte[0];
		}
		for (int j = 0; j < b.length; j++) {
		    int k = b[j];
		    if (k < 0)
			k += 256;
		    sb.append("%" + Integer.toHexString(k).toUpperCase());
		}
	    }
	}
	return sb.toString();
    }

    /**
     * 取请求参数
     * @param request
     * @param paraName
     * @return
     * Author zhuangjf
     * Create On 2013-3-14 上午11:05:59
     */
    public static String getParameter(HttpServletRequest request,
	    String paraName) {
	return request.getParameter(paraName) == null ? "" : request
		.getParameter(paraName);
    }

    /**
     * 取客户端ip地址
     * @param request
     * @return
     * Author zhuangjf
     * Create On 2013-3-14 上午11:06:10
     */
    public static String getIpAddress(HttpServletRequest request) {
	String ip = request.getHeader("X-Forwarded-For");
	if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	    ip = request.getHeader("Proxy-Client-IP");
	}
	if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	    ip = request.getHeader("WL-Proxy-Client-IP");
	}
	if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	    ip = request.getHeader("HTTP_CLIENT_IP");
	}
	if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	    ip = request.getHeader("HTTP_X_FORWARDED_FOR");
	}
	if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	    ip = request.getRemoteAddr();
	}
	return ip;
    }

}
