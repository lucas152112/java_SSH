package com.oim.stores.user.domain;

import java.math.BigDecimal;
import java.util.Date;

/**
 * SyLogOrderId entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class SyLogOrderId implements java.io.Serializable {

	// Fields

	private Date sysTime;
	private BigDecimal orderNo;
	private BigDecimal orderStatus;

	// Constructors

	/** default constructor */
	public SyLogOrderId() {
	}

	/** full constructor */
	public SyLogOrderId(Date sysTime, BigDecimal orderNo,
			BigDecimal orderStatus) {
		this.sysTime = sysTime;
		this.orderNo = orderNo;
		this.orderStatus = orderStatus;
	}

	// Property accessors

	public Date getSysTime() {
		return this.sysTime;
	}

	public void setSysTime(Date sysTime) {
		this.sysTime = sysTime;
	}

	public BigDecimal getOrderNo() {
		return this.orderNo;
	}

	public void setOrderNo(BigDecimal orderNo) {
		this.orderNo = orderNo;
	}

	public BigDecimal getOrderStatus() {
		return this.orderStatus;
	}

	public void setOrderStatus(BigDecimal orderStatus) {
		this.orderStatus = orderStatus;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof SyLogOrderId))
			return false;
		SyLogOrderId castOther = (SyLogOrderId) other;

		return ((this.getSysTime() == castOther.getSysTime()) || (this
				.getSysTime() != null
				&& castOther.getSysTime() != null && this.getSysTime().equals(
				castOther.getSysTime())))
				&& ((this.getOrderNo() == castOther.getOrderNo()) || (this
						.getOrderNo() != null
						&& castOther.getOrderNo() != null && this.getOrderNo()
						.equals(castOther.getOrderNo())))
				&& ((this.getOrderStatus() == castOther.getOrderStatus()) || (this
						.getOrderStatus() != null
						&& castOther.getOrderStatus() != null && this
						.getOrderStatus().equals(castOther.getOrderStatus())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getSysTime() == null ? 0 : this.getSysTime().hashCode());
		result = 37 * result
				+ (getOrderNo() == null ? 0 : this.getOrderNo().hashCode());
		result = 37
				* result
				+ (getOrderStatus() == null ? 0 : this.getOrderStatus()
						.hashCode());
		return result;
	}

}