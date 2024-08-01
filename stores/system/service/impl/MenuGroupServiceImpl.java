package com.oim.stores.system.service.impl;

import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.collections.MapUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import com.oim.stores.common.JSONUtils;
import com.oim.stores.common.Pagination;
import com.oim.stores.exception.DaoException;
import com.oim.stores.exception.ServiceException;
import com.oim.stores.framework.action.IHandleModelAndView;
import com.oim.stores.framework.constants.SystemKeyWord;
import com.oim.stores.framework.service.BaseService;
import com.oim.stores.framework.utils.BeanFactory;
import com.oim.stores.framework.utils.MenuTree;
import com.oim.stores.framework.web.RequestUtils;
import com.oim.stores.system.dao.MenuGroupDao;
import com.oim.stores.system.domain.MenuGroupBean;
import com.oim.stores.system.domain.SyMenuList;
/**
 * 权限分组管理service
 * @author Administrator
 *
 */
@SuppressWarnings("unchecked")
@Service("menuGroupService")
public class MenuGroupServiceImpl extends BaseService implements
	IHandleModelAndView {
    @SuppressWarnings("unused")
	private final static Logger logger = Logger
	    .getLogger(MenuGroupServiceImpl.class);
    private MenuGroupDao dao;//权限分组dao;

    
    
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    /**
     * 视图处理
     */
    public ModelAndView handleModelAndView(Map map, HttpServletRequest request,
	    HttpServletResponse response) throws Exception {
	PrintWriter out = response.getWriter();
	@SuppressWarnings("unused")
	String ctrl_msgpage = RequestUtils.getParamString(map,
		SystemKeyWord.CTRL_MSGPAGE);
	String ctrl_method = RequestUtils.getParamString(map,
		SystemKeyWord.CTRL_METHOD);
	Map<String, String> singleMap = RequestUtils.getNormalMap(map);
	@SuppressWarnings("unused")
	Map<String, List<String>> nameIdxMap = RequestUtils
		.getGroupByNameParam(map);
	// MenuGroupBean menugroup = null;
	// BeanSwapper.toJavaBean(menugroup, singleMap);
	dao = BeanFactory.getInstance(request, "menuGroupDao");
	// Object[] params = new Object[]{new Integer(10),new Integer(12)};
	ModelAndView mv = new ModelAndView();
	try {
	    if (actQueryForPage.equals(ctrl_method)) {
		// 分页
		mv.setViewName("./base_page/json.jsp");
		this.pageNation(singleMap, mv);
	    } else if (actDetail.equals(ctrl_method)) {
		// 详情
		String groupid = MapUtils.getString(singleMap, "groupid", "");
		MenuGroupBean menugroup = this.getMenuGroupBean(groupid);
		JSONObject jsonObject = JSONUtils.toJSONObject(menugroup);
		out.print(jsonObject.toString());
		out.flush();
		out.close();
		return null;
	    } else if (actCreate.equals(ctrl_method)) {
		// 创建
		MenuGroupBean menugroup = new MenuGroupBean();
		@SuppressWarnings("unused")
		String groupid = MapUtils.getString(singleMap, "groupid", "");
		String groupname = MapUtils.getString(singleMap, "groupname",
			"");
		// menugroup.setGroupid(Long.parseLong(groupid));
		menugroup.setGroupname(groupname);
		menugroup.setIsactive(1L);
		menugroup.setModtime(new Date());
		this.insertMenuGroup(menugroup);
		JSONObject jsonObject = new JSONObject();
		jsonObject.accumulate("result", "1");
		out.println(jsonObject.toString());
		out.flush();
		out.close();
		return null;
	    } else if (actModify.equals(ctrl_method)) {
		// 更新
		String groupid = MapUtils.getString(singleMap, "groupid", "");
		MenuGroupBean menugroup = this.getMenuGroupBean(groupid);
		String groupname = MapUtils.getString(singleMap, "groupname",
			"");
		menugroup.setGroupname(groupname);
		menugroup.setModtime(new Date());
		this.updateMenuGroup(menugroup);
		JSONObject jsonObject = new JSONObject();
		jsonObject.accumulate("result", "1");
		out.println(jsonObject.toString());
		out.flush();
		out.close();
		return null;
	    } else if (actDelete.equals(ctrl_method)) {
		// 删除
		String groupid = MapUtils.getString(singleMap, "groupid", "");
		MenuGroupBean menugroup = this.getMenuGroupBean(groupid);
		JSONObject jsonObject = new JSONObject();
		if (this.queryForMenuCount(groupid) > 0) {
		    jsonObject.accumulate("result", "0");
		} else {
		    this.deleteMenuGroup(menugroup);
		    jsonObject.accumulate("result", "1");
		}
		out.println(jsonObject.toString());
		out.flush();
		out.close();
		return null;
	    } else if (actQueryForJson.equals(ctrl_method)) {
		//查询列表数据返回json数组,不分页
		String json = this.queryForJson(singleMap);
		out.println(json);
		out.flush();
		out.close();
		return null;
	    } else if ("menutree".equalsIgnoreCase(ctrl_method)) {
		//加载菜单树
		String groupid = MapUtils.getString(singleMap, "groupid", "");
		String webname = MapUtils.getString(singleMap, "webname", "");
		String json = "[" + this.getTreeContent(groupid, webname) + "]";
		out.println(json);
		out.flush();
		out.close();
		return null;
	    } else if ("updategroupid".equalsIgnoreCase(ctrl_method)) {
		//批量更新菜单分类
		String groupid = MapUtils.getString(singleMap, "groupid", "");
		String menuids = MapUtils.getString(singleMap, "menuids", "");
		this.updateMenuGroupId(menuids, groupid);
		JSONObject jsonObject = new JSONObject();
		jsonObject.accumulate("result", "1");
		out.println(jsonObject.toString());
		out.flush();
		out.close();
		return null;
	    }else if("sso".equalsIgnoreCase(ctrl_method)){
//		System.out.println("测试单点");
//		SyUserInfoService syUserInfoService=BeanFactory.getInstance(request,"syUserInfoService");
//		SyUserInfo user = syUserInfoService.getUserInfoByNameAndpwd("admin123", "admin123");
//		request.getSession().setAttribute(Content.LOGIN_USER,user);
//		response.sendRedirect("./system/toMenuMain.html");
//		return null;
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return mv;
    }
    /**
     * 分页
     * @param singleMap
     * @param mv
     * @throws DaoException
     */
    public void pageNation(Map singleMap, ModelAndView mv) throws DaoException {
	int pageNumber = MapUtils.getIntValue(singleMap, this.pageNumberName);
	int pageSize = MapUtils.getIntValue(singleMap, this.pageSizeName);
	if (pageNumber > 0) {
	    pageNumber = pageNumber - 1;
	}
	Pagination pagination = dao.queryForPage(singleMap, pageNumber,
		pageSize);
	List list = pagination.getList();
	JSONArray jsonArray = new JSONArray();
	for (int i = 0; i < list.size(); i++) {
	    MenuGroupBean bean = (MenuGroupBean) list.get(i);
	    JSONObject jsonObject = JSONUtils.toJSONObject(bean);
	    jsonArray.add(jsonObject);
	}

	mv.addObject(SystemKeyWord.PAGE_JSON_ATTR, jsonArray.toString());
	mv.addObject(SystemKeyWord.PAGE_TOTAL_ATTR, pagination.getTotalRecord());
    }
    /**
     * get group
     * @param groupid
     * @return
     * @throws ServiceException
     */
    public MenuGroupBean getMenuGroupBean(String groupid)
	    throws ServiceException {
	try {
	    return dao.queryById(groupid);
	} catch (DaoException e) {
	    throw new ServiceException(e.getMessage(), e.getCause());
	}
    }
    /**
     * update group
     * @param bean
     * @throws ServiceException
     */
    public void updateMenuGroup(MenuGroupBean bean) throws ServiceException {
	try {
	    dao.updateGroup(bean);
	} catch (DaoException e) {
	    throw new ServiceException(e.getMessage(), e.getCause());
	}
    }
    /**
     * save group
     * @param bean
     * @throws ServiceException
     */
    public void insertMenuGroup(MenuGroupBean bean) throws ServiceException {
	try {
	    dao.saveGroup(bean);
	} catch (DaoException e) {
	    throw new ServiceException(e.getMessage(), e.getCause());
	}
    }
    /**
     * delete menu group
     * @param bean
     * @throws ServiceException
     */
    public void deleteMenuGroup(MenuGroupBean bean) throws ServiceException {
	try {
	    dao.deleteGroup(bean);
	} catch (DaoException e) {
	    throw new ServiceException(e.getMessage(), e.getCause());
	}
    }
    /**
     * 查询json信息
     * @param map
     * @return
     * @throws ServiceException
     */
    public String queryForJson(Map map) throws ServiceException {
	String ret = "";
	try {
	    List<MenuGroupBean> list = dao.query(map);
	    JSONArray jsonArray = new JSONArray();
	    for (MenuGroupBean bean : list) {
		JSONObject jsonObject = JSONUtils.toJSONObject(bean);
		jsonArray.add(jsonObject);
	    }
	    ret = jsonArray.toString();
	    return ret;
	} catch (DaoException e) {
	    throw new ServiceException(e.getMessage(), e.getCause());
	}
    }
    /**
     * 查询菜单数量
     * @param groupid
     * @return
     * @throws ServiceException
     */
    public int queryForMenuCount(String groupid) throws ServiceException {
	Map<String, String> params = new HashMap<String, String>();
	params.put("groupid", groupid);
	try {
	    return dao.queryForMenuCount(params);
	} catch (DaoException e) {
	    throw new ServiceException(e.getMessage(), e.getCause());
	}
    }
    /**
     * 菜单菜单列表
     * @param groupid
     * @param webname
     * @return
     * @throws ServiceException
     */
    public List<SyMenuList> queryForMenus(String groupid, String webname)
	    throws ServiceException {
	Map<String, String> params = new HashMap<String, String>();
	params.put("groupid", groupid);
	params.put("webName", webname);
	// params.put("parentId", "0");
	try {
	    return dao.queryMenus(params);
	} catch (DaoException e) {
	    throw new ServiceException(e.getMessage(), e.getCause());
	}
    }
    /**
     * 
     * @param groupid
     * @param webname
     * @return
     * @throws ServiceException
     */
    public String getTreeContent(String groupid, String webname)
	    throws ServiceException {
	Map<String, String> checkedMap = new HashMap<String, String>();
	List<SyMenuList> menuList = this.queryForMenus("", webname);
	List<SyMenuList> checkedList = this.queryForMenus(groupid, webname);
	for (SyMenuList menu : checkedList) {
	    String menuid = String.valueOf(menu.getMenuId());
	    checkedMap.put(menuid, menuid);
	}
	MenuTree mt = new MenuTree();
	String content = mt.getTreeContent(menuList, checkedMap, 0L);
	return content;
    }
    /**
     * 更新分组信息
     * @param menuids
     * @param groupid
     * @throws ServiceException
     */
    public void updateMenuGroupId(String menuids, String groupid)
	    throws ServiceException {
	try {
	    String[] ary = menuids.split(",");
	    for (int i = 0; i < ary.length; i++) {
		dao.updateMenuGroupId(ary[i], groupid);
	    }
	} catch (DaoException e) {
	    throw new ServiceException(e.getMessage(), e.getCause());
	}
    }

}
