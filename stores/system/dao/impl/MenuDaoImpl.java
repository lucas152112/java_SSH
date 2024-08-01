package com.oim.stores.system.dao.impl;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.transform.Transformers;

import com.oim.stores.common.EntityDao;
import com.oim.stores.common.Pagination;
import com.oim.stores.common.Tool;
import com.oim.stores.exception.DaoException;
import com.oim.stores.store.domain.ShStoreUserRelation;
import com.oim.stores.system.dao.MenuDao;
import com.oim.stores.system.domain.MenuBean;
import com.oim.stores.system.domain.RoleMenuBean;
import com.oim.stores.system.domain.SyMenuList;
import com.oim.stores.system.domain.SyRoleMenuRelation;
/**
 * 菜单管理dao实现类
 * @author yuyan
 *
 */
@SuppressWarnings("unchecked")
public class MenuDaoImpl extends EntityDao implements MenuDao{

	@Override
	public List<SyMenuList> querySonMenu(Map<String, String> params)
			throws DaoException {
		try {
			StringBuffer hql = new StringBuffer("FROM SyMenuList WHERE parentId<>0 and 1=1 ");
			Set<String> keys = params.keySet();
			for (String key : keys) {
				hql.append(" AND " + key + " = " + params.get(key) + "");
			}
			List<SyMenuList> list = hibernateTemplate.find(hql.toString());
			return list;
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}
	@Override
	public void delMenu(SyMenuList menu) throws DaoException {
		try {
			hibernateTemplate.delete(menu);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public List<SyMenuList> query(Map<String, String> params)
			throws DaoException {
		try {
			String hql = "FROM SyMenuList p WHERE p.webName='admin' and 1=1 ";
			Set<String> keys = params.keySet();
			for (String key : keys) {
				if("menuName".equals(key)){
					if (Tool.isNotEmpty(params.get(key))) {
						hql += " AND p." + key + " like '%" + params.get(key) + "%' ";
					}
				}else {
					if (Tool.isNotEmpty(params.get(key))) {
						hql += " AND p." + key + " = '" + params.get(key) + "' ";
					}
				}
			}
			List<SyMenuList> list=hibernateTemplate.find(hql+" order by p.menuOrder asc ");
			return list;

		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public SyMenuList queryById(long menuId) throws DaoException {
		// TODO Auto-generated method stub
		return hibernateTemplate.get(SyMenuList.class, menuId);
	}

	@Override
	public Pagination queryForPage(Map<String, String> params, int pageNumber,
			int pageSize) throws DaoException {
		try {
			String hql = "FROM SyMenuList p WHERE 1=1 ";
			Set<String> keys = params.keySet();
			for (String key : keys) {
				if("menuName".equals(key)){
					if (Tool.isNotEmpty(params.get(key))) {
						hql += " AND p." + key + " like '%" + params.get(key) + "%' ";
					}
				}else {
					if (Tool.isNotEmpty(params.get(key))) {
						hql += " AND p." + key + " = '" + params.get(key) + "' ";
					}
				}
			}
			String counthql = " SELECT COUNT(*) " + hql;
			return queryForPage(hql, counthql, pageNumber, pageSize);

		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public void save(SyMenuList menu) throws DaoException {
		try {
			hibernateTemplate.saveOrUpdate(menu);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

	@Override
	public void updateMenu(SyMenuList menu) throws DaoException {
		try {
			hibernateTemplate.saveOrUpdate(menu);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}
	@Override
	public List<SyMenuList> querySonById(String parentId) throws DaoException {
		try {
			String hql = "FROM SyMenuList p WHERE p.webName='admin' and 1=1 and p.parentId="+parentId;
			List<SyMenuList> list=hibernateTemplate.find(hql+" order by p.menuOrder asc ");
			return list;

		} catch (Exception e) {
			throw new DaoException(e);
		}
	}
	@Override
	public void delMenuRelation(List<SyRoleMenuRelation> menu)
			throws DaoException {
		try {
			hibernateTemplate.deleteAll(menu);
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}
	@Override
	public List<SyRoleMenuRelation> queryMenuRelation(String menuId)
			throws DaoException {
		try {
			String hql = "FROM SyRoleMenuRelation p WHERE 1=1 and p.id.menuId="+menuId;
			List<SyRoleMenuRelation> list=hibernateTemplate.find(hql);
			return list;

		} catch (Exception e) {
			throw new DaoException(e);
		}
	}
	@Override
	public List<MenuBean> queryForTree(Map<String, String> params)
			throws DaoException {
		Session session=getSessionFactory().getCurrentSession();
		try {
//			String preHql="select new com.oim.stores.system.domain.MenuBean(p.menuId," +
//			"p.menuType,p.menuName,p.menuUrl,p.parentId,p.menuOrder,p.webName,p.menuDesc,p.isActive,p.updateDate," +
//			"p.updateUser,to_char(CONNECT_BY_ISLEAF)) ";
//			String hql = " from SyMenuList p where 1=1 and connect by prior menuId=parentId ";
//			Set<String> keys = params.keySet();
//			for (String key : keys) {
//					if (Tool.isNotEmpty(params.get(key))) {
//						hql += " AND p." + key + " = '" + params.get(key) + "' ";
//					}
//			}
//			List<MenuBean> list = hibernateTemplate.find(preHql+hql);
			Set<String> keys = params.keySet();
			String hql="select to_char(t.menu_id) menuId,to_char(t.menu_type) menuType,menu_name menuName,menu_url menuUrl,to_char(parent_id) parentId," +
					"to_char(menu_order) menuOrder,web_name webName,menu_desc menuDesc,to_char(is_active) isActive,update_date updateDate," ;
			for (String key : keys) {
				if (Tool.isNotEmpty(params.get(key))) {
					hql += "to_char(update_user) updateUser,to_char(CONNECT_BY_ISLEAF) isLeaf from SY_MENU_LIST t start with parent_id='" + params.get(key) + "' ";
				}
			}
					hql+=" connect by prior  menu_id=parent_id  where 1=1 ";
			@SuppressWarnings("unused")
			List list2=session.createSQLQuery(hql).list();
			//.setResultTransformer(Transformers.aliasToBean(MenuBean.class)).list();//.addEntity("mb",MenuBean.class).list();
			List<MenuBean> list=session.createSQLQuery(hql)
				.setResultTransformer(Transformers.aliasToBean(MenuBean.class)).list();//.addEntity("mb",MenuBean.class).list();
			return list;
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}
	@Override
	public List<RoleMenuBean> queryRelation(Map<String, String> params)
			throws DaoException {
		try {
//			String preHql=" select new com.oim.stores.system.domain.UserManageBean(vs.id.userId,vs.id.userName," +
//			"vs.id.userRealname,vs.id.telephone,vs.id.userStatus,vs.id.updateDate,vs.id.storeId," +
//			"vs.id.roleName,vs.id.roleId,vs.id.userPwd,vs.id.userMail)";
			//String hql=" from SyRoleMenuRelation t where t.id.menuId=(select m.menuId from SyMenuList m where m.menuName like '%菜单管理%') " ;
			String hql = " select new com.oim.stores.system.domain.RoleMenuBean(t.id.relationType, t.id.userroleId,t.id.menuId,t.isActive,t.updateDate,t.updateUser,m.menuName,m.menuUrl,m.parentId,to_char(m.menuType))" +
			" from SyRoleMenuRelation t,SyMenuList m where m.webName='admin' and t.id.menuId=m.menuId " ;
			Set<String> keys = params.keySet();
			for (String key : keys) {
				if("menuName".equals(key)){
					if (Tool.isNotEmpty(params.get(key))) {
						hql += " AND m." + key + " like '%" + params.get(key) + "%' ";
					}
				}else {
					if (Tool.isNotEmpty(params.get(key))) {
						hql += " AND t.id." + key + " = '" + params.get(key) + "' ";
					}
				}
			}
			List<RoleMenuBean> list = hibernateTemplate.find(hql+" order by m.menuOrder asc ");
			return list;
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}
	
	@Override
	public List<SyMenuList> queryRoleMenu(Map<String, String> params,String roleId)
			throws DaoException {
		try {
			String sql="select distinct m.* from sy_menu_list m where web_name='admin' start with menu_id in (select menu_id from sy_role_menu_relation " +
					"where relation_type=1 and userrole_id=" +roleId+
					") connect by prior m.parent_id=m.menu_id order by m.menu_order";
			List<SyMenuList> tlist=this.getSessionFactory().getCurrentSession().createSQLQuery(sql).addEntity(SyMenuList.class).list();//.addEntity(arg0, arg1)
			
//			String hql = " select distinct new com.oim.stores.system.domain.SyMenuList(m.menuId,m.menuType,m.menuName,m.menuUrl," +
//					"m.parentId,m.menuOrder,m.webName,m.menuDesc,m.isActive,m.updateDate,m.updateUser,m.groupid)" +
//					
//			" from SyMenuList m start with m.menuId in (select t.menuId from SyRoleMenuRelation t where t.id.relationType=1 and t.userroleId=" +roleId+
//			") connect by prior m.parentId=m.menuId " ;
//			Set<String> keys = params.keySet();
//			for (String key : keys) {
//				if("menuName".equals(key)){
//					if (Tool.isNotEmpty(params.get(key))) {
//						hql += " AND m." + key + " like '%" + params.get(key).trim() + "%' ";
//					}
//				}else {
//					if (Tool.isNotEmpty(params.get(key))) {
//						hql += " AND m." + key + " = '" + params.get(key).trim() + "' ";
//					}
//				}
//			}
//			List<SyMenuList> list = hibernateTemplate.find(hql+" order by m.menuOrder asc ");
			return tlist;
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}
	
	@Override
	public List<ShStoreUserRelation> queryStoreUserById(String id)
			throws DaoException {
		try {
			String hql=" from ShStoreUserRelation re where re.id.userId="+id;
			List<ShStoreUserRelation> list=hibernateTemplate.find(hql);
			return list;
		} catch (Exception e) {
			throw new DaoException();
		}
	}
	@Override
	public List<RoleMenuBean> queryAllMenu(Map<String, String> params)
			throws DaoException {
		try {
			String hql = " select new com.oim.stores.system.domain.RoleMenuBean(m.menuName,m.menuUrl,m.parentId,m.menuId" +
			" ,m.isActive,m.updateDate,m.updateUser,to_char(m.menuType)) from SyMenuList m where m.webName='admin'  " ;
			Set<String> keys = params.keySet();
			for (String key : keys) {
				if("menuName".equals(key)){
					if (Tool.isNotEmpty(params.get(key))) {
						hql += " AND m." + key + " like '%" + params.get(key) + "%' ";
					}
				}else {
					if (Tool.isNotEmpty(params.get(key))) {
						hql += " AND t.id." + key + " = '" + params.get(key) + "' ";
					}
				}
			}
			List<RoleMenuBean> list = hibernateTemplate.find(hql+" order by m.menuOrder asc ");
			return list;
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}
	@Override
	public List<RoleMenuBean> queryBusiAllMenu(Map<String, String> params)
			throws DaoException {
		try {
			String hql = " select new com.oim.stores.system.domain.RoleMenuBean(m.menuName,m.menuUrl,m.parentId,m.menuId" +
			" ,m.isActive,m.updateDate,m.updateUser,to_char(m.menuType)) from SyMenuList m where m.webName='business'  " ;
			Set<String> keys = params.keySet();
			for (String key : keys) {
				if("menuName".equals(key)){
					if (Tool.isNotEmpty(params.get(key))) {
						hql += " AND m." + key + " like '%" + params.get(key) + "%' ";
					}
				}else {
					if (Tool.isNotEmpty(params.get(key))) {
						hql += " AND t.id." + key + " = '" + params.get(key) + "' ";
					}
				}
			}
			List<RoleMenuBean> list = hibernateTemplate.find(hql+" order by m.menuOrder asc ");
			return list;
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}
	@Override
	public List<SyMenuList> queryBusiMenu(Map<String, String> params)
			throws DaoException {
		try {
			String hql = "FROM SyMenuList p WHERE p.webName='business' and 1=1 ";
			Set<String> keys = params.keySet();
			for (String key : keys) {
				if("menuName".equals(key)){
					if (Tool.isNotEmpty(params.get(key))) {
						hql += " AND p." + key + " like '%" + params.get(key) + "%' ";
					}
				}else {
					if (Tool.isNotEmpty(params.get(key))) {
						hql += " AND p." + key + " = '" + params.get(key) + "' ";
					}
				}
			}
			List<SyMenuList> list=hibernateTemplate.find(hql+" order by p.menuOrder asc ");
			return list;

		} catch (Exception e) {
			throw new DaoException(e);
		}
	}
	@Override
	public List<RoleMenuBean> queryBusiRelation(Map<String, String> params)
			throws DaoException {
		try {
			String hql = " select new com.oim.stores.system.domain.RoleMenuBean(t.id.relationType, t.id.userroleId,t.id.menuId,t.isActive,t.updateDate,t.updateUser,m.menuName,m.menuUrl,m.parentId,to_char(m.menuType))" +
			" from SyRoleMenuRelation t,SyMenuList m where m.webName='business' and t.id.menuId=m.menuId " ;
			Set<String> keys = params.keySet();
			for (String key : keys) {
				if("menuName".equals(key)){
					if (Tool.isNotEmpty(params.get(key))) {
						hql += " AND m." + key + " like '%" + params.get(key) + "%' ";
					}
				}else {
					if (Tool.isNotEmpty(params.get(key))) {
						hql += " AND t.id." + key + " = '" + params.get(key) + "' ";
					}
				}
			}
			List<RoleMenuBean> list = hibernateTemplate.find(hql+" order by m.menuOrder asc ");
			return list;
		} catch (Exception e) {
			throw new DaoException(e);
		}
	}
	@Override
	public List<SyMenuList> queryBusiSonById(String parentId)
			throws DaoException {
		try {
			String hql = "FROM SyMenuList p WHERE p.webName='business' and 1=1 and p.parentId="+parentId;
			List<SyMenuList> list=hibernateTemplate.find(hql+" order by p.menuOrder asc ");
			return list;

		} catch (Exception e) {
			throw new DaoException(e);
		}
	}

}
