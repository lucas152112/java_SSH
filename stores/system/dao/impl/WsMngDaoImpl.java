package com.oim.stores.system.dao.impl;

import java.util.List;

import com.oim.stores.common.EntityDao;
import com.oim.stores.datadict.domain.SyParameter;
import com.oim.stores.exception.DaoException;
import com.oim.stores.system.dao.WsMngDao;
import com.oim.stores.system.domain.SySmsInfo;
import com.oim.stores.system.domain.SySmsParam;
/**
 * 接口管理dao子类
 * @author yuyan
 *
 */
@SuppressWarnings("unchecked")
public class WsMngDaoImpl extends EntityDao implements WsMngDao{

	@Override
	public SySmsParam qrySmsParam() throws DaoException {
		try {
			List<SySmsParam> list=hibernateTemplate.find("from SySmsParam ");
			if(list != null && list.size() > 0) return list.get(0);
			return null;
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public void save(SySmsParam sms) throws DaoException {
		try {
			hibernateTemplate.saveOrUpdate(sms);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public List<SyParameter> qryInterPath(String type) throws DaoException {
		try {
			List<SyParameter> list=hibernateTemplate.find("from SyParameter s where s.groupName='"+type+"' order by s.id.groupType ");
			return list;
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public void saveInterCfg(SyParameter cfg) throws DaoException {
		try {
			hibernateTemplate.saveOrUpdate(cfg);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public SySmsInfo qryQXTSmsInfo() throws DaoException {
		try {
			List<SySmsInfo> list=hibernateTemplate.find("from SySmsInfo ");
			if(list != null && list.size() > 0) return list.get(0);
			return null;
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public void saveQXTSms(SySmsInfo info) throws DaoException {
		try {
			hibernateTemplate.saveOrUpdate(info);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

}
