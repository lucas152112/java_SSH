package com.oim.stores.store.domain;

import java.math.BigDecimal;
import java.util.Date;

/**
 * ViewStoreClusterId entity. @author MyEclipse Persistence Tools
 */

public class ViewStoreCluster implements java.io.Serializable {

	// Fields

	private Long storeId;
	private String storeName;
	private String storePic;
	private BigDecimal productType;
	private String storeShortDesc;
	private BigDecimal businessType;
	private String storeDesc;
	private String tel;
	private BigDecimal productSource;
	private BigDecimal isEntity;
	private BigDecimal isWareshop;
	private BigDecimal storeStatus;
	private Date addDate;
	private String addDateStr;
	private BigDecimal isAudit;
	private String factorId;
	private String crmId;
	private Date updateDate;
	private BigDecimal updateUser;
	private String auditComment;
	private String cserviceTel;
	private BigDecimal msgType;
	private String msgNumber;
	private BigDecimal isOnline;
	private String openTime;
	private String homeUrl;
	private BigDecimal storeType;
	private BigDecimal managedStore;
	private BigDecimal storeLevel;
	private BigDecimal storeMode;
	private String storeModeStr;
	private BigDecimal regionalId;
	private String regionalIdStr;
	private String regAccount;
	private String clusterName;
	private String cityCode;
	private String orgaName;
	private String positioin;
	private String employeeId;
	private String clusterCrmId;
	private String crmTeam;
	private String bankAccount;
	private String bankName;
	private String bankUserName;

	// Constructors

	/** default constructor */
	public ViewStoreCluster() {
	}

	/** minimal constructor */
	public ViewStoreCluster(Long storeId, String storeName,
			BigDecimal productType, String storeShortDesc,
			BigDecimal businessType, String storeDesc, BigDecimal isEntity,
			BigDecimal isWareshop, BigDecimal storeStatus, Date addDate,
			BigDecimal isAudit, Date updateDate, BigDecimal updateUser,
			String homeUrl, BigDecimal storeType, BigDecimal storeLevel) {
		this.storeId = storeId;
		this.storeName = storeName;
		this.productType = productType;
		this.storeShortDesc = storeShortDesc;
		this.businessType = businessType;
		this.storeDesc = storeDesc;
		this.isEntity = isEntity;
		this.isWareshop = isWareshop;
		this.storeStatus = storeStatus;
		this.addDate = addDate;
		this.isAudit = isAudit;
		this.updateDate = updateDate;
		this.updateUser = updateUser;
		this.homeUrl = homeUrl;
		this.storeType = storeType;
		this.storeLevel = storeLevel;
	}

	/** full constructor */
	public ViewStoreCluster(Long storeId, String storeName,
			String storePic, BigDecimal productType, String storeShortDesc,
			BigDecimal businessType, String storeDesc, String tel,
			BigDecimal productSource, BigDecimal isEntity,
			BigDecimal isWareshop, BigDecimal storeStatus, Date addDate,
			BigDecimal isAudit, String factorId, String crmId,
			Date updateDate, BigDecimal updateUser, String auditComment,
			String cserviceTel, BigDecimal msgType, String msgNumber,
			BigDecimal isOnline, String openTime, String homeUrl,
			BigDecimal storeType, BigDecimal managedStore,
			BigDecimal storeLevel, BigDecimal storeMode, BigDecimal regionalId,
			String regAccount, String clusterName, String cityCode,
			String orgaName, String positioin, String employeeId,
			String clusterCrmId, String crmTeam, String bankAccount,
			String bankName, String bankUserName) {
		this.storeId = storeId;
		this.storeName = storeName;
		this.storePic = storePic;
		this.productType = productType;
		this.storeShortDesc = storeShortDesc;
		this.businessType = businessType;
		this.storeDesc = storeDesc;
		this.tel = tel;
		this.productSource = productSource;
		this.isEntity = isEntity;
		this.isWareshop = isWareshop;
		this.storeStatus = storeStatus;
		this.addDate = addDate;
		this.isAudit = isAudit;
		this.factorId = factorId;
		this.crmId = crmId;
		this.updateDate = updateDate;
		this.updateUser = updateUser;
		this.auditComment = auditComment;
		this.cserviceTel = cserviceTel;
		this.msgType = msgType;
		this.msgNumber = msgNumber;
		this.isOnline = isOnline;
		this.openTime = openTime;
		this.homeUrl = homeUrl;
		this.storeType = storeType;
		this.managedStore = managedStore;
		this.storeLevel = storeLevel;
		this.storeMode = storeMode;
		this.regionalId = regionalId;
		this.regAccount = regAccount;
		this.clusterName = clusterName;
		this.cityCode = cityCode;
		this.orgaName = orgaName;
		this.positioin = positioin;
		this.employeeId = employeeId;
		this.clusterCrmId = clusterCrmId;
		this.crmTeam = crmTeam;
		this.bankAccount = bankAccount;
		this.bankName = bankName;
		this.bankUserName = bankUserName;
	}

	// Property accessors

	
	public Long getStoreId() {
		return this.storeId;
	}

	public String getRegionalIdStr() {
		if(this.regionalId==null){
			regionalIdStr="全省";
			return regionalIdStr;
		}
		if("591".equals(this.regionalId.toString())){
			regionalIdStr="福州市";
		}else if("592".equals(this.regionalId.toString())){
			regionalIdStr="厦门市";
		}else if("593".equals(this.regionalId.toString())){
			regionalIdStr="宁德市";
		}else if("594".equals(this.regionalId.toString())){
			regionalIdStr="莆田市";
		}else if("595".equals(this.regionalId.toString())){
			regionalIdStr="泉州市";
		}else if("596".equals(this.regionalId.toString())){
			regionalIdStr="漳州市";
		}else if("597".equals(this.regionalId.toString())){
			regionalIdStr="龙岩市";
		}else if("598".equals(this.regionalId.toString())){
			regionalIdStr="三明市";
		}else if("599".equals(this.regionalId.toString())){
			regionalIdStr="南平市";
		}else{
			regionalIdStr="全省";
		}
		return regionalIdStr;
	}

	public void setRegionalIdStr(String regionalIdStr) {
		this.regionalIdStr = regionalIdStr;
	}

	public String getStoreModeStr() {
		if("1".equals(this.storeMode.toString())){
			storeModeStr="自营";
		}else if("2".equals(this.storeMode.toString())){
			storeModeStr="客户经理";
		}else{
			storeModeStr="代理商";
		}
		return storeModeStr;
	}

	public void setStoreModeStr(String storeModeStr) {
		this.storeModeStr = storeModeStr;
	}

	public String getAddDateStr() {
		return addDateStr;
	}

	public void setAddDateStr(String addDateStr) {
		this.addDateStr = addDateStr;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}

	public String getStoreName() {
		return this.storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getStorePic() {
		return this.storePic;
	}

	public void setStorePic(String storePic) {
		this.storePic = storePic;
	}

	public BigDecimal getProductType() {
		return this.productType;
	}

	public void setProductType(BigDecimal productType) {
		this.productType = productType;
	}

	public String getStoreShortDesc() {
		return this.storeShortDesc;
	}

	public void setStoreShortDesc(String storeShortDesc) {
		this.storeShortDesc = storeShortDesc;
	}

	public BigDecimal getBusinessType() {
		return this.businessType;
	}

	public void setBusinessType(BigDecimal businessType) {
		this.businessType = businessType;
	}

	public String getStoreDesc() {
		return this.storeDesc;
	}

	public void setStoreDesc(String storeDesc) {
		this.storeDesc = storeDesc;
	}

	public String getTel() {
		return this.tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public BigDecimal getProductSource() {
		return this.productSource;
	}

	public void setProductSource(BigDecimal productSource) {
		this.productSource = productSource;
	}

	public BigDecimal getIsEntity() {
		return this.isEntity;
	}

	public void setIsEntity(BigDecimal isEntity) {
		this.isEntity = isEntity;
	}

	public BigDecimal getIsWareshop() {
		return this.isWareshop;
	}

	public void setIsWareshop(BigDecimal isWareshop) {
		this.isWareshop = isWareshop;
	}

	public BigDecimal getStoreStatus() {
		return this.storeStatus;
	}

	public void setStoreStatus(BigDecimal storeStatus) {
		this.storeStatus = storeStatus;
	}

	public Date getAddDate() {
		return this.addDate;
	}

	public void setAddDate(Date addDate) {
		this.addDate = addDate;
	}

	public BigDecimal getIsAudit() {
		return this.isAudit;
	}

	public void setIsAudit(BigDecimal isAudit) {
		this.isAudit = isAudit;
	}

	public String getFactorId() {
		return this.factorId;
	}

	public void setFactorId(String factorId) {
		this.factorId = factorId;
	}

	public String getCrmId() {
		return this.crmId;
	}

	public void setCrmId(String crmId) {
		this.crmId = crmId;
	}

	public Date getUpdateDate() {
		return this.updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public BigDecimal getUpdateUser() {
		return this.updateUser;
	}

	public void setUpdateUser(BigDecimal updateUser) {
		this.updateUser = updateUser;
	}

	public String getAuditComment() {
		return this.auditComment;
	}

	public void setAuditComment(String auditComment) {
		this.auditComment = auditComment;
	}

	public String getCserviceTel() {
		return this.cserviceTel;
	}

	public void setCserviceTel(String cserviceTel) {
		this.cserviceTel = cserviceTel;
	}

	public BigDecimal getMsgType() {
		return this.msgType;
	}

	public void setMsgType(BigDecimal msgType) {
		this.msgType = msgType;
	}

	public String getMsgNumber() {
		return this.msgNumber;
	}

	public void setMsgNumber(String msgNumber) {
		this.msgNumber = msgNumber;
	}

	public BigDecimal getIsOnline() {
		return this.isOnline;
	}

	public void setIsOnline(BigDecimal isOnline) {
		this.isOnline = isOnline;
	}

	public String getOpenTime() {
		return this.openTime;
	}

	public void setOpenTime(String openTime) {
		this.openTime = openTime;
	}

	public String getHomeUrl() {
		return this.homeUrl;
	}

	public void setHomeUrl(String homeUrl) {
		this.homeUrl = homeUrl;
	}

	public BigDecimal getStoreType() {
		return this.storeType;
	}

	public void setStoreType(BigDecimal storeType) {
		this.storeType = storeType;
	}

	public BigDecimal getManagedStore() {
		return this.managedStore;
	}

	public void setManagedStore(BigDecimal managedStore) {
		this.managedStore = managedStore;
	}

	public BigDecimal getStoreLevel() {
		return this.storeLevel;
	}

	public void setStoreLevel(BigDecimal storeLevel) {
		this.storeLevel = storeLevel;
	}

	public BigDecimal getStoreMode() {
		return this.storeMode;
	}

	public void setStoreMode(BigDecimal storeMode) {
		this.storeMode = storeMode;
	}

	public BigDecimal getRegionalId() {
		return this.regionalId;
	}

	public void setRegionalId(BigDecimal regionalId) {
		this.regionalId = regionalId;
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

	public String getClusterCrmId() {
		return this.clusterCrmId;
	}

	public void setClusterCrmId(String clusterCrmId) {
		this.clusterCrmId = clusterCrmId;
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

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof ViewStoreCluster))
			return false;
		ViewStoreCluster castOther = (ViewStoreCluster) other;

		return ((this.getStoreId() == castOther.getStoreId()) || (this
				.getStoreId() != null
				&& castOther.getStoreId() != null && this.getStoreId().equals(
				castOther.getStoreId())))
				&& ((this.getStoreName() == castOther.getStoreName()) || (this
						.getStoreName() != null
						&& castOther.getStoreName() != null && this
						.getStoreName().equals(castOther.getStoreName())))
				&& ((this.getStorePic() == castOther.getStorePic()) || (this
						.getStorePic() != null
						&& castOther.getStorePic() != null && this
						.getStorePic().equals(castOther.getStorePic())))
				&& ((this.getProductType() == castOther.getProductType()) || (this
						.getProductType() != null
						&& castOther.getProductType() != null && this
						.getProductType().equals(castOther.getProductType())))
				&& ((this.getStoreShortDesc() == castOther.getStoreShortDesc()) || (this
						.getStoreShortDesc() != null
						&& castOther.getStoreShortDesc() != null && this
						.getStoreShortDesc().equals(
								castOther.getStoreShortDesc())))
				&& ((this.getBusinessType() == castOther.getBusinessType()) || (this
						.getBusinessType() != null
						&& castOther.getBusinessType() != null && this
						.getBusinessType().equals(castOther.getBusinessType())))
				&& ((this.getStoreDesc() == castOther.getStoreDesc()) || (this
						.getStoreDesc() != null
						&& castOther.getStoreDesc() != null && this
						.getStoreDesc().equals(castOther.getStoreDesc())))
				&& ((this.getTel() == castOther.getTel()) || (this.getTel() != null
						&& castOther.getTel() != null && this.getTel().equals(
						castOther.getTel())))
				&& ((this.getProductSource() == castOther.getProductSource()) || (this
						.getProductSource() != null
						&& castOther.getProductSource() != null && this
						.getProductSource()
						.equals(castOther.getProductSource())))
				&& ((this.getIsEntity() == castOther.getIsEntity()) || (this
						.getIsEntity() != null
						&& castOther.getIsEntity() != null && this
						.getIsEntity().equals(castOther.getIsEntity())))
				&& ((this.getIsWareshop() == castOther.getIsWareshop()) || (this
						.getIsWareshop() != null
						&& castOther.getIsWareshop() != null && this
						.getIsWareshop().equals(castOther.getIsWareshop())))
				&& ((this.getStoreStatus() == castOther.getStoreStatus()) || (this
						.getStoreStatus() != null
						&& castOther.getStoreStatus() != null && this
						.getStoreStatus().equals(castOther.getStoreStatus())))
				&& ((this.getAddDate() == castOther.getAddDate()) || (this
						.getAddDate() != null
						&& castOther.getAddDate() != null && this.getAddDate()
						.equals(castOther.getAddDate())))
				&& ((this.getIsAudit() == castOther.getIsAudit()) || (this
						.getIsAudit() != null
						&& castOther.getIsAudit() != null && this.getIsAudit()
						.equals(castOther.getIsAudit())))
				&& ((this.getFactorId() == castOther.getFactorId()) || (this
						.getFactorId() != null
						&& castOther.getFactorId() != null && this
						.getFactorId().equals(castOther.getFactorId())))
				&& ((this.getCrmId() == castOther.getCrmId()) || (this
						.getCrmId() != null
						&& castOther.getCrmId() != null && this.getCrmId()
						.equals(castOther.getCrmId())))
				&& ((this.getUpdateDate() == castOther.getUpdateDate()) || (this
						.getUpdateDate() != null
						&& castOther.getUpdateDate() != null && this
						.getUpdateDate().equals(castOther.getUpdateDate())))
				&& ((this.getUpdateUser() == castOther.getUpdateUser()) || (this
						.getUpdateUser() != null
						&& castOther.getUpdateUser() != null && this
						.getUpdateUser().equals(castOther.getUpdateUser())))
				&& ((this.getAuditComment() == castOther.getAuditComment()) || (this
						.getAuditComment() != null
						&& castOther.getAuditComment() != null && this
						.getAuditComment().equals(castOther.getAuditComment())))
				&& ((this.getCserviceTel() == castOther.getCserviceTel()) || (this
						.getCserviceTel() != null
						&& castOther.getCserviceTel() != null && this
						.getCserviceTel().equals(castOther.getCserviceTel())))
				&& ((this.getMsgType() == castOther.getMsgType()) || (this
						.getMsgType() != null
						&& castOther.getMsgType() != null && this.getMsgType()
						.equals(castOther.getMsgType())))
				&& ((this.getMsgNumber() == castOther.getMsgNumber()) || (this
						.getMsgNumber() != null
						&& castOther.getMsgNumber() != null && this
						.getMsgNumber().equals(castOther.getMsgNumber())))
				&& ((this.getIsOnline() == castOther.getIsOnline()) || (this
						.getIsOnline() != null
						&& castOther.getIsOnline() != null && this
						.getIsOnline().equals(castOther.getIsOnline())))
				&& ((this.getOpenTime() == castOther.getOpenTime()) || (this
						.getOpenTime() != null
						&& castOther.getOpenTime() != null && this
						.getOpenTime().equals(castOther.getOpenTime())))
				&& ((this.getHomeUrl() == castOther.getHomeUrl()) || (this
						.getHomeUrl() != null
						&& castOther.getHomeUrl() != null && this.getHomeUrl()
						.equals(castOther.getHomeUrl())))
				&& ((this.getStoreType() == castOther.getStoreType()) || (this
						.getStoreType() != null
						&& castOther.getStoreType() != null && this
						.getStoreType().equals(castOther.getStoreType())))
				&& ((this.getManagedStore() == castOther.getManagedStore()) || (this
						.getManagedStore() != null
						&& castOther.getManagedStore() != null && this
						.getManagedStore().equals(castOther.getManagedStore())))
				&& ((this.getStoreLevel() == castOther.getStoreLevel()) || (this
						.getStoreLevel() != null
						&& castOther.getStoreLevel() != null && this
						.getStoreLevel().equals(castOther.getStoreLevel())))
				&& ((this.getStoreMode() == castOther.getStoreMode()) || (this
						.getStoreMode() != null
						&& castOther.getStoreMode() != null && this
						.getStoreMode().equals(castOther.getStoreMode())))
				&& ((this.getRegionalId() == castOther.getRegionalId()) || (this
						.getRegionalId() != null
						&& castOther.getRegionalId() != null && this
						.getRegionalId().equals(castOther.getRegionalId())))
				&& ((this.getRegAccount() == castOther.getRegAccount()) || (this
						.getRegAccount() != null
						&& castOther.getRegAccount() != null && this
						.getRegAccount().equals(castOther.getRegAccount())))
				&& ((this.getClusterName() == castOther.getClusterName()) || (this
						.getClusterName() != null
						&& castOther.getClusterName() != null && this
						.getClusterName().equals(castOther.getClusterName())))
				&& ((this.getCityCode() == castOther.getCityCode()) || (this
						.getCityCode() != null
						&& castOther.getCityCode() != null && this
						.getCityCode().equals(castOther.getCityCode())))
				&& ((this.getOrgaName() == castOther.getOrgaName()) || (this
						.getOrgaName() != null
						&& castOther.getOrgaName() != null && this
						.getOrgaName().equals(castOther.getOrgaName())))
				&& ((this.getPositioin() == castOther.getPositioin()) || (this
						.getPositioin() != null
						&& castOther.getPositioin() != null && this
						.getPositioin().equals(castOther.getPositioin())))
				&& ((this.getEmployeeId() == castOther.getEmployeeId()) || (this
						.getEmployeeId() != null
						&& castOther.getEmployeeId() != null && this
						.getEmployeeId().equals(castOther.getEmployeeId())))
				&& ((this.getClusterCrmId() == castOther.getClusterCrmId()) || (this
						.getClusterCrmId() != null
						&& castOther.getClusterCrmId() != null && this
						.getClusterCrmId().equals(castOther.getClusterCrmId())))
				&& ((this.getCrmTeam() == castOther.getCrmTeam()) || (this
						.getCrmTeam() != null
						&& castOther.getCrmTeam() != null && this.getCrmTeam()
						.equals(castOther.getCrmTeam())))
				&& ((this.getBankAccount() == castOther.getBankAccount()) || (this
						.getBankAccount() != null
						&& castOther.getBankAccount() != null && this
						.getBankAccount().equals(castOther.getBankAccount())))
				&& ((this.getBankName() == castOther.getBankName()) || (this
						.getBankName() != null
						&& castOther.getBankName() != null && this
						.getBankName().equals(castOther.getBankName())))
				&& ((this.getBankUserName() == castOther.getBankUserName()) || (this
						.getBankUserName() != null
						&& castOther.getBankUserName() != null && this
						.getBankUserName().equals(castOther.getBankUserName())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getStoreId() == null ? 0 : this.getStoreId().hashCode());
		result = 37 * result
				+ (getStoreName() == null ? 0 : this.getStoreName().hashCode());
		result = 37 * result
				+ (getStorePic() == null ? 0 : this.getStorePic().hashCode());
		result = 37
				* result
				+ (getProductType() == null ? 0 : this.getProductType()
						.hashCode());
		result = 37
				* result
				+ (getStoreShortDesc() == null ? 0 : this.getStoreShortDesc()
						.hashCode());
		result = 37
				* result
				+ (getBusinessType() == null ? 0 : this.getBusinessType()
						.hashCode());
		result = 37 * result
				+ (getStoreDesc() == null ? 0 : this.getStoreDesc().hashCode());
		result = 37 * result
				+ (getTel() == null ? 0 : this.getTel().hashCode());
		result = 37
				* result
				+ (getProductSource() == null ? 0 : this.getProductSource()
						.hashCode());
		result = 37 * result
				+ (getIsEntity() == null ? 0 : this.getIsEntity().hashCode());
		result = 37
				* result
				+ (getIsWareshop() == null ? 0 : this.getIsWareshop()
						.hashCode());
		result = 37
				* result
				+ (getStoreStatus() == null ? 0 : this.getStoreStatus()
						.hashCode());
		result = 37 * result
				+ (getAddDate() == null ? 0 : this.getAddDate().hashCode());
		result = 37 * result
				+ (getIsAudit() == null ? 0 : this.getIsAudit().hashCode());
		result = 37 * result
				+ (getFactorId() == null ? 0 : this.getFactorId().hashCode());
		result = 37 * result
				+ (getCrmId() == null ? 0 : this.getCrmId().hashCode());
		result = 37
				* result
				+ (getUpdateDate() == null ? 0 : this.getUpdateDate()
						.hashCode());
		result = 37
				* result
				+ (getUpdateUser() == null ? 0 : this.getUpdateUser()
						.hashCode());
		result = 37
				* result
				+ (getAuditComment() == null ? 0 : this.getAuditComment()
						.hashCode());
		result = 37
				* result
				+ (getCserviceTel() == null ? 0 : this.getCserviceTel()
						.hashCode());
		result = 37 * result
				+ (getMsgType() == null ? 0 : this.getMsgType().hashCode());
		result = 37 * result
				+ (getMsgNumber() == null ? 0 : this.getMsgNumber().hashCode());
		result = 37 * result
				+ (getIsOnline() == null ? 0 : this.getIsOnline().hashCode());
		result = 37 * result
				+ (getOpenTime() == null ? 0 : this.getOpenTime().hashCode());
		result = 37 * result
				+ (getHomeUrl() == null ? 0 : this.getHomeUrl().hashCode());
		result = 37 * result
				+ (getStoreType() == null ? 0 : this.getStoreType().hashCode());
		result = 37
				* result
				+ (getManagedStore() == null ? 0 : this.getManagedStore()
						.hashCode());
		result = 37
				* result
				+ (getStoreLevel() == null ? 0 : this.getStoreLevel()
						.hashCode());
		result = 37 * result
				+ (getStoreMode() == null ? 0 : this.getStoreMode().hashCode());
		result = 37
				* result
				+ (getRegionalId() == null ? 0 : this.getRegionalId()
						.hashCode());
		result = 37
				* result
				+ (getRegAccount() == null ? 0 : this.getRegAccount()
						.hashCode());
		result = 37
				* result
				+ (getClusterName() == null ? 0 : this.getClusterName()
						.hashCode());
		result = 37 * result
				+ (getCityCode() == null ? 0 : this.getCityCode().hashCode());
		result = 37 * result
				+ (getOrgaName() == null ? 0 : this.getOrgaName().hashCode());
		result = 37 * result
				+ (getPositioin() == null ? 0 : this.getPositioin().hashCode());
		result = 37
				* result
				+ (getEmployeeId() == null ? 0 : this.getEmployeeId()
						.hashCode());
		result = 37
				* result
				+ (getClusterCrmId() == null ? 0 : this.getClusterCrmId()
						.hashCode());
		result = 37 * result
				+ (getCrmTeam() == null ? 0 : this.getCrmTeam().hashCode());
		result = 37
				* result
				+ (getBankAccount() == null ? 0 : this.getBankAccount()
						.hashCode());
		result = 37 * result
				+ (getBankName() == null ? 0 : this.getBankName().hashCode());
		result = 37
				* result
				+ (getBankUserName() == null ? 0 : this.getBankUserName()
						.hashCode());
		return result;
	}

}