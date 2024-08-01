package com.oim.stores.user.service.impl;

import java.util.Map;

import com.oim.stores.common.PageResponse;
import com.oim.stores.common.Pagination;
import com.oim.stores.exception.DaoException;
import com.oim.stores.exception.ServiceException;
import com.oim.stores.user.dao.SyLogUserDao;
import com.oim.stores.user.service.SyLogUserService;
/**
 * 日志管理service子类
 * @author Administrator
 *
 */
public class SyLogUserServiceImpl implements SyLogUserService {
	public SyLogUserDao syLogUserDao;
	@Override
	public PageResponse queryForPage(Map<String, String> params,
			int pageNumber, int pageSize) throws ServiceException {
		PageResponse pageResponse = new PageResponse();
		try {
			Pagination pagination = syLogUserDao.queryForPage(params, pageNumber, pageSize);
			pageResponse.setList(pagination.getList());
			pageResponse.setTotalRecord(pagination.getTotalRecord());
			pageResponse.setSuccess(true);
			return pageResponse;
		} catch (DaoException e) {
			pageResponse.setSuccess(false);
			throw new ServiceException(e);
		}
	}
	/**
	 * 管理员操作记录
	 */
	public PageResponse queryAdminForPage(Map<String, String> params,
			int pageNumber, int pageSize) throws ServiceException {
		PageResponse pageResponse = new PageResponse();
		try {
			Pagination pagination = syLogUserDao.queryAdminForPage(params, pageNumber, pageSize);
			pageResponse.setList(pagination.getList());
			pageResponse.setTotalRecord(pagination.getTotalRecord());
			pageResponse.setSuccess(true);
			return pageResponse;
		} catch (DaoException e) {
			pageResponse.setSuccess(false);
			throw new ServiceException(e);
		}
	}
	
	public SyLogUserDao getSyLogUserDao() {
		return syLogUserDao;
	}
	public void setSyLogUserDao(SyLogUserDao syLogUserDao) {
		this.syLogUserDao = syLogUserDao;
	}
	/**
	 * 查询商家操作记录
	 */
	public PageResponse queryBusinessForPage(Map<String, String> params,
			int pageNumber, int pageSize) throws ServiceException {
		PageResponse pageResponse = new PageResponse();
		try {
			Pagination pagination = syLogUserDao.queryBusinessForPage(params, pageNumber, pageSize);
			pageResponse.setList(pagination.getList());
			pageResponse.setTotalRecord(pagination.getTotalRecord());
			pageResponse.setSuccess(true);
			return pageResponse;
		} catch (DaoException e) {
			pageResponse.setSuccess(false);
			throw new ServiceException(e);
		}
	}
	@Override
	public PageResponse queryReportForPage(Map<String, String> params,
			int pageNumber, int pageSize) throws ServiceException {
		PageResponse pageResponse = new PageResponse();
		try {
			Pagination pagination = syLogUserDao.queryReportForPage(params, pageNumber, pageSize);
			pageResponse.setList(pagination.getList());
			pageResponse.setTotalRecord(pagination.getTotalRecord());
			pageResponse.setSuccess(true);
			return pageResponse;
		} catch (DaoException e) {
			pageResponse.setSuccess(false);
			throw new ServiceException(e);
		}
	}
	@Override
	public PageResponse queryOrderLogForPage(Map<String, String> params,
			int pageNumber, int pageSize) throws ServiceException {
		PageResponse pageResponse = new PageResponse();
		try {
			Pagination pagination = syLogUserDao.queryOrderLogForPage(params, pageNumber, pageSize);
			pageResponse.setList(pagination.getList());
			pageResponse.setTotalRecord(pagination.getTotalRecord());
			pageResponse.setSuccess(true);
			return pageResponse;
		} catch (DaoException e) {
			pageResponse.setSuccess(false);
			throw new ServiceException(e);
		}
	}
	@Override
	public PageResponse qryCZJHLogForPage(Map<String, String> params,
			int pageNumber, int pageSize) throws ServiceException {
		PageResponse pageResponse = new PageResponse();
		try {
			Pagination pagination = syLogUserDao.queryCZJHLogForPage(params, pageNumber, pageSize);
			pageResponse.setList(pagination.getList());
			pageResponse.setTotalRecord(pagination.getTotalRecord());
			pageResponse.setSuccess(true);
			return pageResponse;
		} catch (DaoException e) {
			pageResponse.setSuccess(false);
			throw new ServiceException(e);
		}
	}
	@Override
	public PageResponse queryPPMLogForPage(Map<String, String> params,
			int pageNumber, int pageSize) throws ServiceException {
		PageResponse pageResponse = new PageResponse();
		try {
			Pagination pagination = syLogUserDao.queryPPMLogForPage(params, pageNumber, pageSize);
			pageResponse.setList(pagination.getList());
			pageResponse.setTotalRecord(pagination.getTotalRecord());
			pageResponse.setSuccess(true);
			return pageResponse;
		} catch (DaoException e) {
			pageResponse.setSuccess(false);
			throw new ServiceException(e);
		}
	}
	@Override
	public PageResponse queryCZLogForPage(Map<String, String> params,
			int pageNumber, int pageSize) throws ServiceException {
		PageResponse pageResponse = new PageResponse();
		try {
			Pagination pagination = syLogUserDao.queryCZLogForPage(params, pageNumber, pageSize);
			pageResponse.setList(pagination.getList());
			pageResponse.setTotalRecord(pagination.getTotalRecord());
			pageResponse.setSuccess(true);
			return pageResponse;
		} catch (DaoException e) {
			pageResponse.setSuccess(false);
			throw new ServiceException(e);
		}
	}
	@Override
	public PageResponse queryKKLogForPage(Map<String, String> params,
			int pageNumber, int pageSize) throws ServiceException {
		PageResponse pageResponse = new PageResponse();
		try {
			Pagination pagination = syLogUserDao.queryKKLogForPage(params, pageNumber, pageSize);
			pageResponse.setList(pagination.getList());
			pageResponse.setTotalRecord(pagination.getTotalRecord());
			pageResponse.setSuccess(true);
			return pageResponse;
		} catch (DaoException e) {
			pageResponse.setSuccess(false);
			throw new ServiceException(e);
		}
	}
	
	@Override
	public PageResponse queryYZFLogForPage(Map<String, String> params,
			int pageNumber, int pageSize) throws ServiceException {
		PageResponse pageResponse = new PageResponse();
		try {
			Pagination pagination = syLogUserDao.queryYZFLogForPage(params, pageNumber, pageSize);
			pageResponse.setList(pagination.getList());
			pageResponse.setTotalRecord(pagination.getTotalRecord());
			pageResponse.setSuccess(true);
			return pageResponse;
		} catch (DaoException e) {
			pageResponse.setSuccess(false);
			throw new ServiceException(e);
		}
	}

}
