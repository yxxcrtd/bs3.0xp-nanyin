package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class PaBranchSaleDetailKey extends BaseEntity {
    /**
     * 交易编号
     */
    private String tradeId;

    /**
     * 分行
     */
    private String branch;

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
     * 取得 分行
     * @return 分行
     */
    public String getBranch() {
        return branch;
    }

    /**
     * 设置 分行
     * @param branch 分行
     */
    public void setBranch(String branch) {
        this.branch = branch == null ? null : branch.trim();
    }
}