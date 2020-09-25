package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;
import java.util.Date;

public class RskFacilityAdjPlanKey extends BaseEntity {
    /**
     * 风险授信ID
     */
    private String facilityId;

    /**
     * 授信调整开始日
     */
    private Date facilityAdjStartDate;

    /**
     * 取得 风险授信ID
     * @return 风险授信ID
     */
    public String getFacilityId() {
        return facilityId;
    }

    /**
     * 设置 风险授信ID
     * @param facilityId 风险授信ID
     */
    public void setFacilityId(String facilityId) {
        this.facilityId = facilityId == null ? null : facilityId.trim();
    }

    /**
     * 取得 授信调整开始日
     * @return 授信调整开始日
     */
    public Date getFacilityAdjStartDate() {
        return facilityAdjStartDate;
    }

    /**
     * 设置 授信调整开始日
     * @param facilityAdjStartDate 授信调整开始日
     */
    public void setFacilityAdjStartDate(Date facilityAdjStartDate) {
        this.facilityAdjStartDate = facilityAdjStartDate;
    }
}