package com.oim.stores.system.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.oim.stores.common.PageResponse;
import com.oim.stores.common.Pagination;
import com.oim.stores.exception.DaoException;
import com.oim.stores.exception.ServiceException;
import com.oim.stores.system.dao.RoleManageDao;
import com.oim.stores.system.domain.SyRoleInfo;
import com.oim.stores.system.service.RoleManageService;
/**
 * 角色管理service
 * @author yuyan
 *
 */
public class RoleManageServiceImpl implements RoleManageService {
	private RoleManageDao roleMngDao;//角色管理dao
	
	@Override
	public void delRoleInfo(long roleId) throws ServiceException {
		// TODO Auto-generated method stub
		try {
			SyRoleInfo role=roleMngDao.queryById(roleId);
			roleMngDao.delRole(role);
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			throw new ServiceException(e);
		}
	}

	@Override
	public PageResponse queryForPage(Map<String, String> params,
			int pageNumber, int pageSize) throws ServiceException {
		PageResponse pageResponse = new PageResponse();
		try {
			Pagination pagination = roleMngDao.queryForPage(params, pageNumber, pageSize);
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
	public SyRoleInfo queryById(long roleId) throws ServiceException {
		try {
			SyRoleInfo role=roleMngDao.queryById(roleId);
			return role;
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void saveRoleInfo(SyRoleInfo role) throws ServiceException {
		try {
			roleMngDao.save(role);
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			throw new ServiceException(e);
		}
	}
	
	@Override
	public void updateRoleInfo(SyRoleInfo bean, long updateUser)
			throws ServiceException {
		try {
			SyRoleInfo role=roleMngDao.queryById(bean.getRoleId());
			role.setRoleName(bean.getRoleName());
			role.setUpdateDate(new Date());
			role.setUpdateUser(updateUser);
			roleMngDao.save(role);
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			throw new ServiceException(e);
		}
	}

	@Override
	public List<SyRoleInfo> query(Map<String, String> params)
			throws ServiceException {
		try {
			return roleMngDao.query(params);
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			throw new ServiceException(e);
		}
	}
	
	@Override
	public List<SyRoleInfo> queryRoleInfo(Map<String, String> params)
			throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}
	
	public RoleManageDao getRoleMngDao() {
		return roleMngDao;
	}

	public void setRoleMngDao(RoleManageDao roleMngDao) {
		this.roleMngDao = roleMngDao;
	}

	@Override
	public boolean hasRoleName(String name) throws ServiceException {
		try {
			Map<String,String> param=new HashMap<String, String>();
			param.put("roleName", name);
			List<SyRoleInfo> userList = roleMngDao.query(param);
			return userList != null && userList.size() > 0;
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public boolean hasBusiRoleName(String name) throws ServiceException {
		try {
			Map<String,String> param=new HashMap<String, String>();
			param.put("roleName", name);
			List<SyRoleInfo> userList = roleMngDao.queryBusi(param);//(param);
			return userList != null && userList.size() > 0;
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<SyRoleInfo> queryBusi(Map<String, String> params)
			throws ServiceException {
		try {
			return roleMngDao.queryBusi(params);
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			throw new ServiceException(e);
		}
	}

	@Override
	public PageResponse queryBusiForPage(Map<String, String> params,
			int pageNumber, int pageSize) throws ServiceException {
		PageResponse pageResponse = new PageResponse();
		try {
			Pagination pagination = roleMngDao.queryBusiForPage(params, pageNumber, pageSize);
			pageResponse.setList(pagination.getList());
			pageResponse.setTotalRecord(pagination.getTotalRecord());
			pageResponse.setSuccess(true);
			return pageResponse;
		} catch (DaoException e) {
			pageResponse.setSuccess(false);
			throw new ServiceException(e);
		}
	}

}
