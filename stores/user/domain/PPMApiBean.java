package com.oim.stores.user.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

@SuppressWarnings("serial")
public class PPMApiBean implements Serializable{
	private String memo;
	private String orderid;
	private BigDecimal resultid;
	private String ip;
	private String forderid;
	private String partitionid;
	private Timestamp requesttime;
	private String factorid;
	private BigDecimal funcid;
	private BigDecimal requesttype;
	
	
	public PPMApiBean() {
	}
	public PPMApiBean(String memo, String orderid, BigDecimal resultid,
			String ip, String forderid, String partitionid,
			Timestamp requesttime, String factorid, BigDecimal funcid,
			BigDecimal requesttype) {
		this.memo = memo;
		this.orderid = orderid;
		this.resultid = resultid;
		this.ip = ip;
		this.forderid = forderid;
		this.partitionid = partitionid;
		this.requesttime = requesttime;
		this.factorid = factorid;
		this.funcid = funcid;
		this.requesttype = requesttype;
	}

	public void setPPMId(PpmApiLogId id){
		this.requesttime=id.getRequesttime();
		this.factorid=id.getFactorid();
		this.funcid=id.getFuncid();
		this.requesttype=id.getRequesttype();
	}
	public void setBean(PpmApiLog id){
		this.requesttime=id.getId().getRequesttime();
		this.factorid=id.getId().getFactorid();
		this.funcid=id.getId().getFuncid();
		this.requesttype=id.getId().getRequesttype();
		this.memo=id.getMemo();
		this.orderid=id.getOrderid();
		this.resultid=id.getResultid();
		this.ip=id.getIp();
		this.forderid=id.getForderid();
		this.partitionid=id.getPartitionid();
	}
	public void setReapLogBean(Reapilog id){
		this.requesttime=id.getId().getRequesttime();
		this.factorid=id.getId().getFactorid();
		this.funcid=id.getId().getFuncid();
		this.requesttype=id.getId().getRequesttype();
		this.memo=id.getMemo();
		this.orderid=id.getOrderid();
		this.resultid=id.getResultid();
		this.ip=id.getIp();
		this.forderid=id.getForderid();
		this.partitionid=id.getPartitionid();
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getOrderid() {
		return orderid;
	}
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
	public BigDecimal getResultid() {
		return resultid;
	}
	public void setResultid(BigDecimal resultid) {
		this.resultid = resultid;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getForderid() {
		return forderid;
	}
	public void setForderid(String forderid) {
		this.forderid = forderid;
	}
	public String getPartitionid() {
		return partitionid;
	}
	public void setPartitionid(String partitionid) {
		this.partitionid = partitionid;
	}
	public Timestamp getRequesttime() {
		return requesttime;
	}
	public void setRequesttime(Timestamp requesttime) {
		this.requesttime = requesttime;
	}
	public String getFactorid() {
		return factorid;
	}
	public void setFactorid(String factorid) {
		this.factorid = factorid;
	}
	public BigDecimal getFuncid() {
		return funcid;
	}
	public void setFuncid(BigDecimal funcid) {
		this.funcid = funcid;
	}
	public BigDecimal getRequesttype() {
		return requesttype;
	}
	public void setRequesttype(BigDecimal requesttype) {
		this.requesttype = requesttype;
	}
	
}
