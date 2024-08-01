package com.oim.stores.system.dao;

import java.util.List;
import java.util.Map;

import com.oim.stores.common.Pagination;
import com.oim.stores.exception.DaoException;
import com.oim.stores.system.domain.MenuGroupBean;
import com.oim.stores.system.domain.SyMenuList;
/**
 * 权限分组dao
 * @author Administrator
 *
 */
public interface MenuGroupDao {
	/**
	 * 查询权限分组列表
	 * @param params
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 * @throws DaoException
	 */
    public Pagination queryForPage(Map<String, String> params, int pageNumber,
	    int pageSize) throws DaoException;
    /**
     * 更新权限分组
     * @param menugroup
     * @throws DaoException
     */
    public void updateGroup(MenuGroupBean menugroup) throws DaoException;
    /**
     * 根据id查询分组
     * @param groupid
     * @return
     * @throws DaoException
     */
    public MenuGroupBean queryById(String groupid) throws DaoException;
    /**
     * 保存分组信息
     * @param menugroup
     * @throws DaoException
     */
    public void saveGroup(MenuGroupBean menugroup) throws DaoException;
    /**
     * 删除分组信息
     * @param menugroup
     * @throws DaoException
     */
    public void deleteGroup(MenuGroupBean menugroup) throws DaoException;
    /**
     * 根据参数查询权限分组
     * @param params
     * @return
     * @throws DaoException
     */
    public List<MenuGroupBean> query(Map<String, String> params) throws DaoException;
    /**
     * 
     * @param params
     * @return
     * @throws DaoException
     */
    public int queryForMenuCount(Map<String, String> params) throws DaoException;
    /**
     * 查询菜单列表
     * @param params
     * @return
     * @throws DaoException
     */
    public List<SyMenuList> queryMenus(Map<String, String> params) throws DaoException;
    /**
     * 更新分组信息
     * @param menuid
     * @param groupid
     * @throws DaoException
     */
    public void updateMenuGroupId(String menuid,String groupid) throws DaoException;
}
