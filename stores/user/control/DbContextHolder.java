package com.oim.stores.user.control;
/**
 * 内容桩
 * @author yuyan
 *
 */
@SuppressWarnings("unchecked")
public class DbContextHolder {

	private static final ThreadLocal contextHolder=new ThreadLocal();//本地
	/**
	 * 设置type;
	 * @return
	 */
	public static String getDbType() {
		return (String) contextHolder.get();
	}
	/**
	 * 使用参数设置type
	 * @param dbType
	 */
	public static void setDbType(String dbType) {
		contextHolder.set(dbType);
	}
	/**
	 * 清理type内容;
	 */
	public static void clearDbType() {
		contextHolder.remove();
	}
}
