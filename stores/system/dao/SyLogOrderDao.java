package com.oim.stores.system.dao;

import java.util.List;
import java.util.Map;

import com.oim.stores.user.domain.SyLogOrder;

public interface SyLogOrderDao {

	public List<SyLogOrder> query(Map<String, String> params);

	public void save(SyLogOrder logOrder);

}
