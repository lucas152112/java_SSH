package com.oim.stores.common;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
/**
 * 通用dao类
 * @author Administrator
 *
 */
public class BaseDao extends HibernateDaoSupport {
	
	public BaseDao(){
		super();
	}
	
	/**
	 * 保存对象信息到数据库
	 * @param obj 需要进行持久化操作的对象
	 */
	public void saveObject(Object obj){
		this.getHibernateTemplate().save(obj);
	}
	
	/**
	 * 更新持久化对象
	 * @param obj 需要更新的对象
	 */
	public void updateObject(Object obj){
		this.getHibernateTemplate().merge(obj);
	}
	
	/**
	 * 使用HQL语句进行查询
	 * @param hsql 查询语句
	 * @return 符合条件的对象集合
	 */
	public List<?> getObjects(String hsql) throws DataAccessException{
		List<?> result = this.getHibernateTemplate().find(hsql);
		return result;
	}
	
	/**
	 * 使用Hql语句进行查询
	 * @param queryString 查询语句
	 * @param values 参数
	 * @return 符合条件的对象集合
	 */
	public List<?> getObjects(String queryString, Object[] values){
		return getHibernateTemplate().find(queryString, values);
	}
	
	/**
	 * 根据ID值得到持久化的对象
	 * @param cls 对象的类型
	 * @param id ID值
	 * @return 指定ID的对象
	 */
	@SuppressWarnings("unchecked")
	public <T> T getObject(Class<T> cls,String id){
		Object result = this.getHibernateTemplate().get(cls, id);
		return (T)result;
	}
	
	/**
	 * 删除对象信息
	 * @param obj 被删除的对象
	 */
	public void deleteObject(Object obj){
		this.getHibernateTemplate().delete(obj);
	}
	
	
	/**
	 * 分页查询
	 * @param queryString
	 * @param pageSize
	 * @param page
	 * @return
	 */
	public List<?> queryListObjectAllForPage(String queryString,int pageSize,int page){
		Session session = this.getHibernateTemplate().getSessionFactory().openSession();
		List<?> list = null;
		try {
			Query query = session.createQuery(queryString);
			query.setFirstResult((page - 1) * pageSize);
			query.setMaxResults(pageSize);
			list = (List<?>) query.list();
		} finally {
			session.close();
		}
		return list;
	}
	
	/**
	 * 根据sql查询
	 * @param sql
	 * @return
	 */
	public List<?> getObjectsBySql(String sql){
		Session session = this.getHibernateTemplate().getSessionFactory().openSession();
		try {
			return session.createSQLQuery(sql).list();
		} finally {
			session.close();
		}
	}
	
	
}

