package com.oim.stores.store.service;

import java.util.List;
import java.util.Map;

import com.oim.stores.common.PageResponse;
import com.oim.stores.datadict.domain.SyParameter;
import com.oim.stores.exception.ServiceException;
import com.oim.stores.store.domain.ShStoreCluster;
import com.oim.stores.store.domain.ShStoreInfo;
import com.oim.stores.store.domain.ShStoreRoleRel;
import com.oim.stores.store.domain.ShStoreRoleRelPk;
import com.oim.stores.store.domain.ShStoreUserRelation;
import com.oim.stores.store.domain.ViewStoreCluster;
import com.oim.stores.system.domain.SyRoleInfo;
/**
 * 商家管理service
 * @author Administrator
 *
 */
public interface StoreInfoService {
	/**
	 * 保存商家
	 * @param storeInfo
	 * @throws ServiceException
	 */
	public void save(ShStoreInfo storeInfo) throws ServiceException;
	/**
	 * update store;
	 * @param storeInfo
	 * @throws ServiceException
	 */
	public void update(ShStoreInfo storeInfo)throws ServiceException;
	/**
	 * 删除商家
	 * @param storeInfo
	 * @throws ServiceException
	 */
	public void delete(ShStoreInfo storeInfo) throws ServiceException;
	/**
	 * query paging infomation;
	 * @param params
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 * @throws ServiceException
	 */
	public PageResponse queryForPage(Map<String, String> params, int pageNumber, int pageSize) throws ServiceException;
	/**
	 * 根据商家名查询商家
	 * @param storeName
	 * @return
	 * @throws ServiceException
	 */
	public List<ShStoreInfo> queryByStoreName(String storeName) throws ServiceException;
	/**
	 * According to the id query stores;
	 * @param storeId
	 * @return
	 * @throws ServiceException
	 */
	public ShStoreInfo queryById(Long storeId) throws ServiceException;
	/**
	 * query store info ;
	 * @param storeId
	 * @return
	 * @throws ServiceException
	 */
	public ViewStoreCluster queryStoreById(Long storeId) throws ServiceException;
	/**
	 * 更新审核
	 * @param storeIds
	 * @param isAudit
	 * @param updateUser
	 * @param comment
	 * @return
	 * @throws ServiceException
	 */
	public boolean updateAudit(Long[] storeIds, int isAudit,Long updateUser,String comment) throws ServiceException;
	/**
	 * 查询系统商家
	 * @return
	 */
	public List<ShStoreInfo> getShStoreInfos();
	/**
	 * 查询商家角色关联
	 * @param storeId
	 * @return
	 */
	public List<ShStoreRoleRel> getStoreRoleRels(String storeId);
	/**
	 * 查询角色
	 * @return
	 */
	public List<SyRoleInfo> getRoles();
	/**
	 * 保存商家角色关系
	 * @param rel
	 * @throws ServiceException
	 */
	public void saveStoreRoleRel(ShStoreRoleRel rel) throws ServiceException;
	/**
	 * 获取商家角色
	 * @param id
	 * @return
	 * @throws ServiceException
	 */
	public ShStoreRoleRel getStoreRoleById(ShStoreRoleRelPk id) throws ServiceException;
	/**
	 * 查询商家账户关系
	 * @param storeId
	 * @return
	 */
	public List<ShStoreUserRelation> getStoreUserRel(String storeId);
	/**
	 * 查询系统参数
	 * @return
	 */
	public List<SyParameter> getProductSources();

	
	public void saveCluster(ShStoreCluster cluster);
	
	/**
	 * 根据客服电话获得商家
	 * @param cserviceTel
	 * @return
	 * @throws ServiceException
	 */
	public ShStoreInfo getStoreByCserviceTel(String cserviceTel) throws ServiceException;
	
}
