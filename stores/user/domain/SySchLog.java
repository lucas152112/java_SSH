package com.oim.stores.user.domain;

import java.util.Date;

/**
 * SySchLog entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class SySchLog implements java.io.Serializable {

	// Fields

	private Long schLogId;
	private Long schId;
	private Date processTime;
	private String resultCode;
	private String remark;
	private String transId;
	private Long orderId;

	// Constructors

	/** default constructor */
	public SySchLog() {
	}

	/** full constructor */
	public SySchLog(Long schId, Date processTime, String resultCode,
			String remark, String transId, Long orderId) {
		this.schId = schId;
		this.processTime = processTime;
		this.resultCode = resultCode;
		this.remark = remark;
		this.transId = transId;
		this.orderId = orderId;
	}

	// Property accessors

	public Long getSchLogId() {
		return this.schLogId;
	}

	public void setSchLogId(Long schLogId) {
		this.schLogId = schLogId;
	}

	public Long getSchId() {
		return this.schId;
	}

	public void setSchId(Long schId) {
		this.schId = schId;
	}

	public Date getProcessTime() {
		return this.processTime;
	}

	public void setProcessTime(Date processTime) {
		this.processTime = processTime;
	}

	public String getResultCode() {
		return this.resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getTransId() {
		return this.transId;
	}

	public void setTransId(String transId) {
		this.transId = transId;
	}

	public Long getOrderId() {
		return this.orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

}