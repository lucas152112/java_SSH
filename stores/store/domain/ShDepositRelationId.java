package com.oim.stores.store.domain;
// default package

import java.math.BigDecimal;

/**
 * SyDepositRelationId entity. @author MyEclipse Persistence Tools
 */

public class ShDepositRelationId implements java.io.Serializable {

    // Fields

    private Long teamId;
    private Long storeid;

    // Constructors

    /** default constructor */
    public ShDepositRelationId() {
    }

    /** full constructor */
    public ShDepositRelationId(Long teamId, Long storeid) {
	this.teamId = teamId;
	this.storeid = storeid;
    }

    // Property accessors

    public Long getTeamId() {
	return this.teamId;
    }

    public void setTeamId(Long teamId) {
	this.teamId = teamId;
    }

    public Long getStoreid() {
	return this.storeid;
    }

    public void setStoreid(Long storeid) {
	this.storeid = storeid;
    }

    public boolean equals(Object other) {
	if ((this == other))
	    return true;
	if ((other == null))
	    return false;
	if (!(other instanceof ShDepositRelationId))
	    return false;
	ShDepositRelationId castOther = (ShDepositRelationId) other;

	return ((this.getTeamId() == castOther.getTeamId()) || (this
		.getTeamId() != null && castOther.getTeamId() != null && this
		.getTeamId().equals(castOther.getTeamId())))
		&& ((this.getStoreid() == castOther.getStoreid()) || (this
			.getStoreid() != null && castOther.getStoreid() != null && this
			.getStoreid().equals(castOther.getStoreid())));
    }

    public int hashCode() {
	int result = 17;

	result = 37 * result
		+ (getTeamId() == null ? 0 : this.getTeamId().hashCode());
	result = 37 * result
		+ (getStoreid() == null ? 0 : this.getStoreid().hashCode());
	return result;
    }

}