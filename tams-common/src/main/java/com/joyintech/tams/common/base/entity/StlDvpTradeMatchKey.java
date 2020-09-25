package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class StlDvpTradeMatchKey extends BaseEntity {
    /**
     * 指令编号
     */
    private String orderId;

    /**
     * 交易编号
     */
    private String tradeId;

    /**
     * 取得 指令编号
     * @return 指令编号
     */
    public String getOrderId() {
        return orderId;
    }

    /**
     * 设置 指令编号
     * @param orderId 指令编号
     */
    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    /**
     * 取得 交易编号
     * @return 交易编号
     */
    public String getTradeId() {
        return tradeId;
    }

    /**
     * 设置 交易编号
     * @param tradeId 交易编号
     */
    public void setTradeId(String tradeId) {
        this.tradeId = tradeId == null ? null : tradeId.trim();
    }
}