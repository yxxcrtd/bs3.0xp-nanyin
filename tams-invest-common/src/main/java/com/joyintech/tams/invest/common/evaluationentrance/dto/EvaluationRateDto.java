package com.joyintech.tams.invest.common.evaluationentrance.dto;

import java.math.BigDecimal;
import java.util.Date;

public class EvaluationRateDto {

    public EvaluationRateDto(){}

    public EvaluationRateDto(Date startDate, Date endDate, BigDecimal rate) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.rate = rate;
    }

    public EvaluationRateDto(Date startDate, Date endDate, BigDecimal rate, BigDecimal rate2) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.rate = rate;
        this.rate2 = rate2;
    }

    /**
     * 开始日期
     */
    private Date startDate;

    /**
     * 结束日期
     */
    private Date endDate;

    /**
     * 利率
     */
    private BigDecimal rate;

    private BigDecimal rate2;
    /**
     * 组合代码
     */
    private String combinedCode;
    /**
     * 子组合代码
     */
    private String subcombinedCode;

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public BigDecimal getRate2() {
        return rate2;
    }

    public void setRate2(BigDecimal rate2) {
        this.rate2 = rate2;
    }

    public String getCombinedCode() {
        return combinedCode;
    }

    public void setCombinedCode(String combinedCode) {
        this.combinedCode = combinedCode;
    }

    public String getSubcombinedCode() {
        return subcombinedCode;
    }

    public void setSubcombinedCode(String subcombinedCode) {
        this.subcombinedCode = subcombinedCode;
    }
}
