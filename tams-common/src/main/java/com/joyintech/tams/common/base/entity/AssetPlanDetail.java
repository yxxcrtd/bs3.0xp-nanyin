package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;
import java.math.BigDecimal;

public class AssetPlanDetail extends AssetPlanDetailKey {
    /**
     * 规划初始值
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal planInitAmt;

    /**
     * 规划变动值
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal planChangeAmt;

    /**
     * 规划目标值
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal planTargetAmt;

    /**
     * 取得 规划初始值
     * @return 规划初始值
     */
    public BigDecimal getPlanInitAmt() {
        return planInitAmt;
    }

    /**
     * 设置 规划初始值
     * @param planInitAmt 规划初始值
     */
    public void setPlanInitAmt(BigDecimal planInitAmt) {
        this.planInitAmt = planInitAmt;
    }

    /**
     * 取得 规划变动值
     * @return 规划变动值
     */
    public BigDecimal getPlanChangeAmt() {
        return planChangeAmt;
    }

    /**
     * 设置 规划变动值
     * @param planChangeAmt 规划变动值
     */
    public void setPlanChangeAmt(BigDecimal planChangeAmt) {
        this.planChangeAmt = planChangeAmt;
    }

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
}