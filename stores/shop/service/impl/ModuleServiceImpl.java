package com.oim.stores.shop.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringEscapeUtils;

import com.oim.stores.common.Content;
import com.oim.stores.common.PubFun;
import com.oim.stores.common.Tool;
import com.oim.stores.exception.DaoException;
import com.oim.stores.exception.ServiceException;
import com.oim.stores.module.domain.StoreHomeSettingBean;
import com.oim.stores.product.dao.ProductDao;
import com.oim.stores.product.dao.ProductInfoDao;
import com.oim.stores.product.domain.ShProductInfo;
import com.oim.stores.product.domain.ShProductPics;
import com.oim.stores.shop.dao.ModuleDao;
import com.oim.stores.shop.domain.ShProductShare;
import com.oim.stores.shop.domain.ShStoreHomeModule;
import com.oim.stores.shop.domain.ShStoreHomeProduct;
import com.oim.stores.shop.domain.ShStoreHomeSetting;
import com.oim.stores.shop.domain.ShStoreSecurity;
import com.oim.stores.shop.service.ModuleService;
import com.oim.stores.store.dao.StoreInfoDao;
import com.oim.stores.store.domain.ShStoreInfo;
import com.oim.stores.user.domain.SyUserInfo;

/**
 * 模块管理service实现类
 */
public class ModuleServiceImpl implements ModuleService {

	private ModuleDao moduleDao;// 模块管理dao
	private ProductInfoDao productInfoDao; // 商品管理dao

	public ModuleDao getModuleDao() {
		return moduleDao;
	}

	public void setModuleDao(ModuleDao moduleDao) {
		this.moduleDao = moduleDao;
	}

	public ProductInfoDao getProductInfoDao() {
		return productInfoDao;
	}

	public void setProductInfoDao(ProductInfoDao productInfoDao) {
		this.productInfoDao = productInfoDao;
	}

	/**
	 * 根据商家Id获得模块列表
	 */
	@Override
	public List<ShStoreHomeSetting> getModuleSettings(ShStoreHomeSetting setting, int pageSize, int page) throws ServiceException {
		List<ShStoreHomeSetting> list = null;
		try {
			String hql = "from ShStoreHomeSetting " + getWhereHql(setting);
			list = moduleDao.getSettings(hql, pageSize, page);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public int getModuleSettingsCount(ShStoreHomeSetting setting) {
		int count = 0;
		String hql = "select count(*) from ShStoreHomeSetting " + getWhereHql(setting);
		List<Long> list = (List<Long>) moduleDao.getListByHql(hql);
		if (list != null && list.size() > 0) {
			count = list.get(0).intValue();
		}
		return count;
	}

	private String getWhereHql(ShStoreHomeSetting setting) {
		StringBuilder builder = new StringBuilder(" where storeId=" + setting.getStoreId().toString());
		if (setting != null) {
			if (setting.getModuleDesc() != null && setting.getModuleDesc().length() > 0) {
				builder.append(" and moduleDesc like '%" + setting.getModuleDesc() + "%'");
			}
			if (setting.getIsShow() != null && !setting.getIsShow().toString().equals(Content.MODULE_IS_SHOW_ALL)) {
				builder.append(" and isShow =" + setting.getIsShow().toString());
			}
		}
		return builder.toString();
	}

	/**
	 * 根据Id获得模块类别
	 */
	public ShStoreHomeModule getModuleByModuleId(String moduleId) throws ServiceException {
		try {
			return moduleDao.getmodule(moduleId);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * 获得商家控制的模块类别
	 */
	@SuppressWarnings("unchecked")
	public List<ShStoreHomeModule> getStoreModuleType() {
		String hql = "from ShStoreHomeModule where storeAuthority=" + Content.MODULE_STORE_AUTHORITY;
		List<ShStoreHomeModule> modules = (List<ShStoreHomeModule>) moduleDao.getListByHql(hql);
		return modules;
	}

	/**
	 * 根据商家Id获得商家模块列表
	 */
	@SuppressWarnings("unchecked")
	public List<ShStoreHomeSetting> getSettingsByStoreId(String storeId) throws ServiceException {
		String hql = "from ShStoreHomeSetting where storeId=" + storeId;
		return (List<ShStoreHomeSetting>) moduleDao.getListByHql(hql);
	}

	/**
	 * 根据商家模块Id获得模块信息
	 */
	@SuppressWarnings("unchecked")
	public ShStoreHomeSetting getSettingById(String storeModuleId) throws ServiceException {
		String hql = "from ShStoreHomeSetting where storeModuleId=" + storeModuleId;
		List<ShStoreHomeSetting> list = (List<ShStoreHomeSetting>) moduleDao.getListByHql(hql);
		if (list != null && list.size() > 0)
			return list.get(0);
		return null;
	}

	/**
	 * 根据商品Id获得关联的商品
	 */
	@SuppressWarnings("unchecked")
	public ShProductInfo getProductInfoById(String productId) throws ServiceException {
		String hql = "from ShProductInfo where productId =" + productId;
		List<ShProductInfo> list = (List<ShProductInfo>) moduleDao.getListByHql(hql);
		if (list != null && list.size() > 0)
			return list.get(0);
		return null;
	}

	/**
	 * 添加商家模块
	 */
	@Override
	public void saveModuleSetting(ShStoreHomeSetting setting) throws ServiceException {
		try {
			moduleDao.saveModuleSetting(setting);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * 修改模块信息
	 */
	@Override
	public void updateModule(ShStoreHomeSetting setting) throws ServiceException {
		try {
			moduleDao.updateModuleSetting(setting);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * 删除商家模块配置信息
	 */
	@Override
	public void removeModuleSetting(ShStoreHomeSetting setting) throws ServiceException {
		try {
			moduleDao.removeModeleSetting(setting);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * 获得关联的商品列表
	 */
	@Override
	public List<ShProductInfo> getProductInfos(ShProductInfo productInfo, int pageSize, int page) {
		String hql = "from ShProductInfo s " + getProductInfoHql(productInfo);
		return moduleDao.getProductInfos(hql, pageSize, page);
	}

	private String getProductInfoHql(ShProductInfo productInfo) {
		StringBuilder builder = new StringBuilder(" where 1=1 and s.productStatus=1 and s.storeId=" + productInfo.getStoreId().toString() + " and s.isAudit=1 and s.parentId = 0");
		builder.append(" and s.productType != 1008");
		
		if (productInfo != null) {
			if (productInfo.getProductId() != null) {
				builder.append(" and s.productId like '%" + productInfo.getProductId() + "%'");
			}
			if (productInfo.getProductName() != null && productInfo.getProductName().length() > 0) {
				builder.append(" and s.productName like '%" + productInfo.getProductName() + "%'");
			}
			/*
			 * if(productInfo.getStartDate() != null &&
			 * productInfo.getStartDate().length() > 0){
			 * builder.append(" and activeDate >= to_date('" +
			 * productInfo.getStartDate() + "','yyyy-mm-dd hh24:mi:ss')"); }
			 * if(productInfo.getEndDate() != null &&
			 * productInfo.getEndDate().length() > 0){
			 * builder.append(" and activeDate <= to_date('" +
			 * productInfo.getEndDate() + "','yyyy-mm-dd hh24:mi:ss')"); }
			 */
			if (productInfo.getProductNo() != null && productInfo.getProductNo().length() > 0) {
				builder.append(" and s.productNo like '%" + productInfo.getProductNo() + "%'");
			}
		}
		return builder.toString();
	}

	@SuppressWarnings("unchecked")
	@Override
	public long getProductInfosCount(ShProductInfo productInfo) {
		String hql = "select count(*) from ShProductInfo s " + getProductInfoHql(productInfo);
		List<Long> list = (List<Long>) moduleDao.getListByHql(hql);
		if (list != null && list.size() > 0)
			return list.get(0);
		return 0;
	}

	/**
	 * 获得模块列表
	 */
	@Override
	public List<ShStoreHomeModule> getModules(ShStoreHomeModule module, int pageSize, int page) {
		String hql = "from ShStoreHomeModule s " + getModuleHql(module);
		return moduleDao.getModules(hql, pageSize, page);
	}

	private String getModuleHql(ShStoreHomeModule module) {
		StringBuilder builder = new StringBuilder(" where 1=1");
		if (module != null) {
			if (module.getModuleName() != null && module.getModuleName().length() > 0) {
				builder.append(" and moduleName like '%" + module.getModuleName().trim() + "%'");
			}
			if (module.getIsShow() != null && !module.getIsShow().toString().equals(Content.MODULE_IS_SHOW_ALL)) {
				builder.append(" and isShow =" + module.getIsShow().toString().trim());
			}
		}

		return builder.toString();
	}

	@SuppressWarnings("unchecked")
	@Override
	public long getModulesCount(ShStoreHomeModule module) {
		String hql = "select count(*) from ShStoreHomeModule" + getModuleHql(module);
		List<Long> list = (List<Long>) moduleDao.getListByHql(hql);
		if (list != null && list.size() > 0)
			return list.get(0);
		return 0;
	}

	/**
	 * 保存模块信息
	 */
	@Override
	public void saveModule(ShStoreHomeModule module) throws ServiceException {
		try {
			moduleDao.saveModule(module);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * 修改模块信息
	 */
	@Override
	public void updateModule(ShStoreHomeModule module) throws ServiceException {
		try {
			moduleDao.updateModule(module);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * 删除模块
	 */
	@Override
	public void removeModule(ShStoreHomeModule module) throws ServiceException {
		try {
			moduleDao.removeModule(module);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * 根据模板Id获得商家模块
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<ShStoreHomeSetting> getSettingsByModuleId(String moduleId) throws ServiceException {
		String hql = "from ShStoreHomeSetting where moduleId=" + moduleId;
		return (List<ShStoreHomeSetting>) moduleDao.getListByHql(hql);
	}

	/**
	 * 分页查询分销商品
	 */
	@Override
	public List<ShProductInfo> getShareProductForPage(Map<String, String> parameters, String productType, int pageSize, int page) throws ServiceException {

		try {
			String hql = getShareProductHql(parameters, productType);
			List<ShProductInfo> list = moduleDao.getShareProductForPage(hql, pageSize, page);
			return list;
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * 获得分销商品总记录数
	 */
	@SuppressWarnings("unchecked")
	@Override
	public long getShareProductCount(Map<String, String> parameters, String productType) {
		String hql = "select count(*) " + getShareProductHql(parameters, productType);
		List<Long> list = (List<Long>) moduleDao.getListByHql(hql);
		if (list != null && list.size() > 0)
			return list.get(0);
		return 0;
	}

	// 获得分销商品查询hql
	private String getShareProductHql(Map<String, String> params, String productType) {
		StringBuilder hql = new StringBuilder("from ShProductInfo s where 1=1");
		hql.append(" and s.productStatus=1 and s.isAudit=1 and s.parentId = 0 and s.productType != 1008");
		
		if (Tool.isNotEmpty(productType) && productType.equals(Content.MODULE_TYPE_SHARE_PRODUCT)) {
			hql.append(" and exists (select sps.productId from ShProductShare sps where sps.productId=s.productId" + ")");
		} else {
			hql.append(" and s.productId not in (select sps.productId from ShProductShare sps)");
		}

		Set<String> keys = params.keySet();
		for (String key : keys) {
			if (Tool.isNotEmpty(params.get(key))) {
				if ("productName".equals(key)) {
					String productName = StringEscapeUtils.escapeSql(params.get(key).trim());
					hql.append(" and lower(s.productName) like lower('%" + productName + "%')");
				} else if ("productNo".equals(key)) {
					hql.append(" and s.productNo like '%" + params.get(key).trim() + "%'");
				} else if ("startDate".equals(key)) {
					hql.append(" and s.activeDate >= to_date('" + params.get(key) + "','yyyy-mm-dd hh24:mi:ss')");
				} else if ("endDate".equals(key)) {
					hql.append(" and s.activeDate <= to_date('" + params.get(key) + "','yyyy-mm-dd hh24:mi:ss')");
				}else if("storeIds".equals(key)){
					if(Tool.isNotEmpty(params.get(key))){
						hql.append(" AND s.storeId in ("+params.get(key).trim()+")");
					}
				}
			}
		}
		return hql.toString();
	}

	/**
	 * 保存分销商品
	 */
	@Override
	public void saveShProductShare(String[] productIds, String promotionSel, String promotion) throws ServiceException {
		try {
			if (productIds != null) {
				for (String id : productIds) {
					ShProductShare share = new ShProductShare();
					share.setProductId(new BigDecimal(id));
					share.setShareStaus(new BigDecimal("1"));
					if (promotionSel.equals("promotionPerc")) {
						share.setPromotionPerc(new Double(promotion));
					} else if (promotionSel.equals("promotionFee")) {
						share.setPromotionFee(new Double(promotion));
					}
					moduleDao.saveShProductShare(share);
				}
			}
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * 修改分销商品
	 */
	@Override
	public void updateProductShare(ShProductShare share) throws ServiceException {
		try {
			moduleDao.updateShProductShare(share);

			/*
			 * // 获得分销商品模版 List<ShStoreHomeModule> moduleList = moduleDao
			 * .getModulesByTypeId(Content.MODULE_TYPE_SHARE_PRODUCT); if
			 * (moduleList != null) { for (int i = 0; i < moduleList.size();
			 * i++) { // 获得商家分销商品模块 List<ShStoreHomeSetting> settings =
			 * moduleDao .getSettingsByModuleId(moduleList.get(i)
			 * .getModuleId().toString()); // 删除或添加商家商品
			 * removeOrSaveStoreProduct(settings, share.getShareStaus()
			 * .toString(), share.getProductId()); } }
			 */
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/*
	 * private void removeOrSaveStoreProduct(List<ShStoreHomeSetting> settings,
	 * String shareStatus, BigDecimal productId) throws DaoException,
	 * ServiceException { SyUserInfo userinfo = (SyUserInfo) PubFun
	 * .getSession(Content.LOGIN_USER);
	 * 
	 * if (settings != null) { for (int i = 0; i < settings.size(); i++) {
	 * ShStoreHomeSetting setting = settings.get(i); /* 当分销商品的状态为删除时，删除分销商 商品模块
	 * 中对应的商品，当为可用时，则增加 if (shareStatus.equals("2")) { // 删除
	 * List<ShStoreHomeProduct> storeProducts = moduleDao
	 * .getStoreProductBySettingId(setting .getStoreModuleId().toString()); for
	 * (int j = 0; j < storeProducts.size(); j++) { if
	 * (productId.toString().equals(
	 * storeProducts.get(j).getProductId().toString())) {
	 * moduleDao.removeStoreProduct(storeProducts.get(j)); } } } else if
	 * (shareStatus.equals("1")) { // 设为可用，则添加
	 * 
	 * /* 添加商家商品 ShProductInfo productInfo = productInfoDao .queryById(new
	 * Long(productId.toString())); ShStoreHomeProduct p = new
	 * ShStoreHomeProduct(); List<ShProductPics> pics =
	 * productInfo.getProductPics(); if (pics != null) { for (int j = 0; j <
	 * pics.size(); j++) { if (pics.get(j).getIsMain().toString().equals("1")) {
	 * p.setBigPic(pics.get(j).getBigPic());
	 * p.setSmallPic(pics.get(j).getSmallPic()); } } } p.setIsShow(new
	 * BigDecimal(1)); p.setUpdatedDate(new Date());
	 * p.setUpdatedUser(userinfo.getUserId().toString()); p.setProductId(new
	 * BigDecimal(productInfo.getProductId())); if (p.getToUrl() == null ||
	 * p.getToUrl().length() == 0) { String pId = p.getProductId().toString();
	 * String url = Content.PRODUCT_DETAIL_URL.replace( "productId", pId);
	 * ShStoreSecurity security = getSecurityByStoreId(setting
	 * .getStoreId().toString()); if (security != null) { url =
	 * url.replace("storeKey", security.getStoreKey()); }
	 * 
	 * p.setToUrl(url); } p.setTitleDesc(productInfo.getProductName());
	 * p.setProductOrder(new BigDecimal(1)); p.setStoreModuleId(new
	 * BigDecimal(setting .getStoreModuleId())); moduleDao.saveStoreProduct(p);
	 * } } } }
	 */

	/**
	 * 根据商品Id获得分销商品信息
	 */
	@Override
	public ShProductShare getShareByProductId(String productId) throws ServiceException {
		try {
			return moduleDao.getShareByProductId(productId);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * 根据类别Id获得模块
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<ShStoreHomeModule> getMOduleByType(String typeId) throws ServiceException {
		String hql = "from ShStoreHomeModule s where s.typeId=" + typeId;
		return (List<ShStoreHomeModule>) moduleDao.getListByHql(hql);
	}

	/**
	 * 查类别ID组获得模块
	 */
	@Override
	public List<ShStoreHomeModule> getMOduleByTypeIds(String[] typeIds) throws ServiceException {
		String ids = "";
		for (String tid : typeIds) {
			ids += tid + ",";
		}
		ids = ids.substring(0, ids.length() - 1);
		String hql = "from ShStoreHomeModule s where s.typeId in (" + ids + ")";
		return (List<ShStoreHomeModule>) moduleDao.getListByHql(hql);
	}

	/**
	 * 获得可分销商品列表
	 */
	@Override
	public List<ShProductInfo> getShareProducts() throws ServiceException {
		return moduleDao.getShareProducts();
	}

	/**
	 * 根据商家Id获得商家秘钥信息
	 */
	@SuppressWarnings("unchecked")
	@Override
	public ShStoreSecurity getSecurityByStoreId(String storeId) throws ServiceException {
		String hql = "from ShStoreSecurity where storeId=" + storeId;
		List<ShStoreSecurity> list = (List<ShStoreSecurity>) moduleDao.getListByHql(hql);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	/**
	 * 添加商家模块商品
	 */
	@Override
	public void saveStoreProduct(ShStoreHomeProduct storeProduct) throws ServiceException {
		try {
			moduleDao.saveStoreProduct(storeProduct);
		} catch (Exception e) {
			throw new ServiceException();
		}
	}

	@Override
	public List<ShStoreHomeSetting> getSettingsByMSID(String moduleId,
			String storeId) {
		String hql = "from ShStoreHomeSetting where moduleId=" + moduleId+" and storeId="+storeId;
		return (List<ShStoreHomeSetting>) moduleDao.getListByHql(hql);
	}

	@Override
	public List<ShStoreHomeProduct> getProductsByStoreModuleId(
			String storeModuleId) throws ServiceException {
		String hql = "from ShStoreHomeProduct where storeModuleId="+storeModuleId;
		return (List<ShStoreHomeProduct>)moduleDao.getListByHql(hql);
	}

	@Override
	public List<ShStoreHomeSetting> getSettsByStoreIdAndShare(String storeId,
			String isShare) throws ServiceException {
		String hql = "from ShStoreHomeSetting s where s.storeId="+storeId;
		if (isShare != null && isShare.equals("1")) {
			hql += " and exists(select m.moduleId from ShStoreHomeModule m where m.moduleId=s.moduleId and m.typeId= " + Content.MODULE_TYPE_SHARE_PRODUCT + ")";
		}
		return (List<ShStoreHomeSetting>)moduleDao.getListByHql(hql);
	}

	@Override
	public ShStoreHomeProduct getProductById(String moduleProductId)
			throws ServiceException {
		ShStoreHomeProduct product = null;
		try {
			product = moduleDao.getProductById(moduleProductId);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		return product;
	}

	@Override
	public ShStoreSecurity getSecurityByStoreId(BigDecimal storeId)
			throws ServiceException {
		String hql = "from ShStoreSecurity where storeId="+storeId;
		List<ShStoreSecurity> list = (List<ShStoreSecurity>)moduleDao.getListByHql(hql);
		if(list != null && list.size() > 0)
			return list.get(0);
		return null;
	}

	@Override
	public int getMinProductOrderNum(String storeModuleId)
			throws ServiceException {
		String hql = "select min(s.productOrder) from ShStoreHomeProduct s where s.storeModuleId=" + storeModuleId;
		List<BigDecimal> list  = (List<BigDecimal>)moduleDao.getListByHql(hql);
		if (list != null && list.size() > 0) {
			if (list.get(0) != null)
			 return new Integer(list.get(0).toString());
		}
		return 0;
	}

	@Override
	public void saveOrupdateProduct(ShStoreHomeProduct product)
			throws ServiceException {
		try {
			if(product.getIsCreate() != null && product.getIsCreate().equals("false")){
				moduleDao.updateProduct(product);
			}else{
				moduleDao.saveProduct(product);
			}
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<ShStoreHomeSetting> getSettingsByStoreId(String storeId,
			String isShare) throws ServiceException {
		String hql = "from ShStoreHomeSetting s where s.storeId="+storeId;
		if (isShare != null && isShare.equals("1")) {
			hql += " and exists(select m.moduleId from ShStoreHomeModule m where m.moduleId=s.moduleId and m.typeId= " + Content.MODULE_TYPE_SHARE_PRODUCT + ")";
		}
		return (List<ShStoreHomeSetting>)moduleDao.getListByHql(hql);
	}

	@Override
	public List<ShStoreHomeProduct> getProductsByModuleId(
			ShStoreHomeProduct product, int pageSize, int page)
			throws ServiceException {
		List<ShStoreHomeProduct> products = null;
		String hql = "from ShStoreHomeProduct s " + getWhereHql(product);
		try {
			products = moduleDao.getProductsByModuleId(hql,pageSize,page);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		return products;
	}
	
	/**
	 * 获得模块商品查询hql
	 * @param product 商家模块商品信息
	 * @return
	 */
	private String getWhereHql(ShStoreHomeProduct product){
		StringBuilder builder = new StringBuilder(" where 1=1");
		//builder.append(" and exists(select spi.productId from ShProductInfo spi where spi.productType != 1008 and spi.productId = s.productId)");
		builder.append(" and (exists(select spi.productId from ShProductInfo spi where spi.productType != 1008 and spi.productId = s.productId)");
		builder.append(" or s.productId is null)");
		
		if(product != null){
			if(product.getTitleDesc() != null && product.getTitleDesc().length() > 0){
				String titleDesc = StringEscapeUtils.escapeSql(product.getTitleDesc()).trim();
				builder.append(" and lower(s.titleDesc) like lower('%" + titleDesc + "%')");
			}
			if(product.getIsShow() != null && !product.getIsShow().toString().equals(Content.MODULE_IS_SHOW_ALL)){
				builder.append(" and s.isShow =" + product.getIsShow().toString().trim());
			}
			if(product.getUpdateUserFormat()!=null&&!"".equals(product.getUpdateUserFormat())){
				if(product.getStoreModuleId() != null && !product.getStoreModuleId().toString().equals("0")){
					builder.append(" and (s.storeModuleId=" + product.getStoreModuleId().toString().trim());
					builder.append(" or s.updatedUser=" + product.getUpdateUserFormat().trim()+" )");
				}else{
					builder.append(" and s.updatedUser=" + product.getUpdateUserFormat().trim());
				}
			}
			if(product.getStoreModuleId() != null && !product.getStoreModuleId().toString().equals("0")){
				builder.append(" and s.storeModuleId=" + product.getStoreModuleId().toString().trim());
			}
			if(product.getStoreModuleIds() != null && product.getStoreModuleIds().size() > 0){
				String storeModuleIdStr = "";
				for(int i=0;i<product.getStoreModuleIds().size();i++){
					if(i == 0){
						storeModuleIdStr += product.getStoreModuleIds().get(0).trim();
					}else{
						storeModuleIdStr += ","+product.getStoreModuleIds().get(i).trim();
					}
				}
				builder.append(" and s.storeModuleId in("+storeModuleIdStr+")");
			} else {
				builder.append(" and s.storeModuleId in()");
			}
		}
		builder.append(" order by s.productOrder");
		
		return builder.toString();
	}

	@Override
	public ShProductPics getMainPicByProductId(String productId)
			throws ServiceException {
		String hql = "from ShProductPics where productId="+productId;
		List<ShProductPics> pics = (List<ShProductPics>)moduleDao.getListByHql(hql);
		if(pics != null){
			for(int i=0;i<pics.size();i++){
				if(pics.get(i).getIsMain().toString().equals("1")){
					return pics.get(i);
				}
			}
		}
		return null;
	}

	@Override
	public void removeProduct(ShStoreHomeProduct product)
			throws ServiceException {
		try {
			moduleDao.removeProduct(product);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void updateModuleProductSeq(ShStoreHomeProduct moduleProduct,
			String dir) throws ServiceException {
		try { 
			int currSeq = Integer.parseInt(moduleProduct.getProductOrder().toString());
			
			int dirSeq = currSeq;
			
			List<ShStoreHomeProduct> list = (List<ShStoreHomeProduct>)moduleDao.getListByHql("from ShStoreHomeProduct s where s.storeModuleId=" + moduleProduct.getStoreModuleId() 
					+ " order by productOrder asc");
			
			if (list != null) {
				int index = 0;
				for (int i = 0; i < list.size(); i++) {
					ShStoreHomeProduct homeProduct = list.get(i);
					if (homeProduct.getModuleProductId().toString().equals(moduleProduct.getModuleProductId().toString())) {
						index = i;
					}
				}
				//向上移
				if (dir.equals("pre") && index > 0) {
					ShStoreHomeProduct preProduct = list.get(index-1);
					dirSeq = Integer.parseInt(preProduct.getProductOrder().toString());
					if (dirSeq == currSeq) {
						dirSeq--;
					}
					moduleProduct.setProductOrder(new BigDecimal(dirSeq));
					preProduct.setProductOrder(new BigDecimal(currSeq));
					moduleDao.updateProduct(moduleProduct);
					moduleDao.updateProduct(preProduct);
				}  else if (dir.equals("next") && index < (list.size() -1)) { //下移
					ShStoreHomeProduct nextProduct = list.get(index+1);
					dirSeq = Integer.parseInt(nextProduct.getProductOrder().toString());
					if (dirSeq == currSeq) {
						dirSeq++;
					}
					moduleProduct.setProductOrder(new BigDecimal(dirSeq));
					nextProduct.setProductOrder(new BigDecimal(currSeq));
					moduleDao.updateProduct(moduleProduct);
					moduleDao.updateProduct(nextProduct);
				}
			}
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public long getProductCount(ShStoreHomeProduct product) {
		long count = 0;
		String hql = "select count(*) from ShStoreHomeProduct s " + getWhereHql(product);
		List<Long> list = (List<Long>)moduleDao.getListByHql(hql);
		if(list != null && list.size() > 0){
			count = list.get(0);
		}
		return count;
	}

}
