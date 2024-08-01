package com.oim.stores.store.control;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.web.servlet.ModelAndView;

import com.oim.stores.common.Pagination;
import com.oim.stores.exception.ServiceException;
import com.oim.stores.framework.action.AHandleModelAndView;
import com.oim.stores.framework.action.IHandleModelAndView;
import com.oim.stores.framework.utils.Validator;
import com.oim.stores.store.domain.ShGroupTeam;
import com.oim.stores.store.domain.ShGroupTeamId;
import com.oim.stores.store.service.ShGroupTeamService;
import com.oim.stores.system.domain.SyVirtualTeam;
import com.oim.stores.system.service.SyVirtualTeamService;
import com.sun.org.apache.commons.collections.MapUtils;

public class ShGroupTeamAction extends AHandleModelAndView implements
	IHandleModelAndView {
    private ShGroupTeamService shGroupTeamService;
    private SyVirtualTeamService syVirtualTeamService;

    /**
     * @return the shGroupTeamService
     */
    public ShGroupTeamService getShGroupTeamService() {
	return shGroupTeamService;
    }

    /**
     * @param shGroupTeamService
     *            the shGroupTeamService to set
     */
    public void setShGroupTeamService(ShGroupTeamService shGroupTeamService) {
	this.shGroupTeamService = shGroupTeamService;
    }

    /**
     * @return the syVirtualTeamService
     */
    public SyVirtualTeamService getSyVirtualTeamService() {
	return syVirtualTeamService;
    }

    /**
     * @param syVirtualTeamService
     *            the syVirtualTeamService to set
     */
    public void setSyVirtualTeamService(
	    SyVirtualTeamService syVirtualTeamService) {
	this.syVirtualTeamService = syVirtualTeamService;
    }

    public ModelAndView save(HttpServletRequest request,
	    HttpServletResponse response, Map<String, String> singleMap)
	    throws Exception {
	try {
	    Long teamId = MapUtils.getLong(singleMap, "teamId");
	    String storeids = MapUtils.getString(singleMap, "storeids");
	    String storeidAry[] = storeids.split(",");
	    JSONObject json = new JSONObject();
	    json = validateSaveInput(singleMap);
	    if (json.size() > 0) {
		return dispatchDefMv(json);
	    }
	    for (int i = 0; i < storeidAry.length; i++) {
		Long storeId = Long.parseLong(storeidAry[i]);
		SyVirtualTeam team = syVirtualTeamService
			.queryTeamByStoreId(storeId);
		ShGroupTeamId id = new ShGroupTeamId();
		id.setSubTeamId(team.getTeamId());
		id.setTeamId(teamId);
		if (shGroupTeamService.queryById(id) == null) {
		    // json.accumulate(RETURN_CODE, resultErr);
		    // json.accumulate(RETURN_DESC, "保存失败,该管理关系已存在");
		    // return dispatchDefMv(json);
		    ShGroupTeam bean = new ShGroupTeam();
		    bean.setId(id);
		    shGroupTeamService.save(bean);
		}
	    }
	    json.accumulate(RETURN_CODE, resultOk);
	    json.accumulate(RETURN_DESC, "保存成功");
	    return dispatchDefMv(json);
	} catch (ServiceException e) {
	    throw e;
	}
    }

    private JSONObject validateSaveInput(Map<String, String> singleMap) {
	JSONObject json = new JSONObject();
	String teamId = MapUtils.getString(singleMap, "teamId");
	String storeids = MapUtils.getString(singleMap, "storeids");
	if (Validator.isNullOrEmpty(teamId) || !Validator.isInteger(teamId)) {
	    json.accumulate(RETURN_CODE, resultErr);
	    json.accumulate(RETURN_DESC, "聚类中心团队不能为空");
	    return json;
	}
	if (Validator.isNullOrEmpty(storeids)) {
	    json.accumulate(RETURN_CODE, resultErr);
	    json.accumulate(RETURN_DESC, "客户经理团队不能为空");
	    return json;
	}
	return json;
    }

    public ModelAndView delete(HttpServletRequest request,
	    HttpServletResponse response, Map<String, String> singleMap)
	    throws Exception {
	try {
	    Long teamId = MapUtils.getLong(singleMap, "teamId");
	    Long subTeamId = MapUtils.getLong(singleMap, "subTeamId");
	    JSONObject json = new JSONObject();
	    ShGroupTeamId id = new ShGroupTeamId();
	    id.setSubTeamId(subTeamId);
	    id.setTeamId(teamId);
	    shGroupTeamService.delete(id);
	    json.accumulate(RETURN_CODE, resultOk);
	    json.accumulate(RETURN_DESC, "删除成功");
	    return dispatchDefMv(json);
	} catch (ServiceException e) {
	    throw e;
	}
    }

    public ModelAndView queryForPage(HttpServletRequest request,
	    HttpServletResponse response, Map<String, String> singleMap)
	    throws Exception {
	int pageNumber = Integer.valueOf(request.getParameter("page"));
	int pageSize = Integer.valueOf(request.getParameter("rows"));
	if (pageNumber > 0) {
	    pageNumber = pageNumber - 1;
	}
	Pagination pagination = shGroupTeamService.queryForPage(singleMap,
		pageNumber, pageSize);
	List<Map<String, Object>> list = (List<Map<String, Object>>) pagination
		.getList();
	JSONArray jsonArray = new JSONArray();
	for (Map<String, Object> result : list) {
	    JSONObject json = JSONObject.fromObject(result);
	    jsonArray.add(json);
	}
	return dispatchPageMv(String.valueOf(pagination.getTotalRecord()),
		jsonArray.toString());
    }

    public ModelAndView queryForTeam(HttpServletRequest request,
	    HttpServletResponse response, Map<String, String> singleMap)
	    throws Exception {
	Long storeId = MapUtils.getLong(singleMap, "storeId");
	SyVirtualTeam team = syVirtualTeamService.queryTeamByStoreId(storeId);
	JSONObject json = JSONObject.fromObject(team);
	return dispatchDefMv(json);
    }

}
