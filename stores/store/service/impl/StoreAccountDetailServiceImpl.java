package com.oim.stores.store.service.impl;

import java.util.Map;

import com.oim.stores.common.PageResponse;
import com.oim.stores.common.Pagination;
import com.oim.stores.exception.DaoException;
import com.oim.stores.exception.ServiceException;
import com.oim.stores.store.dao.StoreAccountDetailDao;
import com.oim.stores.store.domain.ShStoreAccountDetail;
import com.oim.stores.store.service.StoreAccountDetailService;

/**
 * 商家帐户详细service实现类
 */
public class StoreAccountDetailServiceImpl implements StoreAccountDetailService {
	private StoreAccountDetailDao storeAccountDetailDao;//商家账户详细dao

	@Override
	public void save(ShStoreAccountDetail storeAccount) throws ServiceException {
		try {
			storeAccountDetailDao.save(storeAccount);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void delete(ShStoreAccountDetail storeAccount) throws ServiceException {
		try {
			storeAccountDetailDao.delete(storeAccount);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public PageResponse queryForPage(Map<String, String> params, int pageNumber, int pageSize) throws ServiceException {
		PageResponse pageResponse = new PageResponse();
		try {
			Pagination pagination = storeAccountDetailDao.queryForPage(params, pageNumber, pageSize);
			pageResponse.setList(pagination.getList());
			pageResponse.setTotalRecord(pagination.getTotalRecord());
			pageResponse.setSuccess(true);
			return pageResponse;
		} catch (DaoException e) {
			pageResponse.setSuccess(false);
			throw new ServiceException(e);
		}
	}

	public void setStoreAccountDetailDao(StoreAccountDetailDao storeAccountDetailDao) {
		this.storeAccountDetailDao = storeAccountDetailDao;
	}

}
