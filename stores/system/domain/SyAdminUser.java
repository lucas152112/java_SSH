package com.oim.stores.system.domain;
// default package

import java.math.BigDecimal;

/**
 * SyAdminUser entity. @author MyEclipse Persistence Tools
 */

public class SyAdminUser implements java.io.Serializable {

    // Fields

    private BigDecimal userId;
    private String userName;
    private String userRealname;
    private String userPwd;
    private String mobile;
    private String telephone;
    private String mail;
    private String sex;
    private BigDecimal userStatus;

    // Constructors

    /** default constructor */
    public SyAdminUser() {
    }

    /** full constructor */
    public SyAdminUser(String userName, String userRealname, String userPwd,
	    String mobile, String telephone, String mail, String sex,
	    BigDecimal userStatus) {
	this.userName = userName;
	this.userRealname = userRealname;
	this.userPwd = userPwd;
	this.mobile = mobile;
	this.telephone = telephone;
	this.mail = mail;
	this.sex = sex;
	this.userStatus = userStatus;
    }

    // Property accessors

    public BigDecimal getUserId() {
	return this.userId;
    }

    public void setUserId(BigDecimal userId) {
	this.userId = userId;
    }

    public String getUserName() {
	return this.userName;
    }

    public void setUserName(String userName) {
	this.userName = userName;
    }

    public String getUserRealname() {
	return this.userRealname;
    }

    public void setUserRealname(String userRealname) {
	this.userRealname = userRealname;
    }

    public String getUserPwd() {
	return this.userPwd;
    }

    public void setUserPwd(String userPwd) {
	this.userPwd = userPwd;
    }

    public String getMobile() {
	return this.mobile;
    }

    public void setMobile(String mobile) {
	this.mobile = mobile;
    }

    public String getTelephone() {
	return this.telephone;
    }

    public void setTelephone(String telephone) {
	this.telephone = telephone;
    }

    public String getMail() {
	return this.mail;
    }

    public void setMail(String mail) {
	this.mail = mail;
    }

    public String getSex() {
	return this.sex;
    }

    public void setSex(String sex) {
	this.sex = sex;
    }

    public BigDecimal getUserStatus() {
	return this.userStatus;
    }

    public void setUserStatus(BigDecimal userStatus) {
	this.userStatus = userStatus;
    }

}