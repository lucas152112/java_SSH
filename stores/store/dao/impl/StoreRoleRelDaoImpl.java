package com.oim.stores.store.dao.impl;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.oim.stores.common.EntityDao;
import com.oim.stores.common.Pagination;
import com.oim.stores.common.Tool;
import com.oim.stores.exception.DaoException;
import com.oim.stores.store.dao.StoreRoleRelDao;
import com.oim.stores.store.domain.ShStoreInfo;
import com.oim.stores.store.domain.ShStoreRoleRel;
/**
 * 商家角色关联dao实现类
 * @author Administrator
 *
 */
@SuppressWarnings("unchecked")
@Repository("storeRoleRelDao")
public class StoreRoleRelDaoImpl extends EntityDao implements StoreRoleRelDao {

    @Override
    public Pagination queryForPage(Map<String, String> params, int pageNumber,
	    int pageSize) throws DaoException {
	try {
	    // String hql =
	    // "FROM ShStoreRoleRel a left join SyRoleInfo b with a.id.roleid=b.roleId "
	    // +
	    // " left join ShStoreInfo c with a.id.storeid=c.storeId WHERE  1=1 ";
	    String hql = " FROM ShStoreRoleRel a  left join a.syroleinfo as b left join a.shstoreinfo as c where 1=1 ";
	    Set<String> keys = params.keySet();
	    for (String key : keys) {
		if ("storeName".equals(key)) {
		    if (Tool.isNotEmpty(params.get(key))) {
			hql += " AND c." + key + " like '%" + params.get(key).trim()
				+ "%' ";
		    }
		}
		if ("roleid".equals(key)) {
		    if (Tool.isNotEmpty(params.get(key))) {
			hql += " AND a.id." + key + " = '" + params.get(key).trim()
				+ "' ";
		    }
		}
	    }
	    String counthql = " SELECT COUNT(*) " + hql;
	    hql += "order by a.updateddate desc";
	    return queryForPage(hql, counthql, pageNumber, pageSize);
	} catch (Exception e) {
	    throw new DaoException(e);
	}
    }

    @Override
    public void updateStoreRoleRel(ShStoreRoleRel bean) throws DaoException {
	try {
	    this.getSessionFactory().getCurrentSession().saveOrUpdate(bean);
	} catch (Exception e) {
	    throw new DaoException(e);
	}

    }

    @Override
    public ShStoreRoleRel queryById(String storeid, String roleid)
	    throws DaoException {
	try {
	    ShStoreRoleRel bean = null;
	    List<ShStoreRoleRel> list = this
		    .getSessionFactory()
		    .getCurrentSession()
		    .createQuery(
			    "from ShStoreRoleRel a where a.id.storeid="
				    + storeid + " and a.id.roleid=" + roleid)
		    .list();
	    if (list.size() > 0) {
		bean = list.get(0);
	    }
	    return bean;
	} catch (Exception e) {
	    throw new DaoException(e);
	}
    }

    @Override
    public void saveStoreRoleRel(ShStoreRoleRel bean) throws DaoException {
	try {
	    this.getSessionFactory().getCurrentSession().save(bean);
	} catch (Exception e) {
	    throw new DaoException(e);
	}

    }

    @Override
    public void deleteStoreRoleRel(ShStoreRoleRel bean) throws DaoException {
	try {
	    this.getSessionFactory().getCurrentSession().delete(bean);
	} catch (Exception e) {
	    throw new DaoException(e);
	}

    }

    @Override
    public List<ShStoreRoleRel> query(Map<String, String> params)
	    throws DaoException {
	try {
	    StringBuffer hql = new StringBuffer("FROM StoreRoleRel  WHERE 1=1 ");
	    @SuppressWarnings("unused")
		Set<String> keys = params.keySet();
	    Query query = this.sessionFactory.getCurrentSession().createQuery(
		    hql.toString());
	    List<ShStoreRoleRel> list = query.list();
	    return list;
	} catch (Exception e) {
	    throw new DaoException(e);
	}
    }

    @Override
    public List<ShStoreInfo> queryStore(Map<String, String> params)
	    throws DaoException {
	try {
	    StringBuffer hql = new StringBuffer(
		    "FROM ShStoreInfo a  WHERE 1=1 ");
	    Set<String> keys = params.keySet();
	    for (String key : keys) {
		if ("storeName".equals(key)) {
		    if (Tool.isNotEmpty(params.get(key))) {
			hql.append(" AND a." + key + " like '"
				+ params.get(key) + "%' ");
		    }
		}
	    }
	    Query query = this.sessionFactory.getCurrentSession().createQuery(
		    hql.toString());
	    List<ShStoreInfo> list = query.list();
	    return list;
	} catch (Exception e) {
	    throw new DaoException(e);
	}
    }

}
