package com.joyintech.tams.invest.product.investmentevaluate.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @program: FinInvestmentEvaluate007Dto
 * @description: 收益指标目标
 * @author: jyl
 * @create: 2019-11-11 15:41
 **/
public class FinInvestmentEvaluate007Dto {
    private String indexId;//指标代码
    private String incomeType;//收益目标类型
    private BigDecimal incomeTarget;//收益目标
    private BigDecimal maxIncomeTarget;//最高收益目标
    private BigDecimal minIncomeTarget;//最低收益目标
    private Date effDate;//生效日
    private String portfolioId;//组合代码
    private String portfolioName;//组合名称
    private String evalIndex;//评价指标

    public String getIndexId() {
        return indexId;
    }

    public void setIndexId(String indexId) {
        this.indexId = indexId;
    }

    public String getIncomeType() {
        return incomeType;
    }

    public void setIncomeType(String incomeType) {
        this.incomeType = incomeType;
    }

    public BigDecimal getIncomeTarget() {
        return incomeTarget==null?BigDecimal.ZERO:incomeTarget;
    }

    public void setIncomeTarget(BigDecimal incomeTarget) {
        this.incomeTarget = incomeTarget;
    }

    public BigDecimal getMaxIncomeTarget() {
        return maxIncomeTarget==null?BigDecimal.ZERO:maxIncomeTarget;
    }

    public void setMaxIncomeTarget(BigDecimal maxIncomeTarget) {
        this.maxIncomeTarget = maxIncomeTarget;
    }

    public BigDecimal getMinIncomeTarget() {
        return minIncomeTarget==null?BigDecimal.ZERO:minIncomeTarget;
    }

    public void setMinIncomeTarget(BigDecimal minIncomeTarget) {
        this.minIncomeTarget = minIncomeTarget;
    }

    public Date getEffDate() {
        return effDate;
    }

    public void setEffDate(Date effDate) {
        this.effDate = effDate;
    }

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

    public String getEvalIndex() {
        return evalIndex;
    }

    public void setEvalIndex(String evalIndex) {
        this.evalIndex = evalIndex;
    }
}
