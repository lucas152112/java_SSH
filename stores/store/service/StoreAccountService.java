package com.oim.stores.store.service;

import java.math.BigDecimal;

import java.util.List;
import java.util.Map;


import com.oim.stores.common.PageResponse;
import com.oim.stores.exception.ServiceException;
import com.oim.stores.store.domain.ShStoreAccount;
import com.oim.stores.store.domain.ViewStoreAccount;
/**
 * 商家账户管理service
 * @author Administrator
 *
 */
public interface StoreAccountService {
	/**
	 * 保存商家账号信息
	 * @param storeAccount
	 * @throws ServiceException
	 */
	public void save(ShStoreAccount storeAccount) throws ServiceException;
	/**
	 * Delete merchant account information
	 * @param storeAccount
	 * @throws ServiceException
	 */
	public void delete(ShStoreAccount storeAccount) throws ServiceException;
	/**
	 * According to the id query merchant account infomation;
	 * @param storeAccountId
	 * @return
	 * @throws ServiceException
	 */
	public ShStoreAccount queryById(BigDecimal storeAccountId) throws ServiceException;
	
	/**
	 * query account view infomation;
	 * @param params
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 * @throws ServiceException
	 */
	public PageResponse queryAccViewForPage(Map<String, String> params, int pageNumber, int pageSize) throws ServiceException;
	/**
	 * query account view;
	 * @param storeAccountId
	 * @return
	 * @throws ServiceException
	 */
	public ViewStoreAccount queryAccViewById(BigDecimal storeAccountId) throws ServiceException;
	/**
	 * query account bean;
	 * @param storeAccountId
	 * @return
	 * @throws ServiceException
	 */
	public ShStoreAccount queryAccById(Long storeAccountId) throws ServiceException;
	/**
	 * according to the params query account;
	 * @param params
	 * @return
	 * @throws ServiceException
	 */
	public List<ViewStoreAccount> queryAccByParam(Map<String, String> params)throws ServiceException;
}
