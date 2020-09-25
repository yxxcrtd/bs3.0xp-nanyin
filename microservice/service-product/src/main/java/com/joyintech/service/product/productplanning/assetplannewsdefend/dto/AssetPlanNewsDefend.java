package com.joyintech.service.product.productplanning.assetplannewsdefend.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;

import java.math.BigDecimal;
import java.util.Date;

public class AssetPlanNewsDefend {

    /**
     * 资产规划编号
     */
    private String assetPlanCode;
    /**
     * 资产大类
     */
    private String assetType;

    /**
     * 产品规划系列名称
     */
    private String prdPlanSerisName;

    /**
     * 规划开始日
     */
    private Date vdate;
    /**
     * 规划结束日
     */
    private Date edate;
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
     * 备注
     */
    private String remark;
    /**
     * 基准日
     */
    private Date benchDate;
    /**
     * 产品系列规划目标值
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal planTargetSum;

    public String getAssetPlanCode() {
        return assetPlanCode;
    }

    public void setAssetPlanCode(String assetPlanCode) {
        this.assetPlanCode = assetPlanCode;
    }

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

    public void setAssetType(String assetType) {
        this.assetType = assetType;
    }

    public Date getVdate() {
        return vdate;
    }

    public void setVdate(Date vdate) {
        this.vdate = vdate;
    }

    public Date getEdate() {
        return edate;
    }

    public void setEdate(Date edate) {
        this.edate = edate;
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

    public String getPrdPlanSerisName() {
        return prdPlanSerisName;
    }

    public void setPrdPlanSerisName(String prdPlanSerisName) {
        this.prdPlanSerisName = prdPlanSerisName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
