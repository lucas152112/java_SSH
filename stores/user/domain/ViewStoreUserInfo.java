package com.oim.stores.user.domain;


/**
 * ViewStoreUserInfo entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class ViewStoreUserInfo implements java.io.Serializable {

	// Fields

	private ViewStoreUserInfoId id;

	// Constructors

	/** default constructor */
	public ViewStoreUserInfo() {
	}

	/** full constructor */
	public ViewStoreUserInfo(ViewStoreUserInfoId id) {
		this.id = id;
	}

	// Property accessors

	public ViewStoreUserInfoId getId() {
		return this.id;
	}

	public void setId(ViewStoreUserInfoId id) {
		this.id = id;
	}

}