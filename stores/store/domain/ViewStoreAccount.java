package com.oim.stores.store.domain;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;


/**
 * ViewStoreAccountId entity. @author MyEclipse Persistence Tools
 */

public class ViewStoreAccount  implements java.io.Serializable {


    // Fields    

     private Long storeAccountId;
     private Long storeId;
     private Long accountType;
     private Double amount;
     private Double amountFrozen;
     private Long accountStatus;
     private Date addDate;
     private Date updateDate;
     private String accountNum;
     private String accountTypeDesc;
     private String storeName;
     private String storeShortDesc;
     private BigDecimal storeType;
     private BigDecimal storeMode;


    // Constructors

    /** default constructor */
    public ViewStoreAccount() {
    }

	/** minimal constructor */
    public ViewStoreAccount(Long storeAccountId, Long storeId, Long accountType,
    		Double amount, Long accountStatus, Date updateDate, String storeName, String storeShortDesc, BigDecimal storeType) {
        this.storeAccountId = storeAccountId;
        this.storeId = storeId;
        this.accountType = accountType;
        this.amount = amount;
        this.accountStatus = accountStatus;
        this.updateDate = updateDate;
        this.storeName = storeName;
        this.storeShortDesc = storeShortDesc;
        this.storeType = storeType;
    }
    
    /** full constructor */
    public ViewStoreAccount(Long storeAccountId, Long storeId, Long accountType, 
    		Double amount, Double amountFrozen, Long accountStatus, 
    		Date addDate, Date updateDate, String accountNum, String accountTypeDesc, String storeName,
    		String storeShortDesc, BigDecimal storeType, BigDecimal storeMode) {
        this.storeAccountId = storeAccountId;
        this.storeId = storeId;
        this.accountType = accountType;
        this.amount = amount;
        this.amountFrozen = amountFrozen;
        this.accountStatus = accountStatus;
        this.addDate = addDate;
        this.updateDate = updateDate;
        this.accountNum = accountNum;
        this.accountTypeDesc = accountTypeDesc;
        this.storeName = storeName;
        this.storeShortDesc = storeShortDesc;
        this.storeType = storeType;
        this.storeMode = storeMode;
    }

   
    // Property accessors

    public Long getStoreAccountId() {
        return this.storeAccountId;
    }
    
    public void setStoreAccountId(Long storeAccountId) {
        this.storeAccountId = storeAccountId;
    }

    public Long getStoreId() {
        return this.storeId;
    }
    
    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public Long getAccountType() {
        return this.accountType;
    }
    
    public void setAccountType(Long accountType) {
        this.accountType = accountType;
    }

    public Double getAmount() {
        return this.amount;
    }
    
    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getAmountFrozen() {
        return this.amountFrozen;
    }
    
    public void setAmountFrozen(Double amountFrozen) {
        this.amountFrozen = amountFrozen;
    }

    public Long getAccountStatus() {
        return this.accountStatus;
    }
    
    public void setAccountStatus(Long accountStatus) {
        this.accountStatus = accountStatus;
    }

    public Date getAddDate() {
        return this.addDate;
    }
    
    public void setAddDate(Date addDate) {
        this.addDate = addDate;
    }

    public Date getUpdateDate() {
        return this.updateDate;
    }
    
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getAccountNum() {
        return this.accountNum;
    }
    
    public void setAccountNum(String accountNum) {
        this.accountNum = accountNum;
    }

    public String getAccountTypeDesc() {
        return this.accountTypeDesc;
    }
    
    public void setAccountTypeDesc(String accountTypeDesc) {
        this.accountTypeDesc = accountTypeDesc;
    }

    public String getStoreName() {
        return this.storeName;
    }
    
    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStoreShortDesc() {
        return this.storeShortDesc;
    }
    
    public void setStoreShortDesc(String storeShortDesc) {
        this.storeShortDesc = storeShortDesc;
    }

    public BigDecimal getStoreType() {
        return this.storeType;
    }
    
    public void setStoreType(BigDecimal storeType) {
        this.storeType = storeType;
    }

    public BigDecimal getStoreMode() {
        return this.storeMode;
    }
    
    public void setStoreMode(BigDecimal storeMode) {
        this.storeMode = storeMode;
    }
   



   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof ViewStoreAccount) ) return false;
		 ViewStoreAccount castOther = ( ViewStoreAccount ) other; 
         
		 return ( (this.getStoreAccountId()==castOther.getStoreAccountId()) || ( this.getStoreAccountId()!=null && castOther.getStoreAccountId()!=null && this.getStoreAccountId().equals(castOther.getStoreAccountId()) ) )
 && ( (this.getStoreId()==castOther.getStoreId()) || ( this.getStoreId()!=null && castOther.getStoreId()!=null && this.getStoreId().equals(castOther.getStoreId()) ) )
 && ( (this.getAccountType()==castOther.getAccountType()) || ( this.getAccountType()!=null && castOther.getAccountType()!=null && this.getAccountType().equals(castOther.getAccountType()) ) )
 && ( (this.getAmount()==castOther.getAmount()) || ( this.getAmount()!=null && castOther.getAmount()!=null && this.getAmount().equals(castOther.getAmount()) ) )
 && ( (this.getAmountFrozen()==castOther.getAmountFrozen()) || ( this.getAmountFrozen()!=null && castOther.getAmountFrozen()!=null && this.getAmountFrozen().equals(castOther.getAmountFrozen()) ) )
 && ( (this.getAccountStatus()==castOther.getAccountStatus()) || ( this.getAccountStatus()!=null && castOther.getAccountStatus()!=null && this.getAccountStatus().equals(castOther.getAccountStatus()) ) )
 && ( (this.getAddDate()==castOther.getAddDate()) || ( this.getAddDate()!=null && castOther.getAddDate()!=null && this.getAddDate().equals(castOther.getAddDate()) ) )
 && ( (this.getUpdateDate()==castOther.getUpdateDate()) || ( this.getUpdateDate()!=null && castOther.getUpdateDate()!=null && this.getUpdateDate().equals(castOther.getUpdateDate()) ) )
 && ( (this.getAccountNum()==castOther.getAccountNum()) || ( this.getAccountNum()!=null && castOther.getAccountNum()!=null && this.getAccountNum().equals(castOther.getAccountNum()) ) )
 && ( (this.getAccountTypeDesc()==castOther.getAccountTypeDesc()) || ( this.getAccountTypeDesc()!=null && castOther.getAccountTypeDesc()!=null && this.getAccountTypeDesc().equals(castOther.getAccountTypeDesc()) ) )
 && ( (this.getStoreName()==castOther.getStoreName()) || ( this.getStoreName()!=null && castOther.getStoreName()!=null && this.getStoreName().equals(castOther.getStoreName()) ) )
 && ( (this.getStoreShortDesc()==castOther.getStoreShortDesc()) || ( this.getStoreShortDesc()!=null && castOther.getStoreShortDesc()!=null && this.getStoreShortDesc().equals(castOther.getStoreShortDesc()) ) )
 && ( (this.getStoreType()==castOther.getStoreType()) || ( this.getStoreType()!=null && castOther.getStoreType()!=null && this.getStoreType().equals(castOther.getStoreType()) ) )
 && ( (this.getStoreMode()==castOther.getStoreMode()) || ( this.getStoreMode()!=null && castOther.getStoreMode()!=null && this.getStoreMode().equals(castOther.getStoreMode()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getStoreAccountId() == null ? 0 : this.getStoreAccountId().hashCode() );
         result = 37 * result + ( getStoreId() == null ? 0 : this.getStoreId().hashCode() );
         result = 37 * result + ( getAccountType() == null ? 0 : this.getAccountType().hashCode() );
         result = 37 * result + ( getAmount() == null ? 0 : this.getAmount().hashCode() );
         result = 37 * result + ( getAmountFrozen() == null ? 0 : this.getAmountFrozen().hashCode() );
         result = 37 * result + ( getAccountStatus() == null ? 0 : this.getAccountStatus().hashCode() );
         result = 37 * result + ( getAddDate() == null ? 0 : this.getAddDate().hashCode() );
         result = 37 * result + ( getUpdateDate() == null ? 0 : this.getUpdateDate().hashCode() );
         result = 37 * result + ( getAccountNum() == null ? 0 : this.getAccountNum().hashCode() );
         result = 37 * result + ( getAccountTypeDesc() == null ? 0 : this.getAccountTypeDesc().hashCode() );
         result = 37 * result + ( getStoreName() == null ? 0 : this.getStoreName().hashCode() );
         result = 37 * result + ( getStoreShortDesc() == null ? 0 : this.getStoreShortDesc().hashCode() );
         result = 37 * result + ( getStoreType() == null ? 0 : this.getStoreType().hashCode() );
         result = 37 * result + ( getStoreMode() == null ? 0 : this.getStoreMode().hashCode() );
         return result;
   }   





}