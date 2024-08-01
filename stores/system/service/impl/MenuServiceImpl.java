package com.oim.stores.system.service.impl;

import java.util.List;
import java.util.Map;

import com.oim.stores.exception.DaoException;
import com.oim.stores.exception.ServiceException;
import com.oim.stores.store.domain.ShStoreUserRelation;
import com.oim.stores.system.dao.MenuDao;
import com.oim.stores.system.domain.MenuBean;
import com.oim.stores.system.domain.RoleMenuBean;
import com.oim.stores.system.domain.SyMenuList;
import com.oim.stores.system.domain.SyRoleMenuRelation;
import com.oim.stores.system.service.MenuService;
/**
 * 菜单管理service实现类
 * @author yuyan
 *
 */
public class MenuServiceImpl implements MenuService{
	private MenuDao menuDao;//菜单管理dao
	@Override
	public void delMenu(SyMenuList menu) throws ServiceException {
		try {
			menuDao.delMenu(menu);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public SyMenuList queryById(long menuId)
			throws ServiceException {
		try {
			SyMenuList menu= menuDao.queryById(menuId);
			return menu;
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<SyMenuList> queryMenu(Map<String, String> params)
			throws ServiceException {
		try {
//			List<SyMenuList> list=menuDao.query(params);
//			return list;
			return menuDao.query(params);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<SyMenuList> querySonMenu(Map<String, String> params)
			throws ServiceException {
		try {
			return menuDao.querySonMenu(params);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void save(SyMenuList menu) throws ServiceException {
		try {
			menuDao.save(menu);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void updateMenu(SyMenuList menu) throws ServiceException {
		try {
			menuDao.save(menu);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	public MenuDao getMenuDao() {
		return menuDao;
	}

	public void setMenuDao(MenuDao menuDao) {
		this.menuDao = menuDao;
	}

	@Override
	public List<SyMenuList> querySonById(String parentId) throws ServiceException {
		try {
			return menuDao.querySonById(parentId);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void delMenuRelatin(String menuId) throws ServiceException {
		try {
			List<SyRoleMenuRelation> list=menuDao.queryMenuRelation(menuId);
			if(list.size()>0){
				menuDao.delMenuRelation(list);
			}
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<MenuBean> queryForTree(Map<String, String> params)
			throws ServiceException {
		try {
			return menuDao.queryForTree(params);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<SyRoleMenuRelation> queryMenuRelation(String menuId)
			throws ServiceException {
		try {
			return menuDao.queryMenuRelation(menuId);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<RoleMenuBean> queryRelation(Map<String, String> params)
			throws ServiceException {
		try {
			return menuDao.queryRelation(params);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<ShStoreUserRelation> queryStoreUserById(String id)
			throws ServiceException {
		try {
			return menuDao.queryStoreUserById(id);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<RoleMenuBean> queryAllMenu(Map<String, String> params)
			throws ServiceException {
		try {
			return menuDao.queryAllMenu(params);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<RoleMenuBean> queryBusiAllMenu(Map<String, String> params)
			throws ServiceException {
		try {
			return menuDao.queryBusiAllMenu(params);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<SyMenuList> queryBusiMenu(Map<String, String> params)
			throws ServiceException {
		try {
			return menuDao.queryBusiMenu(params);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<RoleMenuBean> queryBusiRelation(Map<String, String> params)
			throws ServiceException {
		try {
			return menuDao.queryBusiRelation(params);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<SyMenuList> queryBusiSonById(String parentId)
			throws ServiceException {
		try {
			return menuDao.queryBusiSonById(parentId);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<SyMenuList> queryBusiSonMenu(Map<String, String> params)
			throws ServiceException {
		try {
			return menuDao.queryBusiMenu(params);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<SyMenuList> queryRoleMenu(Map<String, String> params,
			String roleId) throws ServiceException {
		try {
			return menuDao.queryRoleMenu(params, roleId);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	

}
