package com.oim.stores.shop.dao.impl;

import java.util.List;

import com.oim.stores.common.BaseDao;
import com.oim.stores.exception.DaoException;
import com.oim.stores.module.domain.StoreHomeSettingBean;
import com.oim.stores.product.domain.ShProductInfo;
import com.oim.stores.shop.dao.ModuleDao;
import com.oim.stores.shop.domain.ShProductShare;
import com.oim.stores.shop.domain.ShStoreHomeModule;
import com.oim.stores.shop.domain.ShStoreHomeProduct;
import com.oim.stores.shop.domain.ShStoreHomeSetting;
import com.oim.stores.system.domain.SyMenuList;
/**
 * 模块管理dao实现类
 * @author Administrator
 *
 */
public class ModuleDaoImpl extends BaseDao implements ModuleDao {

	@SuppressWarnings("unchecked")
	@Override
	public ShStoreHomeModule getmodule(String moduleId)
			throws DaoException {
		List<ShStoreHomeModule> modules = (List<ShStoreHomeModule>)this.getObjects("from ShStoreHomeModule where moduleId="+moduleId);
		if(modules != null && modules.size() > 0) return modules.get(0);
		return null;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<ShStoreHomeSetting> getSettings(String hql,int pageSize,int page)
			throws DaoException {
		return (List<ShStoreHomeSetting>)this.queryListObjectAllForPage(hql, pageSize, page);
	}


	@Override
	public void saveModule(ShStoreHomeModule module) throws DaoException {
		this.saveObject(module);
	}

	@Override
	public void updateModule(ShStoreHomeModule module) throws DaoException {
		this.updateObject(module);
	}
	
	@Override
	public void saveModuleSetting(ShStoreHomeSetting setting)
			throws DaoException {
		try {
			this.saveObject(setting);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void updateModuleSetting(ShStoreHomeSetting setting)
			throws DaoException {
		try{
			this.updateObject(setting);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

	@Override
	public List<?> getListByHql(String hql) {
		return this.getObjects(hql);
	}

	@Override
	public void removeModeleSetting(ShStoreHomeSetting setting)
			throws DaoException {
		this.deleteObject(setting);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ShProductInfo> getProductInfos(String hql,
			int pageSize, int page) {
		return (List<ShProductInfo>)this.queryListObjectAllForPage(hql, pageSize, page);
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<ShStoreHomeModule> getModules(String hql, int pageSize, int page) {
		return (List<ShStoreHomeModule>)this.queryListObjectAllForPage(hql, pageSize, page);
	}

	@Override
	public void removeModule(ShStoreHomeModule module) throws DaoException {
		this.deleteObject(module);
	}
	
	/**
	 * 分页查询分销商品
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<ShProductInfo> getShareProductForPage(String hql, int pageSize, int page) throws DaoException {
		return (List<ShProductInfo>)this.queryListObjectAllForPage(hql, pageSize, page);
	}

	/**
	 * 保存分销商品
	 */
	@Override
	public void saveShProductShare(ShProductShare share)
			throws DaoException {
		this.saveObject(share);
	}

	/**
	 * 修改分销商品
	 */
	@Override
	public void updateShProductShare(ShProductShare share) throws DaoException {
		this.updateObject(share);
	}

	/**
	 * 根据商品Id获得分销商品
	 */
	@SuppressWarnings("unchecked")
	@Override
	public ShProductShare getShareByProductId(String productId)
			throws DaoException {
		List<ShProductShare> list = (List<ShProductShare>)this.getObjects("from ShProductShare where productId="+productId);
		if(list != null && list.size() > 0)
			return list.get(0);
		return null;
	}

	/**
	 * 获得分销商品列表
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<ShProductInfo> getShareProducts() {
		StringBuilder hql = new StringBuilder("from ShProductInfo s where 1=1 ");
		hql.append(" and exists (select sps.productId from ShProductShare sps where sps.productId=s.productId" +
				" and sps.shareStaus = 1)");
		return (List<ShProductInfo>)this.getObjects(hql.toString());
	}

	/**
	 * 添加商家模块商品
	 */
	@Override
	public void saveStoreProduct(ShStoreHomeProduct storeProduct)
			throws DaoException {
		this.saveObject(storeProduct);
	}

	/**
	 * 根据模块类别获得模块列表
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<ShStoreHomeModule> getModulesByTypeId(String typeId)
			throws DaoException {
		return (List<ShStoreHomeModule>)this.getObjects("from ShStoreHomeModule where typeId="+typeId);
	}

	/**
	 * 根据模块Id获得商家模块配置信息
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<ShStoreHomeSetting> getSettingsByModuleId(String moduleId)
			throws DaoException {
		return (List<ShStoreHomeSetting>)this.getObjects("from ShStoreHomeSetting where moduleId = " + moduleId);
	}

	/**
	 * 根据商家模块Id获得商家商品
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<ShStoreHomeProduct> getStoreProductBySettingId(
			String storeModuleId) throws DaoException {
		return (List<ShStoreHomeProduct>)this.getObjects("from ShStoreHomeProduct where storeModuleId="+storeModuleId);
	}

	@Override
	public void removeStoreProduct(ShStoreHomeProduct storeProduct) throws DaoException {
		this.deleteObject(storeProduct);
	}

	

	@Override
	public ShStoreHomeProduct getProductById(String moduleProductId)
			throws DaoException {
		String hql = "from ShStoreHomeProduct s where s.moduleProductId="+moduleProductId;
		List<ShStoreHomeProduct> products = (List<ShStoreHomeProduct>)this.getObjects(hql);
		if(products != null && products.size() > 0) return products.get(0);
		return null;
	}

	@Override
	public void saveProduct(ShStoreHomeProduct product) throws DaoException {
		this.saveObject(product);
	}

	@Override
	public void updateProduct(ShStoreHomeProduct product) throws DaoException {
		this.updateObject(product);
	}

	@Override
	public List<ShStoreHomeProduct> getProductsByModuleId(String hql,
			int pageSize, int page) throws DaoException {
		return (List<ShStoreHomeProduct>)this.queryListObjectAllForPage(hql, pageSize, page);
	}

	@Override
	public void removeProduct(ShStoreHomeProduct product) throws DaoException {
		this.deleteObject(product);
	}

	
}
