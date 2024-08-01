package com.oim.stores.system.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.oim.stores.common.PageResponse;
import com.oim.stores.common.Pagination;
import com.oim.stores.exception.DaoException;
import com.oim.stores.exception.ServiceException;
import com.oim.stores.system.dao.PowerMngDao;
import com.oim.stores.system.domain.SyMenuList;
import com.oim.stores.system.domain.SyRoleMenuRelation;
import com.oim.stores.system.domain.UserMenuBean;
import com.oim.stores.system.service.PowerMngService;
/**
 * 权限service子类
 * @author yuyan
 *
 */
public class PowerMngServiceImpl implements PowerMngService {
	private PowerMngDao powerMngDao;//权限dao;
	@Override
	public SyRoleMenuRelation queryRelationById(Map<String, String> params)
			throws ServiceException {
		try {
			SyRoleMenuRelation rela = powerMngDao.queryRelationById(params);
			return rela;
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void delUserMenu(SyRoleMenuRelation userMenu) throws ServiceException {
		try {
			powerMngDao.delUserMenu(userMenu);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public UserMenuBean queryById(Map<String, String> params)
			throws ServiceException {
		try {
			List<UserMenuBean> list= powerMngDao.queryById(params);
			UserMenuBean bean=list.get(0);
			return bean;
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
	@Override
	public PageResponse queryRoleForPage(Map<String, String> params,
			int pageNumber, int pageSize) throws ServiceException {
		PageResponse pageResponse = new PageResponse();
		try {
			Pagination pagination = powerMngDao.queryRoleForPage(params, pageNumber, pageSize);
			pageResponse.setList(pagination.getList());
			pageResponse.setTotalRecord(pagination.getTotalRecord());
			pageResponse.setSuccess(true);
			return pageResponse;
		} catch (DaoException e) {
			pageResponse.setSuccess(false);
			throw new ServiceException(e);
		}
	}

	@Override
	public UserMenuBean queryRoleMenuById(Map<String, String> params)
			throws ServiceException {
		try {
			List<UserMenuBean> list= powerMngDao.queryRoleById(params);
			UserMenuBean bean=list.get(0);
			return bean;
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public PageResponse queryForPage(Map<String, String> params, int pageNumber,
			int pageSize) throws ServiceException {
		PageResponse pageResponse = new PageResponse();
		try {
			Pagination pagination = powerMngDao.queryForPage(params, pageNumber, pageSize);
			pageResponse.setList(pagination.getList());
			pageResponse.setTotalRecord(pagination.getTotalRecord());
			pageResponse.setSuccess(true);
			return pageResponse;
		} catch (DaoException e) {
			pageResponse.setSuccess(false);
			throw new ServiceException(e);
		}
	}

	@Override
	public List<SyMenuList> queryMenu(Map<String, String> params)
			throws ServiceException {
		// TODO Auto-generated method stub
		try {
			return powerMngDao.queryMenu(params);
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			throw new ServiceException(e);
		}
	}
	
	@Override
	public List<SyMenuList> querySonMenu(Map<String, String> params)
			throws ServiceException {
		try {
			return powerMngDao.querySonMenu(params);
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			throw new ServiceException(e);
		}
	}

	@Override
	public void save(SyRoleMenuRelation userMenu) throws ServiceException {
		try {
			powerMngDao.save(userMenu);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void updateUserMenu(UserMenuBean userMenu) throws ServiceException {
		try {
			Map<String, String> params = new HashMap<String, String>();
			params.put("userroleId", userMenu.getUserroleId()+"");
			params.put("menuId", userMenu.getMenuId()+"");
			params.put("relationType", userMenu.getRelationType()+"");
			SyRoleMenuRelation rela=powerMngDao.queryRelationById(params);
			rela.setIsActive(userMenu.getIsActive());
			powerMngDao.save(rela);
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			throw new ServiceException(e);
		}
	}

	public PowerMngDao getPowerMngDao() {
		return powerMngDao;
	}

	public void setPowerMngDao(PowerMngDao powerMngDao) {
		this.powerMngDao = powerMngDao;
	}

	@Override
	public List<SyRoleMenuRelation> queryRoleRelation(Map<String, String> params)
			throws ServiceException {
		try {
			return powerMngDao.queryRoleRelation(params);
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			throw new ServiceException(e);
		}
	}

	@Override
	public void deleteRoleRelation(List<SyRoleMenuRelation> relalist)
			throws ServiceException {
		try {
			powerMngDao.deleteRoleRelation(relalist);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void saveRoleRelation(List<SyRoleMenuRelation> relalist)
			throws ServiceException {
		try {
			powerMngDao.saveRoleRelation(relalist);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	
}
