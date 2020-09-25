package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class TrdTradeFeeTotalKey extends BaseEntity {
    /**
     * 汇总交易编号
     */
    private String totleTradeId;

    /**
     * 费用类型，结算费、交易手续费、通道费、资产推荐费、增值税、城建税、教育附加税等
     */
    private String feeType;

    /**
     * 取得 汇总交易编号
     * @return 汇总交易编号
     */
    public String getTotleTradeId() {
        return totleTradeId;
    }

    /**
     * 设置 汇总交易编号
     * @param totleTradeId 汇总交易编号
     */
    public void setTotleTradeId(String totleTradeId) {
        this.totleTradeId = totleTradeId == null ? null : totleTradeId.trim();
    }

    /**
     * 取得 费用类型，结算费、交易手续费、通道费、资产推荐费、增值税、城建税、教育附加税等
     * @return 费用类型，结算费、交易手续费、通道费、资产推荐费、增值税、城建税、教育附加税等
     */
    public String getFeeType() {
        return feeType;
    }

    /**
     * 设置 费用类型，结算费、交易手续费、通道费、资产推荐费、增值税、城建税、教育附加税等
     * @param feeType 费用类型，结算费、交易手续费、通道费、资产推荐费、增值税、城建税、教育附加税等
     */
    public void setFeeType(String feeType) {
        this.feeType = feeType == null ? null : feeType.trim();
    }
}