package com.oim.stores.announcements.control;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.oim.stores.common.BaseAction;
import com.oim.stores.common.JSONUtils;
import com.oim.stores.common.PageRequest;
import com.oim.stores.common.PageResponse;
import com.oim.stores.exception.ServiceException;
import com.oim.stores.announcements.domain.ShAnnouncements;
import com.oim.stores.announcements.service.AnnouncementsService;

/**
 * 公告管理action
 * @author Jackson
 *
 */

@SuppressWarnings("serial")
public class AnnouncementsAction extends BaseAction {
	
	private AnnouncementsService announcementsService;//公告管理service
	private String jsonDatas;//json数据
	private int total;//总数
	/**
	 * to query page;
	 * @return
	 */
	public String queryAnnouncements() {
		return SUCCESS;
	}
	/**
	 * to manage page;
	 * @return
	 */
	public String toAnnoMng(){
		return SUCCESS;
	}
	/**
	 * query annoucements for page;
	 * @return
	 */
	public String queryAnnoForPage() {
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
		try {
			PageResponse pageList = announcementsService.queryForPage(params, pageNumber, pageSize);
			JSONArray jsonArray = new JSONArray();
			for (int i = 0; i < pageList.getList().size(); i++) {
				ShAnnouncements si = (ShAnnouncements) pageList.getList().get(i);
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
	
	public void setAnnouncementsService(AnnouncementsService announcementsService) {
		this.announcementsService = announcementsService;
	}

	public AnnouncementsService getAnnouncementsService() {
		return announcementsService;
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
	

}
