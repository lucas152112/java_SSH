package com.oim.stores.system.domain;

import java.util.Date;

@SuppressWarnings("serial")
public class UserMenuBean implements java.io.Serializable{
	private Long isActive;
	private Date updateDate;
	private Long updateUser;
	private Long relationType;
	private Long userroleId;
	private Long menuId;
	private String userName;
	private String roleName;
	
	
	public UserMenuBean() {
	}
	public UserMenuBean(Long userroleId,String userName,Long relationType,Long isActive){
		this.isActive = isActive;
		this.relationType = relationType;
		this.userroleId = userroleId;
		this.userName = userName;
	}
	
	public UserMenuBean(Long userroleId,Long relationType,Long isActive, Long updateUser,Date updateDate, 
			  Long menuId,String roleName) {
		this.isActive = isActive;
		this.updateDate = updateDate;
		this.updateUser = updateUser;
		this.relationType = relationType;
		this.userroleId = userroleId;
		this.menuId = menuId;
		this.roleName = roleName;
	}

	public UserMenuBean(String userName,Long userroleId,Long relationType,Long isActive, Long updateUser,Date updateDate, 
			  Long menuId) {
		this.isActive = isActive;
		this.updateDate = updateDate;
		this.updateUser = updateUser;
		this.relationType = relationType;
		this.userroleId = userroleId;
		this.menuId = menuId;
		this.userName = userName;
	}


	public Long getIsActive() {
		return isActive;
	}
	public void setIsActive(Long isActive) {
		this.isActive = isActive;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public Long getUpdateUser() {
		return updateUser;
	}
	public void setUpdateUser(Long updateUser) {
		this.updateUser = updateUser;
	}
	public Long getRelationType() {
		return relationType;
	}
	public void setRelationType(Long relationType) {
		this.relationType = relationType;
	}
	public Long getUserroleId() {
		return userroleId;
	}
	public void setUserroleId(Long userroleId) {
		this.userroleId = userroleId;
	}
	public Long getMenuId() {
		return menuId;
	}
	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	
}
