package com.oim.stores.user.domain;



/**
 * SyLogStoreUser entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class SyLogStoreUser implements java.io.Serializable {

	// Fields

	private SyLogStoreUserId id;
	private String ipaddress;
	private String memo;
	private Long storeId;

	// Constructors

	/** default constructor */
	public SyLogStoreUser() {
	}

	/** minimal constructor */
	public SyLogStoreUser(SyLogStoreUserId id, Long storeId) {
		this.id = id;
		this.storeId = storeId;
	}

	/** full constructor */
	public SyLogStoreUser(SyLogStoreUserId id, String ipaddress, String memo,
			Long storeId) {
		this.id = id;
		this.ipaddress = ipaddress;
		this.memo = memo;
		this.storeId = storeId;
	}

	// Property accessors

	public SyLogStoreUserId getId() {
		return this.id;
	}

	public void setId(SyLogStoreUserId id) {
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

	public Long getStoreId() {
		return this.storeId;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}

}