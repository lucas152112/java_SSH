package com.oim.stores.system.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.oim.stores.system.dao.SyLogOrderDao;
import com.oim.stores.system.service.SyLogOrderService;
import com.oim.stores.user.domain.SyLogOrder;
import com.oim.stores.user.domain.SyLogOrderId;

/**
 * 订单日志
 */
public class SyLogOrderServiceImpl implements SyLogOrderService {

	public SyLogOrderDao logOrderDao;

	@Override
	public List<SyLogOrder> query(Map<String, String> params) {
		return logOrderDao.query(params);
	}

	@Override
	public void save(SyLogOrder logOrder) {
		logOrder.getId().setSysTime(new Date());
		logOrderDao.save(logOrder);
	}

	public void setLogOrderDao(SyLogOrderDao logOrderDao) {
		this.logOrderDao = logOrderDao;
	}

	@Override
	public void save(BigDecimal orderNo, BigDecimal orderStatus, String ip,
			String remark) {
		SyLogOrder log = new SyLogOrder();
		SyLogOrderId id = new SyLogOrderId();
		id.setOrderNo(orderNo);
		id.setOrderStatus(orderStatus);
		id.setSysTime(new Date());
		log.setIpaddress(ip);
		log.setRemark(remark);
		log.setId(id);
		logOrderDao.save(log);
	}

}
