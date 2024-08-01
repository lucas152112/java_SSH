package com.oim.stores.framework.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.MethodUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.oim.stores.common.Cryto;
import com.oim.stores.common.EntityDao;
import com.oim.stores.framework.action.IHandleModelAndView;
import com.oim.stores.framework.constants.SystemKeyWord;
import com.oim.stores.framework.web.RequestUtils;

public abstract class BaseService extends EntityDao implements
	IHandleModelAndView {
    private final static Logger logger = Logger.getLogger(BaseService.class);
    protected String actQueryForPage = "jsonpage";
    protected String actCreate = "add";
    protected String actModify = "mod";
    protected String actDelete = "del";
    protected String actDetail = "view";
    protected String actQueryForJson = "json";
    protected String pageNumberName = "page";
    protected String pageSizeName = "rows";

    /* 
     * 执行业务方法并转发视图
     * @see com.oim.stores.framework.action.IHandleModelAndView#handleModelAndView(java.util.Map, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     * @param map
     * @param request
     * @param response
     * @return
     * @throws Exception
     * Author zhuangjf
     * Create On 2013-3-14 上午10:44:48
     */
    @Override
    public abstract ModelAndView handleModelAndView(Map map,
	    HttpServletRequest request, HttpServletResponse response)
	    throws Exception;
}
