package com.oim.stores.common;

import org.apache.log4j.Logger;
/**
 * 日志管理工具
 * @author Administrator
 *
 */
public class LogManager {
	private static Logger log = Logger.getLogger(Object.class);
	/**
	 * 错误异常信息
	 * @param msg
	 * @param e
	 */
	public static void error(String msg, Exception e) {
		log.error(msg + " ERROR:" + e.getMessage());
	}
	/**
	 * 错误日志信息
	 * @param msg
	 */
	public static void error(String msg) {
		log.error(msg);
	}
	/**
	 * 正常日志信息
	 * @param msg
	 */
	public static void info(String msg) {
		log.info(msg);
	}
	/**
	 * 调试信息
	 * @param msg
	 */
	public static void debug(String msg) {
		log.debug(msg);
	}
	/**
	 * 错误日志
	 * @param res
	 * @param title
	 * @param msg
	 */
	public static void errorLog(String res,String title,String msg){
		String info = "[" + res + "][" + title + "]" + "[异常信息：" + msg + "]";
		log.equals(info);
	}
}
