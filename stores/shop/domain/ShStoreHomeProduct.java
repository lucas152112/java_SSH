package com.oim.stores.shop.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.oim.stores.common.Content;


public class ShStoreHomeProduct  implements java.io.Serializable {


    // Fields    

	 private Long moduleProductId;
	 private BigDecimal storeModuleId;
     private BigDecimal productId;
     private String toUrl;
     private String titleDesc;
     private String titleColor;
     private BigDecimal isShow;
     private BigDecimal productOrder;
     private String remark;
     private String bigPic;
     private String smallPic;
     private Date updatedDate;
     private String updatedUser;
     
     private String isCreate;
     private String oldProductId;
     private String oldStoreModuleId;
     
     private String moduleDesc; //所属模块名称
     private String productName; //指向商品名称
     
     private String isShowFormat;
     
     private List<String> storeModuleIds;
     private String productPriceFormat; //商品价格
     private String moduleType; //模块类别
     private String promotionPercFormat; //推广比率
     private String promotionFeeFormat; //推广费
     private String promotionFormat; //推广费/推广费百分比
     private String updateUserFormat;
     


    // Constructors

    /** default constructor */
    public ShStoreHomeProduct() {
    }

	
    // Property accessors

    public String getToUrl() {
        return this.toUrl;
    }
    
    public Long getModuleProductId() {
		return moduleProductId;
	}


	public void setModuleProductId(Long moduleProductId) {
		this.moduleProductId = moduleProductId;
	}


	public BigDecimal getStoreModuleId() {
		return storeModuleId;
	}


	public void setStoreModuleId(BigDecimal storeModuleId) {
		this.storeModuleId = storeModuleId;
	}


	public BigDecimal getProductId() {
		return productId;
	}


	public void setProductId(BigDecimal productId) {
		this.productId = productId;
	}


	public void setToUrl(String toUrl) {
        this.toUrl = toUrl;
    }

    public String getTitleDesc() {
        return this.titleDesc;
    }
    
    
    /**
	 * @return the updateUserFormat
	 */
	public String getUpdateUserFormat() {
		return updateUserFormat;
	}


	/**
	 * @param updateUserFormat the updateUserFormat to set
	 */
	public void setUpdateUserFormat(String updateUserFormat) {
		this.updateUserFormat = updateUserFormat;
	}


	public void setTitleDesc(String titleDesc) {
        this.titleDesc = titleDesc;
    }

    public String getTitleColor() {
        return this.titleColor;
    }
    
    public void setTitleColor(String titleColor) {
        this.titleColor = titleColor;
    }

    public BigDecimal getIsShow() {
        return this.isShow;
    }
    
    public void setIsShow(BigDecimal isShow) {
        this.isShow = isShow;
    }

    public BigDecimal getProductOrder() {
        return this.productOrder;
    }
    
    public void setProductOrder(BigDecimal productOrder) {
        this.productOrder = productOrder;
    }

    public String getRemark() {
        return this.remark;
    }
    
    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getBigPic() {
        return this.bigPic;
    }
    
    public void setBigPic(String bigPic) {
        this.bigPic = bigPic;
    }

    public String getSmallPic() {
        return this.smallPic;
    }
    
    public void setSmallPic(String smallPic) {
        this.smallPic = smallPic;
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

	public String getModuleDesc() {
		return moduleDesc;
	}

	public void setModuleDesc(String moduleDesc) {
		this.moduleDesc = moduleDesc;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getIsShowFormat() {
		if(isShow != null){
			if(isShow.toString().equals(Content.MODULE_IS_SHOW_TRUE)){
				isShowFormat = "显示";
			}else if(isShow.toString().equals(Content.MODULE_IS_SHOW_FALSE)){
				isShowFormat = "隐藏";
			}
		}
		return isShowFormat;
	}

	public void setIsShowFormat(String isShowFormat) {
		this.isShowFormat = isShowFormat;
	}

	public String getIsCreate() {
		return isCreate;
	}

	public void setIsCreate(String isCreate) {
		this.isCreate = isCreate;
	}

	public String getOldProductId() {
		return oldProductId;
	}

	public void setOldProductId(String oldProductId) {
		this.oldProductId = oldProductId;
	}

	public String getOldStoreModuleId() {
		return oldStoreModuleId;
	}

	public void setOldStoreModuleId(String oldStoreModuleId) {
		this.oldStoreModuleId = oldStoreModuleId;
	}


	public List<String> getStoreModuleIds() {
		return storeModuleIds;
	}


	public void setStoreModuleIds(List<String> storeModuleIds) {
		this.storeModuleIds = storeModuleIds;
	}


	/**
	 * @return the productPriceFormat
	 */
	public String getProductPriceFormat() {
		return productPriceFormat;
	}


	/**
	 * @param productPriceFormat the productPriceFormat to set
	 */
	public void setProductPriceFormat(String productPriceFormat) {
		this.productPriceFormat = productPriceFormat;
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
	 * @return the promotionPercFormat
	 */
	public String getPromotionPercFormat() {
		return promotionPercFormat;
	}


	/**
	 * @param promotionPercFormat the promotionPercFormat to set
	 */
	public void setPromotionPercFormat(String promotionPercFormat) {
		this.promotionPercFormat = promotionPercFormat;
	}


	/**
	 * @return the promotionFeeFormat
	 */
	public String getPromotionFeeFormat() {
		return promotionFeeFormat;
	}


	/**
	 * @param promotionFeeFormat the promotionFeeFormat to set
	 */
	public void setPromotionFeeFormat(String promotionFeeFormat) {
		this.promotionFeeFormat = promotionFeeFormat;
	}


	/**
	 * @return the promotionFormat
	 */
	public String getPromotionFormat() {
		return promotionFormat;
	}


	/**
	 * @param promotionFormat the promotionFormat to set
	 */
	public void setPromotionFormat(String promotionFormat) {
		this.promotionFormat = promotionFormat;
	}
	
   
}