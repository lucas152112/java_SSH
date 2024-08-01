package com.oim.stores.store.service.impl;

import java.util.Map;

import com.oim.stores.common.Pagination;
import com.oim.stores.exception.DaoException;
import com.oim.stores.exception.ServiceException;
import com.oim.stores.store.dao.ShGroupTeamDao;
import com.oim.stores.store.domain.ShGroupTeam;
import com.oim.stores.store.domain.ShGroupTeamId;
import com.oim.stores.store.service.ShGroupTeamService;

public class ShGroupTeamServiceImpl implements ShGroupTeamService {

    private ShGroupTeamDao shGroupTeamDao;

    /**
     * @return the shGroupTeamDao
     */
    public ShGroupTeamDao getShGroupTeamDao() {
	return shGroupTeamDao;
    }

    /**
     * @param shGroupTeamDao
     *            the shGroupTeamDao to set
     */
    public void setShGroupTeamDao(ShGroupTeamDao shGroupTeamDao) {
	this.shGroupTeamDao = shGroupTeamDao;
    }

    @Override
    public void save(ShGroupTeam shGroupTeam) throws ServiceException {
	try {
	    shGroupTeamDao.save(shGroupTeam);
	} catch (DaoException e) {
	    throw new ServiceException(e);
	}
    }

    @Override
    public void delete(ShGroupTeamId shGroupTeamId) throws ServiceException {
	try {
	    shGroupTeamDao.delete(shGroupTeamId);
	} catch (DaoException e) {
	    throw new ServiceException(e);
	}
    }

    @Override
    public ShGroupTeam queryById(ShGroupTeamId shGroupTeamId)
	    throws ServiceException {
	try {
	    return shGroupTeamDao.queryById(shGroupTeamId);
	} catch (DaoException e) {
	    throw new ServiceException(e);
	}
    }

    @Override
    public Pagination queryForPage(Map<String, String> params, int pageNumber,
	    int pageSize) throws ServiceException {
	try {
	    return shGroupTeamDao.queryForPage(params, pageNumber, pageSize);
	} catch (DaoException e) {
	    throw new ServiceException(e);
	}
    }

}
