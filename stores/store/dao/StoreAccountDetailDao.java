package com.oim.stores.store.dao;

import java.util.Map;

import com.oim.stores.common.Pagination;
import com.oim.stores.exception.DaoException;
import com.oim.stores.store.domain.ShStoreAccountDetail;
/**
 * 商家详细信息dao
 * @author Administrator
 *
 */
public interface StoreAccountDetailDao {
	/**
	 * 保存商家账号明细
	 * @param storeAccountDetail
	 * @throws DaoException
	 */
	public void save(ShStoreAccountDetail storeAccountDetail) throws DaoException;
	/**
	 * delete account detail
	 * @param storeAccountDetail
	 * @throws DaoException
	 */
	public void delete(ShStoreAccountDetail storeAccountDetail) throws DaoException;
	/**
	 * Query paging information.
	 * @param params
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 * @throws DaoException
	 */
	public Pagination queryForPage(Map<String, String> params, int pageNumber, int pageSize) throws DaoException;

}
