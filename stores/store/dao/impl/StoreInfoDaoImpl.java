package com.oim.stores.store.dao.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Query;

import org.hibernate.Session;

import com.oim.stores.common.EntityDao;
import com.oim.stores.common.Pagination;
import com.oim.stores.common.Tool;
import com.oim.stores.exception.DaoException;
import com.oim.stores.shop.domain.ShStoreSecurity;
import com.oim.stores.store.dao.StoreInfoDao;
import com.oim.stores.store.domain.ShStoreCluster;
import com.oim.stores.store.domain.ShStoreInfo;
import com.oim.stores.store.domain.ShStoreRoleRel;
import com.oim.stores.store.domain.ShStoreUserRelation;
import com.oim.stores.store.domain.ViewStoreAccount;
import com.oim.stores.store.domain.ViewStoreCluster;
import com.oim.stores.system.domain.SyRoleInfo;

/**
 * 商家信息
 */
@SuppressWarnings("unchecked")
public class StoreInfoDaoImpl extends EntityDao implements StoreInfoDao {

	@Override
	public void save(ShStoreInfo storeInfo) throws DaoException {
		try {
			hibernateTemplate.saveOrUpdate(storeInfo);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public void delete(ShStoreInfo storeInfo) throws DaoException {
		try {
			hibernateTemplate.delete(storeInfo);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public Pagination queryForPage(Map<String, String> params, int pageNumber, int pageSize) throws DaoException {
		try {
			String hql = "FROM ShStoreInfo p WHERE 1=1 ";
			Set<String> keys = params.keySet();
			for (String key : keys) {
				if (Tool.isNotEmpty(params.get(key))) {
					if ("storeName".equals(key)) {
						hql += " AND p." + key + " like '%" + params.get(key).trim() + "%' ";
					}else if("storeIds".equals(key)){
						hql+=" and p.storeId not in ("+params.get(key).trim()+") ";
					} else {
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
	public ShStoreInfo queryById(Long storeId) throws DaoException {
		try {
			List<ShStoreInfo> si = hibernateTemplate.find("FROM ShStoreInfo WHERE storeId = ?", storeId);
			if (null != si && si.size() > 0)
				return si.get(0);
			return null;
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public List<ShStoreInfo> queryByStoreName(String storeName) throws DaoException {
		try {
			return hibernateTemplate.find("FROM ShStoreInfo WHERE storeName = ?", storeName.trim());
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public List<ShStoreInfo> queryAll(Map<String, String> params) throws DaoException {
		try {
			StringBuffer hql = new StringBuffer("FROM ShStoreInfo  WHERE 1=1 ");
			Query query = this.sessionFactory.getCurrentSession().createQuery(hql.toString());
			List<ShStoreInfo> list = query.list();
			return list;
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public List<ShStoreInfo> getShStoreInfos() {
		return hibernateTemplate.find("FROM ShStoreInfo ");
	}

	@Override
	public List<ShStoreRoleRel> getStoreRoleRels(String storeId) {
		return hibernateTemplate.find("FROM ShStoreRoleRel where id.storeid=" + storeId + " and state=1");
	}

	@Override
	public List<SyRoleInfo> getRoles() {
		return hibernateTemplate.find("from SyRoleInfo");
	}

	@Override
	public void saveStoreRoleRel(ShStoreRoleRel rel) throws DaoException {
		hibernateTemplate.save(rel);
	}

	@Override
	public BigDecimal getStoreSeq() {
		Session session = this.getHibernateTemplate().getSessionFactory().openSession();
		try {
			String sql = "SELECT SEQ_STOREID.NEXTVAL from dual";
			List<BigDecimal> list = session.createSQLQuery(sql).list();
			if (list != null && list.size() > 0)
				return list.get(0);
		} finally {
			session.close();
		}
		return null;
	}

	@Override
	public List<ShStoreUserRelation> getStoreUserRel(String storeId) {
		return hibernateTemplate.find("from ShStoreUserRelation where id.storeId=" + storeId);
	}

	@Override
	public List<?> getByHql(String hql) {
		List<?> result = this.getHibernateTemplate().find(hql);
		return result;
	}

	@Override
	public ShStoreInfo queryStoreById(Long storeId) throws DaoException {
		String sql = " from ShStoreInfo s where s.storeId='" + storeId + "'";
		List<ShStoreInfo> list = hibernateTemplate.find(sql);
		if (list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}

	/**
	 * 根据商家Id获得商家密钥信息
	 */
	@Override
	public ShStoreSecurity getStoreSecurityByStoreId(BigDecimal storeId)
			throws DaoException {
		List<ShStoreSecurity> list = hibernateTemplate.find("from ShStoreSecurity where storeId="+storeId);
		if(list != null && list.size() > 0)
			return list.get(0);
		return null;
	}

	/**
	 * 根据密钥获得商家密钥信息
	 */
	@Override
	public ShStoreSecurity getStoreSecurityByKey(String key)
			throws DaoException {
		List<ShStoreSecurity> list = hibernateTemplate.find("from ShStoreSecurity where storeKey='" + key + "'");
		if(list != null && list.size() > 0)
			return list.get(0);
		return null;
	}

	/**
	 * 保存商家密钥
	 */
	@Override
	public void saveStoreSecurity(ShStoreSecurity security) throws DaoException {
		hibernateTemplate.save(security);
	}

	/**
	 * 根据商家类型获得商家列表
	 */
	@Override
	public List<ShStoreInfo> getStoreByStoreMode(String storeMode) throws DaoException {
		return hibernateTemplate.find("from ShStoreInfo where storeMode = " + storeMode);
	}

	@Override
	public void saveCluster(ShStoreCluster cluster) {
		//hibernateTemplate.save(cluster);
		hibernateTemplate.saveOrUpdate(cluster);
	}

	@Override
	public ViewStoreCluster queryStoreBeanById(Long storeAccountId)
			throws DaoException {
		try {
			String hql = "FROM ViewStoreCluster p WHERE 1=1 and storeId='"+storeAccountId+"' ";
			List<ViewStoreCluster> list=hibernateTemplate.find(hql);
			if(list.size()>0){
				return list.get(0);
			}else{
				return null;
			}

		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

}
