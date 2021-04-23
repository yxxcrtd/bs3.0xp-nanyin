package com.joyintech.background.component.uplink.interbank.database.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.background.component.common.pack.common.json.BigDecimalSerializer;
import com.joyintech.background.component.common.pack.common.json.CustomDateDeserialize;

import java.math.BigDecimal;
import java.util.Date;

public class WflIbuQuoteEntCrepPledge extends WflIbuQuoteEntCrepPledgeKey {
    /**
     * 组合代码
     */
    private String portfolioId;

    /**
     * 质押面值
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal pledgeFaceValue;

    /**
     * 质押数量
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal pledgeNumber;

    /**
     * 质押率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal pledgeRatio;

    /**
     * 质押金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal pledgeAmt;

    /**
     * 单位净价
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal unitCprice;

    /**
     * 工作流代码
     */
    private String workflowId;

    /**
     * 净值日期
     */
    private Date cpriceDate;

    /**
     * 数据状态
     */
    private String workflowStatus;

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
     * 取得 质押面值
     * @return 质押面值
     */
    public BigDecimal getPledgeFaceValue() {
        return pledgeFaceValue;
    }

    /**
     * 设置 质押面值
     * @param pledgeFaceValue 质押面值
     */
    public void setPledgeFaceValue(BigDecimal pledgeFaceValue) {
        this.pledgeFaceValue = pledgeFaceValue;
    }

    /**
     * 取得 质押数量
     * @return 质押数量
     */
    public BigDecimal getPledgeNumber() {
        return pledgeNumber;
    }

    /**
     * 设置 质押数量
     * @param pledgeNumber 质押数量
     */
    public void setPledgeNumber(BigDecimal pledgeNumber) {
        this.pledgeNumber = pledgeNumber;
    }

    /**
     * 取得 质押率
     * @return 质押率
     */
    public BigDecimal getPledgeRatio() {
        return pledgeRatio;
    }

    /**
     * 设置 质押率
     * @param pledgeRatio 质押率
     */
    public void setPledgeRatio(BigDecimal pledgeRatio) {
        this.pledgeRatio = pledgeRatio;
    }

    /**
     * 取得 质押金额
     * @return 质押金额
     */
    public BigDecimal getPledgeAmt() {
        return pledgeAmt;
    }

    /**
     * 设置 质押金额
     * @param pledgeAmt 质押金额
     */
    public void setPledgeAmt(BigDecimal pledgeAmt) {
        this.pledgeAmt = pledgeAmt;
    }

    /**
     * 取得 单位净价
     * @return 单位净价
     */
    public BigDecimal getUnitCprice() {
        return unitCprice;
    }

    /**
     * 设置 单位净价
     * @param unitCprice 单位净价
     */
    public void setUnitCprice(BigDecimal unitCprice) {
        this.unitCprice = unitCprice;
    }

    /**
     * 取得 工作流代码
     * @return 工作流代码
     */
    public String getWorkflowId() {
        return workflowId;
    }

    /**
     * 设置 工作流代码
     * @param workflowId 工作流代码
     */
    public void setWorkflowId(String workflowId) {
        this.workflowId = workflowId == null ? null : workflowId.trim();
    }

    /**
     * 取得 净值日期
     * @return 净值日期
     */
    public Date getCpriceDate() {
        return cpriceDate;
    }

    /**
     * 设置 净值日期
     * @param cpriceDate 净值日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setCpriceDate(Date cpriceDate) {
        this.cpriceDate = cpriceDate;
    }

    /**
     * 取得 数据状态
     * @return 数据状态
     */
    public String getWorkflowStatus() {
        return workflowStatus;
    }

    /**
     * 设置 数据状态
     * @param workflowStatus 数据状态
     */
    public void setWorkflowStatus(String workflowStatus) {
        this.workflowStatus = workflowStatus == null ? null : workflowStatus.trim();
    }
}