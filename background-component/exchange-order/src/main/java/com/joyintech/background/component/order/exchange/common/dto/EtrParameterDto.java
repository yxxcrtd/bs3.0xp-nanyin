/*
 * 文件名：EtrParameterDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2020年7月9日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.background.component.order.exchange.common.dto;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.joyintech.background.component.common.pack.common.security.User;
import com.joyintech.background.component.order.exchange.common.constant.UnitEnum;
import com.joyintech.background.component.order.exchange.common.util.EtrComponentUtil;

/**
 * 投资指令组件参数项Dto
 * 
 * @author zhangrui1
 * @version 1.0 2020年7月9日
 * @see EtrParameterDto
 * @since 1.0
 */
public class EtrParameterDto {

    /**
     * 用户信息
     */
    private User user;

    /**
     * 配置信息
     */
    private EtrSettingsDto settings;

    public EtrParameterDto() {
        this.settings = new EtrSettingsDto();
    }

    /**
     * 获取 用户信息
     * 
     * @return 用户信息.
     */
    public User getUser() {
        return user;
    }

    /**
     * 设置 用户信息
     * 
     * @param user 用户信息
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * 获取 配置信息
     * 
     * @return 配置信息.
     */
    public EtrSettingsDto getSettings() {
        return settings;
    }

    /**
     * 设置 配置信息
     * 
     * @param settings 配置信息
     */
    public void setSettings(EtrSettingsDto settings) {
        this.settings = settings;
    }

    /**
     * 添加大宗交易平台指令数量单位
     * 
     * @param tradeMarket 交易场所
     * @param unit 单位 {@link UnitEnum}
     */
    public void putUnitEnum(String tradeMarket, UnitEnum unit) {
        Map<String, UnitEnum> numUnit = this.settings.getNumUnit();
        if (numUnit == null) {
            numUnit = new HashMap<>();
        }
        numUnit.put(tradeMarket, unit);
        this.settings.setNumUnit(numUnit);
    }

    /**
     * 获取 最小买卖单位
     * 
     * @return 最小买卖单位.
     */
    public BigDecimal getMinDealUnit() {
        return this.settings.getMinDealUnit();
    }

    /**
     * 设置 最小买卖单位
     * 
     * @param minDealUnit 最小买卖单位
     */
    public void setMinDealUnit(BigDecimal minDealUnit) {
        this.settings.setMinDealUnit(minDealUnit);
    }

    /**
     * 获取 最小差价
     * 
     * @return 最小差价.
     */
    public BigDecimal getMinDiffPrice() {
        return this.settings.getMinDiffPrice();
    }

    /**
     * 设置 最小差价
     * 
     * @param minDiffPrice 最小差价
     */
    public void setMinDiffPrice(BigDecimal minDiffPrice) {
        this.settings.setMinDiffPrice(minDiffPrice);
    }

    /**
     * 获取 设置 价格上限（涨停价格）
     * 
     * @return 设置 价格上限（涨停价格）.
     */
    public BigDecimal getLimitUpAmt() {
        return this.settings.getLimitUpAmt();
    }

    /**
     * 设置 价格上限（涨停价格）
     * 
     * @param limitUpAmt 设置 价格上限（涨停价格）
     */
    public void setLimitUpAmt(BigDecimal limitUpAmt) {
        this.settings.setLimitUpAmt(limitUpAmt);
    }

    /**
     * 获取 价格下限（跌停价格）
     * 
     * @return 价格下限（跌停价格）.
     */
    public BigDecimal getLimitDnAmt() {
        return this.settings.getLimitDnAmt();
    }

    /**
     * 设置 价格下限（跌停价格）
     * 
     * @param limitDnAmt 价格下限（跌停价格）
     */
    public void setLimitDnAmt(BigDecimal limitDnAmt) {
        this.settings.setLimitDnAmt(limitDnAmt);
    }

    /**
     * 获取 大宗交易平台数量限额
     * 
     * @return 大宗交易平台数量限额.
     */
    public Map<String, BigDecimal[]> getLimitNum() {
        return this.settings.getLimitNum();
    }

    /**
     * 设置 大宗交易平台数量限额
     * 
     * @param limitNum 大宗交易平台数量限额
     */
    public void setLimitNum(Map<String, BigDecimal[]> limitNum) {
        this.settings.setLimitNum(limitNum);
    }

    /**
     * 取出 指令数量限额信息
     * 
     * @param tradePlat 交易场所
     * @return 指令数量限额信息
     */
    public BigDecimal[] limitNum(String tradePlat) {
        Map<String, BigDecimal[]> limitNum = getLimitNum();
        if (limitNum == null) {
            return new BigDecimal[2];
        }
        return limitNum.getOrDefault(tradePlat, new BigDecimal[2]);
    }

    /**
     * 设置大宗交易平台的指令数量限额
     * 
     * @param tradePlat 交易场所
     * @param limitDn 下限，设置为null则表示没有下限要求
     * @param limitUp 上限，设置为null则表示没有上限要求
     */
    public void putLimitNum(String tradePlat, BigDecimal limitDn, BigDecimal limitUp) {
        setLimitNum(
            EtrComponentUtil.putIfAbsent(getLimitNum(), tradePlat, new BigDecimal[2], arr -> {
            arr[0] = limitDn;
            arr[1] = limitUp;
            }));
    }

    /**
     * 获取 大宗交易平台指令数量单位
     * 
     * @return 大宗交易平台指令数量单位.
     */
    public Map<String, UnitEnum> getNumUnit() {
        return this.settings.getNumUnit();
    }

    /**
     * 取得大宗交易平台指令数量单位
     * 
     * @param tradeMarket 交易场所
     * @return 大宗交易平台指令数量单位
     */
    public UnitEnum getUnitEnum(String tradeMarket) {
        return this.settings.getNumUnit() == null ? null : this.settings.getNumUnit().get(
            tradeMarket);
    }

    /**
     * 设置 大宗交易平台指令数量单位
     * 
     * @param numUnit 大宗交易平台指令数量单位
     */
    public void setNumUnit(Map<String, UnitEnum> numUnit) {
        this.settings.setNumUnit(numUnit);
    }

    /**
     * 获取 最小申报单位
     * 
     * @return 最小申报单位.
     */
    public BigDecimal getMinReportUnit() {
        return this.settings.getMinReportUnit();
    }

    /**
     * 设置 最小申报单位
     * 
     * @param minReportUnit 最小申报单位
     */
    public void setMinReportUnit(BigDecimal minReportUnit) {
        this.settings.setMinReportUnit(minReportUnit);
    }

    /**
     * 取出 指令数量限额信息
     * 
     * @param tradePlat 交易场所
     * @return 指令数量限额信息
     */
    public BigDecimal[] limitAmt(String tradePlat) {
        if (this.settings.getLimitAmt() == null) {
            return new BigDecimal[2];
        }
        return this.settings.getLimitAmt().getOrDefault(tradePlat, new BigDecimal[2]);
    }

    /**
     * 设置大宗交易平台的指令金额限额
     * 
     * @param tradePlat 交易场所
     * @param limitDn 下限，设置为null则表示没有下限要求
     * @param limitUp 上限，设置为null则表示没有上限要求
     */
    public void putLimitAmt(String tradePlat, BigDecimal limitDn, BigDecimal limitUp) {
        this.settings.setLimitAmt(EtrComponentUtil.putIfAbsent(this.settings.getLimitAmt(),
            tradePlat, new BigDecimal[2], arr -> {
            arr[0] = limitDn;
            arr[1] = limitUp;
            }));
    }

    /**
     * 设置 数量限额和金额限额
     * 
     * @param tradeMarket 交易场所
     * @param limitDnNum 数量下限(实际值不含单位)
     * @param limitUpNum 数量上限(实际值不含单位)
     * @param unit 数量单位（用于提示信息）
     * @param limitDnAmt 金额下限
     * @param limitUpAmt 金额上限
     */
    public void putLimitAmtNum(String tradeMarket, BigDecimal limitDnNum, BigDecimal limitUpNum,
                               UnitEnum unit, BigDecimal limitDnAmt, BigDecimal limitUpAmt) {
        putLimitNum(tradeMarket, limitDnNum, limitUpNum);
        putUnitEnum(tradeMarket, unit);
        putLimitAmt(tradeMarket, limitDnAmt, limitUpAmt);
    }
}
