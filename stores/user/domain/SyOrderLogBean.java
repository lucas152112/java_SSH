package com.oim.stores.user.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

@SuppressWarnings("serial")
public class SyOrderLogBean implements Serializable{
	private Date sysTime;
	private BigDecimal orderNo;
	private BigDecimal orderStatus;
	private String ipaddress;
	private String remark;
	
	public SyOrderLogBean() {
	}
	
	public SyOrderLogBean(Timestamp sysTime, BigDecimal orderNo,
			BigDecimal orderStatus, String ipaddress, String remark) {
		super();
		this.sysTime = sysTime;
		this.orderNo = orderNo;
		this.orderStatus = orderStatus;
		this.ipaddress = ipaddress;
		this.remark = remark;
	}

	public Date getSysTime() {
		return sysTime;
	}
	public void setSysTime(Date sysTime) {
		this.sysTime = sysTime;
	}
	public BigDecimal getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(BigDecimal orderNo) {
		this.orderNo = orderNo;
	}
	public BigDecimal getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(BigDecimal orderStatus) {
		this.orderStatus = orderStatus;
	}
	public String getIpaddress() {
		return ipaddress;
	}
	public void setIpaddress(String ipaddress) {
		this.ipaddress = ipaddress;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}
