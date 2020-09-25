package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class RskFacilityAdjPlan extends RskFacilityAdjPlanKey {
    /**
     * 授信调整类型
     */
    private String facilityAdjType;

    /**
     * 授信调整额度
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal facilityAdjAmt;

    /**
     * 授信调整结束日
     */
    private Date facilityAdjEndDate;

    /**
     * 取得 授信调整类型
     * @return 授信调整类型
     */
    public String getFacilityAdjType() {
        return facilityAdjType;
    }

    /**
     * 设置 授信调整类型
     * @param facilityAdjType 授信调整类型
     */
    public void setFacilityAdjType(String facilityAdjType) {
        this.facilityAdjType = facilityAdjType == null ? null : facilityAdjType.trim();
    }

    /**
     * 取得 授信调整额度
     * @return 授信调整额度
     */
    public BigDecimal getFacilityAdjAmt() {
        return facilityAdjAmt;
    }

    /**
     * 设置 授信调整额度
     * @param facilityAdjAmt 授信调整额度
     */
    public void setFacilityAdjAmt(BigDecimal facilityAdjAmt) {
        this.facilityAdjAmt = facilityAdjAmt;
    }

    /**
     * 取得 授信调整结束日
     * @return 授信调整结束日
     */
    public Date getFacilityAdjEndDate() {
        return facilityAdjEndDate;
    }

    /**
     * 设置 授信调整结束日
     * @param facilityAdjEndDate 授信调整结束日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setFacilityAdjEndDate(Date facilityAdjEndDate) {
        this.facilityAdjEndDate = facilityAdjEndDate;
    }
}