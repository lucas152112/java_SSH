package com.oim.stores.store.domain;

import java.util.Date;

/**
 * ShStoreAccount entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
public class ShStoreAccount implements java.io.Serializable {

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

	// Constructors

	/** default constructor */
	public ShStoreAccount() {
	}

	/** minimal constructor */
	public ShStoreAccount(Long storeId, Long accountType, Double amount, Long accountStatus, Date updateDate) {
		this.storeId = storeId;
		this.accountType = accountType;
		this.amount = amount;
		this.accountStatus = accountStatus;
		this.updateDate = updateDate;
	}

	/** full constructor */

	public ShStoreAccount(Long storeId, Long accountType, Double amount,String accountNum, Double amountFrozen, Long accountStatus, Date addDate, Date updateDate) {

		this.storeId = storeId;
		this.accountType = accountType;
		this.amount = amount;
		this.amountFrozen = amountFrozen;
		this.accountStatus = accountStatus;
		this.addDate = addDate;
		this.updateDate = updateDate;
		this.accountNum=accountNum;
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
		return accountNum;
	}

	public void setAccountNum(String accountNum) {
		this.accountNum = accountNum;
	}

}