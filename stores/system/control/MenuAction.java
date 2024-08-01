package com.oim.stores.system.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.oim.stores.common.BaseAction;
import com.oim.stores.common.PubFun;
import com.oim.stores.common.Tool;
import com.oim.stores.exception.ServiceException;
import com.oim.stores.store.domain.ShStoreUserRelation;
import com.oim.stores.system.domain.RoleMenuBean;
import com.oim.stores.system.domain.SyMenuList;
import com.oim.stores.system.service.MenuService;
import com.oim.stores.user.domain.SyUserInfo;
/**
 * 菜单管理action
 * @author yuyan
 *
 */
@SuppressWarnings({ "unchecked", "serial" })
public class MenuAction extends BaseAction {
	private String jsonDatas;//json数据
	private int total;//总数
	private String userName;//用户名
	private SyMenuList userMngInfo;//菜单
	private MenuService menuService;//菜单service
	/**
	 * to menu page;
	 * @return
	 */
	public String toMenuMain(){
		return SUCCESS;
	}
	/**
	 * query menu manage
	 * @return
	 */
	public String queryMenuForPage(){
		String mid=request.getParameter("mid");
		String roleId=request.getParameter("roleId");
		String jsonstr="[";
		Map<String, String> params = new HashMap<String, String>();
		//添加选中状态
		Map<String, String> params2 = new HashMap<String, String>();
		params2.put("relationType", "1");
		params2.put("userroleId", roleId);
		//end
		try {
			if(Tool.isNotEmpty(mid)&&"0".equals(mid)){
				params.put("parentId", "0");
				List<SyMenuList> mlist=menuService.queryMenu(params);
				for (int i = 0; i < mlist.size(); i++) {
					SyMenuList menu=mlist.get(i);
					jsonstr+="{\n" +  
	                "    \"id\":"+menu.getMenuId()+",\n" +   
	                "    \"text\":\"<a href='javaScript:void(0)' target='rightframe'>"+menu.getMenuName()+"</a>\",\n" +              
	                "    \"state\":\"closed\"\n" +   
	                "  },";
				}
			}else{
				List wfNodes;
				wfNodes = menuService.querySonById(mid);
				for(int i=0;i<wfNodes.size();i++){  
	                SyMenuList wfNodesTo=(SyMenuList)wfNodes.get(i);
	                if("30".equals(wfNodesTo.getMenuType().toString())){//function button;
	                	jsonstr=jsonstr+   "{\n" + "    \"id\":"+wfNodesTo.getMenuId()+",\n" +   
		                "    \"text\":\""+wfNodesTo.getMenuName()+"\",\n" +  "    \"attributes\":\""+wfNodesTo.getMenuType()+"\",\n" +
		                "    \"state\":\"open\"\n" +    "  },";
	                }else if("10".equals(wfNodesTo.getMenuType().toString())){//folder
	                	jsonstr=jsonstr+ "{\n" +   "  \"id\":"+wfNodesTo.getMenuId()+",\n" +  "    \"text\":\""+wfNodesTo.getMenuName()+"\",\n" + 
		                "    \"attributes\":\""+wfNodesTo.getMenuType()+"\",\n" +
		                "    \"state\":\"closed\"\n" +   "  },";
	                }else{//menu
	                	List wfList=menuService.querySonById(wfNodesTo.getMenuId().toString());
	                	if(wfList.size()>0){
	                		jsonstr=jsonstr+ 
		                	"{\n" +  
			                "    \"id\":"+wfNodesTo.getMenuId()+",\n" +   
			                "    \"text\":\""+wfNodesTo.getMenuName()+"\",\n" + 
			                "    \"attributes\":\""+wfNodesTo.getMenuType()+"\",\n" +
			                "    \"state\":\"closed\"\n" +   
			                "  },";
	                	}else{
	                		jsonstr=jsonstr+   "{\n" + "    \"id\":"+wfNodesTo.getMenuId()+",\n" +   
			                "    \"text\":\""+wfNodesTo.getMenuName()+"\",\n" +  "    \"attributes\":\""+wfNodesTo.getMenuType()+"\",\n" +
			                "    \"state\":\"open\"\n" +    "  },";
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
	/**
	 * 查询系统菜单列表
	 * @return
	 */
	public String queryForLeftMenu(){
		SyUserInfo user = (SyUserInfo) PubFun.getSession("loginUser");
		String jsonstr="[";
		
		try {//splice jsonStr;
			//query user role
			List<ShStoreUserRelation> rela=menuService.queryStoreUserById(user.getUserId().toString());
			ShStoreUserRelation roleId=rela.get(0);
			Map<String, String> params1 = new HashMap<String, String>();
			params1.put("userroleId", roleId.getUserStoreRoleId().toString());
			params1.put("relationType", "1");
			//query role de menu
			List<RoleMenuBean> list=menuService.queryRelation(params1);
			List<RoleMenuBean> parent=new  ArrayList<RoleMenuBean>();
			for (int i = 0; i < list.size(); i++) {
				RoleMenuBean role=list.get(i);
				if("0".equals(role.getParentId().toString())){
					parent.add(role);
				}
			}
			for (int i = 0; i < parent.size(); i++) {
				RoleMenuBean menu=parent.get(i);
				jsonstr+="<div title='"+menu.getMenuName()+"' style='padding: 10px;'>";
//				jsonstr+="{\n" +  
//                "    \"id\":"+menu.getMenuId()+",\n" +   
//                "    \"text\":\"<a href='javaScript:void(0)' target='rightframe'>"+menu.getMenuName()+"</a>\",\n" +  "    \"state\":\"closed\"\n" +     "  },";
				Map<String, String> params2 = new HashMap<String, String>();
				params2.put("parentId", menu.getMenuId().toString());
				List<SyMenuList> sonNode=menuService.querySonById(menu.getMenuId().toString());
				if(sonNode.size()>0){
					jsonstr=appendChild(jsonstr, sonNode);
				}
				jsonstr+="</div>";
			}
			//int end=jsonstr.length()-1;//去掉最后一个逗号  
            String json=jsonstr;//.substring(0,end);  
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
	 * append child node;
	 * @param jsonstr
	 * @param child
	 * @return
	 * @throws ServiceException
	 */
	public String appendChild(String jsonstr,List child) throws ServiceException{
		for (int i = 0; i < child.size(); i++) {
			SyMenuList menu=(SyMenuList) child.get(i);
			if("20".equals(menu.getMenuType().toString())){
				jsonstr+="<div>";
				jsonstr+="<a href='${APP_PATH}'"+menu.getMenuUrl()+"' target='rightframe'>"+menu.getMenuName()+"</a>";
				jsonstr+="</div>";
			}else if("10".equals(menu.getMenuType().toString())){
				jsonstr+="<div title='"+menu.getMenuName()+"' style='padding: 10px;'>";
				List sonlist=menuService.querySonById(menu.getMenuId().toString());
				if(sonlist.size()>0){ 
					jsonstr=appendChild(jsonstr,sonlist);
				}
				jsonstr+="</div>";
			}
		}
		return jsonstr;
	}
	/**
	 * append child ;
	 * @param jsonstr
	 * @param child
	 * @return
	 * @throws ServiceException
	 */
	public String appendChild2(String jsonstr,List child) throws ServiceException{
		for (int i = 0; i < child.size(); i++) {
			SyMenuList menu=(SyMenuList) child.get(i);
			if("20".equals(menu.getMenuType().toString())){
				jsonstr=jsonstr+   "{\n" + "    \"id\":"+menu.getMenuId()+",\n" +   
                "    \"text\":\""+menu.getMenuName()+"\",\n" +  "    \"attributes\":\""+menu.getMenuType()+"\",\n" +
                "    \"state\":\"open\"\n" +    "  },";
			}else if("10".equals(menu.getMenuType().toString())){
				jsonstr=jsonstr+ "{\n" +   "  \"id\":"+menu.getMenuId()+",\n" +  "    \"text\":\""+menu.getMenuName()+"\",\n" + 
                "    \"attributes\":\""+menu.getMenuType()+"\",\n" +
                "    \"state\":\"closed\"\n" +   "  },";
				List sonlist=menuService.querySonById(menu.getMenuId().toString());
				if(sonlist.size()>0){ 
					appendChild(jsonstr,sonlist);
				}
			}
		}
		return jsonstr;
	}
	/**
	 * old query list;
	 * @return
	 */
	public String queryMenuForPageOld(){
		String mid=request.getParameter("mid");
		String jsonstr="[";
		Map<String, String> params = new HashMap<String, String>();
		if(Tool.isNotEmpty(mid)&&"0".equals(mid)){
			try {
				params.put("parentId", "0");
				List<SyMenuList> mlist=menuService.queryMenu(params);
				for (int i = 0; i < mlist.size(); i++) {
					SyMenuList menu=mlist.get(i);
					jsonstr+="{\n" +  
	                "    \"id\":"+menu.getMenuId()+",\n" +   
	                "    \"text\":\"<a href='javaScript:void(0)' target='rightframe'>"+menu.getMenuName()+"</a>\",\n" +              
	                "    \"state\":\"closed\"\n" +   
	                "  },"; 
				}
				int end=jsonstr.length()-1;
				String json=jsonstr.substring(0, end);
				json=json+"]";
				response.setContentType("application/json;charset=gbk");  
		        response.setCharacterEncoding("gbk");  
		        PrintWriter pw;
				try {
					pw = response.getWriter();
					pw.write(json);  
			        pw.flush();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}  
			} catch (ServiceException e) {
				e.printStackTrace();
			}
		}else{
			List wfNodes;
			try {
				wfNodes = menuService.querySonById(mid);
				for(int i=0;i<wfNodes.size();i++){  
	                SyMenuList wfNodesTo=(SyMenuList)wfNodes.get(i);
	                //if node is leaf {}else {}
	                if(Tool.isNotEmpty(wfNodesTo.getMenuUrl())){
	                	jsonstr=jsonstr+  
		                "{\n" +  
		                "    \"id\":"+wfNodesTo.getMenuId()+",\n" +   
		                "    \"text\":\""+wfNodesTo.getMenuName()+"\",\n" +  
		                "    \"attributes\":\""+wfNodesTo.getMenuUrl()+"\",\n" +
		                "    \"state\":\"open\"\n" +   
		                "  },";
	                }else{
	                	jsonstr=jsonstr+ 
	                	"{\n" +  
		                "    \"id\":"+wfNodesTo.getMenuId()+",\n" +   
		                "    \"text\":\""+wfNodesTo.getMenuName()+"\",\n" + 
		                //"    \"attributes\":\""+wfNodesTo.getMenuUrl()+"\",\n" +
		                "    \"state\":\"closed\"\n" +   
		                "  },";
	                }  
//	                if(Tool.isNotEmpty(wfNodesTo.getMenuUrl())){
//	                	jsonstr=jsonstr+  
//		                "{\n" +  
//		                "    \"id\":"+wfNodesTo.getMenuId()+",\n" +   
//		                "    \"text\":\"<a href='" + request.getContextPath()+wfNodesTo.getMenuUrl()+
//		                "' target='rightframe'>"+wfNodesTo.getMenuName()+"</a>\",\n" +
//		               // "/system/queryMenuForPage.html?mid="+mid+"&nodeId="+wfNodesTo.getMenuId()+"' target='rightframe'>"+wfNodesTo.getMenuName()+"("+wfNodesTo.getMenuId()+")</a>\",\n" +        
//		                "    \"state\":\"open\"\n" +   
//		                "  },";
//	                }else{
//	                	jsonstr=jsonstr+  
//		                "{\n" +  
//		                "    \"id\":"+wfNodesTo.getMenuId()+",\n" +   
//		                "    \"text\":\"<a href='" + request.getContextPath()+
//		                "/system/queryMenuForPage.html?mid="+mid+"&nodeId="+wfNodesTo.getMenuId()+"' target='rightframe'>"+wfNodesTo.getMenuName()+"</a>\",\n" +        
//		                "    \"state\":\"closed\"\n" +   
//		                "  },";
//	                }  
	            }  
	            int end=jsonstr.length()-1;//去掉最后一个逗号  
	            String json=jsonstr.substring(0,end);  
	            json=json+"]";  
	        response.setContentType("application/json;charset=gbk");  
	        response.setCharacterEncoding("gbk");  
	        PrintWriter pw;
			try {
				pw = response.getWriter();
				pw.write(json);  
		        pw.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}   
			} catch (ServiceException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}  
		}
		return null;
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
	public SyMenuList getUserMngInfo() {
		return userMngInfo;
	}
	public void setUserMngInfo(SyMenuList userMngInfo) {
		this.userMngInfo = userMngInfo;
	}

	public MenuService getMenuService() {
		return menuService;
	}

	public void setMenuService(MenuService menuService) {
		this.menuService = menuService;
	}
	
}
