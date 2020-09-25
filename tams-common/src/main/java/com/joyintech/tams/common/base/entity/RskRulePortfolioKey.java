package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class RskRulePortfolioKey extends BaseEntity {
    /**
     * 组合包代码
     */
    private String ruleId;

    /**
     * 组合代码
     */
    private String portfolioId;

    /**
     * 取得 组合包代码
     * @return 组合包代码
     */
    public String getRuleId() {
        return ruleId;
    }

    /**
     * 设置 组合包代码
     * @param ruleId 组合包代码
     */
    public void setRuleId(String ruleId) {
        this.ruleId = ruleId == null ? null : ruleId.trim();
    }

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
}