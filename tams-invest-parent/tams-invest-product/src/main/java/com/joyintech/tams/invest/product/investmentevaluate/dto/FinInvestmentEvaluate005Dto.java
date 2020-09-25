package com.joyintech.tams.invest.product.investmentevaluate.dto;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @program: FinInvestmentEvaluate005Dto
 * @description: 策略执行有效性查询
 * @author: jyl
 * @create: 2019-10-30 19:09
 **/
public class FinInvestmentEvaluate005Dto {
    private String portfolioId;//组合代码
    private String portfolioName;//组合名称
    private Date effDate;//评价日
    private String batchId;//策略明细编号
    private BigDecimal timePointEff;//时点策略执行有效性
    private BigDecimal avgAmtRatio;//平均策略执行有效性

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

    public Date getEffDate() {
        return effDate;
    }

    public void setEffDate(Date effDate) {
        this.effDate = effDate;
    }

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    public BigDecimal getTimePointEff() {
        return timePointEff;
    }

    public void setTimePointEff(BigDecimal timePointEff) {
        this.timePointEff = timePointEff;
    }

    public BigDecimal getAvgAmtRatio() {
        return avgAmtRatio;
    }

    public void setAvgAmtRatio(BigDecimal avgAmtRatio) {
        this.avgAmtRatio = avgAmtRatio;
    }
}

