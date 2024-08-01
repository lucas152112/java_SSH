package com.oim.stores.system.dao;

import java.util.List;
import java.util.Map;

import com.oim.stores.common.Pagination;
import com.oim.stores.exception.DaoException;
import com.oim.stores.exception.ServiceException;
import com.oim.stores.system.domain.SyMenuList;
import com.oim.stores.system.domain.SyRoleMenuRelation;
import com.oim.stores.system.domain.UserMenuBean;
/**
 * 权限管理dao
 * @author yuyan
 *
 */
public interface PowerMngDao {
	/**
	 * 查询菜单列表
	 * @param params
	 * @return
	 * @throws ServiceException
	 */
	public List<SyMenuList> queryMenu(Map<String, String> params) throws DaoException;
	/**
	 * 获取用户列表
	 * 
	 */
	public Pagination queryForPage(Map<String, String> params, int pageNumber, int pageSize) throws DaoException;
	/**
	 * 获取角色列表
	 * 
	 */
	public Pagination queryRoleForPage(Map<String, String> params, int pageNumber, int pageSize) throws DaoException;
	/**
	 * 修改用户菜单信息
	 * 
	 * @param userInfo
	 */
	public void updateUserMenu(SyRoleMenuRelation userMenu)throws DaoException;

	/**
	 * 删除用户菜单信息
	 * 
	 * @param userInfo
	 * @throws DaoException 
	 */
	public void delUserMenu(SyRoleMenuRelation userMenu) throws DaoException;
	/**
	 * save the role menu;
	 * @param userMenu
	 * @throws DaoException
	 */
	public void save(SyRoleMenuRelation userMenu) throws DaoException;
	/**
	 * According to the id query user menu;
	 * @param params
	 * @return
	 * @throws DaoException
	 */
	public List<UserMenuBean> queryById(Map<String,String> params)throws DaoException;
	/**
	 * query user menu ;
	 * @param params
	 * @return
	 * @throws DaoException
	 */
	public List<UserMenuBean> queryRoleById(Map<String,String> params)throws DaoException;
	/**
	 * query the role menu relations;
	 * @param params
	 * @return
	 * @throws DaoException
	 */
	public SyRoleMenuRelation queryRelationById(Map<String,String> params)throws DaoException;
	/**
	 * 查询子菜单列表
	 * @param params
	 * @return
	 * @throws ServiceException
	 */
	public List<SyMenuList> querySonMenu(Map<String, String> params) throws DaoException;
	/**
	 * query the role menu relations;
	 * @param params
	 * @return
	 * @throws DaoException
	 */
	public List<SyRoleMenuRelation> queryRoleRelation(Map<String, String> params)throws DaoException;
	/**
	 * save the role menu relation;
	 * @param relalist
	 * @throws DaoException
	 */
	public void saveRoleRelation(List<SyRoleMenuRelation> relalist)throws DaoException;
	/**
	 * delete the role relations;
	 * @param relalist
	 * @throws DaoException
	 */
	public void deleteRoleRelation(List<SyRoleMenuRelation> relalist)throws DaoException;
}
