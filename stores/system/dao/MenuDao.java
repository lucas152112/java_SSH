package com.oim.stores.system.dao;

import java.util.List;
import java.util.Map;

import com.oim.stores.common.Pagination;
import com.oim.stores.exception.DaoException;
import com.oim.stores.exception.ServiceException;
import com.oim.stores.store.domain.ShStoreUserRelation;
import com.oim.stores.system.domain.MenuBean;
import com.oim.stores.system.domain.RoleMenuBean;
import com.oim.stores.system.domain.SyMenuList;
import com.oim.stores.system.domain.SyRoleMenuRelation;
/**
 * 菜单管理dao
 * @author yuyan
 *
 */
public interface MenuDao {
	/**
	 * 查询菜单列表
	 * @param params
	 * @return
	 * @throws ServiceException
	 */
	public List<SyMenuList> query(Map<String, String> params) throws DaoException;
	/**
	 * 查询商家菜单列表
	 * @param params
	 * @return
	 * @throws DaoException
	 */
	public List<SyMenuList> queryBusiMenu(Map<String, String> params) throws DaoException;
	/**
	 * 获取菜单管理列表
	 * 
	 */
	public Pagination queryForPage(Map<String, String> params, int pageNumber, int pageSize) throws DaoException;
	/**
	 * 修改菜单信息
	 * 
	 * @param userInfo
	 */
	public void updateMenu(SyMenuList menu)throws DaoException;

	/**
	 * 删除菜单信息
	 * 
	 * @param userInfo
	 * @throws DaoException 
	 */
	public void delMenu(SyMenuList menu) throws DaoException;
	/**
	 * 保存菜单
	 * @param menu
	 * @throws DaoException
	 */
	public void save(SyMenuList menu) throws DaoException;
	/**
	 * According to the id query associated menu;
	 * @param menuId
	 * @return
	 * @throws DaoException
	 */
	public SyMenuList queryById(long menuId)throws DaoException;
	/**
	 * Query submenu
	 * @param parentId
	 * @return
	 * @throws DaoException
	 */
	public List<SyMenuList> querySonById(String parentId)throws DaoException;
	/**
	 * Query business submenu
	 * @param parentId
	 * @return
	 * @throws DaoException
	 */
	public List<SyMenuList> queryBusiSonById(String parentId)throws DaoException;
	/**
	 * 查询子菜单列表
	 * @param params
	 * @return
	 * @throws ServiceException
	 */
	public List<SyMenuList> querySonMenu(Map<String, String> params) throws DaoException;
	/**
	 * 删除菜单相关信息
	 * 
	 * @param userInfo
	 * @throws DaoException 
	 */
	public void delMenuRelation(List<SyRoleMenuRelation> menu) throws DaoException;
	/**
	 * Query role menu Relations
	 * @param menuId
	 * @return
	 * @throws DaoException
	 */
	public List<SyRoleMenuRelation> queryMenuRelation(String menuId) throws DaoException;
	/**
	 * 查询功能树
	 * @param params
	 * @return
	 * @throws ServiceException
	 */
	public List<MenuBean> queryForTree(Map<String, String> params) throws DaoException;
	/**
	 * Query role menu Relations bean;
	 * @param params
	 * @return
	 * @throws DaoException
	 */
	public List<RoleMenuBean> queryRelation(Map<String, String> params) throws DaoException;
	/**
	 * According to the id query store user Relations
	 * @param id
	 * @return
	 * @throws DaoException
	 */
	public List<ShStoreUserRelation> queryStoreUserById(String id) throws DaoException;
	/**
	 * query all menu;
	 * @param params
	 * @return
	 * @throws DaoException
	 */
	public List<RoleMenuBean> queryAllMenu(Map<String, String> params) throws DaoException;
	/**
	 * query all business role menu relations;
	 * @param params
	 * @return
	 * @throws DaoException
	 */
	public List<RoleMenuBean> queryBusiRelation(Map<String, String> params) throws DaoException;
	/**
	 * query all business menu;
	 * @param params
	 * @return
	 * @throws DaoException
	 */
	public List<RoleMenuBean> queryBusiAllMenu(Map<String, String> params) throws DaoException;
	/**
	 * 查询授权菜单
	 * @param params
	 * @return
	 * @throws DaoException
	 */
	public List<SyMenuList> queryRoleMenu(Map<String, String> params,String roleId) throws DaoException;
}
