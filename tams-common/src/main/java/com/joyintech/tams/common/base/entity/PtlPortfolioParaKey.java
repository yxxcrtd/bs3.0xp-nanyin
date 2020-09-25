package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class PtlPortfolioParaKey extends BaseEntity {
    /**
     * 组合代码
     */
    private String portfolioId;

    /**
     * 参数项
     */
    private String paramCode;

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
     * 取得 参数项
     * @return 参数项
     */
    public String getParamCode() {
        return paramCode;
    }

    /**
     * 设置 参数项
     * @param paramCode 参数项
     */
    public void setParamCode(String paramCode) {
        this.paramCode = paramCode == null ? null : paramCode.trim();
    }
}