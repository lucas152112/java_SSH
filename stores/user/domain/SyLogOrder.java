package com.oim.stores.user.domain;

/**
 * SyLogOrder entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class SyLogOrder implements java.io.Serializable {

	// Fields

	private SyLogOrderId id;
	private String ipaddress;
	private String remark;

	// Constructors

	/** default constructor */
	public SyLogOrder() {
	}

	/** minimal constructor */
	public SyLogOrder(SyLogOrderId id) {
		this.id = id;
	}

	/** full constructor */
	public SyLogOrder(SyLogOrderId id, String ipaddress, String remark) {
		this.id = id;
		this.ipaddress = ipaddress;
		this.remark = remark;
	}

	// Property accessors

	public SyLogOrderId getId() {
		return this.id;
	}

	public void setId(SyLogOrderId id) {
		this.id = id;
	}

	public String getIpaddress() {
		return this.ipaddress;
	}

	public void setIpaddress(String ipaddress) {
		this.ipaddress = ipaddress;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}