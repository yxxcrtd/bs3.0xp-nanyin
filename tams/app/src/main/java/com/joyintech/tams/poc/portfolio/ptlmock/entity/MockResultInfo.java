package com.joyintech.tams.poc.portfolio.ptlmock.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class MockResultInfo extends BaseEntity {
    /**
     * 流水号
     */
    private String seqNo;

    /**
     * 生成时间
     */
    private Date generateTime;

    /**
     * 测算日
     */
    private Date mockDate;

    /**
     * 产品设计系列编号
     */
    private String prdDesignId;

    /**
     * 产品规模
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal prdScale;

    /**
     * 产品期限
     */
    private String prdPeriod;

    /**
     * 收益率上限
     */
    private String rateMax;

    /**
     * 收益率下限
     */
    private String rateMix;

    /**
     * 需求编号
     */
    private String requirementId;

    /**
     * 批次号
     */
    private String batchId;

    /**
     * 数据状态，有效，撤销
     */
    private String dataStatus;

    /**
     * 取得 流水号
     * @return 流水号
     */
    public String getSeqNo() {
        return seqNo;
    }

    /**
     * 设置 流水号
     * @param seqNo 流水号
     */
    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo == null ? null : seqNo.trim();
    }

    /**
     * 取得 生成时间
     * @return 生成时间
     */
    public Date getGenerateTime() {
        return generateTime;
    }

    /**
     * 设置 生成时间
     * @param generateTime 生成时间
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setGenerateTime(Date generateTime) {
        this.generateTime = generateTime;
    }

    /**
     * 取得 测算日
     * @return 测算日
     */
    public Date getMockDate() {
        return mockDate;
    }

    /**
     * 设置 测算日
     * @param mockDate 测算日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setMockDate(Date mockDate) {
        this.mockDate = mockDate;
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
     * 取得 产品规模
     * @return 产品规模
     */
    public BigDecimal getPrdScale() {
        return prdScale;
    }

    /**
     * 设置 产品规模
     * @param prdScale 产品规模
     */
    public void setPrdScale(BigDecimal prdScale) {
        this.prdScale = prdScale;
    }

    /**
     * 取得 产品期限
     * @return 产品期限
     */
    public String getPrdPeriod() {
        return prdPeriod;
    }

    /**
     * 设置 产品期限
     * @param prdPeriod 产品期限
     */
    public void setPrdPeriod(String prdPeriod) {
        this.prdPeriod = prdPeriod == null ? null : prdPeriod.trim();
    }

    /**
     * 取得 收益率上限
     * @return 收益率上限
     */
    public String getRateMax() {
        return rateMax;
    }

    /**
     * 设置 收益率上限
     * @param rateMax 收益率上限
     */
    public void setRateMax(String rateMax) {
        this.rateMax = rateMax == null ? null : rateMax.trim();
    }

    /**
     * 取得 收益率下限
     * @return 收益率下限
     */
    public String getRateMix() {
        return rateMix;
    }

    /**
     * 设置 收益率下限
     * @param rateMix 收益率下限
     */
    public void setRateMix(String rateMix) {
        this.rateMix = rateMix == null ? null : rateMix.trim();
    }

    /**
     * 取得 需求编号
     * @return 需求编号
     */
    public String getRequirementId() {
        return requirementId;
    }

    /**
     * 设置 需求编号
     * @param requirementId 需求编号
     */
    public void setRequirementId(String requirementId) {
        this.requirementId = requirementId == null ? null : requirementId.trim();
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
     * 取得 数据状态，有效，撤销
     * @return 数据状态，有效，撤销
     */
    public String getDataStatus() {
        return dataStatus;
    }

    /**
     * 设置 数据状态，有效，撤销
     * @param dataStatus 数据状态，有效，撤销
     */
    public void setDataStatus(String dataStatus) {
        this.dataStatus = dataStatus == null ? null : dataStatus.trim();
    }
}