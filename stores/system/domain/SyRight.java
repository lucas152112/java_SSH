package com.oim.stores.system.domain;
// default package

import java.math.BigDecimal;


/**
 * SyRight entity. @author MyEclipse Persistence Tools
 */

public class SyRight  implements java.io.Serializable {


    // Fields    

     private BigDecimal rightId;
     private String rightName;
     private BigDecimal rightType;
     private String rightValue;
     private BigDecimal parentRightId;
     private String rightCode;
     private BigDecimal rightRange;
     private String rightDesc;
     private BigDecimal rightSeq;


    // Constructors

    /** default constructor */
    public SyRight() {
    }

    
    /** full constructor */
    public SyRight(String rightName, BigDecimal rightType, String rightValue, BigDecimal parentRightId, String rightCode, BigDecimal rightRange, String rightDesc, BigDecimal rightSeq) {
        this.rightName = rightName;
        this.rightType = rightType;
        this.rightValue = rightValue;
        this.parentRightId = parentRightId;
        this.rightCode = rightCode;
        this.rightRange = rightRange;
        this.rightDesc = rightDesc;
        this.rightSeq = rightSeq;
    }

   
    // Property accessors

    public BigDecimal getRightId() {
        return this.rightId;
    }
    
    public void setRightId(BigDecimal rightId) {
        this.rightId = rightId;
    }

    public String getRightName() {
        return this.rightName;
    }
    
    public void setRightName(String rightName) {
        this.rightName = rightName;
    }

    public BigDecimal getRightType() {
        return this.rightType;
    }
    
    public void setRightType(BigDecimal rightType) {
        this.rightType = rightType;
    }

    public String getRightValue() {
        return this.rightValue;
    }
    
    public void setRightValue(String rightValue) {
        this.rightValue = rightValue;
    }

    public BigDecimal getParentRightId() {
        return this.parentRightId;
    }
    
    public void setParentRightId(BigDecimal parentRightId) {
        this.parentRightId = parentRightId;
    }

    public String getRightCode() {
        return this.rightCode;
    }
    
    public void setRightCode(String rightCode) {
        this.rightCode = rightCode;
    }

    public BigDecimal getRightRange() {
        return this.rightRange;
    }
    
    public void setRightRange(BigDecimal rightRange) {
        this.rightRange = rightRange;
    }

    public String getRightDesc() {
        return this.rightDesc;
    }
    
    public void setRightDesc(String rightDesc) {
        this.rightDesc = rightDesc;
    }

    public BigDecimal getRightSeq() {
        return this.rightSeq;
    }
    
    public void setRightSeq(BigDecimal rightSeq) {
        this.rightSeq = rightSeq;
    }


    /* 
     * @see java.lang.Object#hashCode()
     * @return
     * Author zhuangjf
     * Create On 2013-4-8 下午03:22:47
     */
    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result
		+ ((parentRightId == null) ? 0 : parentRightId.hashCode());
	result = prime * result
		+ ((rightCode == null) ? 0 : rightCode.hashCode());
	result = prime * result
		+ ((rightDesc == null) ? 0 : rightDesc.hashCode());
	result = prime * result + ((rightId == null) ? 0 : rightId.hashCode());
	result = prime * result
		+ ((rightName == null) ? 0 : rightName.hashCode());
	result = prime * result
		+ ((rightRange == null) ? 0 : rightRange.hashCode());
	result = prime * result
		+ ((rightSeq == null) ? 0 : rightSeq.hashCode());
	result = prime * result
		+ ((rightType == null) ? 0 : rightType.hashCode());
	result = prime * result
		+ ((rightValue == null) ? 0 : rightValue.hashCode());
	return result;
    }


    /* 
     * @see java.lang.Object#equals(java.lang.Object)
     * @param obj
     * @return
     * Author zhuangjf
     * Create On 2013-4-8 下午03:22:47
     */
    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	SyRight other = (SyRight) obj;
	if (parentRightId == null) {
	    if (other.parentRightId != null)
		return false;
	} else if (!parentRightId.equals(other.parentRightId))
	    return false;
	if (rightCode == null) {
	    if (other.rightCode != null)
		return false;
	} else if (!rightCode.equals(other.rightCode))
	    return false;
	if (rightDesc == null) {
	    if (other.rightDesc != null)
		return false;
	} else if (!rightDesc.equals(other.rightDesc))
	    return false;
	if (rightId == null) {
	    if (other.rightId != null)
		return false;
	} else if (!rightId.equals(other.rightId))
	    return false;
	if (rightName == null) {
	    if (other.rightName != null)
		return false;
	} else if (!rightName.equals(other.rightName))
	    return false;
	if (rightRange == null) {
	    if (other.rightRange != null)
		return false;
	} else if (!rightRange.equals(other.rightRange))
	    return false;
	if (rightSeq == null) {
	    if (other.rightSeq != null)
		return false;
	} else if (!rightSeq.equals(other.rightSeq))
	    return false;
	if (rightType == null) {
	    if (other.rightType != null)
		return false;
	} else if (!rightType.equals(other.rightType))
	    return false;
	if (rightValue == null) {
	    if (other.rightValue != null)
		return false;
	} else if (!rightValue.equals(other.rightValue))
	    return false;
	return true;
    }
   








}