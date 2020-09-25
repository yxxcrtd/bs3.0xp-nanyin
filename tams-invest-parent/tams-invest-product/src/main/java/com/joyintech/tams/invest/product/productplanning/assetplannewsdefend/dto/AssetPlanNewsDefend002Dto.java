package com.joyintech.tams.invest.product.productplanning.assetplannewsdefend.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.tams.invest.product.productplanning.assetplannewsdefend.validator.AssetPlanNewsDefend002DtoValidator;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.dto.ProcessEntity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@PageId("AssetPlanNewsDefend002")
@ValidLogic(targetClass = AssetPlanNewsDefend002DtoValidator.class)
public class AssetPlanNewsDefend002Dto extends BaseEntity implements ProcessEntity {

    private static final long serialVersionUID = -8756502589538999775L;
    /**
     * 按钮Id
     */
    private String elementId;

    /**
     * 操作类型
     */
    private String operateType;

    /**
     * 资产规划编号
     */
    private String assetPlanCode;

    /**
     * 规划开始日
     */
    @NotEmpty(fieldName = "AssetPlanNewsDefend002.vdate")
    private Date vdate;
    /**
     * 规划结束日
     */
    @NotEmpty(fieldName = "AssetPlanNewsDefend002.edate")
    private Date edate;
    /**
     * 规划基准日
     */
    @NotEmpty(fieldName = "AssetPlanNewsDefend002.benchDate")
    private Date benchDate;
    /**
     * 目标资产规模
     */
    @NotEmpty(fieldName = "AssetPlanNewsDefend002.assetTargetScale")
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal assetTargetScale;

    /**
     * 资产规划系列列表
     */
    private List<AssetPlanNewsDefend> assetPlanNewsDefendList;

    /**
     * 备注
     */
    private String remark;

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

    @JsonDeserialize(using = CustomDateDeserialize.class)
    public Date getVdate() {
        return vdate;
    }

    public void setVdate(Date vdate) {
        this.vdate = vdate;
    }

    @JsonDeserialize(using = CustomDateDeserialize.class)
    public Date getEdate() {
        return edate;
    }

    public void setEdate(Date edate) {
        this.edate = edate;
    }

    @JsonDeserialize(using = CustomDateDeserialize.class)
    public Date getBenchDate() {
        return benchDate;
    }

    public void setBenchDate(Date benchDate) {
        this.benchDate = benchDate;
    }

    public BigDecimal getAssetTargetScale() {
        return assetTargetScale;
    }

    public void setAssetTargetScale(BigDecimal assetTargetScale) {
        this.assetTargetScale = assetTargetScale;
    }

    public List<AssetPlanNewsDefend> getAssetPlanNewsDefendList() {
        return assetPlanNewsDefendList;
    }

    public void setAssetPlanNewsDefendList(List<AssetPlanNewsDefend> assetPlanNewsDefendList) {
        this.assetPlanNewsDefendList = assetPlanNewsDefendList;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
