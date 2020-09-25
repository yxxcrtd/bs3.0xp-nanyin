package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class RskPortfolioIndicatorKey extends BaseEntity {
    /**
     * 组合代码，包括组合包代码
     */
    private String portfolioId;

    /**
     * 监督指标编号
     */
    private String indId;

    /**
     * 取得 组合代码，包括组合包代码
     * @return 组合代码，包括组合包代码
     */
    public String getPortfolioId() {
        return portfolioId;
    }

    /**
     * 设置 组合代码，包括组合包代码
     * @param portfolioId 组合代码，包括组合包代码
     */
    public void setPortfolioId(String portfolioId) {
        this.portfolioId = portfolioId == null ? null : portfolioId.trim();
    }

    /**
     * 取得 监督指标编号
     * @return 监督指标编号
     */
    public String getIndId() {
        return indId;
    }

    /**
     * 设置 监督指标编号
     * @param indId 监督指标编号
     */
    public void setIndId(String indId) {
        this.indId = indId == null ? null : indId.trim();
    }
}