package com.oim.stores.store.domain;
// default package

/**
 * ShTeamStore entity. @author MyEclipse Persistence Tools
 */

public class ShTeamStore implements java.io.Serializable {

    // Fields

    private ShTeamStoreId id;

    // Constructors

    /** default constructor */
    public ShTeamStore() {
    }

    /** full constructor */
    public ShTeamStore(ShTeamStoreId id) {
	this.id = id;
    }

    // Property accessors

    public ShTeamStoreId getId() {
	return this.id;
    }

    public void setId(ShTeamStoreId id) {
	this.id = id;
    }

}