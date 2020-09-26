/*
 * 文件名：PositionTemplateDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：niuqianyu
 * 修改时间：2018年11月8日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.datatransplant.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 头寸模版实体类
 * 
 * @author niuqianyu
 * @version 1.0 2018年11月8日
 * @see PositionTemplateDto
 * @since 1.0
 */
public class PositionTemplateDto {

    /**
     * 账套代码
     */
    public String booksetId;

    /**
     * 产品/组合代码
     */
    public String productId;

    /**
     * 日期
     */
    public Date date;

    /**
     * 收益类型
     */
    public String profitType;

    /**
     * 实收资本
     */
    public BigDecimal capital;

    /**
     * 资产净值
     */
    public BigDecimal netAssetValue;

    /**
     * 单位净值
     */
    public BigDecimal netUnitValue;

    /**
     * 万份收益
     */
    public BigDecimal pYield;

    /**
     * 累加分红金额
     */
    public BigDecimal totBounsAmt;

    /**
     * 累加分红净值
     */
    public BigDecimal totBounsNet;

    /**
     * 获取 账套代码
     * 
     * @return booksetId.
     */
    public String getBooksetId() {
        return booksetId;
    }

    /**
     * 设置 账套代码
     * 
     * @param booksetId booksetId
     */
    public void setBooksetId(String booksetId) {
        this.booksetId = booksetId;
    }

    /**
     * 获取 产品/组合代码
     * 
     * @return productId.
     */
    public String getProductId() {
        return productId;
    }

    /**
     * 设置 产品/组合代码
     * 
     * @param productId productId
     */
    public void setProductId(String productId) {
        this.productId = productId;
    }

    /**
     * 获取 日期
     * 
     * @return date.
     */
    public Date getDate() {
        return date;
    }

    /**
     * 设置 日期
     * 
     * @param date date
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * 获取 收益类型
     * 
     * @return profitType.
     */
    public String getProfitType() {
        return profitType;
    }

    /**
     * 设置 收益类型
     * 
     * @param profitType profitType
     */
    public void setProfitType(String profitType) {
        this.profitType = profitType;
    }

    /**
     * 获取 实收资本
     * 
     * @return capital.
     */
    public BigDecimal getCapital() {
        return capital;
    }

    /**
     * 设置 实收资本
     * 
     * @param capital capital
     */
    public void setCapital(BigDecimal capital) {
        this.capital = capital;
    }

    /**
     * 获取 资产净值
     * 
     * @return netAssetValue.
     */
    public BigDecimal getNetAssetValue() {
        return netAssetValue;
    }

    /**
     * 设置 资产净值
     * 
     * @param netAssetValue netAssetValue
     */
    public void setNetAssetValue(BigDecimal netAssetValue) {
        this.netAssetValue = netAssetValue;
    }

    /**
     * 获取 单位净值
     * 
     * @return netUnitValue.
     */
    public BigDecimal getNetUnitValue() {
        return netUnitValue;
    }

    /**
     * 设置 单位净值
     * 
     * @param netUnitValue netUnitValue
     */
    public void setNetUnitValue(BigDecimal netUnitValue) {
        this.netUnitValue = netUnitValue;
    }

    /**
     * 获取 万份收益
     * 
     * @return pYield.
     */
    public BigDecimal getpYield() {
        return pYield;
    }

    /**
     * 设置 万份收益
     * 
     * @param pYield pYield
     */
    public void setpYield(BigDecimal pYield) {
        this.pYield = pYield;
    }

    /**
     * 获取 累加分红金额
     * 
     * @return totBounsAmt.
     */
    public BigDecimal getTotBounsAmt() {
        return totBounsAmt;
    }

    /**
     * 设置 累加分红金额
     * 
     * @param totBounsAmt totBounsAmt
     */
    public void setTotBounsAmt(BigDecimal totBounsAmt) {
        this.totBounsAmt = totBounsAmt;
    }

    /**
     * 获取 累加分红净值
     * 
     * @return totBounsNet.
     */
    public BigDecimal getTotBounsNet() {
        return totBounsNet;
    }

    /**
     * 设置 累加分红净值
     * 
     * @param totBounsNet totBounsNet
     */
    public void setTotBounsNet(BigDecimal totBounsNet) {
        this.totBounsNet = totBounsNet;
    }

}
