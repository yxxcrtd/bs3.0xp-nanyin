package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class SettleTransDataStatus extends BaseEntity {
    /**
     * 成交编号
     */
    private String tradeId;

    /**
     * 对账结果
     */
    private String checkResult;

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

    /**
     * 取得 对账结果
     * @return 对账结果
     */
    public String getCheckResult() {
        return checkResult;
    }

    /**
     * 设置 对账结果
     * @param checkResult 对账结果
     */
    public void setCheckResult(String checkResult) {
        this.checkResult = checkResult == null ? null : checkResult.trim();
    }
}