package com.oim.stores.system.dao;

import java.util.List;
import java.util.Map;

import com.oim.stores.common.Pagination;
import com.oim.stores.exception.DaoException;
import com.oim.stores.exception.ServiceException;
import com.oim.stores.system.domain.SyRoleInfo;
/**
 * 角色管理dao
 * @author yuyan
 *
 */
public interface RoleManageDao {
	/**
	 * 查询角色列表
	 * @param params
	 * @return
	 * @throws ServiceException
	 */
	public List<SyRoleInfo> query(Map<String, String> params) throws DaoException;
	/**
	 * 查询商家角色列表
	 * @param params
	 * @return
	 * @throws DaoException
	 */
	public List<SyRoleInfo> queryBusi(Map<String, String> params) throws DaoException;
	/**
	 * 获取角色管理列表
	 * 
	 */
	public Pagination queryForPage(Map<String, String> params, int pageNumber, int pageSize) throws DaoException;
	/**
	 * query busi list;
	 * @param params
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 * @throws DaoException
	 */
	public Pagination queryBusiForPage(Map<String, String> params, int pageNumber, int pageSize) throws DaoException;
	/**
	 * 修改角色信息
	 * 
	 * @param userInfo
	 */
	public void updateRole(SyRoleInfo role);

	/**
	 * 删除角色信息
	 * 
	 * @param userInfo
	 * @throws DaoException 
	 */
	public void delRole(SyRoleInfo role) throws DaoException;
	/**
	 * save role
	 * @param role
	 * @throws DaoException
	 */
	public void save(SyRoleInfo role) throws DaoException;
	/**
	 * according to the id query role;
	 * @param roleId
	 * @return
	 * @throws DaoException
	 */
	public SyRoleInfo queryById(long roleId)throws DaoException;
}
