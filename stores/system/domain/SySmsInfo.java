package com.oim.stores.system.domain;

import java.math.BigDecimal;

/**
 * SySmsInfo entity. @author MyEclipse Persistence Tools
 */

public class SySmsInfo implements java.io.Serializable {

	// Fields

	private BigDecimal smsId;
	private String smsObj;
	private String smsContent;
	private BigDecimal smsType;
	private String smsObjStr;

	// Constructors

	/** default constructor */
	public SySmsInfo() {
	}

	/** full constructor */
	public SySmsInfo(String smsObj, String smsContent, BigDecimal smsType) {
		this.smsObj = smsObj;
		this.smsContent = smsContent;
		this.smsType = smsType;
	}

	// Property accessors
	
	
	public BigDecimal getSmsId() {
		return this.smsId;
	}

	public String getSmsObjStr() {
		return smsObjStr;
	}

	public void setSmsObjStr(String smsObjStr) {
		this.smsObjStr = smsObjStr;
	}

	public void setSmsId(BigDecimal smsId) {
		this.smsId = smsId;
	}

	public String getSmsObj() {
		return this.smsObj;
	}

	public void setSmsObj(String smsObj) {
		this.smsObj = smsObj;
	}

	public String getSmsContent() {
		return this.smsContent;
	}

	public void setSmsContent(String smsContent) {
		this.smsContent = smsContent;
	}

	public BigDecimal getSmsType() {
		return this.smsType;
	}

	public void setSmsType(BigDecimal smsType) {
		this.smsType = smsType;
	}

}