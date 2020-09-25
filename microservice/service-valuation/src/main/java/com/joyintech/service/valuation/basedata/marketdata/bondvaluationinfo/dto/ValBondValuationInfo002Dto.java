/*
 * 文件名：ValBondValuationInfo001Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：Administrator
 * 修改时间：2018年7月18日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.service.valuation.basedata.marketdata.bondvaluationinfo.dto;

import java.math.BigDecimal;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.constant.DictCode;
/**
 * 双击展示详情dto
 * 
 * @author niuqianyu
 * @version 1.0 2018年7月18日
 * @see ValBondValuationInfo002Dto
 * @since 1.0
 */
public class ValBondValuationInfo002Dto extends BaseEntity {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * 债券代码
     */
    private String secId;

    /**
     * 估值来源
     */
    private String valueSource;

    /**
     * 估值日
     */
    private String valueDate;

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
     * 获取 valueSource
     * @return valueSource.
     */
    public String getValueSource() {
        return this.valueSource;
    }

    
    /**
     * 设置 valueSource
     * @param valueSource valueSource
     */
    public void setValueSource(String valueSource) {
        this.valueSource = valueSource;
    }

    
    /**
     * 获取 valueDate
     * @return valueDate.
     */
    public String getValueDate() {
        return DateUtil.formatDateAddSplit(valueDate);
    }

    
    /**
     * 设置 valueDate
     * @param valueDate valueDate
     */
    public void setValueDate(String valueDate) {
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

    
    
}
