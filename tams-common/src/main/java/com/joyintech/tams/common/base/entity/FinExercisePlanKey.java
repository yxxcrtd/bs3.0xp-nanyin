package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;
import java.util.Date;

public class FinExercisePlanKey extends BaseEntity {
    /**
     * 现金流代码
     */
    private String cashId;

    /**
     * 行权方式
     */
    private String exerciseType;

    /**
     * 计划行权日
     */
    private Date exerciseDate;

    /**
     * 取得 现金流代码
     * @return 现金流代码
     */
    public String getCashId() {
        return cashId;
    }

    /**
     * 设置 现金流代码
     * @param cashId 现金流代码
     */
    public void setCashId(String cashId) {
        this.cashId = cashId == null ? null : cashId.trim();
    }

    /**
     * 取得 行权方式
     * @return 行权方式
     */
    public String getExerciseType() {
        return exerciseType;
    }

    /**
     * 设置 行权方式
     * @param exerciseType 行权方式
     */
    public void setExerciseType(String exerciseType) {
        this.exerciseType = exerciseType == null ? null : exerciseType.trim();
    }

    /**
     * 取得 计划行权日
     * @return 计划行权日
     */
    public Date getExerciseDate() {
        return exerciseDate;
    }

    /**
     * 设置 计划行权日
     * @param exerciseDate 计划行权日
     */
    public void setExerciseDate(Date exerciseDate) {
        this.exerciseDate = exerciseDate;
    }
}