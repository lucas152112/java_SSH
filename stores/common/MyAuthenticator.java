package com.oim.stores.common;

import javax.mail.*;
/**
 * 认证类
 * @author Administrator
 *
 */
public class MyAuthenticator extends Authenticator{
	String userName=null;//用户名
	String password=null;//密码
	/**
	 * 默认构造器
	 */
	public MyAuthenticator(){
	}
	public MyAuthenticator(String username, String password) { 
		this.userName = username; 
		this.password = password; 
	} 
	/**
	 * 密码认证
	 */
	protected PasswordAuthentication getPasswordAuthentication(){
		return new PasswordAuthentication(userName, password);
	}
}
 
