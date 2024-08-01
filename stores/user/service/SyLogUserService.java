package com.oim.stores.user.service;

import java.util.Map;

import com.oim.stores.common.PageResponse;
import com.oim.stores.exception.ServiceException;
/**
 * 日志管理service;
 * @author yuyan;
 *
 */
public interface SyLogUserService {

	/**
	 * 查询日志列表
	 * @param params
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 * @throws ServiceException
	 */
	public PageResponse queryForPage(Map<String, String> params, int pageNumber, int pageSize) throws ServiceException;
	/**
	 * 查询管理员操作记录
	 * @param params
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 * @throws ServiceException
	 */
	public PageResponse queryAdminForPage(Map<String, String> params, int pageNumber, int pageSize) throws ServiceException;
	/**
	 * 查询商家操作记录
	 * @param params
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 * @throws ServiceException
	 */
	public PageResponse queryBusinessForPage(Map<String, String> params, int pageNumber, int pageSize) throws ServiceException;
	/**
	 * 查询上报日志记录
	 * @param params
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 * @throws ServiceException
	 */
	public PageResponse queryReportForPage(Map<String, String> params, int pageNumber, int pageSize) throws ServiceException;
	/**
	 * 查询订单操作日志
	 * @param params
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 * @throws ServiceException
	 */
	public PageResponse queryOrderLogForPage(Map<String, String> params, int pageNumber, int pageSize) throws ServiceException;
	/**
	 * 充值稽核对账日志
	 * @param params
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 * @throws ServiceException
	 */
	public PageResponse qryCZJHLogForPage(Map<String, String> params, int pageNumber, int pageSize) throws ServiceException;
	/**
	 * PPM接口日志
	 * @param params
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 * @throws ServiceException
	 */
	public PageResponse queryPPMLogForPage(Map<String, String> params, int pageNumber, int pageSize) throws ServiceException;
	/**
	 * 翼支付稽核对账日志
	 * @param params
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 * @throws ServiceException
	 */
	public PageResponse queryYZFLogForPage(Map<String, String> params, int pageNumber, int pageSize) throws ServiceException;
	/**
	 * 开卡操作日志
	 * @param params
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 * @throws ServiceException
	 */
	public PageResponse queryKKLogForPage(Map<String, String> params, int pageNumber, int pageSize) throws ServiceException;
	/**
	 * 充值操作日志
	 * @param params
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 * @throws ServiceException
	 */
	public PageResponse queryCZLogForPage(Map<String, String> params, int pageNumber, int pageSize) throws ServiceException;
	
}
