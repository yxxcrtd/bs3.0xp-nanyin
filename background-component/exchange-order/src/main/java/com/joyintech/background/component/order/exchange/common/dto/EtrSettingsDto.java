/*
 * 文件名：EtrSettingsDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2020年7月9日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.background.component.order.exchange.common.dto;

import java.math.BigDecimal;
import java.util.Map;

import com.joyintech.background.component.order.exchange.common.constant.UnitEnum;

/**
 * 投资指令组件指令配置项Dto
 * 
 * @author zhangrui1
 * @version 1.0 2020年7月9日
 * @see EtrSettingsDto
 * @since 1.0
 */
class EtrSettingsDto {

    /**
     * 最小买卖单位
     */
    private BigDecimal minDealUnit;

    /**
     * 最小差价
     */
    private BigDecimal minDiffPrice;

    /**
     * 价格上限（涨停价格）
     */
    private BigDecimal limitUpAmt;

    /**
     * 价格下限（跌停价格）
     */
    private BigDecimal limitDnAmt;

    /**
     * 大宗交易平台的数量限额
     */
    private Map<String, BigDecimal[]> limitNum;

    /**
     * 大宗交易平台的金额限额
     */
    private Map<String, BigDecimal[]> limitAmt;

    /**
     * 大宗交易平台指令数量单位
     */
    private Map<String, UnitEnum> numUnit;

    /**
     * 最小申报单位
     */
    private BigDecimal minReportUnit;

    /**
     * 获取 最小买卖单位
     * 
     * @return 最小买卖单位.
     */
    public BigDecimal getMinDealUnit() {
        return minDealUnit;
    }

    /**
     * 设置 最小买卖单位
     * 
     * @param minDealUnit 最小买卖单位
     */
    public void setMinDealUnit(BigDecimal minDealUnit) {
        this.minDealUnit = minDealUnit;
    }

    /**
     * 获取 最小差价
     * 
     * @return 最小差价.
     */
    public BigDecimal getMinDiffPrice() {
        return minDiffPrice;
    }

    /**
     * 设置 最小差价
     * 
     * @param minDiffPrice 最小差价
     */
    public void setMinDiffPrice(BigDecimal minDiffPrice) {
        this.minDiffPrice = minDiffPrice;
    }

    /**
     * 获取 设置 价格上限（涨停价格）
     * 
     * @return 设置 价格上限（涨停价格）.
     */
    public BigDecimal getLimitUpAmt() {
        return limitUpAmt;
    }

    /**
     * 设置 价格上限（涨停价格）
     * 
     * @param limitUpAmt 设置 价格上限（涨停价格）
     */
    public void setLimitUpAmt(BigDecimal limitUpAmt) {
        this.limitUpAmt = limitUpAmt;
    }

    /**
     * 获取 价格下限（跌停价格）
     * 
     * @return 价格下限（跌停价格）.
     */
    public BigDecimal getLimitDnAmt() {
        return limitDnAmt;
    }

    /**
     * 设置 价格下限（跌停价格）
     * 
     * @param limitDnAmt 价格下限（跌停价格）
     */
    public void setLimitDnAmt(BigDecimal limitDnAmt) {
        this.limitDnAmt = limitDnAmt;
    }

    /**
     * 获取 大宗交易平台数量限额
     * 
     * @return 大宗交易平台数量限额.
     */
    public Map<String, BigDecimal[]> getLimitNum() {
        return limitNum;
    }

    /**
     * 设置 大宗交易平台数量限额
     * 
     * @param limitNum 大宗交易平台数量限额
     */
    public void setLimitNum(Map<String, BigDecimal[]> limitNum) {
        this.limitNum = limitNum;
    }

    /**
     * 获取 大宗交易平台指令数量单位
     * 
     * @return 大宗交易平台指令数量单位.
     */
    public Map<String, UnitEnum> getNumUnit() {
        return numUnit;
    }

    /**
     * 取得大宗交易平台指令数量单位
     * 
     * @param tradeMarket 交易场所
     * @return 大宗交易平台指令数量单位
     */
    public UnitEnum getUnitEnum(String tradeMarket) {
        return numUnit == null ? null : numUnit.get(tradeMarket);
    }

    /**
     * 设置 大宗交易平台指令数量单位
     * 
     * @param numUnit 大宗交易平台指令数量单位
     */
    public void setNumUnit(Map<String, UnitEnum> numUnit) {
        this.numUnit = numUnit;
    }

    /**
     * 获取 最小申报单位
     * 
     * @return 最小申报单位.
     */
    public BigDecimal getMinReportUnit() {
        return minReportUnit;
    }

    /**
     * 设置 最小申报单位
     * 
     * @param minReportUnit 最小申报单位
     */
    public void setMinReportUnit(BigDecimal minReportUnit) {
        this.minReportUnit = minReportUnit;
    }

    /**
     * 获取 大宗交易平台的金额限额
     * 
     * @return 大宗交易平台的金额限额
     */
    public Map<String, BigDecimal[]> getLimitAmt() {
        return limitAmt;
    }

    /**
     * 设置 大宗交易平台的金额限额
     * 
     * @param limitAmt 大宗交易平台的金额限额
     */
    public void setLimitAmt(Map<String, BigDecimal[]> limitAmt) {
        this.limitAmt = limitAmt;
    }
}
