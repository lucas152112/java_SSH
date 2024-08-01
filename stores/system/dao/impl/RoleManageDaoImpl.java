package com.oim.stores.system.dao.impl;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.oim.stores.common.EntityDao;
import com.oim.stores.common.Pagination;
import com.oim.stores.common.Tool;
import com.oim.stores.exception.DaoException;
import com.oim.stores.system.dao.RoleManageDao;
import com.oim.stores.system.domain.SyRoleInfo;
/**
 * 角色管理dao;
 * @author yuyan;
 *
 */
@SuppressWarnings("unchecked")
public class RoleManageDaoImpl extends EntityDao implements RoleManageDao{
	@Override
	public List<SyRoleInfo> query(Map<String, String> params)
			throws DaoException {
		try {
			String hql = "FROM SyRoleInfo p WHERE p.roleType='1' and 1=1 ";
			Set<String> keys = params.keySet();
			for (String key : keys) {
				if("roleName".equals(key)){
					if (Tool.isNotEmpty(params.get(key))) {
						hql += " AND p." + key + " = '" + params.get(key).trim() + "' ";
					}
				}else {
					if (Tool.isNotEmpty(params.get(key))) {
						hql += " AND p." + key + " = '" + params.get(key).trim() + "' ";
					}
				}
			}
			return hibernateTemplate.find(hql);

		} catch (Exception e) {
			throw new DaoException(e);
		}
	}
	

	@Override
	public Pagination queryForPage(Map<String, String> params, int pageNumber,
			int pageSize) throws DaoException {
		try {
			String hql = "FROM SyRoleInfo p WHERE p.roleType='1' and 1=1 ";
			Set<String> keys = params.keySet();
			for (String key : keys) {
				if("roleName".equals(key)){
					if (Tool.isNotEmpty(params.get(key))) {
						hql += " AND p." + key + " like '%" + params.get(key).trim() + "%' ";
					}
				}else {
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
	public void save(SyRoleInfo role) throws DaoException {
		try {
			hibernateTemplate.saveOrUpdate(role);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public void updateRole(SyRoleInfo role) {
		// TODO Auto-generated method stub
	}
	
	@Override
	public void delRole(SyRoleInfo role) throws DaoException {
		try {
			hibernateTemplate.delete(role);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}


	@Override
	public SyRoleInfo queryById(long roleId) throws DaoException {
		// TODO Auto-generated method stub
		return hibernateTemplate.get(SyRoleInfo.class, roleId);
	}


	@Override
	public List<SyRoleInfo> queryBusi(Map<String, String> params)
			throws DaoException {
		try {
			String hql = "FROM SyRoleInfo p WHERE p.roleType='2' and 1=1 ";
			Set<String> keys = params.keySet();
			for (String key : keys) {
				if("roleName".equals(key)){
					if (Tool.isNotEmpty(params.get(key))) {
						hql += " AND p." + key + " = '" + params.get(key).trim() + "' ";
					}
				}else {
					if (Tool.isNotEmpty(params.get(key))) {
						hql += " AND p." + key + " = '" + params.get(key).trim() + "' ";
					}
				}
			}
			return hibernateTemplate.find(hql);

		} catch (Exception e) {
			throw new DaoException(e);
		}
	}


	@Override
	public Pagination queryBusiForPage(Map<String, String> params,
			int pageNumber, int pageSize) throws DaoException {
		try {
			String hql = "FROM SyRoleInfo p WHERE p.roleType='2' and 1=1 ";
			Set<String> keys = params.keySet();
			for (String key : keys) {
				if("roleName".equals(key)){
					if (Tool.isNotEmpty(params.get(key))) {
						hql += " AND p." + key + " like '%" + params.get(key).trim() + "%' ";
					}
				}else {
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

}
