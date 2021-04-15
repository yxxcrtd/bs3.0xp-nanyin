package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class TrdTempTradeFeeDetailKey extends BaseEntity {
    /**
     * 交易编号
     */
    private String tradeId;

    /**
     * 费用类型，结算费、交易手续费、通道费、资产推荐费、增值税、城建税、教育附加税等
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