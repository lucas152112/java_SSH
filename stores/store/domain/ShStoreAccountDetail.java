package com.oim.stores.store.domain;

/**
 * ShStoreAccountDetail entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
public class ShStoreAccountDetail implements java.io.Serializable {

	// Fields

	private ShStoreAccountDetailId id;
	private Double amount;
	private Double amountOver;
	private String dataDesc;
	private String relateNumber;

	// Constructors

	/** default constructor */
	public ShStoreAccountDetail() {
	}

	/** minimal constructor */
	public ShStoreAccountDetail(ShStoreAccountDetailId id, Double amount,
			Double amountOver) {
		this.id = id;
		this.amount = amount;
		this.amountOver = amountOver;
	}

	/** full constructor */
	public ShStoreAccountDetail(ShStoreAccountDetailId id, Double amount,
			Double amountOver, String dataDesc, String relateNumber) {
		this.id = id;
		this.amount = amount;
		this.amountOver = amountOver;
		this.dataDesc = dataDesc;
		this.relateNumber = relateNumber;
	}

	// Property accessors

	public ShStoreAccountDetailId getId() {
		return this.id;
	}

	public void setId(ShStoreAccountDetailId id) {
		this.id = id;
	}

	public Double getAmount() {
		return this.amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Double getAmountOver() {
		return this.amountOver;
	}

	public void setAmountOver(Double amountOver) {
		this.amountOver = amountOver;
	}

	public String getDataDesc() {
		return this.dataDesc;
	}

	public void setDataDesc(String dataDesc) {
		this.dataDesc = dataDesc;
	}

	public String getRelateNumber() {
		return this.relateNumber;
	}

	public void setRelateNumber(String relateNumber) {
		this.relateNumber = relateNumber;
	}

}