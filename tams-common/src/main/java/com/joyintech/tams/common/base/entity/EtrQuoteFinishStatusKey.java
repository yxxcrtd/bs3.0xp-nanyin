package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class EtrQuoteFinishStatusKey extends BaseEntity {
    /**
     * 委托代码
     */
    private String entrustId;

    /**
     * 报价编号
     */
    private String quoteId;

    /**
     * 取得 委托代码
     * @return 委托代码
     */
    public String getEntrustId() {
        return entrustId;
    }

    /**
     * 设置 委托代码
     * @param entrustId 委托代码
     */
    public void setEntrustId(String entrustId) {
        this.entrustId = entrustId == null ? null : entrustId.trim();
    }

    /**
     * 取得 报价编号
     * @return 报价编号
     */
    public String getQuoteId() {
        return quoteId;
    }

    /**
     * 设置 报价编号
     * @param quoteId 报价编号
     */
    public void setQuoteId(String quoteId) {
        this.quoteId = quoteId == null ? null : quoteId.trim();
    }
}