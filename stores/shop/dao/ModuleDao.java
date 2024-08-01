package com.oim.stores.shop.dao;

import java.util.List;

import com.oim.stores.exception.DaoException;
import com.oim.stores.exception.ServiceException;
import com.oim.stores.module.domain.StoreHomeSettingBean;
import com.oim.stores.product.domain.ShProductInfo;
import com.oim.stores.shop.domain.ShProductShare;
import com.oim.stores.shop.domain.ShStoreHomeModule;
import com.oim.stores.shop.domain.ShStoreHomeProduct;
import com.oim.stores.shop.domain.ShStoreHomeSetting;
/**
 * 模版管理dao
 * @author Administrator
 *
 */
public interface ModuleDao {

	/**
	 * 根据模块Id获得模块
	 * @param moduleId
	 * @return
	 * @throws DaoException
	 */
	public ShStoreHomeModule getmodule(String moduleId) throws DaoException;
	
	
	/**
	 * 根据商家Id获得商家配置信息
	 * @param storeId
	 * @return
	 * @throws DaoException
	 */
	public List<ShStoreHomeSetting> getSettings(String hql,int pageSize,int page) throws DaoException;
	
	
	/**
	 * 增加模块
	 * @param module
	 * @throws DaoException
	 */
	public void saveModule(ShStoreHomeModule module) throws DaoException;
	
	/**
	 * 修改模块
	 * @param module
	 * @throws DaoException
	 */
	public void updateModule(ShStoreHomeModule module) throws DaoException;
	
	/**
	 * 增加模块配置信息
	 * @param setting
	 * @throws DaoException
	 */
	public void saveModuleSetting(ShStoreHomeSetting setting) throws DaoException;
	
	/**
	 * 修改模块配置信息
	 * @param setting
	 * @throws DaoException
	 */
	public void updateModuleSetting(ShStoreHomeSetting setting) throws DaoException;
	
	
	
	/**
	 * 根据hql查询
	 * @param hql
	 * @return
	 */
	public List<?> getListByHql(String hql);
	/**
	 * 根据Id获得模块商品
	 * @param moduleProductId
	 * @return
	 */
	public ShStoreHomeProduct getProductById(String moduleProductId) throws DaoException;
	/**
	 * 删除模块商品
	 * @param product
	 * @throws DaoException
	 */
	public void removeProduct(ShStoreHomeProduct product) throws DaoException;
	/**
	 * 根据模块Id获得模块商品列表
	 * @param moduleId
	 * @return
	 * @throws DaoException
	 */
	public List<ShStoreHomeProduct> getProductsByModuleId(String hql,int pageSize,int page) throws DaoException;
	/**
	 * 增加模块商品
	 * @param product
	 * @throws DaoException
	 */
	public void saveProduct(ShStoreHomeProduct product) throws DaoException;
	
	/**
	 * 修改模块商品
	 * @param product
	 * @throws DaoException
	 */
	public void updateProduct(ShStoreHomeProduct product) throws DaoException;
	
	/**
	 * 删除商家模块配置信息
	 * @param setting
	 * @throws DaoException
	 */
	public void removeModeleSetting(ShStoreHomeSetting setting) throws DaoException;
	
	/**
	 * 获得关联的商品列表
	 * @param hql
	 * @param pageSize
	 * @param page
	 * @return
	 */
	public List<ShProductInfo> getProductInfos(String hql,int pageSize,int page);

	
	/**
	 * 获得模块列表
	 * @param hql
	 * @param pageSize
	 * @param page
	 * @return
	 */
	public List<ShStoreHomeModule> getModules(String hql,int pageSize,int page);
	
	/**
	 * 删除模块
	 * @param module
	 */
	public void removeModule(ShStoreHomeModule module) throws DaoException;
	
	/**
	 * 分页查询分销商品
	 * 
	 * @param hql 查询语句
	 * @param pageSize 每页显示的条数
 	 * @param page 页数
	 * @return
	 * @throws DaoException
	 */
	public List<ShProductInfo> getShareProductForPage(String hql,int pageSize,int page) throws DaoException;
	
	/**
	 * 保存分销商品
	 * @param share
	 * @throws ServiceException
	 */
	public void saveShProductShare(ShProductShare share) throws DaoException;
	
	/**
	 * 修改分销商品
	 * @param share
	 * @throws DaoException
	 */
	public void updateShProductShare(ShProductShare share) throws DaoException;
	
	/**
	 * 根据商品Id获得分销商品
	 * @param productId
	 * @return
	 * @throws DaoException
	 */
	public ShProductShare getShareByProductId(String productId) throws DaoException;
	
	/**
	 * 获得分销商品列表
	 * @return
	 */
	public List<ShProductInfo> getShareProducts();
	
	
	/**
	 * 添加商家模块商品
	 * @param storeProduct 商家模块商品
	 * @throws DaoException
	 */
	public void saveStoreProduct(ShStoreHomeProduct storeProduct) throws DaoException;
	
	/**
	 * 根据模块类别获得模块列表
	 * @param typeId 模块类别
	 * @return
	 * @throws DaoException
	 */
	public List<ShStoreHomeModule> getModulesByTypeId(String typeId) throws DaoException;
	
	/**
	 * 根据模块Id获得商家模块配置信息
	 * @param moduleId 模块Id
	 * @return
	 * @throws DaoException
	 */
	public List<ShStoreHomeSetting> getSettingsByModuleId(String moduleId) throws DaoException;
	
	/**
	 * 根据商家模块Id获得商家商品
	 * @param storeModuleId 商家模块Id
	 * @return
	 * @throws DaoException
	 */
	public List<ShStoreHomeProduct> getStoreProductBySettingId(String storeModuleId) throws DaoException;
	
	/**
	 * 删除商家商品
	 * @throws DaoException
	 */
	public void removeStoreProduct(ShStoreHomeProduct storeProduct) throws DaoException;
	
}
