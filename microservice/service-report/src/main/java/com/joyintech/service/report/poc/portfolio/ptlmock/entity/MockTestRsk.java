package com.joyintech.service.report.poc.portfolio.ptlmock.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class MockTestRsk extends MockTestRskKey {
    /**
     * 指标名称
     */
    private String indName;

    /**
     * 日期
     */
    private Date riskDate;

    /**
     * 预警阈值
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal wInvValue;

    /**
     * 审批阈值
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal aInvValue;

    /**
     * 违规阈值
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal eInvValue;

    /**
     * 当前实际值
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal currValue;

    /**
     * 取得 指标名称
     * @return 指标名称
     */
    public String getIndName() {
        return indName;
    }

    /**
     * 设置 指标名称
     * @param indName 指标名称
     */
    public void setIndName(String indName) {
        this.indName = indName == null ? null : indName.trim();
    }

    /**
     * 取得 日期
     * @return 日期
     */
    public Date getRiskDate() {
        return riskDate;
    }

    /**
     * 设置 日期
     * @param riskDate 日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setRiskDate(Date riskDate) {
        this.riskDate = riskDate;
    }

    /**
     * 取得 预警阈值
     * @return 预警阈值
     */
    public BigDecimal getwInvValue() {
        return wInvValue;
    }

    /**
     * 设置 预警阈值
     * @param wInvValue 预警阈值
     */
    public void setwInvValue(BigDecimal wInvValue) {
        this.wInvValue = wInvValue;
    }

    /**
     * 取得 审批阈值
     * @return 审批阈值
     */
    public BigDecimal getaInvValue() {
        return aInvValue;
    }

    /**
     * 设置 审批阈值
     * @param aInvValue 审批阈值
     */
    public void setaInvValue(BigDecimal aInvValue) {
        this.aInvValue = aInvValue;
    }

    /**
     * 取得 违规阈值
     * @return 违规阈值
     */
    public BigDecimal geteInvValue() {
        return eInvValue;
    }

    /**
     * 设置 违规阈值
     * @param eInvValue 违规阈值
     */
    public void seteInvValue(BigDecimal eInvValue) {
        this.eInvValue = eInvValue;
    }

    /**
     * 取得 当前实际值
     * @return 当前实际值
     */
    public BigDecimal getCurrValue() {
        return currValue;
    }

    /**
     * 设置 当前实际值
     * @param currValue 当前实际值
     */
    public void setCurrValue(BigDecimal currValue) {
        this.currValue = currValue;
    }
}