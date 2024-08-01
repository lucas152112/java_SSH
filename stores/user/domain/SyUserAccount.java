package com.oim.stores.user.domain;

import java.math.BigDecimal;
import java.util.Date;

@SuppressWarnings("serial")
public class SyUserAccount  implements java.io.Serializable {

     private BigDecimal accountId;
     private BigDecimal userId;
     private BigDecimal accountType;
     private Double amount;
     private Double amountFrozen;
     private BigDecimal accountStatus;
     private Date addDate;
     private Date updateDate;

    /** default constructor */
    public SyUserAccount() {
    }

	/** minimal constructor */
    public SyUserAccount(BigDecimal userId, BigDecimal accountType, Double amount, BigDecimal accountStatus, Date addDate, Date updateDate) {
        this.userId = userId;
        this.accountType = accountType;
        this.amount = amount;
        this.accountStatus = accountStatus;
        this.addDate = addDate;
        this.updateDate = updateDate;
    }
    
    /** full constructor */
    public SyUserAccount(BigDecimal userId, BigDecimal accountType, Double amount, Double amountFrozen, BigDecimal accountStatus, Date addDate, Date updateDate) {
        this.userId = userId;
        this.accountType = accountType;
        this.amount = amount;
        this.amountFrozen = amountFrozen;
        this.accountStatus = accountStatus;
        this.addDate = addDate;
        this.updateDate = updateDate;
    }

   
    // Property accessors

    public BigDecimal getAccountId() {
        return this.accountId;
    }
    
    public void setAccountId(BigDecimal accountId) {
        this.accountId = accountId;
    }

    public BigDecimal getUserId() {
        return this.userId;
    }
    
    public void setUserId(BigDecimal userId) {
        this.userId = userId;
    }

    public BigDecimal getAccountType() {
        return this.accountType;
    }
    
    public void setAccountType(BigDecimal accountType) {
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

    public BigDecimal getAccountStatus() {
        return this.accountStatus;
    }
    
    public void setAccountStatus(BigDecimal accountStatus) {
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
   








}