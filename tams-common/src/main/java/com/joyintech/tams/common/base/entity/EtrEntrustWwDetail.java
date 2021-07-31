package com.joyintech.tams.common.base.entity;

import java.math.BigDecimal;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;

public class EtrEntrustWwDetail extends EtrEntrustWwDetailKey {
    /**
     * 组合代码
     */
    private String portfolioId;

    /**
     * 持仓份额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal entrustAmt;

    /**
     * 组合分配金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal portfolioAmt;

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
     * 取得 持仓份额
     * @return 持仓份额
     */
    public BigDecimal getEntrustAmt() {
        return entrustAmt;
    }

    /**
     * 设置 持仓份额
     * @param entrustAmt 持仓份额
     */
    public void setEntrustAmt(BigDecimal entrustAmt) {
        this.entrustAmt = entrustAmt;
    }

    /**
     * 取得 组合分配金额
     * @return 组合分配金额
     */
    public BigDecimal getPortfolioAmt() {
        return portfolioAmt;
    }

    /**
     * 设置 组合分配金额
     * @param portfolioAmt 组合分配金额
     */
    public void setPortfolioAmt(BigDecimal portfolioAmt) {
        this.portfolioAmt = portfolioAmt;
    }
}