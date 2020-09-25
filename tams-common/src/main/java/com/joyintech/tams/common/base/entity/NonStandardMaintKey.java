package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;
import java.util.Date;

public class NonStandardMaintKey extends BaseEntity {
    /**
     * 生效日
     */
    private Date effDate;

    /**
     * 产品规划系列编号
     */
    private String prdPlanSerisId;

    /**
     * 取得 生效日
     * @return 生效日
     */
    public Date getEffDate() {
        return effDate;
    }

    /**
     * 设置 生效日
     * @param effDate 生效日
     */
    public void setEffDate(Date effDate) {
        this.effDate = effDate;
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