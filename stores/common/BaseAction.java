package com.oim.stores.common;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.util.ServletContextAware;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 基础action
 */
public class BaseAction extends ActionSupport implements ServletRequestAware, ServletResponseAware, ServletContextAware {

	private static final long serialVersionUID = 1L;
	protected HttpSession session;//会话
	protected HttpServletRequest request;//客户端请求
	protected HttpServletResponse response;//回应信息
	protected ServletContext context;//servlet上下文
	protected String returnMsg;//返回信息
	
	/**
	 * 取IP
	 * 
	 * @return
	 */
	public String getIpAddr() {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
		this.session = request.getSession();
	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	public Object getRequestParameter(String key) {
		return request.getParameter(key);
	}

	public void setRequestAttribute(String key, Object value) {
		request.setAttribute(key, value);
	}

	public Object getSessionAttribute(String key) {
		return session.getAttribute(key);
	}

	public void setSessionAttribute(String key, Object value) {
		session.setAttribute(key, value);
	}

	protected void showMsg(String msg) {
		setRequestAttribute("showMsg", msg);
	}

	public ServletContext getContext() {
		return context;
	}

	public void setContext(ServletContext context) {
		this.context = context;
	}

	public void setServletContext(ServletContext context) {
		this.context = context;
	}

	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	public String getReturnMsg() {
		return returnMsg;
	}

	public void setReturnMsg(String returnMsg) {
		this.returnMsg = returnMsg;
	}

}
