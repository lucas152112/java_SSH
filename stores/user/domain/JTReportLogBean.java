package com.oim.stores.user.domain;

import java.util.Date;

@SuppressWarnings("serial")
public class JTReportLogBean implements java.io.Serializable {
	private Long schLogId;
	private Long schId;
	private Date processTime;
	private String resultCode;
	private String remark;
	private String transId;
	private Long orderId;
	private String orderNumber;
	
	
	public JTReportLogBean() {
	}
	public JTReportLogBean(Long schId,
			Date processTime, String resultCode, String remark,
			String transId, Long orderId, String orderNumber) {
		this.schId = schId;
		this.processTime = processTime;
		this.resultCode = resultCode;
		this.remark = remark;
		this.transId = transId;
		this.orderId = orderId;
		this.orderNumber = orderNumber;
	}
	public JTReportLogBean(Long schLogId, Long schId,
			Date processTime, String resultCode, String remark,
			String transId, Long orderId, String orderNumber) {
		this.schLogId = schLogId;
		this.schId = schId;
		this.processTime = processTime;
		this.resultCode = resultCode;
		this.remark = remark;
		this.transId = transId;
		this.orderId = orderId;
		this.orderNumber = orderNumber;
	}

	public Long getSchLogId() {
		return schLogId;
	}
	public void setSchLogId(Long schLogId) {
		this.schLogId = schLogId;
	}
	public Long getSchId() {
		return schId;
	}
	public void setSchId(Long schId) {
		this.schId = schId;
	}
	public Date getProcessTime() {
		return processTime;
	}
	public void setProcessTime(Date processTime) {
		this.processTime = processTime;
	}
	public String getResultCode() {
		return resultCode;
	}
	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getTransId() {
		return transId;
	}
	public void setTransId(String transId) {
		this.transId = transId;
	}
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	
}
