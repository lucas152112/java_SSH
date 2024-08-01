package com.oim.stores.system.service;

import java.util.List;
import java.util.Map;

import com.oim.stores.common.PageResponse;
import com.oim.stores.exception.ServiceException;
import com.oim.stores.system.domain.SyMenuList;
import com.oim.stores.system.domain.SyRoleMenuRelation;
import com.oim.stores.system.domain.UserMenuBean;
/**
 * 权限管理service
 * @author yuyan;
 *
 */
public interface PowerMngService {
	/**
	 * 查询菜单列表
	 * @param params
	 * @return
	 * @throws ServiceException
	 */
	public List<SyMenuList> queryMenu(Map<String, String> params) throws ServiceException;
	/**
	 * 获取用户列表
	 * 
	 */
	public PageResponse queryForPage(Map<String, String> params, int pageNumber, int pageSize) throws ServiceException;
	/**
	 * 获取角色列表
	 * 
	 */
	public PageResponse queryRoleForPage(Map<String, String> params, int pageNumber, int pageSize) throws ServiceException;
	/**
	 * 修改用户菜单信息
	 * 
	 * @param userInfo
	 */
	public void updateUserMenu(UserMenuBean userMenu)throws ServiceException;

	/**
	 * 删除用户菜单信息
	 * 
	 * @param userInfo
	 * @throws ServiceException 
	 */
	public void delUserMenu(SyRoleMenuRelation userMenu) throws ServiceException;
	/**
	 * save the role menu;
	 * @param userMenu
	 * @throws ServiceException
	 */
	public void save(SyRoleMenuRelation userMenu) throws ServiceException;
	/**
	 * According to the id query the user menu;
	 * @param params
	 * @return
	 * @throws ServiceException
	 */
	public UserMenuBean queryById(Map<String,String> params)throws ServiceException;
	/**
	 * According to the random parameter query the user menu;
	 * @param params
	 * @return
	 * @throws ServiceException
	 */
	public UserMenuBean queryRoleMenuById(Map<String,String> params)throws ServiceException;
	/**
	 * 
	 * @param params
	 * @return
	 * @throws ServiceException
	 */
	public SyRoleMenuRelation queryRelationById(Map<String,String> params)throws ServiceException;
	/**
	 * 查询子菜单列表
	 * @param params
	 * @return
	 * @throws ServiceException
	 */
	public List<SyMenuList> querySonMenu(Map<String, String> params) throws ServiceException;
	/**
	 * According to the random parameter query role menu relation;
	 * @param params
	 * @return
	 * @throws ServiceException
	 */
	public List<SyRoleMenuRelation> queryRoleRelation(Map<String, String> params)throws ServiceException;
	/**
	 * save  role menu relation;
	 * @param relalist
	 * @throws ServiceException
	 */
	public void saveRoleRelation(List<SyRoleMenuRelation> relalist)throws ServiceException;
	/**
	 * delete role relation;
	 * @param relalist
	 * @throws ServiceException
	 */
	public void deleteRoleRelation(List<SyRoleMenuRelation> relalist)throws ServiceException;
}
