package com.oim.stores.store.domain;

import java.util.Date;

import com.oim.stores.user.domain.SyUserInfo;

/**
 * ShStoreUserRelation entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
public class ShStoreUserRelation implements java.io.Serializable {

	// Fields

	private ShStoreUserRelationId id;
	private Long userStoreRoleId;
	private Long userStatus;
	private Date addDate;
	private Date updateDate;
	private Long updateUser;

	private SyUserInfo userInfo;

	// Constructors

	/** default constructor */
	public ShStoreUserRelation() {
	}

	/** full constructor */
	public ShStoreUserRelation(ShStoreUserRelationId id, Long userStoreRoleId, Long userStatus, Date addDate, Date updateDate, Long updateUser) {
		this.id = id;
		this.userStoreRoleId = userStoreRoleId;
		this.userStatus = userStatus;
		this.addDate = addDate;
		this.updateDate = updateDate;
		this.updateUser = updateUser;
	}

	// Property accessors

	public ShStoreUserRelationId getId() {
		return this.id;
	}

	public void setId(ShStoreUserRelationId id) {
		this.id = id;
	}

	public Long getUserStoreRoleId() {
		return this.userStoreRoleId;
	}

	public void setUserStoreRoleId(Long userStoreRoleId) {
		this.userStoreRoleId = userStoreRoleId;
	}

	public Long getUserStatus() {
		return this.userStatus;
	}

	public void setUserStatus(Long userStatus) {
		this.userStatus = userStatus;
	}

	public Date getAddDate() {
		return this.addDate;
	}

	public void setAddDate(Date addDate) {
		this.addDate = addDate;
	}

	public Date getUpdateDate() {
		return this.updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public Long getUpdateUser() {
		return this.updateUser;
	}

	public void setUpdateUser(Long updateUser) {
		this.updateUser = updateUser;
	}

	public SyUserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(SyUserInfo userInfo) {
		this.userInfo = userInfo;
	}

}