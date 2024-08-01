package com.oim.stores.announcements.dao.impl;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.oim.stores.common.EntityDao;
import com.oim.stores.common.Pagination;
import com.oim.stores.announcements.domain.ShAnnouncements;
import com.oim.stores.announcements.dao.AnnouncementsDao;
import com.oim.stores.exception.DaoException;
/**
 * 公告管理dao子类
 * @author Administrator
 *
 */
@SuppressWarnings("unchecked")
public class AnnouncementsDaoImpl extends EntityDao implements AnnouncementsDao {
	@Override
	public ShAnnouncements getAnnouncements(long AnnoId) throws DaoException {
		try {
			return hibernateTemplate.get(ShAnnouncements.class, AnnoId);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}


	@Override
	public void saveAnnouncements(ShAnnouncements Announcements) throws DaoException {
		try {
			hibernateTemplate.saveOrUpdate(Announcements);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public void deleteAnnouncements(ShAnnouncements Announcements) throws DaoException {
		try {
			hibernateTemplate.delete(Announcements);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public List<ShAnnouncements> query(Map<String, String> params) throws DaoException {
		try {
			String hql = "FROM ShAnnouncements WHERE 1=1 ";
			Set<String> keys = params.keySet();
			for (String key : keys) {
				hql += " AND " + key + " = '" + params.get(key) + "' ";
			}
			return hibernateTemplate.find(hql);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public Pagination queryForPage(Map<String, String> params, int pageNumber, int pageSize) throws DaoException {
		try {
			String hql = "FROM ShAnnouncements WHERE 1=1 ";
			Set<String> keys = params.keySet();
			for (String key : keys) {
				hql += " AND " + key + " = '" + params.get(key) + "' ";
			}
			String counthql = " SELECT COUNT(*) " + hql;
			return queryForPage(hql, counthql, pageNumber, pageSize);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

}
