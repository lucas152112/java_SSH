package com.oim.stores.system.dao.impl;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Session;

import com.oim.stores.common.EntityDao;
import com.oim.stores.common.Pagination;
import com.oim.stores.common.Tool;
import com.oim.stores.exception.DaoException;
import com.oim.stores.system.dao.PowerMngDao;
import com.oim.stores.system.domain.SyMenuList;
import com.oim.stores.system.domain.SyRoleMenuRelation;
import com.oim.stores.system.domain.UserMenuBean;
/**
 * 权限管理dao
 * @author yuyan;
 *
 */
@SuppressWarnings("unchecked")
public class PowerMngDaoImpl extends EntityDao implements PowerMngDao {

	@Override
	public SyRoleMenuRelation queryRelationById(Map<String, String> params)
			throws DaoException {
		String hql="from SyRoleMenuRelation rm where 1=1 ";
		Set<String> keys = params.keySet();
		for (String key : keys) {
				if (Tool.isNotEmpty(params.get(key))) {
					hql += " AND rm.id." + key + " = '" + params.get(key) + "' ";
				}
		}
		List<SyRoleMenuRelation> rmList = hibernateTemplate.find(hql.toString());
		if (rmList != null && rmList.size() > 0) {
			return rmList.get(0);
		}
		return null;
	}

	@Override
	public void delUserMenu(SyRoleMenuRelation userMenu) throws DaoException {
		try {
			hibernateTemplate.delete(userMenu);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public List<UserMenuBean> queryById(Map<String,String> params) throws DaoException {
		try {
			String preHql="select new com.oim.stores.system.domain.UserMenuBean(u.userName,p.id.userroleId," +
			"p.id.relationType,p.isActive,p.updateUser,p.updateDate,p.id.menuId) ";
			String hql = " FROM SyRoleMenuRelation p,SyUserInfo u WHERE p.id.userroleId=u.userId and 1=1 ";
			Set<String> keys = params.keySet();
			for (String key : keys) {
					if (Tool.isNotEmpty(params.get(key))) {
						hql += " AND p.id." + key + " = '" + params.get(key) + "' ";
					}
			}
			List<UserMenuBean> list = hibernateTemplate.find(preHql+hql);
			return list;
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}
	
	@Override
	public List<UserMenuBean> queryRoleById(Map<String, String> params)
			throws DaoException {
		try {
			String preHql="select new com.oim.stores.system.domain.UserMenuBean(p.id.userroleId," +
			"p.id.relationType,p.isActive,p.updateUser,p.updateDate,p.id.menuId,r.roleName) ";
			String hql = " FROM SyRoleMenuRelation p,SyRoleInfo r WHERE p.id.userroleId=r.roleId and 1=1 ";
			Set<String> keys = params.keySet();
			for (String key : keys) {
					if (Tool.isNotEmpty(params.get(key))) {
						hql += " AND p.id." + key + " = '" + params.get(key) + "' ";
					}
			}
			List<UserMenuBean> list = hibernateTemplate.find(preHql+hql);
			return list;
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public Pagination queryRoleForPage(Map<String, String> params,
			int pageNumber, int pageSize) throws DaoException {
		try {
			String preHql="select new com.oim.stores.system.domain.UserMenuBean(p.id.userroleId," +
					"p.id.relationType,p.isActive,p.updateUser,p.updateDate,p.id.menuId,r.roleName) ";
			String hql = " FROM SyRoleMenuRelation p,SyRoleInfo r WHERE p.id.userroleId=r.roleId and 1=1 ";
			Set<String> keys = params.keySet();
			for (String key : keys) {
				if("roleName".equals(key)){
					if (Tool.isNotEmpty(params.get(key))) {
						hql += " AND r." + key + " like '%" + params.get(key) + "%' ";
					}
				}else{
					if (Tool.isNotEmpty(params.get(key))) {
						hql += " AND p.id." + key + " = '" + params.get(key) + "' ";
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
	public Pagination queryForPage(Map<String, String> params, int pageNumber,
			int pageSize) throws DaoException {
		try {
			String preHql="select new com.oim.stores.system.domain.UserMenuBean(u.userName,p.id.userroleId," +
					"p.id.relationType,p.isActive,p.updateUser,p.updateDate,p.id.menuId) ";
			String hql = " FROM SyRoleMenuRelation p,SyUserInfo u WHERE p.id.userroleId=u.userId and 1=1 ";
			Set<String> keys = params.keySet();
			for (String key : keys) {
				if("userName".equals(key)){
					if (Tool.isNotEmpty(params.get(key))) {
						hql += " AND u." + key + " like '%" + params.get(key) + "%' ";
					}
				}else{
					if (Tool.isNotEmpty(params.get(key))) {
						hql += " AND p.id." + key + " = '" + params.get(key) + "' ";
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
	public List<SyMenuList> queryMenu(Map<String, String> params)
			throws DaoException {
		try {
			StringBuffer hql = new StringBuffer("FROM SyMenuList WHERE 1=1 ");
			Set<String> keys = params.keySet();
			for (String key : keys) {
				hql.append(" AND " + key + " = " + params.get(key) + "");
			}
			List<SyMenuList> list = hibernateTemplate.find(hql.toString());
			return list;
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}
	
	@Override
	public List<SyMenuList> querySonMenu(Map<String, String> params)
			throws DaoException {
		try {
			StringBuffer hql = new StringBuffer("FROM SyMenuList WHERE parentId<>0 and 1=1 ");
			Set<String> keys = params.keySet();
			for (String key : keys) {
				hql.append(" AND " + key + " = " + params.get(key) + "");
			}
			List<SyMenuList> list = hibernateTemplate.find(hql.toString());
			return list;
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public void save(SyRoleMenuRelation userMenu) throws DaoException {
		try {
			hibernateTemplate.saveOrUpdate(userMenu);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public void updateUserMenu(SyRoleMenuRelation userMenu) throws DaoException {
		try {
			hibernateTemplate.saveOrUpdate(userMenu);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public List<SyRoleMenuRelation> queryRoleRelation(Map<String, String> params)
			throws DaoException {
		try {
			String hql = "FROM SyRoleMenuRelation p WHERE 1=1 ";
			Set<String> keys = params.keySet();
			for (String key : keys) {
				if("menuName".equals(key)){
					if (Tool.isNotEmpty(params.get(key))) {
						hql += " AND p.id." + key + " like '%" + params.get(key) + "%' ";
					}
				}else {
					if (Tool.isNotEmpty(params.get(key))) {
						hql += " AND p.id." + key + " = '" + params.get(key) + "' ";
					}
				}
			}
			List<SyRoleMenuRelation> list=hibernateTemplate.find(hql);
			return list;

		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public void deleteRoleRelation(List<SyRoleMenuRelation> relalist)
			throws DaoException {
		try {
			hibernateTemplate.deleteAll(relalist);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public void saveRoleRelation(List<SyRoleMenuRelation> relalist)
			throws DaoException {
		try {
			Session session=getSessionFactory().getCurrentSession();
			session.flush();
			hibernateTemplate.saveOrUpdateAll(relalist);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

}
