package com.oim.stores.store.dao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Query;

import com.oim.stores.common.EntityDao;
import com.oim.stores.common.Pagination;
import com.oim.stores.common.Tool;
import com.oim.stores.exception.DaoException;
import com.oim.stores.framework.constants.SystemKeyWord;
import com.oim.stores.store.dao.ShGroupTeamDao;
import com.oim.stores.store.domain.ShDepositRelation;
import com.oim.stores.store.domain.ShGroupTeam;
import com.oim.stores.store.domain.ShGroupTeamId;

public class ShGroupTeamDaoImpl extends EntityDao implements ShGroupTeamDao {

    @Override
    public void save(ShGroupTeam shGroupTeam) throws DaoException {
	try {
	    getSessionFactory().getCurrentSession().save(shGroupTeam);
	} catch (Exception e) {
	    throw new DaoException(e);
	}

    }

    @Override
    public void delete(ShGroupTeamId shGroupTeamId) throws DaoException {
	try {
	    Query query = getSessionFactory()
		    .getCurrentSession()
		    .createQuery(
			    "delete from ShGroupTeam as model where model.id.teamId=:teamId and model.id.subTeamId=:subTeamId");
	    query.setLong("teamId", shGroupTeamId.getTeamId());
	    query.setLong("subTeamId", shGroupTeamId.getSubTeamId());
	    query.executeUpdate();
	} catch (Exception e) {
	    throw new DaoException(e);
	}

    }

    @Override
    public ShGroupTeam queryById(ShGroupTeamId shGroupTeamId)
	    throws DaoException {
	try {
	    return (ShGroupTeam) getSessionFactory().getCurrentSession()
		    .get(ShGroupTeam.class, shGroupTeamId);
	} catch (Exception e) {
	    throw new DaoException(e);
	}
    }

    @Override
    public Pagination queryForPage(Map<String, String> params, int pageNumber,
	    int pageSize) throws DaoException {
	try {
	    List<Map<String, String>> fieldList = new ArrayList<Map<String, String>>();
	    Map<String, String> fieldMap = new HashMap<String, String>();
	    fieldMap.put(SystemKeyWord.FIELD_KEY, "a.id.teamId");
	    fieldMap.put(SystemKeyWord.FIELD_ALIAS_KEY, "teamId");
	    fieldList.add(fieldMap);
	    fieldMap = new HashMap<String, String>();
	    fieldMap.put(SystemKeyWord.FIELD_KEY, "a.id.subTeamId");
	    fieldMap.put(SystemKeyWord.FIELD_ALIAS_KEY, "subTeamId");
	    fieldList.add(fieldMap);
	    fieldMap = new HashMap<String, String>();
	    fieldMap.put(SystemKeyWord.FIELD_KEY, "b.teamName");
	    fieldMap.put(SystemKeyWord.FIELD_ALIAS_KEY, "teamName");
	    fieldList.add(fieldMap);
	    fieldMap = new HashMap<String, String>();
	    fieldMap.put(SystemKeyWord.FIELD_KEY, "c.teamName");
	    fieldMap.put(SystemKeyWord.FIELD_ALIAS_KEY, "subTeamName");
	    fieldList.add(fieldMap);
	    fieldMap = new HashMap<String, String>();
	    fieldMap.put(SystemKeyWord.FIELD_KEY, "e.storeName");
	    fieldMap.put(SystemKeyWord.FIELD_ALIAS_KEY, "subTeamStoreName");
	    fieldList.add(fieldMap);
	    fieldMap = new HashMap<String, String>();
	    fieldMap.put(SystemKeyWord.FIELD_KEY, "e.regionalId");
	    fieldMap.put(SystemKeyWord.FIELD_ALIAS_KEY, "subRegionalId");
	    fieldList.add(fieldMap);
	    String queryFields = "";
	    for (Map<String, String> map : fieldList) {
		if (queryFields.equals("")) {
		    queryFields += "select " + map.get(SystemKeyWord.FIELD_KEY);
		} else {
		    queryFields += "," + map.get(SystemKeyWord.FIELD_KEY);
		}
	    }
	    String queryString = " from ShGroupTeam a,SyVirtualTeam  as b, SyVirtualTeam  as c,ShTeamStore d,ShStoreInfo as e";
	    queryString += " where a.id.teamId=b.teamId and a.id.subTeamId=c.teamId and c.teamId=d.id.teamId and d.id.storeId=e.storeId";
	    if (params != null) {
		Set<String> keys = params.keySet();
		for (String key : keys) {
		    if ("regionalId".equals(key)) {
			if (Tool.isNotEmpty(params.get(key))) {
			    queryString += " AND e." + key + " = "
				    + params.get(key).trim() + "";
			}
		    }
		    if ("teamName".equals(key)) {
			if (Tool.isNotEmpty(params.get(key))) {
			    queryString += " AND b." + key + " like '%"
				    + params.get(key).trim() + "%' ";
			}
		    }
		}
	    }
	    String countQueryString = "select count(*)  " + queryString + "";
	    queryString = queryFields + queryString;
	    queryString+=" order by a.id.teamId desc";
	    return queryMapForPage(queryString, countQueryString, fieldList,
		    pageNumber, pageSize);
	} catch (Exception e) {
	    throw new DaoException(e);
	}
    }

}
