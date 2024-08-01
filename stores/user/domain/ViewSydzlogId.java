package com.oim.stores.user.domain;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * ViewSydzlogId entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class ViewSydzlogId implements java.io.Serializable {

	// Fields

	private BigDecimal dzlogid;
	private String dzid;
	private Timestamp accdate;
	private Timestamp processdate;
	private String filename;
	private String dzresult;
	private String note;
	private String dzname;
	private String status;
	private String doadjust;
	private String dzclass;

	// Constructors

	/** default constructor */
	public ViewSydzlogId() {
	}

	/** minimal constructor */
	public ViewSydzlogId(BigDecimal dzlogid) {
		this.dzlogid = dzlogid;
	}

	/** full constructor */
	public ViewSydzlogId(BigDecimal dzlogid, String dzid, Timestamp accdate,
			Timestamp processdate, String filename, String dzresult,
			String note, String dzname, String status, String doadjust,
			String dzclass) {
		this.dzlogid = dzlogid;
		this.dzid = dzid;
		this.accdate = accdate;
		this.processdate = processdate;
		this.filename = filename;
		this.dzresult = dzresult;
		this.note = note;
		this.dzname = dzname;
		this.status = status;
		this.doadjust = doadjust;
		this.dzclass = dzclass;
	}

	// Property accessors

	public BigDecimal getDzlogid() {
		return this.dzlogid;
	}

	public void setDzlogid(BigDecimal dzlogid) {
		this.dzlogid = dzlogid;
	}

	public String getDzid() {
		return this.dzid;
	}

	public void setDzid(String dzid) {
		this.dzid = dzid;
	}

	public Timestamp getAccdate() {
		return this.accdate;
	}

	public void setAccdate(Timestamp accdate) {
		this.accdate = accdate;
	}

	public Timestamp getProcessdate() {
		return this.processdate;
	}

	public void setProcessdate(Timestamp processdate) {
		this.processdate = processdate;
	}

	public String getFilename() {
		return this.filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getDzresult() {
		return this.dzresult;
	}

	public void setDzresult(String dzresult) {
		this.dzresult = dzresult;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getDzname() {
		return this.dzname;
	}

	public void setDzname(String dzname) {
		this.dzname = dzname;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDoadjust() {
		return this.doadjust;
	}

	public void setDoadjust(String doadjust) {
		this.doadjust = doadjust;
	}

	public String getDzclass() {
		return this.dzclass;
	}

	public void setDzclass(String dzclass) {
		this.dzclass = dzclass;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof ViewSydzlogId))
			return false;
		ViewSydzlogId castOther = (ViewSydzlogId) other;

		return ((this.getDzlogid() == castOther.getDzlogid()) || (this
				.getDzlogid() != null
				&& castOther.getDzlogid() != null && this.getDzlogid().equals(
				castOther.getDzlogid())))
				&& ((this.getDzid() == castOther.getDzid()) || (this.getDzid() != null
						&& castOther.getDzid() != null && this.getDzid()
						.equals(castOther.getDzid())))
				&& ((this.getAccdate() == castOther.getAccdate()) || (this
						.getAccdate() != null
						&& castOther.getAccdate() != null && this.getAccdate()
						.equals(castOther.getAccdate())))
				&& ((this.getProcessdate() == castOther.getProcessdate()) || (this
						.getProcessdate() != null
						&& castOther.getProcessdate() != null && this
						.getProcessdate().equals(castOther.getProcessdate())))
				&& ((this.getFilename() == castOther.getFilename()) || (this
						.getFilename() != null
						&& castOther.getFilename() != null && this
						.getFilename().equals(castOther.getFilename())))
				&& ((this.getDzresult() == castOther.getDzresult()) || (this
						.getDzresult() != null
						&& castOther.getDzresult() != null && this
						.getDzresult().equals(castOther.getDzresult())))
				&& ((this.getNote() == castOther.getNote()) || (this.getNote() != null
						&& castOther.getNote() != null && this.getNote()
						.equals(castOther.getNote())))
				&& ((this.getDzname() == castOther.getDzname()) || (this
						.getDzname() != null
						&& castOther.getDzname() != null && this.getDzname()
						.equals(castOther.getDzname())))
				&& ((this.getStatus() == castOther.getStatus()) || (this
						.getStatus() != null
						&& castOther.getStatus() != null && this.getStatus()
						.equals(castOther.getStatus())))
				&& ((this.getDoadjust() == castOther.getDoadjust()) || (this
						.getDoadjust() != null
						&& castOther.getDoadjust() != null && this
						.getDoadjust().equals(castOther.getDoadjust())))
				&& ((this.getDzclass() == castOther.getDzclass()) || (this
						.getDzclass() != null
						&& castOther.getDzclass() != null && this.getDzclass()
						.equals(castOther.getDzclass())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getDzlogid() == null ? 0 : this.getDzlogid().hashCode());
		result = 37 * result
				+ (getDzid() == null ? 0 : this.getDzid().hashCode());
		result = 37 * result
				+ (getAccdate() == null ? 0 : this.getAccdate().hashCode());
		result = 37
				* result
				+ (getProcessdate() == null ? 0 : this.getProcessdate()
						.hashCode());
		result = 37 * result
				+ (getFilename() == null ? 0 : this.getFilename().hashCode());
		result = 37 * result
				+ (getDzresult() == null ? 0 : this.getDzresult().hashCode());
		result = 37 * result
				+ (getNote() == null ? 0 : this.getNote().hashCode());
		result = 37 * result
				+ (getDzname() == null ? 0 : this.getDzname().hashCode());
		result = 37 * result
				+ (getStatus() == null ? 0 : this.getStatus().hashCode());
		result = 37 * result
				+ (getDoadjust() == null ? 0 : this.getDoadjust().hashCode());
		result = 37 * result
				+ (getDzclass() == null ? 0 : this.getDzclass().hashCode());
		return result;
	}

}