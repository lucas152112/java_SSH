package com.oim.stores.shop.domain;

import java.math.BigDecimal;
import java.util.Date;

import com.oim.stores.common.Content;
@SuppressWarnings("serial")
public class ShStoreHomeModule  implements java.io.Serializable {


    // Fields    

     private Long moduleId;
     private String moduleName;
     private BigDecimal typeId;
     private BigDecimal isShow;
     private BigDecimal moduleState;
     private Date updatedDate;
     private String updatedUser;
     private BigDecimal storeAuthority;
     private String picFormat;
     
     private String typeFormat;
     private String isShowFormat;
     private String storeAuthorityFormat;


    // Constructors

    /** default constructor */
    public ShStoreHomeModule() {
    }

    
    /** full constructor */
    public ShStoreHomeModule(Long moduleId, String moduleName, BigDecimal typeId, BigDecimal isShow, BigDecimal moduleState, Date updatedDate, String updatedUser, BigDecimal storeAuthority) {
        this.moduleId = moduleId;
        this.moduleName = moduleName;
        this.typeId = typeId;
        this.isShow = isShow;
        this.moduleState = moduleState;
        this.updatedDate = updatedDate;
        this.updatedUser = updatedUser;
        this.storeAuthority = storeAuthority;
    }

   
    // Property accessors

    public Long getModuleId() {
        return this.moduleId;
    }
    
    public void setModuleId(Long moduleId) {
        this.moduleId = moduleId;
    }

    public String getModuleName() {
        return this.moduleName;
    }
    
    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public BigDecimal getTypeId() {
        return this.typeId;
    }
    
    public void setTypeId(BigDecimal typeId) {
        this.typeId = typeId;
    }

    public BigDecimal getIsShow() {
        return this.isShow;
    }
    
    public void setIsShow(BigDecimal isShow) {
        this.isShow = isShow;
    }

    public BigDecimal getModuleState() {
        return this.moduleState;
    }
    
    public void setModuleState(BigDecimal moduleState) {
        this.moduleState = moduleState;
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

    public BigDecimal getStoreAuthority() {
        return this.storeAuthority;
    }
    
    public void setStoreAuthority(BigDecimal storeAuthority) {
        this.storeAuthority = storeAuthority;
    }
   
    public String getTypeFormat() {
    	if(typeId != null){
    		if (typeId.toString().equals(Content.MODULE_TYPE_PRODUCT)) {
    			typeFormat = "商品";
    		} else if (typeId.toString().equals(Content.MODULE_TYPE_SEARCH_RIGHT)) {
    			typeFormat = "搜索栏右侧广告";
    		} else if (typeId.toString().equals(Content.MODULE_TYPE_CONTENT_TOP)) {
    			typeFormat = "商品头部广告";
    		} else if (typeId.toString().equals(Content.MODULE_TYPE_CONTENT_LEFT)) {
    			typeFormat = "左侧广告";
    		} else if (typeId.toString().equals(Content.MODULE_TYPE_SHOP)) {
    			typeFormat = "商家LOGO";
    		} else if (typeId.toString().equals(Content.MODULE_TYPE_SHOP_BACKGROUND)) {
    			typeFormat = "商家LOGO背景";
    		} else if (typeId.toString().equals(Content.MODULE_TYPE_SHARE_PRODUCT)) {
    			typeFormat = "分销商品";
    		}
    	}
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


	public String getStoreAuthorityFormat() {
		if(storeAuthority != null){
			if(storeAuthority.toString().equals(Content.MODULE_STORE_AUTHORITY)){
				storeAuthorityFormat = "商家控制";
			}else if(storeAuthority.toString().equals(Content.MODULE_STORE_EDIT)){
				storeAuthorityFormat = "商家修改";
			}
		}
		return storeAuthorityFormat;
	}


	public void setStoreAuthorityFormat(String storeAuthorityFormat) {
		this.storeAuthorityFormat = storeAuthorityFormat;
	}

	public String getPicFormat() {
		return picFormat;
	}

	public void setPicFormat(String picFormat) {
		this.picFormat = picFormat;
	}

}