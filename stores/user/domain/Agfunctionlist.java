package com.oim.stores.user.domain;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Agfunctionlist entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class Agfunctionlist implements java.io.Serializable {

	// Fields

	private BigDecimal funcid;
	private String funcname;
	private BigDecimal functype;
	private String funcdesc;
	private BigDecimal parentid;
	private BigDecimal isfunc;
	private Timestamp updatedate;
	private BigDecimal updateuser;
	private BigDecimal funcorder;
	private Boolean ischeckkey;
	private Byte checktype;
	private String updateip;

	// Constructors

	/** default constructor */
	public Agfunctionlist() {
	}

	/** minimal constructor */
	public Agfunctionlist(String funcname, BigDecimal parentid,
			BigDecimal isfunc, Timestamp updatedate, BigDecimal updateuser,
			BigDecimal funcorder, Boolean ischeckkey, Byte checktype) {
		this.funcname = funcname;
		this.parentid = parentid;
		this.isfunc = isfunc;
		this.updatedate = updatedate;
		this.updateuser = updateuser;
		this.funcorder = funcorder;
		this.ischeckkey = ischeckkey;
		this.checktype = checktype;
	}

	/** full constructor */
	public Agfunctionlist(String funcname, BigDecimal functype,
			String funcdesc, BigDecimal parentid, BigDecimal isfunc,
			Timestamp updatedate, BigDecimal updateuser, BigDecimal funcorder,
			Boolean ischeckkey, Byte checktype, String updateip) {
		this.funcname = funcname;
		this.functype = functype;
		this.funcdesc = funcdesc;
		this.parentid = parentid;
		this.isfunc = isfunc;
		this.updatedate = updatedate;
		this.updateuser = updateuser;
		this.funcorder = funcorder;
		this.ischeckkey = ischeckkey;
		this.checktype = checktype;
		this.updateip = updateip;
	}

	// Property accessors

	public BigDecimal getFuncid() {
		return this.funcid;
	}

	public void setFuncid(BigDecimal funcid) {
		this.funcid = funcid;
	}

	public String getFuncname() {
		return this.funcname;
	}

	public void setFuncname(String funcname) {
		this.funcname = funcname;
	}

	public BigDecimal getFunctype() {
		return this.functype;
	}

	public void setFunctype(BigDecimal functype) {
		this.functype = functype;
	}

	public String getFuncdesc() {
		return this.funcdesc;
	}

	public void setFuncdesc(String funcdesc) {
		this.funcdesc = funcdesc;
	}

	public BigDecimal getParentid() {
		return this.parentid;
	}

	public void setParentid(BigDecimal parentid) {
		this.parentid = parentid;
	}

	public BigDecimal getIsfunc() {
		return this.isfunc;
	}

	public void setIsfunc(BigDecimal isfunc) {
		this.isfunc = isfunc;
	}

	public Timestamp getUpdatedate() {
		return this.updatedate;
	}

	public void setUpdatedate(Timestamp updatedate) {
		this.updatedate = updatedate;
	}

	public BigDecimal getUpdateuser() {
		return this.updateuser;
	}

	public void setUpdateuser(BigDecimal updateuser) {
		this.updateuser = updateuser;
	}

	public BigDecimal getFuncorder() {
		return this.funcorder;
	}

	public void setFuncorder(BigDecimal funcorder) {
		this.funcorder = funcorder;
	}

	public Boolean getIscheckkey() {
		return this.ischeckkey;
	}

	public void setIscheckkey(Boolean ischeckkey) {
		this.ischeckkey = ischeckkey;
	}

	public Byte getChecktype() {
		return this.checktype;
	}

	public void setChecktype(Byte checktype) {
		this.checktype = checktype;
	}

	public String getUpdateip() {
		return this.updateip;
	}

	public void setUpdateip(String updateip) {
		this.updateip = updateip;
	}

}