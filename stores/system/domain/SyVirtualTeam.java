package com.oim.stores.system.domain;
// default package

import java.math.BigDecimal;

/**
 * SyVirtualTeam entity. @author MyEclipse Persistence Tools
 */

public class SyVirtualTeam implements java.io.Serializable {

    // Fields

    private Long teamId;
    private String teamName;
    private BigDecimal parentTeamId;
    private String teamCode;
    private BigDecimal teamSeq;
    private BigDecimal teamType;
    private BigDecimal regionalId;

    // Constructors

    /** default constructor */
    public SyVirtualTeam() {
    }

    /** full constructor */
    public SyVirtualTeam(String teamName, BigDecimal parentTeamId,
	    String teamCode, BigDecimal teamSeq, BigDecimal teamType,BigDecimal regionalId) {
	this.teamName = teamName;
	this.parentTeamId = parentTeamId;
	this.teamCode = teamCode;
	this.teamSeq = teamSeq;
	this.teamType = teamType;
	this.regionalId=regionalId;
    }

    // Property accessors

    
    public Long getTeamId() {
	return this.teamId;
    }

    public BigDecimal getRegionalId() {
		return regionalId;
	}

	public void setRegionalId(BigDecimal regionalId) {
		this.regionalId = regionalId;
	}

	public void setTeamId(Long teamId) {
	this.teamId = teamId;
    }

    public String getTeamName() {
	return this.teamName;
    }

    public void setTeamName(String teamName) {
	this.teamName = teamName;
    }

    public BigDecimal getParentTeamId() {
	return this.parentTeamId;
    }

    public void setParentTeamId(BigDecimal parentTeamId) {
	this.parentTeamId = parentTeamId;
    }

    public String getTeamCode() {
	return this.teamCode;
    }

    public void setTeamCode(String teamCode) {
	this.teamCode = teamCode;
    }

    public BigDecimal getTeamSeq() {
	return this.teamSeq;
    }

    public void setTeamSeq(BigDecimal teamSeq) {
	this.teamSeq = teamSeq;
    }

    public BigDecimal getTeamType() {
	return this.teamType;
    }

    public void setTeamType(BigDecimal teamType) {
	this.teamType = teamType;
    }

}