package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class StlSecReconDetailKey extends BaseEntity {
    /**
     * 券商对账单流水号
     */
    private String seqNo;

    /**
     * 成交编号
     */
    private String tradeId;

    /**
     * 对账维度
     */
    private String stlSecDeme;

    /**
     * 取得 券商对账单流水号
     * @return 券商对账单流水号
     */
    public String getSeqNo() {
        return seqNo;
    }

    /**
     * 设置 券商对账单流水号
     * @param seqNo 券商对账单流水号
     */
    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo == null ? null : seqNo.trim();
    }

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

    public String getStlSecDeme() {
        return stlSecDeme;
    }

    public void setStlSecDeme(String stlSecDeme) {
        this.stlSecDeme = this.stlSecDeme = stlSecDeme == null ? null : stlSecDeme.trim();
    }
}