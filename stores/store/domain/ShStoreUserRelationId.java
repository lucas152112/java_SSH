package com.oim.stores.store.domain;


/**
 * ShStoreUserRelationId entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
public class ShStoreUserRelationId implements java.io.Serializable {

	// Fields

	private Long storeId;
	private Long userId;

	// Constructors

	/** default constructor */
	public ShStoreUserRelationId() {
	}

	/** full constructor */
	public ShStoreUserRelationId(Long storeId, Long userId) {
		this.storeId = storeId;
		this.userId = userId;
	}

	// Property accessors

	public Long getStoreId() {
		return this.storeId;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}

	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof ShStoreUserRelationId))
			return false;
		ShStoreUserRelationId castOther = (ShStoreUserRelationId) other;

		return ((this.getStoreId() == castOther.getStoreId()) || (this
				.getStoreId() != null
				&& castOther.getStoreId() != null && this.getStoreId().equals(
				castOther.getStoreId())))
				&& ((this.getUserId() == castOther.getUserId()) || (this
						.getUserId() != null
						&& castOther.getUserId() != null && this.getUserId()
						.equals(castOther.getUserId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getStoreId() == null ? 0 : this.getStoreId().hashCode());
		result = 37 * result
				+ (getUserId() == null ? 0 : this.getUserId().hashCode());
		return result;
	}

}