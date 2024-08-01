package com.oim.stores.store.dao.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.oim.stores.common.EntityDao;
import com.oim.stores.common.Pagination;
import com.oim.stores.common.Tool;
import com.oim.stores.exception.DaoException;
import com.oim.stores.store.dao.StoreAccountDao;
import com.oim.stores.store.domain.ShStoreAccount;
import com.oim.stores.store.domain.ViewStoreAccount;

/**
 * 商家信息
 */
@SuppressWarnings("unchecked")
public class StoreAccountDaoImpl extends EntityDao implements StoreAccountDao {

	@Override
	public void save(ShStoreAccount storeAccount) throws DaoException {
		try {
			hibernateTemplate.saveOrUpdate(storeAccount);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public void delete(ShStoreAccount storeAccount) throws DaoException {
		try {
			hibernateTemplate.delete(storeAccount);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public ShStoreAccount queryById(BigDecimal storeAccountId) throws DaoException {
		try {
			return hibernateTemplate.get(ShStoreAccount.class, storeAccountId);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}
	
	/**
	 * 根据账户类别获得商家账户
	 */
	@Override
	public List<ShStoreAccount> getStoreAccountByType(String type) {
		String hql = "from ShStoreAccount s where s.accountType=" + type + " and s.accountStatus=1";
		return hibernateTemplate.find(hql);
	}

	/**
	 * 根据商家Id和商家类别获得商家账户
	 */
	@Override
	public ShStoreAccount getStoreAccountByStoreIdAndType(String storeId,
			String type) {
		String hql = "from ShStoreAccount s where s.storeId=" + storeId + " and s.accountType=" + type + " and s.accountStatus=1";
		List<ShStoreAccount> list = hibernateTemplate.find(hql);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public Pagination queryAccForPage(Map<String, String> params,
			int pageNumber, int pageSize) throws DaoException {
		try {
			String hql = "FROM ViewStoreAccount p WHERE 1=1 ";
			Set<String> keys = params.keySet();
			for (String key : keys) {
				if (Tool.isNotEmpty(params.get(key))) {
					if ("storeName".equals(key)) {
						hql += " AND p." + key + " like '%" + params.get(key).trim() + "%' ";
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
	public ViewStoreAccount queryAccViewById(BigDecimal storeAccountId)
			throws DaoException {
		try {
			String hql = "FROM ViewStoreAccount p WHERE 1=1 and storeAccountId='"+storeAccountId+"' ";
			List<ViewStoreAccount> list=hibernateTemplate.find(hql);
			if(list.size()>0){
				return list.get(0);
			}else{
				return null;
			}

		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public ShStoreAccount queryAccById(Long storeAccountId) throws DaoException {
		try {
			return hibernateTemplate.get(ShStoreAccount.class, storeAccountId);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public List<ViewStoreAccount> queryAccByParam(Map<String, String> params)
			throws DaoException {
		try {
			String hql = "FROM ViewStoreAccount p WHERE 1=1 ";
			Set<String> keys = params.keySet();
			for (String key : keys) {
				if (Tool.isNotEmpty(params.get(key))) {
					if ("storeName".equals(key)) {
						hql += " AND p." + key + " like '%" + params.get(key).trim() + "%' ";
					} else {
						hql += " AND p." + key + " = '" + params.get(key).trim() + "' ";
					}
				}
			}
			List<ViewStoreAccount> list=hibernateTemplate.find(hql);
			return list;
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

}
