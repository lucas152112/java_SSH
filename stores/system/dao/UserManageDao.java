package com.oim.stores.system.dao;

import java.util.List;
import java.util.Map;

import com.oim.stores.common.Pagination;
import com.oim.stores.exception.DaoException;
import com.oim.stores.exception.ServiceException;
import com.oim.stores.store.domain.ShStoreUserRelation;
import com.oim.stores.store.domain.ShStoreUserRelationId;
import com.oim.stores.system.domain.SyRoleInfo;
import com.oim.stores.system.domain.UserManageBean;
import com.oim.stores.user.domain.SyUserInfo;
/**
 * 用户管理dao
 * @author yuyan
 *
 */
public interface UserManageDao {
	
	/**
	 * 修改用户角色信息
	 * 
	 * @param userInfo
	 */
	public void updateUserRole(ShStoreUserRelation userRole);

	/**
	 * 删除用户角色信息
	 * 
	 * @param userInfo
	 */
	public void delUserRole(ShStoreUserRelation userRole);
	/**
	 * save user;
	 * @param user
	 * @throws DaoException
	 */
	public void saveUser(SyUserInfo user) throws DaoException;
	/**
	 * 获取用户管理列表
	 * 
	 */
	public Pagination queryForPage(Map<String, String> params, int pageNumber, int pageSize) throws DaoException;
	/**
	 * 查询用户角色列表
	 * @param params
	 * @return
	 * @throws ServiceException
	 */
	public List<SyRoleInfo> query(Map<String, String> params) throws DaoException;
	/**
	 * 查询用户商家关系
	 * @param id
	 * @return
	 * @throws DaoException
	 */
	public ShStoreUserRelation queryStoreUserById(ShStoreUserRelationId id) throws DaoException;
	/**
	 * query user list;
	 * @param params
	 * @return
	 * @throws DaoException
	 */
	public List<UserManageBean> queryUserInfo(Map<String, String> params) throws DaoException;
}
