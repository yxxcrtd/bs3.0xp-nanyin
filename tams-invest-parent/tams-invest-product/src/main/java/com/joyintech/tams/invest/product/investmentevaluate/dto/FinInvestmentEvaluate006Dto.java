package com.joyintech.tams.invest.product.investmentevaluate.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @program: FinInvestmentEvaluate005Dto
 * @description: 策略执行有效性查询
 * @author: jyl
 * @create: 2019-10-30 19:09
 **/
public class FinInvestmentEvaluate006Dto {
    private String portfolioId;//组合代码
    private String portfolioName;//组合名称
    private  Date endDate;//评价日
    private  Date cDate;//评价日(创建日)
    private BigDecimal bonds;//持仓
    private String finprodId;//资产代码
    private String finprodName;//资产名称
    private BigDecimal assetNetVal;//资产净值
    private BigDecimal assetCost;//资产成本
    private BigDecimal ptlAssetNetVal;//组合资产净值
    private BigDecimal ptlAssetCost;//组合资产成本
    private BigDecimal calcAssetContributionRate;//资产收益贡献率

    public String getPortfolioId() {
        return portfolioId;
    }

    public void setPortfolioId(String portfolioId) {
        this.portfolioId = portfolioId;
    }

    public String getPortfolioName() {
        return portfolioName;
    }

    public void setPortfolioName(String portfolioName) {
        this.portfolioName = portfolioName;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public BigDecimal getBonds() {
        return bonds;
    }

    public void setBonds(BigDecimal bonds) {
        this.bonds = bonds;
    }

    public String getFinprodId() {
        return finprodId;
    }

    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
    }

    public String getFinprodName() {
        return finprodName;
    }

    public void setFinprodName(String finprodName) {
        this.finprodName = finprodName;
    }

    public BigDecimal getAssetNetVal() {
        return assetNetVal;
    }

    public void setAssetNetVal(BigDecimal assetNetVal) {
        this.assetNetVal = assetNetVal;
    }

    public BigDecimal getAssetCost() {
        return assetCost;
    }

    public void setAssetCost(BigDecimal assetCost) {
        this.assetCost = assetCost;
    }

    public BigDecimal getPtlAssetNetVal() {
        return ptlAssetNetVal;
    }

    public void setPtlAssetNetVal(BigDecimal ptlAssetNetVal) {
        this.ptlAssetNetVal = ptlAssetNetVal;
    }

    public BigDecimal getPtlAssetCost() {
        return ptlAssetCost;
    }

    public void setPtlAssetCost(BigDecimal ptlAssetCost) {
        this.ptlAssetCost = ptlAssetCost;
    }

    public BigDecimal getCalcAssetContributionRate() {
        return calcAssetContributionRate;
    }

    public void setCalcAssetContributionRate(BigDecimal calcAssetContributionRate) {
        this.calcAssetContributionRate = calcAssetContributionRate;
    }

    public Date getcDate() {
        return cDate;
    }

    public void setcDate(Date cDate) {
        this.cDate = cDate;
    }
}

