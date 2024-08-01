package com.oim.stores.system.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.oim.stores.user.domain.SyLogOrder;

public interface SyLogOrderService {

	public List<SyLogOrder> query(Map<String, String> params);

	public void save(SyLogOrder logOrder);
	
	public void save(BigDecimal orderNo, BigDecimal orderStatus, String ip,String remark);
}
