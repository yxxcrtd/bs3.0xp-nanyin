package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;

public class PrdPlanDetailKey extends BaseEntity {
    /**
     * 产品规划编号
     */
    private String prdPlanCode;

    /**
     * 产品规划系列编号
     */
    private String prdPlanSerisId;

    /**
     * 取得 产品规划编号
     * @return 产品规划编号
     */
    public String getPrdPlanCode() {
        return prdPlanCode;
    }

    /**
     * 设置 产品规划编号
     * @param prdPlanCode 产品规划编号
     */
    public void setPrdPlanCode(String prdPlanCode) {
        this.prdPlanCode = prdPlanCode == null ? null : prdPlanCode.trim();
    }

    /**
     * 取得 产品规划系列编号
     * @return 产品规划系列编号
     */
    public String getPrdPlanSerisId() {
        return prdPlanSerisId;
    }

    /**
     * 设置 产品规划系列编号
     * @param prdPlanSerisId 产品规划系列编号
     */
    public void setPrdPlanSerisId(String prdPlanSerisId) {
        this.prdPlanSerisId = prdPlanSerisId == null ? null : prdPlanSerisId.trim();
    }
}