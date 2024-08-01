package com.oim.stores.store.control;



import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.oim.stores.datadict.service.ProductTypeService;
import com.oim.stores.exception.ServiceException;

import com.oim.stores.store.domain.ShStoreAccount;
import com.oim.stores.store.domain.ShStoreCluster;
import com.oim.stores.store.domain.ShStoreInfo;
import com.oim.stores.store.domain.ViewStoreAccount;
import com.oim.stores.store.domain.ViewStoreCluster;


import com.oim.stores.store.service.StoreAccountDetailService;
import com.oim.stores.store.service.StoreAccountService;
import com.oim.stores.store.service.StoreInfoService;
import com.oim.stores.store.service.StoreUserRelationService;
import com.oim.stores.system.service.RoleManageService;
import com.oim.stores.user.service.SyUserInfoService;
/**
 * 商家管理dwr
 * @author Administrator
 *
 */
public class StoreDwrControl {
	/**
	 * 产品类型service
	 */
	private ProductTypeService productTypeService;
	private StoreAccountService storeAccountService;//商家accountservice
	private StoreAccountDetailService storeAccountDetailService;//商家详细service
	private StoreInfoService storeInfoService;//商家service
	private StoreUserRelationService storeUserRelationService;//商家用户关系service
	private SyUserInfoService syUserInfoService;//系统用户service
	private RoleManageService roleMngService;//角色管理service
	/**
	 * query store for modify;
	 * @param storeId
	 * @return
	 */
	public ViewStoreCluster queryStoreById(Long storeId){
		try {
			if (storeId!=null) {
				ViewStoreCluster acc=storeInfoService.queryStoreById(storeId);
				return acc;
			}
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String updateStoreCluster(ShStoreInfo info,ShStoreCluster cluster){
		if(info!=null){
			info.setUpdateDate(new Date());
			if (info.getRegionalId() != null && info.getRegionalId().toString().equals("590")) {
				info.setRegionalId(null);
			}
			try {
				List<ShStoreInfo> sis = storeInfoService.queryByStoreName(info.getStoreName());
				for (ShStoreInfo si : sis) {
					if ((null == info.getStoreId() || si.getStoreId().longValue() != info.getStoreId().longValue()) && si.getStoreName().equals(info.getStoreName())) {
						return "3";
					}
				}
				
				ShStoreInfo bean=storeInfoService.queryById(info.getStoreId());
				bean.setStoreName(info.getStoreName());
				bean.setStorePic(info.getStorePic());
				bean.setTel(info.getTel());
				bean.setBusinessType(info.getBusinessType());
				bean.setRegionalId(info.getRegionalId());
				bean.setProductSource(info.getProductSource());
				bean.setStoreShortDesc(info.getStoreShortDesc());
				bean.setIsEntity(info.getIsEntity());
				bean.setIsWareshop(info.getIsWareshop());
				bean.setFactorId(info.getFactorId());
				bean.setCrmId(info.getCrmId());
				bean.setStoreDesc(info.getStoreDesc());
				bean.setCserviceTel(info.getCserviceTel());
				bean.setMsgNumber(info.getMsgNumber());
				bean.setMsgType(info.getMsgType());
				bean.setOpenTime(info.getOpenTime());
				bean.setStoreMode(info.getStoreMode());
				bean.setStoreType(info.getStoreType());
				
				storeInfoService.update(bean);
				//storeInfoService.update(info);
				
				if (cluster != null) {
					if ((cluster.getBankAccount() != null && cluster.getBankAccount().length() > 0) || (cluster.getRegAccount() != null && cluster.getRegAccount().length() > 0) || (cluster.getClusterName() != null && cluster.getClusterName().length() > 0)) {
						cluster.setStoreId(new Double(info.getStoreId().toString()));
						storeInfoService.saveCluster(cluster);
					}
				}
				return "1";
			} catch (ServiceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return "0";
	}
	/**
	 * query account view;
	 * @param accId
	 * @return
	 */
	public ViewStoreAccount queryAccById(BigDecimal accId){
		try {
			if (accId!=null) {
				ViewStoreAccount acc=storeAccountService.queryAccViewById(accId);
				return acc;
			}
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * according to the type query;
	 * @param accId
	 * @return
	 */
	public boolean queryAccByType(){
		try {
				Map<String,String> params=new HashMap<String,String>();
				params.put("accountType", "11");
				List<ViewStoreAccount> list=storeAccountService.queryAccByParam(params);
				if(list.size()>0){
					return true;
				}
				return false;
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return false;
	}
	/**
	 * update acc info;
	 * @param acc
	 * @return
	 */
	public String updateAccStore(ShStoreAccount acc){
		try {
			if (acc!=null) {
				if(acc.getStoreAccountId()!=null){
					ViewStoreAccount view=storeAccountService.queryAccViewById(new BigDecimal(acc.getStoreAccountId()));
					acc.setUpdateDate(new Date());
					acc.setAddDate(view.getAddDate());
					//acc.setStoreId(view.getStoreId());
					acc.setAmount(view.getAmount());
					storeAccountService.save(acc);
					return "1";
				}else{
					Map<String,String> params=new HashMap<String,String>();
					params.put("accountNum", acc.getAccountNum().toString());
					List<ViewStoreAccount> list=storeAccountService.queryAccByParam(params);
					if(list.size()>0){
						return "2";
					}
					acc.setUpdateDate(new Date());
					if("11".equals(acc.getAccountType().toString())){
						acc.setStoreId(1L);
					}
					acc.setAddDate(new Date());
					acc.setAmount(0.0);
					storeAccountService.save(acc);
					return "1";
				}
			}
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return "0";
	}
	/**
	 * delete account store;
	 * @return
	 */
	public boolean delYpStore(Long accId){
		try {
			if (accId!=null) {
				ShStoreAccount acc=storeAccountService.queryAccById(accId);
				storeAccountService.delete(acc);
				return true;
			}
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return false;
	}
	/**
	 * 审核
	 */
	public boolean updateStoreAudit(Long[] storeIds, int isAudit, Long updateUser,String comment) {
		try {
			//Long adminUserId = 1L; // 
			if (storeIds.length > 0) {
				return storeInfoService.updateAudit(storeIds, isAudit,updateUser,comment);
			}
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
	public ProductTypeService getProductTypeService() {
		return productTypeService;
	}
	public void setProductTypeService(ProductTypeService productTypeService) {
		this.productTypeService = productTypeService;
	}
	public StoreAccountService getStoreAccountService() {
		return storeAccountService;
	}
	public void setStoreAccountService(StoreAccountService storeAccountService) {
		this.storeAccountService = storeAccountService;
	}
	public StoreAccountDetailService getStoreAccountDetailService() {
		return storeAccountDetailService;
	}
	public void setStoreAccountDetailService(
			StoreAccountDetailService storeAccountDetailService) {
		this.storeAccountDetailService = storeAccountDetailService;
	}
	public StoreInfoService getStoreInfoService() {
		return storeInfoService;
	}
	public void setStoreInfoService(StoreInfoService storeInfoService) {
		this.storeInfoService = storeInfoService;
	}
	public StoreUserRelationService getStoreUserRelationService() {
		return storeUserRelationService;
	}
	public void setStoreUserRelationService(
			StoreUserRelationService storeUserRelationService) {
		this.storeUserRelationService = storeUserRelationService;
	}
	public SyUserInfoService getSyUserInfoService() {
		return syUserInfoService;
	}
	public void setSyUserInfoService(SyUserInfoService syUserInfoService) {
		this.syUserInfoService = syUserInfoService;
	}
	public RoleManageService getRoleMngService() {
		return roleMngService;
	}
	public void setRoleMngService(RoleManageService roleMngService) {
		this.roleMngService = roleMngService;
	}
}
