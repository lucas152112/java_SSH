package com.oim.stores.system.dao;

import com.oim.stores.exception.DaoException;
import com.oim.stores.system.domain.SyLogInfo;

public interface SyLogInfoDao {
    /**
     * 保存操作日志
     * @throws DaoException
     * Author zhuangjf
     * Create On 2013-4-19 上午09:24:45
     */
    public void saveLog(SyLogInfo bean) throws DaoException;

}
