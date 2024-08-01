package com.oim.stores.user.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;

import com.oim.stores.system.domain.RoleMenuBean;

public class UserInfoBean {
	private SyUserInfo userinfo;
	private List<RoleMenuBean> menus= new ArrayList<RoleMenuBean>();
	
	
	public UserInfoBean() {
	}
	public UserInfoBean(SyUserInfo userinfo, List<RoleMenuBean> menus) {
		this.userinfo = userinfo;
		this.menus = menus;
	}
	public SyUserInfo getUserinfo() {
		return userinfo;
	}
	public void setUserinfo(SyUserInfo userinfo) {
		this.userinfo = userinfo;
	}
	public List<RoleMenuBean> getMenus() {
		return menus;
	}
	public void setMenus(List<RoleMenuBean> menus) {
		this.menus = menus;
	}
	
	@SuppressWarnings("unchecked")
	public String getUserPrivilage(){
		JSONArray array = new JSONArray();
		for(int i=0;i<menus.size();i++){
			Map map = new HashMap();
			map.put("menuID", menus.get(i).getMenuId());
			map.put("webName", menus.get(i).getWebName());
			array.put(map);
		}
		return array.toString();
	}
	
}
