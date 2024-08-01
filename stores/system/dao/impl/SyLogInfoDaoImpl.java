package com.oim.stores.system.dao.impl;

import com.oim.stores.common.EntityDao;
import com.oim.stores.exception.DaoException;
import com.oim.stores.system.dao.SyLogInfoDao;
import com.oim.stores.system.domain.SyLogInfo;

public class SyLogInfoDaoImpl extends EntityDao implements SyLogInfoDao {

    @Override
    public void saveLog(SyLogInfo bean) throws DaoException {
	try {
	    getSessionFactory().getCurrentSession().save(bean);
	} catch (Exception e) {
	    throw new DaoException(e);
	}
    }

}
