package com.oim.stores.system.service;

import java.util.List;
import java.util.Map;

import com.oim.stores.common.PageResponse;
import com.oim.stores.exception.ServiceException;
import com.oim.stores.store.domain.ShStoreUserRelation;
import com.oim.stores.store.domain.ShStoreUserRelationId;
import com.oim.stores.system.domain.SyRoleInfo;
import com.oim.stores.system.domain.UserManageBean;
import com.oim.stores.user.domain.SyUserInfo;
/**
 * 用户管理service
 * @author yuyan
 *
 */
public interface UserManageService {
	/**
	 * 修改用户角色信息
	 * 
	 * @param userInfo
	 */
	public void updateUserInfo(UserManageBean bean,long updateUser) throws ServiceException;

	/**
	 * 删除用户信息
	 * 
	 * @param userInfo
	 */
	public void delUserInfo(long userId,long storeId) throws ServiceException;
	/**
	 * save user;
	 * @param user
	 * @param updateUser
	 * @throws ServiceException
	 */
	public void saveUserInfo(SyUserInfo user,long updateUser) throws ServiceException;
	/**
	 * save store infomation;
	 * @param user
	 * @throws ServiceException
	 */
	public void saveStoreInfo(ShStoreUserRelation user) throws ServiceException;
	/**
	 * According to the id query store user Relations
	 * @param id
	 * @return
	 * @throws ServiceException
	 */
	public ShStoreUserRelation queryStoreById(ShStoreUserRelationId id) throws ServiceException;
	/**
	 * 获取用户管理列表
	 * 
	 */
	public PageResponse queryForPage(Map<String, String> params, int pageNumber, int pageSize) throws ServiceException;
	/**
	 * 查询用户角色列表
	 * @param params
	 * @return
	 * @throws ServiceException
	 */
	public List<SyRoleInfo> query(Map<String, String> params) throws ServiceException;
	/**
	 * query the user infomation;
	 * @param params
	 * @return
	 * @throws ServiceException
	 */
	public List<UserManageBean> queryUserInfo(Map<String, String> params) throws ServiceException;
	/**
	 * 电子邮箱是否已注册
	 * 
	 * @param userMail
	 * @return true：已注册，false：未注册
	 */
	public boolean hasUserMail(String userMail) throws ServiceException;
	/**
	 * 用户名是否存在
	 * @param name
	 * @return
	 * @throws ServiceException
	 */
	public boolean hasLoginName(String name) throws ServiceException;
}
