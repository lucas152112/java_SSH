/**
 * 
 */
package com.oim.stores.user.dao;

import java.util.List;
import java.util.Map;

import com.oim.stores.common.Pagination;
import com.oim.stores.exception.DaoException;
import com.oim.stores.user.domain.SyLogUser;

/**
 * 日志管理dao;
 * @author sunyuyan
 *
 */
public interface SyLogUserDao {
	/**
	 * 根据用户名获得用户日志信息
	 * 
	 * @param userName
	 * @return
	 */
	public List<SyLogUser> getUserLogByName(String userName);
	/**
	 * 获取日志列表
	 * 
	 */
	public Pagination queryForPage(Map<String, String> params, int pageNumber, int pageSize) throws DaoException;
	/**
	 * 获取管理员日志列表
	 * 
	 */
	public Pagination queryAdminForPage(Map<String, String> params, int pageNumber, int pageSize) throws DaoException;
	
	/**
	 * 获取商家日志列表
	 * 
	 */
	public Pagination queryBusinessForPage(Map<String, String> params, int pageNumber, int pageSize) throws DaoException;
	/**
	 * 获取集团接口上报日志列表
	 * 
	 */
	public Pagination queryReportForPage(Map<String, String> params, int pageNumber, int pageSize) throws DaoException;
	/**
	 * 获取订单操作日志
	 * 
	 */
	public Pagination queryOrderLogForPage(Map<String, String> params, int pageNumber, int pageSize) throws DaoException;
	/**
	 * 充值稽核对账日志
	 * 
	 */
	public Pagination queryCZJHLogForPage(Map<String, String> params, int pageNumber, int pageSize) throws DaoException;
	/**
	 * PPM接口日志
	 * 
	 */
	public Pagination queryPPMLogForPage(Map<String, String> params, int pageNumber, int pageSize) throws DaoException;
	/**
	 * 翼支付稽核对账日志
	 * 
	 */
	public Pagination queryYZFLogForPage(Map<String, String> params, int pageNumber, int pageSize) throws DaoException;
	/**
	 * 开卡操作日志
	 * 
	 */
	public Pagination queryKKLogForPage(Map<String, String> params, int pageNumber, int pageSize) throws DaoException;
	/**
	 * 充值操作日志
	 * 
	 */
	public Pagination queryCZLogForPage(Map<String, String> params, int pageNumber, int pageSize) throws DaoException;
	
}
