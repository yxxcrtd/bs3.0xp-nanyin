package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class RskFacilityMng extends BaseEntity {
    /**
     * 风险授信ID
     */
    private String facilityId;

    /**
     * 额度类型
     */
    private String amountType;

    /**
     * 额度类型分项
     */
    private String amountTypeSub;

    /**
     * 授信类型
     */
    private String facilityType;

    /**
     * 授信主体（客户信息表主键）
     */
    private String facilitySubject;

    /**
     * 授信生效日
     */
    private Date facilityEffDate;

    /**
     * 授信到期日
     */
    private Date facilityEndDate;

    /**
     * 最长可投期限
     */
    private String maxDurationLimit;

    /**
     * 预警比例
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal earlyWarnRatio;

    /**
     * 批复号
     */
    private String batchNumber;

    /**
     * 备注
     */
    private String remark;

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
     * 取得 额度类型
     * @return 额度类型
     */
    public String getAmountType() {
        return amountType;
    }

    /**
     * 设置 额度类型
     * @param amountType 额度类型
     */
    public void setAmountType(String amountType) {
        this.amountType = amountType == null ? null : amountType.trim();
    }

    /**
     * 取得 额度类型分项
     * @return 额度类型分项
     */
    public String getAmountTypeSub() {
        return amountTypeSub;
    }

    /**
     * 设置 额度类型分项
     * @param amountTypeSub 额度类型分项
     */
    public void setAmountTypeSub(String amountTypeSub) {
        this.amountTypeSub = amountTypeSub == null ? null : amountTypeSub.trim();
    }

    /**
     * 取得 授信类型
     * @return 授信类型
     */
    public String getFacilityType() {
        return facilityType;
    }

    /**
     * 设置 授信类型
     * @param facilityType 授信类型
     */
    public void setFacilityType(String facilityType) {
        this.facilityType = facilityType == null ? null : facilityType.trim();
    }

    /**
     * 取得 授信主体（客户信息表主键）
     * @return 授信主体（客户信息表主键）
     */
    public String getFacilitySubject() {
        return facilitySubject;
    }

    /**
     * 设置 授信主体（客户信息表主键）
     * @param facilitySubject 授信主体（客户信息表主键）
     */
    public void setFacilitySubject(String facilitySubject) {
        this.facilitySubject = facilitySubject == null ? null : facilitySubject.trim();
    }

    /**
     * 取得 授信生效日
     * @return 授信生效日
     */
    public Date getFacilityEffDate() {
        return facilityEffDate;
    }

    /**
     * 设置 授信生效日
     * @param facilityEffDate 授信生效日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setFacilityEffDate(Date facilityEffDate) {
        this.facilityEffDate = facilityEffDate;
    }

    /**
     * 取得 授信到期日
     * @return 授信到期日
     */
    public Date getFacilityEndDate() {
        return facilityEndDate;
    }

    /**
     * 设置 授信到期日
     * @param facilityEndDate 授信到期日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setFacilityEndDate(Date facilityEndDate) {
        this.facilityEndDate = facilityEndDate;
    }

    /**
     * 取得 最长可投期限
     * @return 最长可投期限
     */
    public String getMaxDurationLimit() {
        return maxDurationLimit;
    }

    /**
     * 设置 最长可投期限
     * @param maxDurationLimit 最长可投期限
     */
    public void setMaxDurationLimit(String maxDurationLimit) {
        this.maxDurationLimit = maxDurationLimit == null ? null : maxDurationLimit.trim();
    }

    /**
     * 取得 预警比例
     * @return 预警比例
     */
    public BigDecimal getEarlyWarnRatio() {
        return earlyWarnRatio;
    }

    /**
     * 设置 预警比例
     * @param earlyWarnRatio 预警比例
     */
    public void setEarlyWarnRatio(BigDecimal earlyWarnRatio) {
        this.earlyWarnRatio = earlyWarnRatio;
    }

    /**
     * 取得 批复号
     * @return 批复号
     */
    public String getBatchNumber() {
        return batchNumber;
    }

    /**
     * 设置 批复号
     * @param batchNumber 批复号
     */
    public void setBatchNumber(String batchNumber) {
        this.batchNumber = batchNumber == null ? null : batchNumber.trim();
    }

    /**
     * 取得 备注
     * @return 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置 备注
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}