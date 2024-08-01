package com.oim.stores.store.domain;

import java.util.Date;

import com.oim.stores.system.domain.SyRoleInfo;
@SuppressWarnings("serial")
public class ShStoreRoleRel  implements java.io.Serializable{
    private ShStoreRoleRelPk id;
    private Long state;
    private Long updateduser;
    private Date updateddate;
    private ShStoreInfo shstoreinfo;
    private SyRoleInfo syroleinfo;

    public ShStoreRoleRel() {
    }

    /**
     * @return the id
     */
    public ShStoreRoleRelPk getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(ShStoreRoleRelPk id) {
        this.id = id;
    }

    /**
     * @return the state
     */
    public Long getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(Long state) {
        this.state = state;
    }

    /**
     * @return the updateduser
     */
    public Long getUpdateduser() {
        return updateduser;
    }

    /**
     * @param updateduser the updateduser to set
     */
    public void setUpdateduser(Long updateduser) {
        this.updateduser = updateduser;
    }

    /**
     * @return the updateddate
     */
    public Date getUpdateddate() {
        return updateddate;
    }

    /**
     * @param updateddate the updateddate to set
     */
    public void setUpdateddate(Date updateddate) {
        this.updateddate = updateddate;
    }

    /**
     * @return the shstoreinfo
     */
    public ShStoreInfo getShstoreinfo() {
        return shstoreinfo;
    }

    /**
     * @param shstoreinfo the shstoreinfo to set
     */
    public void setShstoreinfo(ShStoreInfo shstoreinfo) {
        this.shstoreinfo = shstoreinfo;
    }

    /**
     * @return the syroleinfo
     */
    public SyRoleInfo getSyroleinfo() {
        return syroleinfo;
    }

    /**
     * @param syroleinfo the syroleinfo to set
     */
    public void setSyroleinfo(SyRoleInfo syroleinfo) {
        this.syroleinfo = syroleinfo;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "ShStoreRoleRel [id=" + id + ", state=" + state
		+ ", updateduser=" + updateduser + ", updateddate="
		+ updateddate + ", shstoreinfo=" + shstoreinfo
		+ ", syroleinfo=" + syroleinfo + "]";
    }
    
    

   




}
