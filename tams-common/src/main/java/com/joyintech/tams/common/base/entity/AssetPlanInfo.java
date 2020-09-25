package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class AssetPlanInfo extends BaseEntity {
    /**
     * 资产规划编号
     */
    private String assetPlanCode;

    /**
     * 基准日
     */
    private Date benchDate;

    /**
     * 开始日
     */
    private Date vdate;

    /**
     * 结束日
     */
    private Date edate;

    /**
     * 目标总规模
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal targetScale;

    /**
     * 备注
     */
    private String remark;

    /**
     * 取得 资产规划编号
     * @return 资产规划编号
     */
    public String getAssetPlanCode() {
        return assetPlanCode;
    }

    /**
     * 设置 资产规划编号
     * @param assetPlanCode 资产规划编号
     */
    public void setAssetPlanCode(String assetPlanCode) {
        this.assetPlanCode = assetPlanCode == null ? null : assetPlanCode.trim();
    }

    /**
     * 取得 基准日
     * @return 基准日
     */
    public Date getBenchDate() {
        return benchDate;
    }

    /**
     * 设置 基准日
     * @param benchDate 基准日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setBenchDate(Date benchDate) {
        this.benchDate = benchDate;
    }

    /**
     * 取得 开始日
     * @return 开始日
     */
    public Date getVdate() {
        return vdate;
    }

    /**
     * 设置 开始日
     * @param vdate 开始日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setVdate(Date vdate) {
        this.vdate = vdate;
    }

    /**
     * 取得 结束日
     * @return 结束日
     */
    public Date getEdate() {
        return edate;
    }

    /**
     * 设置 结束日
     * @param edate 结束日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setEdate(Date edate) {
        this.edate = edate;
    }

    /**
     * 取得 目标总规模
     * @return 目标总规模
     */
    public BigDecimal getTargetScale() {
        return targetScale;
    }

    /**
     * 设置 目标总规模
     * @param targetScale 目标总规模
     */
    public void setTargetScale(BigDecimal targetScale) {
        this.targetScale = targetScale;
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