package com.oim.stores.store.control;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.oim.stores.common.BaseAction;
import com.oim.stores.common.Content;
import com.oim.stores.common.JSONUtils;
import com.oim.stores.common.LogManager;
import com.oim.stores.common.PageRequest;
import com.oim.stores.common.PageResponse;
import com.oim.stores.common.PubFun;
import com.oim.stores.common.Tool;
import com.oim.stores.datadict.domain.SyParameter;
import com.oim.stores.datadict.domain.SyRegional;
import com.oim.stores.datadict.service.ProductTypeService;
import com.oim.stores.datadict.service.RegionalService;
import com.oim.stores.exception.ServiceException;
import com.oim.stores.shop.domain.ShStoreHomeModule;
import com.oim.stores.shop.domain.ShStoreHomeProduct;
import com.oim.stores.shop.domain.ShStoreHomeSetting;
import com.oim.stores.shop.service.ModuleService;
import com.oim.stores.store.domain.ShStoreCluster;
import com.oim.stores.store.domain.ShStoreInfo;
import com.oim.stores.store.domain.ShStoreRoleRel;
import com.oim.stores.store.domain.ShStoreRoleRelPk;
import com.oim.stores.store.domain.ShStoreUserRelation;
import com.oim.stores.store.domain.ShStoreUserRelationId;
import com.oim.stores.store.domain.ViewStoreAccount;
import com.oim.stores.store.domain.ViewStoreCluster;
import com.oim.stores.store.service.StoreAccountDetailService;
import com.oim.stores.store.service.StoreAccountService;
import com.oim.stores.store.service.StoreInfoService;
import com.oim.stores.store.service.StoreUserRelationService;
import com.oim.stores.system.domain.SyRoleInfo;
import com.oim.stores.system.service.RoleManageService;
import com.oim.stores.user.domain.SyUserInfo;
import com.oim.stores.user.service.SyUserInfoService;

/**
 * 商家管理Action
 */
@SuppressWarnings({ "serial" })
public class StoreAction extends BaseAction {
	@SuppressWarnings("unused")
	private ProductTypeService productTypeService;// 商品类型service
	private StoreAccountService storeAccountService;// 商家accountservice
	@SuppressWarnings("unused")
	private StoreAccountDetailService storeAccountDetailService;// 商家详细service;
	private StoreInfoService storeInfoService;// 商家管理service
	private StoreUserRelationService storeUserRelationService;// 商家用户关系service
	private SyUserInfoService syUserInfoService;// 用户service
	private RoleManageService roleMngService;// 角色管理service
	private ModuleService moduleService; // 商家模块Service
	private RegionalService regionalService; // 行政区管理service

	private List<SyParameter> productSources;// 系统参数
	private List<SyRegional> cityList; // 九地市列表

	public RoleManageService getRoleMngService() {
		return roleMngService;
	}

	public void setRoleMngService(RoleManageService roleMngService) {
		this.roleMngService = roleMngService;
	}

	private ShStoreInfo storeInfo;// 商家

	private String jsonDatas;// json数据
	private int total;// 总数
	private String userName;// 用户名
	private String userIds;// 用户id
	private Long storeId;// 商家id
	private String storeName;// 商家名
	private int storeStatus;// 商家状态

	private List<ShStoreInfo> managedStores; // 托管商家列表
	private String mamagedRole; // 托管角色Id

	private ShStoreCluster cluster; // 聚类客户信息
	private ViewStoreCluster viewStoreC;
	
	/**
	 * query store detail info;
	 * @return
	 */
	public String toStoreDetail(){
		String storeId=request.getParameter("storeId");
		try {
			viewStoreC=storeInfoService.queryStoreById(new Long(storeId));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	/**
	 * to yipay page;
	 * 
	 * @return
	 */
	public String toYIPayStore() {
		return SUCCESS;
	}

	public String qryYIPayStore() {
		PageRequest pageRequest = new PageRequest();
		int pageNumber = Integer.valueOf(request.getParameter("page"));
		int pageSize = Integer.valueOf(request.getParameter("rows"));
		if (pageNumber > 0) {
			pageNumber = pageNumber - 1;
		}
		pageRequest.setPageNumber(pageNumber);
		pageRequest.setPageSize(pageSize);
		// 查询条件
		Map<String, String> params = new HashMap<String, String>();
		params.put("storeName", request.getParameter("storeName"));
		try {
			PageResponse pageList = storeAccountService.queryAccViewForPage(params, pageNumber, pageSize);
			JSONArray jsonArray = new JSONArray();
			for (int i = 0; i < pageList.getList().size(); i++) {
				ViewStoreAccount si = (ViewStoreAccount) pageList.getList().get(i);
				JSONObject jsonObject = JSONUtils.toJSONObject(si);
				jsonArray.add(jsonObject);
			}
			jsonDatas = jsonArray.toString();
			total = pageList.getTotalRecord();
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return "json";
	}

	/**
	 * query store for yipay add;
	 * 
	 * @return
	 */
	public String queryStoreForYP() {
		PageRequest pageRequest = new PageRequest();
		int pageNumber = Integer.valueOf(request.getParameter("page"));
		int pageSize = Integer.valueOf(request.getParameter("rows"));
		if (pageNumber > 0) {
			pageNumber = pageNumber - 1;
		}
		pageRequest.setPageNumber(pageNumber);
		pageRequest.setPageSize(pageSize);
		// 查询条件
		Map<String, String> params = new HashMap<String, String>();
		params.put("storeName", request.getParameter("storeName"));
		params.put("isAudit", request.getParameter("isAudit"));
		params.put("storeType", "1");
		Map<String, String> params2 = new HashMap<String, String>();
		try {
			List<ViewStoreAccount> elist = storeAccountService.queryAccByParam(params2);
			String str = "";
			for (int i = 0; i < elist.size(); i++) {
				ViewStoreAccount view=elist.get(i);
				if("1".equals(view.getStoreId().toString())&&"11".equals(view.getAccountType().toString())){	
				}else{
					str = str + elist.get(i).getStoreId() + ",";
				}
			}
			if (str.length() > 0) {
				str = str.substring(0, str.length() - 1);
			}
			params.put("storeIds", str);
			PageResponse pageList = storeInfoService.queryForPage(params, pageNumber, pageSize);
			JSONArray jsonArray = new JSONArray();
			for (int i = 0; i < pageList.getList().size(); i++) {
				ShStoreInfo si = (ShStoreInfo) pageList.getList().get(i);
				JSONObject jsonObject = JSONUtils.toJSONObject(si);
				jsonArray.add(jsonObject);
			}
			jsonDatas = jsonArray.toString();
			total = pageList.getTotalRecord();
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return "json";
	}

	/**
	 * 跳转至商品管理页
	 * 
	 * @return
	 */
	public String storeManager() {
		Map<String, String> params = new HashMap<String, String>();
		productSources = storeInfoService.getProductSources();
		managedStores = storeInfoService.getShStoreInfos();
		try {
			cityList = regionalService.queryCity(350000);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 跳转至商家添加页
	 * 
	 * @return
	 */
	public String toAddStore() {
		try {
			cityList = regionalService.queryCity(350000);
			storeInfo = new ShStoreInfo();
			@SuppressWarnings("unused")
			Map<String, String> params = new HashMap<String, String>();
			// params.put("parentType", "0");
			productSources = storeInfoService.getProductSources();
			// List<ShProductType> list = productTypeService.query(params);
			// request.setAttribute("pts", list);
			managedStores = storeInfoService.getShStoreInfos();
			//cityList = regionalService.queryCity(350000);
		} catch (Exception e) {
			try {
				storeInfo = new ShStoreInfo();
				productSources = storeInfoService.getProductSources();
				managedStores = storeInfoService.getShStoreInfos();
				cityList = regionalService.queryCity(350000);
			} catch (ServiceException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 获得托管商家角色
	 */
	public void getStoreRoles() {
		if (storeId != null) {
			List<SyRoleInfo> roles = storeInfoService.getRoles();
			List<ShStoreUserRelation> storeUserRels = storeInfoService.getStoreUserRel(storeId.toString());
			Set<String> set = new HashSet<String>();
			if (storeUserRels != null) {
				for (int i = 0; i < storeUserRels.size(); i++) {
					set.add(storeUserRels.get(i).getUserStoreRoleId().toString());
				}
			}

			JSONArray array = new JSONArray();
			for (String rolId : set) {
				for (int i = 0; i < roles.size(); i++) {
					if (rolId.equals(roles.get(i).getRoleId().toString())) {
						array.add(roles.get(i));
					}
				}
			}

			try {
				PubFun.getResponseWriter().write(array.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 查询商家
	 * 
	 * @return
	 */
	public String queryStoreForPage() {
		PageRequest pageRequest = new PageRequest();
		int pageNumber = Integer.valueOf(request.getParameter("page"));
		int pageSize = Integer.valueOf(request.getParameter("rows"));
		String storeName = request.getParameter("storeName");
		String isAudit = request.getParameter("isAudit");
		String storeType = request.getParameter("storeType");
		String storeMode = request.getParameter("storeMode");
		String regionalId = request.getParameter("regionalId");
		if (pageNumber > 0) {
			pageNumber = pageNumber - 1;
		}
		pageRequest.setPageNumber(pageNumber);
		pageRequest.setPageSize(pageSize);
		// 查询条件
		Map<String, String> params = new HashMap<String, String>();
		params.put("storeName", storeName);
		params.put("isAudit", isAudit);
		params.put("storeType", storeType);
		params.put("storeMode", storeMode);
		params.put("regionalId", regionalId);
		
		try {
			PageResponse pageList = storeInfoService.queryForPage(params, pageNumber, pageSize);
			JSONArray jsonArray = new JSONArray();
			List<SyRegional> regList = regionalService.queryCity(350000);
			for (int i = 0; i < pageList.getList().size(); i++) {
				ShStoreInfo si = (ShStoreInfo) pageList.getList().get(i);
				if (si != null && Tool.isNotEmpty(si.getCserviceTel())) {
					String storeUrl = Content.STORE_URL.replace("cserviceTel", si.getCserviceTel());
					si.setStoreUrl(storeUrl);
				}
				if (si != null && si.getRegionalId() != null) {
					for (int j = 0; j < regList.size(); j++) {
						SyRegional reg = regList.get(j);
						if (reg != null) {
							String areaCode = new BigDecimal(reg.getAreacode()).toString();
							if (si.getRegionalId().toString().equals(areaCode)) {
								si.setRegionalName(reg.getRegionalName());
							}
						}
					}
				}
				JSONObject jsonObject = JSONUtils.toJSONObject(si);
				jsonArray.add(jsonObject);
			}
			jsonDatas = jsonArray.toString();
			total = pageList.getTotalRecord();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "json";
	}

	/**
	 * 验证商家名称是否存在
	 * 
	 * @return
	 */
	public void checkStoreName() {
		boolean state = true;
		try {
			if (null != storeName) { 
				List<ShStoreInfo> sis = storeInfoService.queryByStoreName(storeName);
				for (ShStoreInfo si : sis) {
					if ((null == storeId || si.getStoreId().longValue() != storeId) && si.getStoreName().equals(storeName)) {
						state = false;
						break;
					}
				}
			}
		} catch (ServiceException e) {
			state = false;
			e.printStackTrace();
		}
		try {
			response.setCharacterEncoding("utf-8");
			response.getWriter().write("" + state);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 保存商家
	 * 
	 * @return
	 */
	public String saveStore() {
		boolean isNew = false;// 标记为修改还是新增 默认为修改
		SyUserInfo userinfo = (SyUserInfo) session.getAttribute(Content.LOGIN_USER);
		try {
			if (null != storeInfo) {
				storeInfo.setUpdateDate(new Date());
				storeInfo.setUpdateUser(userinfo.getUserId());
				
				if (storeInfo.getRegionalId() != null && storeInfo.getRegionalId().toString().equals("590")) {
					storeInfo.setRegionalId(null);
				}

				List<ShStoreInfo> sis = storeInfoService.queryByStoreName(storeInfo.getStoreName());
				for (ShStoreInfo si : sis) {
					if ((null == storeInfo.getStoreId() || si.getStoreId().longValue() != storeInfo.getStoreId().longValue()) && si.getStoreName().equals(storeInfo.getStoreName())) {
						returnMsg = "保存商家名称已经存在,请更换其它名称!";
						return ERROR;
					}
				}

				if (null == storeInfo.getStoreId()) {
					storeInfo.setStoreStatus(1L);
					storeInfo.setIsAudit(0L);
					storeInfo.setAddDate(new Date());
					isNew = true;
				}
				storeInfoService.save(storeInfo);
				if (storeInfo.getManagedStore() != null && mamagedRole != null && mamagedRole.length() > 0) {
					ShStoreRoleRel rel = new ShStoreRoleRel();
					ShStoreRoleRelPk id = new ShStoreRoleRelPk();
					id.setRoleid(new Long(mamagedRole));
					id.setStoreid(storeInfo.getStoreId());
					if (storeInfoService.getStoreRoleById(id) == null) {
						rel.setId(id);
						rel.setState(new Long("1"));
						rel.setUpdateddate(new Date());
						rel.setUpdateduser(userinfo.getUserId());
						storeInfoService.saveStoreRoleRel(rel);
					}
				}

				if (cluster != null) {
					if ((cluster.getBankAccount() != null && cluster.getBankAccount().length() > 0) || (cluster.getRegAccount() != null && cluster.getRegAccount().length() > 0) || (cluster.getClusterName() != null && cluster.getClusterName().length() > 0)) {
						cluster.setStoreId(new Double(storeInfo.getStoreId().toString()));
						storeInfoService.saveCluster(cluster);
					}
				}
				addStoreModel(isNew);// 插入默认模块
				returnMsg = "1";// "保存商家信息成功!";
				return SUCCESS;
			}
		} catch (Exception e) {
			e.printStackTrace();
			LogManager.error("商家保存时出现错误:" + e.getMessage());
		}
		returnMsg = "0";// "保存商家信息出错了!";
		return ERROR;
	}

	/**
	 * 添加默认商家模块
	 * 
	 * @param isNew
	 *            是否新增商家
	 * @throws ServiceException
	 */
	private void addStoreModel(boolean isNew) throws ServiceException {
		// 商家类型为客户经理时，为该商家创建商家模块，并添加可分销商品
		List<ShStoreHomeModule> moduleList = null;
		if (null != storeInfo && isNew) {
			if (storeInfo.getStoreMode() != null && !storeInfo.getStoreMode().toString().equals("2")) {
				// 商家店铺默认模块: 商家Logo背景、 商家logo、 头部广告、商品
				String[] typeIds = { Content.MODULE_TYPE_PRODUCT, Content.MODULE_TYPE_CONTENT_TOP, Content.MODULE_TYPE_SHOP, Content.MODULE_TYPE_SHOP_BACKGROUND };
				// 查得商品模版
				moduleList = moduleService.getMOduleByTypeIds(typeIds);
			} else if (storeInfo.getStoreMode() != null && storeInfo.getStoreMode().toString().equals("2")) {
				// 聚类客户经理店铺默认模块： 商家Logo背景、商家logo、分销商品
				String[] typeIds = { Content.MODULE_TYPE_SHOP, Content.MODULE_TYPE_SHOP_BACKGROUND, Content.MODULE_TYPE_SHARE_PRODUCT };
				// 查得商品模版
				moduleList = moduleService.getMOduleByTypeIds(typeIds);
			}
			if (moduleList != null && moduleList.size() > 0) {
				for (ShStoreHomeModule module : moduleList) {
					// 创建商家分销商品模块
					ShStoreHomeSetting setting = new ShStoreHomeSetting();
					setting.setStoreId(new BigDecimal(storeInfo.getStoreId()));
					setting.setModuleId(new BigDecimal(module.getModuleId()));
					setting.setModuleDesc(module.getModuleName());
					setting.setModuleOrder(new BigDecimal(1));
					setting.setIsShow(new BigDecimal(1));
					// 设置显示个数 商品默认20 其它为1
					if (Content.MODULE_TYPE_PRODUCT.equals(module.getTypeId().toString()) || Content.MODULE_TYPE_SHARE_PRODUCT.equals(module.getTypeId().toString())) {
						setting.setShowNum(new BigDecimal(20));
					} else {
						setting.setShowNum(new BigDecimal(1));
					}
					setting.setUpdatedDate(new Date());
					setting.setUpdatedUser(storeInfo.getUpdateUser().toString());
					moduleService.saveModuleSetting(setting);

					if (Content.MODULE_TYPE_CONTENT_TOP.equals(module.getTypeId().toString())) {
						ShStoreHomeProduct shp = new ShStoreHomeProduct();
						shp.setStoreModuleId(new BigDecimal(setting.getStoreModuleId()));
						shp.setToUrl("#");
						shp.setTitleDesc(setting.getModuleDesc());
						shp.setIsShow(new BigDecimal(1));
						shp.setProductOrder(new BigDecimal(1));
						shp.setRemark(setting.getModuleDesc());
						shp.setBigPic(Content.MODULE_DEFAULT_TOP_ADVERT);
						shp.setSmallPic(Content.MODULE_DEFAULT_TOP_ADVERT);
						shp.setUpdatedDate(new Date());
						shp.setUpdatedUser(setting.getUpdatedUser());
						moduleService.saveStoreProduct(shp);
					}

				}
			}
		}
	}

	/**
	 * 根据用户名查用户
	 * 
	 * @param userName
	 */
	public void queryByUserName() {
		if (Tool.isNotEmpty(userName)) {
			List<SyUserInfo> list = syUserInfoService.getUserInfoByName(userName);
			JSONArray jsonArray = new JSONArray();
			for (SyUserInfo sui : list) {
				Map<String, Object> u = new HashMap<String, Object>();
				u.put("userId", sui.getUserId());
				u.put("userName", sui.getUserName());
				jsonArray.add(u);
			}
			try {
				response.setCharacterEncoding("utf-8");
				response.getWriter().write(jsonArray.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 查询已关联的用户
	 * 
	 * @param userName
	 */
	public void queryStoreUsers() {
		try {
			if (null != storeId) {
				Map<String, String> params = new HashMap<String, String>();
				// params.put("roleName", Content.DEFAULT_BUSINESS_ADMIN);
				params.put("roleId", Content.DEFAULT_BUSINESS_ADMIN1);
				List<SyRoleInfo> derole = roleMngService.queryBusi(params);
				List<ShStoreUserRelation> list = new ArrayList<ShStoreUserRelation>();
				if (derole.size() > 0) {
					list = storeUserRelationService.queryAdminByStoreId(storeId, derole.get(0).getRoleId());// .queryByStoreId(storeId);
				}
				JSONArray jsonArray = new JSONArray();
				for (ShStoreUserRelation sui : list) {
					Map<String, Object> u = new HashMap<String, Object>();
					u.put("userId", sui.getUserInfo().getUserId());
					u.put("userName", sui.getUserInfo().getUserName());
					jsonArray.add(u);
				}
				try {
					response.setCharacterEncoding("utf-8");
					response.getWriter().print(jsonArray.toString());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} catch (ServiceException e1) {
			e1.printStackTrace();
		}
	}

	/**
	 * 商家关联用户
	 */
	public void saveRelUser() {
		int ok = 0;
		try {
			String[] ids = userIds.split(",");
			List<ShStoreUserRelation> surs = new ArrayList<ShStoreUserRelation>();
			SyUserInfo userInfo = (SyUserInfo) session.getAttribute(Content.LOGIN_USER);
			Map<String, String> params = new HashMap<String, String>();
			// params.put("roleName", Content.DEFAULT_BUSINESS_ADMIN);
			params.put("roleId", Content.DEFAULT_BUSINESS_ADMIN1);
			List<SyRoleInfo> derole = roleMngService.queryBusi(params);
			long roleId;
			if (derole.size() > 0) {
				roleId = derole.get(0).getRoleId();
			} else {
				roleId = 1L;
			}
			String roleid = roleId + "";
			if (null != ids[0] && !"".equals(ids[0]) && ids.length > 0 && null != storeId) {
				List<ShStoreUserRelation> oldlist = storeUserRelationService.queryAdminByStoreId(storeId, roleId);
				if (oldlist.size() > 0) {// 存在default;
					if (ids[0].equals(oldlist.get(0).getId().getUserId())) {
						ok = 1;
					} else {
						ok = 5;// 已经存在默认，不允许覆盖;
					}
				} else {// 不存在默认,确认用户能否进行关联
					ShStoreUserRelation exist = storeUserRelationService.queryByUserId(ids[0]);
					if (exist != null) {
						if ("2".equals(exist.getUserStatus().toString())) {
							ok = 1;
						} else if ("0".equals(exist.getUserStatus().toString())) {
							ok = 3;// user is ice;
						} else if ("1".equals(exist.getUserStatus().toString()) && "1".equals(exist.getId().getStoreId().toString()) && !(roleid.equals(exist.getUserStoreRoleId().toString()))) {
							ok = 1;
						} else {
							ok = 2;// user is inuse;
						}
						if (ok == 1) {
							ShStoreUserRelation sr = new ShStoreUserRelation();
							ShStoreUserRelationId id = new ShStoreUserRelationId();
							id.setStoreId(storeId);
							id.setUserId(new Long(ids[0]));
							sr.setId(id);
							sr.setAddDate(new Date());
							sr.setUpdateDate(new Date());
							sr.setUpdateUser(userInfo.getUserId());
							sr.setUserStoreRoleId(roleId);
							sr.setUserStatus(1L);
							surs.add(sr);
							storeUserRelationService.delete(exist);
						}
					} else {
						ShStoreUserRelation sr = new ShStoreUserRelation();
						ShStoreUserRelationId id = new ShStoreUserRelationId();
						id.setStoreId(storeId);
						id.setUserId(new Long(ids[0]));
						sr.setId(id);
						sr.setAddDate(new Date());
						sr.setUpdateDate(new Date());
						sr.setUpdateUser(userInfo.getUserId());
						sr.setUserStoreRoleId(roleId);
						sr.setUserStatus(1L);
						surs.add(sr);
						ok = 1;
					}
				}

			} else if ("".equals(ids[0]) || !(ids[0] != null)) {// 取消关联;
				List<ShStoreUserRelation> oldlist = storeUserRelationService.queryAdminByStoreId(storeId, roleId);
				ShStoreUserRelation old = oldlist.get(0);
				old.setUpdateDate(new Date());
				old.setUserStatus(2L);
				surs.add(old);
				ok = 1;
			}
			if (surs.size() > 0) {
				storeUserRelationService.saveAll(surs);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			response.getWriter().write("{\"status\":" + ok + "}");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 保存关联用户
	 */
	public void saveRelUserOld() {
		int ok = 0;
		try {
			String[] ids = userIds.split(",");
			SyUserInfo userInfo = (SyUserInfo) session.getAttribute(Content.LOGIN_USER);
			List<ShStoreUserRelation> surs = new ArrayList<ShStoreUserRelation>();
			if (null != userInfo && null != ids && ids.length > 0 && null != storeId) {
				Map<String, String> params = new HashMap<String, String>();
				// params.put("roleName", Content.DEFAULT_BUSINESS_ADMIN);
				params.put("roleId", Content.DEFAULT_BUSINESS_ADMIN1);
				List<SyRoleInfo> derole = roleMngService.query(params);
				long roleId;
				if (derole.size() > 0) {
					roleId = derole.get(0).getRoleId();
				} else {
					roleId = 1L;
				}
				List<ShStoreUserRelation> storeAdmin = storeUserRelationService.queryAdminByStoreId(storeId, roleId);
				for (String userId : ids) {
					if (Tool.isNotEmpty(userId)) {
						ShStoreUserRelation exist = storeUserRelationService.queryByUserId(userId);
						if (exist != null) {
							@SuppressWarnings("unused")
							String storeid = storeId + "";
							String roleid = roleId + "";
							if ("0".equals(exist.getUserStatus().toString())) {
								ok = 3;// this user is ice;
							} else if ("1".equals(exist.getUserStatus().toString())) {
								if ("1".equals(exist.getId().getStoreId().toString()) && (!roleid.equals(exist.getUserStoreRoleId().toString()))) {
									ShStoreUserRelation sr = new ShStoreUserRelation();
									ShStoreUserRelationId id = new ShStoreUserRelationId();
									id.setStoreId(storeId);
									id.setUserId(new Long(userId));
									sr.setId(id);
									sr.setAddDate(new Date());
									sr.setUpdateDate(new Date());
									sr.setUpdateUser(userInfo.getUserId());
									sr.setUserStoreRoleId(roleId);
									sr.setUserStatus(1L);
									surs.add(sr);
									storeUserRelationService.delete(exist);
								} else {
									ok = 2;// this user inuse;
								}
							} else {
								ShStoreUserRelation sr = new ShStoreUserRelation();
								ShStoreUserRelationId id = new ShStoreUserRelationId();
								id.setStoreId(storeId);
								id.setUserId(new Long(userId));
								sr.setId(id);
								sr.setAddDate(new Date());
								sr.setUpdateDate(new Date());
								sr.setUpdateUser(userInfo.getUserId());
								sr.setUserStoreRoleId(roleId);
								sr.setUserStatus(1L);
								surs.add(sr);
								storeUserRelationService.delete(exist);
							}
						} else {// null,
							ShStoreUserRelation sr = new ShStoreUserRelation();
							ShStoreUserRelationId id = new ShStoreUserRelationId();
							id.setStoreId(storeId);
							id.setUserId(new Long(userId));
							sr.setId(id);
							sr.setAddDate(new Date());
							sr.setUpdateDate(new Date());
							sr.setUpdateUser(userInfo.getUserId());
							sr.setUserStoreRoleId(roleId);
							sr.setUserStatus(1L);
							surs.add(sr);
							// ok=1;
						}
					} else {
						List<ShStoreUserRelation> oldlist = storeUserRelationService.queryAdminByStoreId(storeId, roleId);// .queryByStoreId(storeId);
						for (int i = 0; i < oldlist.size(); i++) {
							ShStoreUserRelation old = oldlist.get(i);
							old.setUpdateDate(new Date());
							old.setUserStatus(2L);
							surs.add(old);
						}
						// ok=1;
					}
				}
				if (storeAdmin.size() > 0) {
					ShStoreUserRelation sur = storeAdmin.get(0);
					sur.setUserStatus(2L);
					surs.add(sur);
				}
				if (surs.size() > 0) {
					storeUserRelationService.saveAll(surs);
					ok = 1;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			response.getWriter().write("{\"status\":" + ok + "}");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 关闭开启商家
	 * 
	 * @param userName
	 */
	public void updateStoreStatus() {
		int ok = 0;
		try {
			if (null != storeId && storeStatus == 0 || storeStatus == 1) {
				ShStoreInfo si = storeInfoService.queryById(storeId);
				if (null != si) {
					si.setStoreStatus(new Long(storeStatus));
					storeInfoService.save(si);
					ok = 1;
				}
			}
		} catch (ServiceException e1) {
			e1.printStackTrace();
		}
		try {
			response.getWriter().write("{\"status\":" + ok + "}");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 判定是否已存在客服电话
	 */
	public void hasCserviceTel() {
		boolean state = false;
		try {
			String cserviceTel = request.getParameter("cserviceTel");
			ShStoreInfo store = storeInfoService.getStoreByCserviceTel(cserviceTel);
			if (store != null) {
				state = false;
			} else {
				state = true;
			}
		} catch (Exception e) {
			state = false;
			e.printStackTrace();
		}
		try {
			PubFun.getResponseWriter().write("" + state);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void getRegionals(){
		try {
			List<SyRegional> list = regionalService.queryCity(350000);
			JSONArray array = new JSONArray();
			if (list != null) {
				for (int i = 0; i < list.size(); i++) {
					JSONObject obj = JSONUtils.toJSONObject(list.get(i));
					array.add(obj);
				}
			}
			PubFun.getResponseWriter().write(array.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void setProductTypeService(ProductTypeService productTypeService) {
		this.productTypeService = productTypeService;
	}

	public void setStoreAccountService(StoreAccountService storeAccountService) {
		this.storeAccountService = storeAccountService;
	}

	public void setStoreAccountDetailService(StoreAccountDetailService storeAccountDetailService) {
		this.storeAccountDetailService = storeAccountDetailService;
	}

	public void setStoreInfoService(StoreInfoService storeInfoService) {
		this.storeInfoService = storeInfoService;
	}

	public void setStoreUserRelationService(StoreUserRelationService storeUserRelationService) {
		this.storeUserRelationService = storeUserRelationService;
	}

	public ShStoreInfo getStoreInfo() {
		return storeInfo;
	}

	public void setStoreInfo(ShStoreInfo storeInfo) {
		this.storeInfo = storeInfo;
	}

	public String getJsonDatas() {
		return jsonDatas;
	}

	public void setJsonDatas(String jsonDatas) {
		this.jsonDatas = jsonDatas;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public void setSyUserInfoService(SyUserInfoService syUserInfoService) {
		this.syUserInfoService = syUserInfoService;
	}

	public String getUserIds() {
		return userIds;
	}

	public void setUserIds(String userIds) {
		this.userIds = userIds;
	}

	public Long getStoreId() {
		return storeId;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getStoreStatus() {
		return storeStatus;
	}

	public void setStoreStatus(int storeStatus) {
		this.storeStatus = storeStatus;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public List<ShStoreInfo> getManagedStores() {
		return managedStores;
	}

	public void setManagedStores(List<ShStoreInfo> managedStores) {
		this.managedStores = managedStores;
	}

	public String getMamagedRole() {
		return mamagedRole;
	}

	public void setMamagedRole(String mamagedRole) {
		this.mamagedRole = mamagedRole;
	}

	public List<SyParameter> getProductSources() {
		return productSources;
	}

	public void setProductSources(List<SyParameter> productSources) {
		this.productSources = productSources;
	}

	public void setModuleService(ModuleService moduleService) {
		this.moduleService = moduleService;
	}

	public ShStoreCluster getCluster() {
		return cluster;
	}

	public void setCluster(ShStoreCluster cluster) {
		this.cluster = cluster;
	}

	public void setRegionalService(RegionalService regionalService) {
		this.regionalService = regionalService;
	}

	public List<SyRegional> getCityList() {
		return cityList;
	}

	public void setCityList(List<SyRegional> cityList) {
		this.cityList = cityList;
	}

	public ViewStoreCluster getViewStoreC() {
		return viewStoreC;
	}

	public void setViewStoreC(ViewStoreCluster viewStoreC) {
		this.viewStoreC = viewStoreC;
	}

	
}