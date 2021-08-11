package com.joyintech.tams.model.impairment.batch.eadcalculation.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 资产逾期交易DTO
 */
public class OverDueTradeAssetDto {

    /**
     * 投资组合代码（有子组合是子组合代码，没有子组合是母组合代码）
     */
    private String portfolioId;

    /**
     * 资产代码
     */
    private String assetId;

    /**
     * 计划截止日
     */
    private Date planEndDate;
    /**
     * 模型资产类型
     */
    private String modelAssetType;

    /**
     * 已收金额
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal amtReceived;

    /**
     * 应收金额
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal accountAmt;
    /**
     * 应收未收本息和
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal princiAndInterAmt;
    /**
     * 现金流类型
     */
    private String cashType;

    public String getPortfolioId() {
        return portfolioId;
    }

    public void setPortfolioId(String portfolioId) {
        this.portfolioId = portfolioId;
    }

    public String getAssetId() {
        return assetId;
    }

    public void setAssetId(String assetId) {
        this.assetId = assetId;
    }

    public Date getPlanEndDate() {
        return planEndDate;
    }

    public void setPlanEndDate(Date planEndDate) {
        this.planEndDate = planEndDate;
    }

    public BigDecimal getAmtReceived() {
        return amtReceived;
    }

    public void setAmtReceived(BigDecimal amtReceived) {
        this.amtReceived = amtReceived;
    }

    public BigDecimal getAccountAmt() {
        return accountAmt;
    }

    public void setAccountAmt(BigDecimal accountAmt) {
        this.accountAmt = accountAmt;
    }

    public BigDecimal getPrinciAndInterAmt() {
        return princiAndInterAmt;
    }

    public void setPrinciAndInterAmt(BigDecimal princiAndInterAmt) {
        this.princiAndInterAmt = princiAndInterAmt;
    }

    public String getCashType() {
        return cashType;
    }

    public void setCashType(String cashType) {
        this.cashType = cashType;
    }

    public String getModelAssetType() {
        return modelAssetType;
    }

    public void setModelAssetType(String modelAssetType) {
        this.modelAssetType = modelAssetType;
    }
}
