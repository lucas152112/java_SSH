package com.oim.stores.system.service.impl;

import java.math.BigDecimal;
import java.util.List;

import com.oim.stores.exception.DaoException;
import com.oim.stores.exception.ServiceException;
import com.oim.stores.system.dao.SyRightDao;
import com.oim.stores.system.domain.SyAdminUser;
import com.oim.stores.system.domain.SyRight;
import com.oim.stores.system.service.SyRightService;

public class SyRightServiceImpl implements SyRightService {

    private SyRightDao syRightDao;

    @Override
    public List<SyRight> queryByUserName(String userName,
	    List<BigDecimal> rightRangeList, List<BigDecimal> rightTypeList)
	    throws ServiceException {
	try {
	    return syRightDao.queryByUserName(userName, rightRangeList,
		    rightTypeList);
	} catch (DaoException e) {
	    throw new ServiceException(e.getMessage(), e.getCause());
	}
    }

    /**
     * @return the syRightDao
     */
    public SyRightDao getSyRightDao() {
	return syRightDao;
    }

    /**
     * @param syRightDao
     *            the syRightDao to set
     */
    public void setSyRightDao(SyRightDao syRightDao) {
	this.syRightDao = syRightDao;
    }

    @Override
    public SyAdminUser queryByUserName(String userName) throws ServiceException {
	try{
	    return syRightDao.queryByUserName(userName);
	}catch(DaoException e){
	    throw new ServiceException(e.getMessage(), e.getCause());
	}
    }

}
