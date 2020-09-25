package com.joyintech.service.product.investmentevaluate.dto;

import java.math.BigDecimal;

/**
 * @program: FinInvestmentEvaluate008Dto
 * @description: 基准明细
 * @author: jyl
 * @create: 2019-11-11 15:54
 **/
public class FinInvestmentEvaluate008Dto {
    private String indexId;//指标代码
    private BigDecimal intRate;//利率
    private BigDecimal coefficient;//系数
    private BigDecimal spreadRate;//利差

    public String getIndexId() {
        return indexId;
    }

    public void setIndexId(String indexId) {
        this.indexId = indexId;
    }

    public BigDecimal getIntRate() {
        return intRate;
    }

    public void setIntRate(BigDecimal intRate) {
        this.intRate = intRate;
    }

    public BigDecimal getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(BigDecimal coefficient) {
        this.coefficient = coefficient;
    }

    public BigDecimal getSpreadRate() {
        return spreadRate;
    }

    public void setSpreadRate(BigDecimal spreadRate) {
        this.spreadRate = spreadRate;
    }
}
