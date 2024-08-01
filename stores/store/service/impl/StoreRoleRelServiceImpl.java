package com.oim.stores.store.service.impl;

import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.beanutils.MethodUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import com.oim.stores.common.Content;
import com.oim.stores.common.JSONUtils;
import com.oim.stores.common.Pagination;
import com.oim.stores.exception.DaoException;
import com.oim.stores.exception.ServiceException;
import com.oim.stores.framework.action.IHandleModelAndView;
import com.oim.stores.framework.constants.SystemKeyWord;
import com.oim.stores.framework.service.BaseService;
import com.oim.stores.framework.utils.BeanConvert;
import com.oim.stores.framework.utils.BeanFactory;
import com.oim.stores.framework.web.RequestUtils;
import com.oim.stores.store.dao.StoreInfoDao;
import com.oim.stores.store.dao.StoreRoleRelDao;
import com.oim.stores.store.domain.ShStoreInfo;
import com.oim.stores.store.domain.ShStoreRoleRel;
import com.oim.stores.store.domain.ShStoreRoleRelPk;
import com.oim.stores.system.dao.RoleManageDao;
import com.oim.stores.system.domain.SyRoleInfo;
import com.oim.stores.user.domain.SyUserInfo;
/**
 * 商家角色关联service实现类
 * @author Administrator
 *
 */
@SuppressWarnings("unchecked")
@Service("storeRoleRelService")
public class StoreRoleRelServiceImpl extends BaseService implements
	IHandleModelAndView {
    @SuppressWarnings("unused")
	private final static Logger logger = Logger
	    .getLogger(StoreRoleRelServiceImpl.class);
    private StoreRoleRelDao dao;
    private Map<String, String> singleMap;
    @SuppressWarnings("unused")
	private Map<String, List<String>> nameIdxMap;
    private HttpServletRequest request;
    @SuppressWarnings("unused")
	private PrintWriter out;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public ModelAndView handleModelAndView(Map map, HttpServletRequest request,
	    HttpServletResponse response) throws Exception {
	this.request = request;
	this.out = response.getWriter();
	@SuppressWarnings("unused")
	String ctrl_msgpage = RequestUtils.getParamString(map,
		SystemKeyWord.CTRL_MSGPAGE);
	String ctrl_method = RequestUtils.getParamString(map,
		SystemKeyWord.CTRL_METHOD);
	singleMap = RequestUtils.getNormalMap(map);
	nameIdxMap = RequestUtils.getGroupByNameParam(map);
	dao = BeanFactory.getInstance(request, "storeRoleRelDao");
	ModelAndView mv = (ModelAndView) MethodUtils.invokeMethod(this,
		ctrl_method, null);
	return mv;
    }

    public ModelAndView queryForPage() throws ServiceException {
	int pageNumber = MapUtils.getIntValue(singleMap, this.pageNumberName);
	int pageSize = MapUtils.getIntValue(singleMap, this.pageSizeName);
	if (pageNumber > 0) {
	    pageNumber = pageNumber - 1;
	}
	try {
	    Pagination pagination = dao.queryForPage(singleMap, pageNumber,
		    pageSize);
	    List list = pagination.getList();
	    JSONArray jsonArray = new JSONArray();
	    for (int i = 0; i < list.size(); i++) {
		Object[] ary = (Object[]) list.get(i);
		ShStoreRoleRel bean = (ShStoreRoleRel) ary[0];
		Map<String, Object> row = new HashMap<String, Object>();
		row.put("roleid", bean.getId().getRoleid().toString());
		row.put("storeid", bean.getId().getStoreid().toString());
		row.put("rolename", bean.getSyroleinfo().getRoleName());
		row.put("storename", bean.getShstoreinfo().getStoreName());
		JSONObject jsonObject = BeanConvert.toJSONObject(row);
		jsonArray.add(jsonObject);
	    }
	    ModelAndView mv = new ModelAndView();
	    mv.setViewName("./base_page/json.jsp");
	    mv.addObject(SystemKeyWord.PAGE_JSON_ATTR, jsonArray.toString());
	    mv.addObject(SystemKeyWord.PAGE_TOTAL_ATTR,
		    pagination.getTotalRecord());
	    return mv;
	} catch (DaoException e) {
	    throw new ServiceException(e.getMessage(), e.getCause());
	}
    }

    public ModelAndView queryRoles() throws ServiceException {
	RoleManageDao roledao = BeanFactory.getInstance(request, "roleMngDao");
	Map<String, String> params = new HashMap<String, String>();
	try {
	    List<SyRoleInfo> list = roledao.queryBusi(params);
	    JSONArray jsonArray = new JSONArray();
	    for (SyRoleInfo role : list) {
		JSONObject json = JSONUtils.toJSONObject(role);
		jsonArray.add(json);
	    }
	    ModelAndView mv = new ModelAndView();
	    mv.setViewName("./base_page/jsondata.jsp");
	    mv.addObject(SystemKeyWord.PAGE_JSON_ATTR, jsonArray.toString());
	    return mv;
	} catch (DaoException e) {
	    throw new ServiceException(e.getMessage(), e.getCause());
	}
    }

    public ModelAndView queryStore() throws ServiceException {
	@SuppressWarnings("unused")
	StoreInfoDao storedao = BeanFactory
		.getInstance(request, "storeInfoDao");
	try {
	    String storeName = MapUtils.getString(singleMap, "storename");
	    Map<String, String> params = new HashMap<String, String>();
	    params.put("storeName", storeName);
	    List<ShStoreInfo> list = dao.queryStore(params);
	    JSONArray jsonArray = new JSONArray();
	    for (ShStoreInfo bean : list) {
		JSONObject json = JSONUtils.toJSONObject(bean);
		jsonArray.add(json);
	    }
	    ModelAndView mv = new ModelAndView();
	    mv.setViewName("./base_page/jsondata.jsp");
	    mv.addObject(SystemKeyWord.PAGE_JSON_ATTR, jsonArray.toString());
	    return mv;
	} catch (DaoException e) {
	    throw new ServiceException(e.getMessage(), e.getCause());
	}
    }

    public ModelAndView saveStoreRoleRel() throws ServiceException {
	try {
	    SyUserInfo user = (SyUserInfo) request.getSession().getAttribute(
		    Content.LOGIN_USER);
	    String storeid = MapUtils.getString(singleMap, "storeid");
	    String roleid = MapUtils.getString(singleMap, "roleid");
	    ShStoreRoleRel bean = null;
	    bean = dao.queryById(storeid, roleid);
	    JSONObject jsonObject = new JSONObject();
	    if (bean == null) {
		bean = new ShStoreRoleRel();
		bean.setId(new ShStoreRoleRelPk(Long.parseLong(roleid), Long
			.parseLong(storeid)));
		bean.setState(1L);
		bean.setUpdateddate(new Date());
		bean.setUpdateduser(user.getUserId());
		dao.saveStoreRoleRel(bean);
		jsonObject.accumulate("result", "1");
	    } else {
		jsonObject.accumulate("result", "0");
	    }
	    ModelAndView mv = new ModelAndView();
	    mv.setViewName("./base_page/jsondata.jsp");
	    mv.addObject(SystemKeyWord.PAGE_JSON_ATTR, jsonObject.toString());
	    return mv;
	} catch (DaoException e) {
	    throw new ServiceException(e.getMessage(), e.getCause());
	}
    }

    public ModelAndView deleteStoreRoleRel() throws ServiceException {
	try {
	    String storeid = MapUtils.getString(singleMap, "storeid");
	    String roleid = MapUtils.getString(singleMap, "roleid");
	    ShStoreRoleRel bean = dao.queryById(storeid, roleid);
	    dao.deleteStoreRoleRel(bean);
	    JSONObject jsonObject = new JSONObject();
	    jsonObject.accumulate("result", "1");
	    ModelAndView mv = new ModelAndView();
	    mv.setViewName("./base_page/jsondata.jsp");
	    mv.addObject(SystemKeyWord.PAGE_JSON_ATTR, jsonObject.toString());
	    return mv;
	} catch (DaoException e) {
	    throw new ServiceException(e.getMessage(), e.getCause());
	}
    }
}
