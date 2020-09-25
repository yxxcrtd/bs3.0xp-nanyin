package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;
import java.math.BigDecimal;

public class AssetConfigDetail extends AssetConfigDetailKey {
    /**
     * 总比例
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal totalRatio;

    /**
     * 总金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal totalAmt;

    /**
     * 当前存量金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal curPerAmt;

    /**
     * 当前存量金额
     */
    private String remark;

    /**
     * 取得 总比例
     * @return 总比例
     */
    public BigDecimal getTotalRatio() {
        return totalRatio;
    }

    /**
     * 设置 总比例
     * @param totalRatio 总比例
     */
    public void setTotalRatio(BigDecimal totalRatio) {
        this.totalRatio = totalRatio;
    }

    /**
     * 取得 总金额
     * @return 总金额
     */
    public BigDecimal getTotalAmt() {
        return totalAmt;
    }

    /**
     * 设置 总金额
     * @param totalAmt 总金额
     */
    public void setTotalAmt(BigDecimal totalAmt) {
        this.totalAmt = totalAmt;
    }

    /**
     * 取得 当前存量金额
     * @return 当前存量金额
     */
    public BigDecimal getCurPerAmt() {
        return curPerAmt;
    }

    /**
     * 设置 当前存量金额
     * @param curPerAmt 当前存量金额
     */
    public void setCurPerAmt(BigDecimal curPerAmt) {
        this.curPerAmt = curPerAmt;
    }

    /**
     * 取得 当前存量金额
     * @return 当前存量金额
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置 当前存量金额
     * @param remark 当前存量金额
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}