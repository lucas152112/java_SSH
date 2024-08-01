package com.oim.stores.store.service;

import java.util.Map;

import com.oim.stores.common.PageResponse;
import com.oim.stores.exception.ServiceException;
import com.oim.stores.store.domain.ShStoreAccountDetail;
/**
 * 商家账户明细
 * @author Administrator
 *
 */
public interface StoreAccountDetailService {
	/**
	 * 保存商家账号明细
	 * @param storeAccountDetail
	 * @throws ServiceException
	 */
	public void save(ShStoreAccountDetail storeAccountDetail) throws ServiceException;
	/**
	 * delete account detail
	 * @param storeAccountDetail
	 * @throws ServiceException
	 */
	public void delete(ShStoreAccountDetail storeAccountDetail) throws ServiceException;
	/**
	 * Query paging information.
	 * @param params
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 * @throws ServiceException
	 */
	public PageResponse queryForPage(Map<String, String> params, int pageNumber, int pageSize) throws ServiceException;
}
