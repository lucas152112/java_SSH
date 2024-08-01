package com.oim.stores.store.dao.impl;

import java.util.Map;
import java.util.Set;

import com.oim.stores.common.EntityDao;
import com.oim.stores.common.Pagination;
import com.oim.stores.common.Tool;
import com.oim.stores.exception.DaoException;
import com.oim.stores.store.dao.StoreAccountDetailDao;
import com.oim.stores.store.domain.ShStoreAccountDetail;

/**
 * 商家详细信息dao实现类
 */
@SuppressWarnings("unchecked")
public class StoreAccountDetailDaoImpl extends EntityDao implements StoreAccountDetailDao {

	@Override
	public void save(ShStoreAccountDetail storeAccountDetail) throws DaoException {
		try {
			hibernateTemplate.saveOrUpdate(storeAccountDetail);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public void delete(ShStoreAccountDetail storeAccountDetail) throws DaoException {
		try {
			hibernateTemplate.delete(storeAccountDetail);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public Pagination queryForPage(Map<String, String> params, int pageNumber, int pageSize) throws DaoException {
		try {
			String hql = "FROM ShStoreAccountDetail p WHERE 1=1 ";
			Set<String> keys = params.keySet();
			for (String key : keys) {
				if (Tool.isNotEmpty(params.get(key))) {
					hql += " AND p." + key + " = '" + params.get(key) + "' ";
				}
			}
			String counthql = " SELECT COUNT(*) " + hql;
			return queryForPage(hql, counthql, pageNumber, pageSize);

		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

}
