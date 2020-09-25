package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;
import java.math.BigDecimal;
import java.util.Date;

public class ChbAstBtmDealSub extends ChbAstBtmDealSubKey {
    /**
     * 发行机构代码
     */
    private String issuePartyId;

    /**
     * 计划行内资产/负债编码
     */
    private String planAssetCode;

    /**
     * 人民币发行金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal issueAmt;

    /**
     * 总发行数量
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal totalNum;

    /**
     * 未投资头寸
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal uninvestCash;

    /**
     * 行内资产/负债编码
     */
    private String assetCode;

    /**
     * 持仓数量
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal posNum;

    /**
     * 折算市值
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal marketValue;

    /**
     * 持仓日期
     */
    private Date posDate;

    /**
     * 工作流代码
     */
    private String workflowId;

    /**
     * 报送状态
     */
    private String sendStatus;

    /**
     * 数据日期
     */
    private Date statusDate;

    /**
     * 取得 发行机构代码
     * @return 发行机构代码
     */
    public String getIssuePartyId() {
        return issuePartyId;
    }

    /**
     * 设置 发行机构代码
     * @param issuePartyId 发行机构代码
     */
    public void setIssuePartyId(String issuePartyId) {
        this.issuePartyId = issuePartyId == null ? null : issuePartyId.trim();
    }

    /**
     * 取得 计划行内资产/负债编码
     * @return 计划行内资产/负债编码
     */
    public String getPlanAssetCode() {
        return planAssetCode;
    }

    /**
     * 设置 计划行内资产/负债编码
     * @param planAssetCode 计划行内资产/负债编码
     */
    public void setPlanAssetCode(String planAssetCode) {
        this.planAssetCode = planAssetCode == null ? null : planAssetCode.trim();
    }

    /**
     * 取得 人民币发行金额
     * @return 人民币发行金额
     */
    public BigDecimal getIssueAmt() {
        return issueAmt;
    }

    /**
     * 设置 人民币发行金额
     * @param issueAmt 人民币发行金额
     */
    public void setIssueAmt(BigDecimal issueAmt) {
        this.issueAmt = issueAmt;
    }

    /**
     * 取得 总发行数量
     * @return 总发行数量
     */
    public BigDecimal getTotalNum() {
        return totalNum;
    }

    /**
     * 设置 总发行数量
     * @param totalNum 总发行数量
     */
    public void setTotalNum(BigDecimal totalNum) {
        this.totalNum = totalNum;
    }

    /**
     * 取得 未投资头寸
     * @return 未投资头寸
     */
    public BigDecimal getUninvestCash() {
        return uninvestCash;
    }

    /**
     * 设置 未投资头寸
     * @param uninvestCash 未投资头寸
     */
    public void setUninvestCash(BigDecimal uninvestCash) {
        this.uninvestCash = uninvestCash;
    }

    /**
     * 取得 行内资产/负债编码
     * @return 行内资产/负债编码
     */
    public String getAssetCode() {
        return assetCode;
    }

    /**
     * 设置 行内资产/负债编码
     * @param assetCode 行内资产/负债编码
     */
    public void setAssetCode(String assetCode) {
        this.assetCode = assetCode == null ? null : assetCode.trim();
    }

    /**
     * 取得 持仓数量
     * @return 持仓数量
     */
    public BigDecimal getPosNum() {
        return posNum;
    }

    /**
     * 设置 持仓数量
     * @param posNum 持仓数量
     */
    public void setPosNum(BigDecimal posNum) {
        this.posNum = posNum;
    }

    /**
     * 取得 折算市值
     * @return 折算市值
     */
    public BigDecimal getMarketValue() {
        return marketValue;
    }

    /**
     * 设置 折算市值
     * @param marketValue 折算市值
     */
    public void setMarketValue(BigDecimal marketValue) {
        this.marketValue = marketValue;
    }

    /**
     * 取得 持仓日期
     * @return 持仓日期
     */
    public Date getPosDate() {
        return posDate;
    }

    /**
     * 设置 持仓日期
     * @param posDate 持仓日期
     */
    public void setPosDate(Date posDate) {
        this.posDate = posDate;
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
     * 取得 报送状态
     * @return 报送状态
     */
    public String getSendStatus() {
        return sendStatus;
    }

    /**
     * 设置 报送状态
     * @param sendStatus 报送状态
     */
    public void setSendStatus(String sendStatus) {
        this.sendStatus = sendStatus == null ? null : sendStatus.trim();
    }

    /**
     * 取得 数据日期
     * @return 数据日期
     */
    public Date getStatusDate() {
        return statusDate;
    }

    /**
     * 设置 数据日期
     * @param statusDate 数据日期
     */
    public void setStatusDate(Date statusDate) {
        this.statusDate = statusDate;
    }
}