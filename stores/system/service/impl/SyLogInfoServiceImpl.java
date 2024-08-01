package com.oim.stores.system.service.impl;

import com.oim.stores.exception.DaoException;
import com.oim.stores.exception.ServiceException;
import com.oim.stores.system.dao.SyLogInfoDao;
import com.oim.stores.system.domain.SyLogInfo;
import com.oim.stores.system.service.SyLogInfoService;

public class SyLogInfoServiceImpl implements SyLogInfoService {

    private SyLogInfoDao syLogInfoDao;
    
    /**
     * @return the syLogInfoDao
     */
    public SyLogInfoDao getSyLogInfoDao() {
        return syLogInfoDao;
    }

    /**
     * @param syLogInfoDao the syLogInfoDao to set
     */
    public void setSyLogInfoDao(SyLogInfoDao syLogInfoDao) {
        this.syLogInfoDao = syLogInfoDao;
    }

    @Override
    public void saveLog(SyLogInfo bean) throws ServiceException {
	try {
	    syLogInfoDao.saveLog(bean);
	} catch (DaoException e) {
	    throw new ServiceException(e);
	}
    }

}
