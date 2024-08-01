package com.oim.stores.shop.domain;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * 分销商品信息
 */
public class ShProductShare  implements java.io.Serializable {

     private BigDecimal productId;
     private BigDecimal shareStaus;
     /** 推广费百分比 */
  	private Double promotionPerc;
  	/** 推广费 */
  	private Double promotionFee;
  	
  	private String promotionFormat; //推广费/推广费百分比
  	private String promotionPercFormat; //推广比率
    private String promotionFeeFormat; //推广费


    // Constructors

    /** default constructor */
    public ShProductShare() {
    }

    
    /** full constructor */
    public ShProductShare(BigDecimal productId, BigDecimal shareStaus) {
        this.productId = productId;
        this.shareStaus = shareStaus;
    }

   
    // Property accessors

    public BigDecimal getProductId() {
        return this.productId;
    }
    
    public void setProductId(BigDecimal productId) {
        this.productId = productId;
    }

    public BigDecimal getShareStaus() {
        return this.shareStaus;
    }
    
    public void setShareStaus(BigDecimal shareStaus) {
        this.shareStaus = shareStaus;
    }

	public Double getPromotionPerc() {
		return promotionPerc;
	}

	public void setPromotionPerc(Double promotionPerc) {
		this.promotionPerc = promotionPerc;
	}

	public Double getPromotionFee() {
		return promotionFee;
	}

	public void setPromotionFee(Double promotionFee) {
		this.promotionFee = promotionFee;
	}

	public String getPromotionFormat() {
		NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(Locale.CHINA);
		if (promotionFee != null && promotionFee.doubleValue() > 0) {
			String sum = currencyFormat.format(promotionFee);
			promotionFormat = sum;
		} else if (promotionPerc != null && promotionPerc.doubleValue() > 0) {
			String sum = currencyFormat.format(promotionPerc);
			promotionFormat = sum.substring(1, sum.length()) + "%";
		}
		return promotionFormat;
	}

	public void setPromotionFormat(String promotionFormat) {
		this.promotionFormat = promotionFormat;
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
	
   
}