package com.oim.stores.store.service.impl;

import java.util.List;
import java.util.Map;

import com.oim.stores.common.PageResponse;
import com.oim.stores.common.Pagination;
import com.oim.stores.exception.DaoException;
import com.oim.stores.exception.ServiceException;
import com.oim.stores.store.dao.StoreUserRelationDao;
import com.oim.stores.store.domain.ShStoreUserRelation;
import com.oim.stores.store.domain.ShStoreUserRelationId;
import com.oim.stores.store.service.StoreUserRelationService;

/**
 * 商家帐户关联service实现类
 */
@SuppressWarnings("unchecked")
public class StoreUserRelationServiceImpl implements StoreUserRelationService {
	private StoreUserRelationDao storeUserRelationDao;

	@Override
	public void save(ShStoreUserRelation storeUserRelation) throws ServiceException {
		try {
			storeUserRelationDao.save(storeUserRelation);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void saveAll(List<ShStoreUserRelation> surs) throws ServiceException {
		try {
			storeUserRelationDao.saveAll(surs);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void delete(ShStoreUserRelation storeUserRelation) throws ServiceException {
		try {
			storeUserRelationDao.delete(storeUserRelation);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public PageResponse queryForPage(Map<String, String> params, int pageNumber, int pageSize) throws ServiceException {
		PageResponse pageResponse = new PageResponse();
		try {
			Pagination pagination = storeUserRelationDao.queryForPage(params, pageNumber, pageSize);
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
	public ShStoreUserRelation queryById(ShStoreUserRelationId id) throws ServiceException {
		try {
			return storeUserRelationDao.queryById(id);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<ShStoreUserRelation> queryByStoreId(Long storeId) throws ServiceException {
		try {
			return storeUserRelationDao.queryByStoreId(storeId);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void deleteByStoreId(Long storeId) throws ServiceException {
		try {
			storeUserRelationDao.deleteByStoreId(storeId);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	public void setStoreUserRelationDao(StoreUserRelationDao storeUserRelationDao) {
		this.storeUserRelationDao = storeUserRelationDao;
	}

	@Override
	public boolean isUseUserId(String userId) throws ServiceException {
		try {
			List list=storeUserRelationDao.queryByUserId(userId);
			if(list.size()>0){
				return true;
			}else{
				return false;
			}
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public ShStoreUserRelation queryByUserId(String userId)
			throws ServiceException {
		try {
			List<ShStoreUserRelation> list=storeUserRelationDao.queryByUserId(userId);
			if(list.size()>0){
				return list.get(0);
			}else{
				return null;
			}
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<ShStoreUserRelation> queryAdminByStoreId(Long storeId,
			Long deroleId) throws ServiceException {
		try {
			return storeUserRelationDao.queryAdminByStoreId(storeId, deroleId);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

}
