package com.oim.stores.announcements.service.impl;

import java.util.List;
import java.util.Map;

import com.oim.stores.announcements.dao.AnnouncementsDao;
import com.oim.stores.announcements.domain.ShAnnouncements;
import com.oim.stores.announcements.service.AnnouncementsService;
import com.oim.stores.common.PageResponse;
import com.oim.stores.common.Pagination;
import com.oim.stores.exception.DaoException;
import com.oim.stores.exception.ServiceException;
/**
 * 公告管理service子类
 * @author yuyan
 *
 */
public class AnnouncementsServiceImpl implements AnnouncementsService {
	private AnnouncementsDao announcementsDao;//公告管理dao;

	@Override
	public ShAnnouncements getAnnouncements(long AnnouncementsId) throws ServiceException {
		try {
			return announcementsDao.getAnnouncements(AnnouncementsId);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<ShAnnouncements> query(Map<String, String> params) throws ServiceException {
		try {
			return announcementsDao.query(params);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void saveAnnouncements(ShAnnouncements Announcements) throws ServiceException {
		try {
			announcementsDao.saveAnnouncements(Announcements);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void deleteAnnouncements(ShAnnouncements Announcements) throws ServiceException {
		try {
			announcementsDao.deleteAnnouncements(Announcements);
			// 删关联值表
			//announcementsDao.deleteAnnouncementsTypeByInfo(Announcements);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public PageResponse queryForPage(Map<String, String> params, int pageNumber, int pageSize) throws ServiceException {
		PageResponse pageResponse = new PageResponse();
		try {
			Pagination pagination = announcementsDao.queryForPage(params, pageNumber, pageSize);
			pageResponse.setList(pagination.getList());
			pageResponse.setTotalRecord(pagination.getTotalRecord());
			pageResponse.setSuccess(true);
			return pageResponse;
		} catch (DaoException e) {
			pageResponse.setSuccess(false);
			throw new ServiceException(e);
		}
	}

	public void setAnnouncementsDao(AnnouncementsDao announcementsDao) {
		this.announcementsDao = announcementsDao;
	}


}
