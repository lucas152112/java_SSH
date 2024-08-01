package com.oim.stores.store.dao;

import java.util.List;
import java.util.Map;

import com.oim.stores.common.Pagination;
import com.oim.stores.exception.DaoException;
import com.oim.stores.store.domain.ShStoreUserRelation;
import com.oim.stores.store.domain.ShStoreUserRelationId;
/**
 * 商家用户关联dao
 * @author Administrator
 *
 */
public interface StoreUserRelationDao {
	/**
	 * 保存商家用户关联信息
	 * @param storeUserRelation
	 * @throws DaoException
	 */
	public void save(ShStoreUserRelation storeUserRelation) throws DaoException;
	/**
	 * 更新商家用户关联关系
	 * @param storeUserRelation
	 * @throws DaoException
	 */
	public void update(ShStoreUserRelation storeUserRelation) throws DaoException;
	/**
	 * 保存商家关联信息
	 * @param surs
	 * @throws DaoException
	 */
	public void saveAll(List<ShStoreUserRelation> surs) throws DaoException;
	/**
	 * delete store related information
	 * @param storeUserRelation
	 * @throws DaoException
	 */
	public void delete(ShStoreUserRelation storeUserRelation) throws DaoException;
	/**
	 * query paging infomation
	 * @param params
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 * @throws DaoException
	 */
	public Pagination queryForPage(Map<String, String> params, int pageNumber, int pageSize) throws DaoException;
	/**
	 * 根据id查询商家用户关系
	 * @param id
	 * @return
	 * @throws DaoException
	 */
	public ShStoreUserRelation queryById(ShStoreUserRelationId id) throws DaoException;
	/**
	 * 根据商家id查询商家用户关联
	 * @param storeId
	 * @return
	 * @throws DaoException
	 */
	public List<ShStoreUserRelation> queryByStoreId(Long storeId) throws DaoException;
	/**
	 * According to the id query admin store user relation;
	 * @param storeId
	 * @param deroleId
	 * @return
	 * @throws DaoException
	 */
	public List<ShStoreUserRelation> queryAdminByStoreId(Long storeId,Long deroleId) throws DaoException;
	/**
	 * 删除商家
	 * @param storeId
	 * @throws DaoException
	 */
	public void deleteByStoreId(Long storeId) throws DaoException;
	/**
	 * 根据用户查询商家
	 * @param userId
	 * @return
	 * @throws DaoException
	 */
	public List<ShStoreUserRelation> queryByUserId(String userId) throws DaoException;
}
