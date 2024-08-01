package com.oim.stores.user.domain;

@SuppressWarnings("serial")
public class SyUserAccountDetail  implements java.io.Serializable {

     private SyUserAccountDetailId id;
     private Double amount;
     private Double amountOver;
     private String dataDesc;
     private String relateNumber;

    /** default constructor */
    public SyUserAccountDetail() {
    }

	/** minimal constructor */
    public SyUserAccountDetail(SyUserAccountDetailId id, Double amount, Double amountOver) {
        this.id = id;
        this.amount = amount;
        this.amountOver = amountOver;
    }
    
    /** full constructor */
    public SyUserAccountDetail(SyUserAccountDetailId id, Double amount, Double amountOver, String dataDesc, String relateNumber) {
        this.id = id;
        this.amount = amount;
        this.amountOver = amountOver;
        this.dataDesc = dataDesc;
        this.relateNumber = relateNumber;
    }

   
    // Property accessors

    public SyUserAccountDetailId getId() {
        return this.id;
    }
    
    public void setId(SyUserAccountDetailId id) {
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