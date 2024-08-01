package com.oim.stores.user.domain;

import java.sql.Timestamp;

/**
 * SyLogStoreUserId entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class SyLogStoreUserId implements java.io.Serializable {

	// Fields

	private Timestamp sysTime;
	private Long userId;
	private String userAction;

	// Constructors

	/** default constructor */
	public SyLogStoreUserId() {
	}

	/** full constructor */
	public SyLogStoreUserId(Timestamp sysTime, Long userId,
			String userAction) {
		this.sysTime = sysTime;
		this.userId = userId;
		this.userAction = userAction;
	}

	// Property accessors

	public Timestamp getSysTime() {
		return this.sysTime;
	}

	public void setSysTime(Timestamp sysTime) {
		this.sysTime = sysTime;
	}

	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserAction() {
		return this.userAction;
	}

	public void setUserAction(String userAction) {
		this.userAction = userAction;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof SyLogStoreUserId))
			return false;
		SyLogStoreUserId castOther = (SyLogStoreUserId) other;

		return ((this.getSysTime() == castOther.getSysTime()) || (this
				.getSysTime() != null
				&& castOther.getSysTime() != null && this.getSysTime().equals(
				castOther.getSysTime())))
				&& ((this.getUserId() == castOther.getUserId()) || (this
						.getUserId() != null
						&& castOther.getUserId() != null && this.getUserId()
						.equals(castOther.getUserId())))
				&& ((this.getUserAction() == castOther.getUserAction()) || (this
						.getUserAction() != null
						&& castOther.getUserAction() != null && this
						.getUserAction().equals(castOther.getUserAction())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getSysTime() == null ? 0 : this.getSysTime().hashCode());
		result = 37 * result
				+ (getUserId() == null ? 0 : this.getUserId().hashCode());
		result = 37
				* result
				+ (getUserAction() == null ? 0 : this.getUserAction()
						.hashCode());
		return result;
	}

}