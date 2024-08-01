package com.oim.stores.system.service.impl;

import java.util.List;

import com.oim.stores.datadict.domain.SyParameter;
import com.oim.stores.exception.ServiceException;
import com.oim.stores.system.dao.WsMngDao;
import com.oim.stores.system.domain.SySmsInfo;
import com.oim.stores.system.domain.SySmsParam;
import com.oim.stores.system.service.WsMngService;
/**
 * 接口管理service子类
 * @author yuyan
 *
 */
public class WsMngServiceImpl implements WsMngService {
	private WsMngDao wsdao;//接口管理dao;
	@Override
	public SySmsParam qrySmsParam() throws ServiceException {
		try {
			return wsdao.qrySmsParam();
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void save(SySmsParam sms) throws ServiceException {
		try {
			wsdao.save(sms);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	public WsMngDao getWsdao() {
		return wsdao;
	}

	public void setWsdao(WsMngDao wsdao) {
		this.wsdao = wsdao;
	}

	@Override
	public List<SyParameter> qryInterPath(String name) throws ServiceException {
		try {
			return wsdao.qryInterPath(name);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void saveInterCfg(SyParameter cfg) throws ServiceException {
		try {
			wsdao.saveInterCfg(cfg);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public SySmsInfo qryQxtSmsInfo() throws ServiceException {
		try {
			return wsdao.qryQXTSmsInfo();
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void saveQXTSms(SySmsInfo info) throws ServiceException {
		try {
			wsdao.saveQXTSms(info);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

}
