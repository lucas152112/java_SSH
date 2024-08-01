package com.oim.stores.store.service.impl;

import java.util.Map;

import com.oim.stores.common.Pagination;
import com.oim.stores.exception.DaoException;
import com.oim.stores.exception.ServiceException;
import com.oim.stores.store.dao.ShDepositRelationDao;
import com.oim.stores.store.domain.ShDepositRelation;
import com.oim.stores.store.domain.ShDepositRelationId;
import com.oim.stores.store.service.ShDepositRelationService;

public class ShDepositRelationServiceImpl implements ShDepositRelationService {

    private ShDepositRelationDao shDepositRelationDao;

    /**
     * @return the shDepositRelationDao
     */
    public ShDepositRelationDao getShDepositRelationDao() {
	return shDepositRelationDao;
    }

    /**
     * @param shDepositRelationDao
     *            the shDepositRelationDao to set
     */
    public void setShDepositRelationDao(
	    ShDepositRelationDao shDepositRelationDao) {
	this.shDepositRelationDao = shDepositRelationDao;
    }

    @Override
    public Pagination queryForPage(Map<String, String> params, int pageNumber,
	    int pageSize) throws ServiceException {
	try {
	    return shDepositRelationDao.queryForPage(params, pageNumber,
		    pageSize);
	} catch (DaoException e) {
	    throw new ServiceException(e);
	}
    }

    @Override
    public ShDepositRelation queryById(ShDepositRelationId id)
	    throws ServiceException {
	try {
	    return shDepositRelationDao.queryById(id);
	} catch (DaoException e) {
	    throw new ServiceException(e);
	}
    }

    @Override
    public void save(ShDepositRelation bean) throws ServiceException {
	try {
	    shDepositRelationDao.save(bean);
	} catch (DaoException e) {
	    throw new ServiceException(e);
	}
    }

    @Override
    public void deleteById(ShDepositRelationId id) throws ServiceException {
	try {
	    shDepositRelationDao.deleteById(id);
	} catch (DaoException e) {
	    throw new ServiceException(e);
	}

    }

    @Override
    public Long queryDepositTeamStoreId(Long storeId) throws ServiceException {
	try {
	    return shDepositRelationDao.queryDepositTeamStoreId(storeId);
	} catch (DaoException e) {
	    throw new ServiceException(e);
	}
    }

    @Override
    public boolean isExistsDepositTeam(Long storeId) throws ServiceException {
	try {
	    return shDepositRelationDao.isExistsDepositTeam(storeId);
	} catch (DaoException e) {
	    throw new ServiceException(e);
	}
    }

}
