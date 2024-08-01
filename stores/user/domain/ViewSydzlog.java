package com.oim.stores.user.domain;

/**
 * ViewSydzlog entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class ViewSydzlog implements java.io.Serializable {

	// Fields

	private ViewSydzlogId id;

	// Constructors

	/** default constructor */
	public ViewSydzlog() {
	}

	/** full constructor */
	public ViewSydzlog(ViewSydzlogId id) {
		this.id = id;
	}

	// Property accessors

	public ViewSydzlogId getId() {
		return this.id;
	}

	public void setId(ViewSydzlogId id) {
		this.id = id;
	}

}