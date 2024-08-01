package com.oim.stores.system.domain;

import java.util.Date;

/**
 * SyRoleInfo entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class SyRoleInfo implements java.io.Serializable {

	// Fields

	private Long roleId;
	private String roleName;
	private Long roleType;
	private Date updateDate;
	private Long updateUser;

	// Constructors

	/** default constructor */
	public SyRoleInfo() {
	}

	/** minimal constructor */
	public SyRoleInfo(String roleName) {
		this.roleName = roleName;
	}

	/** full constructor */
	public SyRoleInfo(String roleName, Long roleType,
			Date updateDate, Long updateUser) {
		this.roleName = roleName;
		this.roleType = roleType;
		this.updateDate = updateDate;
		this.updateUser = updateUser;
	}

	// Property accessors

	public Long getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Long getRoleType() {
		return this.roleType;
	}

	public void setRoleType(Long roleType) {
		this.roleType = roleType;
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