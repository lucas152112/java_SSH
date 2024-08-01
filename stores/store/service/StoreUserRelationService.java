package com.oim.stores.store.service;

import java.util.List;
import java.util.Map;

import com.oim.stores.common.PageResponse;
import com.oim.stores.exception.ServiceException;
import com.oim.stores.store.domain.ShStoreUserRelation;
import com.oim.stores.store.domain.ShStoreUserRelationId;

/**
 * 商家用户关联service						
 */
public interface StoreUserRelationService {
	/**
	 * 保存商家用户关联信息
	 * @param storeUserRelation
	 * @throws ServiceException
	 */
	public void save(ShStoreUserRelation storeUserRelation) throws ServiceException;
	/**
	 * 更新商家用户关联关系
	 * @param surs
	 * @throws ServiceException
	 */
	public void saveAll(List<ShStoreUserRelation> surs) throws ServiceException;
	/**
	 * delete store related information
	 * @param storeUserRelation
	 * @throws ServiceException
	 */
	public void delete(ShStoreUserRelation storeUserRelation) throws ServiceException;
	/**
	 * query paging infomation
	 * @param params
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 * @throws ServiceException
	 */
	public PageResponse queryForPage(Map<String, String> params, int pageNumber, int pageSize) throws ServiceException;
	/**
	 * 根据id查询商家用户关系
	 * @param id
	 * @return
	 * @throws ServiceException
	 */
	public ShStoreUserRelation queryById(ShStoreUserRelationId id) throws ServiceException;
	/**
	 * 根据商家id查询商家用户关联
	 * @param storeId
	 * @return
	 * @throws ServiceException
	 */
	public List<ShStoreUserRelation> queryByStoreId(Long storeId) throws ServiceException;
	/**
	 * 删除商家
	 * @param storeId
	 * @throws ServiceException
	 */
	public void deleteByStoreId(Long storeId) throws ServiceException;
	/**
	 * 是否在使用
	 * @param userId
	 * @return
	 * @throws ServiceException
	 */
	public boolean isUseUserId(String userId) throws ServiceException;
	/**
	 * 根据用户查询商家
	 * @param userId
	 * @return
	 * @throws ServiceException
	 */
	public ShStoreUserRelation queryByUserId(String userId) throws ServiceException;
	/**
	 * 根据商家id查询运营后台商家关联
	 * @param storeId
	 * @param deroleId
	 * @return
	 * @throws ServiceException
	 */
	public List<ShStoreUserRelation> queryAdminByStoreId(Long storeId,Long deroleId) throws ServiceException;
}
