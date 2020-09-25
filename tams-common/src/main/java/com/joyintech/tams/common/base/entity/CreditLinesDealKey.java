package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class CreditLinesDealKey extends BaseEntity {
    /**
     * 授信额度代码
     */
    private String seqno;

    /**
     * 交易编号/汇总交易编号/预占用编号
     */
    private String tradeId;

    /**
     * 取得 授信额度代码
     * @return 授信额度代码
     */
    public String getSeqno() {
        return seqno;
    }

    /**
     * 设置 授信额度代码
     * @param seqno 授信额度代码
     */
    public void setSeqno(String seqno) {
        this.seqno = seqno == null ? null : seqno.trim();
    }

    /**
     * 取得 交易编号/汇总交易编号/预占用编号
     * @return 交易编号/汇总交易编号/预占用编号
     */
    public String getTradeId() {
        return tradeId;
    }

    /**
     * 设置 交易编号/汇总交易编号/预占用编号
     * @param tradeId 交易编号/汇总交易编号/预占用编号
     */
    public void setTradeId(String tradeId) {
        this.tradeId = tradeId == null ? null : tradeId.trim();
    }
}