package com.oim.stores.system.domain;
// default package

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * SyLogInfo entity. @author MyEclipse Persistence Tools
 */

public class SyLogInfo implements java.io.Serializable {

    // Fields

    private BigDecimal logId;
    private BigDecimal logUserId;
    private Timestamp logTime;
    private String logCode;
    private String logAction;
    private String logIp;
    private String logDesc;
    private String logData;

    // Constructors

    /** default constructor */
    public SyLogInfo() {
    }

    /** full constructor */
    public SyLogInfo(BigDecimal logUserId, Timestamp logTime, String logCode,
	    String logAction, String logIp, String logDesc, String logData) {
	this.logUserId = logUserId;
	this.logTime = logTime;
	this.logCode = logCode;
	this.logAction = logAction;
	this.logIp = logIp;
	this.logDesc = logDesc;
	this.logData = logData;
    }

    // Property accessors

    public BigDecimal getLogId() {
	return this.logId;
    }

    public void setLogId(BigDecimal logId) {
	this.logId = logId;
    }

    public BigDecimal getLogUserId() {
	return this.logUserId;
    }

    public void setLogUserId(BigDecimal logUserId) {
	this.logUserId = logUserId;
    }

    public Timestamp getLogTime() {
	return this.logTime;
    }

    public void setLogTime(Timestamp logTime) {
	this.logTime = logTime;
    }

    public String getLogCode() {
	return this.logCode;
    }

    public void setLogCode(String logCode) {
	this.logCode = logCode;
    }

    public String getLogAction() {
	return this.logAction;
    }

    public void setLogAction(String logAction) {
	this.logAction = logAction;
    }

    public String getLogIp() {
	return this.logIp;
    }

    public void setLogIp(String logIp) {
	this.logIp = logIp;
    }

    public String getLogDesc() {
	return this.logDesc;
    }

    public void setLogDesc(String logDesc) {
	this.logDesc = logDesc;
    }

    public String getLogData() {
	return this.logData;
    }

    public void setLogData(String logData) {
	this.logData = logData;
    }

}