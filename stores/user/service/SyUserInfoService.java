package com.oim.stores.user.service;

import java.util.List;
import java.util.Map;

import com.oim.stores.common.PageResponse;
import com.oim.stores.exception.ServiceException;
import com.oim.stores.user.domain.SyUserInfo;
/**
 * 系统用户service;
 * @author Administrator
 *
 */
public interface SyUserInfoService {

	/**
	 * 用户注册
	 * 
	 * @param userInfo
	 *            用户信息
	 * @return true：成功，false：失败
	 */
	public boolean registration(SyUserInfo userInfo);

	/**
	 * 根据用户名和密码获得用户信息
	 * 
	 * @param userName
	 * @param userpwd
	 * @return
	 */
	public SyUserInfo getUserInfoByNameAndpwd(String userName, String userpwd);

	/**
	 * 登录名是否存在
	 * 
	 * @param userName
	 * @return true：存在，false：不存在
	 */
	public boolean hasLoginName(String userName);

	/**
	 * 根据用户Id获得用户信息
	 * 
	 * @param userId
	 * @return 用户信息
	 */
	public SyUserInfo getUserInfoById(String userId);

	/**
	 * 修改密码
	 * 
	 * @param userId
	 * @param userPwd
	 */
	public void updatePwd(String userId, String userPwd);

	/**
	 * 电子邮箱是否已注册
	 * 
	 * @param userMail
	 * @return true：已注册，false：未注册
	 */
	public boolean hasUserMail(String userMail);

	/**
	 * 找回密码
	 * 
	 * @param userInfo
	 *            用户信息
	 */
	public void pwdBack(SyUserInfo userInfo);

	/**
	 * 修改个人资料
	 * 
	 * @param userInfo
	 */
	public void updateUser(SyUserInfo userInfo);

	/**
	 * 查用户名
	 * 
	 * @param userName
	 * @return
	 */
	public List<SyUserInfo> getUserInfoByName(String userName);
	/**
	 * 查询用户列表
	 * @param params
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 * @throws ServiceException
	 */
	public PageResponse queryForPage(Map<String, String> params, int pageNumber, int pageSize) throws ServiceException;
	/**
	 * 密码重置
	 * @param userInfo
	 */
	public void findBackPwd(SyUserInfo userInfo,String basepath);
	/**
	 * 是否是管理员
	 * 
	 * @param userId
	 * @return true：
	 */
	public boolean isAdmin(String userId);
}
