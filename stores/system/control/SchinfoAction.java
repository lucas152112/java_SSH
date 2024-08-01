package com.oim.stores.system.control;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.oim.stores.common.BaseAction;
import com.oim.stores.common.JSONUtils;
import com.oim.stores.common.PageRequest;
import com.oim.stores.common.PageResponse;
import com.oim.stores.exception.ServiceException;
import com.oim.stores.system.domain.SchInfoBean;
import com.oim.stores.system.service.SchInfoService;
/**
 * 定时任务管理action
 * @author Administrator
 *
 */
@SuppressWarnings("serial")
public class SchinfoAction extends BaseAction {
    private SchInfoService schinfoService;//任务service
    private String schid;//任务id
    private String interval;//间隔
    private String schname;//任务名
    private String status;//状态
    private String schtime;//开始时间
    private String schtimeend;//结束时间
    private String unittype;//单位
    private String memo;//备注
    private String jsonDatas;//json数据
    private int total;//总数
    /**
     * @return the unittype
     */
    public String getUnittype() {
        return unittype;
    }
    /**
     * @param unittype the unittype to set
     */
    public void setUnittype(String unittype) {
        this.unittype = unittype;
    }

    /**
     * @return the interval
     */
    public String getInterval() {
        return interval;
    }

    /**
     * @param interval the interval to set
     */
    public void setInterval(String interval) {
        this.interval = interval;
    }

    /**
     * @return the schinfoService
     */
    public SchInfoService getSchinfoService() {
        return schinfoService;
    }

    /**
     * @param schinfoService the schinfoService to set
     */
    public void setSchinfoService(SchInfoService schinfoService) {
        this.schinfoService = schinfoService;
    }
    /**
     * @return the schid
     */
    public String getSchid() {
	return schid;
    }

    /**
     * @param schid
     *            the schid to set
     */
    public void setSchid(String schid) {
	this.schid = schid;
    }

    /**
     * @return the schname
     */
    public String getSchname() {
	return schname;
    }

    /**
     * @param schname
     *            the schname to set
     */
    public void setSchname(String schname) {
	this.schname = schname;
    }

    /**
     * @return the status
     */
    public String getStatus() {
	return status;
    }

    /**
     * @param status
     *            the status to set
     */
    public void setStatus(String status) {
	this.status = status;
    }

    /**
     * @return the schtime
     */
    public String getSchtime() {
	return schtime;
    }

    /**
     * @param schtime
     *            the schtime to set
     */
    public void setSchtime(String schtime) {
	this.schtime = schtime;
    }

    /**
     * @return the schtimeend
     */
    public String getSchtimeend() {
	return schtimeend;
    }

    /**
     * @param schtimeend
     *            the schtimeend to set
     */
    public void setSchtimeend(String schtimeend) {
	this.schtimeend = schtimeend;
    }

    /**
     * @return the memo
     */
    public String getMemo() {
	return memo;
    }

    /**
     * @param memo
     *            the memo to set
     */
    public void setMemo(String memo) {
	this.memo = memo;
    }
    /**
     * to 任务配置页
     * @return
     */
    public String toSchinfoMain() {
	System.out.println("任务调度配置!");
	return SUCCESS;
    }
    /**
     * 查询任务列表
     * @return
     */
    public String querySchForPage() {
	PageRequest pageRequest = new PageRequest();
	int pageNumber = Integer.valueOf(request.getParameter("page"));
	int pageSize = Integer.valueOf(request.getParameter("rows"));

	if (pageNumber > 0) {
	    pageNumber = pageNumber - 1;
	}
	Map<String, String> params = new HashMap<String, String>();
	try {
	    PageResponse pageList = schinfoService.queryForPage(params,
		    pageNumber, pageSize);
	    JSONArray jsonArray = new JSONArray();
	    for (int i = 0; i < pageList.getList().size(); i++) {
		SchInfoBean si = (SchInfoBean) pageList.getList().get(i);
		JSONObject jsonObject = JSONUtils.toJSONObject(si);
		jsonArray.add(jsonObject);
	    }
	    jsonDatas = jsonArray.toString();
	    System.out.println(jsonDatas);
	    total = pageList.getTotalRecord();
	} catch (ServiceException e) {
	    e.printStackTrace();
	}
	pageRequest.setPageNumber(pageNumber);
	pageRequest.setPageSize(pageSize);
	return "json";
    }

    /**
     * @return the jsonDatas
     */
    public String getJsonDatas() {
        return jsonDatas;
    }

    /**
     * @param jsonDatas the jsonDatas to set
     */
    public void setJsonDatas(String jsonDatas) {
        this.jsonDatas = jsonDatas;
    }

    /**
     * @return the total
     */
    public int getTotal() {
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(int total) {
        this.total = total;
    }

}
