package com.oim.stores.system.domain;
import java.util.Date;

/**
 * SyMenuList entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class SyMenuList implements java.io.Serializable {

    // Fields

    private Long menuId;
    private Byte menuType;
    private String menuName;
    private String menuUrl;
    private Long parentId;
    private Long menuOrder;
    private String webName;
    private String menuDesc;
    private Long isActive;
    private Date updateDate;
    private Long updateUser;
    private Long groupid;

    // Constructors

    /** default constructor */
    public SyMenuList() {
    }

    /** full constructor */
    public SyMenuList(Byte menuType, String menuName, String menuUrl,
	    Long parentId, Long menuOrder, String webName, String menuDesc,
	    Long isActive, Date updateDate, Long updateUser, Long groupid) {
	this.menuType = menuType;
	this.menuName = menuName;
	this.menuUrl = menuUrl;
	this.parentId = parentId;
	this.menuOrder = menuOrder;
	this.webName = webName;
	this.menuDesc = menuDesc;
	this.isActive = isActive;
	this.updateDate = updateDate;
	this.updateUser = updateUser;
	this.groupid = groupid;
    }
    public SyMenuList(Long menuId,Byte menuType, String menuName, String menuUrl,
    	    Long parentId, Long menuOrder, String webName, String menuDesc,
    	    Long isActive, Date updateDate, Long updateUser, Long groupid) {
    	this.menuId=menuId;
    	this.menuType = menuType;
    	this.menuName = menuName;
    	this.menuUrl = menuUrl;
    	this.parentId = parentId;
    	this.menuOrder = menuOrder;
    	this.webName = webName;
    	this.menuDesc = menuDesc;
    	this.isActive = isActive;
    	this.updateDate = updateDate;
    	this.updateUser = updateUser;
    	this.groupid = groupid;
        }

    // Property accessors

    public Long getMenuId() {
	return this.menuId;
    }

    public void setMenuId(Long menuId) {
	this.menuId = menuId;
    }

    public Byte getMenuType() {
	return this.menuType;
    }

    public void setMenuType(Byte menuType) {
	this.menuType = menuType;
    }

    public String getMenuName() {
	return this.menuName;
    }

    public void setMenuName(String menuName) {
	this.menuName = menuName;
    }

    public String getMenuUrl() {
	return this.menuUrl;
    }

    public void setMenuUrl(String menuUrl) {
	this.menuUrl = menuUrl;
    }

    public Long getParentId() {
	return this.parentId;
    }

    public void setParentId(Long parentId) {
	this.parentId = parentId;
    }

    public Long getMenuOrder() {
	return this.menuOrder;
    }

    public void setMenuOrder(Long menuOrder) {
	this.menuOrder = menuOrder;
    }

    public String getWebName() {
	return this.webName;
    }

    public void setWebName(String webName) {
	this.webName = webName;
    }

    public String getMenuDesc() {
	return this.menuDesc;
    }

    public void setMenuDesc(String menuDesc) {
	this.menuDesc = menuDesc;
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

    /**
     * @return the groupid
     */
    public Long getGroupid() {
        return groupid;
    }

    /**
     * @param groupid the groupid to set
     */
    public void setGroupid(Long groupid) {
        this.groupid = groupid;
    }
    

}