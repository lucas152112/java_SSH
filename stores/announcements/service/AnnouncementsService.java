package com.oim.stores.announcements.service;

import java.util.List;
import java.util.Map;

import com.oim.stores.common.PageResponse;
import com.oim.stores.announcements.domain.ShAnnouncements;
import com.oim.stores.exception.ServiceException;
/**
 * 公告管理service
 * @author Administrator
 *
 */
public interface AnnouncementsService {
	/**
	 * 根据参数查询公告
	 * @param params
	 * @return
	 * @throws ServiceException
	 */
	public List<ShAnnouncements> query(Map<String, String> params) throws ServiceException;
	/**
	 * save announcements;
	 * @param Announcements
	 * @throws ServiceException
	 */
	public void saveAnnouncements(ShAnnouncements Announcements) throws ServiceException;
	/**
	 * delete announcement;
	 * @param Announcements
	 * @throws ServiceException
	 */
	public void deleteAnnouncements(ShAnnouncements Announcements) throws ServiceException;
	/**
	 * query announcement for page;
	 * @param params
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 * @throws ServiceException
	 */
	public PageResponse queryForPage(Map<String, String> params, int pageNumber, int pageSize) throws ServiceException;
	/**
	 * get announcement;
	 * @param annoId
	 * @return
	 * @throws ServiceException
	 */
	public ShAnnouncements getAnnouncements(long annoId) throws ServiceException;

}
