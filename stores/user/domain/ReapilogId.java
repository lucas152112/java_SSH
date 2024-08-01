package com.oim.stores.user.domain;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * ReapilogId entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class ReapilogId implements java.io.Serializable {

	// Fields

	private Timestamp requesttime;
	private String factorid;
	private BigDecimal funcid;
	private BigDecimal requesttype;

	// Constructors

	/** default constructor */
	public ReapilogId() {
	}

	/** full constructor */
	public ReapilogId(Timestamp requesttime, String factorid,
			BigDecimal funcid, BigDecimal requesttype) {
		this.requesttime = requesttime;
		this.factorid = factorid;
		this.funcid = funcid;
		this.requesttype = requesttype;
	}

	// Property accessors

	public Timestamp getRequesttime() {
		return this.requesttime;
	}

	public void setRequesttime(Timestamp requesttime) {
		this.requesttime = requesttime;
	}

	public String getFactorid() {
		return this.factorid;
	}

	public void setFactorid(String factorid) {
		this.factorid = factorid;
	}

	public BigDecimal getFuncid() {
		return this.funcid;
	}

	public void setFuncid(BigDecimal funcid) {
		this.funcid = funcid;
	}

	public BigDecimal getRequesttype() {
		return this.requesttype;
	}

	public void setRequesttype(BigDecimal requesttype) {
		this.requesttype = requesttype;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof ReapilogId))
			return false;
		ReapilogId castOther = (ReapilogId) other;

		return ((this.getRequesttime() == castOther.getRequesttime()) || (this
				.getRequesttime() != null
				&& castOther.getRequesttime() != null && this.getRequesttime()
				.equals(castOther.getRequesttime())))
				&& ((this.getFactorid() == castOther.getFactorid()) || (this
						.getFactorid() != null
						&& castOther.getFactorid() != null && this
						.getFactorid().equals(castOther.getFactorid())))
				&& ((this.getFuncid() == castOther.getFuncid()) || (this
						.getFuncid() != null
						&& castOther.getFuncid() != null && this.getFuncid()
						.equals(castOther.getFuncid())))
				&& ((this.getRequesttype() == castOther.getRequesttype()) || (this
						.getRequesttype() != null
						&& castOther.getRequesttype() != null && this
						.getRequesttype().equals(castOther.getRequesttype())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getRequesttime() == null ? 0 : this.getRequesttime()
						.hashCode());
		result = 37 * result
				+ (getFactorid() == null ? 0 : this.getFactorid().hashCode());
		result = 37 * result
				+ (getFuncid() == null ? 0 : this.getFuncid().hashCode());
		result = 37
				* result
				+ (getRequesttype() == null ? 0 : this.getRequesttype()
						.hashCode());
		return result;
	}

}