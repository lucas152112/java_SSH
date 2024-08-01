/**
 * 
 */
package com.oim.stores.user.dao.impl;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.oim.stores.common.EntityDao;
import com.oim.stores.common.Pagination;
import com.oim.stores.common.Tool;
import com.oim.stores.exception.DaoException;
import com.oim.stores.user.dao.SyLogUserDao;
import com.oim.stores.user.domain.SyLogUser;

/**
 * 系统日志dao子类
 * @author yuyan
 *
 */
@SuppressWarnings("unchecked")
public class SyLogUserDaoImpl extends EntityDao implements SyLogUserDao {

	/**
	 * 根据用户名获得用户日志信息
	 * 
	 * @param userName
	 * @return
	 */
	@Override
	public List<SyLogUser> getUserLogByName(String userName) {
		//return hibernateTemplate.find("from SyUserInfo u where u.userName='" + userName + "'");
		return null;
	}

	/**
	 * 获取日志列表
	 * 
	 */
	@Override
	public Pagination queryForPage(Map<String, String> params, int pageNumber,
			int pageSize) throws DaoException {
		try {
			String preHql="select new com.oim.stores.user.domain.SyLogUserBean(u.userName,p.id.userAction,p.ipaddress,p.memo,p.id.userId,p.id.sysTime) ";
			String hql = " FROM SyLogUser p,SyUserInfo u WHERE p.id.userId=u.userId and 1=1 ";
//			String hql = " FROM SyLogUser p WHERE 1=1 ";
			Set<String> keys = params.keySet();
			for (String key : keys) {
				if("userName".equals(key)){
					if (Tool.isNotEmpty(params.get(key))) {
						hql += " AND u." + key + " like '%" + params.get(key).trim() + "%' ";
					}
				}else if("sysTime".equals(key)){
					hql += " AND to_char(p.id.sysTime,'yyyy-MM-dd') = '" + params.get(key).trim() + "' ";
				}else{
					if (Tool.isNotEmpty(params.get(key))) {
						hql += " AND u." + key + " = '" + params.get(key).trim() + "' ";
					}
				}
			}
			hql+=" order by p.id.sysTime desc ";
			//String counthql = " SELECT COUNT(*) " + hql;
			String counthql = " SELECT COUNT(*) " + hql;
			//return queryForPage(hql, counthql, pageNumber, pageSize);
			return queryForPage(preHql+hql, counthql, pageNumber, pageSize);

		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	public Pagination queryAdminForPage(Map<String, String> params, int pageNumber,
			int pageSize) throws DaoException {
		try {
			String preHql="select new com.oim.stores.user.domain.SyLogUserBean(u.userName,p.id.userAction,p.ipaddress,p.memo,p.id.userId,p.id.sysTime) ";
			String hql = " FROM SyLogUser p,SyUserInfo u WHERE p.id.userId=u.userId and 1=1 ";
			hql+=" and p.id.userId in (select vu.id.userId from ViewStoreUserInfo vu where vu.id.roleId=1) ";
//			String hql = " FROM SyLogUser p WHERE 1=1 ";
			Set<String> keys = params.keySet();
			for (String key : keys) {
				if("userName".equals(key)){
					if (Tool.isNotEmpty(params.get(key))) {
						hql += " AND u." + key + " like '%" + params.get(key).trim() + "%' ";
					}
				}else if("sysTime".equals(key)){
					hql += " AND to_char(p.id.sysTime,'yyyy-MM-dd') = '" + params.get(key).trim() + "' ";
				}else {
					if (Tool.isNotEmpty(params.get(key))) {
						hql += " AND p.id." + key + " = '" + params.get(key).trim() + "' ";
					}
				}
			}
			hql+=" order by p.id.sysTime desc ";
			//String counthql = " SELECT COUNT(*) " + hql;
			String counthql = " SELECT COUNT(*) " + hql;
			//return queryForPage(hql, counthql, pageNumber, pageSize);
			return queryForPage(preHql+hql, counthql, pageNumber, pageSize);

		} catch (Exception e) {
			throw new DaoException(e);
		}
	}
	/**
	 * 查询商家日志
	 */
	@Override
	public Pagination queryBusinessForPage(Map<String, String> params,
			int pageNumber, int pageSize) throws DaoException {
		try {
			String preHql="select new com.oim.stores.user.domain.SyLogStoreBean(u.storeName,p.id.userAction,p.ipaddress,p.memo,p.id.userId,p.id.sysTime) ";
			String hql = " FROM SyLogStoreUser p,ShStoreInfo u WHERE p.storeId=u.storeId and 1=1 ";
			Set<String> keys = params.keySet();
			for (String key : keys) {
				if("storeName".equals(key)){
					if (Tool.isNotEmpty(params.get(key))) {
						hql += " AND u." + key + " like '%" + params.get(key).trim() + "%' ";
					}
				}else if("sysTime".equals(key)){
					hql += " AND to_char(p.id.sysTime,'yyyy-MM-dd') = '" + params.get(key).trim() + "' ";
				}else{
					if (Tool.isNotEmpty(params.get(key))) {
						hql += " AND u." + key + " = '" + params.get(key).trim() + "' ";
					}
				}
			}
			hql+=" order by p.id.sysTime desc ";
			String counthql = " SELECT COUNT(*) " + hql;
			return queryForPage(preHql+hql, counthql, pageNumber, pageSize);

		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public Pagination queryReportForPage(Map<String, String> params,
			int pageNumber, int pageSize) throws DaoException {
		try {
			@SuppressWarnings("unused")
			String preHql="select new com.oim.stores.user.domain.JTReportLogBean(sl.schLogId,sl.schId,sl.processTime,sl.resultCode,sl.remark,sl.transId,sl.orderId,o.orderCode) ";
			@SuppressWarnings("unused")
			String preHql2="select new com.oim.stores.user.domain.JTReportLogBean(sl.schLogId,sl.schId,sl.processTime,sl.resultCode,sl.remark,sl.transId,sl.orderId,o.orderNumber) ";
			//String hql = " FROM SySchLog sl,TyOrderInfo o WHERE sl.orderId=o.orderNo and 1=1 ";
			String hql=" FROM SySchLog sl where 1=1 ";
			Set<String> keys = params.keySet();
			for (String key : keys) {
				if(Tool.isNotEmpty(params.get(key))){
					if("orderCode".equals(key)){
						//hql += " AND o." + key + " ='" + params.get(key).trim()+ "' ";
					}else if("startDate".equals(key)){
						hql+=" AND sl.processTime >= to_date('" + params.get(key).trim() + "', 'yyyy-MM-dd HH24:mi:ss') ";
					}else if("endDate".equals(key)){
						hql+=" AND sl.processTime <= to_date('" + params.get(key).trim() + "', 'yyyy-MM-dd HH24:mi:ss') ";
					}else{
						hql += " AND sl." + key + " = '" + params.get(key).trim() + "' ";
					}
				}
			}
			hql+=" order by sl.processTime desc ";
			String counthql = " SELECT COUNT(*) " + hql;
			return queryForPage(hql, counthql, pageNumber, pageSize);

		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public Pagination queryOrderLogForPage(Map<String, String> params,
			int pageNumber, int pageSize) throws DaoException {
		try {
			//String preHql="select new com.oim.stores.user.domain.SyLogStoreBean(u.storeName,p.id.userAction,p.ipaddress,p.memo,p.id.userId,p.id.sysTime) ";
			String hql = " FROM SyLogOrder u WHERE 1=1 ";
			Set<String> keys = params.keySet();
			for (String key : keys) {
				if(Tool.isNotEmpty(params.get(key))){
					if("startDate".equals(key)){
						hql+=" AND u.id.sysTime >= to_date('" + params.get(key).trim() + "', 'yyyy-MM-dd HH24:mi:ss') ";
					}else if("endDate".equals(key)){
						hql+=" AND u.id.sysTime <= to_date('" + params.get(key).trim() + "', 'yyyy-MM-dd HH24:mi:ss') ";
					}else{
						hql += " AND u.id." + key + " = '" + params.get(key).trim() + "' ";
					}
				}
			}
			hql+=" order by u.id.sysTime desc ";
			String counthql = " SELECT COUNT(*) " + hql;
			return queryForPage(hql, counthql, pageNumber, pageSize);

		} catch (Exception e) {
			throw new DaoException(e);
		}
	}
	@Override
	public Pagination queryCZJHLogForPage(Map<String, String> params,
			int pageNumber, int pageSize) throws DaoException {
		try {
			//String preHql="select new com.oim.stores.user.domain.SyLogStoreBean(u.storeName,p.id.userAction,p.ipaddress,p.memo,p.id.userId,p.id.sysTime) ";
			String hql = " FROM ViewSydzlogId u WHERE 1=1 ";
			Set<String> keys = params.keySet();
			for (String key : keys) {
				if(Tool.isNotEmpty(params.get(key))){
					if("startDate".equals(key)){
						hql+=" AND u.id.processdate >= to_date('" + params.get(key).trim() + "', 'yyyy-MM-dd HH24:mi:ss') ";
					}else if("endDate".equals(key)){
						hql+=" AND u.id.processdate <= to_date('" + params.get(key).trim() + "', 'yyyy-MM-dd HH24:mi:ss') ";
					}else{
						hql += " AND u.id." + key + " = '" + params.get(key).trim() + "' ";
					}
				}
			}
			hql+=" order by u.id.processdate desc ";
			String counthql = " SELECT COUNT(*) " + hql;
			return queryForPage(hql, counthql, pageNumber, pageSize);

		} catch (Exception e) {
			throw new DaoException(e);
		}
	}
	@Override
	public Pagination queryPPMLogForPage(Map<String, String> params,
			int pageNumber, int pageSize) throws DaoException {
		try {
			//String preHql="select new com.oim.stores.user.domain.SyLogStoreBean(u.storeName,p.id.userAction,p.ipaddress,p.memo,p.id.userId,p.id.sysTime) ";
			String hql = " FROM PpmApiLog u WHERE 1=1 ";
			Set<String> keys = params.keySet();
			for (String key : keys) {
				if(Tool.isNotEmpty(params.get(key))){
					if("startDate".equals(key)){
						hql+=" AND u.id.requesttime >= to_date('" + params.get(key).trim() + "', 'yyyy-MM-dd HH24:mi:ss') ";
					}else if("endDate".equals(key)){
						hql+=" AND u.id.requesttime <= to_date('" + params.get(key).trim() + "', 'yyyy-MM-dd HH24:mi:ss') ";
					}else{
						hql += " AND u.id." + key + " = '" + params.get(key).trim() + "' ";
					}
				}
			}
			hql+=" order by u.id.requesttime desc ";
			String counthql = " SELECT COUNT(*) " + hql;
			//return queryForPage(hql, counthql, pageNumber, pageSize);
			return queryForPage(hql, counthql, pageNumber, pageSize);

		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public Pagination queryCZLogForPage(Map<String, String> params,
			int pageNumber, int pageSize) throws DaoException {
		try {
			//String preHql="select new com.oim.stores.user.domain.SyLogStoreBean(u.storeName,p.id.userAction,p.ipaddress,p.memo,p.id.userId,p.id.sysTime) ";
			String hql = " FROM Reapilog u WHERE substr(u.id.funcid,1,6)='100001' and 1=1 ";
			Set<String> keys = params.keySet();
			for (String key : keys) {
				if(Tool.isNotEmpty(params.get(key))){
					if("startDate".equals(key)){
						hql+=" AND u.id.requesttime >= to_date('" + params.get(key).trim() + "', 'yyyy-MM-dd HH24:mi:ss') ";
					}else if("endDate".equals(key)){
						hql+=" AND u.id.requesttime <= to_date('" + params.get(key).trim() + "', 'yyyy-MM-dd HH24:mi:ss') ";
					}else{
						hql += " AND u.id." + key + " = '" + params.get(key).trim() + "' ";
					}
				}
			}
			hql+=" order by u.id.requesttime desc ";
			String counthql = " SELECT COUNT(*) " + hql;
			return queryForPage(hql, counthql, pageNumber, pageSize);

		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public Pagination queryKKLogForPage(Map<String, String> params,
			int pageNumber, int pageSize) throws DaoException {
		try {
			//String preHql="select new com.oim.stores.user.domain.SyLogStoreBean(u.storeName,p.id.userAction,p.ipaddress,p.memo,p.id.userId,p.id.sysTime) ";
			String hql = " FROM Reapilog u WHERE substr(u.id.funcid,1,3)='101' ";
			Set<String> keys = params.keySet();
			for (String key : keys) {
				if(Tool.isNotEmpty(params.get(key))){
					if("startDate".equals(key)){
						hql+=" AND u.id.requesttime >= to_date('" + params.get(key).trim() + "', 'yyyy-MM-dd HH24:mi:ss') ";
					}else if("endDate".equals(key)){
						hql+=" AND u.id.requesttime <= to_date('" + params.get(key).trim() + "', 'yyyy-MM-dd HH24:mi:ss') ";
					}else{
						hql += " AND u.id." + key + " = '" + params.get(key).trim() + "' ";
					}
				}
			}
			hql+=" order by u.id.requesttime desc ";
			String counthql = " SELECT COUNT(*) " + hql;
			return queryForPage(hql, counthql, pageNumber, pageSize);

		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public Pagination queryYZFLogForPage(Map<String, String> params,
			int pageNumber, int pageSize) throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}
}
