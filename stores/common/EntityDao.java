package com.oim.stores.common;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.oim.stores.exception.DaoException;
import com.oim.stores.framework.constants.SystemKeyWord;
import com.oim.stores.order.control.OrderCount;

/**
 * dao工具类
 * 
 * @author Administrator
 * 
 * @param <T>
 */
public class EntityDao<T> {
    protected HibernateTemplate hibernateTemplate;
    @Resource(name = "sessionFactory")
    protected SessionFactory sessionFactory;

    /**
     * 分页数据处理
     * 
     * @param hql
     * @param counthql
     * @param pageNumber
     * @param pageSize
     * @return
     * @throws DaoException
     */
    @SuppressWarnings("unchecked")
    protected Pagination queryForPage(String hql, String counthql,
	    int pageNumber, int pageSize) throws DaoException {
	Session session = sessionFactory.getCurrentSession();
	Pagination pagination = new Pagination();
	try {
	    int countNum = ((Number) session.createQuery(counthql)
		    .uniqueResult()).intValue();
	    Query query = session.createQuery(hql);
	    query.setFirstResult(pageNumber * pageSize);
	    query.setMaxResults(pageSize);
	    List<T> list = query.list();
	    pagination.setList(list);
	    pagination.setTotalRecord(countNum);
	    pagination.setPageNumber(pageNumber);
	} catch (Exception e) {
	    throw new DaoException(e);
	} finally {
	    // session.flush();
	    // session.close();
	}
	return pagination;
    }
    
  //ADD  李文平  2013-06-05  start
    /**
     * 卖场营销报表分页数据处理
     * 
     * @param hql
     * @param counthql
     * @param pageNumber
     * @param pageSize
     * @return
     * @throws DaoException
     */
    @SuppressWarnings("unchecked")
    protected Pagination queryOrderForPage(String hql, String counthql,int pageNumber, int pageSize) throws DaoException
    {
	Session session = sessionFactory.getCurrentSession();
	Pagination pagination = new Pagination();
	try
	{
	    int countNum = ((Number) session.createSQLQuery(counthql).uniqueResult()).intValue();
	    
	    Query query = session.createSQLQuery(hql).setResultTransformer(Transformers.aliasToBean(OrderCount.class));
	    query.setFirstResult(pageNumber * pageSize);
	    query.setMaxResults(pageSize);
	    List<T> list = query.list();
	    pagination.setList(list);
	    pagination.setTotalRecord(countNum);
	    pagination.setPageNumber(pageNumber);
	} catch (Exception e) {
	    throw new DaoException(e);
	} finally {
	    // session.flush();
	    // session.close();
	}
	return pagination;
    }
    
    /**
     * 导出卖场营销报表全部数据
     * 
     * @param hql
     * @return
     * @throws DaoException
     */
    @SuppressWarnings("unchecked")
    protected Pagination queryOrderForExcel(String hql) throws DaoException
    {
	Session session = sessionFactory.getCurrentSession();
	Pagination pagination = new Pagination();
	try
	{
	    Query query = session.createSQLQuery(hql).setResultTransformer(Transformers.aliasToBean(OrderCount.class));
	    List<T> list = query.list();
	    pagination.setList(list);
	} catch (Exception e) {
	    throw new DaoException(e);
	} finally {
	    // session.flush();
	    // session.close();
	}
	return pagination;
    }
    /**
     * 执行HQL语句，返回Map
     * 
     * @param hql
     * @return
     * @throws DaoException
     */
    protected List executeHqlForMap(String hql)
    {
    	Session session = sessionFactory.getCurrentSession();
    	Query query = session.createQuery(hql);
    	List list = query.list();
    	return list;
    }
    //ADD  李文平  2013-06-05  end

    protected Pagination queryMapForPage(String hql, String counthql,
	    List<Map<String, String>> fieldList, int pageNumber, int pageSize)
	    throws DaoException {
	Session session = sessionFactory.getCurrentSession();
	Pagination pagination = new Pagination();
	try {
	    int countNum = ((Number) session.createQuery(counthql)
		    .uniqueResult()).intValue();
	    Query query = session.createQuery(hql);
	    query.setFirstResult(pageNumber * pageSize);
	    query.setMaxResults(pageSize);
	    // query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
	    List<Object[]> list = query.list();
	    List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
	    for (Object[] objArray : list) {
		Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
		for (int i = 0; i < objArray.length; i++) {
		    String key = fieldList.get(i).get(
			    SystemKeyWord.FIELD_ALIAS_KEY);
		    Object value = objArray[i];
		    resultMap.put(key, value);
		}
		resultList.add(resultMap);
	    }
	    pagination.setList(resultList);
	    pagination.setTotalRecord(countNum);
	    pagination.setPageNumber(pageNumber);
	} catch (Exception e) {
	    throw new DaoException(e);
	} finally {
	    // session.flush();
	    // session.close();
	}
	return pagination;
    }

    public String hqlToSql(String hql) throws Exception {
	org.hibernate.hql.ast.QueryTranslatorImpl queryTranslator = new org.hibernate.hql.ast.QueryTranslatorImpl(
		hql, hql, java.util.Collections.EMPTY_MAP,
		(org.hibernate.engine.SessionFactoryImplementor) sessionFactory);
	queryTranslator.compile(java.util.Collections.EMPTY_MAP, false);
	return queryTranslator.getSQLString();
    }

    public HibernateTemplate getHibernateTemplate() {
	return hibernateTemplate;
    }

    public SessionFactory getSessionFactory() {
	return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
	this.sessionFactory = sessionFactory;
	this.hibernateTemplate = new HibernateTemplate(sessionFactory);
    }

    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
	this.hibernateTemplate = hibernateTemplate;
    }
}
