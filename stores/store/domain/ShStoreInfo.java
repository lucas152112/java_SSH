package com.oim.stores.store.domain;

import java.math.BigDecimal;
import java.util.Date;

import com.oim.stores.datadict.domain.ShProductType;

/**
 * 商家信息
 */
@SuppressWarnings("serial")
public class ShStoreInfo implements java.io.Serializable {

	// Fields

	private Long storeId;
	private String storeName;
	private String storePic;
	private Long productType;
	private String storeShortDesc;
	private Long businessType;
	private String storeDesc;
	private String tel;
	private Long productSource;
	private Long isEntity;
	private Long isWareshop;
	private Long storeStatus;
	private Date addDate;
	private Long isAudit;
	private String factorId;
	private String crmId;
	private Date updateDate;
	private Long updateUser;
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
    private BigDecimal regionalId;
	
	private ShProductType shProductType;
	private String productTypeName;
	
	private String storeUrl;
	
	private String regionalName;
	
	
	// Constructors

	/** default constructor */
	public ShStoreInfo() {
	}

	/** minimal constructor */
	public ShStoreInfo(String storeName, Long productType,
			String storeShortDesc, Long businessType, String storeDesc,
			Long isEntity, Long isWareshop, Long storeStatus,
			Date addDate, Long isAudit, Date updateDate,
			Long updateUser) {
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
	}

	/** full constructor */
	public ShStoreInfo(String storeName, String storePic,
			Long productType, String storeShortDesc,
			Long businessType, String storeDesc, String tel,
			Long productSource, Long isEntity,
			Long isWareshop, Long storeStatus, Date addDate,
			Long isAudit, String factorId, String crmId, Date updateDate,
			Long updateUser,String auditComment) {
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
		this.auditComment=auditComment;
	}

	// Property accessors

	public Long getStoreId() {
		return this.storeId;
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

	public Long getProductType() {
		return this.productType;
	}

	public void setProductType(Long productType) {
		this.productType = productType;
	}

	public String getStoreShortDesc() {
		return this.storeShortDesc;
	}

	public void setStoreShortDesc(String storeShortDesc) {
		this.storeShortDesc = storeShortDesc;
	}

	public Long getBusinessType() {
		return this.businessType;
	}

	public void setBusinessType(Long businessType) {
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

	public Long getProductSource() {
		return this.productSource;
	}

	public void setProductSource(Long productSource) {
		this.productSource = productSource;
	}

	public Long getIsEntity() {
		return this.isEntity;
	}

	public void setIsEntity(Long isEntity) {
		this.isEntity = isEntity;
	}

	public Long getIsWareshop() {
		return this.isWareshop;
	}

	public void setIsWareshop(Long isWareshop) {
		this.isWareshop = isWareshop;
	}

	public Long getStoreStatus() {
		return this.storeStatus;
	}

	public void setStoreStatus(Long storeStatus) {
		this.storeStatus = storeStatus;
	}

	public Date getAddDate() {
		return this.addDate;
	}

	public void setAddDate(Date addDate) {
		this.addDate = addDate;
	}

	public Long getIsAudit() {
		return this.isAudit;
	}

	public void setIsAudit(Long isAudit) {
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

	public Long getUpdateUser() {
		return this.updateUser;
	}

	public void setUpdateUser(Long updateUser) {
		this.updateUser = updateUser;
	}

	public ShProductType getShProductType() {
		return shProductType;
	}

	public void setShProductType(ShProductType shProductType) {
		this.shProductType = shProductType;
	}

	public String getProductTypeName() {
		productTypeName = "无";
		if(null != shProductType){
			productTypeName = shProductType.getTypeName();
		}
		return productTypeName;
	}

	public String getAuditComment() {
		return auditComment;
	}

	public void setAuditComment(String auditComment) {
		this.auditComment = auditComment;
	}

	public String getCserviceTel() {
		return cserviceTel;
	}

	public void setCserviceTel(String cserviceTel) {
		this.cserviceTel = cserviceTel;
	}

	public BigDecimal getMsgType() {
		return msgType;
	}

	public void setMsgType(BigDecimal msgType) {
		this.msgType = msgType;
	}

	public String getMsgNumber() {
		return msgNumber;
	}

	public void setMsgNumber(String msgNumber) {
		this.msgNumber = msgNumber;
	}

	public BigDecimal getIsOnline() {
		return isOnline;
	}

	public void setIsOnline(BigDecimal isOnline) {
		this.isOnline = isOnline;
	}

	public String getOpenTime() {
		return openTime;
	}

	public void setOpenTime(String openTime) {
		this.openTime = openTime;
	}

	public String getHomeUrl() {
		return homeUrl;
	}

	public void setHomeUrl(String homeUrl) {
		this.homeUrl = homeUrl;
	}

	public BigDecimal getStoreType() {
		return storeType;
	}

	public void setStoreType(BigDecimal storeType) {
		this.storeType = storeType;
	}

	public BigDecimal getManagedStore() {
		return managedStore;
	}

	public void setManagedStore(BigDecimal managedStore) {
		this.managedStore = managedStore;
	}

	public BigDecimal getStoreLevel() {
		return storeLevel;
	}

	public void setStoreLevel(BigDecimal storeLevel) {
		this.storeLevel = storeLevel;
	}

	public void setProductTypeName(String productTypeName) {
		this.productTypeName = productTypeName;
	}

	public BigDecimal getStoreMode() {
		return storeMode;
	}

	public void setStoreMode(BigDecimal storeMode) {
		this.storeMode = storeMode;
	}

	public String getStoreUrl() {
		return storeUrl;
	}

	public void setStoreUrl(String storeUrl) {
		this.storeUrl = storeUrl;
	}

	public BigDecimal getRegionalId() {
		return regionalId;
	}

	public void setRegionalId(BigDecimal regionalId) {
		this.regionalId = regionalId;
	}

	public String getRegionalName() {
		return regionalName;
	}

	public void setRegionalName(String regionalName) {
		this.regionalName = regionalName;
	}

	

}