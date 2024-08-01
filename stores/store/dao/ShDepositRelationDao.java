package com.oim.stores.store.dao;

import java.util.Map;

import com.oim.stores.common.Pagination;
import com.oim.stores.exception.DaoException;
import com.oim.stores.store.domain.ShDepositRelation;
import com.oim.stores.store.domain.ShDepositRelationId;

public interface ShDepositRelationDao {
    /**
     * 分页查询
     * 
     * @param params
     * @param pageNumber
     * @param pageSize
     * @return
     * @throws DaoException
     *             Author zhuangjf Create On 2013-4-18 下午03:04:52
     */
    public Pagination queryForPage(Map<String, String> params, int pageNumber,
	    int pageSize) throws DaoException;

    /**
     * 根据主键取对象实体
     * @param id
     * @return
     * @throws DaoException
     * Author zhuangjf
     * Create On 2013-4-18 下午03:08:19
     */
    public ShDepositRelation queryById(ShDepositRelationId id)
	    throws DaoException;

    /**
     * 保存托管关系
     * @param bean
     * @throws DaoException
     * Author zhuangjf
     * Create On 2013-4-18 下午03:08:30
     */
    public void save(ShDepositRelation bean) throws DaoException;

    /**
     * 删除托管关系
     * @param id
     * @throws DaoException
     * Author zhuangjf
     * Create On 2013-4-18 下午03:08:34
     */
    public void deleteById(ShDepositRelationId id) throws DaoException;
    
    /**
     * 托管商家受理团队所属商家
     * @param storeId
     * @param teamStoreId
     * @return
     * @throws DaoException
     * Author zhuangjf
     * Create On 2013-4-23 下午04:36:38
     */
    public Long queryDepositTeamStoreId(Long storeId) throws DaoException;
    
    
    /**
     * 商家是否已成为受理团队
     * @param storeId
     * @return
     * @throws DaoException
     * Author zhuangjf
     * Create On 2013-4-25 上午09:17:25
     */
    public boolean isExistsDepositTeam(Long storeId) throws DaoException;
    
    /**
     * 取最大tsid,用于排序
     * @return
     * @throws DaoException
     * Author zhuangjf
     * Create On 2013-5-6 下午02:32:40
     */
    public Long queryMaxTsId() throws DaoException;
}
