package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class WflRegTradeFeeTotalKey extends BaseEntity {
    /**
     * 汇总登记编号
     */
    private String tradeId;

    /**
     * 费用类型，结算服务费、交易手续费
     */
    private String feeType;

    /**
     * 取得 汇总登记编号
     * @return 汇总登记编号
     */
    public String getTradeId() {
        return tradeId;
    }

    /**
     * 设置 汇总登记编号
     * @param tradeId 汇总登记编号
     */
    public void setTradeId(String tradeId) {
        this.tradeId = tradeId == null ? null : tradeId.trim();
    }

    /**
     * 取得 费用类型，结算服务费、交易手续费
     * @return 费用类型，结算服务费、交易手续费
     */
    public String getFeeType() {
        return feeType;
    }

    /**
     * 设置 费用类型，结算服务费、交易手续费
     * @param feeType 费用类型，结算服务费、交易手续费
     */
    public void setFeeType(String feeType) {
        this.feeType = feeType == null ? null : feeType.trim();
    }
}