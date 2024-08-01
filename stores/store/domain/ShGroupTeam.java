package com.oim.stores.store.domain;
// default package



/**
 * ShGroupTeam entity. @author MyEclipse Persistence Tools
 */

public class ShGroupTeam  implements java.io.Serializable {


    // Fields    

     private ShGroupTeamId id;


    // Constructors

    /** default constructor */
    public ShGroupTeam() {
    }

    
    /** full constructor */
    public ShGroupTeam(ShGroupTeamId id) {
        this.id = id;
    }

   
    // Property accessors

    public ShGroupTeamId getId() {
        return this.id;
    }
    
    public void setId(ShGroupTeamId id) {
        this.id = id;
    }
   








}