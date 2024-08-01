package com.oim.stores.framework.utils;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;



/**
 * spring上下文工具类
 * @author zhuangjf
 *
 */
public class AppContext {
	private static AppContext instance;

	private AbstractApplicationContext appContext;

	public synchronized static AppContext getInstance() {
		if (instance == null) {
			instance = new AppContext();
		}
		return instance;
	}

	private AppContext() {
		this.appContext = new ClassPathXmlApplicationContext(
				"classpath:/conf/spring/applicationContext*.xml");
	}

	public AbstractApplicationContext getAppContext() {
		return appContext;
	}
}
