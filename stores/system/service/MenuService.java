package com.oim.stores.system.service;

import java.util.List;
import java.util.Map;

import com.oim.stores.exception.DaoException;
import com.oim.stores.exception.ServiceException;
import com.oim.stores.store.domain.ShStoreUserRelation;
import com.oim.stores.system.domain.MenuBean;
import com.oim.stores.system.domain.RoleMenuBean;
import com.oim.stores.system.domain.SyMenuList;
import com.oim.stores.system.domain.SyRoleMenuRelation;
/**
 * 菜单管理service
 * @author yuyan
 *
 */
public interface MenuService {
	/**
	 * 查询菜单列表
	 * @param params
	 * @return
	 * @throws ServiceException
	 */
	public List<SyMenuList> queryMenu(Map<String, String> params) throws ServiceException;
	/**
	 * 查询商家菜单列表
	 * @param params
	 * @return
	 * @throws ServiceException
	 */
	public List<SyMenuList> queryBusiMenu(Map<String, String> params) throws ServiceException;
	/**
	 * 修改菜单信息
	 * 
	 * @param userInfo
	 */
	public void updateMenu(SyMenuList menu)throws ServiceException;

	/**
	 * 删除菜单信息
	 * 
	 * @param userInfo
	 * @throws ServiceException 
	 */
	public void delMenu(SyMenuList menu) throws ServiceException;
	/**
	 * 保存菜单
	 * @param menu
	 * @throws ServiceException
	 */
	public void save(SyMenuList menu) throws ServiceException;
	/**
	 * According to the id query associated menu;
	 * @param menuId
	 * @return
	 * @throws ServiceException
	 */
	public SyMenuList queryById(long menuId)throws ServiceException;
	/**
	 * Query submenu
	 * @param parentId
	 * @return
	 * @throws ServiceException
	 */
	public List<SyMenuList> querySonById(String parentId)throws ServiceException;
	/**
	 * query business menu by parentId
	 * @param parentId
	 * @return
	 * @throws ServiceException
	 */
	public List<SyMenuList> queryBusiSonById(String parentId)throws ServiceException;
	/**
	 * 查询子菜单列表
	 * @param params
	 * @return
	 * @throws ServiceException
	 */
	public List<SyMenuList> querySonMenu(Map<String, String> params) throws ServiceException;
	/**
	 * Query business submenu
	 * @param params
	 * @return
	 * @throws ServiceException
	 */
	public List<SyMenuList> queryBusiSonMenu(Map<String, String> params) throws ServiceException;
	/**
	 * 删除菜单相关信息
	 * 
	 * @param userInfo
	 * @throws ServiceException 
	 */
	public void delMenuRelatin(String menuId) throws ServiceException;
	/**
	 * Query role menu Relations
	 * @param menuId
	 * @return
	 * @throws ServiceException
	 */
	public List<SyRoleMenuRelation> queryMenuRelation(String menuId) throws ServiceException;
	/**
	 * 查询功能树
	 * @param params
	 * @return
	 * @throws ServiceException
	 */
	public List<MenuBean> queryForTree(Map<String, String> params) throws ServiceException;
	/**
	 * Query role menu Relations bean;
	 * @param params
	 * @return
	 * @throws ServiceException
	 */
	public List<RoleMenuBean> queryRelation(Map<String, String> params) throws ServiceException;
	/**
	 * query all business role menu relations;
	 * @param params
	 * @return
	 * @throws ServiceException
	 */
	public List<RoleMenuBean> queryBusiRelation(Map<String, String> params) throws ServiceException;
	/**
	 * According to the id query store user Relations
	 * @param id
	 * @return
	 * @throws ServiceException
	 */
	public List<ShStoreUserRelation> queryStoreUserById(String id) throws ServiceException;
	/**
	 * 查询菜单
	 * @param params
	 * @return
	 * @throws ServiceException
	 */
	public List<RoleMenuBean> queryAllMenu(Map<String, String> params) throws ServiceException;
	/**
	 * query all menus for business;
	 * @param params
	 * @return
	 * @throws ServiceException
	 */
	public List<RoleMenuBean> queryBusiAllMenu(Map<String, String> params) throws ServiceException;
	/**
	 * 查询授权菜单
	 * @param params
	 * @return
	 * @throws DaoException
	 */
	public List<SyMenuList> queryRoleMenu(Map<String, String> params,String roleId) throws ServiceException;
}
