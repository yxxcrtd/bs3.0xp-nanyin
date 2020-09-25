/*
 * 文件名：TrdInvestPersonInfo002Dto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：杨智
 * 修改时间：2018年09月29日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.service.trade.bond.dto;

public class TrdInvestPersonInfoDto {
    /**
     * 本方交易员
     */
    private String trader;
    /**
     * 交易员所属部门
     */
    private String traderDept;
    /**
     * 交易对手
     */
    private String counterId;
    /**
     * 对方交易员
     */
    private String counterTrader;
    /**
     * 联系方式
     */
    private String contactInfo;
    /**
     * 是否外部交易
     */
    private String isOutTrade;

    /**
     * 获取 trader
     *
     * @return trader
     */
    public String getTrader() {
        return trader;
    }

    /**
     * 设置 trader
     *
     * @param trader trader
     */
    public void setTrader(String trader) {
        this.trader = trader;
    }

    /**
     * 获取 traderDept
     *
     * @return traderDept
     */
    public String getTraderDept() {
        return traderDept;
    }

    /**
     * 设置 traderDept
     *
     * @param traderDept traderDept
     */
    public void setTraderDept(String traderDept) {
        this.traderDept = traderDept;
    }

    /**
     * 获取 counterId
     *
     * @return counterId
     */
    public String getCounterId() {
        return counterId;
    }

    /**
     * 设置 counterId
     *
     * @param counterId counterId
     */
    public void setCounterId(String counterId) {
        this.counterId = counterId;
    }

    /**
     * 获取 counterTrader
     *
     * @return counterTrader
     */
    public String getCounterTrader() {
        return counterTrader;
    }

    /**
     * 设置 counterTrader
     *
     * @param counterTrader counterTrader
     */
    public void setCounterTrader(String counterTrader) {
        this.counterTrader = counterTrader;
    }

    /**
     * 获取 contactInfo
     *
     * @return contactInfo
     */
    public String getContactInfo() {
        return contactInfo;
    }

    /**
     * 设置 contactInfo
     *
     * @param contactInfo contactInfo
     */
    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    /**
     * 获取 isOutTrade
     *
     * @return isOutTrade
     */
    public String getIsOutTrade() {
        return isOutTrade;
    }

    /**
     * 设置 isOutTrade
     *
     * @param isOutTrade isOutTrade
     */
    public void setIsOutTrade(String isOutTrade) {
        this.isOutTrade = isOutTrade;
    }
}
