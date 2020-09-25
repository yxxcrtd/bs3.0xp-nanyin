package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class LqdPipelineMatchKey extends BaseEntity {
    /**
     * 托管流水号
     */
    private String seqNo;

    /**
     * 交易编号
     */
    private String tradeId;

    /**
     * 取得 托管流水号
     * @return 托管流水号
     */
    public String getSeqNo() {
        return seqNo;
    }

    /**
     * 设置 托管流水号
     * @param seqNo 托管流水号
     */
    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo == null ? null : seqNo.trim();
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