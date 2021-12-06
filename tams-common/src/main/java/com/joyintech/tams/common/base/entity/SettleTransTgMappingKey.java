package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class SettleTransTgMappingKey extends BaseEntity {
    /**
     * 成交编号
     */
    private String tradeId;

    /**
     * 投管交易编号
     */
    private String tradeIdTg;

    /**
     * 取得 成交编号
     * @return 成交编号
     */
    public String getTradeId() {
        return tradeId;
    }

    /**
     * 设置 成交编号
     * @param tradeId 成交编号
     */
    public void setTradeId(String tradeId) {
        this.tradeId = tradeId == null ? null : tradeId.trim();
    }

    /**
     * 取得 投管交易编号
     * @return 投管交易编号
     */
    public String getTradeIdTg() {
        return tradeIdTg;
    }

    /**
     * 设置 投管交易编号
     * @param tradeIdTg 投管交易编号
     */
    public void setTradeIdTg(String tradeIdTg) {
        this.tradeIdTg = tradeIdTg == null ? null : tradeIdTg.trim();
    }
}