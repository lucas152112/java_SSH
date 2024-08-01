package com.oim.stores.shop.domain;

import java.math.BigDecimal;

/**
 * 商家密钥
 * @author Administrator
 *
 */
public class ShStoreSecurity  implements java.io.Serializable {

     private BigDecimal storeId;
     private String storeKey;


    // Constructors

    /** default constructor */
    public ShStoreSecurity() {
    }

    
    /** full constructor */
    public ShStoreSecurity(BigDecimal storeId, String storeKey) {
        this.storeId = storeId;
        this.storeKey = storeKey;
    }

   
    // Property accessors

    public BigDecimal getStoreId() {
        return this.storeId;
    }
    
    public void setStoreId(BigDecimal storeId) {
        this.storeId = storeId;
    }

    public String getStoreKey() {
        return this.storeKey;
    }
    
    public void setStoreKey(String storeKey) {
        this.storeKey = storeKey;
    }
   








}