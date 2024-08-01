package com.oim.stores.announcements.dao;

import java.util.List;
import java.util.Map;

import com.oim.stores.common.Pagination;
import com.oim.stores.announcements.domain.ShAnnouncements;
import com.oim.stores.exception.DaoException;
/**
 * 公告管理dao
 * @author Administrator
 *
 */
public interface AnnouncementsDao {

	/**
	 * According to the random parameters query announcements;
	 * @param params
	 * @return
	 * @throws DaoException
	 */
	public List<ShAnnouncements> query(Map<String, String> params) throws DaoException;
	/**
	 * save announcement
	 * @param Announcements
	 * @throws DaoException
	 */
	public void saveAnnouncements(ShAnnouncements Announcements) throws DaoException;
	/**
	 * delete announcement;
	 * @param Announcements
	 * @throws DaoException
	 */
	public void deleteAnnouncements(ShAnnouncements Announcements) throws DaoException;
	/**
	 * query announcements for page;
	 * @param params
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 * @throws DaoException
	 */
	public Pagination queryForPage(Map<String, String> params, int pageNumber, int pageSize) throws DaoException;
	/**
	 * access the annoucement;
	 * @param AnnouncementsId
	 * @return
	 * @throws DaoException
	 */
	public ShAnnouncements getAnnouncements(long AnnouncementsId) throws DaoException;

}