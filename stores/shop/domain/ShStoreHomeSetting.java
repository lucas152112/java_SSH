package com.oim.stores.shop.domain;

import java.math.BigDecimal;
import java.util.Date;

import com.oim.stores.common.Content;

@SuppressWarnings("serial")
public class ShStoreHomeSetting  implements java.io.Serializable {


    // Fields    

     private Long storeModuleId;
     private BigDecimal storeId;
     private BigDecimal moduleId;
     private String moduleDesc;
     private String menuDesc;
     private BigDecimal moduleOrder;
     private String moduleUrl;
     private BigDecimal isShow;
     private BigDecimal showNum;
     private Date updatedDate;
     private String updatedUser;
     
     private String typeFormat;
     private String isShowFormat;
     private String moduleType;
     private String storeAuthority;
     private String picFormat;


    // Constructors

    /** default constructor */
    public ShStoreHomeSetting() {
    }

	/** minimal constructor */
    public ShStoreHomeSetting(Long storeModuleId, BigDecimal storeId, BigDecimal moduleId, String moduleDesc, BigDecimal moduleOrder, String moduleUrl, BigDecimal isShow, BigDecimal showNum) {
        this.storeModuleId = storeModuleId;
        this.storeId = storeId;
        this.moduleId = moduleId;
        this.moduleDesc = moduleDesc;
        this.moduleOrder = moduleOrder;
        this.moduleUrl = moduleUrl;
        this.isShow = isShow;
        this.showNum = showNum;
    }
    
    /** full constructor */
    public ShStoreHomeSetting(Long storeModuleId, BigDecimal storeId, BigDecimal moduleId, String moduleDesc, String menuDesc, BigDecimal moduleOrder, String moduleUrl, BigDecimal isShow, BigDecimal showNum, Date updatedDate, String updatedUser) {
        this.storeModuleId = storeModuleId;
        this.storeId = storeId;
        this.moduleId = moduleId;
        this.moduleDesc = moduleDesc;
        this.menuDesc = menuDesc;
        this.moduleOrder = moduleOrder;
        this.moduleUrl = moduleUrl;
        this.isShow = isShow;
        this.showNum = showNum;
        this.updatedDate = updatedDate;
        this.updatedUser = updatedUser;
    }

   
    // Property accessors

    public Long getStoreModuleId() {
        return this.storeModuleId;
    }
    
    public void setStoreModuleId(Long storeModuleId) {
        this.storeModuleId = storeModuleId;
    }

    public BigDecimal getStoreId() {
        return this.storeId;
    }
    
    public void setStoreId(BigDecimal storeId) {
        this.storeId = storeId;
    }

    public BigDecimal getModuleId() {
        return this.moduleId;
    }
    
    public void setModuleId(BigDecimal moduleId) {
        this.moduleId = moduleId;
    }

    public String getModuleDesc() {
        return this.moduleDesc;
    }
    
    public void setModuleDesc(String moduleDesc) {
        this.moduleDesc = moduleDesc;
    }

    public String getMenuDesc() {
        return this.menuDesc;
    }
    
    public void setMenuDesc(String menuDesc) {
        this.menuDesc = menuDesc;
    }

    public BigDecimal getModuleOrder() {
        return this.moduleOrder;
    }
    
    public void setModuleOrder(BigDecimal moduleOrder) {
        this.moduleOrder = moduleOrder;
    }

    public String getModuleUrl() {
        return this.moduleUrl;
    }
    
    public void setModuleUrl(String moduleUrl) {
        this.moduleUrl = moduleUrl;
    }

    public BigDecimal getIsShow() {
        return this.isShow;
    }
    
    public void setIsShow(BigDecimal isShow) {
        this.isShow = isShow;
    }

    public BigDecimal getShowNum() {
        return this.showNum;
    }
    
    public void setShowNum(BigDecimal showNum) {
        this.showNum = showNum;
    }

    public Date getUpdatedDate() {
        return this.updatedDate;
    }
    
    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getUpdatedUser() {
        return this.updatedUser;
    }
    
    public void setUpdatedUser(String updatedUser) {
        this.updatedUser = updatedUser;
    }

	public String getTypeFormat() {
		return typeFormat;
	}

	public void setTypeFormat(String typeFormat) {
		this.typeFormat = typeFormat;
	}

	public String getIsShowFormat() {
		if(isShow != null){
			if(isShow.toString().equals(Content.MODULE_IS_SHOW_TRUE)){
				isShowFormat = "是";
			}else if(isShow.toString().equals(Content.MODULE_IS_SHOW_FALSE)){
				isShowFormat = "否";
			}
		}
		return isShowFormat;
	}

	public void setIsShowFormat(String isShowFormat) {
		this.isShowFormat = isShowFormat;
	}

	public String getStoreAuthority() {
		return storeAuthority;
	}

	public void setStoreAuthority(String storeAuthority) {
		this.storeAuthority = storeAuthority;
	}

	/**
	 * @return the moduleType
	 */
	public String getModuleType() {
		return moduleType;
	}

	/**
	 * @param moduleType the moduleType to set
	 */
	public void setModuleType(String moduleType) {
		this.moduleType = moduleType;
	}

	/**
	 * @return the picFormat
	 */
	public String getPicFormat() {
		return picFormat;
	}

	/**
	 * @param picFormat the picFormat to set
	 */
	public void setPicFormat(String picFormat) {
		this.picFormat = picFormat;
	}
   
	
}