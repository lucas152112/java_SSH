package com.oim.stores.system.service;

import java.util.Map;

import com.oim.stores.common.PageResponse;
import com.oim.stores.exception.ServiceException;
import com.oim.stores.system.domain.SchInfoBean;
/**
 * 任务管理service
 * @author Administrator
 *
 */
public interface SchInfoService {
    /**
     * udpate config;
     * @param schinfo
     * @throws ServiceException
     */
    public void updateSchInfo(SchInfoBean schinfo)throws  ServiceException;
    
    /**
     * According to the random parameter query;
     * @param params
     * @param pageNumber
     * @param pageSize
     * @return
     * @throws ServiceException
     */
    public PageResponse queryForPage(Map<String, String> params, int pageNumber, int pageSize) throws ServiceException;
    /**
     * According to the id query task;
     * @param schid
     * @return
     * @throws ServiceException
     */
    public SchInfoBean queryById(String schid)throws ServiceException;
}
