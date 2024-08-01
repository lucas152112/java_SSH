package com.oim.stores.user.domain;

import java.math.BigDecimal;
import java.util.Date;

@SuppressWarnings("serial")
public class SyUserAccountDetailId  implements java.io.Serializable {

     private Date changeDate;
     private BigDecimal accountId;
     private BigDecimal dataType;

    /** default constructor */
    public SyUserAccountDetailId() {
    }

    
    /** full constructor */
    public SyUserAccountDetailId(Date changeDate, BigDecimal accountId, BigDecimal dataType) {
        this.changeDate = changeDate;
        this.accountId = accountId;
        this.dataType = dataType;
    }

   
    // Property accessors

    public Date getChangeDate() {
        return this.changeDate;
    }
    
    public void setChangeDate(Date changeDate) {
        this.changeDate = changeDate;
    }

    public BigDecimal getAccountId() {
        return this.accountId;
    }
    
    public void setAccountId(BigDecimal accountId) {
        this.accountId = accountId;
    }

    public BigDecimal getDataType() {
        return this.dataType;
    }
    
    public void setDataType(BigDecimal dataType) {
        this.dataType = dataType;
    }
   



   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof SyUserAccountDetailId) ) return false;
		 SyUserAccountDetailId castOther = ( SyUserAccountDetailId ) other; 
         
		 return ( (this.getChangeDate()==castOther.getChangeDate()) || ( this.getChangeDate()!=null && castOther.getChangeDate()!=null && this.getChangeDate().equals(castOther.getChangeDate()) ) )
 && ( (this.getAccountId()==castOther.getAccountId()) || ( this.getAccountId()!=null && castOther.getAccountId()!=null && this.getAccountId().equals(castOther.getAccountId()) ) )
 && ( (this.getDataType()==castOther.getDataType()) || ( this.getDataType()!=null && castOther.getDataType()!=null && this.getDataType().equals(castOther.getDataType()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getChangeDate() == null ? 0 : this.getChangeDate().hashCode() );
         result = 37 * result + ( getAccountId() == null ? 0 : this.getAccountId().hashCode() );
         result = 37 * result + ( getDataType() == null ? 0 : this.getDataType().hashCode() );
         return result;
   }   





}