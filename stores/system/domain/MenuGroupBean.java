package com.oim.stores.system.domain;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class MenuGroupBean implements Serializable {
    private Long groupid;
    private String groupname;
    private Long isactive;
    private Date modtime;
    public MenuGroupBean() {

    }

    /**
     * @return the groupid
     */
    public Long getGroupid() {
	return groupid;
    }

    /**
     * @param groupid
     *            the groupid to set
     */
    public void setGroupid(Long groupid) {
	this.groupid = groupid;
    }

    /**
     * @return the groupname
     */
    public String getGroupname() {
	return groupname;
    }

    /**
     * @param groupname
     *            the groupname to set
     */
    public void setGroupname(String groupname) {
	this.groupname = groupname;
    }

    /**
     * @return the isactive
     */
    public Long getIsactive() {
	return isactive;
    }

    /**
     * @param isactive
     *            the isactive to set
     */
    public void setIsactive(Long isactive) {
	this.isactive = isactive;
    }

    /**
     * @return the modtime
     */
    public Date getModtime() {
        return modtime;
    }

    /**
     * @param modtime the modtime to set
     */
    public void setModtime(Date modtime) {
        this.modtime = modtime;
    }
    

}
