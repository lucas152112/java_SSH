package com.oim.stores.store.service.impl;

import java.math.BigDecimal;

import java.util.List;
import java.util.Map;


import com.oim.stores.common.PageResponse;
import com.oim.stores.common.Pagination;
import com.oim.stores.exception.DaoException;
import com.oim.stores.exception.ServiceException;
import com.oim.stores.store.dao.StoreAccountDao;
import com.oim.stores.store.domain.ShStoreAccount;
import com.oim.stores.store.domain.ViewStoreAccount;
import com.oim.stores.store.service.StoreAccountService;

/**
 * 商家帐户service实现类
 */
public class StoreAccountServiceImpl implements StoreAccountService {
	private StoreAccountDao storeAccountDao;

	@Override
	public void save(ShStoreAccount storeAccount) throws ServiceException {
		try {
			storeAccountDao.save(storeAccount);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void delete(ShStoreAccount storeAccount) throws ServiceException {
		try {
			storeAccountDao.delete(storeAccount);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public ShStoreAccount queryById(BigDecimal storeAccountId) throws ServiceException {
		try {
			return storeAccountDao.queryById(storeAccountId);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	public void setStoreAccountDao(StoreAccountDao storeAccountDao) {
		this.storeAccountDao = storeAccountDao;
	}
	
	@Override
	public PageResponse queryAccViewForPage(Map<String, String> params,
			int pageNumber, int pageSize) throws ServiceException {
		PageResponse pageResponse = new PageResponse();
		try {
			Pagination pagination = storeAccountDao.queryAccForPage(params, pageNumber, pageSize);
			
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
	public ViewStoreAccount queryAccViewById(BigDecimal storeAccountId)
			throws ServiceException {
		try {
			return storeAccountDao.queryAccViewById(storeAccountId);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public ShStoreAccount queryAccById(Long storeAccountId)
			throws ServiceException {
		try {
			return storeAccountDao.queryAccById(storeAccountId);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<ViewStoreAccount> queryAccByParam(Map<String, String> params)
			throws ServiceException {
		try {
			return storeAccountDao.queryAccByParam(params);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}


}
