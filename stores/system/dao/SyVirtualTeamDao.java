package com.oim.stores.system.dao;

import java.util.List;
import java.util.Map;

import com.oim.stores.common.Pagination;
import com.oim.stores.exception.DaoException;
import com.oim.stores.store.domain.ShTeamStore;
import com.oim.stores.system.domain.SyVirtualTeam;

public interface SyVirtualTeamDao {
	/**
	 * 根据上级团队id查询子团队列表
	 * 
	 * @param teamId
	 * @return
	 * @throws DaoException
	 *             Author zhuangjf Create On 2013-4-18 下午04:52:30
	 */
	public List<SyVirtualTeam> queryByParnetTeamId(Long teamId) throws DaoException;

	public Long queryCountByParnetTeamId(Long teamId) throws DaoException;

	public Long queryTeamStoreId(Long teamId) throws DaoException;

	public List<SyVirtualTeam> queryByParnetTeamId(Long teamId, Long storeType) throws DaoException;

	public Long queryCountByParnetTeamId(Long teamId, Long storeType) throws DaoException;

	public Pagination queryTeamForPage(Map<String, String> params, int pageNumber, int pageSize) throws DaoException;

	public List<SyVirtualTeam> queryTeamList(Map<String, String> params) throws DaoException;

    /**
     * 通过商家id查询最高级团队
     * @param storeId
     * @return
     * @throws DaoException
     * Author zhuangjf
     * Create On 2013-5-24 上午11:11:35
     */
    public SyVirtualTeam queryTeamByStoreId(Long storeId) throws DaoException;

    public List<ShTeamStore> queryShTeamStoreByUserId(String userId) throws DaoException;
}
