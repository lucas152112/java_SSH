package com.oim.stores.store.dao;

import java.math.BigDecimal;

import java.util.List;
import java.util.Map;


import com.oim.stores.common.Pagination;
import com.oim.stores.exception.DaoException;
import com.oim.stores.exception.ServiceException;
import com.oim.stores.store.domain.ShStoreAccount;
import com.oim.stores.store.domain.ViewStoreAccount;
/**
 * 商家账号信息dao
 * @author Administrator
 *
 */
public interface StoreAccountDao {
	/**
	 * 保存商家账号信息
	 * @param storeAccount
	 * @throws DaoException
	 */
	public void save(ShStoreAccount storeAccount) throws DaoException;
	/**
	 * Delete merchant account information
	 * @param storeAccount
	 * @throws DaoException
	 */
	public void delete(ShStoreAccount storeAccount) throws DaoException;
	/**
	 * According to the id query merchant account infomation;
	 * @param storeAccountId
	 * @return
	 * @throws DaoException
	 */
	public ShStoreAccount queryById(BigDecimal storeAccountId) throws DaoException;
	
	/**
	 * 根据账户类别获得商家账户
	 * @param type
	 * @return
	 */
	public List<ShStoreAccount> getStoreAccountByType(String type);
	
	/**
	 * 根据商家Id和商家类别获得商家账户
	 * @param storeId
	 * @param type
	 * @return
	 */
	public ShStoreAccount getStoreAccountByStoreIdAndType(String storeId,String type);
	
	/**
	 * 查询商户分页；
	 * @param params
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 * @throws DaoException
	 */
	public Pagination queryAccForPage(Map<String, String> params, int pageNumber, int pageSize) throws DaoException;
	/**
	 * query account view info;
	 * @param storeAccountId
	 * @return
	 * @throws DaoException
	 */
	public ViewStoreAccount queryAccViewById(BigDecimal storeAccountId) throws DaoException;
	/**
	 * query account bean;
	 * @param storeAccountId
	 * @return
	 * @throws DaoException
	 */
	public ShStoreAccount queryAccById(Long storeAccountId) throws DaoException;
	/**
	 * according to the params query account;
	 * @param params
	 * @return
	 * @throws DaoException
	 */
	public List<ViewStoreAccount> queryAccByParam(Map<String, String> params)throws DaoException;
}
