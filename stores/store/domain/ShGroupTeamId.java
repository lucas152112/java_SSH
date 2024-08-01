package com.oim.stores.store.domain;
// default package



/**
 * ShGroupTeamId entity. @author MyEclipse Persistence Tools
 */

public class ShGroupTeamId  implements java.io.Serializable {


    // Fields    

     private Long teamId;
     private Long subTeamId;


    // Constructors

    /** default constructor */
    public ShGroupTeamId() {
    }

    
    /** full constructor */
    public ShGroupTeamId(Long teamId, Long subTeamId) {
        this.teamId = teamId;
        this.subTeamId = subTeamId;
    }

   
    // Property accessors

    public Long getTeamId() {
        return this.teamId;
    }
    
    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    public Long getSubTeamId() {
        return this.subTeamId;
    }
    
    public void setSubTeamId(Long subTeamId) {
        this.subTeamId = subTeamId;
    }
   



   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof ShGroupTeamId) ) return false;
		 ShGroupTeamId castOther = ( ShGroupTeamId ) other; 
         
		 return ( (this.getTeamId()==castOther.getTeamId()) || ( this.getTeamId()!=null && castOther.getTeamId()!=null && this.getTeamId().equals(castOther.getTeamId()) ) )
 && ( (this.getSubTeamId()==castOther.getSubTeamId()) || ( this.getSubTeamId()!=null && castOther.getSubTeamId()!=null && this.getSubTeamId().equals(castOther.getSubTeamId()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getTeamId() == null ? 0 : this.getTeamId().hashCode() );
         result = 37 * result + ( getSubTeamId() == null ? 0 : this.getSubTeamId().hashCode() );
         return result;
   }   





}