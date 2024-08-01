package com.oim.stores.system.domain;

import java.util.Date;

@SuppressWarnings("serial")
public class RoleMenuBean implements java.io.Serializable{
	private Long relationType;
	private Long userroleId;
	private Long menuId;
	private Long isActive;
	private Date updateDate;
	private Long updateUser;
	private String menuName;
	private String menuUrl;
	private Long parentId;
	private String menuType;
	private String webName;
	public RoleMenuBean() {
	}
	public RoleMenuBean(Long relationType, Long userroleId, Long menuId,
			Long isActive, Date updateDate, Long updateUser,String menuName,String menuUrl,Long parentId,String menuType) {
		this.relationType = relationType;
		this.userroleId = userroleId;
		this.menuId = menuId;
		this.isActive = isActive;
		this.updateDate = updateDate;
		this.updateUser = updateUser;
		this.menuName=menuName;
		this.menuUrl=menuUrl;
		this.parentId=parentId;
		this.menuType=menuType;
	}
	
	public RoleMenuBean(String menuName, String menuUrl, Long parentId,Long menuId,Long isActive,Date updateDate, Long updateUser,  
			String menuType) {
		this.menuName = menuName;
        this.menuUrl = menuUrl;
        this.parentId = parentId;
		this.menuId = menuId;
        this.isActive = isActive;
        this.updateDate = updateDate;
        this.updateUser = updateUser;
        this.menuType = menuType;
    }
	
	public String getWebName() {
		return webName;
	}
	public void setWebName(String webName) {
		this.webName = webName;
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
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public String getMenuUrl() {
		return menuUrl;
	}
	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	public String getMenuType() {
		return menuType;
	}
	public void setMenuType(String menuType) {
		this.menuType = menuType;
	}
	
}
