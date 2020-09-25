package com.joyintech.service.report.poc.portfolio.ptlmock.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class MockTradeDeal extends BaseEntity {
    /**
     * 模拟交易编号
     */
    private String tradeId;

    /**
     * 日期
     */
    private Date tradeDate;

    /**
     * 产品设计系列编号
     */
    private String prdDesignId;

    /**
     * 资产大类
     */
    private String assetType;

    /**
     * 资产风险等级
     */
    private String assetRiskLevel;

    /**
     * 模拟匹配金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal mockAmt;

    /**
     * 模拟匹配份额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal mockShareAmt;

    /**
     * 批次号
     */
    private String batchId;

    /**
     * 数据状态，有效
     */
    private String dataStatus;

    /**
     * 取得 模拟交易编号
     * @return 模拟交易编号
     */
    public String getTradeId() {
        return tradeId;
    }

    /**
     * 设置 模拟交易编号
     * @param tradeId 模拟交易编号
     */
    public void setTradeId(String tradeId) {
        this.tradeId = tradeId == null ? null : tradeId.trim();
    }

    /**
     * 取得 日期
     * @return 日期
     */
    public Date getTradeDate() {
        return tradeDate;
    }

    /**
     * 设置 日期
     * @param tradeDate 日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setTradeDate(Date tradeDate) {
        this.tradeDate = tradeDate;
    }

    /**
     * 取得 产品设计系列编号
     * @return 产品设计系列编号
     */
    public String getPrdDesignId() {
        return prdDesignId;
    }

    /**
     * 设置 产品设计系列编号
     * @param prdDesignId 产品设计系列编号
     */
    public void setPrdDesignId(String prdDesignId) {
        this.prdDesignId = prdDesignId == null ? null : prdDesignId.trim();
    }

    /**
     * 取得 资产大类
     * @return 资产大类
     */
    public String getAssetType() {
        return assetType;
    }

    /**
     * 设置 资产大类
     * @param assetType 资产大类
     */
    public void setAssetType(String assetType) {
        this.assetType = assetType == null ? null : assetType.trim();
    }

    /**
     * 取得 资产风险等级
     * @return 资产风险等级
     */
    public String getAssetRiskLevel() {
        return assetRiskLevel;
    }

    /**
     * 设置 资产风险等级
     * @param assetRiskLevel 资产风险等级
     */
    public void setAssetRiskLevel(String assetRiskLevel) {
        this.assetRiskLevel = assetRiskLevel == null ? null : assetRiskLevel.trim();
    }

    /**
     * 取得 模拟匹配金额
     * @return 模拟匹配金额
     */
    public BigDecimal getMockAmt() {
        return mockAmt;
    }

    /**
     * 设置 模拟匹配金额
     * @param mockAmt 模拟匹配金额
     */
    public void setMockAmt(BigDecimal mockAmt) {
        this.mockAmt = mockAmt;
    }

    /**
     * 取得 模拟匹配份额
     * @return 模拟匹配份额
     */
    public BigDecimal getMockShareAmt() {
        return mockShareAmt;
    }

    /**
     * 设置 模拟匹配份额
     * @param mockShareAmt 模拟匹配份额
     */
    public void setMockShareAmt(BigDecimal mockShareAmt) {
        this.mockShareAmt = mockShareAmt;
    }

    /**
     * 取得 批次号
     * @return 批次号
     */
    public String getBatchId() {
        return batchId;
    }

    /**
     * 设置 批次号
     * @param batchId 批次号
     */
    public void setBatchId(String batchId) {
        this.batchId = batchId == null ? null : batchId.trim();
    }

    /**
     * 取得 数据状态，有效
     * @return 数据状态，有效
     */
    public String getDataStatus() {
        return dataStatus;
    }

    /**
     * 设置 数据状态，有效
     * @param dataStatus 数据状态，有效
     */
    public void setDataStatus(String dataStatus) {
        this.dataStatus = dataStatus == null ? null : dataStatus.trim();
    }
}