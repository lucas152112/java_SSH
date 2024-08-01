package com.oim.stores.store.control;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.web.servlet.ModelAndView;

import com.oim.stores.common.Pagination;
import com.oim.stores.framework.action.AHandleModelAndView;
import com.oim.stores.framework.action.IHandleModelAndView;
import com.oim.stores.framework.utils.Validator;
import com.oim.stores.store.domain.ShDepositRelation;
import com.oim.stores.store.domain.ShDepositRelationId;
import com.oim.stores.store.service.ShDepositRelationService;
import com.oim.stores.system.service.SyVirtualTeamService;
import com.sun.org.apache.commons.collections.MapUtils;

public class ShDepositRelationAction extends AHandleModelAndView implements
	IHandleModelAndView {

    private ShDepositRelationService shDepositRelationService;

    private SyVirtualTeamService syVirtualTeamService;

    /**
     * @return the shDepositRelationService
     */
    public ShDepositRelationService getShDepositRelationService() {
	return shDepositRelationService;
    }

    /**
     * @param shDepositRelationService
     *            the shDepositRelationService to set
     */
    public void setShDepositRelationService(
	    ShDepositRelationService shDepositRelationService) {
	this.shDepositRelationService = shDepositRelationService;
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

    /**
     * 保存关联
     * 
     * @param request
     * @param response
     * @param singleMap
     * @return
     * @throws Exception
     *             Author zhuangjf Create On 2013-4-18 下午03:51:28
     */
    public ModelAndView save(HttpServletRequest request,
	    HttpServletResponse response, Map<String, String> singleMap)
	    throws Exception {
	Long teamId = MapUtils.getLong(singleMap, "teamId");
	Long storeId = MapUtils.getLong(singleMap, "storeId");
	JSONObject json = new JSONObject();
	json = validateSaveInput(singleMap);
	if (json.size() > 0) {
	    return dispatchDefMv(json);
	}
	ShDepositRelation bean = new ShDepositRelation();
	ShDepositRelationId id = new ShDepositRelationId();
	id.setStoreid(storeId);
	id.setTeamId(teamId);
	bean.setId(id);
	ShDepositRelation teampBean = shDepositRelationService.queryById(id);
	if (teampBean != null) {
	    json.accumulate(RETURN_CODE, resultErr);
	    json.accumulate(RETURN_DESC, "保存失败,商家托管关系已存在");
	    return dispatchDefMv(json);
	}
	boolean isExistsDepositTeam = shDepositRelationService
		.isExistsDepositTeam(storeId);
	if (isExistsDepositTeam) {
	    json.accumulate(RETURN_CODE, resultErr);
	    json.accumulate(RETURN_DESC, "保存失败,选择的商家已成为受理团队,不允许托管");
	    return dispatchDefMv(json);
	}
	Long teamStoreId = shDepositRelationService
		.queryDepositTeamStoreId(bean.getId().getStoreid());
	//
	Long tStoreId = syVirtualTeamService.queryTeamStoreId(teamId);
	if (teamStoreId.equals(0L) || teamStoreId.equals(tStoreId)) {
	    shDepositRelationService.save(bean);
	    json.accumulate(RETURN_CODE, resultOk);
	    json.accumulate(RETURN_DESC, "保存成功");
	} else {
	    json.accumulate(RETURN_CODE, resultErr);
	    json.accumulate(RETURN_DESC, "保存失败,不允许托管给不同商家的受理团队");
	}
	return dispatchDefMv(json);
    }

    private JSONObject validateSaveInput(Map<String, String> singleMap) {
	JSONObject json = new JSONObject();
	String teamId = MapUtils.getString(singleMap, "teamId");
	String storeId = MapUtils.getString(singleMap, "storeId");
	if (Validator.isNullOrEmpty(teamId) || !Validator.isInteger(teamId)) {
	    json.accumulate(RETURN_CODE, resultErr);
	    json.accumulate(RETURN_DESC, "团队不能为空");
	    return json;
	}
	if (Validator.isNullOrEmpty(storeId) || !Validator.isInteger(storeId)) {
	    json.accumulate(RETURN_CODE, resultErr);
	    json.accumulate(RETURN_DESC, "托管商家不能为空");
	    return json;
	}
	return json;
    }

    public ModelAndView queryForPage(HttpServletRequest request,
	    HttpServletResponse response, Map<String, String> singleMap)
	    throws Exception {
	int pageNumber = Integer.valueOf(request.getParameter("page"));
	int pageSize = Integer.valueOf(request.getParameter("rows"));
	if (pageNumber > 0) {
	    pageNumber = pageNumber - 1;
	}
	Pagination pagination = shDepositRelationService.queryForPage(
		singleMap, pageNumber, pageSize);
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

    public ModelAndView delete(HttpServletRequest request,
	    HttpServletResponse response, Map<String, String> singleMap)
	    throws Exception {
	Long teamId = MapUtils.getLong(singleMap, "teamId");
	Long storeId = MapUtils.getLong(singleMap, "storeId");
	JSONObject json = new JSONObject();
	json = validateSaveInput(singleMap);
	if (json.size() > 0) {
	    return dispatchDefMv(json);
	}
	ShDepositRelationId id = new ShDepositRelationId();
	id.setStoreid(storeId);
	id.setTeamId(teamId);
	shDepositRelationService.deleteById(id);
	json.accumulate(RETURN_CODE, resultOk);
	json.accumulate(RETURN_DESC, "删除成功");
	return dispatchDefMv(json);
    }
}
