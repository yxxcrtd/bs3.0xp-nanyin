package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import java.math.BigDecimal;

public class EtrEntrustDetail extends BaseEntity {
    /**
     * 指令编号
     */
    private String entrustId;

    /**
     * 组合代码
     */
    private String portfolioId;

    /**
     * 分配比例
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal disRate;

    /**
     * 委托金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal entrustAmt;

    /**
     * 委托数量
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal entrustNum;

    /**
     * 目标类型
     */
    private String targetType;

    /**
     * 取得 指令编号
     * @return 指令编号
     */
    public String getEntrustId() {
        return entrustId;
    }

    /**
     * 设置 指令编号
     * @param entrustId 指令编号
     */
    public void setEntrustId(String entrustId) {
        this.entrustId = entrustId == null ? null : entrustId.trim();
    }

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
     * 取得 分配比例
     * @return 分配比例
     */
    public BigDecimal getDisRate() {
        return disRate;
    }

    /**
     * 设置 分配比例
     * @param disRate 分配比例
     */
    public void setDisRate(BigDecimal disRate) {
        this.disRate = disRate;
    }

    /**
     * 取得 委托金额
     * @return 委托金额
     */
    public BigDecimal getEntrustAmt() {
        return entrustAmt;
    }

    /**
     * 设置 委托金额
     * @param entrustAmt 委托金额
     */
    public void setEntrustAmt(BigDecimal entrustAmt) {
        this.entrustAmt = entrustAmt;
    }

    /**
     * 取得 委托数量
     * @return 委托数量
     */
    public BigDecimal getEntrustNum() {
        return entrustNum;
    }

    /**
     * 设置 委托数量
     * @param entrustNum 委托数量
     */
    public void setEntrustNum(BigDecimal entrustNum) {
        this.entrustNum = entrustNum;
    }

    /**
     * 取得 目标类型
     * @return 目标类型
     */
    public String getTargetType() {
        return targetType;
    }

    /**
     * 设置 目标类型
     * @param targetType 目标类型
     */
    public void setTargetType(String targetType) {
        this.targetType = targetType == null ? null : targetType.trim();
    }
}