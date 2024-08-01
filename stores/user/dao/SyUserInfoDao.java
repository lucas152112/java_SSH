package com.oim.stores.user.dao;

import java.util.List;
import java.util.Map;

import com.oim.stores.common.Pagination;
import com.oim.stores.exception.DaoException;
import com.oim.stores.user.domain.SyUserAccount;
import com.oim.stores.user.domain.SyUserInfo;
/**
 * 系统用户dao;
 * @author yuyan
 *
 */
public interface SyUserInfoDao {

	/**
	 * 保存用户信息
	 * 
	 * @param userInfo
	 */
	public void saveUserInfo(SyUserInfo userInfo);

	/**
	 * 修改用户信息
	 * 
	 * @param userInfo
	 */
	public void updateUserInfo(SyUserInfo userInfo);

	/**
	 * 添加用户账户信息
	 * 
	 * @param account
	 */
	public void saveUserAccount(SyUserAccount account);

	/**
	 * 根据用户名和密码获得用户信息
	 * 
	 * @param userName
	 * @param userpwd
	 * @return
	 */
	public SyUserInfo getUserInfoByNameAndpwd(String userName, String userpwd);

	/**
	 * 根据用户ID获得用户信息
	 * 
	 * @param userId
	 * @return 用户信息
	 */
	public SyUserInfo getUserInfoById(String userId);

	/**
	 * 根据用户名获得用户信息
	 * 
	 * @param userName
	 * @return
	 */
	public List<SyUserInfo> getUserInfoByName(String userName);

	/**
	 * 根据电子邮箱获得用户信息
	 * 
	 * @param userMail
	 * @return
	 */
	public List<SyUserInfo> getUserInfoByMail(String userMail);
	/**
	 * 获取注册用户列表
	 * 
	 */
	public Pagination queryForPage(Map<String, String> params, int pageNumber, int pageSize) throws DaoException;
	/**
	 * 查询用户列表
	 * @return
	 */
	public List<SyUserInfo> getUserList();
	/**
	 * 根据用户ID获得管理权限
	 * 
	 * @param userId
	 * @return 用户信息
	 */
	@SuppressWarnings("unchecked")
	public List getAdminById(String userId);
}
