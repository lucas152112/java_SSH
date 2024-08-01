package com.oim.stores.framework.utils;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.support.RequestContextUtils;

/**
 * bean工厂类，用于spring通过名称获取对象
 * @author iampc
 *
 */
public class BeanFactory {
    public BeanFactory() {

    }

    /**
     * 在action中通过类映射名取实例
     * @param <T>
     * @param request
     * @param beanName
     * @return
     * Author zhuangjf
     * Create On 2013-3-14 上午11:00:56
     */
    public static <T> T getInstance(HttpServletRequest request, String beanName) {
	T instace = null;
	if (null != request) {
	    WebApplicationContext ctx = RequestContextUtils
		    .getWebApplicationContext(request);
	    instace = (T) ctx.getBean(beanName);
	}
	return instace;
    }

    /**
     * 通过类映射名取实例
     * @param <T>
     * @param beanName
     * @return
     * Author zhuangjf
     * Create On 2013-3-14 上午11:01:32
     */
    public static <T> T getInstance(String beanName) {
	T instace = null;
	instace = (T) AppContext.getInstance().getAppContext()
		.getBean(beanName);
	return instace;
    }
}
