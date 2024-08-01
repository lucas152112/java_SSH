package com.oim.stores.user.domain;

import java.util.Date;

@SuppressWarnings("serial")
public class SyLogStoreBean implements java.io.Serializable{
	private String ipaddress;
	private String memo;
	private Date sysTime;
	private Long userId;
	private String userAction;
	private String storeName;
	
	// Constructors

	/** default constructor */
	public SyLogStoreBean() {
	}
	public SyLogStoreBean(String storeName,String userAction,String ipaddress,String memo,Long userId,Date sysTime){
		this.storeName=storeName;
		this.userAction=userAction;
		this.ipaddress=ipaddress;
		this.memo=memo;
		this.userId=userId;
		this.sysTime=sysTime;
	}

	public String getIpaddress() {
		return ipaddress;
	}

	public void setIpaddress(String ipaddress) {
		this.ipaddress = ipaddress;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Date getSysTime() {
		return sysTime;
	}

	public void setSysTime(Date sysTime) {
		this.sysTime = sysTime;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserAction() {
		return userAction;
	}

	public void setUserAction(String userAction) {
		this.userAction = userAction;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	
	
}
