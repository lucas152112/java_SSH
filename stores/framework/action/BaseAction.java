package com.oim.stores.framework.action;

import com.oim.stores.framework.constants.SystemKeyWord;
import com.oim.stores.framework.utils.BeanFactory;
import com.oim.stores.framework.web.HttpRequestObject;
import com.oim.stores.framework.web.RequestUtils;
import com.oim.stores.framework.web.ServletInputStreamParse;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class BaseAction implements Controller {
	private Log logger = LogFactory.getLog(BaseAction.class);

	/* 
	 * mvc控制器核心入口,分发command到业务逻辑层处理
	 * @see org.springframework.web.servlet.mvc.Controller#handleRequest(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * Author zhuangjf
	 * Create On 2013-3-14 上午10:42:11
	 */
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
	    	response.setCharacterEncoding("UTF-8");
		Map<String, HttpRequestObject> map = null;
		String type = RequestUtils.getExtractType(request);
		if (type == null
				|| !type.toLowerCase().startsWith("multipart/form-data")) {
			map = RequestUtils.getHttpRequestObject(request);
		} else {
			ServletInputStreamParse input = new ServletInputStreamParse(request);
			map = input.getHttpRequestObject();
			Map<String, HttpRequestObject> tmap = RequestUtils
					.getHttpRequestObject(request);
			Iterator iter = tmap.keySet().iterator();
			int pos = null == map ? 1 : map.size() + 1;
			while (iter.hasNext()) {
				String key = (String) iter.next();
				map.put(Integer.toString(pos), tmap.get(key));
			}
		}
		// Map<String, String> singleMap = RequestUtils.getNormalMap(map);
		request.setAttribute(SystemKeyWord.EWEB_REQUSET_PARAM_MAP, map);
		logger.debug("\r\n\t " + SystemKeyWord.EWEB_REQUSET_PARAM_MAP + ":"
				+ map);

		String ctrl_service = null;
		ctrl_service = RequestUtils.getParamString(map,
				SystemKeyWord.CTRL_SERVICE);
		logger.debug("\r\n\t " + SystemKeyWord.CTRL_SERVICE + "="
				+ ctrl_service);
		if (null == ctrl_service) {
			throw new IllegalArgumentException(SystemKeyWord.CTRL_SERVICE
					+ " isn't defined");
		}

		IHandleModelAndView imv = (IHandleModelAndView) BeanFactory
				.getInstance(request, ctrl_service);
		ModelAndView mv = imv.handleModelAndView(map, request, response);
		return mv;
	}
}
