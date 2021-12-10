package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class RegFinPledgeKey extends BaseEntity {
    /**
     * 登记编号
     */
    private String tradeId;

    /**
     * 指令代码
     */
    private String entrustId;

    /**
     * 质押金融产品代码
     */
    private String pledgeFinprodId;

    /**
     * 取得 登记编号
     * @return 登记编号
     */
    public String getTradeId() {
        return tradeId;
    }

    /**
     * 设置 登记编号
     * @param tradeId 登记编号
     */
    public void setTradeId(String tradeId) {
        this.tradeId = tradeId == null ? null : tradeId.trim();
    }

    /**
     * 取得 指令代码
     * @return 指令代码
     */
    public String getEntrustId() {
        return entrustId;
    }

    /**
     * 设置 指令代码
     * @param entrustId 指令代码
     */
    public void setEntrustId(String entrustId) {
        this.entrustId = entrustId == null ? null : entrustId.trim();
    }

    /**
     * 取得 质押金融产品代码
     * @return 质押金融产品代码
     */
    public String getPledgeFinprodId() {
        return pledgeFinprodId;
    }

    /**
     * 设置 质押金融产品代码
     * @param pledgeFinprodId 质押金融产品代码
     */
    public void setPledgeFinprodId(String pledgeFinprodId) {
        this.pledgeFinprodId = pledgeFinprodId == null ? null : pledgeFinprodId.trim();
    }
}