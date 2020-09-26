/*
 * 文件名：TaxCalTypeDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：weipeng
 * 修改时间：2018年8月3日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.batch.vatdraw.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.joyintech.framework.util.DateUtil;

/**
 * 税则共通Dto
 * 
 * @author weipeng
 * @version 1.0 2018年8月3日
 * @see TaxRuleDto
 * @since 1.0
 */
public class TaxRuleDto {

    /**
     * 流水号
     */
    private String seqNo;

    /**
     * 账务明细代码
     */
    private String bokDetailId;

    /**
     * 优先级
     */
    private String priority;

    /**
     * 资产代码
     */
    private String busiId;

    /**
     * 本金收益特征
     */
    private String capiIncomeFeature;

    /**
     * 收入类型
     */
    private String incomeType;

    /**
     * 应税标志
     */
    private String taxableSign;

    /**
     * 应税税率
     */
    private BigDecimal taxRate;

    /**
     * 生效日
     */
    private Date effDate;

    /**
     * 免税收入类型
     */
    private String feeType;

    /**
     * 获取 流水号
     * 
     * @return 流水号.
     */
    public String getSeqNo() {
        return seqNo;
    }

    /**
     * 设置 流水号
     * 
     * @param seqNo 流水号
     */
    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo;
    }

    /**
     * 取得 账务明细代码
     * 
     * @return 账务明细代码
     */
    public String getBokDetailId() {
        return bokDetailId;
    }

    /**
     * 设置 账务明细代码
     * 
     * @param bokDetailId 账务明细代码
     */
    public void setBokDetailId(String bokDetailId) {
        this.bokDetailId = bokDetailId;
    }

    /**
     * 取得 优先级
     * 
     * @return 优先级
     */
    public String getPriority() {
        return priority;
    }

    /**
     * 设置 优先级
     * 
     * @param priority 优先级
     */
    public void setPriority(String priority) {
        this.priority = priority;
    }

    /**
     * 取得 资产代码
     * 
     * @return 资产代码
     */
    public String getBusiId() {
        return busiId;
    }

    /**
     * 设置 资产代码
     * 
     * @param busiId 资产代码
     */
    public void setBusiId(String busiId) {
        this.busiId = busiId;
    }

    /**
     * 取得 本金收益特征
     * 
     * @return 本金收益特征
     */
    public String getCapiIncomeFeature() {
        return capiIncomeFeature;
    }

    /**
     * 设置 本金收益特征
     * 
     * @param capiIncomeFeature 本金收益特征
     */
    public void setCapiIncomeFeature(String capiIncomeFeature) {
        this.capiIncomeFeature = capiIncomeFeature;
    }

    /**
     * 取得 收入类型
     * 
     * @return 收入类型
     */
    public String getIncomeType() {
        return incomeType;
    }

    /**
     * 设置 收入类型
     * 
     * @param incomeType 收入类型
     */
    public void setIncomeType(String incomeType) {
        this.incomeType = incomeType;
    }

    /**
     * 取得 应税标志
     * 
     * @return 应税标志
     */
    public String getTaxableSign() {
        return taxableSign;
    }

    /**
     * 设置 应税标志
     * 
     * @param taxableSign 应税标志
     */
    public void setTaxableSign(String taxableSign) {
        this.taxableSign = taxableSign;
    }

    /**
     * 取得 应税税率
     * 
     * @return 应税税率
     */
    public BigDecimal getTaxRate() {
        return taxRate;
    }

    /**
     * 设置 应税税率
     * 
     * @param taxRate 应税税率
     */
    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

    /**
     * 取得 生效日
     * 
     * @return 生效日
     */
    public Date getEffDate() {
        return effDate;
    }

    /**
     * 设置 生效日
     * 
     * @param effDate 生效日
     */
    public void setEffDate(Date effDate) {
        this.effDate = effDate;
    }

    /**
     * 获取 免税收入类型
     * 
     * @return 免税收入类型
     */
    public String getFeeType() {
        return feeType;
    }

    /**
     * 设置 免税收入类型
     * 
     * @param feeType 免税收入类型
     */
    public void setFeeType(String feeType) {
        this.feeType = feeType;
    }

    /**
     * 获取 分组字符串：账务明细代码+收入类型
     * 
     * @return
     * @see
     * @since 1.0
     */
    public String getGroupStr() {
        return getBokDetailId() + getIncomeType();
    }

    public String getSortStr() {
        return getPriority() + DateUtil.format(getEffDate(), "yyyyMMdd");
    }
}
