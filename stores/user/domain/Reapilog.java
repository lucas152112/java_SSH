package com.oim.stores.user.domain;

import java.math.BigDecimal;

/**
 * Reapilog entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class Reapilog implements java.io.Serializable {

	// Fields

	private ReapilogId id;
	private String memo;
	private String orderid;
	private BigDecimal resultid;
	private String ip;
	private String forderid;
	private String partitionid;

	// Constructors

	/** default constructor */
	public Reapilog() {
	}

	/** minimal constructor */
	public Reapilog(ReapilogId id) {
		this.id = id;
	}

	/** full constructor */
	public Reapilog(ReapilogId id, String memo, String orderid,
			BigDecimal resultid, String ip, String forderid, String partitionid) {
		this.id = id;
		this.memo = memo;
		this.orderid = orderid;
		this.resultid = resultid;
		this.ip = ip;
		this.forderid = forderid;
		this.partitionid = partitionid;
	}

	// Property accessors

	public ReapilogId getId() {
		return this.id;
	}

	public void setId(ReapilogId id) {
		this.id = id;
	}

	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getOrderid() {
		return this.orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public BigDecimal getResultid() {
		return this.resultid;
	}

	public void setResultid(BigDecimal resultid) {
		this.resultid = resultid;
	}

	public String getIp() {
		return this.ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getForderid() {
		return this.forderid;
	}

	public void setForderid(String forderid) {
		this.forderid = forderid;
	}

	public String getPartitionid() {
		return this.partitionid;
	}

	public void setPartitionid(String partitionid) {
		this.partitionid = partitionid;
	}

}