package com.oim.stores.system.dao;

import java.util.Map;

import com.oim.stores.common.Pagination;
import com.oim.stores.exception.DaoException;
import com.oim.stores.system.domain.SchInfoBean;
/**
 * 任务管理dao;
 * @author Administrator
 *
 */
public interface SchInfoDao {
	/**
	 * According to the random parameter query;
	 * @param params
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 * @throws DaoException
	 */
    public Pagination queryForPage(Map<String, String> params, int pageNumber, int pageSize) throws DaoException;
    /**
     * update the task config;
     * @param schinfo
     * @throws DaoException
     */
    public void updateSchInfo(SchInfoBean schinfo)throws DaoException;
    /**
     * According to the parameter query 
     * @param schid
     * @return
     * @throws DaoException
     */
    public SchInfoBean queryById(String schid)throws DaoException;
}
