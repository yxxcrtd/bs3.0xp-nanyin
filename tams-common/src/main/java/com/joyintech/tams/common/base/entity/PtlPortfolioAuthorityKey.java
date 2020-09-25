package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class PtlPortfolioAuthorityKey extends BaseEntity {
    /**
     * 组合代码
     */
    private String portfolioId;

    /**
     * 用户代码
     */
    private String userId;

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
     * 取得 用户代码
     * @return 用户代码
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置 用户代码
     * @param userId 用户代码
     */
    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }
}