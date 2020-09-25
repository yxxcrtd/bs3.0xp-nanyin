package com.joyintech.tams.invest.product.productplanning.assetplannewsdefend.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.constant.DictCode;

import java.math.BigDecimal;
import java.util.Date;

public class AssetPlanNewsDefend {
    /**
     * 资产大类
     */
    private String assetType;

    /**
     * 资产规划编号
     */
    private String assetPlanCode;

    /**
     * 规划初始值
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal planInitAmt;
    /**
     * 规划变动值
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal planChangeAmt;
    /**
     * 规划目标值
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal planTargetAmt;

    /**
     * 基准日
     */
    private Date benchDate;
    /**
     * 产品系列规划目标值
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal planTargetSum;

    public Date getBenchDate() {
        return benchDate;
    }

    public void setBenchDate(Date benchDate) {
        this.benchDate = benchDate;
    }

    public BigDecimal getPlanTargetSum() {
        return planTargetSum;
    }

    public void setPlanTargetSum(BigDecimal planTargetSum) {
        this.planTargetSum = planTargetSum;
    }

    public String getAssetType() {
        return assetType;
    }

    public String getAssetTypeStr(){
        return SysDataUtil.getNameFromDataDict(DictCode.资产大类,assetType);
    }

    public void setAssetType(String assetType) {
        this.assetType = assetType;
    }

    public String getAssetPlanCode() {
        return assetPlanCode;
    }

    public void setAssetPlanCode(String assetPlanCode) {
        this.assetPlanCode = assetPlanCode;
    }

    public BigDecimal getPlanInitAmt() {
        return planInitAmt;
    }

    public void setPlanInitAmt(BigDecimal planInitAmt) {
        this.planInitAmt = planInitAmt;
    }

    public BigDecimal getPlanChangeAmt() {
        return planChangeAmt;
    }

    public void setPlanChangeAmt(BigDecimal planChangeAmt) {
        this.planChangeAmt = planChangeAmt;
    }

    public BigDecimal getPlanTargetAmt() {
        return planTargetAmt;
    }

    public void setPlanTargetAmt(BigDecimal planTargetAmt) {
        this.planTargetAmt = planTargetAmt;
    }
}
