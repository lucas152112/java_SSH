package com.oim.stores.framework.sqlutils;

import java.util.LinkedHashMap;

public class HibernateSqlParameters extends
		LinkedHashMap<String, HibernateSqlParameter> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3219405642464633602L;

	/**
	 * 设置query参数集合
	 * @param parameterName
	 * @param value
	 * Author zhuangjf
	 * Create On 2013-3-14 上午10:48:08
	 */
	public void setValue(String parameterName, String value) {
		HibernateSqlParameter parameter= get(parameterName);
		parameter.setValue(value);
	}
}
