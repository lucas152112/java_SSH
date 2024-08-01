package com.oim.stores.exception;
/**
 * dao异常工具类
 * @author Administrator
 *
 */
@SuppressWarnings("serial")
public class DaoException extends Exception {
	/**
	 * 默认构造器
	 */
	public DaoException() {
		super();
	}
	/**
	 * 重置构造器
	 * @param message
	 */
	public DaoException(String message) {
		super(message);
	}
	/**
	 * 两个参数构造器
	 * @param message
	 * @param cause
	 */
	public DaoException(String message, Throwable cause) {
		super(message, cause);
	}
	/**
	 * constructor
	 * @param cause
	 */
	public DaoException(Throwable cause) {
		super(cause);
	}
}
