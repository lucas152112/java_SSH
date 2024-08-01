package com.oim.stores.system.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.oim.stores.common.Cryto;
import com.oim.stores.common.PageResponse;
import com.oim.stores.common.Pagination;
import com.oim.stores.exception.DaoException;
import com.oim.stores.exception.ServiceException;
import com.oim.stores.store.dao.StoreUserRelationDao;
import com.oim.stores.store.domain.ShStoreUserRelation;
import com.oim.stores.store.domain.ShStoreUserRelationId;
import com.oim.stores.system.dao.UserManageDao;
import com.oim.stores.system.domain.SyRoleInfo;
import com.oim.stores.system.domain.UserManageBean;
import com.oim.stores.system.service.UserManageService;
import com.oim.stores.user.dao.SyUserInfoDao;
import com.oim.stores.user.domain.SyUserInfo;
/**
 * 用户管理service子类
 * @author yuyan
 *
 */
public class UserManageServiceImpl implements UserManageService {
	private UserManageDao userMngDao;//用户管理dao;
	private SyUserInfoDao syUserInfoDao;//系统用户dao;
	private StoreUserRelationDao storeUserRelationDao;//商家管理dao;

	@Override
	public PageResponse queryForPage(Map<String, String> params, int pageNumber, int pageSize) throws ServiceException {
		PageResponse pageResponse = new PageResponse();
		try {
			Pagination pagination = userMngDao.queryForPage(params, pageNumber, pageSize);
			pageResponse.setList(pagination.getList());
			pageResponse.setTotalRecord(pagination.getTotalRecord());
			pageResponse.setSuccess(true);
			return pageResponse;
		} catch (DaoException e) {
			pageResponse.setSuccess(false);
			throw new ServiceException(e);
		}
	}
	
	@Override
	public void delUserInfo(long userId,long storeId)throws ServiceException {
		String uid=""+userId;
		SyUserInfo userInfo=syUserInfoDao.getUserInfoById(uid);
		userInfo.setUpdateDate(new Date());
		userInfo.setUserStatus(3L);
		syUserInfoDao.updateUserInfo(userInfo);
		ShStoreUserRelationId ids=new ShStoreUserRelationId();
		ids.setUserId(userId);
		ids.setStoreId(storeId);
		ShStoreUserRelation storeRe;
		try {
			storeRe = storeUserRelationDao.queryById(ids);
			storeUserRelationDao.delete(storeRe);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}
	
	@Override
	public List<SyRoleInfo> query(Map<String, String> params)
			throws ServiceException {
		try {
			return userMngDao.query(params);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void updateUserInfo(UserManageBean bean,long updateUser) throws ServiceException{
		SyUserInfo userInfo=syUserInfoDao.getUserInfoById(bean.getUserId().toString());
		userInfo.setUpdateDate(new Date());
		userInfo.setUserName(bean.getUserName());
		userInfo.setUserRealname(bean.getUserRealname());
		userInfo.setTelephone(bean.getTelephone());
		if(!userInfo.getUserPwd().equals(bean.getUserPwd())){
			String newPwd=Cryto.cryptMD5ToHEX(bean.getUserPwd());
			userInfo.setUserPwd(newPwd);
		}
		userInfo.setUserStatus(bean.getUserStatus());
		userInfo.setUserMail(bean.getUserMail());
		syUserInfoDao.updateUserInfo(userInfo);
		
		ShStoreUserRelationId ids=new ShStoreUserRelationId();
		ids.setUserId(bean.getUserId());
		ids.setStoreId(bean.getStoreId());
		ShStoreUserRelation storeRe;
		try {
			storeRe = storeUserRelationDao.queryById(ids);
			storeRe.setUserStoreRoleId(bean.getRoleId());
			storeRe.setUpdateDate(new Date());
			storeRe.setUserStatus(bean.getUserStatus());
			storeRe.setUpdateUser(updateUser);
			storeUserRelationDao.save(storeRe);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	public UserManageDao getUserMngDao() {
		return userMngDao;
	}

	public void setUserMngDao(UserManageDao userMngDao) {
		this.userMngDao = userMngDao;
	}
	public SyUserInfoDao getSyUserInfoDao() {
		return syUserInfoDao;
	}

	public void setSyUserInfoDao(SyUserInfoDao syUserInfoDao) {
		this.syUserInfoDao = syUserInfoDao;
	}

	public StoreUserRelationDao getStoreUserRelationDao() {
		return storeUserRelationDao;
	}

	public void setStoreUserRelationDao(StoreUserRelationDao storeUserRelationDao) {
		this.storeUserRelationDao = storeUserRelationDao;
	}

	@Override
	public List<UserManageBean> queryUserInfo(Map<String, String> params)
			throws ServiceException {
		// TODO Auto-generated method stub
		try {
			return userMngDao.queryUserInfo(params);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void saveUserInfo(SyUserInfo user, long updateUser)
			throws ServiceException {
		try {
			userMngDao.saveUser(user);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void saveStoreInfo(ShStoreUserRelation user)
			throws ServiceException {
		try {
			storeUserRelationDao.save(user);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public ShStoreUserRelation queryStoreById(ShStoreUserRelationId id) throws ServiceException {
		try {
			return storeUserRelationDao.queryById(id);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public boolean hasUserMail(String userMail) throws ServiceException {
		try {
			List<SyUserInfo> userList = syUserInfoDao.getUserInfoByMail(userMail);
			return userList != null && userList.size() > 0;
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public boolean hasLoginName(String name) throws ServiceException {
		try {
			List<SyUserInfo> userList = syUserInfoDao.getUserInfoByName(name);
			return userList != null && userList.size() > 0;
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
}
