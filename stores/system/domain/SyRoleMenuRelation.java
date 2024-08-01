package com.oim.stores.system.domain;
import java.util.Date;


/**
 * SyRoleMenuRelation entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class SyRoleMenuRelation implements java.io.Serializable {

	// Fields

	private SyRoleMenuRelationId id;
	private Long isActive;
	private Date updateDate;
	private Long updateUser;

	// Constructors

	/** default constructor */
	public SyRoleMenuRelation() {
	}

	/** minimal constructor */
	public SyRoleMenuRelation(SyRoleMenuRelationId id) {
		this.id = id;
	}

	/** full constructor */
	public SyRoleMenuRelation(SyRoleMenuRelationId id, Long isActive,
			Date updateDate, Long updateUser) {
		this.id = id;
		this.isActive = isActive;
		this.updateDate = updateDate;
		this.updateUser = updateUser;
	}

	// Property accessors

	public SyRoleMenuRelationId getId() {
		return this.id;
	}

	public void setId(SyRoleMenuRelationId id) {
		this.id = id;
	}

	public Long getIsActive() {
		return this.isActive;
	}

	public void setIsActive(Long isActive) {
		this.isActive = isActive;
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

}