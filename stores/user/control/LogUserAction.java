package com.oim.stores.user.control;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.oim.stores.common.BaseAction;
import com.oim.stores.common.Content;
import com.oim.stores.common.JSONUtils;
import com.oim.stores.common.PageRequest;
import com.oim.stores.common.PageResponse;
import com.oim.stores.exception.ServiceException;
import com.oim.stores.user.domain.PPMApiBean;
import com.oim.stores.user.domain.PpmApiLog;
import com.oim.stores.user.domain.Reapilog;
import com.oim.stores.user.domain.SyLogOrder;
import com.oim.stores.user.domain.SyLogStoreBean;
import com.oim.stores.user.domain.SyLogUserBean;
import com.oim.stores.user.domain.SyOrderLogBean;
import com.oim.stores.user.domain.SySchLog;
import com.oim.stores.user.domain.ViewSydzlogId;
import com.oim.stores.user.service.SyLogUserService;

/**
 * 用户日志Action
 * 
 * @author syy
 */
@SuppressWarnings("serial")
public class LogUserAction extends BaseAction {
	private SyLogUserService syLogUserService;//日志管理service;
	private String userId;//用户id
	private String userName;//用户名
	private int total;//总数
	private String jsonDatas;//json数据
	private SyLogUserBean logInfo;//日志
	/**
	 * to PPM log page;
	 * @return
	 */
	public String toPPMlog(){
		return SUCCESS;
	}
	/**
	 * query PPM log list;
	 * @return
	 */
	public String qryPPMLogPage(){
		PageRequest pageRequest = new PageRequest();
		int pageNumber = Integer.valueOf(request.getParameter("page"));
		int pageSize = Integer.valueOf(request.getParameter("rows"));
		if (pageNumber > 0) {
			pageNumber = pageNumber - 1;
		}
		pageRequest.setPageNumber(pageNumber);
		pageRequest.setPageSize(pageSize);
		// 查询条件
		Map<String, String> params = new HashMap<String, String>();
		//params.put("dzresult", request.getParameter("dzResult"));
		params.put("startDate", request.getParameter("startDate"));
		params.put("endDate", request.getParameter("endDate"));
		params.put("factorid", request.getParameter("factorid"));
		try {
			DbContextHolder.setDbType("myweb");
			PageResponse pageList = syLogUserService.queryPPMLogForPage(params, pageNumber, pageSize);
			DbContextHolder.clearDbType();
			JSONArray jsonArray = new JSONArray();
			for (int i = 0; i < pageList.getList().size(); i++) {
				PpmApiLog si = (PpmApiLog) pageList.getList().get(i);
				PPMApiBean bean=new PPMApiBean();
				bean.setBean(si);
				JSONObject jsonObject = JSONUtils.toJSONObject(bean);
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
	 * To recharge log page
	 * @return
	 */
	public String toCZlog(){
		return SUCCESS;
	}
	/**
	 * query recharge log list;
	 * @return
	 */
	public String qryCZLogPage(){
		PageRequest pageRequest = new PageRequest();
		int pageNumber = Integer.valueOf(request.getParameter("page"));
		int pageSize = Integer.valueOf(request.getParameter("rows"));
		if (pageNumber > 0) {
			pageNumber = pageNumber - 1;
		}
		pageRequest.setPageNumber(pageNumber);
		pageRequest.setPageSize(pageSize);
		// 查询条件
		Map<String, String> params = new HashMap<String, String>();
		//params.put("dzresult", request.getParameter("dzResult"));
		params.put("startDate", request.getParameter("startDate"));
		params.put("endDate", request.getParameter("endDate"));
		params.put("factorid", request.getParameter("factorid"));
		try {
			DbContextHolder.setDbType("myweb");
			PageResponse pageList = syLogUserService.queryCZLogForPage(params, pageNumber, pageSize);
			DbContextHolder.clearDbType();
			JSONArray jsonArray = new JSONArray();
			for (int i = 0; i < pageList.getList().size(); i++) {
				Reapilog si = (Reapilog) pageList.getList().get(i);
				PPMApiBean bean=new PPMApiBean();
				bean.setReapLogBean(si);
				JSONObject jsonObject = JSONUtils.toJSONObject(bean);
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
	 * To Tianyi opened the card log page
	 * @return
	 */
	public String toKKAlog(){
		return SUCCESS;
	}
	/**
	 * query Tianyi opened the card log list ;
	 * @return
	 */
	public String qryKKALogPage(){
		PageRequest pageRequest = new PageRequest();
		int pageNumber = Integer.valueOf(request.getParameter("page"));
		int pageSize = Integer.valueOf(request.getParameter("rows"));
		if (pageNumber > 0) {
			pageNumber = pageNumber - 1;
		}
		pageRequest.setPageNumber(pageNumber);
		pageRequest.setPageSize(pageSize);
		// 查询条件
		Map<String, String> params = new HashMap<String, String>();
		//params.put("dzresult", request.getParameter("dzResult"));
		params.put("startDate", request.getParameter("startDate"));
		params.put("endDate", request.getParameter("endDate"));
		params.put("factorid", request.getParameter("factorid"));
		try {
			DbContextHolder.setDbType("myweb");
			PageResponse pageList = syLogUserService.queryKKLogForPage(params, pageNumber, pageSize);
			DbContextHolder.clearDbType();
			JSONArray jsonArray = new JSONArray();
			for (int i = 0; i < pageList.getList().size(); i++) {
				Reapilog si = (Reapilog) pageList.getList().get(i);
				PPMApiBean bean=new PPMApiBean();
				bean.setReapLogBean(si);
				JSONObject jsonObject = JSONUtils.toJSONObject(bean);
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
	 * to recharge audit log page
	 * @return
	 */
	public String toCZJHlog(){
		return SUCCESS;
	}
	/**
	 * query recharge audit log list;
	 * @return
	 */
	public String qryCZJHLogPage(){
		PageRequest pageRequest = new PageRequest();
		int pageNumber = Integer.valueOf(request.getParameter("page"));
		int pageSize = Integer.valueOf(request.getParameter("rows"));
		if (pageNumber > 0) {
			pageNumber = pageNumber - 1;
		}
		pageRequest.setPageNumber(pageNumber);
		pageRequest.setPageSize(pageSize);
		// 查询条件
		Map<String, String> params = new HashMap<String, String>();
		//params.put("dzresult", request.getParameter("dzResult"));
		params.put("startDate", request.getParameter("startDate"));
		params.put("endDate", request.getParameter("endDate"));
		params.put("dzid", request.getParameter("dzid"));
		try {
			DbContextHolder.setDbType("myweb");
			PageResponse pageList = syLogUserService.qryCZJHLogForPage(params, pageNumber, pageSize);
			DbContextHolder.setDbType("myweb");
			JSONArray jsonArray = new JSONArray();
			for (int i = 0; i < pageList.getList().size(); i++) {
				ViewSydzlogId si = (ViewSydzlogId) pageList.getList().get(i);
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
	 * to order log page;
	 * @return
	 */
	public String toOrderLog(){
		return SUCCESS;
	}
	/**
	 * query the order log list;
	 * @return
	 */
	public String qryOrderLogPage(){
		PageRequest pageRequest = new PageRequest();
		int pageNumber = Integer.valueOf(request.getParameter("page"));
		int pageSize = Integer.valueOf(request.getParameter("rows"));
		if (pageNumber > 0) {
			pageNumber = pageNumber - 1;
		}
		pageRequest.setPageNumber(pageNumber);
		pageRequest.setPageSize(pageSize);
		// 查询条件
		Map<String, String> params = new HashMap<String, String>();
		params.put("orderNo", request.getParameter("orderNumber"));
		params.put("startDate", request.getParameter("startDate"));
		params.put("endDate", request.getParameter("endDate"));
		params.put("orderStatus", request.getParameter("orderStatus"));
		try {
			PageResponse pageList = syLogUserService.queryOrderLogForPage(params, pageNumber, pageSize);
			JSONArray jsonArray = new JSONArray();
			for (int i = 0; i < pageList.getList().size(); i++) {
				SyLogOrder si = (SyLogOrder) pageList.getList().get(i);
				SyOrderLogBean bean=new SyOrderLogBean();
				bean.setIpaddress(si.getIpaddress());
				bean.setOrderNo(si.getId().getOrderNo());
				bean.setRemark(si.getRemark());
				bean.setOrderStatus(si.getId().getOrderStatus());
				bean.setSysTime(si.getId().getSysTime());
				JSONObject jsonObject = JSONUtils.toJSONObject(bean);
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
	 * to report log page;
	 * @return
	 */
	public String toReportLog(){
		return SUCCESS;
	}
	/**
	 * query report log list;
	 * @return
	 */
	public String queryReportForPage(){
		PageRequest pageRequest = new PageRequest();
		int pageNumber = Integer.valueOf(request.getParameter("page"));
		int pageSize = Integer.valueOf(request.getParameter("rows"));
		//String orderId=request.getParameter("orderId");
		//String processTime=request.getParameter("processTime");
		if (pageNumber > 0) {
			pageNumber = pageNumber - 1;
		}
		pageRequest.setPageNumber(pageNumber);
		pageRequest.setPageSize(pageSize);
		// 查询条件
		Map<String, String> params = new HashMap<String, String>();
		params.put("orderId", request.getParameter("orderNumber"));
		params.put("startDate", request.getParameter("startDate"));
		params.put("endDate", request.getParameter("endDate"));
		params.put("transId", request.getParameter("tranId"));
		params.put("schId", Content.JT_INTER_ID);
		try {
			PageResponse pageList = syLogUserService.queryReportForPage(params, pageNumber, pageSize);
			JSONArray jsonArray = new JSONArray();
			for (int i = 0; i < pageList.getList().size(); i++) {
				SySchLog si = (SySchLog) pageList.getList().get(i);
				//JTReportLogBean si = (JTReportLogBean) pageList.getList().get(i);
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
	 * 操作日志列表
	 * @return
	 */
	public String toUserLog(){
		return SUCCESS;
	}
	/**
	 * 查询系统操作日志
	 * 
	 * @return
	 */
	public String queryUserLogForPage() {
		PageRequest pageRequest = new PageRequest();
		int pageNumber = Integer.valueOf(request.getParameter("page"));
		int pageSize = Integer.valueOf(request.getParameter("rows"));
		String userNum=request.getParameter("userNumber");
		String searchTime=request.getParameter("searchTime");
		if (pageNumber > 0) {
			pageNumber = pageNumber - 1;
		}
		pageRequest.setPageNumber(pageNumber);
		pageRequest.setPageSize(pageSize);
		// 查询条件
		Map<String, String> params = new HashMap<String, String>();
		if(userNum!=null&&userNum!=""){
			params.put("userName", userNum);
		}
		if(searchTime!=null&&searchTime!=""){
			params.put("sysTime", searchTime);
		}
		try {
			PageResponse pageList = syLogUserService.queryForPage(params, pageNumber, pageSize);
			JSONArray jsonArray = new JSONArray();
			for (int i = 0; i < pageList.getList().size(); i++) {
				SyLogUserBean si = (SyLogUserBean) pageList.getList().get(i);
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
	public SyLogUserBean getLogInfo() {
		return logInfo;
	}
	public void setLogInfo(SyLogUserBean logInfo) {
		this.logInfo = logInfo;
	}
	/**
	 * 管理员操作日志列表
	 * @return
	 */
	public String toAdminLog(){
		return SUCCESS;
	}
	/**
	 * 查询管理员操作日志
	 * 
	 * @return
	 */
	public String queryAdminLogForPage() {
		PageRequest pageRequest = new PageRequest();
		int pageNumber = Integer.valueOf(request.getParameter("page"));
		int pageSize = Integer.valueOf(request.getParameter("rows"));
		String userNum=request.getParameter("userNumber");
		String searchTime=request.getParameter("searchTime");
		if (pageNumber > 0) {
			pageNumber = pageNumber - 1;
		}
		pageRequest.setPageNumber(pageNumber);
		pageRequest.setPageSize(pageSize);
		// 查询条件
		Map<String, String> params = new HashMap<String, String>();
		if(userNum!=null&&userNum!=""){
			params.put("userName", userNum);
		}
		if(searchTime!=null&&searchTime!=""){
			params.put("sysTime", searchTime);
		}
//		params.put("userName", "test1234");
		try {
			PageResponse pageList = syLogUserService.queryAdminForPage(params, pageNumber, pageSize);
			JSONArray jsonArray = new JSONArray();
			for (int i = 0; i < pageList.getList().size(); i++) {
				SyLogUserBean si = (SyLogUserBean) pageList.getList().get(i);
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
	 * 管理员操作日志列表
	 * @return
	 */
	public String toBusinessLog(){
		return SUCCESS;
	}
	/**
	 * 查询商家操作日志
	 * 
	 * @return
	 */
	public String queryBusiLogForPage() {
		PageRequest pageRequest = new PageRequest();
		int pageNumber = Integer.valueOf(request.getParameter("page"));
		int pageSize = Integer.valueOf(request.getParameter("rows"));
		String userNum=request.getParameter("userNumber");
		String searchTime=request.getParameter("searchTime");
		if (pageNumber > 0) {
			pageNumber = pageNumber - 1;
		}
		pageRequest.setPageNumber(pageNumber);
		pageRequest.setPageSize(pageSize);
		// 查询条件
		Map<String, String> params = new HashMap<String, String>();
		if(userNum!=null&&userNum!=""){
			params.put("storeName", userNum);
		}
		if(searchTime!=null&&searchTime!=""){
			params.put("sysTime", searchTime);
		}
//		params.put("userName", "test1234");
		try {
			PageResponse pageList = syLogUserService.queryBusinessForPage(params, pageNumber, pageSize);
			JSONArray jsonArray = new JSONArray();
			for (int i = 0; i < pageList.getList().size(); i++) {
				SyLogStoreBean si = (SyLogStoreBean) pageList.getList().get(i);
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
	
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}

	public String getJsonDatas() {
		return jsonDatas;
	}

	public void setJsonDatas(String jsonDatas) {
		this.jsonDatas = jsonDatas;
	}
	public SyLogUserService getSyLogUserService() {
		return syLogUserService;
	}
	public void setSyLogUserService(SyLogUserService syLogUserService) {
		this.syLogUserService = syLogUserService;
	}
	
	
}
