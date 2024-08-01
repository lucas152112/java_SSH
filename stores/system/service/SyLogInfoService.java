package com.oim.stores.system.service;

import com.oim.stores.exception.ServiceException;
import com.oim.stores.system.domain.SyLogInfo;

public interface SyLogInfoService {
    public void saveLog(SyLogInfo bean) throws ServiceException;

}
