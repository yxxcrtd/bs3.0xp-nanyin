package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class TrdAsynHandleStatusKey extends BaseEntity {
    /**
     * 交易编号
     */
    private String tradeId;

    /**
     * 交易表类型(01：金融产品交易汇总表，02：金融产品交易表)
     */
    private String trdTableType;

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
     * 取得 交易表类型(01：金融产品交易汇总表，02：金融产品交易表)
     * @return 交易表类型(01：金融产品交易汇总表，02：金融产品交易表)
     */
    public String getTrdTableType() {
        return trdTableType;
    }

    /**
     * 设置 交易表类型(01：金融产品交易汇总表，02：金融产品交易表)
     * @param trdTableType 交易表类型(01：金融产品交易汇总表，02：金融产品交易表)
     */
    public void setTrdTableType(String trdTableType) {
        this.trdTableType = trdTableType == null ? null : trdTableType.trim();
    }
}