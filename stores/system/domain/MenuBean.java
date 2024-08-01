package com.oim.stores.system.domain;

import java.util.Date;

@SuppressWarnings("serial")
public class MenuBean implements java.io.Serializable{
	private String MENUID;
    private String MENUTYPE;
    private String MENUNAME;
    private String MENUURL;
    private String PARENTID;
    private String MENUORDER;
    private String WEBNAME;
    private String MENUDESC;
    private String ISACTIVE;
    private Date UPDATEDATE;
    private String UPDATEUSER;
    private String ISLEAF;
    
	public MenuBean() {
	}

	public MenuBean(String mENUID, String mENUTYPE, String mENUNAME,
			String mENUURL, String pARENTID, String mENUORDER, String wEBNAME,
			String mENUDESC, String iSACTIVE, Date uPDATEDATE, String uPDATEUSER,
			String iSLEAF) {
		MENUID = mENUID;
		MENUTYPE = mENUTYPE;
		MENUNAME = mENUNAME;
		MENUURL = mENUURL;
		PARENTID = pARENTID;
		MENUORDER = mENUORDER;
		WEBNAME = wEBNAME;
		MENUDESC = mENUDESC;
		ISACTIVE = iSACTIVE;
		UPDATEDATE = uPDATEDATE;
		UPDATEUSER = uPDATEUSER;
		ISLEAF = iSLEAF;
	}

	public String getMENUID() {
		return MENUID;
	}

	public void setMENUID(String mENUID) {
		MENUID = mENUID;
	}

	public String getMENUTYPE() {
		return MENUTYPE;
	}

	public void setMENUTYPE(String mENUTYPE) {
		MENUTYPE = mENUTYPE;
	}

	public String getMENUNAME() {
		return MENUNAME;
	}

	public void setMENUNAME(String mENUNAME) {
		MENUNAME = mENUNAME;
	}

	public String getMENUURL() {
		return MENUURL;
	}

	public void setMENUURL(String mENUURL) {
		MENUURL = mENUURL;
	}

	public String getPARENTID() {
		return PARENTID;
	}

	public void setPARENTID(String pARENTID) {
		PARENTID = pARENTID;
	}

	public String getMENUORDER() {
		return MENUORDER;
	}

	public void setMENUORDER(String mENUORDER) {
		MENUORDER = mENUORDER;
	}

	public String getWEBNAME() {
		return WEBNAME;
	}

	public void setWEBNAME(String wEBNAME) {
		WEBNAME = wEBNAME;
	}

	public String getMENUDESC() {
		return MENUDESC;
	}

	public void setMENUDESC(String mENUDESC) {
		MENUDESC = mENUDESC;
	}

	public String getISACTIVE() {
		return ISACTIVE;
	}

	public void setISACTIVE(String iSACTIVE) {
		ISACTIVE = iSACTIVE;
	}

	public Date getUPDATEDATE() {
		return UPDATEDATE;
	}

	public void setUPDATEDATE(Date uPDATEDATE) {
		UPDATEDATE = uPDATEDATE;
	}

	public String getUPDATEUSER() {
		return UPDATEUSER;
	}

	public void setUPDATEUSER(String uPDATEUSER) {
		UPDATEUSER = uPDATEUSER;
	}

	public String getISLEAF() {
		return ISLEAF;
	}

	public void setISLEAF(String iSLEAF) {
		ISLEAF = iSLEAF;
	}
    
	
}
