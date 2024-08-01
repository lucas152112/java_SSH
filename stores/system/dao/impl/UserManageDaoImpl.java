package com.oim.stores.system.dao.impl;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.oim.stores.common.EntityDao;
import com.oim.stores.common.Pagination;
import com.oim.stores.common.Tool;
import com.oim.stores.exception.DaoException;
import com.oim.stores.store.domain.ShStoreUserRelation;
import com.oim.stores.store.domain.ShStoreUserRelationId;
import com.oim.stores.system.dao.UserManageDao;
import com.oim.stores.system.domain.SyRoleInfo;
import com.oim.stores.system.domain.UserManageBean;
import com.oim.stores.user.domain.SyUserInfo;
/**
 * 用户管理dao实现类
 * @author yuyan
 *
 */
@SuppressWarnings("unchecked")
public class UserManageDaoImpl extends EntityDao implements UserManageDao {

	@Override
	public void delUserRole(ShStoreUserRelation userRole) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * 查询用户管理列表
	 */
	@Override
	public Pagination queryForPage(Map<String, String> params, int pageNumber,
			int pageSize) throws DaoException {
		try {
//			String preHql="select new com.oim.stores.user.domain.ViewStoreUserInfoId(vs.id.storeId, vs.id.storeName,vs.id.storeStatus," +
//					" vs.id.roleId, vs.id.roleName,vs.id.roleType,vs.id.userId,vs.id.userMail,vs.id.userName," +
//					"vs.id.userRealname,vs.id.userPwd,vs.id.pwdQuestion,vs.id.pwdAnswer, " +
//					" vs.id.mobile,vs.id.telephone,vs.id.province,vs.id.city,vs.id.county,vs.id.regDate,vs.id.userFrom,vs.id.payPwd,vs.id.userSex,vs.id.birthday, " +
//					" vs.id.mobileFlag,vs.id.mailFlag,vs.id.cardId,vs.id.userStatus,vs.id.updateDate,vs.id.updateUser) ";
			String preHql=" select new com.oim.stores.system.domain.UserManageBean(vs.id.userId,vs.id.userName," +
					"vs.id.userRealname,vs.id.telephone,vs.id.userStatus,vs.id.updateDate,vs.id.storeId," +
					"vs.id.roleName,vs.id.roleId)";
			String hql = " FROM ViewStoreUserInfo vs WHERE  1=1 ";
			Set<String> keys = params.keySet();
			for (String key : keys) {
				if("userName".equals(key)){
					if (Tool.isNotEmpty(params.get(key))) {
						hql += " AND vs.id." + key + " like '%" + params.get(key).trim() + "%' ";
					}
				}else {
					if (Tool.isNotEmpty(params.get(key))) {
						hql += " AND vs.id." + key + " = '" + params.get(key).trim() + "' ";
					}
				}
			}
			String counthql = " SELECT COUNT(*) " + hql;
			return queryForPage(preHql+hql, counthql, pageNumber, pageSize);

		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public void updateUserRole(ShStoreUserRelation userRole) {
		

	}

	@Override
	public List<SyRoleInfo> query(Map<String, String> params)
			throws DaoException {
		try {
			StringBuffer hql = new StringBuffer("FROM SyRoleInfo WHERE 1=1 ");
			Set<String> keys = params.keySet();
			for (String key : keys) {
				hql.append(" AND " + key + " = '" + params.get(key).trim() + "'");
			}
			List<SyRoleInfo> list = hibernateTemplate.find(hql.toString());
			return list;
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public ShStoreUserRelation queryStoreUserById(ShStoreUserRelationId id)
			throws DaoException {
		try {
			return hibernateTemplate.get(ShStoreUserRelation.class, id);
		} catch (Exception e) {
			throw new DaoException();
		}
	}

	@Override
	public List<UserManageBean> queryUserInfo(Map<String, String> params)
			throws DaoException {
		try {
			String preHql=" select new com.oim.stores.system.domain.UserManageBean(vs.id.userId,vs.id.userName," +
			"vs.id.userRealname,vs.id.telephone,vs.id.userStatus,vs.id.updateDate,vs.id.storeId," +
			"vs.id.roleName,vs.id.roleId,vs.id.userPwd,vs.id.userMail)";
			String hql = " FROM ViewStoreUserInfo vs WHERE  1=1 ";
			Set<String> keys = params.keySet();
			for (String key : keys) {
				if("userName".equals(key)){
					if (Tool.isNotEmpty(params.get(key))) {
						hql += " AND vs.id." + key + " like '%" + params.get(key).trim() + "%' ";
					}
				}else {
					if (Tool.isNotEmpty(params.get(key))) {
						hql += " AND vs.id." + key + " = '" + params.get(key).trim() + "' ";
					}
				}
			}
			List<UserManageBean> list = hibernateTemplate.find(preHql+hql);
			return list;
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public void saveUser(SyUserInfo user) throws DaoException {
		try {
			hibernateTemplate.save(user);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

}
