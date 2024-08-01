package com.oim.stores.store.dao;

import java.util.Map;

import com.oim.stores.common.Pagination;
import com.oim.stores.exception.DaoException;
import com.oim.stores.store.domain.ShGroupTeam;
import com.oim.stores.store.domain.ShGroupTeamId;

public interface ShGroupTeamDao {
    /**
     * 保存聚类团队管理关系信息
     * @throws DaoException
     * Author zhuangjf
     * Create On 2013-5-23 上午10:47:14
     */
    public void save(ShGroupTeam shGroupTeam) throws DaoException;
    
    /**
     * 删除聚类团队管理关系信息
     * @param shGroupTeam
     * @throws DaoException
     * Author zhuangjf
     * Create On 2013-5-23 上午10:51:10
     */
    public void delete(ShGroupTeamId shGroupTeamId) throws DaoException;
    
    /**
     * 根据主键取对象实体
     * @param shGroupTeamId
     * @return
     * @throws DaoException
     * Author zhuangjf
     * Create On 2013-5-23 上午10:51:25
     */
    public ShGroupTeam queryById(ShGroupTeamId shGroupTeamId) throws DaoException;
    
    /**
     * 分页查询
     * @param params
     * @param pageNumber
     * @param pageSize
     * @return
     * @throws DaoException
     * Author zhuangjf
     * Create On 2013-5-23 上午11:13:55
     */
    public Pagination queryForPage(Map<String, String> params, int pageNumber,
	    int pageSize) throws DaoException;
}
