package com.oim.stores.store.dao.impl;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Session;

import com.oim.stores.common.EntityDao;
import com.oim.stores.common.Pagination;
import com.oim.stores.common.Tool;
import com.oim.stores.exception.DaoException;
import com.oim.stores.store.dao.StoreUserRelationDao;
import com.oim.stores.store.domain.ShStoreUserRelation;
import com.oim.stores.store.domain.ShStoreUserRelationId;

/**
 * 商家用户关联dao实现类
 */
@SuppressWarnings("unchecked")
public class StoreUserRelationDaoImpl extends EntityDao implements StoreUserRelationDao {

	@Override
	public void save(ShStoreUserRelation storeUserRelation) throws DaoException {
		try {
			hibernateTemplate.saveOrUpdate(storeUserRelation);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public void saveAll(List<ShStoreUserRelation> surs) throws DaoException {
		try {
			hibernateTemplate.saveOrUpdateAll(surs);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public void delete(ShStoreUserRelation storeUserRelation) throws DaoException {
		try {
			hibernateTemplate.delete(storeUserRelation);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public Pagination queryForPage(Map<String, String> params, int pageNumber, int pageSize) throws DaoException {
		try {
			String hql = "FROM ShStoreUserRelation p WHERE 1=1 ";
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

	@Override
	public ShStoreUserRelation queryById(ShStoreUserRelationId id) throws DaoException {
		try {
			return hibernateTemplate.get(ShStoreUserRelation.class, id);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public List<ShStoreUserRelation> queryByStoreId(Long storeId) throws DaoException {
		try {
			String hql = "FROM ShStoreUserRelation p WHERE p.id.storeId = ?";
			return hibernateTemplate.find(hql, storeId);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public void deleteByStoreId(Long storeId) throws DaoException {
		try {
			Session session = sessionFactory.getCurrentSession();
			String hql = "delete ShStoreUserRelation p WHERE p.id.storeId = :storeId";
			session.createQuery(hql).setLong("storeId", storeId).executeUpdate();
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public List<ShStoreUserRelation> queryByUserId(String userId)
			throws DaoException {
		try {
			String hql = "FROM ShStoreUserRelation p WHERE  p.id.userId ="+userId;
			return hibernateTemplate.find(hql);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public void update(ShStoreUserRelation storeUserRelation)
			throws DaoException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<ShStoreUserRelation> queryAdminByStoreId(Long storeId,Long deroleId)
			throws DaoException {
		try {
			String hql = "FROM ShStoreUserRelation p WHERE p.userStatus='1' and p.id.storeId = "+storeId+" and p.userStoreRoleId="+deroleId;
			return hibernateTemplate.find(hql);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}
}
