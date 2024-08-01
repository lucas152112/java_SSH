package com.oim.stores.store.domain;

import java.math.BigDecimal;
// default package

/**
 * SyDepositRelation entity. @author MyEclipse Persistence Tools
 */

public class ShDepositRelation implements java.io.Serializable {

    // Fields

    private ShDepositRelationId id;
    private BigDecimal tsId;

    // Constructors

    /** default constructor */
    public ShDepositRelation() {
    }

    /** full constructor */
    public ShDepositRelation(ShDepositRelationId id) {
	this.id = id;
    }

    // Property accessors

    public ShDepositRelationId getId() {
	return this.id;
    }

    public void setId(ShDepositRelationId id) {
	this.id = id;
    }

    public BigDecimal getTsId() {
	return this.tsId;
    }

    public void setTsId(BigDecimal tsId) {
	this.tsId = tsId;
    }
}