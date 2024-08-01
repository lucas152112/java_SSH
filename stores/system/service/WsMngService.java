package com.oim.stores.system.service;

import java.util.List;

import com.oim.stores.datadict.domain.SyParameter;
import com.oim.stores.exception.ServiceException;
import com.oim.stores.system.domain.SySmsInfo;
import com.oim.stores.system.domain.SySmsParam;
/**
 * 接口管理service
 * @author yuyan
 *
 */
public interface WsMngService {
	/**
	 * save system parameter;
	 * @param sms
	 * @throws ServiceException
	 */
	public void save(SySmsParam sms) throws ServiceException;
	/**
	 * query system param;
	 * @return
	 * @throws ServiceException
	 */
	public SySmsParam qrySmsParam() throws ServiceException;
	
	public void saveQXTSms(SySmsInfo info)throws ServiceException;
	/**
	 * query qi xin tong sms info;
	 * @return
	 * @throws ServiceException
	 */
	public SySmsInfo qryQxtSmsInfo() throws ServiceException;
	/**
	 * query the interface path;
	 * @param type
	 * @return
	 * @throws ServiceException
	 */
	public List<SyParameter> qryInterPath(String type) throws ServiceException;
	/**
	 * save inferface param;
	 * @param cfg
	 * @throws ServiceException
	 */
	public void saveInterCfg(SyParameter cfg) throws ServiceException;
}
