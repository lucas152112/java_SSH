package com.oim.stores.system.service.impl;

import java.util.List;
import java.util.Map;

import com.oim.stores.common.PageResponse;
import com.oim.stores.common.Pagination;
import com.oim.stores.exception.DaoException;
import com.oim.stores.exception.ServiceException;
import com.oim.stores.store.domain.ShTeamStore;
import com.oim.stores.system.dao.SyVirtualTeamDao;
import com.oim.stores.system.domain.SyVirtualTeam;
import com.oim.stores.system.service.SyVirtualTeamService;

public class SyVirtualTeamServiceImpl implements SyVirtualTeamService {

	private SyVirtualTeamDao syVirtualTeamDao;

	/**
	 * @return the syVirtualTeamDao
	 */
	public SyVirtualTeamDao getSyVirtualTeamDao() {
		return syVirtualTeamDao;
	}

	/**
	 * @param syVirtualTeamDao
	 *            the syVirtualTeamDao to set
	 */
	public void setSyVirtualTeamDao(SyVirtualTeamDao syVirtualTeamDao) {
		this.syVirtualTeamDao = syVirtualTeamDao;
	}

	@Override
	public List<SyVirtualTeam> queryByParnetTeamId(Long teamId) throws ServiceException {
		try {
			return syVirtualTeamDao.queryByParnetTeamId(teamId);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public Long queryCountByParnetTeamId(Long teamId) throws ServiceException {
		try {
			return syVirtualTeamDao.queryCountByParnetTeamId(teamId);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public Long queryTeamStoreId(Long teamId) throws ServiceException {
		try {
			return syVirtualTeamDao.queryTeamStoreId(teamId);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public Long queryCountByParnetTeamId(Long teamId, Long storeType) throws ServiceException {
		try {
			return syVirtualTeamDao.queryCountByParnetTeamId(teamId, storeType);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<SyVirtualTeam> queryByParnetTeamId(Long teamId, Long storeType) throws ServiceException {
		try {
			return syVirtualTeamDao.queryByParnetTeamId(teamId, storeType);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public PageResponse queryTeamForPage(Map<String, String> params, int pageNumber, int pageSize) throws ServiceException {
		PageResponse pageResponse = new PageResponse();
		try {
			Pagination pagination = syVirtualTeamDao.queryTeamForPage(params, pageNumber, pageSize);
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
	public List<SyVirtualTeam> queryTeamList(Map<String, String> params) throws ServiceException {
		try {
			return syVirtualTeamDao.queryTeamList(params);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public SyVirtualTeam queryTeamByStoreId(Long storeId) throws ServiceException {
		try {
			return syVirtualTeamDao.queryTeamByStoreId(storeId);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * 根据用户查团队
	 * 
	 * @throws ServiceException
	 */
	@Override
	public List<ShTeamStore> queryShTeamStoreByUserId(String userId) throws ServiceException {
		try {
			return syVirtualTeamDao.queryShTeamStoreByUserId(userId);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

}
