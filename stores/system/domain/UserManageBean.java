package com.oim.stores.system.domain;

import java.util.Date;

@SuppressWarnings("serial")
public class UserManageBean implements java.io.Serializable{
	private Long userId;
	private String userName;
    private String userRealname;
    private String telephone;
    private Long userStatus;
    private Date updateDate;
    private Long storeId;
    private String roleName;
    private Long roleId;
    private String userMail;
    private String userPwd;
    
	public UserManageBean() {
	}
	
	public UserManageBean(Long userId, String userName, String userRealname,
			String telephone, Long userStatus, Date updateDate, Long storeId,
			String roleName, Long roleId) {
		this.userId = userId;
		this.userName = userName;
		this.userRealname = userRealname;
		this.telephone = telephone;
		this.userStatus = userStatus;
		this.updateDate = updateDate;
		this.storeId = storeId;
		this.roleName = roleName;
		this.roleId = roleId;
	}
	
	public UserManageBean(Long userId, String userName, String userRealname,
			String telephone, Long userStatus, Date updateDate, Long storeId,
			String roleName, Long roleId,String userPwd,String userMail) {
		this.userId = userId;
		this.userName = userName;
		this.userRealname = userRealname;
		this.telephone = telephone;
		this.userStatus = userStatus;
		this.updateDate = updateDate;
		this.storeId = storeId;
		this.roleName = roleName;
		this.roleId = roleId;
		this.userPwd=userPwd;
		this.userMail=userMail;
	}

	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserRealname() {
		return userRealname;
	}
	public void setUserRealname(String userRealname) {
		this.userRealname = userRealname;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public Long getUserStatus() {
		return userStatus;
	}
	public void setUserStatus(Long userStatus) {
		this.userStatus = userStatus;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public Long getStoreId() {
		return storeId;
	}
	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getUserMail() {
		return userMail;
	}

	public void setUserMail(String userMail) {
		this.userMail = userMail;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
    
}
