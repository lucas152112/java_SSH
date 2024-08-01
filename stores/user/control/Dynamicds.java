package com.oim.stores.user.control;

import java.util.logging.Logger;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
/**
 * Dynamic connect;
 * @author yuyan;
 *
 */
public class Dynamicds extends AbstractRoutingDataSource{
	/**
	 * 重写父类方法;
	 */
	@Override
	protected Object determineCurrentLookupKey() {
		
        return DbContextHolder.getDbType(); 
	}
	/**
	 * log;
	 */
	public Logger getParentLogger() {
		// TODO Auto-generated method stub
		return null;
	}

}
