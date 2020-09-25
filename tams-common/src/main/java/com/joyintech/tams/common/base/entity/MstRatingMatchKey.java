package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class MstRatingMatchKey extends BaseEntity {
    /**
     * 原始评级结果
     */
    private String gradeResultOr;

    /**
     * 标准评级结果
     */
    private String gradeResultSt;

    /**
     * 取得 原始评级结果
     * @return 原始评级结果
     */
    public String getGradeResultOr() {
        return gradeResultOr;
    }

    /**
     * 设置 原始评级结果
     * @param gradeResultOr 原始评级结果
     */
    public void setGradeResultOr(String gradeResultOr) {
        this.gradeResultOr = gradeResultOr == null ? null : gradeResultOr.trim();
    }

    /**
     * 取得 标准评级结果
     * @return 标准评级结果
     */
    public String getGradeResultSt() {
        return gradeResultSt;
    }

    /**
     * 设置 标准评级结果
     * @param gradeResultSt 标准评级结果
     */
    public void setGradeResultSt(String gradeResultSt) {
        this.gradeResultSt = gradeResultSt == null ? null : gradeResultSt.trim();
    }
}