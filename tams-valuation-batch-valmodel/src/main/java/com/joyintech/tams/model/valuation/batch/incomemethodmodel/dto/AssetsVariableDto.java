/*
 * 文件名：ValBondValuationInfo003Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：ZhangYangFei
 * 修改时间：2018年12月7日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.model.valuation.batch.incomemethodmodel.dto;

import com.joyintech.batch.batchbase.baseclass.entity.NeedToWriteEntity;
import com.joyintech.tams.model.valuation.batch.binarytreemodel.dto.ModYieldCurveInfoDto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * @Description:process计算变量dto
 * @auther: yuzj
 * @date: 2021/7/5
 * @param:
 * @return:
 */
public class AssetsVariableDto  {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;

    /**
     * 股息预期股息增长率
     */
    private BigDecimal stockIntrate;

    /**
     * 年股息
     */
    private BigDecimal yearDividend;

    /**
     * 年折现率
     */
    private BigDecimal yearDiscountRate;


    /**
     * 当前面值
     */
    private BigDecimal faceValue;

    /**
     * 票面利值
     */
    private BigDecimal paperRate;

    /**
     * 国债收益率
     */
    private BigDecimal governmentDebtYield;

    /**
     * 买入全价
     */
    private BigDecimal bFullprice;

    /**
     * 当天是否存在买入交易
     */
    private String  isTodayTrade;

    /**
     * 基准日
     */
    private Date benchDate;

    /**
     * 已还本面值
     */
    private BigDecimal paidPrice;

    /**
     * 估值日曲线收益率列表
     */
    List<ModYieldCurveInfoDto> modYieldCurveInfoVList;

    /**
     * 基准日曲线收益率列表
     */
    List<ModYieldCurveInfoDto> modYieldCurveInfoBList;


    /**
     * 估值日国债收益率
     */
    private BigDecimal valuationGovernmentDebtYield;

    /**
     * 基准日国债收益率
     */
    private BigDecimal benchDateGovernmentDebtYield;

    /**
     * 估值全价
     */
    private BigDecimal fullprice;
    /**
     * 估值净价
     */
    private BigDecimal bAnalNetCnbd;
    /**
     * 单位应计利息
     */
    private BigDecimal interest;


    public AssetsVariableDto(BigDecimal stockIntrate, BigDecimal yearDividend, BigDecimal yearDiscountRate, BigDecimal faceValue,
                             BigDecimal paperRate, BigDecimal governmentDebtYield, BigDecimal bFullprice, String isTodayTrade,
                             BigDecimal paidPrice,BigDecimal valuationGovernmentDebtYield,BigDecimal benchDateGovernmentDebtYield,
                             BigDecimal fullprice,BigDecimal bAnalNetCnbd,BigDecimal interest) {
        this.stockIntrate = stockIntrate;
        this.yearDividend = yearDividend;
        this.yearDiscountRate = yearDiscountRate;
        this.faceValue = faceValue;
        this.paperRate = paperRate;
        this.governmentDebtYield = governmentDebtYield;
        this.bFullprice = bFullprice;
        this.isTodayTrade = isTodayTrade;
        this.paidPrice = paidPrice;
        this.valuationGovernmentDebtYield = valuationGovernmentDebtYield;
        this.benchDateGovernmentDebtYield = benchDateGovernmentDebtYield;
        this.fullprice = fullprice;
        this.bAnalNetCnbd = bAnalNetCnbd;
        this.interest = interest;
    }

    public BigDecimal getStockIntrate() {
        return stockIntrate;
    }

    public void setStockIntrate(BigDecimal stockIntrate) {
        this.stockIntrate = stockIntrate;
    }

    public BigDecimal getYearDividend() {
        return yearDividend;
    }

    public void setYearDividend(BigDecimal yearDividend) {
        this.yearDividend = yearDividend;
    }

    public BigDecimal getYearDiscountRate() {
        return yearDiscountRate;
    }

    public void setYearDiscountRate(BigDecimal yearDiscountRate) {
        this.yearDiscountRate = yearDiscountRate;
    }

    public BigDecimal getFaceValue() {
        return faceValue;
    }

    public void setFaceValue(BigDecimal faceValue) {
        this.faceValue = faceValue;
    }

    public BigDecimal getPaperRate() {
        return paperRate;
    }

    public void setPaperRate(BigDecimal paperRate) {
        this.paperRate = paperRate;
    }

    public BigDecimal getGovernmentDebtYield() {
        return governmentDebtYield;
    }

    public void setGovernmentDebtYield(BigDecimal governmentDebtYield) {
        this.governmentDebtYield = governmentDebtYield;
    }

    public BigDecimal getbFullprice() {
        return bFullprice;
    }

    public void setbFullprice(BigDecimal bFullprice) {
        this.bFullprice = bFullprice;
    }

    public String getIsTodayTrade() {
        return isTodayTrade;
    }

    public void setIsTodayTrade(String isTodayTrade) {
        this.isTodayTrade = isTodayTrade;
    }

    public Date getBenchDate() {
        return benchDate;
    }

    public void setBenchDate(Date benchDate) {
        this.benchDate = benchDate;
    }

    public BigDecimal getPaidPrice() {
        return paidPrice;
    }

    public void setPaidPrice(BigDecimal paidPrice) {
        this.paidPrice = paidPrice;
    }

    public List<ModYieldCurveInfoDto> getModYieldCurveInfoVList() {
        return modYieldCurveInfoVList;
    }

    public void setModYieldCurveInfoVList(List<ModYieldCurveInfoDto> modYieldCurveInfoVList) {
        this.modYieldCurveInfoVList = modYieldCurveInfoVList;
    }

    public List<ModYieldCurveInfoDto> getModYieldCurveInfoBList() {
        return modYieldCurveInfoBList;
    }

    public void setModYieldCurveInfoBList(List<ModYieldCurveInfoDto> modYieldCurveInfoBList) {
        this.modYieldCurveInfoBList = modYieldCurveInfoBList;
    }

    public BigDecimal getValuationGovernmentDebtYield() {
        return valuationGovernmentDebtYield;
    }

    public void setValuationGovernmentDebtYield(BigDecimal valuationGovernmentDebtYield) {
        this.valuationGovernmentDebtYield = valuationGovernmentDebtYield;
    }

    public BigDecimal getBenchDateGovernmentDebtYield() {
        return benchDateGovernmentDebtYield;
    }

    public void setBenchDateGovernmentDebtYield(BigDecimal benchDateGovernmentDebtYield) {
        this.benchDateGovernmentDebtYield = benchDateGovernmentDebtYield;
    }

    public BigDecimal getFullprice() {
        return fullprice;
    }

    public void setFullprice(BigDecimal fullprice) {
        this.fullprice = fullprice;
    }

    public BigDecimal getbAnalNetCnbd() {
        return bAnalNetCnbd;
    }

    public void setbAnalNetCnbd(BigDecimal bAnalNetCnbd) {
        this.bAnalNetCnbd = bAnalNetCnbd;
    }

    public BigDecimal getInterest() {
        return interest;
    }

    public void setInterest(BigDecimal interest) {
        this.interest = interest;
    }
}
