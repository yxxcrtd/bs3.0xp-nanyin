package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;
import java.math.BigDecimal;

public class PtlGoal extends PtlGoalKey {
    /**
     * 投资占比上限
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal investMax;

    /**
     * 投资占比下限
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal investMin;

    /**
     * 备注
     */
    private String remark;

    /**
     * 取得 投资占比上限
     * @return 投资占比上限
     */
    public BigDecimal getInvestMax() {
        return investMax;
    }

    /**
     * 设置 投资占比上限
     * @param investMax 投资占比上限
     */
    public void setInvestMax(BigDecimal investMax) {
        this.investMax = investMax;
    }

    /**
     * 取得 投资占比下限
     * @return 投资占比下限
     */
    public BigDecimal getInvestMin() {
        return investMin;
    }

    /**
     * 设置 投资占比下限
     * @param investMin 投资占比下限
     */
    public void setInvestMin(BigDecimal investMin) {
        this.investMin = investMin;
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
}