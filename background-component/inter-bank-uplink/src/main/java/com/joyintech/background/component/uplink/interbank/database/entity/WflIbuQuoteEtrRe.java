package com.joyintech.background.component.uplink.interbank.database.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.background.component.common.pack.common.json.BigDecimalSerializer;

import java.math.BigDecimal;

public class WflIbuQuoteEtrRe extends WflIbuQuoteEtrReKey {
    /**
     * 组合代码
     */
    private String portfolioId;

    /**
     * 交易份额，债券数量等
     */
    @JsonSerialize(using= BigDecimalSerializer.class)
    private BigDecimal shareAmt;

    /**
     * 全价总额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal fpriceAmt;

    /**
     * 备注
     */
    private String remark;

    /**
     * 工作流代码
     */
    private String workflowId;

    /**
     * 交易本金(中债登)
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal cbFpriceAmt;

    /**
     * 交易本金(上清所)
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal scFpriceAmt;

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
     * 取得 交易份额，债券数量等
     * @return 交易份额，债券数量等
     */
    public BigDecimal getShareAmt() {
        return shareAmt;
    }

    /**
     * 设置 交易份额，债券数量等
     * @param shareAmt 交易份额，债券数量等
     */
    public void setShareAmt(BigDecimal shareAmt) {
        this.shareAmt = shareAmt;
    }

    /**
     * 取得 全价总额
     * @return 全价总额
     */
    public BigDecimal getFpriceAmt() {
        return fpriceAmt;
    }

    /**
     * 设置 全价总额
     * @param fpriceAmt 全价总额
     */
    public void setFpriceAmt(BigDecimal fpriceAmt) {
        this.fpriceAmt = fpriceAmt;
    }

    /**
     * 取得 备注
     * @return 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置 备注
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
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
     * 取得 交易本金(中债登)
     * @return 交易本金(中债登)
     */
    public BigDecimal getCbFpriceAmt() {
        return cbFpriceAmt;
    }

    /**
     * 设置 交易本金(中债登)
     * @param cbFpriceAmt 交易本金(中债登)
     */
    public void setCbFpriceAmt(BigDecimal cbFpriceAmt) {
        this.cbFpriceAmt = cbFpriceAmt;
    }

    /**
     * 取得 交易本金(上清所)
     * @return 交易本金(上清所)
     */
    public BigDecimal getScFpriceAmt() {
        return scFpriceAmt;
    }

    /**
     * 设置 交易本金(上清所)
     * @param scFpriceAmt 交易本金(上清所)
     */
    public void setScFpriceAmt(BigDecimal scFpriceAmt) {
        this.scFpriceAmt = scFpriceAmt;
    }
}