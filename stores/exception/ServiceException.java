package com.oim.stores.exception;
/**
 * service异常类
 * @author Administrator
 *
 */
@SuppressWarnings("serial")
public class ServiceException extends Exception {
	/**
	 * 默认构造器
	 */
	public ServiceException() {
		super();
	}
	/**
	 * 异常信息构造器
	 * @param message
	 */
	public ServiceException(String message) {
		super(message);
	}
	/**
	 * 构造器
	 * @param message异常信息
	 * @param cause原因
	 */
	public ServiceException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public ServiceException(Throwable cause) {
		super(cause);
	}
}
