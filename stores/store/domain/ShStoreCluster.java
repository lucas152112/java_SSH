package com.oim.stores.store.domain;

public class ShStoreCluster  implements java.io.Serializable {

     private Double storeId;
     private String regAccount;
     private String clusterName;
     private String cityCode;
     private String orgaName;
     private String positioin;
     private String employeeId;
     private String crmId;
     private String crmTeam;
     private String bankAccount;
     private String bankName;
     private String bankUserName;


    /** default constructor */
    public ShStoreCluster() {
    }

	/** minimal constructor */
    public ShStoreCluster(Double storeId) {
        this.storeId = storeId;
    }
    
    /** full constructor */
    public ShStoreCluster(Double storeId, String regAccount, String clusterName, String cityCode, String orgaName, String positioin, String employeeId, String crmId, String crmTeam, String bankAccount, String bankName, String bankUserName) {
        this.storeId = storeId;
        this.regAccount = regAccount;
        this.clusterName = clusterName;
        this.cityCode = cityCode;
        this.orgaName = orgaName;
        this.positioin = positioin;
        this.employeeId = employeeId;
        this.crmId = crmId;
        this.crmTeam = crmTeam;
        this.bankAccount = bankAccount;
        this.bankName = bankName;
        this.bankUserName = bankUserName;
    }

   
    // Property accessors

    public Double getStoreId() {
        return this.storeId;
    }
    
    public void setStoreId(Double storeId) {
        this.storeId = storeId;
    }

    public String getRegAccount() {
        return this.regAccount;
    }
    
    public void setRegAccount(String regAccount) {
        this.regAccount = regAccount;
    }

    public String getClusterName() {
        return this.clusterName;
    }
    
    public void setClusterName(String clusterName) {
        this.clusterName = clusterName;
    }

    public String getCityCode() {
        return this.cityCode;
    }
    
    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getOrgaName() {
        return this.orgaName;
    }
    
    public void setOrgaName(String orgaName) {
        this.orgaName = orgaName;
    }

    public String getPositioin() {
        return this.positioin;
    }
    
    public void setPositioin(String positioin) {
        this.positioin = positioin;
    }

    public String getEmployeeId() {
        return this.employeeId;
    }
    
    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getCrmId() {
        return this.crmId;
    }
    
    public void setCrmId(String crmId) {
        this.crmId = crmId;
    }

    public String getCrmTeam() {
        return this.crmTeam;
    }
    
    public void setCrmTeam(String crmTeam) {
        this.crmTeam = crmTeam;
    }

    public String getBankAccount() {
        return this.bankAccount;
    }
    
    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getBankName() {
        return this.bankName;
    }
    
    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankUserName() {
        return this.bankUserName;
    }
    
    public void setBankUserName(String bankUserName) {
        this.bankUserName = bankUserName;
    }

}