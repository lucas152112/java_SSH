package com.oim.stores.system.dao.impl;

import java.util.Map;
import java.util.Set;

import com.oim.stores.common.EntityDao;
import com.oim.stores.common.Pagination;
import com.oim.stores.exception.DaoException;
import com.oim.stores.system.dao.SchInfoDao;
import com.oim.stores.system.domain.SchInfoBean;
/**
 * 任务管理dao子类
 * @author Administrator
 *
 */
@SuppressWarnings("unchecked")
public class SchInfoDaoImpl extends EntityDao implements SchInfoDao {

    @Override
    public Pagination queryForPage(Map<String, String> params, int pageNumber,
	    int pageSize) throws DaoException {
	try {
		String hql = "FROM SchInfoBean p WHERE  1=1 ";
		@SuppressWarnings("unused")
		Set<String> keys = params.keySet();
		String counthql = " SELECT COUNT(*) " + hql;
		return queryForPage(hql, counthql, pageNumber, pageSize);

	} catch (Exception e) {
		throw new DaoException(e);
	}
    }

    @Override
    public void updateSchInfo(SchInfoBean schinfo) throws DaoException {
	try {
		hibernateTemplate.saveOrUpdate(schinfo);
	} catch (Exception e) {
		throw new DaoException(e);
	}
    }

    @Override
    public SchInfoBean queryById(String schid) throws DaoException {
	return hibernateTemplate.get(SchInfoBean.class, schid);
    }

}
