/*
 * 文件名：FeeInfoDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：huwei
 * 修改时间：2018年11月20日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.interf.valuation.send.assetmangementsys.feeinfo.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;

/**
 * 产品费用Dto
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author huwei
 * @version 1.0 2018年11月20日
 * @see FeeInfoDto
 * @since 1.0
 */
public class FeeInfoDto extends BaseEntity{

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * 产品代码
     */
    private String prodId;
    
    /**
     * 账套代码
     */
    private String booksetId;
    
    /**
     * 分层代码
     */
    private String layerId;
    
    /**
     * 科目号
     */
    private String subjectNo;
    
    /**
     * 现金流代码
     */
    private String cashId;
    
    /**
     * 基数类型
     */
    private String baseType;
    
    /**
     * 计提日期
     */
    private String valDate;
    
    /**
     * 产品类型
     */
    private String prodType;
    
    /**
     * 费用类型Code
     */
    private String feeTypeCode;
    
    /**
     * 费用类型
     */
    private String feeType;
    
    /**
     * 费用品种编号：UUID
     */
    private String feeId;
    
    /**
     * 产品总份额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal totalProductShare;
    
    /**
     * 单位净值
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal netAssetValue;
    
    /**
     * 计费基数
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal feeCalcBase;
    
    /**
     * 费率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal feeRate;
    
    /**
     * 计息基础
     */
    private String feeCalcType;
    
    /**
     * 当日累计应付费用
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal feeAmt;
    
    public String getProdId() {
        return prodId;
    }
    
    public void setProdId(String prodId) {
        this.prodId = prodId;
    }
    
    public String getLayerId() {
        return layerId;
    }
    
    public void setLayerId(String layerId) {
        this.layerId = layerId;
    }
    
    public String getValDate() {
        return valDate;
    }
    
    public void setValDate(String valDate) {
        this.valDate = valDate;
    }
    
    public String getProdType() {
        return prodType;
    }
    
    public void setProdType(String prodType) {
        this.prodType = prodType;
    }
    
    public String getFeeType() {
        return feeType;
    }
    
    public void setFeeType(String feeType) {
        this.feeType = feeType;
    }
    
    public String getFeeId() {
        return feeId;
    }
    
    public void setFeeId(String feeId) {
        this.feeId = feeId;
    }
    
    public BigDecimal getFeeCalcBase() {
        return feeCalcBase;
    }
    
    public void setFeeCalcBase(BigDecimal feeCalcBase) {
        this.feeCalcBase = feeCalcBase;
    }
    
    public BigDecimal getFeeRate() {
        return feeRate;
    }
    
    public void setFeeRate(BigDecimal feeRate) {
        this.feeRate = feeRate;
    }
    
    
    public BigDecimal getFeeAmt() {
        return feeAmt;
    }
    
    public void setFeeAmt(BigDecimal feeAmt) {
        this.feeAmt = feeAmt;
    }
    
    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    
    public String getSubjectNo() {
        return subjectNo;
    }

    
    public void setSubjectNo(String subjectNo) {
        this.subjectNo = subjectNo;
    }

    
    public String getFeeTypeCode() {
        return feeTypeCode;
    }

    
    public void setFeeTypeCode(String feeTypeCode) {
        this.feeTypeCode = feeTypeCode;
    }

    
    public String getCashId() {
        return cashId;
    }

    
    public void setCashId(String cashId) {
        this.cashId = cashId;
    }

    
    public String getBaseType() {
        return baseType;
    }

    
    public void setBaseType(String baseType) {
        this.baseType = baseType;
    }

    
    public BigDecimal getTotalProductShare() {
        return totalProductShare;
    }

    
    public void setTotalProductShare(BigDecimal totalProductShare) {
        this.totalProductShare = totalProductShare;
    }

    
    public BigDecimal getNetAssetValue() {
        return netAssetValue;
    }

    
    public void setNetAssetValue(BigDecimal netAssetValue) {
        this.netAssetValue = netAssetValue;
    }

    
    public String getFeeCalcType() {
        return feeCalcType;
    }

    
    public void setFeeCalcType(String feeCalcType) {
        this.feeCalcType = feeCalcType;
    }

    /**
     * 获取 booksetId
     * @return booksetId.
     */
    public String getBooksetId() {
        return booksetId;
    }

    /**
     * 设置 booksetId
     * @param booksetId booksetId
     */
    public void setBooksetId(String booksetId) {
        this.booksetId = booksetId;
    }
    

}
