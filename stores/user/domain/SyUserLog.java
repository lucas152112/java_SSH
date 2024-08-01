package com.oim.stores.user.domain;

/**
 * SyUserLog entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class SyUserLog implements java.io.Serializable {

	// Fields

	private SyUserLogId id;
	private String userAction;
	private String ipaddress;
	private String memo;

	// Constructors

	/** default constructor */
	public SyUserLog() {
	}

	/** minimal constructor */
	public SyUserLog(SyUserLogId id) {
		this.id = id;
	}

	/** full constructor */
	public SyUserLog(SyUserLogId id, String userAction, String ipaddress, String memo) {
		this.id = id;
		this.userAction = userAction;
		this.ipaddress = ipaddress;
		this.memo = memo;
	}

	// Property accessors

	public SyUserLogId getId() {
		return this.id;
	}

	public void setId(SyUserLogId id) {
		this.id = id;
	}

	public String getUserAction() {
		return this.userAction;
	}

	public void setUserAction(String userAction) {
		this.userAction = userAction;
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