package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class TgTradeFeeDetailKey extends BaseEntity {
    /**
     * 交易编号
     */
    private String tradeId;

    /**
     * 一次性费用类型
     */
    private String feeType;

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

    /**
     * 取得 一次性费用类型
     * @return 一次性费用类型
     */
    public String getFeeType() {
        return feeType;
    }

    /**
     * 设置 一次性费用类型
     * @param feeType 一次性费用类型
     */
    public void setFeeType(String feeType) {
        this.feeType = feeType == null ? null : feeType.trim();
    }
}