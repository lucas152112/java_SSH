package com.oim.stores.user.control;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.Cookie;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.oim.stores.common.BaseAction;
import com.oim.stores.common.Content;
import com.oim.stores.common.Cryto;
import com.oim.stores.common.JSONUtils;
import com.oim.stores.common.LogManager;
import com.oim.stores.common.PageRequest;
import com.oim.stores.common.PageResponse;
import com.oim.stores.common.PubFun;
import com.oim.stores.common.Tool;
import com.oim.stores.exception.ServiceException;
import com.oim.stores.framework.utils.DateUtil;
import com.oim.stores.store.domain.ShStoreUserRelation;
import com.oim.stores.system.domain.RoleMenuBean;
import com.oim.stores.system.domain.SyAdminUser;
import com.oim.stores.system.domain.SyMenuList;
import com.oim.stores.system.domain.SyRight;
import com.oim.stores.system.domain.SyRoleInfo;
import com.oim.stores.system.service.MenuService;
import com.oim.stores.system.service.RoleManageService;
import com.oim.stores.system.service.SyRightService;
import com.oim.stores.user.domain.SyUserInfo;
import com.oim.stores.user.service.SyUserInfoService;

/**
 * 用户登录Action
 * 
 * @author czw20120808
 */
@SuppressWarnings({ "unchecked", "serial" })
public class LoginAction extends BaseAction {
	private SyUserInfoService syUserInfoService;//系统用户service
	private RoleManageService roleService;//角色管理service
	private SyRightService syRightService;//权限管理service
	private SyUserInfo userInfo;//系统用户
	private String userId;//用户id
	private String userName;//用户名
	private String userPwd;//用户密码
	private String msg;//message
	private String param;//参数
	private String name;//名字
	private String process;//过程
	private String type;//类型
	private String verCode;//验证码
	private String oldPwd;//旧密码
	private int total;//总数
	private String jsonDatas;//json数据
	private String userMail;//用户邮箱
	private String menus;//菜单
	private List<RoleMenuBean> menulist = new ArrayList<RoleMenuBean>();//角色菜单列表
	private List<SyMenuList> menulist2 = new ArrayList<SyMenuList>();//菜单列表

	public List<SyMenuList> getMenulist2() {
		return menulist2;
	}

	public void setMenulist2(List<SyMenuList> menulist2) {
		this.menulist2 = menulist2;
	}

	public String getUserMail() {
		return userMail;
	}

	public void setUserMail(String userMail) {
		this.userMail = userMail;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}
	
	/**
	 * @return the syRightService
	 */
	public SyRightService getSyRightService() {
	    return syRightService;
	}

	/**
	 * @param syRightService the syRightService to set
	 */
	public void setSyRightService(SyRightService syRightService) {
	    this.syRightService = syRightService;
	}

	/**
	 * 登录
	 */
	public String login() {
		// 验证码
		String rand = (String) session.getAttribute("rand");
		if (null != rand && rand.equals(verCode) && Tool.isNotEmpty(userName) && Tool.isNotEmpty(userPwd)) {
			//check
			if(!checkUserName(userName)){
				msg = "登录失败，用户名只能输入中文、字母和 数字！";
				return INPUT;
			}
			
			SyUserInfo user = syUserInfoService.getUserInfoByNameAndpwd(userName, userPwd);
			if (user != null) {
				if (syUserInfoService.isAdmin(user.getUserId().toString()) || "admin123".equals(user.getUserName())) {
					if (!"1".equals(user.getUserStatus().toString())) {
						msg = "登录失败，没有权限进入后台管理！";
						return INPUT;
					}
					try {
						List<ShStoreUserRelation> relas = menuService.queryStoreUserById(user.getUserId().toString());
						ShStoreUserRelation rela = relas.get(0);
						SyRoleInfo role = roleService.queryById(rela.getUserStoreRoleId());
						user.setRoleName(role.getRoleName());

						session.setAttribute(Content.LOGIN_USER, user);
						queryForLeftMenu();
						user.setMenus(menulist2);
						// user.setMenus(menulist);
						// session.setAttribute(Content.LOGIN_USER, user);
						LogManager.info(user.getUserName() + " LOGIN...");
					} catch (Exception e) {
						e.printStackTrace();
					}
					return SUCCESS;
				} else {
					msg = "登录失败，没有权限进入后台管理！";
					return INPUT;
				}
			} else {
				msg = "登录失败，请检查用户名和密码是否正确！";
				return INPUT;
			}
		} else {
			msg = "验证码错误！";
			return INPUT;
		}
	}
	/**
	 * check userName;
	 * @param userName
	 * @return
	 */
	public boolean checkUserName(String userName) {
		  String regex = "([a-z]|[A-Z]|[0-9]|[\\u4e00-\\u9fa5])+";
		  Pattern p = Pattern.compile(regex);
		  Matcher m = p.matcher(userName);
		  return m.matches();
	 }
	public List<RoleMenuBean> getMenulist() {
		return menulist;
	}

	public void setMenulist(List<RoleMenuBean> menulist) {
		this.menulist = menulist;
	}

	/**
	 * 退出
	 */
	public void quit() {
		session.removeAttribute(Content.LOGIN_USER);
		try {
			response.getWriter().print("<script>top.location='" + request.getContextPath() + "/index.jsp';</script>");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return;
	}

	/**
	 * 进入首页
	 */
	public String main() {
		return SUCCESS;
	}

	/**
	 * 进入密码重置
	 */
	public String pwReset() {
		return SUCCESS;
	}

	/**
	 * 修改密码
	 */
	public String updatePwd() {
		SyUserInfo loginUser = (SyUserInfo) PubFun.getSession("loginUser");
		String rand = (String) session.getAttribute("rand");
		if (!rand.equals(verCode)) {
			returnMsg = "1";
			return SUCCESS;
		}
		if (!loginUser.getUserPwd().toString().equals(Cryto.cryptMD5ToHEX(oldPwd))) {
			returnMsg = "2";
			return SUCCESS;
		}
		syUserInfoService.updatePwd(loginUser.getUserId().toString(), userPwd);
		returnMsg = "3";
		return SUCCESS;
		// SyUserInfo loginUser = (SyUserInfo) PubFun.getSession("loginUser");
		// syUserInfoService.updatePwd(loginUser.getUserId().toString(),
		// userPwd);
		// try {
		// PubFun.getResponseWriter().write(
		// "{\"info\":\"修改成功！\",\"status\":\"y\"}");
		// } catch (IOException e) {
		// e.printStackTrace();
		// }
	}

	/**
	 * 原密码是否正确
	 */
	public void isOldPwd() {
		SyUserInfo loginUser = (SyUserInfo) PubFun.getSession("loginUser");
		SyUserInfo user = syUserInfoService.getUserInfoById(loginUser.getUserId().toString());
		try {
			if (name.equals("oldPwd")) {
				if (user.getUserPwd().toString().equals(Cryto.cryptMD5ToHEX(param))) {
					PubFun.getResponseWriter().write("y");
				} else {
					PubFun.getResponseWriter().write("原密码错误！");
				}
			} else if (name.equals("userPwd")) {
				if (user.getUserPwd().toString().equals(Cryto.cryptMD5ToHEX(param))) {
					PubFun.getResponseWriter().write("新密码必须与旧密码不同！");
				} else {
					PubFun.getResponseWriter().write("y");
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 跳转至用户管理页
	 * 
	 * @return
	 */
	public String userinfoManager() {
		return SUCCESS;
	}

	/**
	 * 查询用户
	 * 
	 * @return
	 */
	public String queryUsersForPage() {
		PageRequest pageRequest = new PageRequest();
		int pageNumber = Integer.valueOf(request.getParameter("page"));
		int pageSize = Integer.valueOf(request.getParameter("rows"));
		String userNum = request.getParameter("userNumber");
		String userStatus = request.getParameter("userStatus");
		if (pageNumber > 0) {
			pageNumber = pageNumber - 1;
		}
		pageRequest.setPageNumber(pageNumber);
		pageRequest.setPageSize(pageSize);
		// 查询条件
		Map<String, String> params = new HashMap<String, String>();
		if (userNum != null && userNum != "") {
			params.put("userName", userNum);
		}
		if (userStatus != null && userStatus != "") {
			params.put("userStatus", userStatus);
		}
		try {
			PageResponse pageList = syUserInfoService.queryForPage(params, pageNumber, pageSize);
			JSONArray jsonArray = new JSONArray();
			for (int i = 0; i < pageList.getList().size(); i++) {
				SyUserInfo si = (SyUserInfo) pageList.getList().get(i);
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

	/**
	 * 找回密码
	 * 
	 * @return
	 */
	public String findBackPwd() {
		List<SyUserInfo> userList = syUserInfoService.getUserInfoByName(userName);
		@SuppressWarnings("unused")
		SyUserInfo loginUser = (SyUserInfo) PubFun.getSession("loginUser");
		String path2 = request.getContextPath();
		String path3 = request.getRemoteAddr();
		@SuppressWarnings("unused")
		String realPath = path3 + path2;
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "";
		String base = basePath + path2;

		if (userList != null && userList.size() > 0) {
			SyUserInfo user = userList.get(0);
			if (user.getUserMail().equals(userMail)) {
				// user.setUserPwd(Cryto.cryptMD5ToHEX(userInfo.getUserPwd()));
				// syUserInfoService.updateUserInfo(user); // 修改用户信息
				syUserInfoService.findBackPwd(user, base); // 发送邮件
				return SUCCESS;
			} else {
				returnMsg = "3";
				return "fail";
			}
		} else {
			returnMsg = "2";
			return "fail";
		}
	}
	/**
	 * find back password link;
	 * @return
	 */
	public String findPwdLink() {
		userId = request.getParameter("userId");
		request.setAttribute("userId", userId);
		return SUCCESS;
	}
	/**
	 * update password;
	 * @return
	 */
	public String updateFindPwd() {
		// SyUserInfo loginUser = (SyUserInfo) PubFun.getSession("loginUser");
		@SuppressWarnings("unused")
		String uid = request.getParameter("userId");
		String rand = (String) session.getAttribute("rand");
		if (!rand.equals(verCode)) {
			returnMsg = "1";
			return SUCCESS;
		}
		syUserInfoService.updatePwd(userId, userPwd);
		returnMsg = "3";
		return SUCCESS;
	}

	/**
	 * 注册
	 * 
	 * @return
	 */
	public void registration() {
		try {
			userInfo.setUserName(userInfo.getUserName().trim());
			if (syUserInfoService.registration(userInfo)) {
				PubFun.getResponseWriter().write("{\"status\":\"y\"}");
			} else {
				PubFun.getResponseWriter().write("{\"status\":\"n\"}");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 用户名、电子邮箱、验证码的验证
	 */
	public void verify() {
		try {
			// 用户名是否已存在
			if (name.equals("userInfo.userName")) {
				if (!syUserInfoService.hasLoginName(param)) {
					PubFun.getResponseWriter().write("y");
				} else {
					PubFun.getResponseWriter().write("用户名已存在！");
				}
				// 电子邮箱是否已存在
			} else if (name.equals("userInfo.userMail")) {
				if (!syUserInfoService.hasUserMail(param)) {
					PubFun.getResponseWriter().write("y");
				} else {
					PubFun.getResponseWriter().write("电子邮箱已存在！");
				}
				// 验证码
			} else if (name.equals("verCode")) {
				String rand = (String) PubFun.getSession("rand");
				if (rand.equals(param)) {
					PubFun.getResponseWriter().write("y");
				} else {
					PubFun.getResponseWriter().write("验证码错误！");
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 找回密码
	 */
	public String getPwd() {
		if (process.equals("1")) {
			// info = "\"info\":\"" + userInfo.getUserName() + "\"";
			return "toType";
		} else if (process.equals("2")) {
			return "toReset";
		} else if (process.equals("3")) {
			if (type.equals("mail")) {
				syUserInfoService.pwdBack(userInfo);
				return "result";
			}
		}

		return INPUT;
	}

	private MenuService menuService;

	/**
	 * 查询系统菜单列表
	 * 
	 * @return
	 */
	public void queryForLeftMenu() {
		SyUserInfo user = (SyUserInfo) PubFun.getSession("loginUser");
		String jsonstr = "";// "[";

		try {// splice jsonStr;
				// query user role
			List<ShStoreUserRelation> rela = menuService.queryStoreUserById(user.getUserId().toString());
			ShStoreUserRelation roleId = rela.get(0);
			Map<String, String> params1 = new HashMap<String, String>();
			// List<RoleMenuBean> list=new ArrayList<RoleMenuBean>();
			List<SyMenuList> mlist = new ArrayList<SyMenuList>();
			if ("admin123".equals(user.getUserName())) {
				mlist = menuService.queryMenu(params1);
			} else {
				// params1.put("userroleId",
				// roleId.getUserStoreRoleId().toString());
				// params1.put("relationType", "1");
				mlist = menuService.queryRoleMenu(params1, roleId.getUserStoreRoleId().toString());
			}
			// query role de menu
			// List<RoleMenuBean> parent=new ArrayList<RoleMenuBean>();
			List<SyMenuList> parent = new ArrayList<SyMenuList>();
			for (int i = 0; i < mlist.size(); i++) {
				SyMenuList role = mlist.get(i);
				if ("0".equals(role.getParentId().toString())) {
					parent.add(role);
				} else {
					menulist2.add(role);
				}
			}
			for (int i = 0; i < parent.size(); i++) {
				SyMenuList menu = parent.get(i);
				jsonstr += "<div title='" + menu.getMenuName() + "' style='padding: 10px;'><ul>";
				List<SyMenuList> sonNs = new ArrayList<SyMenuList>();
				for (int j = 0; j < mlist.size(); j++) {
					SyMenuList bean = mlist.get(j);
					if ((menu.getMenuId().toString()).equals(bean.getParentId().toString())) {
						sonNs.add(bean);
					}
				}
				if (sonNs.size() > 0) {
					jsonstr = appendChildNew(jsonstr, sonNs, mlist);
				}
				sonNs = null;
				jsonstr += "</ul></div>";
			}
			menus = jsonstr;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 查询系统菜单列表
	 * 
	 * @return
	 */
	public void queryForLeftMenuOld() {
		SyUserInfo user = (SyUserInfo) PubFun.getSession("loginUser");
		String jsonstr = "";// "[";

		try {// splice jsonStr;
				// query user role
			List<ShStoreUserRelation> rela = menuService.queryStoreUserById(user.getUserId().toString());
			ShStoreUserRelation roleId = rela.get(0);
			Map<String, String> params1 = new HashMap<String, String>();
			List<RoleMenuBean> list = new ArrayList<RoleMenuBean>();
			if ("admin123".equals(user.getUserName())) {
				list = menuService.queryAllMenu(params1);
			} else {
				params1.put("userroleId", roleId.getUserStoreRoleId().toString());
				params1.put("relationType", "1");
				list = menuService.queryRelation(params1);
			}
			// query role de menu
			List<RoleMenuBean> parent = new ArrayList<RoleMenuBean>();
			for (int i = 0; i < list.size(); i++) {
				RoleMenuBean role = list.get(i);
				if ("0".equals(role.getParentId().toString())) {
					parent.add(role);
				} else {
					menulist.add(role);
				}
			}
			for (int i = 0; i < parent.size(); i++) {
				RoleMenuBean menu = parent.get(i);
				jsonstr += "<div title='" + menu.getMenuName() + "' style='padding: 10px;'><ul>";
				Map<String, String> params2 = new HashMap<String, String>();
				params2.put("parentId", menu.getMenuId().toString());
				List<RoleMenuBean> sonNs = new ArrayList<RoleMenuBean>();
				for (int j = 0; j < list.size(); j++) {
					RoleMenuBean bean = list.get(j);
					if ((menu.getMenuId().toString()).equals(bean.getParentId().toString())) {
						sonNs.add(bean);
					}
				}
				if (sonNs.size() > 0) {
					jsonstr = appendChild(jsonstr, sonNs, list);
				}
				sonNs = null;
				jsonstr += "</ul></div>";
			}
			menus = jsonstr;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * append child node;
	 * @param jsonstr
	 * @param child
	 * @param list
	 * @return
	 * @throws ServiceException
	 */
	public String appendChild(String jsonstr, List child, List<RoleMenuBean> list) throws ServiceException {
		for (int i = 0; i < child.size(); i++) {
			RoleMenuBean menu = (RoleMenuBean) child.get(i);
			String path = (String) context.getAttribute("APP_PATH");
			if ("20".equals(menu.getMenuType().toString())) {
				jsonstr += "<li style='padding-left: 10px;'>";
				if (menu.getMenuUrl() != null && !"".equals(menu.getMenuUrl())) {
					jsonstr += "<a href='" + path + menu.getMenuUrl() + "' target='rightframe'>" + menu.getMenuName() + "</a>";
				} else {
					jsonstr += "<a href='javaScript:void(0)' target='rightframe'>" + menu.getMenuName() + "</a>";
				}
				jsonstr += "</li>";
			} else if ("10".equals(menu.getMenuType().toString())) {
				String dstr = "closed" + menu.getMenuId();
				String dostr = "opened" + menu.getMenuId();
				String clkdstr = "" + dstr + "";
				String clkdo = "" + dostr + "";
				jsonstr += "<li id='" + dstr + "' class=\"folder\" style='padding-left: 10px;margin-left:10px;'>";
				jsonstr += "<span onclick=\"menuClk('" + clkdo + "','" + clkdstr + "');\"" + " style=\"cursor:pointer;padding-left: 10px;\" >" + menu.getMenuName() + "</span>";
				jsonstr += "</li>";
				jsonstr += "<li id='" + dostr + "' class=\"cfolder\" style='display:none;padding-left: 10px;margin-left:10px;'><span  onclick=\"menuClk('" + clkdstr + "','" + clkdo + "');\"" + " style=\"cursor:pointer;padding-left: 10px;\">" + menu.getMenuName() + "</span>";
				List ssonlist = new ArrayList<RoleMenuBean>();
				for (int j = 0; j < list.size(); j++) {
					RoleMenuBean bean = list.get(j);
					if ((menu.getMenuId().toString()).equals(bean.getParentId().toString())) {
						ssonlist.add(bean);
					}
				}
				if (ssonlist.size() > 0) {
					jsonstr = appendChild(jsonstr, ssonlist, list);
				}
				jsonstr += "</li>";
			}
		}
		return jsonstr;
	}
	/**
	 * new append child node;
	 * @param jsonstr
	 * @param child
	 * @param list
	 * @return
	 * @throws ServiceException
	 */
	public String appendChildNew(String jsonstr, List child, List<SyMenuList> list) throws ServiceException {
		for (int i = 0; i < child.size(); i++) {
			SyMenuList menu = (SyMenuList) child.get(i);
			String path = (String) context.getAttribute("APP_PATH");
			if ("20".equals(menu.getMenuType().toString())) {
				jsonstr += "<li style='padding-left: 10px;'>";
				if (menu.getMenuUrl() != null && !"".equals(menu.getMenuUrl())) {
					jsonstr += "<a href='" + path + menu.getMenuUrl() + "' target='rightframe'>" + menu.getMenuName() + "</a>";
				} else {
					jsonstr += "<a href='javaScript:void(0)' target='rightframe'>" + menu.getMenuName() + "</a>";
				}
				jsonstr += "</li>";
			} else if ("10".equals(menu.getMenuType().toString())) {
				String dstr = "closed" + menu.getMenuId();
				String dostr = "opened" + menu.getMenuId();
				String clkdstr = "" + dstr + "";
				String clkdo = "" + dostr + "";
				jsonstr += "<li id='" + dstr + "' class=\"folder\" style='padding-left: 10px;margin-left:10px;'>";
				jsonstr += "<span onclick=\"menuClk('" + clkdo + "','" + clkdstr + "');\"" + " style=\"cursor:pointer;padding-left: 10px;\" >" + menu.getMenuName() + "</span>";
				jsonstr += "</li>";
				jsonstr += "<li id='" + dostr + "' class=\"cfolder\" style='display:none;padding-left: 10px;margin-left:10px;'><span  onclick=\"menuClk('" + clkdstr + "','" + clkdo + "');\"" + " style=\"cursor:pointer;padding-left: 10px;\">" + menu.getMenuName() + "</span>";
				List ssonlist = new ArrayList<SyMenuList>();
				for (int j = 0; j < list.size(); j++) {
					SyMenuList bean = list.get(j);
					if ((menu.getMenuId().toString()).equals(bean.getParentId().toString())) {
						ssonlist.add(bean);
					}
				}
				if (ssonlist.size() > 0) {
					jsonstr = appendChildNew(jsonstr, ssonlist, list);
				}
				jsonstr += "</li>";
			}
		}
		return jsonstr;
	}

	/**
	 * @return userInfo
	 */
	public SyUserInfo getUserInfo() {
		return userInfo;
	}

	/**
	 * @param userInfo
	 */
	public void setUserInfo(SyUserInfo userInfo) {
		this.userInfo = userInfo;
	}

	/**
	 * @return userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return userPwd
	 */
	public String getUserPwd() {
		return userPwd;
	}

	/**
	 * @param userPwd
	 */
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	/**
	 * @return userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return param
	 */
	public String getParam() {
		return param;
	}

	/**
	 * @param param
	 */
	public void setParam(String param) {
		this.param = param;
	}

	/**
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return process
	 */
	public String getProcess() {
		return process;
	}

	/**
	 * @param process
	 */
	public void setProcess(String process) {
		this.process = process;
	}

	/**
	 * @return type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 */
	public void setType(String type) {
		this.type = type;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public void setSyUserInfoService(SyUserInfoService syUserInfoService) {
		this.syUserInfoService = syUserInfoService;
	}

	public String getVerCode() {
		return verCode;
	}

	public void setVerCode(String verCode) {
		this.verCode = verCode;
	}

	public String getOldPwd() {
		return oldPwd;
	}

	public void setOldPwd(String oldPwd) {
		this.oldPwd = oldPwd;
	}

	public String getJsonDatas() {
		return jsonDatas;
	}

	public void setJsonDatas(String jsonDatas) {
		this.jsonDatas = jsonDatas;
	}

	public MenuService getMenuService() {
		return menuService;
	}

	public void setMenuService(MenuService menuService) {
		this.menuService = menuService;
	}

	public SyUserInfoService getSyUserInfoService() {
		return syUserInfoService;
	}

	public String getMenus() {
		return menus;
	}

	public void setMenus(String menus) {
		this.menus = menus;
	}

	public RoleManageService getRoleService() {
		return roleService;
	}

	public void setRoleService(RoleManageService roleService) {
		this.roleService = roleService;
	}
	
	public String  ssoLogin(){
	    response.setHeader("P3P","CP=CAO PSA OUR");
	    LogManager.debug("运营中心单点登录");
	    LogManager.info("运营中心单点登录");
        	//String genkey = request.getParameter("genkey");
	    	String protocolKey = "EF2073AD1364201C2597E0BC5E1CC4A1548329677AEC5EFB";

 
        	String user_token = request.getParameter("user_token");
        	String timestamp = request.getParameter("timestamp");
        	String user_ticket = request.getParameter("user_ticket");
        	//genkey = new String(Cryto.base64Decode(genkey));
        	user_token = new String(Cryto.base64Decode(user_token));
        	timestamp = new String(Cryto.base64Decode(timestamp));
        	user_ticket = new String(Cryto.base64Decode(user_ticket));
        	// 使用随机密钥genkey解密user_token,timestamp
        	user_token = Cryto.decryptBase643DES(user_token, protocolKey);
        	timestamp = Cryto.decryptBase643DES(timestamp, protocolKey);
        	int im = DateUtil.intervalMinute(converToTs(timestamp),
        		DateUtil.currentTimestamp());
        	// 比较传递的时间戳和当前的时间戳相隔是否小于3分钟
        	// 使用协议密钥解密user_ticket
        	 LogManager.info("间隔时间:" + im + "分钟");
        	if (im <= 3 && im >= 0) {
        	    // 协议秘钥
        	    String sysname = "storesadmin";
        	    user_ticket = Cryto.decryptBase643DES(user_ticket, protocolKey);
        	    if (user_ticket.equals(sysname)) {
        		// 设置cookie或者session
        		
//        		response.setHeader(
//        			"P3P",
//        			"CP=\"NON DSP COR CURa ADMa DEVa TAIa PSAa PSDa IVAa IVDa CONa HISa TELa OTPa OUR UNRa IND UNI COM NAV INT DEM CNT PRE LOC\"");
//        		Cookie cookie = new Cookie("user_token", user_token);
//        		cookie.setMaxAge(-1);
//        		cookie.setPath("/");
//        		response.addCookie(cookie);
        		//
        		// 验证user_token(用户标识)是否在本地库，本地库存在则直接登录否则提示相应的绑定本地用户名操作，通常user_token为手机号或者邮箱

        		
        		 LogManager.info("运营中心单点登录--开始验证用户信息");
        		    try {
        			SyAdminUser bean= syRightService.queryByUserName(user_token);
        			if (bean!=null) {
            				SyUserInfo user = new SyUserInfo();
            				user.setUserName(bean.getUserName());
                		    	user.setUserMail(bean.getMail());
                		    	user.setMobile(bean.getTelephone());
                		    	user.setUserId(bean.getUserId().longValue());
                		    	user.setUserStatus(bean.getUserStatus().longValue());
                		    	user.setUserRealname(bean.getUserRealname());
                		    	List<BigDecimal> rightRangeList=new ArrayList<BigDecimal>();
                		    	rightRangeList.add(new BigDecimal(1));
                		    	List<BigDecimal> rightTypeList=new ArrayList<BigDecimal>();
                		    	rightTypeList.add(new BigDecimal(3));
        				List<SyRight> rightList=syRightService.queryByUserName(user_token, rightRangeList, rightTypeList);
        				user.setRights(rightList);
        				//
//        				List<ShStoreUserRelation> relas = menuService.queryStoreUserById(user.getUserId().toString());
//        				ShStoreUserRelation rela = relas.get(0);
//        				SyRoleInfo role = roleService.queryById(rela.getUserStoreRoleId());
//        				user.setRoleName(role.getRoleName());
        				session.setAttribute(Content.LOGIN_USER, user);
        				 LogManager.info("运营中心单点登录--验证通过,"+user.getUserName());
//        				queryForLeftMenu();
//        				user.setMenus(menulist2);
				}
			    } catch (ServiceException e) {
				e.printStackTrace();
			    }

        		    return SUCCESS;
        		
        	    }
        	}
        	return ERROR;
	}
	
        private Timestamp converToTs(String timestamp) {
        	System.out.println("时间戳参数=" + timestamp);
        	Timestamp t = null;
        	Date d = new Date(Long.parseLong(timestamp));
        	String ts = DateUtil.date2String(d, DateUtil.PATTERN_STANDARD);
        	t = DateUtil.string2Timestamp(ts, DateUtil.PATTERN_STANDARD);
        	return t;
        }
	public static void main(String[] arg){
	    System.out.println(Cryto.generateKey());
	}
}
