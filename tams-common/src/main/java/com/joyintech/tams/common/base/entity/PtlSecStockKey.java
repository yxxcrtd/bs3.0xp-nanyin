package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class PtlSecStockKey extends BaseEntity {
    /**
     * 组合代码
     */
    private String portfolioId;

    /**
     * 质押库代码
     */
    private String pledgeStockId;

    /**
     * 取得 组合代码
     * @return 组合代码
     */
    public String getPortfolioId() {
        return portfolioId;
    }

    /**
     * 设置 组合代码
     * @param portfolioId 组合代码
     */
    public void setPortfolioId(String portfolioId) {
        this.portfolioId = portfolioId == null ? null : portfolioId.trim();
    }

    /**
     * 取得 质押库代码
     * @return 质押库代码
     */
    public String getPledgeStockId() {
        return pledgeStockId;
    }

    /**
     * 设置 质押库代码
     * @param pledgeStockId 质押库代码
     */
    public void setPledgeStockId(String pledgeStockId) {
        this.pledgeStockId = pledgeStockId == null ? null : pledgeStockId.trim();
    }
}