package com.oim.stores.common;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionContext;

/**
 * 提供获得request、response、write、session等的操作的基础类
 * 
 * @author chenzhaowen
 */
@SuppressWarnings("unchecked")
public class PubFun {

	public static String USER_SESSION_KEY = "user";//用户session;
	/**
	 * 获取response写操作;
	 * @return
	 * @throws IOException
	 */
	public static PrintWriter getResponseWriter() throws IOException {
		HttpServletResponse response = (HttpServletResponse) ActionContext
				.getContext().get(ServletActionContext.HTTP_RESPONSE);
		response.setContentType("text/html;charset=UTF-8");
		return response.getWriter();
	}
	/**
	 * 获取response
	 * @return
	 * @throws IOException
	 */
	public static HttpServletResponse getResponse() throws IOException {
		HttpServletResponse response = (HttpServletResponse) ActionContext
				.getContext().get(ServletActionContext.HTTP_RESPONSE);
		response.setContentType("text/html;charset=UTF-8");
		return response;
	}
	/**
	 * 获取request;
	 * @return
	 * @throws IOException
	 */
	public static HttpServletRequest getRequest() throws IOException {
		HttpServletRequest request = (HttpServletRequest) ActionContext
				.getContext().get(ServletActionContext.HTTP_REQUEST);
		request.setCharacterEncoding("text/html;charset=UTF-8");
		return request;
	}

	//@SuppressWarnings("rawtypes")
	public static Object getSession(String key) {
		Map sessionMap = ActionContext.getContext().getSession();
		return sessionMap.get(key);

	}

	//@SuppressWarnings( { "rawtypes", "unchecked" })
	public static void setSession(String key, Object value) {
		Map sessionMap = ActionContext.getContext().getSession();
		sessionMap.put(key, value);
	}
	/**
	 * 移除session;
	 */
	public static void removeSession() {
		HttpSession session = ServletActionContext.getRequest().getSession();
		session.invalidate();
	}
	/**
	 * 根据关键字移除session;
	 * @param key
	 */
	public static void removeSession(String key) {
		Map sessionMap = ActionContext.getContext().getSession();
		if (sessionMap.get(key) != null) {
			sessionMap.remove(key);
		}
	}
	
}
