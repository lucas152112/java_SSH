package com.oim.stores.system.service;

import java.util.List;
import java.util.Map;

import com.oim.stores.common.PageResponse;
import com.oim.stores.exception.ServiceException;
import com.oim.stores.system.domain.SyRoleInfo;
/**
 * 角色管理service
 * @author yuyan
 *
 */
public interface RoleManageService {
	/**
	 * 修改角色信息
	 * 
	 * @param userInfo
	 */
	public void updateRoleInfo(SyRoleInfo bean,long updateUser) throws ServiceException;

	/**
	 * 删除角色信息
	 * 
	 * @param userInfo
	 */
	public void delRoleInfo(long roleId) throws ServiceException;
	/**
	 * 获取管理列表
	 * 
	 */
	public PageResponse queryForPage(Map<String, String> params, int pageNumber, int pageSize) throws ServiceException;
	/**
	 * query busi list;
	 * @param params
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 * @throws ServiceException
	 */
	public PageResponse queryBusiForPage(Map<String, String> params, int pageNumber, int pageSize) throws ServiceException;
	/**
	 * 查询角色列表
	 * @param params
	 * @return
	 * @throws ServiceException
	 */
	public List<SyRoleInfo> query(Map<String, String> params) throws ServiceException;
	/**
	 * query busi role list;
	 * @param params
	 * @return
	 * @throws ServiceException
	 */
	public List<SyRoleInfo> queryBusi(Map<String, String> params) throws ServiceException;
	/**
	 * according to the param query the role;
	 * @param params
	 * @return
	 * @throws ServiceException
	 */
	public List<SyRoleInfo> queryRoleInfo(Map<String, String> params) throws ServiceException;
	/**
	 * save role;
	 * @param role
	 * @throws ServiceException
	 */
	public void saveRoleInfo(SyRoleInfo role)throws ServiceException;
	/**
	 * according to the id query the role;
	 * @param roleId
	 * @return
	 * @throws ServiceException
	 */
	public SyRoleInfo queryById(long roleId)throws ServiceException;
	/**
	 * 后台角色是否存在
	 * @param name
	 * @return
	 * @throws ServiceException
	 */
	public boolean hasRoleName(String name)throws ServiceException;
	/**
	 * 商家角色名称是否存在
	 * @param name
	 * @return
	 * @throws ServiceException
	 */
	public boolean hasBusiRoleName(String name)throws ServiceException;
}
