package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;
import java.math.BigDecimal;

public class ChbMonAstSub extends ChbMonAstSubKey {
    /**
     * 报送期数
     */
    private String prodId;

    /**
     * 资产名称
     */
    private String assetName;

    /**
     * 金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal amt;

    /**
     * 报送状态
     */
    private String sendStatus;

    /**
     * 工作流代码
     */
    private String workflowId;

    /**
     * 取得 报送期数
     * @return 报送期数
     */
    public String getProdId() {
        return prodId;
    }

    /**
     * 设置 报送期数
     * @param prodId 报送期数
     */
    public void setProdId(String prodId) {
        this.prodId = prodId == null ? null : prodId.trim();
    }

    /**
     * 取得 资产名称
     * @return 资产名称
     */
    public String getAssetName() {
        return assetName;
    }

    /**
     * 设置 资产名称
     * @param assetName 资产名称
     */
    public void setAssetName(String assetName) {
        this.assetName = assetName == null ? null : assetName.trim();
    }

    /**
     * 取得 金额
     * @return 金额
     */
    public BigDecimal getAmt() {
        return amt;
    }

    /**
     * 设置 金额
     * @param amt 金额
     */
    public void setAmt(BigDecimal amt) {
        this.amt = amt;
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
}