package com.oim.stores.framework.action;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

public interface IHandleModelAndView {
	public abstract ModelAndView handleModelAndView (Map map,
			HttpServletRequest request, 
			HttpServletResponse response) throws Exception ;
}
