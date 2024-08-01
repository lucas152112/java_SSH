package com.oim.stores.system.dao.impl;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.oim.stores.common.EntityDao;
import com.oim.stores.common.Pagination;
import com.oim.stores.common.Tool;
import com.oim.stores.exception.DaoException;
import com.oim.stores.system.dao.MenuGroupDao;
import com.oim.stores.system.domain.MenuGroupBean;
import com.oim.stores.system.domain.SyMenuList;
/**
 * 权限分组dao子类
 * @author Administrator
 *
 */
@SuppressWarnings("unchecked")
@Repository("menuGroupDao")
public class MenuGroupDaoImpl extends EntityDao implements MenuGroupDao {

    @Override
    public Pagination queryForPage(Map<String, String> params, int pageNumber,
	    int pageSize) throws DaoException {
	try {
	    String hql = "FROM MenuGroupBean p WHERE  1=1 ";
	    Set<String> keys = params.keySet();
	    for (String key : keys) {
		if ("groupname".equals(key)) {
		    if (Tool.isNotEmpty(params.get(key))) {
			hql += " AND p.id." + key + " like '%"
				+ params.get(key) + "%' ";
		    }
		}
	    }
	    String counthql = " SELECT COUNT(*) " + hql;
	    hql+="order by p.groupid";
	    return queryForPage(hql, counthql, pageNumber, pageSize);
	} catch (Exception e) {
	    throw new DaoException(e);
	}
    }

    @Override
    public void updateGroup(MenuGroupBean menugroup) throws DaoException {
	try {
	    this.getSessionFactory().getCurrentSession()
		    .saveOrUpdate(menugroup);
	} catch (Exception e) {
	    throw new DaoException(e);
	}

    }

    @Override
    public MenuGroupBean queryById(String groupid) throws DaoException {
	return (MenuGroupBean) this.getSessionFactory().getCurrentSession()
		.get(MenuGroupBean.class, Long.parseLong(groupid));
    }

    @Override
    public void saveGroup(MenuGroupBean menugroup) throws DaoException {
	try {
	    this.getSessionFactory().getCurrentSession().save(menugroup);
	} catch (Exception e) {
	    throw new DaoException(e);
	}
    }

    @Override
    public void deleteGroup(MenuGroupBean menugroup) throws DaoException {
	try {
	    this.getSessionFactory().getCurrentSession().delete(menugroup);
	} catch (Exception e) {
	    throw new DaoException(e);
	}
    }

    @Override
    public List<MenuGroupBean> query(Map<String, String> params)
	    throws DaoException {
	try {
	    StringBuffer hql = new StringBuffer(
		    "FROM MenuGroupBean  WHERE 1=1 ");
	    Set<String> keys = params.keySet();
	    for (String key : keys) {
		if ("groupname".equals(key)) {
		    if (Tool.isNotEmpty(params.get(key))) {
			hql.append(" AND " + key + " like '%" + params.get(key)
				+ "%' ");
		    }
		}
	    }
	    Query query = this.sessionFactory.getCurrentSession().createQuery(
		    hql.toString());
	    List<MenuGroupBean> list = query.list();
	    return list;
	} catch (Exception e) {
	    throw new DaoException(e);
	}
    }

    @Override
    public int queryForMenuCount(Map<String, String> params)
	    throws DaoException {
	try {
	    String hql = "FROM SyMenuList p WHERE  1=1 ";
	    Set<String> keys = params.keySet();
	    for (String key : keys) {
		if ("groupid".equals(key)) {
		    if (Tool.isNotEmpty(params.get(key))) {
			hql += " AND p.id." + key + " like '%"
				+ params.get(key) + "%' ";
		    }
		}
	    }
	    String counthql = "SELECT COUNT(*) " + hql;
	    int countNum = ((Number) sessionFactory.getCurrentSession()
		    .createQuery(counthql).uniqueResult()).intValue();
	    return countNum;
	} catch (Exception e) {
	    throw new DaoException(e);
	}
    }

    @Override
    public List<SyMenuList> queryMenus(Map<String, String> params)
	    throws DaoException {
	try {
	    String hql = "FROM SyMenuList p WHERE  1=1 ";
	    Set<String> keys = params.keySet();
	    for (String key : keys) {
		if ("groupid".equals(key)) {
		    if (Tool.isNotEmpty(params.get(key))) {
			hql += " AND p.id." + key + " = '"
				+ params.get(key) + "' ";
		    }
		}
		if ("parentId".equals(key)) {
		    if (Tool.isNotEmpty(params.get(key))) {
			hql += " AND p.id." + key + " = '"
				+ params.get(key) + "' ";
		    }
		}
		if ("webName".equals(key)) {
		    if (Tool.isNotEmpty(params.get(key))) {
			hql += " AND p.id." + key + " = '"
				+ params.get(key) + "' ";
		    }
		}
	    }
	    hql+=" order by p.menuOrder ";
	    Query query = this.sessionFactory.getCurrentSession().createQuery(
		    hql);
	    List<SyMenuList> list = query.list();
	    return list;
	} catch (Exception e) {
	    throw new DaoException(e);
	}
    }

    @Override
    public void updateMenuGroupId(String menuid,String groupid) throws DaoException {
	try {
	    String hql="update SyMenuList set groupid="+groupid+" where menuId="+menuid;
	    Query query = this.sessionFactory.getCurrentSession().createQuery(
		    hql);
	    query.executeUpdate();
	} catch (Exception e) {
	    throw new DaoException(e);
	}
    }

}
