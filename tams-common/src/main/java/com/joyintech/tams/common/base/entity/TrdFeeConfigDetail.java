package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import java.math.BigDecimal;

public class TrdFeeConfigDetail extends BaseEntity {
    /**
     * 流水号
     */
    private String seqNo;

    /**
     * 配置流水号
     */
    private String configSeqNo;

    /**
     * 是否多券种
     */
    private String isMlti;

    /**
     * 计费值
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal chargeValue;

    /**
     * 费用上限
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal feeMaxi;

    /**
     * 费用下限
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal feeMini;

    /**
     * 计费比率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal chargeRate;

    /**
     * 是否包含起点金额
     */
    private String isCludeStartamt;

    /**
     * 分档金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal gradeAmount;

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
     * 取得 是否多券种
     * @return 是否多券种
     */
    public String getIsMlti() {
        return isMlti;
    }

    /**
     * 设置 是否多券种
     * @param isMlti 是否多券种
     */
    public void setIsMlti(String isMlti) {
        this.isMlti = isMlti == null ? null : isMlti.trim();
    }

    /**
     * 取得 计费值
     * @return 计费值
     */
    public BigDecimal getChargeValue() {
        return chargeValue;
    }

    /**
     * 设置 计费值
     * @param chargeValue 计费值
     */
    public void setChargeValue(BigDecimal chargeValue) {
        this.chargeValue = chargeValue;
    }

    /**
     * 取得 费用上限
     * @return 费用上限
     */
    public BigDecimal getFeeMaxi() {
        return feeMaxi;
    }

    /**
     * 设置 费用上限
     * @param feeMaxi 费用上限
     */
    public void setFeeMaxi(BigDecimal feeMaxi) {
        this.feeMaxi = feeMaxi;
    }

    /**
     * 取得 费用下限
     * @return 费用下限
     */
    public BigDecimal getFeeMini() {
        return feeMini;
    }

    /**
     * 设置 费用下限
     * @param feeMini 费用下限
     */
    public void setFeeMini(BigDecimal feeMini) {
        this.feeMini = feeMini;
    }

    /**
     * 取得 计费比率
     * @return 计费比率
     */
    public BigDecimal getChargeRate() {
        return chargeRate;
    }

    /**
     * 设置 计费比率
     * @param chargeRate 计费比率
     */
    public void setChargeRate(BigDecimal chargeRate) {
        this.chargeRate = chargeRate;
    }

    /**
     * 取得 是否包含起点金额
     * @return 是否包含起点金额
     */
    public String getIsCludeStartamt() {
        return isCludeStartamt;
    }

    /**
     * 设置 是否包含起点金额
     * @param isCludeStartamt 是否包含起点金额
     */
    public void setIsCludeStartamt(String isCludeStartamt) {
        this.isCludeStartamt = isCludeStartamt == null ? null : isCludeStartamt.trim();
    }

    /**
     * 取得 分档金额
     * @return 分档金额
     */
    public BigDecimal getGradeAmount() {
        return gradeAmount;
    }

    /**
     * 设置 分档金额
     * @param gradeAmount 分档金额
     */
    public void setGradeAmount(BigDecimal gradeAmount) {
        this.gradeAmount = gradeAmount;
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
}