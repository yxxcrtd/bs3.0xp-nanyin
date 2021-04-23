package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import java.math.BigDecimal;

public class TrdMinpriceConfigDetail extends BaseEntity {
    /**
     * 流水号
     */
    private String seqNo;

    /**
     * 配置流水号
     */
    private String configSeqNo;

    /**
     * 分档条件
     */
    private String gradeCondition;

    /**
     * 分档金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal gradeAmt;

    /**
     * 最小价差
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal miniPrice;

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
     * 取得 配置流水号
     * @return 配置流水号
     */
    public String getConfigSeqNo() {
        return configSeqNo;
    }

    /**
     * 设置 配置流水号
     * @param configSeqNo 配置流水号
     */
    public void setConfigSeqNo(String configSeqNo) {
        this.configSeqNo = configSeqNo == null ? null : configSeqNo.trim();
    }

    /**
     * 取得 分档条件
     * @return 分档条件
     */
    public String getGradeCondition() {
        return gradeCondition;
    }

    /**
     * 设置 分档条件
     * @param gradeCondition 分档条件
     */
    public void setGradeCondition(String gradeCondition) {
        this.gradeCondition = gradeCondition == null ? null : gradeCondition.trim();
    }

    /**
     * 取得 分档金额
     * @return 分档金额
     */
    public BigDecimal getGradeAmt() {
        return gradeAmt;
    }

    /**
     * 设置 分档金额
     * @param gradeAmt 分档金额
     */
    public void setGradeAmt(BigDecimal gradeAmt) {
        this.gradeAmt = gradeAmt;
    }

    /**
     * 取得 最小价差
     * @return 最小价差
     */
    public BigDecimal getMiniPrice() {
        return miniPrice;
    }

    /**
     * 设置 最小价差
     * @param miniPrice 最小价差
     */
    public void setMiniPrice(BigDecimal miniPrice) {
        this.miniPrice = miniPrice;
    }
}