package com.oim.stores.store.dao;

import java.util.List;
import java.util.Map;

import com.oim.stores.common.Pagination;
import com.oim.stores.exception.DaoException;
import com.oim.stores.store.domain.ShStoreInfo;
import com.oim.stores.store.domain.ShStoreRoleRel;
/**
 * 商家角色关联dao
 * @author Administrator
 *
 */
public interface StoreRoleRelDao {
	/**
	 * query paging infomation;
	 * @param params
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 * @throws DaoException
	 */
    public Pagination queryForPage(Map<String, String> params, int pageNumber,
	    int pageSize) throws DaoException;
    /**
     * 更新商家角色关联
     * @param bean
     * @throws DaoException
     */
    public void updateStoreRoleRel(ShStoreRoleRel bean) throws DaoException;
    /**
     * According to the id query store role relation;
     * @param storeid
     * @param roleid
     * @return
     * @throws DaoException
     */
    public ShStoreRoleRel queryById(String storeid,String roleid) throws DaoException;
    /**
     * save the store role relation;
     * @param bean
     * @throws DaoException
     */
    public void saveStoreRoleRel(ShStoreRoleRel bean) throws DaoException;
    /**
     * delete the store role infomation;
     * @param bean
     * @throws DaoException
     */
    public void deleteStoreRoleRel(ShStoreRoleRel bean) throws DaoException;
    /**
     * According to the random parameters query store role infomation;
     * @param params
     * @return
     * @throws DaoException
     */
    public List<ShStoreRoleRel> query(Map<String, String> params) throws DaoException;
    /**
     * 查询商家信息
     * @param params
     * @return
     * @throws DaoException
     */
    public List<ShStoreInfo> queryStore(Map<String, String> params) throws DaoException;
}
