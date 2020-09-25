package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import java.math.BigDecimal;

public class PtlPortfolioDis extends BaseEntity {
    /**
     * 组合代码
     */
    private String portfolioId;

    /**
     * 分配比率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal disPercent;

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
     * 取得 分配比率
     * @return 分配比率
     */
    public BigDecimal getDisPercent() {
        return disPercent;
    }

    /**
     * 设置 分配比率
     * @param disPercent 分配比率
     */
    public void setDisPercent(BigDecimal disPercent) {
        this.disPercent = disPercent;
    }
}