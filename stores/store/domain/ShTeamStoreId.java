package com.oim.stores.store.domain;
// default package

import java.math.BigDecimal;

/**
 * ShTeamStoreId entity. @author MyEclipse Persistence Tools
 */

public class ShTeamStoreId implements java.io.Serializable {

    // Fields

    private BigDecimal teamId;
    private BigDecimal storeId;

    // Constructors

    /** default constructor */
    public ShTeamStoreId() {
    }

    /** full constructor */
    public ShTeamStoreId(BigDecimal teamId, BigDecimal storeId) {
	this.teamId = teamId;
	this.storeId = storeId;
    }

    // Property accessors

    public BigDecimal getTeamId() {
	return this.teamId;
    }

    public void setTeamId(BigDecimal teamId) {
	this.teamId = teamId;
    }

    public BigDecimal getStoreId() {
	return this.storeId;
    }

    public void setStoreId(BigDecimal storeId) {
	this.storeId = storeId;
    }

    public boolean equals(Object other) {
	if ((this == other))
	    return true;
	if ((other == null))
	    return false;
	if (!(other instanceof ShTeamStoreId))
	    return false;
	ShTeamStoreId castOther = (ShTeamStoreId) other;

	return ((this.getTeamId() == castOther.getTeamId()) || (this
		.getTeamId() != null && castOther.getTeamId() != null && this
		.getTeamId().equals(castOther.getTeamId())))
		&& ((this.getStoreId() == castOther.getStoreId()) || (this
			.getStoreId() != null && castOther.getStoreId() != null && this
			.getStoreId().equals(castOther.getStoreId())));
    }

    public int hashCode() {
	int result = 17;

	result = 37 * result
		+ (getTeamId() == null ? 0 : this.getTeamId().hashCode());
	result = 37 * result
		+ (getStoreId() == null ? 0 : this.getStoreId().hashCode());
	return result;
    }

}