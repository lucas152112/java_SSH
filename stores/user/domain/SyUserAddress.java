package com.oim.stores.user.domain;

import java.math.BigDecimal;
import java.util.Date;

@SuppressWarnings("serial")
public class SyUserAddress  implements java.io.Serializable {

     private BigDecimal addressId;
     private BigDecimal userId;
     private String recivieName;
     private String address;
     private String tel;
     private String mobile;
     private String province;
     private String city;
     private String county;
     private String zipcode;
     private BigDecimal isDefault;
     private Date addDate;
     private Date updateDate;
     private String cardId;

    /** default constructor */
    public SyUserAddress() {
    }

	/** minimal constructor */
    public SyUserAddress(BigDecimal userId, String recivieName, String address, String province, String city, String county, BigDecimal isDefault, Date addDate, Date updateDate) {
        this.userId = userId;
        this.recivieName = recivieName;
        this.address = address;
        this.province = province;
        this.city = city;
        this.county = county;
        this.isDefault = isDefault;
        this.addDate = addDate;
        this.updateDate = updateDate;
    }
    
    /** full constructor */
    public SyUserAddress(BigDecimal userId, String recivieName, String address, String tel, String mobile, String province, String city, String county, String zipcode, BigDecimal isDefault, Date addDate, Date updateDate, String cardId) {
        this.userId = userId;
        this.recivieName = recivieName;
        this.address = address;
        this.tel = tel;
        this.mobile = mobile;
        this.province = province;
        this.city = city;
        this.county = county;
        this.zipcode = zipcode;
        this.isDefault = isDefault;
        this.addDate = addDate;
        this.updateDate = updateDate;
        this.cardId = cardId;
    }

   
    // Property accessors

    public BigDecimal getAddressId() {
        return this.addressId;
    }
    
    public void setAddressId(BigDecimal addressId) {
        this.addressId = addressId;
    }

    public BigDecimal getUserId() {
        return this.userId;
    }
    
    public void setUserId(BigDecimal userId) {
        this.userId = userId;
    }

    public String getRecivieName() {
        return this.recivieName;
    }
    
    public void setRecivieName(String recivieName) {
        this.recivieName = recivieName;
    }

    public String getAddress() {
        return this.address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }

    public String getTel() {
        return this.tel;
    }
    
    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getMobile() {
        return this.mobile;
    }
    
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getProvince() {
        return this.province;
    }
    
    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return this.city;
    }
    
    public void setCity(String city) {
        this.city = city;
    }

    public String getCounty() {
        return this.county;
    }
    
    public void setCounty(String county) {
        this.county = county;
    }

    public String getZipcode() {
        return this.zipcode;
    }
    
    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public BigDecimal getIsDefault() {
        return this.isDefault;
    }
    
    public void setIsDefault(BigDecimal isDefault) {
        this.isDefault = isDefault;
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

    public String getCardId() {
        return this.cardId;
    }
    
    public void setCardId(String cardId) {
        this.cardId = cardId;
    }
   








}