package com.oim.stores.system.domain;

import java.io.Serializable;
import java.math.BigDecimal;

@SuppressWarnings("serial")
public class SchInfoBean implements Serializable {
	private String schid;
    private String schname;
    private String status;
    private String schtime;
    private String schtimeend;
    private String memo;
    private String interval;
    private String unittype;
    private String schClass;
    private BigDecimal sendSch;
    private String cronExp;
    private BigDecimal schRepeat;
    private BigDecimal schType;
    private BigDecimal isFile;
    
    public SchInfoBean() {
	}
	public SchInfoBean(String schName,String status, BigDecimal schType, String schTime, String schTimeend, 
			BigDecimal schRepeat, String memo, String schClass, BigDecimal sendSch, String cronExp,BigDecimal isFile) {
        this.schname = schName;
        this.status = status;
        this.schType = schType;
        this.schtime = schTime;
        this.schtimeend = schTimeend;
        this.schRepeat = schRepeat;
        this.memo = memo;
        this.schClass = schClass;
        this.sendSch = sendSch;
        this.cronExp = cronExp;
        this.isFile=isFile;
    }
	
	
    public BigDecimal getIsFile() {
		return isFile;
	}
	public void setIsFile(BigDecimal isFile) {
		this.isFile = isFile;
	}
	public String getSchClass() {
		return schClass;
	}
	public void setSchClass(String schClass) {
		this.schClass = schClass;
	}
	public BigDecimal getSendSch() {
		return sendSch;
	}
	public void setSendSch(BigDecimal sendSch) {
		this.sendSch = sendSch;
	}
	public String getCronExp() {
		return cronExp;
	}
	public void setCronExp(String cronExp) {
		this.cronExp = cronExp;
	}
	public BigDecimal getSchRepeat() {
		return schRepeat;
	}
	public void setSchRepeat(BigDecimal schRepeat) {
		this.schRepeat = schRepeat;
	}
	public BigDecimal getSchType() {
		return schType;
	}
	public void setSchType(BigDecimal schType) {
		this.schType = schType;
	}
	/**
     * @return the unittype
     */
    public String getUnittype() {
        return unittype;
    }
    /**
     * @param unittype the unittype to set
     */
    public void setUnittype(String unittype) {
        this.unittype = unittype;
    }
    /**
     * @return the interval
     */
    public String getInterval() {
        return interval;
    }
    /**
     * @param interval the interval to set
     */
    public void setInterval(String interval) {
        this.interval = interval;
    }
    /**
     * @return the schid
     */
    public String getSchid() {
        return schid;
    }
    /**
     * @param schid the schid to set
     */
    public void setSchid(String schid) {
        this.schid = schid;
    }
    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }
    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }
    /**
     * @return the memo
     */
    public String getMemo() {
        return memo;
    }
    
	public String getSchname() {
		return schname;
	}
	public void setSchname(String schname) {
		this.schname = schname;
	}
	public String getSchtime() {
		return schtime;
	}
	public void setSchtime(String schtime) {
		this.schtime = schtime;
	}
	public String getSchtimeend() {
		return schtimeend;
	}
	public void setSchtimeend(String schtimeend) {
		this.schtimeend = schtimeend;
	}
	/**
     * @param memo the memo to set
     */
    public void setMemo(String memo) {
        this.memo = memo;
    }
    
    
}
