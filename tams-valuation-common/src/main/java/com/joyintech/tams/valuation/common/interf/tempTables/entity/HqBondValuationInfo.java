package com.joyintech.tams.valuation.common.interf.tempTables.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.joyintech.framework.base.BaseEntity;

public class HqBondValuationInfo extends BaseEntity {

    /**
     * 债券代码
     */
    private String secId;

    /**
     * 估值来源
     */
    private String valueource;
    
    /**
     * 估值日
     */
    private Date valueDate;

    /**
     * 剩余期限
     */
    private BigDecimal lastPeriod;

    /**
     * 市价全价
     */
    private BigDecimal price;

    /**
     * 市价净价
     */
    private BigDecimal netprice;

    /**
     * 市价久期
     */
    private BigDecimal mDuration;

    /**
     * 市价凸性
     */
    private BigDecimal mConvexity;

    /**
     * 基点价值
     */
    private BigDecimal bpvalue;

    /**
     * 利差久期
     */
    private BigDecimal sduration;
    
    /**
     * 利差凸性
     */
    private BigDecimal scnvxty;
    
    /**
     * 利率久期
     */
    private BigDecimal interestDuration;
    
    /**
     * 利率凸性
     */
    private BigDecimal interestCnvxty;
    
    /**
     * 市价收益率
     */
    private BigDecimal bpyield;
    
    /**
     * 是否推荐
     */
    private String relibity;

    
    /**
     * 获取 secId
     * @return secId.
     */
    public String getSecId() {
        return secId;
    }

    
    /**
     * 设置 secId
     * @param secId secId
     */
    public void setSecId(String secId) {
        this.secId = secId;
    }

    
    /**
     * 获取 valueource
     * @return valueource.
     */
    public String getValueource() {
        return valueource;
    }

    
    /**
     * 设置 valueource
     * @param valueource valueource
     */
    public void setValueource(String valueource) {
        this.valueource = valueource;
    }

    
    /**
     * 获取 valueDate
     * @return valueDate.
     */
    public Date getValueDate() {
        return valueDate;
    }

    
    /**
     * 设置 valueDate
     * @param valueDate valueDate
     */
    public void setValueDate(Date valueDate) {
        this.valueDate = valueDate;
    }

    
    /**
     * 获取 lastPeriod
     * @return lastPeriod.
     */
    public BigDecimal getLastPeriod() {
        return lastPeriod;
    }

    
    /**
     * 设置 lastPeriod
     * @param lastPeriod lastPeriod
     */
    public void setLastPeriod(BigDecimal lastPeriod) {
        this.lastPeriod = lastPeriod;
    }

    
    /**
     * 获取 price
     * @return price.
     */
    public BigDecimal getPrice() {
        return price;
    }

    
    /**
     * 设置 price
     * @param price price
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    
    /**
     * 获取 netprice
     * @return netprice.
     */
    public BigDecimal getNetprice() {
        return netprice;
    }

    
    /**
     * 设置 netprice
     * @param netprice netprice
     */
    public void setNetprice(BigDecimal netprice) {
        this.netprice = netprice;
    }

    
    /**
     * 获取 mDuration
     * @return mDuration.
     */
    public BigDecimal getmDuration() {
        return mDuration;
    }

    
    /**
     * 设置 mDuration
     * @param mDuration mDuration
     */
    public void setmDuration(BigDecimal mDuration) {
        this.mDuration = mDuration;
    }

    
    /**
     * 获取 mConvexity
     * @return mConvexity.
     */
    public BigDecimal getmConvexity() {
        return mConvexity;
    }

    
    /**
     * 设置 mConvexity
     * @param mConvexity mConvexity
     */
    public void setmConvexity(BigDecimal mConvexity) {
        this.mConvexity = mConvexity;
    }

    
    /**
     * 获取 bpvalue
     * @return bpvalue.
     */
    public BigDecimal getBpvalue() {
        return bpvalue;
    }

    
    /**
     * 设置 bpvalue
     * @param bpvalue bpvalue
     */
    public void setBpvalue(BigDecimal bpvalue) {
        this.bpvalue = bpvalue;
    }

    
    /**
     * 获取 sduration
     * @return sduration.
     */
    public BigDecimal getSduration() {
        return sduration;
    }

    
    /**
     * 设置 sduration
     * @param sduration sduration
     */
    public void setSduration(BigDecimal sduration) {
        this.sduration = sduration;
    }

    
    /**
     * 获取 scnvxty
     * @return scnvxty.
     */
    public BigDecimal getScnvxty() {
        return scnvxty;
    }

    
    /**
     * 设置 scnvxty
     * @param scnvxty scnvxty
     */
    public void setScnvxty(BigDecimal scnvxty) {
        this.scnvxty = scnvxty;
    }

    
    /**
     * 获取 interestDuration
     * @return interestDuration.
     */
    public BigDecimal getInterestDuration() {
        return interestDuration;
    }

    
    /**
     * 设置 interestDuration
     * @param interestDuration interestDuration
     */
    public void setInterestDuration(BigDecimal interestDuration) {
        this.interestDuration = interestDuration;
    }

    
    /**
     * 获取 interestCnvxty
     * @return interestCnvxty.
     */
    public BigDecimal getInterestCnvxty() {
        return interestCnvxty;
    }

    
    /**
     * 设置 interestCnvxty
     * @param interestCnvxty interestCnvxty
     */
    public void setInterestCnvxty(BigDecimal interestCnvxty) {
        this.interestCnvxty = interestCnvxty;
    }

    
    /**
     * 获取 bpyield
     * @return bpyield.
     */
    public BigDecimal getBpyield() {
        return bpyield;
    }

    
    /**
     * 设置 bpyield
     * @param bpyield bpyield
     */
    public void setBpyield(BigDecimal bpyield) {
        this.bpyield = bpyield;
    }


	/**
	 * @return the relibity
	 */
	public String getRelibity() {
		return relibity;
	}


	/**
	 * @param relibity the relibity to set
	 */
	public void setRelibity(String relibity) {
		this.relibity = relibity;
	}

}