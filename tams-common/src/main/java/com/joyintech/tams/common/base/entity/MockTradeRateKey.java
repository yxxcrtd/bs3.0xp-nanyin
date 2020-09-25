package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class MockTradeRateKey extends BaseEntity {
    /**
     * 模拟交易编号
     */
    private String tradeId;

    /**
     * 收益类型
     */
    private String rateType;

    /**
     * 利率上下区分
     */
    private String rateMnclass;

    /**
     * 取得 模拟交易编号
     * @return 模拟交易编号
     */
    public String getTradeId() {
        return tradeId;
    }

    /**
     * 设置 模拟交易编号
     * @param tradeId 模拟交易编号
     */
    public void setTradeId(String tradeId) {
        this.tradeId = tradeId == null ? null : tradeId.trim();
    }

    /**
     * 取得 收益类型
     * @return 收益类型
     */
    public String getRateType() {
        return rateType;
    }

    /**
     * 设置 收益类型
     * @param rateType 收益类型
     */
    public void setRateType(String rateType) {
        this.rateType = rateType == null ? null : rateType.trim();
    }

    /**
     * 取得 利率上下区分
     * @return 利率上下区分
     */
    public String getRateMnclass() {
        return rateMnclass;
    }

    /**
     * 设置 利率上下区分
     * @param rateMnclass 利率上下区分
     */
    public void setRateMnclass(String rateMnclass) {
        this.rateMnclass = rateMnclass == null ? null : rateMnclass.trim();
    }
}