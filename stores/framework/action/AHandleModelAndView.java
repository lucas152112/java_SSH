package com.oim.stores.framework.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.beanutils.MethodUtils;
import org.springframework.web.servlet.ModelAndView;

import com.oim.stores.framework.constants.SystemKeyWord;
import com.oim.stores.framework.utils.BeanFactory;
import com.oim.stores.framework.web.RequestUtils;

public class AHandleModelAndView implements IHandleModelAndView {

    protected static final String RETURN_CODE = "result";
    protected static final String RETURN_DESC = "desc";
    protected static final String resultOk = "1";
    protected static final String resultErr = "0";

    @Override
    public ModelAndView handleModelAndView(Map map, HttpServletRequest request,
	    HttpServletResponse response) throws Exception {
	try {
	    String ctrl_method = RequestUtils.getParamString(map,
		    SystemKeyWord.CTRL_METHOD);
	    Map<String, String> singleMap = RequestUtils.getNormalMap(map);
	    Map<String, List<String>> nameIdxMap = RequestUtils
		    .getGroupByNameParam(map);
	    ModelAndView mv = (ModelAndView) MethodUtils.invokeMethod(this,
		    ctrl_method, new Object[] { request, response, singleMap },
		    new Class[] { HttpServletRequest.class,
			    HttpServletResponse.class, Map.class });
	    // ModelAndView mv = (ModelAndView) MethodUtils.invokeMethod(this,
	    // ctrl_method, new Object[] { singleMap },
	    // new Class[] { Map.class });
	    return mv;
	} catch (Exception e) {
	    throw e;
	}
    }

    protected ModelAndView dispatchDefMv(JSONObject json) {
	ModelAndView mv = new ModelAndView();
	String viewName = SystemKeyWord.JSON_VIEW;
	mv.setViewName(viewName);
	mv.addObject(SystemKeyWord.PAGE_JSON_ATTR, json.toString());
	return mv;
    }

    protected ModelAndView dispatchDefMv(String content) {
	ModelAndView mv = new ModelAndView();
	String viewName = SystemKeyWord.JSON_VIEW;
	mv.setViewName(viewName);
	mv.addObject(SystemKeyWord.PAGE_JSON_ATTR, content);
	return mv;
    }

    /**
     * @param total
     * @param content
     * @return
     * Author zhuangjf
     * Create On 2013-4-22 上午10:24:29
     */
    protected ModelAndView dispatchPageMv(String total, String content) {
	ModelAndView mv = new ModelAndView();
	String viewName = SystemKeyWord.JSON_PAGE_VIEW;
	mv.setViewName(viewName);
	mv.addObject("total", total);
	mv.addObject(SystemKeyWord.PAGE_JSON_ATTR, content);
	return mv;
    }
}
