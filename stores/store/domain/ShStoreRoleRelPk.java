package com.oim.stores.store.domain;

import java.io.Serializable;
@SuppressWarnings("serial")
public class ShStoreRoleRelPk implements Serializable {
    private Long roleid;
    private Long storeid;

    public ShStoreRoleRelPk() {

    }

    public ShStoreRoleRelPk(Long roleid, Long storeid) {
	this.roleid = roleid;
	this.storeid = storeid;
    }

    /**
     * @return the roleid
     */
    public Long getRoleid() {
	return roleid;
    }

    /**
     * @param roleid
     *            the roleid to set
     */
    public void setRoleid(Long roleid) {
	this.roleid = roleid;
    }

    /**
     * @return the storeid
     */
    public Long getStoreid() {
	return storeid;
    }

    /**
     * @param storeid
     *            the storeid to set
     */
    public void setStoreid(Long storeid) {
	this.storeid = storeid;
    }

}
