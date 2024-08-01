package com.oim.stores.system.dao;

import java.util.List;

import com.oim.stores.datadict.domain.SyParameter;
import com.oim.stores.exception.DaoException;
import com.oim.stores.system.domain.SySmsInfo;
import com.oim.stores.system.domain.SySmsParam;
/**
 * 接口管理dao
 * @author yuyan
 *
 */
public interface WsMngDao {
	/**
	 * Save the system parameters
	 * @param sms
	 * @throws DaoException
	 */
	public void save(SySmsParam sms) throws DaoException;
	/**
	 * query the system parameter;
	 * @return
	 * @throws DaoException
	 */
	public SySmsParam qrySmsParam() throws DaoException;
	/**
	 * save qxt sms info;
	 * @param info
	 * @throws DaoException
	 */
	public void saveQXTSms(SySmsInfo info)throws DaoException;
	/**
	 * query qi xin tong sms info;
	 * @return
	 * @throws DaoException
	 */
	public SySmsInfo qryQXTSmsInfo()throws DaoException;
	/**
	 * query interface path;
	 * @param type
	 * @return
	 * @throws DaoException
	 */
	public List<SyParameter> qryInterPath(String type) throws DaoException;
	/**
	 * save interface parameters;
	 * @param cfg
	 * @throws DaoException
	 */
	public void saveInterCfg(SyParameter cfg) throws DaoException;
}		
