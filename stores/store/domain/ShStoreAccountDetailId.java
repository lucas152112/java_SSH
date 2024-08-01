package com.oim.stores.store.domain;

import java.util.Date;

/**
 * ShStoreAccountDetailId entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
public class ShStoreAccountDetailId implements java.io.Serializable {

	// Fields

	private Date changeDate;
	private Long storeAccountId;
	private Long dataType;

	// Constructors

	/** default constructor */
	public ShStoreAccountDetailId() {
	}

	/** full constructor */
	public ShStoreAccountDetailId(Date changeDate, Long storeAccountId,
			Long dataType) {
		this.changeDate = changeDate;
		this.storeAccountId = storeAccountId;
		this.dataType = dataType;
	}

	// Property accessors

	public Date getChangeDate() {
		return this.changeDate;
	}

	public void setChangeDate(Date changeDate) {
		this.changeDate = changeDate;
	}

	public Long getStoreAccountId() {
		return this.storeAccountId;
	}

	public void setStoreAccountId(Long storeAccountId) {
		this.storeAccountId = storeAccountId;
	}

	public Long getDataType() {
		return this.dataType;
	}

	public void setDataType(Long dataType) {
		this.dataType = dataType;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof ShStoreAccountDetailId))
			return false;
		ShStoreAccountDetailId castOther = (ShStoreAccountDetailId) other;

		return ((this.getChangeDate() == castOther.getChangeDate()) || (this
				.getChangeDate() != null
				&& castOther.getChangeDate() != null && this.getChangeDate()
				.equals(castOther.getChangeDate())))
				&& ((this.getStoreAccountId() == castOther.getStoreAccountId()) || (this
						.getStoreAccountId() != null
						&& castOther.getStoreAccountId() != null && this
						.getStoreAccountId().equals(
								castOther.getStoreAccountId())))
				&& ((this.getDataType() == castOther.getDataType()) || (this
						.getDataType() != null
						&& castOther.getDataType() != null && this
						.getDataType().equals(castOther.getDataType())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getChangeDate() == null ? 0 : this.getChangeDate()
						.hashCode());
		result = 37
				* result
				+ (getStoreAccountId() == null ? 0 : this.getStoreAccountId()
						.hashCode());
		result = 37 * result
				+ (getDataType() == null ? 0 : this.getDataType().hashCode());
		return result;
	}

}