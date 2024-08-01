package com.oim.stores.system.control;

import java.io.IOException;
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
import com.oim.stores.system.domain.SyMenuList;
import com.oim.stores.system.domain.UserMenuBean;
import com.oim.stores.system.service.PowerMngService;
/**
 * 权限action
 * @author yuyan
 *
 */
@SuppressWarnings("serial")
public class PowerMngAction extends BaseAction {
	private PowerMngService powerMngService;//权限service
	private String jsonDatas;//json数据
	private int total;//总数
	private String userName;//用户名
	private UserMenuBean umenuInfo;//用户菜单bean;
	/**
	 * to the menu of role page;
	 * @return
	 */
	public String toRoleMenuMain(){
		String html=htmlString();
		request.setAttribute("htmlStr", html);
		return SUCCESS;
	}
	/**
	 * query role info ;
	 * @return
	 */
	public String queryRoleMenuForPage(){
		PageRequest pageRequest = new PageRequest();
		int pageNumber = Integer.valueOf(request.getParameter("page"));
		int pageSize = Integer.valueOf(request.getParameter("rows"));
		String menuId=request.getParameter("menuId");

		if (pageNumber > 0) {
			pageNumber = pageNumber - 1;
		}
		pageRequest.setPageNumber(pageNumber);
		pageRequest.setPageSize(pageSize);
		// 查询条件
		Map<String, String> params = new HashMap<String, String>();
		if(Tool.isNotEmpty(menuId)){
			params.put("menuId", menuId);
		}
		try {
			PageResponse pageList = powerMngService.queryRoleForPage(params, pageNumber, pageSize);
			JSONArray jsonArray = new JSONArray();
			for (int i = 0; i < pageList.getList().size(); i++) {
				UserMenuBean si = (UserMenuBean) pageList.getList().get(i);
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
	 * to user power page;
	 * @return
	 */
	public String toUserMenuMain(){
		String html=htmlString();
		request.setAttribute("htmlStr", html);
		return SUCCESS;
	}
	/**
	 * query user power;
	 * @return
	 */
	public String queryUserMenuForPage(){
		PageRequest pageRequest = new PageRequest();
		int pageNumber = Integer.valueOf(request.getParameter("page"));
		int pageSize = Integer.valueOf(request.getParameter("rows"));
		String menuId=request.getParameter("menuId");

		if (pageNumber > 0) {
			pageNumber = pageNumber - 1;
		}
		pageRequest.setPageNumber(pageNumber);
		pageRequest.setPageSize(pageSize);
		// 查询条件
		Map<String, String> params = new HashMap<String, String>();
		if(Tool.isNotEmpty(menuId)){
			params.put("menuId", menuId);
		}
		try {
			PageResponse pageList = powerMngService.queryForPage(params, pageNumber, pageSize);
			JSONArray jsonArray = new JSONArray();
			for (int i = 0; i < pageList.getList().size(); i++) {
				UserMenuBean si = (UserMenuBean) pageList.getList().get(i);
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
	 * 
	 * @return html 字符串
	 */
	public String htmlString(){
		String mtree="<ul id='mtree' class='filetree treeview-famfamfam' style='padding-left: 0px;margin-left: 0px;padding-top: 0px;margin-top: 0px;'>";
				//"<span class='folder'>"+"menu"+"</span>";
		mtree+=createTree();
		mtree+="</ul>";
		return mtree;
	}
	/**
	 * 
	 * @return tree 字符串
	 */
	private String createTree(){
		Map<String, String> params = new HashMap<String, String>();
		String tree="";
		try {
			params.put("parentId", "0");
			List<SyMenuList> rootlist=powerMngService.queryMenu(params);
			Map<String, String> params2 = new HashMap<String, String>();
			List<SyMenuList> sonlist=powerMngService.querySonMenu(params2);
			if(rootlist.size()>0){
				for (int i = 0; i < rootlist.size(); i++) {
					SyMenuList menu=rootlist.get(i);
					tree+="<li style='padding-left: 0px;margin-left: 0px;padding-top: 0px;margin-top: 0px;'><span class='folder'>"+menu.getMenuName()+"</span>";
					tree+="<ul>";
					for (int j = 0; j < sonlist.size(); j++) {
						SyMenuList sub=sonlist.get(j);
						String menuId=menu.getMenuId()+"";
						String parentId=sub.getParentId()+"";
						if(menuId.equals(parentId)){
							tree+="<li><span class='file'>"
								+"<a href='#' onclick='menuClick("+sub.getMenuId()+");'>"+sub.getMenuName()+"</a></span></li>";
						}
					}
					tree+="</ul></li>";
				}
			}
			
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tree;
	}
	/**
	 * 
	 * @return 菜单列表字符串
	 */
	public String menuList(){
		 try {
			  response.getWriter().print(generateJsonString());
			  } catch (IOException e) {
			   // TODO Auto-generated catch block
			   e.printStackTrace();
			  }
		return null;
	}
	
	/**
	  * 产生jsonstring
	  */ 
	public String generateJsonString() {       
		 System.out.println("generateJsonString start .....");   
	     String id = request.getParameter("root");  
	     System.out.println("id:"+id);  
	     String output = "";  
	     if (id == null) {  
	        return "";  
	     } else if (id.equalsIgnoreCase("source")) {  
	       output = generateInitTreeString();  
	        } else {  
	      output = generateTreeChildNodeString(id);  
	  }  
	     System.out.println("generateJsonString end,return:"+output);  
	       return output;  
	  }  


	  /**
	    * 产生子节点jsonstring 
	  **/ 
	 private String generateTreeChildNodeString(String departmentId) {  
	     StringBuilder jsonString = new StringBuilder();  
	      // 查询条件
		 Map<String, String> params = new HashMap<String, String>();
	     jsonString.append("[");  
	     List<SyMenuList> rootlist;
		try {
			rootlist = powerMngService.queryMenu(params);
			if(rootlist.isEmpty())
				return "";
			int i = 0;
			for (SyMenuList menu : rootlist) {  
		         if (i > 0) {  
		              jsonString.append(",");  
		           }  
		           jsonString.append(toJSONString(menu));  
		           i++;  
		     }
			jsonString.append("]"); 
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}      
	   return jsonString.toString();  
	 }  
	 /**
	  * 拼接init
	  * @return
	  */
	 private String generateInitTreeString() {  
	    StringBuilder jsonString = new StringBuilder(); 
	    Map<String, String> params = new HashMap<String, String>();
	    jsonString.append("[");  
	    List<SyMenuList> rootlist;
		try {
			rootlist = powerMngService.queryMenu(params);
			int i=0;
			for(SyMenuList menu:rootlist){
				if(i>0){
					jsonString.append(",");
				}
				jsonString.append(toJSONString(menu));  
		        i++;
			}
			jsonString.append("]"); 
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		return jsonString.toString(); 
	 }   
   /**
   * 将对象转换成String
   * @param menu
   * @return
   */
  private String toJSONString(SyMenuList menu) {  
     StringBuilder sb = new StringBuilder();  
     sb.append(" {");  
     sb.append("  \"text\": \"" + generateLinkString(menu) + "\"");  
//     if (umMngService.getDepartmentRootsById(menu.getMenuId()).size()>0) {  
//    	 sb.append(",  \"id\":\"" + menu.getMenuId() + "\"");  
//            sb.append(",  \"hasChildren\":true");  
//     }  
     sb.append(" }");  
     return sb.toString();  
   } 
  /**
   * 设置超链接
   * @param category
   * @return
   */
  private String generateLinkString(SyMenuList  menu) {  
     String link = "<a href='sys_department!dedaoDepartmentById.action?id="+menu.getMenuId()+"' target='parent.rightMenu' >" + menu.getMenuName() + "</a>";  
      //link = category.getCatName();  
     return link;  
  }  
	
	
	public PowerMngService getPowerMngService() {
		return powerMngService;
	}
	public void setPowerMngService(PowerMngService powerMngService) {
		this.powerMngService = powerMngService;
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
	public UserMenuBean getUmenuInfo() {
		return umenuInfo;
	}
	public void setUmenuInfo(UserMenuBean umenuInfo) {
		this.umenuInfo = umenuInfo;
	}
	
	
}
