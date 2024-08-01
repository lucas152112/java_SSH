package com.oim.stores.system.dao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;

import com.oim.stores.common.EntityDao;
import com.oim.stores.exception.DaoException;
import com.oim.stores.store.domain.ShTeamStore;
import com.oim.stores.system.dao.SyRightDao;
import com.oim.stores.system.domain.SyAdminUser;
import com.oim.stores.system.domain.SyRight;
import com.oim.stores.system.domain.SyVirtualTeam;
import com.sun.org.apache.commons.collections.MapUtils;

public class SyRightDaoImpl extends EntityDao implements SyRightDao {

    @Override
    public List<SyRight> queryByUserName(String userName,
	    List<BigDecimal> rightRangeList, List<BigDecimal> rightTypeList)
	    throws DaoException {
	try {
	    String sql = "SELECT * FROM SY_RIGHT E WHERE EXISTS( "
		    + " SELECT 1 FROM SY_VIRTUAL_TEAM A,SY_VIRTUAL_TEAM_RIGHT D WHERE 1 = 1  "
		    + " AND (EXISTS (SELECT B.TEAM_ID FROM SY_VIRTUAL_TEAM B, SY_VIRTUAL_TEAM_USER C,SY_ADMIN_USER F "
		    + " WHERE B.TEAM_ID = C.TEAM_ID AND C.USER_ID = F.USER_ID AND F.USER_NAME=:userName AND "
		    + " (A.TEAM_CODE LIKE B.TEAM_CODE || '%')))  "
		    + " AND A.TEAM_ID=D.TEAM_ID "
		    + "AND D.RIGHT_ID=E.RIGHT_ID ) ";
	    if (rightRangeList != null && rightRangeList.size() > 0) {
		sql += " and e.right_range in(:rightRanges)";
	    }
	    if (rightTypeList != null && rightTypeList.size() > 0) {
		sql += " and e.right_type in(:rightTypes)";
	    }
	    sql += " order by e.right_seq";
	    SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(
		    sql);
	    query.setParameter("userName", userName);
	    if (rightRangeList != null && rightRangeList.size() > 0) {
		query.setParameterList("rightRanges", rightRangeList);
	    }
	    if (rightTypeList != null && rightTypeList.size() > 0) {
		query.setParameterList("rightTypes", rightTypeList);
	    }
	    query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
	    List<Map<String, String>> list = query.list();
	    List<SyRight> result = new ArrayList<SyRight>();
	    for (Map<String, String> map : list) {
		SyRight right = new SyRight();
		right.setParentRightId(new BigDecimal(MapUtils.getString(map,
			"PARENT_RIGHT_ID")));
		right.setRightCode(MapUtils.getString(map, "RIGHT_CODE"));
		right.setRightDesc(MapUtils.getString(map, "RIGHT_DESC"));
		right.setRightId(new BigDecimal(MapUtils.getString(map,
			"RIGHT_ID")));
		right.setRightName(MapUtils.getString(map, "RIGHT_NAME"));
		right.setRightRange(new BigDecimal(MapUtils.getString(map,
			"RIGHT_RANGE")));
		right.setRightSeq(new BigDecimal(MapUtils.getString(map,
			"RIGHT_SEQ")));
		right.setRightType(new BigDecimal(MapUtils.getString(map,
			"RIGHT_TYPE")));
		right.setRightValue(MapUtils.getString(map, "RIGHT_VALUE"));
		result.add(right);
	    }
	    return result;
	} catch (Exception e) {
	    throw new DaoException(e.getMessage(), e.getCause());
	}
    }

    @Override
    public SyAdminUser queryByUserName(String userName) throws DaoException {
	try {
	    String queryString = "from SyAdminUser as model where model.userName=:userName";
	    Query query = sessionFactory.getCurrentSession().createQuery(
		    queryString);
	    query.setParameter("userName", userName);
	    List<SyAdminUser> list = query.list();
	    if (list.size() > 0) {
		return list.get(0);
	    }
	    return null;
	} catch (Exception e) {
	    throw new DaoException(e.getMessage(), e.getCause());
	}
    }

    @Override
    public void saveVirtualTeam(SyVirtualTeam bean) throws DaoException {
	try {
	    sessionFactory.getCurrentSession().save(bean);
	} catch (Exception e) {
	    throw new DaoException(e.getMessage(), e.getCause());
	}
    }

    @Override
    public void saveTeamStore(ShTeamStore bean) throws DaoException {
	try {
	    sessionFactory.getCurrentSession().save(bean);
	} catch (Exception e) {
	    throw new DaoException(e.getMessage(), e.getCause());
	}
    }

    @Override
    public void deleteTeamStore(BigDecimal storeId) throws DaoException {
	try {
	    String hql = "delete from ShTeamStore model where model.id.storeId=:storeId";
	    Query query = sessionFactory.getCurrentSession().createQuery(hql);
	    query.executeUpdate();
	} catch (Exception e) {
	    throw new DaoException(e.getMessage(), e.getCause());
	}
    }

    @Override
    public Long queryTeamCountByStoreId(BigDecimal storeId) throws DaoException {
	try {
	    String queryString = "select count(*) from ShTeamStore as model where model.id.storeId=:storeId";
	    Query query = sessionFactory.getCurrentSession().createQuery(
		    queryString);
	    query.setParameter("storeId", storeId);
	    Long count = ((Number) query.uniqueResult()).longValue();
	    return count;
	} catch (Exception e) {
	    throw new DaoException(e.getMessage(), e.getCause());
	}
    }

}
