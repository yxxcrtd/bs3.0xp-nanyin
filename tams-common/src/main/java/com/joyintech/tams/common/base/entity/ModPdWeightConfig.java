package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import java.math.BigDecimal;

public class ModPdWeightConfig extends BaseEntity {
    /**
     * 流水号
     */
    private String seqno;

    /**
     * 乐观权重
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal optimisticWeight;

    /**
     * 基础权重
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal basicWeight;

    /**
     * 悲观权重
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal pessimismWeight;

    /**
     * 取得 流水号
     * @return 流水号
     */
    public String getSeqno() {
        return seqno;
    }

    /**
     * 设置 流水号
     * @param seqno 流水号
     */
    public void setSeqno(String seqno) {
        this.seqno = seqno == null ? null : seqno.trim();
    }

    /**
     * 取得 乐观权重
     * @return 乐观权重
     */
    public BigDecimal getOptimisticWeight() {
        return optimisticWeight;
    }

    /**
     * 设置 乐观权重
     * @param optimisticWeight 乐观权重
     */
    public void setOptimisticWeight(BigDecimal optimisticWeight) {
        this.optimisticWeight = optimisticWeight;
    }

    /**
     * 取得 基础权重
     * @return 基础权重
     */
    public BigDecimal getBasicWeight() {
        return basicWeight;
    }

    /**
     * 设置 基础权重
     * @param basicWeight 基础权重
     */
    public void setBasicWeight(BigDecimal basicWeight) {
        this.basicWeight = basicWeight;
    }

    /**
     * 取得 悲观权重
     * @return 悲观权重
     */
    public BigDecimal getPessimismWeight() {
        return pessimismWeight;
    }

    /**
     * 设置 悲观权重
     * @param pessimismWeight 悲观权重
     */
    public void setPessimismWeight(BigDecimal pessimismWeight) {
        this.pessimismWeight = pessimismWeight;
    }
}