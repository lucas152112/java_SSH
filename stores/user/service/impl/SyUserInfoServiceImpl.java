package com.oim.stores.user.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.oim.stores.common.Cryto;
import com.oim.stores.common.MailSenderInfo;
import com.oim.stores.common.PageResponse;
import com.oim.stores.common.Pagination;
import com.oim.stores.common.SimpleMailSender;
import com.oim.stores.exception.DaoException;
import com.oim.stores.exception.ServiceException;
import com.oim.stores.user.dao.SyUserInfoDao;
import com.oim.stores.user.domain.SyUserAccount;
import com.oim.stores.user.domain.SyUserInfo;
import com.oim.stores.user.service.SyUserInfoService;
/**
 * 系统用户service子类
 * @author yuyan
 *
 */
public class SyUserInfoServiceImpl implements SyUserInfoService {

	public SyUserInfoDao syUserInfoDao;

	/**
	 * 用户注册
	 */
	public boolean registration(SyUserInfo userInfo) {
		try {
			userInfo.setUserPwd(Cryto.cryptMD5ToHEX(userInfo.getUserPwd()));
			userInfo.setUserFrom(new Long(1));
			userInfo.setUserStatus(new Long(1));
			userInfo.setRegDate(new Date());
			userInfo.setUpdateDate(new Date());
			userInfo.setUserSex(new Long(2));
			syUserInfoDao.saveUserInfo(userInfo);
			SyUserAccount account = new SyUserAccount();
			account.setUserId(new BigDecimal(userInfo.getUserId()));
			account.setAccountType(new BigDecimal(1));
			account.setAmount(Double.parseDouble("0"));
			account.setAccountStatus(new BigDecimal(1));
			account.setAddDate(new Date());
			account.setUpdateDate(new Date());
			account.setUserId(new BigDecimal(userInfo.getUserId()));
			syUserInfoDao.saveUserAccount(account);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	/**
	 * 根据用户名和密码获得用户信息
	 */
	public SyUserInfo getUserInfoByNameAndpwd(String userName, String userpwd) {
		userpwd = Cryto.cryptMD5ToHEX(userpwd);
		return syUserInfoDao.getUserInfoByNameAndpwd(userName.trim(), userpwd);
	}

	/**
	 * 用户名是否存在
	 */
	public boolean hasLoginName(String userName) {
		List<SyUserInfo> userList = syUserInfoDao.getUserInfoByName(userName);
		return userList != null && userList.size() > 0;
	}

	/**
	 * 根据用户ID获得用户信息
	 */
	public SyUserInfo getUserInfoById(String userId) {
		return syUserInfoDao.getUserInfoById(userId);
	}

	/**
	 * 电子邮箱是否已注册
	 */
	public boolean hasUserMail(String userMail) {
		List<SyUserInfo> userList = syUserInfoDao.getUserInfoByMail(userMail);
		return userList != null && userList.size() > 0;
	}

	/**
	 * 找回密码
	 */
	public void pwdBack(SyUserInfo userInfo) {
		List<SyUserInfo> userList = syUserInfoDao.getUserInfoByName(userInfo.getUserName());
		if (userList != null && userList.size() > 0) {
			SyUserInfo user = userList.get(0);
			user.setUserPwd(Cryto.cryptMD5ToHEX(userInfo.getUserPwd()));
			syUserInfoDao.updateUserInfo(user); // 修改用户信息

			sendMail(userInfo); // 发送邮件
		}
	}

	@SuppressWarnings("static-access")
	public void findBackPwd(SyUserInfo userInfo,String basepath){
		//sendMail(userInfo); // 发送邮件
		MailSenderInfo mailInfo = new MailSenderInfo();
		mailInfo.setMailServerHost("smtp.163.com");
		mailInfo.setMailServerPort("25");
		mailInfo.setLocalHost("163.com");
		mailInfo.setValidate(true);
		mailInfo.setUserName("czw861021@163.com");
		mailInfo.setPassword("7286871904");// 您的邮箱密码
		mailInfo.setFromAddress("czw861021@163.com");

		mailInfo.setToAddress(userInfo.getUserMail());
		mailInfo.setSubject("找回密码");

		StringBuilder content = new StringBuilder("您的登录名是：").append(userInfo.getUserName()+",点击下面的链接进行密码重置")//.append("<br/>您的密码是：" + userInfo.getUserPwd());
		.append("<br/>" +
				"<br/><a href='").append(basepath).append("/index/findPwdLink.html")
		.append("?userId=").append(userInfo.getUserId())
		.append("'>点击这里链接进行密码重置</a>");

		mailInfo.setContent(content.toString());
		// 这个类主要来发送邮件
		SimpleMailSender sms = new SimpleMailSender();
		// sms.sendTextMail(mailInfo);//发送文体格式
		sms.sendHtmlMail(mailInfo);// 发送html格式
	}
	/**
	 * 发送邮件
	 * 
	 * @param userInfo
	 */
	@SuppressWarnings("static-access")
	private void sendMail(SyUserInfo userInfo) {
		MailSenderInfo mailInfo = new MailSenderInfo();
		mailInfo.setMailServerHost("smtp.163.com");
		mailInfo.setMailServerPort("25");
		mailInfo.setLocalHost("163.com");
		mailInfo.setValidate(true);
		mailInfo.setUserName("czw861021@163.com");
		mailInfo.setPassword("7286871904");// 您的邮箱密码
		mailInfo.setFromAddress("czw861021@163.com");

		mailInfo.setToAddress(userInfo.getUserMail());
		mailInfo.setSubject("找回密码");

		StringBuilder content = new StringBuilder("您的登录名是：").append(userInfo.getUserName()).append("<br/>您的密码是：" + userInfo.getUserPwd());

		mailInfo.setContent(content.toString());
		// 这个类主要来发送邮件
		SimpleMailSender sms = new SimpleMailSender();
		// sms.sendTextMail(mailInfo);//发送文体格式
		sms.sendHtmlMail(mailInfo);// 发送html格式
	}

	/**
	 * 修改个人资料
	 */
	public void updateUser(SyUserInfo userInfo) {
		userInfo.setUpdateDate(new Date());
		syUserInfoDao.updateUserInfo(userInfo);
	}

	/**
	 * 查用户名
	 */
	public List<SyUserInfo> getUserInfoByName(String userName) {
		return syUserInfoDao.getUserInfoByName(userName);
	}

	/**
	 * 修改密码
	 */
	public void updatePwd(String userId, String userPwd) {
		SyUserInfo userInfo = getUserInfoById(userId);
		userInfo.setUserPwd(Cryto.cryptMD5ToHEX(userPwd));
	}

	public void setSyUserInfoDao(SyUserInfoDao syUserInfoDao) {
		this.syUserInfoDao = syUserInfoDao;
	}

	public SyUserInfoDao getSyUserInfoDao() {
		return syUserInfoDao;
	}
	
	@Override
	public PageResponse queryForPage(Map<String, String> params,
			int pageNumber, int pageSize) throws ServiceException {
		PageResponse pageResponse = new PageResponse();
		try {
			Pagination pagination = syUserInfoDao.queryForPage(params, pageNumber, pageSize);
			pageResponse.setList(pagination.getList());
			pageResponse.setTotalRecord(pagination.getTotalRecord());
			pageResponse.setSuccess(true);
			return pageResponse;
		} catch (DaoException e) {
			pageResponse.setSuccess(false);
			throw new ServiceException(e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean isAdmin(String userId) {
		List list=syUserInfoDao.getAdminById(userId);
		if(list.size()>0){
			return true;
		}else{
			return false;
		}
	}

}
