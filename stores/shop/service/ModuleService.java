package com.oim.stores.shop.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.oim.stores.exception.ServiceException;
import com.oim.stores.module.domain.StoreHomeSettingBean;
import com.oim.stores.product.domain.ShProductInfo;
import com.oim.stores.product.domain.ShProductPics;
import com.oim.stores.shop.domain.ShProductShare;
import com.oim.stores.shop.domain.ShStoreHomeModule;
import com.oim.stores.shop.domain.ShStoreHomeProduct;
import com.oim.stores.shop.domain.ShStoreHomeSetting;
import com.oim.stores.shop.domain.ShStoreSecurity;

/**
 * 模块管理service
 * 
 * @author Administrator
 * 
 */
public interface ModuleService {

	/**
	 * 根据商家Id获得商家模块列表
	 * 
	 * @param storeId
	 * @return
	 * @throws ServiceException
	 */
	public List<ShStoreHomeSetting> getModuleSettings(ShStoreHomeSetting setting, int pageSize, int page) throws ServiceException;

	/**
	 * 查询模块设置数量
	 * 
	 * @param setting
	 * @return
	 */
	public int getModuleSettingsCount(ShStoreHomeSetting setting);

	/**
	 * 根据Id获得模块
	 * 
	 * @param moduleId
	 * @return
	 * @throws ServiceException
	 */
	public ShStoreHomeModule getModuleByModuleId(String moduleId) throws ServiceException;
	/**
	 * 根据商家Id获得商家模块列表
	 * @param storeId 商家Id
	 * @return
	 * @throws ServiceException
	 */
	public List<ShStoreHomeSetting> getSettsByStoreIdAndShare(String storeId, String isShare) throws ServiceException;

	/**
	 * 获得商家控制的模块类别
	 * 
	 * @return
	 */
	public List<ShStoreHomeModule> getStoreModuleType();
	/**
	 * 根据商家模块Id获得模块商品列表
	 * @param moduleId 模块Id
	 * @return
	 * @throws ServiceException
	 */
	public List<ShStoreHomeProduct> getProductsByModuleId(ShStoreHomeProduct product,int pageSize,int page) throws ServiceException;

	/**
	 * 根据商家Id获得商家模块列表
	 * 
	 * @param storeId
	 * @return
	 * @throws ServiceException
	 */
	public List<ShStoreHomeSetting> getSettingsByStoreId(String storeId) throws ServiceException;
	/**
	 * 删除模块商品信息
	 * @param product 关联商品
	 * @throws ServiceException
	 */
	public void removeProduct(ShStoreHomeProduct product) throws ServiceException;
	
	
	/**
	 * 根据模板Id获得模块配置列表
	 * @param moduleId 模块Id
	 * @return
	 */
	public List<ShStoreHomeSetting> getSettingsByMSID(String moduleId,String storeId);

	/**
	 * 根据商家模块Id获得模块信息
	 * 
	 * @param storeModuleId
	 * @return
	 */
	public ShStoreHomeSetting getSettingById(String storeModuleId) throws ServiceException;
	/**
	 * 根据商家Id获得商家模块列表
	 * @param storeId 商家Id
	 * @return
	 * @throws ServiceException
	 */
	public List<ShStoreHomeSetting> getSettingsByStoreId(String storeId, String isShare) throws ServiceException;

	/**
	 * 根据商品Id获得关联的商品
	 * 
	 * @param productId
	 * @return
	 * @throws ServiceException
	 */
	public ShProductInfo getProductInfoById(String productId) throws ServiceException;
	/**
	 * 根据模块商品Id获得商品信息
	 * @param id 模块商品Id
	 * @return
	 * @throws ServiceException
	 */
	public ShStoreHomeProduct getProductById(String moduleProductId) throws ServiceException;
	/**
	 * 获得模块商品总条数
	 * @param product 模块商品 
	 * @return
	 */
	public long getProductCount(ShStoreHomeProduct product);
	/**
	 * 模块商品排序
	 * @param moduleProduct
	 * @param dir
	 * @throws ServiceException
	 */
	public void updateModuleProductSeq(ShStoreHomeProduct moduleProduct,String dir) throws ServiceException;	
	/**
	 * 根据商品Id获得商品主图
	 * @param productId 商品Id
	 * @return
	 * @throws ServiceException
	 */
	public ShProductPics getMainPicByProductId(String productId) throws ServiceException;
	/**
	 * 根据商家Id获得商家密钥信息
	 * @param storeId 商家Id
	 * @return
	 * @throws ServiceException
	 */
	public ShStoreSecurity getSecurityByStoreId(BigDecimal storeId) throws ServiceException;
	/**
	 * 添加或修改模块商品信息
	 * @param product 关联商品
	 * @throws ServiceException
	 */
	public void saveOrupdateProduct(ShStoreHomeProduct product) throws ServiceException;
	/**
	 * 获得最小排序号
	 * @param storeModuleId
	 * @return
	 * @throws ServiceException
	 */
	public int getMinProductOrderNum(String storeModuleId) throws ServiceException;

	/**
	 * 添加商家模块
	 * 
	 * @param module
	 * @param setting
	 * @throws ServiceException
	 */
	public void saveModuleSetting(ShStoreHomeSetting setting) throws ServiceException;

	/**
	 * 修改模块信息
	 * 
	 * @param setting
	 * @throws ServiceException
	 */
	public void updateModule(ShStoreHomeSetting setting) throws ServiceException;

	/**
	 * 删除商家模块配置信息
	 * 
	 * @param setting
	 * @throws ServiceException
	 */
	public void removeModuleSetting(ShStoreHomeSetting setting) throws ServiceException;

	/**
	 * 获得关联的商品列表
	 * 
	 * @param productInfo
	 * @param pageSize
	 * @param page
	 * @return
	 */
	public List<ShProductInfo> getProductInfos(ShProductInfo productInfo, int pageSize, int page);

	/**
	 * 获取商品数量
	 * 
	 * @param productInfo
	 * @return
	 */
	public long getProductInfosCount(ShProductInfo productInfo);

	/**
	 * 获得模块列表
	 * 
	 * @param module
	 * @param pageSize
	 * @param page
	 * @return
	 */
	public List<ShStoreHomeModule> getModules(ShStoreHomeModule module, int pageSize, int page);

	/**
	 * 获取模块数量
	 * 
	 * @param module
	 * @return
	 */
	public long getModulesCount(ShStoreHomeModule module);

	/**
	 * 保存模块信息
	 * 
	 * @param module
	 */
	public void saveModule(ShStoreHomeModule module) throws ServiceException;

	/**
	 * 修改模块信息
	 * 
	 * @param module
	 */
	public void updateModule(ShStoreHomeModule module) throws ServiceException;

	/**
	 * 删除模块
	 * 
	 * @param module
	 * @throws ServiceException
	 */
	public void removeModule(ShStoreHomeModule module) throws ServiceException;

	/**
	 * 根据模板Id获得商家模块
	 * 
	 * @param moduleId
	 * @return
	 * @throws ServiceException
	 */
	public List<ShStoreHomeSetting> getSettingsByModuleId(String moduleId) throws ServiceException;

	/**
	 * 分页查询分销商品
	 * 
	 * @param parameters
	 *            查询条件
	 * @param pageSize
	 *            每页显示的条数
	 * @param page
	 *            页数
	 * @return
	 */
	public List<ShProductInfo> getShareProductForPage(Map<String, String> parameters, String productType, int pageSize, int page) throws ServiceException;

	/**
	 * 获得分销商品总记录数
	 * 
	 * @param parameters
	 *            查询条件
	 * @return
	 */
	public long getShareProductCount(Map<String, String> parameters, String productType);

	/**
	 * 保存分销商品
	 * 
	 * @param share
	 *            分销商品
	 * @throws DaoException
	 */
	public void saveShProductShare(String[] productIds, String promotionSel, String promotion) throws ServiceException;

	/**
	 * 修改分销商品
	 * 
	 * @param share
	 * @throws ServiceException
	 */
	public void updateProductShare(ShProductShare share) throws ServiceException;

	/**
	 * 根据商品Id获得分销商品信息
	 * 
	 * @return
	 * @throws ServiceException
	 */
	public ShProductShare getShareByProductId(String productId) throws ServiceException;

	/**
	 * 根据类别Id获得模块
	 * 
	 * @param typeId
	 *            类别Id
	 * @return
	 * @throws ServiceException
	 */
	public List<ShStoreHomeModule> getMOduleByType(String typeId) throws ServiceException;

	/**
	 * 获得可分销商品列表
	 * 
	 * @return
	 * @throws ServiceException
	 */
	public List<ShProductInfo> getShareProducts() throws ServiceException;

	/**
	 * 根据商家Id获得商家秘钥信息
	 * 
	 * @param storeId
	 *            商家Id
	 * @return
	 * @throws ServiceException
	 */
	public ShStoreSecurity getSecurityByStoreId(String storeId) throws ServiceException;

	/**
	 * 添加商家模块商品
	 * 
	 * @param storeProduct
	 * @throws ServiceException
	 */
	public void saveStoreProduct(ShStoreHomeProduct storeProduct) throws ServiceException;

	/**
	 * 查类别ID组获得模块
	 */
	public List<ShStoreHomeModule> getMOduleByTypeIds(String[] typeIds) throws ServiceException;
	/**
	 * 根据商家模块Id获得模块商品
	 * @param storeModuleId 商家模块Id
	 * @return
	 * @throws ServiceException
	 */
	public List<ShStoreHomeProduct> getProductsByStoreModuleId(String storeModuleId) throws ServiceException;
}
