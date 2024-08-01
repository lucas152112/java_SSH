package com.oim.stores.system.service;

import java.util.List;
import java.util.Map;

import com.oim.stores.common.PageResponse;
import com.oim.stores.exception.ServiceException;
import com.oim.stores.store.domain.ShTeamStore;
import com.oim.stores.system.domain.SyVirtualTeam;

public interface SyVirtualTeamService {
	public List<SyVirtualTeam> queryByParnetTeamId(Long teamId) throws ServiceException;

	public Long queryCountByParnetTeamId(Long teamId) throws ServiceException;

	public Long queryTeamStoreId(Long teamId) throws ServiceException;

	public List<SyVirtualTeam> queryByParnetTeamId(Long teamId, Long storeType) throws ServiceException;

	public Long queryCountByParnetTeamId(Long teamId, Long storeType) throws ServiceException;

	public List<SyVirtualTeam> queryTeamList(Map<String, String> params) throws ServiceException;

	public PageResponse queryTeamForPage(Map<String, String> params, int pageNumber, int pageSize) throws ServiceException;

	public SyVirtualTeam queryTeamByStoreId(Long storeId) throws ServiceException;

	public List<ShTeamStore> queryShTeamStoreByUserId(String userId) throws ServiceException;

}
