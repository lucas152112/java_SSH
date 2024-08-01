package com.oim.stores.store.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.oim.stores.common.Pagination;
import com.oim.stores.exception.DaoException;
import com.oim.stores.shop.domain.ShStoreSecurity;
import com.oim.stores.store.domain.ShStoreCluster;
import com.oim.stores.store.domain.ShStoreInfo;
import com.oim.stores.store.domain.ShStoreRoleRel;
import com.oim.stores.store.domain.ShStoreUserRelation;
import com.oim.stores.store.domain.ViewStoreCluster;
import com.oim.stores.system.domain.SyRoleInfo;
/**
 * 商家管理dao
 * @author Administrator
 *
 */
@SuppressWarnings("unchecked")
public interface StoreInfoDao {
	/**
	 * save stores
	 * @param storeInfo
	 * @throws DaoException
	 */
	public void save(ShStoreInfo storeInfo) throws DaoException;
	/**
	 * delete stores;
	 * @param storeInfo
	 * @throws DaoException
	 */
	public void delete(ShStoreInfo storeInfo) throws DaoException;
	/**
	 * query paging infomation;
	 * @param params
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 * @throws DaoException
	 */
	public Pagination queryForPage(Map<String, String> params, int pageNumber, int pageSize) throws DaoException;
	/**
	 * According to the id query stores;
	 * @param storeId
	 * @return
	 * @throws DaoException
	 */
	public ShStoreInfo queryById(Long storeId) throws DaoException;
	/**
	 * According to the storeName query stores;
	 * @param storeName
	 * @return
	 * @throws DaoException
	 */
	public List<ShStoreInfo> queryByStoreName(String storeName) throws DaoException;
	/**
	 * query all stores
	 * @param params
	 * @return
	 * @throws DaoException
	 */
	public List<ShStoreInfo> queryAll(Map<String, String> params) throws DaoException;
	/**
	 * Query system all businesses
	 * @return
	 */
	public List<ShStoreInfo> getShStoreInfos();
	/**
	 * 查询商家角色关系
	 * @param storeId
	 * @return
	 */
	public List<ShStoreRoleRel> getStoreRoleRels(String storeId);
	/**
	 * 查询系统角色
	 * @return
	 */
	public List<SyRoleInfo> getRoles();
	/**
	 * 保存商家角色关联
	 * @param rel
	 * @throws DaoException
	 */
	public void saveStoreRoleRel(ShStoreRoleRel rel) throws DaoException;
	/**
	 * 获取序列
	 * @return
	 */
	public BigDecimal getStoreSeq();
	/**
	 * 查询商家用户关联
	 * @param storeId
	 * @return
	 */
	public List<ShStoreUserRelation> getStoreUserRel(String storeId);
	
	/**
	 * 根据hql查询
	 * @param hql
	 * @return
	 */
	public List<?> getByHql(String hql);
	/**
	 * 根据id查询商家
	 * @param storeId
	 * @return
	 * @throws DaoException
	 */
	public ShStoreInfo queryStoreById(Long storeId) throws DaoException;
	/**
	 * query store bean;
	 * @param storeAccountId
	 * @return
	 * @throws DaoException
	 */
	public ViewStoreCluster queryStoreBeanById(Long storeId) throws DaoException;
	
	/**
	 * 根据商家Id获得商家密钥信息
	 * @param storeId 商家Id
	 * @return
	 * @throws DaoException
	 */
	public ShStoreSecurity getStoreSecurityByStoreId(BigDecimal storeId) throws DaoException;
	
	/**
	 * 根据密钥获得商家密钥信息
	 * @param key 商家密钥
	 * @return
	 * @throws DaoException
	 */
	public ShStoreSecurity getStoreSecurityByKey(String key) throws DaoException;
	
	/**
	 * 保存商家密钥
	 * @param security
	 * @throws DaoException
	 */
	public void saveStoreSecurity(ShStoreSecurity security) throws DaoException;
	
	/**
	 * 根据商家类型获得商家列表
	 * @param type
	 * @return
	 * @throws DaoException
	 */
	public List<ShStoreInfo> getStoreByStoreMode(String storeMode) throws DaoException;
	
	public void saveCluster(ShStoreCluster cluster);
	
}
