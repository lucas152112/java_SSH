package com.oim.stores.system.control;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.oim.stores.common.BaseAction;
import com.oim.stores.common.JSONUtils;
import com.oim.stores.common.PageRequest;
import com.oim.stores.common.PageResponse;
import com.oim.stores.common.Tool;
import com.oim.stores.exception.ServiceException;
import com.oim.stores.system.domain.MenuBean;
import com.oim.stores.system.domain.SyMenuList;
import com.oim.stores.system.domain.SyRoleInfo;
import com.oim.stores.system.domain.SyRoleMenuRelation;
import com.oim.stores.system.service.MenuService;
import com.oim.stores.system.service.PowerMngService;
import com.oim.stores.system.service.RoleManageService;
/**
 * 商家中心角色管理action
 * @author yuyan;
 *
 */
@SuppressWarnings({ "unchecked", "serial" })
public class BusiRoleManageAction extends BaseAction {
	private RoleManageService roleMngService;//角色管理service;
	private MenuService menuService;//菜单管理service;
	private PowerMngService powerMngService;//权限service
	private String jsonDatas;//json数据
	private int total;//总数
	private String userName;//用户名
	private SyRoleInfo roleInfo;//角色对象
	/**
	 * to 角色管理页
	 * @return
	 */
	public String toRoleMain(){
		return SUCCESS;
	}
	/**
	 * 查询角色列表
	 * 
	 * @return
	 */
	public String queryRoleForPage() {
		PageRequest pageRequest = new PageRequest();
		int pageNumber = Integer.valueOf(request.getParameter("page"));
		int pageSize = Integer.valueOf(request.getParameter("rows"));
		String userNum=request.getParameter("roleName");

		if (pageNumber > 0) {
			pageNumber = pageNumber - 1;
		}
		pageRequest.setPageNumber(pageNumber);
		pageRequest.setPageSize(pageSize);
		// 查询条件
		Map<String, String> params = new HashMap<String, String>();
		if(userNum!=null&&userNum!=""){
			params.put("roleName", userNum);
		}
		try {
			PageResponse pageList = roleMngService.queryBusiForPage(params, pageNumber, pageSize);
			JSONArray jsonArray = new JSONArray();
			for (int i = 0; i < pageList.getList().size(); i++) {
				SyRoleInfo si = (SyRoleInfo) pageList.getList().get(i);
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
	 * 查询授权菜单
	 * @return
	 */
	public String queryMenuForImpower(){
		//String mid=request.getParameter("mid");
		String roleId=request.getParameter("roleId");
		String jsonstr="[";
		Map<String, String> params = new HashMap<String, String>();
		//添加选中状态
		Map<String, String> params2 = new HashMap<String, String>();
		params2.put("relationType", "1");
		params2.put("userroleId", roleId);
		List<SyRoleMenuRelation> dblist = null;
		try {
			dblist=powerMngService.queryRoleRelation(params2);
		} catch (ServiceException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		List menuIds=new ArrayList();
		for (int i = 0; i < dblist.size(); i++) {
			menuIds.add(dblist.get(i).getId().getMenuId());
		}
		//end
		try {
			List<SyMenuList> allist=menuService.queryBusiMenu(params);
			//String jstr=JsonUtil.toJson(allist);
			List<SyMenuList> parent=new  ArrayList<SyMenuList>();
			for (int i = 0; i < allist.size(); i++) {
				SyMenuList role=allist.get(i);
				if("0".equals(role.getParentId().toString())){
					parent.add(role);
				}
			}
			for (int i = 0; i < parent.size(); i++) {
				SyMenuList menu=parent.get(i);
				jsonstr=jsonstr+"{\n" +  
                "    \"id\":"+menu.getMenuId()+",\n" +   
                "    \"text\":\"<a href='javaScript:void(0)' target='rightframe'>"+menu.getMenuName()+"</a>\",\n" ;
				if(menuIds.contains(menu.getMenuId())){
					jsonstr+= " \"checked\":\"true\",\n"; 
				}
				List<SyMenuList> sonNs=new  ArrayList<SyMenuList>();
				for (int j = 0; j < allist.size(); j++) {
					SyMenuList bean=allist.get(j);
					if((menu.getMenuId().toString()).equals(bean.getParentId().toString())){
						sonNs.add(bean);
					}
				}
				if(sonNs.size()>0){//exist son,add children node;
					jsonstr+="  \"state\":\"closed\",\n"+" \"children\":[";
					jsonstr=appendChild(jsonstr,menuIds, sonNs,allist);
					jsonstr+=" ]";
				}else{
					jsonstr+="  \"state\":\"open\"\n" ;
				}
				jsonstr+="  },";
				sonNs=null;
			}
			int end=jsonstr.length()-1;//去掉最后一个逗号  
            String json=jsonstr.substring(0,end);  
            json=json+"]";  
            response.setContentType("application/json;charset=gbk");  
            response.setCharacterEncoding("gbk");  
            PrintWriter pw;
            pw = response.getWriter();
			pw.write(json);  
	        pw.flush();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * append child node
	 * @param jsonstr
	 * @param menuIds
	 * @param child
	 * @param list
	 * @return
	 * @throws ServiceException
	 */
	public String appendChild(String jsonstr,List menuIds,List child,List<SyMenuList> list) throws ServiceException{
		for (int i = 0; i < child.size(); i++) {
			SyMenuList menu=(SyMenuList) child.get(i);
			if("30".equals(menu.getMenuType().toString())){//function button;
            	jsonstr=jsonstr+   "{\n" +   "    \"id\":"+menu.getMenuId()+",\n" +  "    \"text\":\""+menu.getMenuName()+"\",\n" ;
            	if(menuIds.contains(menu.getMenuId())){
            		jsonstr=jsonstr+  "  \"checked\":\"true\",\n" ;
            	}
            	jsonstr=jsonstr+"    \"attributes\":\""+menu.getMenuUrl()+"\",\n" +"    \"state\":\"open\"\n" +   "  },";
            }else if("10".equals(menu.getMenuType().toString())){//folder
            	jsonstr=jsonstr+"{\n" + "    \"id\":"+menu.getMenuId()+",\n" +   
                "    \"text\":\"<a href='javaScript:void(0)' target='rightframe'>"+menu.getMenuName()+"</a>\",\n" ;
				if(menuIds.contains(menu.getMenuId())){
					jsonstr+= " \"checked\":\"true\",\n"; 
				}
				//add children
				List<SyMenuList> sonNs=new  ArrayList<SyMenuList>();
				for (int j = 0; j < list.size(); j++) {
					SyMenuList bean=list.get(j);
					if((menu.getMenuId().toString()).equals(bean.getParentId().toString())){
						sonNs.add(bean);
					}
				}
				if(sonNs.size()>0){//exist son,add children node;
					jsonstr+="  \"state\":\"closed\",\n"+" \"children\":[";
					jsonstr=appendChild(jsonstr,menuIds, sonNs,list);
					jsonstr+=" ]";
				}else{
					jsonstr+="  \"state\":\"open\"\n" ;
				}
				jsonstr+="  },";
				//add end;
            }else{//menu
            	List wfList=menuService.queryBusiSonById(menu.getMenuId().toString());
            	if(wfList.size()>0){
            		jsonstr=jsonstr+"{\n" + "    \"id\":"+menu.getMenuId()+",\n" +   
	                "    \"text\":\"<a href='javaScript:void(0)' target='rightframe'>"+menu.getMenuName()+"</a>\",\n" ;
					if(menuIds.contains(menu.getMenuId())){
						jsonstr+= " \"checked\":\"true\",\n"; 
					}
					//add children
					List<SyMenuList> sonNs=new  ArrayList<SyMenuList>();
					for (int j = 0; j < list.size(); j++) {
						SyMenuList bean=list.get(j);
						if((menu.getMenuId().toString()).equals(bean.getParentId().toString())){
							sonNs.add(bean);
						}
					}
					if(sonNs.size()>0){//exist son,add children node;
						jsonstr+="  \"state\":\"closed\",\n"+" \"children\":[";
						jsonstr=appendChild(jsonstr,menuIds, sonNs,list);
						jsonstr+=" ]";
					}else{
						jsonstr+="  \"state\":\"closed\"\n" ;
					}
					jsonstr+="  },";
					//add end;
            	}else{
            		jsonstr=jsonstr+"{\n" + "    \"id\":"+menu.getMenuId()+",\n" +   
	                "    \"text\":\"<a href='javaScript:void(0)' target='rightframe'>"+menu.getMenuName()+"</a>\",\n" ;
					if(menuIds.contains(menu.getMenuId())){
						jsonstr+= " \"checked\":\"true\",\n"; 
					}
					jsonstr+=" \"state\":\"open\"\n" +   "  },";
            	}
            }  
		}
		int end=jsonstr.length()-1;//去掉最后一个逗号  
        String json=jsonstr.substring(0,end);
        jsonstr=json;
		return jsonstr;
	}
	/**
	 * 
	 * @return impower string
	 */
	public String queryMenuForImpowerTree(){
		//String mid=request.getParameter("mid");
		String jsonstr="[";
		Map<String, String> params = new HashMap<String, String>();
		try {
			List<MenuBean> allmenu=menuService.queryForTree(params);
			for (int i = 0; i < allmenu.size(); i++) {
				MenuBean menu=allmenu.get(i);
				String state=null;
				if("1".equals(menu.getISLEAF())){ state="    \"state\":\"open\"\n";
				}else{ state="    \"state\":\"closed\"\n";}
				jsonstr+="{\n" +  
                "    \"id\":"+menu.getMENUID()+",\n" +   
                "    \"text\":\"<a href='javaScript:void(0)' target='rightframe'>"+menu.getMENUNAME()+"</a>\",\n" + 
                state+
                //"    \"state\":\"closed\"\n" +   
                "  },"; 
			}
			int end=jsonstr.length()-1;
			String json=jsonstr.substring(0, end);
			json=json+"]";
			response.setContentType("application/json;charset=gbk");  
	        response.setCharacterEncoding("gbk");  
	        PrintWriter pw;
	        pw = response.getWriter();
			pw.write(json);  
	        pw.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * impower tree
	 * @return
	 */
	public String queryMenuForImpowerInuse(){
		String mid=request.getParameter("mid");
		String roleId=request.getParameter("roleId");
		String jsonstr="[";
		Map<String, String> params = new HashMap<String, String>();
		//添加选中状态
		Map<String, String> params2 = new HashMap<String, String>();
		params2.put("relationType", "1");
		params2.put("userroleId", roleId);
		List<SyRoleMenuRelation> dblist = null;
		try {
			dblist=powerMngService.queryRoleRelation(params2);
		} catch (ServiceException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		List menuIds=new ArrayList();
		for (int i = 0; i < dblist.size(); i++) {
			menuIds.add(dblist.get(i).getId().getMenuId());
		}
		//end
		try {
			if(Tool.isNotEmpty(mid)&&"0".equals(mid)){
				params.put("parentId", "0");
				List<SyMenuList> mlist=menuService.queryMenu(params);
				for (int i = 0; i < mlist.size(); i++) {
					SyMenuList menu=mlist.get(i);
					jsonstr=jsonstr+"{\n" +  
	                "    \"id\":"+menu.getMenuId()+",\n" +   
	                "    \"text\":\"<a href='javaScript:void(0)' target='rightframe'>"+menu.getMenuName()+"</a>\",\n" ;
					if(menuIds.contains(menu.getMenuId())){
						jsonstr+= "    \"checked\":\"true\",\n"; 
					}
					jsonstr+="    \"state\":\"closed\"\n" +   "  },";
				}
			}else{
				List wfNodes;
				wfNodes = menuService.querySonById(mid);
				for(int i=0;i<wfNodes.size();i++){  
	                SyMenuList wfNodesTo=(SyMenuList)wfNodes.get(i);
	                if("30".equals(wfNodesTo.getMenuType().toString())){//function button;
	                	jsonstr=jsonstr+   "{\n" +   "    \"id\":"+wfNodesTo.getMenuId()+",\n" +  "    \"text\":\""+wfNodesTo.getMenuName()+"\",\n" ;
	                	if(menuIds.contains(wfNodesTo.getMenuId())){
	                		jsonstr=jsonstr+  "  \"checked\":\"true\",\n" ;
	                	}
	                	jsonstr=jsonstr+"    \"attributes\":\""+wfNodesTo.getMenuUrl()+"\",\n" +"    \"state\":\"open\"\n" +   "  },";
	                }else if("10".equals(wfNodesTo.getMenuType().toString())){//folder
	                	jsonstr=jsonstr+"{\n" + "    \"id\":"+wfNodesTo.getMenuId()+",\n" +   
		                "    \"text\":\"<a href='javaScript:void(0)' target='rightframe'>"+wfNodesTo.getMenuName()+"</a>\",\n" ;
						if(menuIds.contains(wfNodesTo.getMenuId())){
							jsonstr+= " \"checked\":\"true\",\n"; 
						}
						jsonstr+=" \"state\":\"closed\"\n" +   "  },";
	                }else{//menu
	                	List wfList=menuService.querySonById(wfNodesTo.getMenuId().toString());
	                	if(wfList.size()>0){
	                		jsonstr=jsonstr+"{\n" + "    \"id\":"+wfNodesTo.getMenuId()+",\n" +   
			                "    \"text\":\"<a href='javaScript:void(0)' target='rightframe'>"+wfNodesTo.getMenuName()+"</a>\",\n" ;
							if(menuIds.contains(wfNodesTo.getMenuId())){
								jsonstr+= " \"checked\":\"true\",\n"; 
							}
							jsonstr+=" \"state\":\"closed\"\n" +   "  },";
	                	}else{
	                		jsonstr=jsonstr+"{\n" + "    \"id\":"+wfNodesTo.getMenuId()+",\n" +   
			                "    \"text\":\"<a href='javaScript:void(0)' target='rightframe'>"+wfNodesTo.getMenuName()+"</a>\",\n" ;
							if(menuIds.contains(wfNodesTo.getMenuId())){
								jsonstr+= " \"checked\":\"true\",\n"; 
							}
							jsonstr+=" \"state\":\"open\"\n" +   "  },";
	                	}
	                }  
	            }  
			}
			int end=jsonstr.length()-1;//去掉最后一个逗号  
            String json=jsonstr.substring(0,end);  
            json=json+"]";  
            response.setContentType("application/json;charset=gbk");  
            response.setCharacterEncoding("gbk");  
            PrintWriter pw;
            pw = response.getWriter();
			pw.write(json);  
	        pw.flush();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public RoleManageService getRoleMngService() {
		return roleMngService;
	}
	public void setRoleMngService(RoleManageService roleMngService) {
		this.roleMngService = roleMngService;
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
	public SyRoleInfo getRoleInfo() {
		return roleInfo;
	}
	public void setRoleInfo(SyRoleInfo roleInfo) {
		this.roleInfo = roleInfo;
	}
	public MenuService getMenuService() {
		return menuService;
	}
	public void setMenuService(MenuService menuService) {
		this.menuService = menuService;
	}
	public PowerMngService getPowerMngService() {
		return powerMngService;
	}
	public void setPowerMngService(PowerMngService powerMngService) {
		this.powerMngService = powerMngService;
	}
	
	
}
