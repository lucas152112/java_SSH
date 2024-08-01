package com.oim.stores.system.service;

import java.math.BigDecimal;
import java.util.List;

import com.oim.stores.exception.ServiceException;
import com.oim.stores.system.domain.SyAdminUser;
import com.oim.stores.system.domain.SyRight;

public interface SyRightService {
    public List<SyRight> queryByUserName(String userName,
	    List<BigDecimal> rightRangeList, List<BigDecimal> rightTypeList)
	    throws ServiceException;
    
    public SyAdminUser queryByUserName(String userName) throws ServiceException;
}
