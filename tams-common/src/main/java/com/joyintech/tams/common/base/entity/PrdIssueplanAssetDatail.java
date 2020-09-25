package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;
import java.math.BigDecimal;

public class PrdIssueplanAssetDatail extends PrdIssueplanAssetDatailKey {
    /**
     * 规划目标值
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal planTargetAmt;

    /**
     * 剩余规划变动值
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal remainChangeAmt;

    /**
     * 取得 规划目标值
     * @return 规划目标值
     */
    public BigDecimal getPlanTargetAmt() {
        return planTargetAmt;
    }

    /**
     * 设置 规划目标值
     * @param planTargetAmt 规划目标值
     */
    public void setPlanTargetAmt(BigDecimal planTargetAmt) {
        this.planTargetAmt = planTargetAmt;
    }

    /**
     * 取得 剩余规划变动值
     * @return 剩余规划变动值
     */
    public BigDecimal getRemainChangeAmt() {
        return remainChangeAmt;
    }

    /**
     * 设置 剩余规划变动值
     * @param remainChangeAmt 剩余规划变动值
     */
    public void setRemainChangeAmt(BigDecimal remainChangeAmt) {
        this.remainChangeAmt = remainChangeAmt;
    }
}