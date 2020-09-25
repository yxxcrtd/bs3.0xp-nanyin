package com.joyintech.tams.invest.product.productplanning.assetplannewsdefend.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.dto.ProcessEntity;

import java.math.BigDecimal;
import java.util.Date;

@PageId("AssetPlanNewsDefend001")
public class AssetPlanNewsDefend001Dto extends BaseEntity implements ProcessEntity {

    private static final long serialVersionUID = 1794392952230029928L;
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
     * 操作类型
     */
    private String operateType;

    private String elementId;

    public String getElementId() {
        return elementId;
    }

    public void setElementId(String elementId) {
        this.elementId = elementId;
    }

    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }
    @PrimaryKey(PrimaryKey.Field.PK_1)
    public String getAssetPlanCode() {
        return assetPlanCode;
    }

    public void setAssetPlanCode(String assetPlanCode) {
        this.assetPlanCode = assetPlanCode;
    }

    @PrimaryKey(PrimaryKey.Field.PK_2)
    public String getAssetType() {
        return assetType;
    }

    public String getAssetTypeStr(){
        return SysDataUtil.getNameFromDataDict(DictCode.资产大类,assetType);
    }

    public void setAssetType(String assetType) {
        this.assetType = assetType;
    }

    public Date getVdate() {
        return vdate;
    }

    public String getVdateFmt(){
        return DateUtil.format(this.getVdate(), "yyyy-MM-dd");
    }

    public void setVdate(Date vdate) {
        this.vdate = vdate;
    }

    public Date getEdate() {
        return edate;
    }

    public String getEdateFmt(){
        return DateUtil.format(this.getEdate(), "yyyy-MM-dd");
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
