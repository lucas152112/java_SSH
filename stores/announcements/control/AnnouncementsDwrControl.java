package com.oim.stores.announcements.control;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.oim.stores.common.LogManager;
import com.oim.stores.common.PageRequest;
import com.oim.stores.common.PageResponse;
import com.oim.stores.announcements.domain.ShAnnouncements;
import com.oim.stores.announcements.service.AnnouncementsService;
import com.oim.stores.exception.ServiceException;
/**
 * 公告管理dwr;
 * @author Administrator
 *
 */
public class AnnouncementsDwrControl {
	private AnnouncementsService announcementsService;//公告管理service
	/**
	 * 分页数据
	 * @param pageRequest
	 * @return
	 */
	public PageResponse queryAnnouncementsForPage(PageRequest pageRequest) {
		Map<String, String> params = new HashMap<String, String>();
		try {
			return announcementsService.queryForPage(params, pageRequest.getPageNumber(), pageRequest.getPageSize());
		} catch (ServiceException e) {
			LogManager.error(e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 获取公告
	 * @param annoId
	 * @return
	 */
	public ShAnnouncements getAnnouncements(long annoId) {
		try {
			return announcementsService.getAnnouncements(annoId);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 保存公告
	 * @param shAnnouncements
	 * @return
	 */
	public boolean saveAnnouncements(ShAnnouncements shAnnouncements) {
		try {
			shAnnouncements.setUpdateDate(new Date());
			announcementsService.saveAnnouncements(shAnnouncements);
			return true;
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return false;
	}
	/**
	 * 删除公告
	 * @param annoId
	 * @return
	 */
	public boolean deleteAnnouncements(long annoId) {
		try {
			ShAnnouncements announcements = announcementsService.getAnnouncements(annoId);
			announcementsService.deleteAnnouncements(announcements);
			return true;
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return false;
	}

	public AnnouncementsService getAnnouncementsService() {
		return announcementsService;
	}

	public void setAnnouncementsService(AnnouncementsService announcementsService) {
		this.announcementsService = announcementsService;
	}
	
	
}
