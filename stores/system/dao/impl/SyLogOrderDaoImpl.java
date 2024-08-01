package com.oim.stores.system.dao.impl;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.oim.stores.common.CommonUtils;
import com.oim.stores.common.EntityDao;
import com.oim.stores.system.dao.SyLogOrderDao;
import com.oim.stores.user.domain.SyLogOrder;

public class SyLogOrderDaoImpl extends EntityDao implements SyLogOrderDao {

	@Override
	public List<SyLogOrder> query(Map<String, String> params) {
		String hql = "from SyLogOrder t where 1=1 ";
		Set<String> keys = params.keySet();
		for (String key : keys) {
			if (!CommonUtils.isEmpty(key)) {
				hql += " AND t." + key + " = '" + params.get(key) + "'";
			}
		}
		hql += " order by t.id.sysTime desc ";
		System.out.println(hql);
		return hibernateTemplate.find(hql);
	}

	@Override
	public void save(SyLogOrder logOrder) {
		hibernateTemplate.save(logOrder);
	}
}
