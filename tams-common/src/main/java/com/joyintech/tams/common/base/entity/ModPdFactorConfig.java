package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import java.math.BigDecimal;

public class ModPdFactorConfig extends BaseEntity {
    /**
     * 流水号
     */
    private String seqno;

    /**
     * 年份
     */
    private String vouchYear;

    /**
     * 季度
     */
    private String vouchQuarter;

    /**
     * 乐观因子
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal optimisticFactor;

    /**
     * 基础因子
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal basicFactor;

    /**
     * 悲观因子
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal pessimismFactor;

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
     * 取得 年份
     * @return 年份
     */
    public String getVouchYear() {
        return vouchYear;
    }

    /**
     * 设置 年份
     * @param vouchYear 年份
     */
    public void setVouchYear(String vouchYear) {
        this.vouchYear = vouchYear == null ? null : vouchYear.trim();
    }

    /**
     * 取得 季度
     * @return 季度
     */
    public String getVouchQuarter() {
        return vouchQuarter;
    }

    /**
     * 设置 季度
     * @param vouchQuarter 季度
     */
    public void setVouchQuarter(String vouchQuarter) {
        this.vouchQuarter = vouchQuarter == null ? null : vouchQuarter.trim();
    }

    /**
     * 取得 乐观因子
     * @return 乐观因子
     */
    public BigDecimal getOptimisticFactor() {
        return optimisticFactor;
    }

    /**
     * 设置 乐观因子
     * @param optimisticFactor 乐观因子
     */
    public void setOptimisticFactor(BigDecimal optimisticFactor) {
        this.optimisticFactor = optimisticFactor;
    }

    /**
     * 取得 基础因子
     * @return 基础因子
     */
    public BigDecimal getBasicFactor() {
        return basicFactor;
    }

    /**
     * 设置 基础因子
     * @param basicFactor 基础因子
     */
    public void setBasicFactor(BigDecimal basicFactor) {
        this.basicFactor = basicFactor;
    }

    /**
     * 取得 悲观因子
     * @return 悲观因子
     */
    public BigDecimal getPessimismFactor() {
        return pessimismFactor;
    }

    /**
     * 设置 悲观因子
     * @param pessimismFactor 悲观因子
     */
    public void setPessimismFactor(BigDecimal pessimismFactor) {
        this.pessimismFactor = pessimismFactor;
    }
}