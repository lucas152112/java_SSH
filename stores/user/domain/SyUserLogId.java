package com.oim.stores.user.domain;

import java.sql.Timestamp;

/**
 * SyUserLogId entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class SyUserLogId implements java.io.Serializable {

	// Fields

	private Timestamp sysTime;
	private Double userId;

	// Constructors

	/** default constructor */
	public SyUserLogId() {
	}

	/** full constructor */
	public SyUserLogId(Timestamp sysTime, Double userId) {
		this.sysTime = sysTime;
		this.userId = userId;
	}

	// Property accessors

	public Timestamp getSysTime() {
		return this.sysTime;
	}

	public void setSysTime(Timestamp sysTime) {
		this.sysTime = sysTime;
	}

	public Double getUserId() {
		return this.userId;
	}

	public void setUserId(Double userId) {
		this.userId = userId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof SyUserLogId))
			return false;
		SyUserLogId castOther = (SyUserLogId) other;

		return ((this.getSysTime() == castOther.getSysTime()) || (this.getSysTime() != null && castOther.getSysTime() != null && this.getSysTime().equals(castOther.getSysTime())))
				&& ((this.getUserId() == castOther.getUserId()) || (this.getUserId() != null && castOther.getUserId() != null && this.getUserId().equals(castOther.getUserId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getSysTime() == null ? 0 : this.getSysTime().hashCode());
		result = 37 * result + (getUserId() == null ? 0 : this.getUserId().hashCode());
		return result;
	}

}