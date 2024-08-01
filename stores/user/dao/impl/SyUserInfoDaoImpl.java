package com.oim.stores.user.dao.impl;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.oim.stores.common.EntityDao;
import com.oim.stores.common.Pagination;
import com.oim.stores.common.Tool;
import com.oim.stores.exception.DaoException;
import com.oim.stores.user.dao.SyUserInfoDao;
import com.oim.stores.user.domain.SyUserAccount;
import com.oim.stores.user.domain.SyUserInfo;
/**
 * 系统用户管理dao子类
 * @author Administrator
 *
 */
@SuppressWarnings("unchecked")
public class SyUserInfoDaoImpl extends EntityDao implements SyUserInfoDao {

	/**
	 * 根据用户Id获得用户信息
	 */
	@SuppressWarnings("unchecked")
	public SyUserInfo getUserInfoById(String userId) {
		List<SyUserInfo> userList = hibernateTemplate.find("from SyUserInfo u where userId=" + userId);
		if (userList != null && userList.size() > 0) {
			return userList.get(0);
		}
		return null;
	}

	/**
	 * 根据用户名和密码获得用户信息
	 */
	@SuppressWarnings("unchecked")
	public SyUserInfo getUserInfoByNameAndpwd(String userName, String userpwd) {
		String hql = "from SyUserInfo u where u.userName = '" + userName + "' and u.userPwd='" + userpwd + "'";
		List<SyUserInfo> list = hibernateTemplate.find(hql);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	/**
	 * 添加用户信息
	 */
	public void saveUserInfo(SyUserInfo userInfo) {
		try {
			hibernateTemplate.save(userInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 修改用户信息
	 */
	public void updateUserInfo(SyUserInfo userInfo) {
		hibernateTemplate.update(userInfo);
	}

	/**
	 * 根据用户名获得用户信息
	 */
	@SuppressWarnings("unchecked")
	public List<SyUserInfo> getUserInfoByName(String userName) {
		return hibernateTemplate.find("from SyUserInfo u where u.userName='" + userName + "'");
	}

	/**
	 * 根据电子邮箱获得用户信息
	 */
	@SuppressWarnings("unchecked")
	public List<SyUserInfo> getUserInfoByMail(String userMail) {
		return hibernateTemplate.find("from SyUserInfo u where u.userMail='" + userMail + "'");
	}

	/**
	 * 添加用户账户信息
	 */
	public void saveUserAccount(SyUserAccount account) {
		hibernateTemplate.save(account);
	}

	/**
	 * 获取注册用户列表
	 */
	public List<SyUserInfo> getUserList() {
		// TODO Auto-generated method stub
		return hibernateTemplate.find("from SyUserInfo u");
	}

	@Override
	public Pagination queryForPage(Map<String, String> params, int pageNumber,
			int pageSize) throws DaoException {
		try {
			String hql = "FROM SyUserInfo p WHERE 1=1 ";
			Set<String> keys = params.keySet();
			for (String key : keys) {
				if("userName".equals(key)){
					if (Tool.isNotEmpty(params.get(key))) {
						hql += " AND p." + key + " like '%" + params.get(key).trim() + "%' ";
					}
				}else{
					if (Tool.isNotEmpty(params.get(key))) {
						hql += " AND p." + key + " = '" + params.get(key).trim() + "' ";
					}
				}
			}
			String counthql = " SELECT COUNT(*) " + hql;
			return queryForPage(hql, counthql, pageNumber, pageSize);

		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public List getAdminById(String userId) {
		//String hql = " FROM ViewStoreUserInfo vu WHERE vu.id.roleType=1 and vu.id.userId= "+userId;
		String hql = " FROM ViewStoreUserInfo vu WHERE vu.id.storeId=1 and vu.id.userId= "+userId;
		List list=hibernateTemplate.find(hql);
		return list;
	}

}
