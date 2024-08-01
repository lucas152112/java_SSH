package com.oim.stores.system.control;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.web.servlet.ModelAndView;

import com.oim.stores.framework.action.AHandleModelAndView;
import com.oim.stores.framework.action.IHandleModelAndView;
import com.oim.stores.system.domain.SyVirtualTeam;
import com.oim.stores.system.service.SyVirtualTeamService;
import com.sun.org.apache.commons.collections.MapUtils;

public class SyVirtualTeamAction extends AHandleModelAndView implements
	IHandleModelAndView {

    private SyVirtualTeamService syVirtualTeamService;

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

    public ModelAndView queryByParentId(HttpServletRequest request,
	    HttpServletResponse response, Map<String, String> singleMap)
	    throws Exception {
	BigDecimal parentTeamId = new BigDecimal(MapUtils.getString(singleMap,
		"parentTeamId"));
	Long storeType = 1L;
	List<SyVirtualTeam> list = syVirtualTeamService.queryByParnetTeamId(
		parentTeamId.longValue(), storeType);
	StringBuffer sb = new StringBuffer();
	for (SyVirtualTeam team : list) {
	    Long ct = syVirtualTeamService.queryCountByParnetTeamId(
		    team.getTeamId(), storeType);
	    if (!sb.toString().equals(""))
		sb.append(",");
	    sb.append("{\r\n\"id\":" + team.getTeamId());
	    sb.append(",\"text\":\"" + team.getTeamName() + "\"");
	    if (ct.intValue() > 0) {
		sb.append(",\"state\":\"closed\"");
	    } else {
		sb.append(",\"state\":\"open\"");
	    }
	    sb.append("}");
	}
	String content = "[" + sb.toString() + "]";
	return dispatchDefMv(content);
    }

    public ModelAndView queryTeamsByParentId(HttpServletRequest request,
	    HttpServletResponse response, Map<String, String> singleMap)
	    throws Exception {
	Long parentTeamId = MapUtils.getLong(singleMap, "parentTeamId");
	Long storeType = 1L;
	List<SyVirtualTeam> list = syVirtualTeamService
		.queryByParnetTeamId(parentTeamId);
	StringBuffer sb = new StringBuffer();
	for (SyVirtualTeam team : list) {
	    Long ct = syVirtualTeamService.queryCountByParnetTeamId(team
		    .getTeamId());
	    if (!sb.toString().equals(""))
		sb.append(",");
	    sb.append("{\r\n\"id\":" + team.getTeamId());
	    sb.append(",\"text\":\"" + team.getTeamName() + "\"");
	    if (ct.intValue() > 0) {
		sb.append(",\"state\":\"closed\"");
	    } else {
		sb.append(",\"state\":\"open\"");
	    }
	    sb.append("}");
	}
	String content = "[" + sb.toString() + "]";
	return dispatchDefMv(content);
    }
}
