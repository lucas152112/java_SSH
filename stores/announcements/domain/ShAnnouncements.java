package com.oim.stores.announcements.domain;

import java.util.Date;


/**
 * ShAnnouncements entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class ShAnnouncements implements java.io.Serializable {

	// Fields

	private Long AId;
	private String ATitle;
	private String ATag;
	private Long AType;
	private String AUrl;
	private String shortDesc;
	private String AText;
	private Long AOrder;
	private Long isTop;
	private Long isDisplay;
	private String titleColor;
	private String remark;
	private Date updateDate;
	private Long updateUser;
	private Long platformId;
	
	private String isTopFormat;
	private String isDisplayFormat;
	

	// Constructors

	/** default constructor */
	public ShAnnouncements() {
	}

	/** minimal constructor */
	public ShAnnouncements(String ATitle, String ATag, Long AType,
			Date updateDate, Long updateUser, Long platformId) {
		this.ATitle = ATitle;
		this.ATag = ATag;
		this.AType = AType;
		this.updateDate = updateDate;
		this.updateUser = updateUser;
		this.platformId = platformId;
	}

	/** full constructor */
	public ShAnnouncements(String ATitle, String ATag, Long AType,
			String AUrl, String shortDesc, String AText, Long AOrder,
			Long isTop, Long isDisplay, String titleColor, String remark,
			Date updateDate, Long updateUser, Long platformId) {
		this.ATitle = ATitle;
		this.ATag = ATag;
		this.AType = AType;
		this.AUrl = AUrl;
		this.shortDesc = shortDesc;
		this.AText = AText;
		this.AOrder = AOrder;
		this.isTop = isTop;
		this.isDisplay = isDisplay;
		this.titleColor = titleColor;
		this.remark = remark;
		this.updateDate = updateDate;
		this.updateUser = updateUser;
		this.platformId = platformId;
	}

	// Property accessors

	public Long getAId() {
		return this.AId;
	}

	public void setAId(Long AId) {
		this.AId = AId;
	}

	public String getATitle() {
		return this.ATitle;
	}

	public void setATitle(String ATitle) {
		this.ATitle = ATitle;
	}

	public String getATag() {
		return this.ATag;
	}

	public void setATag(String ATag) {
		this.ATag = ATag;
	}

	public Long getAType() {
		return this.AType;
	}

	public void setAType(Long AType) {
		this.AType = AType;
	}

	public String getAUrl() {
		return this.AUrl;
	}

	public void setAUrl(String AUrl) {
		this.AUrl = AUrl;
	}

	public String getShortDesc() {
		return this.shortDesc;
	}

	public void setShortDesc(String shortDesc) {
		this.shortDesc = shortDesc;
	}

	public String getAText() {
		return this.AText;
	}

	public void setAText(String AText) {
		this.AText = AText;
	}

	public Long getAOrder() {
		return this.AOrder;
	}

	public void setAOrder(Long AOrder) {
		this.AOrder = AOrder;
	}

	public Long getIsTop() {
		return this.isTop;
	}

	public void setIsTop(Long isTop) {
		this.isTop = isTop;
	}

	public Long getIsDisplay() {
		return this.isDisplay;
	}

	public void setIsDisplay(Long isDisplay) {
		this.isDisplay = isDisplay;
	}

	public String getTitleColor() {
		return this.titleColor;
	}

	public void setTitleColor(String titleColor) {
		this.titleColor = titleColor;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

	public Long getPlatformId() {
		return this.platformId;
	}

	public void setPlatformId(Long platformId) {
		this.platformId = platformId;
	}

	public String getIsTopFormat() {
		isTopFormat = "否";
		if(isTop != null){
			if(isTop.toString().equals("1"))
				isTopFormat = "是";
		}
		return isTopFormat;
	}

	public void setIsTopFormat(String isTopFormat) {
		this.isTopFormat = isTopFormat;
	}

	public String getIsDisplayFormat() {
		isDisplayFormat = "否";
		if(isDisplay != null){
			if(isDisplay.toString().equals("1"))
				isDisplayFormat = "是";
		}
		return isDisplayFormat;
	}

	public void setIsDisplayFormat(String isDisplayFormat) {
		this.isDisplayFormat = isDisplayFormat;
	}

}