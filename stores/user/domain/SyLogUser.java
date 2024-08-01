package com.oim.stores.user.domain;




/**
 * SyLogUser entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class SyLogUser implements java.io.Serializable {

	// Fields

	private SyLogUserId id;
	private String ipaddress;
	private String memo;

	// Constructors

	/** default constructor */
	public SyLogUser() {
	}

	/** minimal constructor */
	public SyLogUser(SyLogUserId id) {
		this.id = id;
	}

	/** full constructor */
	public SyLogUser(SyLogUserId id, String ipaddress, String memo) {
		this.id = id;
		this.ipaddress = ipaddress;
		this.memo = memo;
	}

	// Property accessors

	public SyLogUserId getId() {
		return this.id;
	}

	public void setId(SyLogUserId id) {
		this.id = id;
	}

	public String getIpaddress() {
		return this.ipaddress;
	}

	public void setIpaddress(String ipaddress) {
		this.ipaddress = ipaddress;
	}

	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

}