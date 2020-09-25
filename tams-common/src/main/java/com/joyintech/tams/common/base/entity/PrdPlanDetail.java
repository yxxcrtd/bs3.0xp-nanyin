package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;
import java.math.BigDecimal;

public class PrdPlanDetail extends PrdPlanDetailKey {
    /**
     * 产品规划系列名称
     */
    private String prdPlanSerisName;

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
     * 是否新创
     */
    private String isNew;

    /**
     * 取得 产品规划系列名称
     * @return 产品规划系列名称
     */
    public String getPrdPlanSerisName() {
        return prdPlanSerisName;
    }

    /**
     * 设置 产品规划系列名称
     * @param prdPlanSerisName 产品规划系列名称
     */
    public void setPrdPlanSerisName(String prdPlanSerisName) {
        this.prdPlanSerisName = prdPlanSerisName == null ? null : prdPlanSerisName.trim();
    }

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

    /**
     * 取得 是否新创
     * @return 是否新创
     */
    public String getIsNew() {
        return isNew;
    }

    /**
     * 设置 是否新创
     * @param isNew 是否新创
     */
    public void setIsNew(String isNew) {
        this.isNew = isNew == null ? null : isNew.trim();
    }
}