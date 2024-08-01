package com.oim.stores.system.domain;

import java.math.BigDecimal;

/**
 * SySmsParam entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class SySmsParam implements java.io.Serializable {

	// Fields

	private BigDecimal smsId;
	private String busiTels;
	private String shortContent;
	private BigDecimal busiNum;
	private String userName;
	private BigDecimal sendType;
	private BigDecimal status;
	private Long updateUser;

	// Constructors

	/** default constructor */
	public SySmsParam() {
	}

	/** full constructor */
	public SySmsParam(String busiTels, String shortContent, BigDecimal busiNum,
			String userName, BigDecimal sendType, BigDecimal status,Long updateUser) {
		this.busiTels = busiTels;
		this.shortContent = shortContent;
		this.busiNum = busiNum;
		this.userName = userName;
		this.sendType = sendType;
		this.status = status;
		this.updateUser=updateUser;
	}

	// Property accessors

	
	public BigDecimal getSmsId() {
		return this.smsId;
	}

	public Long getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(Long updateUser) {
		this.updateUser = updateUser;
	}

	public void setSmsId(BigDecimal smsId) {
		this.smsId = smsId;
	}

	public String getBusiTels() {
		return this.busiTels;
	}

	public void setBusiTels(String busiTels) {
		this.busiTels = busiTels;
	}

	public String getShortContent() {
		return this.shortContent;
	}

	public void setShortContent(String shortContent) {
		this.shortContent = shortContent;
	}

	public BigDecimal getBusiNum() {
		return this.busiNum;
	}

	public void setBusiNum(BigDecimal busiNum) {
		this.busiNum = busiNum;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public BigDecimal getSendType() {
		return this.sendType;
	}

	public void setSendType(BigDecimal sendType) {
		this.sendType = sendType;
	}

	public BigDecimal getStatus() {
		return this.status;
	}

	public void setStatus(BigDecimal status) {
		this.status = status;
	}

}