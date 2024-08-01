package com.oim.stores.store.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.oim.stores.common.Cryto;
import com.oim.stores.common.PageResponse;
import com.oim.stores.common.Pagination;
import com.oim.stores.datadict.domain.SyParameter;
import com.oim.stores.exception.DaoException;
import com.oim.stores.exception.ServiceException;
import com.oim.stores.shop.domain.ShStoreSecurity;
import com.oim.stores.store.dao.StoreInfoDao;
import com.oim.stores.store.domain.ShStoreCluster;
import com.oim.stores.store.domain.ShStoreInfo;
import com.oim.stores.store.domain.ShStoreRoleRel;
import com.oim.stores.store.domain.ShStoreRoleRelPk;
import com.oim.stores.store.domain.ShStoreUserRelation;
import com.oim.stores.store.domain.ShTeamStore;
import com.oim.stores.store.domain.ShTeamStoreId;
import com.oim.stores.store.domain.ViewStoreCluster;
import com.oim.stores.store.service.StoreInfoService;
import com.oim.stores.system.dao.SyRightDao;
import com.oim.stores.system.domain.SyRoleInfo;
import com.oim.stores.system.domain.SyVirtualTeam;

/**
 * 商家service实现类
 */
public class StoreInfoServiceImpl implements StoreInfoService {
	private StoreInfoDao storeInfoDao;//商家dao
	private SyRightDao syRightDao;
	
	/**
	 * @param syRightDao the syRightDao to set
	 */
	public void setSyRightDao(SyRightDao syRightDao) {
	    this.syRightDao = syRightDao;
	}

	/**
	 * 保存商家
	 */
	@Override
	public void save(ShStoreInfo storeInfo) throws ServiceException {
		try {
			storeInfo.setHomeUrl(storeInfo.getStoreName());
			storeInfoDao.save(storeInfo);
			//添加商家密钥
			ShStoreSecurity security = storeInfoDao.getStoreSecurityByStoreId(new BigDecimal(storeInfo.getStoreId()));
			if(security == null) {
				saveStoreSecurity(new BigDecimal(storeInfo.getStoreId()));
			}
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}
	
	//保存商家密钥
	private void saveStoreSecurity(BigDecimal storeId) throws DaoException{
		String key = Cryto.cryptMD5ToHEX(storeId.toString());
		ShStoreSecurity sec = new ShStoreSecurity();
		sec.setStoreId(storeId);
		sec.setStoreKey(key);
		storeInfoDao.saveStoreSecurity(sec);
	}

	@Override
	public void delete(ShStoreInfo storeInfo) throws ServiceException {
		try {
			storeInfoDao.delete(storeInfo);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public ShStoreInfo queryById(Long storeInfoId) throws ServiceException {
		try {
			return storeInfoDao.queryById(storeInfoId);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	public List<ShStoreInfo> queryByStoreName(String storeName) throws ServiceException {
		try {
			return storeInfoDao.queryByStoreName(storeName);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public PageResponse queryForPage(Map<String, String> params, int pageNumber, int pageSize) throws ServiceException {
		PageResponse pageResponse = new PageResponse();
		try {
			Pagination pagination = storeInfoDao.queryForPage(params, pageNumber, pageSize);
			pageResponse.setList(pagination.getList());
			pageResponse.setTotalRecord(pagination.getTotalRecord());
			pageResponse.setSuccess(true);
			return pageResponse;
		} catch (DaoException e) {
			pageResponse.setSuccess(false);
			throw new ServiceException(e);
		}
	}

	public void setStoreInfoDao(StoreInfoDao storeInfoDao) {
		this.storeInfoDao = storeInfoDao;
	}

	@Override
	public boolean updateAudit(Long[] storeIds, int isAudit, Long updateUser,String comment)
			throws ServiceException {
		try {
			int ok = 0;
			for (Long storeId : storeIds) {
				if (0 != storeId) {
					//ShStoreInfo pi = storeInfoDao.queryById(storeId);
					ShStoreInfo pi=storeInfoDao.queryStoreById(storeId);
					pi.setUpdateDate(new Date());
					pi.setUpdateUser(updateUser);
					pi.setIsAudit(new Long(isAudit));
					if(!"".equals(comment)&&comment!=null){
						pi.setAuditComment(comment);
					}
					storeInfoDao.save(pi);
					//isAudit=1则创建商家团队,创建团队关联 .  isAudit=2则删除团队关联
					if(isAudit==1){
					    	if(syRightDao.queryTeamCountByStoreId(new BigDecimal(storeId))==0L){
                					SyVirtualTeam team=new SyVirtualTeam();
                					team.setTeamName(pi.getStoreName());
                					team.setTeamSeq(new BigDecimal(pi.getStoreId()));
                					team.setTeamType(new BigDecimal("2"));//商家中心类型为2
                					team.setParentTeamId(new BigDecimal("2"));//默认为商家团队id
                					syRightDao.saveVirtualTeam(team);
                					team.setTeamCode(String.valueOf(team.getParentTeamId())
                						+ "." + team.getTeamId());
                					ShTeamStore teamStore=new ShTeamStore();
                					ShTeamStoreId tsId=new ShTeamStoreId();
                					tsId.setStoreId(new BigDecimal(storeId));
                					tsId.setTeamId(new BigDecimal(team.getTeamId()));
                					teamStore.setId(tsId);
                					syRightDao.saveTeamStore(teamStore);
					    	}
					}
					ok++;
				}
			}
			return ok > 0;
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<ShStoreInfo> getShStoreInfos() {
		return storeInfoDao.getShStoreInfos();
	}

	@Override
	public List<ShStoreRoleRel> getStoreRoleRels(String storeId) {
		return storeInfoDao.getStoreRoleRels(storeId);
	}

	@Override
	public List<SyRoleInfo> getRoles() {
		return storeInfoDao.getRoles();
	}

	@Override
	public void saveStoreRoleRel(ShStoreRoleRel rel) throws ServiceException {
		try {
			storeInfoDao.saveStoreRoleRel(rel);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<ShStoreUserRelation> getStoreUserRel(String storeId) {
		return storeInfoDao.getStoreUserRel(storeId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SyParameter> getProductSources() {
		String hql = "from SyParameter where id.groupId=21";
		return (List<SyParameter>)storeInfoDao.getByHql(hql);
	}

	@SuppressWarnings("unchecked")
	@Override
	public ShStoreRoleRel getStoreRoleById(ShStoreRoleRelPk id)
			throws ServiceException {
		String hql = "from ShStoreRoleRel where id.roleid="+id.getRoleid()+" and id.storeid="+id.getStoreid();
		List<ShStoreRoleRel> list = (List<ShStoreRoleRel>)storeInfoDao.getByHql(hql);
		if(list != null && list.size() > 0) return list.get(0);
		return null;
	}

	@Override
	public void saveCluster(ShStoreCluster cluster) {
		storeInfoDao.saveCluster(cluster);
	}

	@Override
	public ShStoreInfo getStoreByCserviceTel(String cserviceTel)
			throws ServiceException {
		String hql = "from ShStoreInfo s where s.cserviceTel='" + cserviceTel + "'";
		List<ShStoreInfo> list = (List<ShStoreInfo>)storeInfoDao.getByHql(hql);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public ViewStoreCluster queryStoreById(Long storeId) throws ServiceException {
		try {
			return storeInfoDao.queryStoreBeanById(storeId);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void update(ShStoreInfo storeInfo) throws ServiceException {
		try {
			storeInfoDao.save(storeInfo);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

}
