package com.oim.stores.store.dao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Query;
import org.springframework.transaction.annotation.Transactional;

import com.oim.stores.common.EntityDao;
import com.oim.stores.common.Pagination;
import com.oim.stores.common.Tool;
import com.oim.stores.exception.DaoException;
import com.oim.stores.framework.constants.SystemKeyWord;
import com.oim.stores.framework.utils.BeanFactory;
import com.oim.stores.store.dao.ShDepositRelationDao;
import com.oim.stores.store.domain.ShDepositRelation;
import com.oim.stores.store.domain.ShDepositRelationId;

@Transactional
public class ShDepositRelationDaoImpl extends EntityDao implements
	ShDepositRelationDao {

    @Override
    public Pagination queryForPage(Map<String, String> params, int pageNumber,
	    int pageSize) throws DaoException {
	// TODO Auto-generated method stub
	try {
	    List<Map<String, String>> fieldList = new ArrayList<Map<String, String>>();
	    Map<String, String> fieldMap = new HashMap<String, String>();
	    fieldMap.put(SystemKeyWord.FIELD_KEY, "a.id.teamId");
	    fieldMap.put(SystemKeyWord.FIELD_ALIAS_KEY, "teamId");
	    fieldList.add(fieldMap);
	    fieldMap = new HashMap<String, String>();
	    fieldMap.put(SystemKeyWord.FIELD_KEY, "a.id.storeid");
	    fieldMap.put(SystemKeyWord.FIELD_ALIAS_KEY, "storeId");
	    fieldList.add(fieldMap);
	    fieldMap = new HashMap<String, String>();
	    fieldMap.put(SystemKeyWord.FIELD_KEY, "b.teamName");
	    fieldMap.put(SystemKeyWord.FIELD_ALIAS_KEY, "teamName");
	    fieldList.add(fieldMap);
	    fieldMap = new HashMap<String, String>();
	    fieldMap.put(SystemKeyWord.FIELD_KEY, "c.storeName");
	    fieldMap.put(SystemKeyWord.FIELD_ALIAS_KEY, "storeName");
	    fieldList.add(fieldMap);
	    fieldMap = new HashMap<String, String>();
	    fieldMap.put(SystemKeyWord.FIELD_KEY, "e.storeName");
	    fieldMap.put(SystemKeyWord.FIELD_ALIAS_KEY, "teamStoreName");
	    fieldList.add(fieldMap);
	    String queryFields = "";
	    for (Map<String, String> map : fieldList) {
		if (queryFields.equals("")) {
		    queryFields += "select " + map.get(SystemKeyWord.FIELD_KEY);
		} else {
		    queryFields += "," + map.get(SystemKeyWord.FIELD_KEY);
		}
	    }
	    String queryString = " from ShDepositRelation as a,SyVirtualTeam  as b,ShStoreInfo as c,ShTeamStore d,ShStoreInfo as e";
	    queryString += " where a.id.teamId=b.teamId and a.id.storeid=c.storeId and b.teamId=d.id.teamId and d.id.storeId=e.storeId";
	    if (params != null) {
		Set<String> keys = params.keySet();
		for (String key : keys) {
		    if ("teamName".equals(key)) {
			if (Tool.isNotEmpty(params.get(key))) {
			    queryString += " AND b." + key + " like '%"
				    + params.get(key).trim() + "%' ";
			}
		    }
		    if ("storeName".equals(key)) {
			if (Tool.isNotEmpty(params.get(key))) {
			    queryString += " AND c." + key + " like '%"
				    + params.get(key).trim() + "%' ";
			}
		    }
		}
	    }
	    String countQueryString = "select count(*)  " + queryString + "";
	    queryString = queryFields + queryString;
	    queryString+=" order by a.tsId desc";
	    return queryMapForPage(queryString, countQueryString, fieldList,
		    pageNumber, pageSize);
	} catch (Exception e) {
	    throw new DaoException(e);
	}
    }

    @Override
    public ShDepositRelation queryById(ShDepositRelationId id)
	    throws DaoException {
	try {
	    return (ShDepositRelation) getSessionFactory().getCurrentSession()
		    .get(ShDepositRelation.class, id);
	} catch (Exception e) {
	    throw new DaoException(e);
	}
    }

    @Override
    public void save(ShDepositRelation bean) throws DaoException {
	try {
	    Long maxTsId = queryMaxTsId();
	    BigDecimal tsId = new BigDecimal(maxTsId.intValue()+1);
	    bean.setTsId(tsId);
	    getSessionFactory().getCurrentSession().save(bean);
	} catch (Exception e) {
	    throw new DaoException(e);
	}

    }

    @Override
    public void deleteById(ShDepositRelationId id) throws DaoException {
	try {
	    Query query = getSessionFactory()
		    .getCurrentSession()
		    .createQuery(
			    "delete from ShDepositRelation as model where model.id.teamId=:teamId and model.id.storeid=:storeId");
	    query.setLong("teamId", id.getTeamId());
	    query.setLong("storeId", id.getStoreid());
	    query.executeUpdate();
	} catch (Exception e) {
	    throw new DaoException(e);
	}

    }

    @Override
    public Long queryDepositTeamStoreId(Long storeId) throws DaoException {
	try {
	    String hql = "select b.id.storeId from ShDepositRelation a,ShTeamStore b  "
		    + "where a.id.teamId=b.id.teamId "
		    + " and a.id.storeid=:storeId" + " group by b.id.storeId";
	    Query query = getSessionFactory().getCurrentSession().createQuery(
		    hql);
	    query.setLong("storeId", storeId);
	    List list = query.list();
	    if (list.size() == 1) {
		return ((BigDecimal) list.get(0)).longValue();
	    } else if (list.size() > 1) {
		return new Long(-1);
	    } else {
		return 0L;
	    }
	} catch (Exception e) {
	    throw new DaoException(e);
	}
    }

    @Override
    public boolean isExistsDepositTeam(Long storeId) throws DaoException {
	try {
	    String hql = "select count(*) from ShDepositRelation a , ShTeamStore b "
		    + "where a.id.teamId=b.id.teamId and b.id.storeId=:storeId";
	    Query query = getSessionFactory().getCurrentSession().createQuery(
		    hql);
	    query.setLong("storeId", storeId);
	    int ct = ((Number) query.uniqueResult()).intValue();
	    return ct > 0 ? true : false;
	} catch (Exception e) {
	    throw new DaoException(e);
	}
    }

    public static void main(String[] arg) {
	ShDepositRelationDao dao = BeanFactory
		.getInstance("shDepositRelationDao");
	try {
	    dao.queryDepositTeamStoreId(1L);
	} catch (DaoException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }

    @Override
    public Long queryMaxTsId() throws DaoException {
	try {
	    String hql = "select max(tsId) from ShDepositRelation a ";
	    Query query = getSessionFactory().getCurrentSession().createQuery(
		    hql);
	    if (query.uniqueResult() == null) {
		return 0L;
	    } else {
		Long ct = ((Number) query.uniqueResult()).longValue();
		return ct;
	    }
	} catch (Exception e) {
	    throw new DaoException(e);
	}
    }
}
