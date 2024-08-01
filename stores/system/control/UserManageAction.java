package com.oim.stores.system.control;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.oim.stores.common.BaseAction;
import com.oim.stores.common.JSONUtils;
import com.oim.stores.common.PageRequest;
import com.oim.stores.common.PageResponse;
import com.oim.stores.exception.ServiceException;
import com.oim.stores.system.domain.SyRoleInfo;
import com.oim.stores.system.domain.UserManageBean;
import com.oim.stores.system.service.MenuService;
import com.oim.stores.system.service.UserManageService;
/**
 * 用户管理action
 * @author yuyan
 *
 */
@SuppressWarnings("serial")
public class UserManageAction extends BaseAction {
	private UserManageService userMngService;//用户管理service
	private String jsonDatas;//json数据
	private int total;//总数
	private String userName;//用户名
	private UserManageBean userMngInfo;//用户信息
	private MenuService menuService;//菜单service
	/**
	 * 去用户管理页
	 * @return
	 */
	public String toMain(){
		Map<String, String> params2 = new HashMap<String, String>();
		//params.put("parentType", "0");
		params2.put("roleType", "1");
		try {
			List<SyRoleInfo> list2 = userMngService.query(params2);
			request.setAttribute("pts", list2);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 查询用户列表
	 * 
	 * @return
	 */
	public String queryUsersForPage() {
		PageRequest pageRequest = new PageRequest();
		int pageNumber = Integer.valueOf(request.getParameter("page"));
		int pageSize = Integer.valueOf(request.getParameter("rows"));
		String userNum=request.getParameter("userNumber");
		String userRole=request.getParameter("userRole");

		if (pageNumber > 0) {
			pageNumber = pageNumber - 1;
		}
		pageRequest.setPageNumber(pageNumber);
		pageRequest.setPageSize(pageSize);
		// 查询条件
		Map<String, String> params = new HashMap<String, String>();
		params.put("roleType", "1");
		if(userNum!=null&&userNum!=""){
			params.put("userName", userNum);
		}
		if(userRole!=null&&userRole!=""){
			params.put("roleId", userRole);
		}
		try {
			PageResponse pageList = userMngService.queryForPage(params, pageNumber, pageSize);
			JSONArray jsonArray = new JSONArray();
			for (int i = 0; i < pageList.getList().size(); i++) {
				UserManageBean si = (UserManageBean) pageList.getList().get(i);
				JSONObject jsonObject = JSONUtils.toJSONObject(si);
				jsonArray.add(jsonObject);
			}
			jsonDatas = jsonArray.toString();
			System.out.println(jsonDatas);
			total = pageList.getTotalRecord();
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return "json";
	}

	
	public UserManageService getUserMngService() {
		return userMngService;
	}

	public void setUserMngService(UserManageService userMngService) {
		this.userMngService = userMngService;
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public UserManageBean getUserMngInfo() {
		return userMngInfo;
	}

	public void setUserMngInfo(UserManageBean userMngInfo) {
		this.userMngInfo = userMngInfo;
	}

	public MenuService getMenuService() {
		return menuService;
	}

	public void setMenuService(MenuService menuService) {
		this.menuService = menuService;
	}
	
	
}
