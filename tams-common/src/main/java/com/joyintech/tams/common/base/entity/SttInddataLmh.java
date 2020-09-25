package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.tams.common.util.BigDecimalSerializer;
import java.math.BigDecimal;

public class SttInddataLmh extends BaseEntity {
    /**
     * 指标值代码
     */
    private String seqNo;

    /**
     * 指标代码
     */
    private String indId;

    /**
     * 模板代码
     */
    private String testId;

    /**
     * 轻度
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal lightValue;

    /**
     * 中度
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal mediumValue;

    /**
     * 重度
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal heavyValue;

    /**
     * 取得 指标值代码
     * @return 指标值代码
     */
    public String getSeqNo() {
        return seqNo;
    }

    /**
     * 设置 指标值代码
     * @param seqNo 指标值代码
     */
    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo == null ? null : seqNo.trim();
    }

    /**
     * 取得 指标代码
     * @return 指标代码
     */
    public String getIndId() {
        return indId;
    }

    /**
     * 设置 指标代码
     * @param indId 指标代码
     */
    public void setIndId(String indId) {
        this.indId = indId == null ? null : indId.trim();
    }

    /**
     * 取得 模板代码
     * @return 模板代码
     */
    public String getTestId() {
        return testId;
    }

    /**
     * 设置 模板代码
     * @param testId 模板代码
     */
    public void setTestId(String testId) {
        this.testId = testId == null ? null : testId.trim();
    }

    /**
     * 取得 轻度
     * @return 轻度
     */
    public BigDecimal getLightValue() {
        return lightValue;
    }

    /**
     * 设置 轻度
     * @param lightValue 轻度
     */
    public void setLightValue(BigDecimal lightValue) {
        this.lightValue = lightValue;
    }

    /**
     * 取得 中度
     * @return 中度
     */
    public BigDecimal getMediumValue() {
        return mediumValue;
    }

    /**
     * 设置 中度
     * @param mediumValue 中度
     */
    public void setMediumValue(BigDecimal mediumValue) {
        this.mediumValue = mediumValue;
    }

    /**
     * 取得 重度
     * @return 重度
     */
    public BigDecimal getHeavyValue() {
        return heavyValue;
    }

    /**
     * 设置 重度
     * @param heavyValue 重度
     */
    public void setHeavyValue(BigDecimal heavyValue) {
        this.heavyValue = heavyValue;
    }
}