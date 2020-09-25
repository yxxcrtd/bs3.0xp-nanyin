/*
 * 文件名：ValIncomeStatisticsQuery001Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：HuangJian
 * 修改时间：2018年11月1日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.service.valuation.bankaccount.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.service.valuation.bankaccount.validator.ValIncomeStatisticsQuery001CheckValidator;
import com.joyintech.tams.constant.DictCode;

/**
 * 收入统计查询
 * 
 * @author HuangJian
 * @version 1.0 2018年11月1日
 * @see ValIncomeStatisticsQuery001Dto
 * @since 1.0
 */
@ValidLogic(targetClass = ValIncomeStatisticsQuery001CheckValidator.class)
public class ValIncomeStatisticsQuery001Dto extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 开始日期
     */
    private Date vdate;

    /**
     * 结束日期
     */
    @NotEmpty(fieldName = "ValIncomeStatistics.mdate")
    private Date mdate;

    /**
     * 产品代码
     */
    private String productId;

    /**
     * 日期统计维度
     */
    @NotEmpty(fieldName = "ValIncomeStatistics.dateDimension")
    private String dateDimension;

    /**
     * 产品统计维度
     */
    @NotEmpty(fieldName = "ValIncomeStatistics.productDimension")
    private String productDimension;

    /**
     * 产品
     */
    private String product;

    /**
     * 本金标识
     */
    private String amtFlag;

    /**
     * 日期
     */
    private String cdate;

    /**
     * 已实现收入
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal realizedAmt;

    /**
     * 已提取收入
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal extractAmt;

    /**
     * 取得 开始日期
     * 
     * @return 开始日期
     */
    public Date getVdate() {
        return vdate;
    }

    /**
     * 设置 开始日期
     * 
     * @param vdate 开始日期
     */
    public void setVdate(Date vdate) {
        this.vdate = vdate;
    }

    /**
     * 取得 结束日期
     * 
     * @return 结束日期
     */
    public Date getMdate() {
        return mdate;
    }

    /**
     * 设置 结束日期
     * 
     * @param mdate 结束日期
     */
    public void setMdate(Date mdate) {
        this.mdate = mdate;
    }

    /**
     * 取得 产品代码
     * 
     * @return 产品代码
     */
    public String getProductId() {
        return productId;
    }

    /**
     * 设置 产品代码
     * 
     * @param productId 产品代码
     */
    public void setProductId(String productId) {
        this.productId = productId;
    }

    /**
     * 取得 日期统计维度
     * 
     * @return 日期统计维度
     */
    public String getDateDimension() {
        return dateDimension;
    }

    /**
     * 设置 日期统计维度
     * 
     * @param dateDimension 日期统计维度
     */
    public void setDateDimension(String dateDimension) {
        this.dateDimension = dateDimension;
    }

    /**
     * 取得 产品统计维度
     * 
     * @return 产品统计维度
     */
    public String getProductDimension() {
        return productDimension;
    }

    /**
     * 设置 产品统计维度
     * 
     * @param productDimension 产品统计维度
     */
    public void setProductDimension(String productDimension) {
        this.productDimension = productDimension;
    }

    /**
     * 取得 产品
     * 
     * @return 产品
     */
    public String getProduct() {
        return product;
    }

    /**
     * 设置 产品
     * 
     * @param product 产品
     */
    public void setProduct(String product) {
        this.product = product;
    }

    /**
     * 取得 本金标识
     * 
     * @return 本金标识
     */
    public String getAmtFlag() {
        return amtFlag;
    }

    /**
     * 设置 本金标识
     * 
     * @param amtFlag 本金标识
     */
    public void setAmtFlag(String amtFlag) {
        this.amtFlag = amtFlag;
    }

    /**
     * 取得 日期
     * 
     * @return 日期
     */
    public String getCdate() {
        return cdate;
    }

    /**
     * 设置 日期
     * 
     * @param date 日期
     */
    public void setCdate(String date) {
        this.cdate = date;
    }

    /**
     * 取得 已实现收入
     * 
     * @return 已实现收入
     */
    public BigDecimal getRealizedAmt() {
        return realizedAmt;
    }

    /**
     * 设置 已实现收入
     * 
     * @param realizedAmt 已实现收入
     */
    public void setRealizedAmt(BigDecimal realizedAmt) {
        this.realizedAmt = realizedAmt;
    }

    /**
     * 取得 已提取收入
     * 
     * @return 已提取收入
     */
    public BigDecimal getExtractAmt() {
        return extractAmt;
    }

    /**
     * 设置 已提取收入
     * 
     * @param extractAmt 已提取收入
     */
    public void setExtractAmt(BigDecimal extractAmt) {
        this.extractAmt = extractAmt;
    }

}
