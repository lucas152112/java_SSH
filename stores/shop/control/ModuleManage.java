package com.oim.stores.shop.control;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.oim.stores.common.BaseAction;
import com.oim.stores.common.Content;
import com.oim.stores.common.JsonUtil;
import com.oim.stores.common.PubFun;
import com.oim.stores.common.Tool;
import com.oim.stores.exception.ServiceException;
import com.oim.stores.product.domain.ShProductInfo;
import com.oim.stores.product.service.ProductInfoService;
import com.oim.stores.product.service.ProductPriceService;
import com.oim.stores.product.service.ProductService;
import com.oim.stores.shop.domain.ShProductShare;
import com.oim.stores.shop.domain.ShStoreHomeModule;
import com.oim.stores.shop.domain.ShStoreHomeSetting;
import com.oim.stores.shop.service.ModuleService;
import com.oim.stores.store.domain.ShGroupTeam;
import com.oim.stores.store.domain.ShStoreUserRelation;
import com.oim.stores.store.domain.ShTeamStore;
import com.oim.stores.user.domain.SyUserInfo;
/**
 * 模块管理action;
 * @author Administrator
 *
 */
@SuppressWarnings("serial")
public class ModuleManage extends BaseAction {

	private ModuleService moduleService;//模块管理service
	private ProductInfoService productInfoService;
	private ShStoreHomeModule module; //模块
	private String moduleId; //模块Id

	private int page;//页
	private int rows;//行数
	
	/**
	 * @return the moduleService
	 */
	public ModuleService getModuleService() {
		return moduleService;
	}

	/**
	 * @param moduleService the moduleService to set
	 */
	public void setModuleService(ModuleService moduleService) {
		this.moduleService = moduleService;
	}
	/**
	 * to moudle page;
	 * @return
	 */
	@SuppressWarnings("static-access")
	public String toModule(){
		return this.SUCCESS;
	}
	
	/**
	 * 获得模块列表
	 */
	public void getModules(){
		if(page == 0) page = 1;
		List<ShStoreHomeModule> modules = moduleService.getModules(module, rows, page);
		long count = moduleService.getModulesCount(module);
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("total", count);
		JsonUtil.writeJson(modules, map);
	}
	
	/**
	 * 保存模块信息
	 */
	public void saveModule(){
		try {
			SyUserInfo loginUser = (SyUserInfo) PubFun.getSession("loginUser");
			module.setModuleState(new BigDecimal("1"));
			module.setUpdatedDate(new Date());
			module.setUpdatedUser(loginUser.getUserId().toString());
			moduleService.saveModule(module);
			PubFun.getResponseWriter().write("success");
		} catch (ServiceException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 修改模块
	 */
	public void updateModule(){
		try {
			SyUserInfo loginUser = (SyUserInfo) PubFun.getSession("loginUser");
			module.setModuleState(new BigDecimal("1"));
			module.setUpdatedDate(new Date());
			module.setUpdatedUser(loginUser.getUserId().toString());
			moduleService.updateModule(module);
			PubFun.getResponseWriter().write("success");
		} catch (ServiceException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 删除模块
	 */
	public void removeModule(){
		try {
			ShStoreHomeModule mod = moduleService.getModuleByModuleId(moduleId);
			moduleService.removeModule(mod);
			PubFun.getResponseWriter().write("success");
		} catch (ServiceException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 根据Id获得模块信息
	 */
	public void getModuleByModuleId(){
		try {
			ShStoreHomeModule mod = moduleService.getModuleByModuleId(moduleId);
			List<ShStoreHomeModule> list = new ArrayList<ShStoreHomeModule>();
			list.add(mod);
			JsonUtil.writeJson(list, null);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 验证模板是否已存在商家模块
	 */
	public void hasStoreModule(){
		try {
			List<ShStoreHomeSetting> settings = moduleService.getSettingsByModuleId(moduleId);
			if(settings != null && settings.size() > 0){
				PubFun.getResponseWriter().write("true");
			}else{
				PubFun.getResponseWriter().write("false");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 跳转到分销商品设置页面
	 * @return
	 */
	public String toShareProduct(){
		return SUCCESS;
	}
	
	/**
	 * 分页查询分销商品列表
	 */
	public void getShareProductForPage(){
		try {
			if(page == 0) page = 1;
			HttpServletRequest request = ServletActionContext.getRequest();
			String productType = request.getParameter("productType");
			Map<String, String> params = new HashMap<String, String>();
			params.put("productName", request.getParameter("productName"));
			params.put("productNo", request.getParameter("productNo"));
			params.put("startDate", request.getParameter("startDate"));
			params.put("endDate", request.getParameter("endDate"));
			SyUserInfo user=(SyUserInfo) session.getAttribute(Content.LOGIN_USER);
			List<ShGroupTeam> tlist=productInfoService.queryTeamById(user.getUserId());
//			if(tlist.size()>0&&"-1".equals(productType)){//聚类负责人
			if(tlist.size()>0){//聚类负责人
				String teamIds="";
				for (int i = 0; i < tlist.size(); i++) {
					teamIds+=tlist.get(i).getId().getSubTeamId()+",";
				}
				if(teamIds!=null&&!"".equals(teamIds)){
					teamIds=teamIds.substring(0,teamIds.length()-1);
				}
				//List<ShStoreUserRelation> relist=productInfoService.queryUserById(teamIds);
				List<ShTeamStore> relist=productInfoService.queryStoreByTeamId(teamIds);
				String storeIds="";
				if(relist.size()>0){
					for (int i = 0; i < relist.size(); i++) {
						storeIds+=relist.get(i).getId().getStoreId()+",";
					}
					storeIds=storeIds.substring(0,storeIds.length()-1);
					params.put("storeIds", storeIds);
				}else{//负责人下没有商家
				}
			}
			
			List<ShProductInfo> productList = moduleService.getShareProductForPage(params, productType, rows, page);
			List<ShProductInfo> list = null;
			if(productList != null){
				list = new ArrayList<ShProductInfo>();
				for (ShProductInfo shProductInfo : productList) {
					ShProductShare share = moduleService.getShareByProductId(shProductInfo.getProductId().toString());
					if(share != null) {
						shProductInfo.setShareProductStaus(share.getShareStaus());
						shProductInfo.setPromotionFormat(share.getPromotionFormat());
					}
					shProductInfo.setStoreName(shProductInfo.getStore().getStoreName());
					list.add(shProductInfo);
				}
			}
			
			long count = moduleService.getShareProductCount(params, productType);
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("total", count);
			JsonUtil.writeJson(list, map);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 跳转到商品列表页面
	 * @return
	 */
	public String toProductList() {
		return SUCCESS;
	}
	
	/**
	 * 添加到分销商品
	 */
	public void addShareProduct() {
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			String ids = request.getParameter("ids");
			if(Tool.isNotEmpty(ids)) {
				String[] productIds = ids.split(",");
				for (String id : productIds) {
					ShProductShare share = moduleService.getShareByProductId(id);
					if (share != null) {
						ShProductInfo productInfo = productInfoService.queryById(new Long(share.getProductId().toString()));
						PubFun.getResponseWriter().write("商品名为“" + productInfo.getProductName() 
								+ "”，编号为 “" + productInfo.getProductNo() + "”的商品已添加到分销商品中！请重新选择。");
						return;
					}
				}
				String promotionSel = request.getParameter("promotionSel");
				String promotion = request.getParameter("promotion");
				if (Tool.isNotEmpty(promotionSel) && Tool.isNotEmpty(promotion)) {
					moduleService.saveShProductShare(productIds,promotionSel,promotion);
					PubFun.getResponseWriter().write("success");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 修改分销商品状态
	 */
	public void updateShare(){
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			String productId = request.getParameter("productId");
			String shareStaus = request.getParameter("shareStaus");
			ShProductShare share = moduleService.getShareByProductId(productId);
			share.setShareStaus(new BigDecimal(shareStaus));
			moduleService.updateProductShare(share);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 根据商品Id获得分销商品
	 */
	public void getShareProductByProductId(){
		try {
			String productId = request.getParameter("productId");
			ShProductShare share = moduleService.getShareByProductId(productId);
			ShProductInfo productInfo = moduleService.getProductInfoById(productId);
			productInfo.setPromotionFee(share.getPromotionFee());
			productInfo.setPromotionPerc(share.getPromotionPerc());
			productInfo.setPromotionFormat(share.getPromotionFormat());
			List<ShProductInfo> list = new ArrayList<ShProductInfo>();
			list.add(productInfo);
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("productPrice", productInfo.getProductPrice().getProductPrice());
			JsonUtil.writeJson(list, map);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 修改推广费
	 */
	public void updatePromotion(){
		try {
			String promotionSel = request.getParameter("promotionSel");
			String promotion = request.getParameter("promotion");
			String productId = request.getParameter("productId");
			ShProductShare share = moduleService.getShareByProductId(productId);
			
			if (share != null && promotionSel != null && promotion != null && promotion.length() > 0) {
				if (promotionSel.equals("promotionPerc")) {
					share.setPromotionPerc(new Double(promotion));
					share.setPromotionFee(new Double(0));
				} else if (promotionSel.equals("promotionFee")) {
					share.setPromotionFee(new Double(promotion));
					share.setPromotionPerc(new Double(0));
				}
			}
			moduleService.updateProductShare(share);
			PubFun.getResponseWriter().write("success");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public ShStoreHomeModule getModule() {
		return module;
	}

	public void setModule(ShStoreHomeModule module) {
		this.module = module;
	}

	public String getModuleId() {
		return moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

	public ProductInfoService getProductInfoService() {
		return productInfoService;
	}

	public void setProductInfoService(ProductInfoService productInfoService) {
		this.productInfoService = productInfoService;
	}
	
}
