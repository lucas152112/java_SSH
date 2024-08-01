package com.oim.stores.system.dao.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Query;

import com.oim.stores.common.EntityDao;
import com.oim.stores.common.Pagination;
import com.oim.stores.common.Tool;
import com.oim.stores.exception.DaoException;
import com.oim.stores.store.domain.ShTeamStore;
import com.oim.stores.system.dao.SyVirtualTeamDao;
import com.oim.stores.system.domain.SyVirtualTeam;

public class SyVirtualTeamDaoImpl extends EntityDao implements SyVirtualTeamDao {

	@Override
	public List<SyVirtualTeam> queryByParnetTeamId(Long teamId) throws DaoException {
		try {
			String queryString = "from SyVirtualTeam as model where 1=1 and model.parentTeamId=:parentTeamId ";
			queryString += " order by model.teamSeq ";
			Query query = getSessionFactory().getCurrentSession().createQuery(queryString);
			query.setParameter("parentTeamId", new BigDecimal(teamId));
			return query.list();
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public Long queryCountByParnetTeamId(Long teamId) throws DaoException {
		try {
			String queryString = "select count(*) from SyVirtualTeam as model where 1=1 and model.parentTeamId=:parentTeamId ";
			queryString += " order by model.teamSeq ";
			Query query = getSessionFactory().getCurrentSession().createQuery(queryString);
			query.setParameter("parentTeamId", new BigDecimal(teamId));
			return ((Number) query.uniqueResult()).longValue();
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public Long queryTeamStoreId(Long teamId) throws DaoException {
		try {
			String queryString = "from ShTeamStore as model where model.id.teamId=:teamId";
			Query query = getSessionFactory().getCurrentSession().createQuery(queryString);
			query.setParameter("teamId", new BigDecimal(teamId));
			List<ShTeamStore> list = query.list();
			if (list.size() > 0) {
				ShTeamStore bean = list.get(0);
				return bean.getId().getStoreId().longValue();
			} else {
				return null;
			}
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public List<SyVirtualTeam> queryByParnetTeamId(Long teamId, Long storeType) throws DaoException {
		try {
			String queryString = "from SyVirtualTeam as model  where 1=1 " + " and model.parentTeamId=:parentTeamId ";
			queryString += "and exists(select b.id.teamId from ShTeamStore b,ShStoreInfo c where b.id.storeId=c.storeId" + " and model.teamId=b.id.teamId and c.storeType=:storeType  )";
			queryString += " order by model.teamSeq ";
			Query query = getSessionFactory().getCurrentSession().createQuery(queryString);
			query.setParameter("parentTeamId", new BigDecimal(teamId));
			query.setParameter("storeType", new BigDecimal(storeType));
			System.out.println(hqlToSql(queryString));
			return query.list();
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public Long queryCountByParnetTeamId(Long teamId, Long storeType) throws DaoException {
		try {
			String queryString = "select count(*) from SyVirtualTeam as model  where 1=1 " + " and model.parentTeamId=:parentTeamId ";
			queryString += "and exists(select b.id.teamId from ShTeamStore b,ShStoreInfo c where b.id.storeId=c.storeId" + " and model.teamId=b.id.teamId and c.storeType=:storeType  )";
			queryString += " order by model.teamSeq ";
			Query query = getSessionFactory().getCurrentSession().createQuery(queryString);
			query.setParameter("parentTeamId", new BigDecimal(teamId));
			query.setParameter("storeType", new BigDecimal(storeType));
			System.out.println(hqlToSql(queryString));
			return ((Number) query.uniqueResult()).longValue();
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public Pagination queryTeamForPage(Map<String, String> params, int pageNumber, int pageSize) throws DaoException {
		try {
			String hql = "FROM SyVirtualTeam p WHERE 1=1 ";
			Set<String> keys = params.keySet();
			for (String key : keys) {
				if (Tool.isNotEmpty(params.get(key))) {
					if ("teamName".equals(key)) {
						hql += " AND p." + key + " like '%" + params.get(key).trim() + "%' ";
					} else if ("storeIds".equals(key)) {
						hql += " and p.storeId not in (" + params.get(key).trim() + ") ";
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
	public List<SyVirtualTeam> queryTeamList(Map<String, String> params) throws DaoException {
		try {
			String hql = "FROM SyVirtualTeam p WHERE 1=1 ";
			Set<String> keys = params.keySet();
			for (String key : keys) {
				if (Tool.isNotEmpty(params.get(key))) {
					if ("teamName".equals(key)) {
						hql += " AND p." + key + " like '%" + params.get(key).trim() + "%' ";
					} else if ("teamIds".equals(key)) {
						hql += " and p.teamId in (" + params.get(key).trim() + ") ";
					} else {
						hql += " AND p." + key + " = '" + params.get(key).trim() + "' ";
					}
				}
			}
			List<SyVirtualTeam> list = hibernateTemplate.find(hql);
			return list;
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public SyVirtualTeam queryTeamByStoreId(Long storeId) throws DaoException {
		try {
			String queryString = "FROM SyVirtualTeam p WHERE 1=1 ";
			queryString += " and exists(select b.id.teamId  from ShTeamStore b where p.teamId=b.id.teamId and b.id.storeId=" + storeId + ")";
			queryString += " order by p.teamCode";
			Query query = getSessionFactory().getCurrentSession().createQuery(queryString);
			query.setFirstResult(0);
			query.setMaxResults(1);
			List<SyVirtualTeam> list = query.list();
			if (list.size() > 0) {
				return list.get(0);
			}
			return null;
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	/**
	 * 根据用户查团队
	 */
	@Override
	public List<ShTeamStore> queryShTeamStoreByUserId(String userId) throws DaoException {
		try {

			String hql = "select tb from ShGroupTeam ga ,ShTeamStore tb where ga.id.subTeamId=tb.id.teamId and ga.id.teamId in (";
			hql += "select b from SyVirtualTeamUser a ,SyVirtualTeam b ";
			hql += "where a.id.teamId=b.teamId and a.id.userId=" + userId;
			hql += ")";
			return hibernateTemplate.find(hql);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

}
