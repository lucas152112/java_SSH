package com.oim.stores.system.dao;

import java.math.BigDecimal;
import java.util.List;

import com.oim.stores.exception.DaoException;
import com.oim.stores.store.domain.ShTeamStore;
import com.oim.stores.system.domain.SyAdminUser;
import com.oim.stores.system.domain.SyRight;
import com.oim.stores.system.domain.SyVirtualTeam;

public interface SyRightDao {
    public List<SyRight> queryByUserName(String userName,
	    List<BigDecimal> rightRangeList, List<BigDecimal> rightTypeList)
	    throws DaoException;
    
    public SyAdminUser queryByUserName(String userName) throws DaoException;
    
    public void saveVirtualTeam(SyVirtualTeam bean) throws DaoException;
    
    public void saveTeamStore(ShTeamStore bean) throws DaoException;
    
    public void deleteTeamStore(BigDecimal storeId) throws DaoException;
    
    public Long queryTeamCountByStoreId(BigDecimal storeId) throws DaoException;
}
