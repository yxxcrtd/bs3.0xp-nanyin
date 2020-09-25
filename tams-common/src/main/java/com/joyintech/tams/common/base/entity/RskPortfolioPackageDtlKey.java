package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class RskPortfolioPackageDtlKey extends BaseEntity {
    /**
     * 组合包代码
     */
    private String ptlBagId;

    /**
     * 组合代码
     */
    private String portfolioId;

    /**
     * 取得 组合包代码
     * @return 组合包代码
     */
    public String getPtlBagId() {
        return ptlBagId;
    }

    /**
     * 设置 组合包代码
     * @param ptlBagId 组合包代码
     */
    public void setPtlBagId(String ptlBagId) {
        this.ptlBagId = ptlBagId == null ? null : ptlBagId.trim();
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