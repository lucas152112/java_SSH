package com.oim.stores.system.service.impl;

import java.util.Map;

import com.oim.stores.common.PageResponse;
import com.oim.stores.common.Pagination;
import com.oim.stores.exception.DaoException;
import com.oim.stores.exception.ServiceException;
import com.oim.stores.system.dao.SchInfoDao;
import com.oim.stores.system.domain.SchInfoBean;
import com.oim.stores.system.service.SchInfoService;
/**
 * 任务管理service子类
 * @author Administrator
 *
 */
public class SchInfoServiceImpl implements SchInfoService {
    private SchInfoDao schinfoDao;//任务管理dao
    /**
     * @return the schinfoDao
     */
    public SchInfoDao getSchinfoDao() {
        return schinfoDao;
    }

    /**
     * @param schinfoDao the schinfoDao to set
     */
    public void setSchinfoDao(SchInfoDao schinfoDao) {
        this.schinfoDao = schinfoDao;
    }

    @Override
    public void updateSchInfo(SchInfoBean schinfo) throws ServiceException {
	    try {
		schinfoDao.updateSchInfo(schinfo);
	    } catch (DaoException e) {
		e.printStackTrace();
	    }
    }

    @Override
    public PageResponse queryForPage(Map<String, String> params,
	    int pageNumber, int pageSize) throws ServiceException {
	PageResponse pageResponse = new PageResponse();
	try {
		Pagination pagination = schinfoDao.queryForPage(params, pageNumber, pageSize);
		pageResponse.setList(pagination.getList());
		pageResponse.setTotalRecord(pagination.getTotalRecord());
		pageResponse.setSuccess(true);
		return pageResponse;
	} catch (DaoException e) {
		pageResponse.setSuccess(false);
		throw new ServiceException(e);
	}
    }

    @Override
    public SchInfoBean queryById(String schid) throws ServiceException {
	try {
	    return schinfoDao.queryById(schid);
	} catch (DaoException e) {
	    e.printStackTrace();
	}
	return null;
    }

}
