package com.joyintech.service.product.productplanning.informationMaintenance.dto;


import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;

import java.math.BigDecimal;
import java.util.Date;

/**
 *产品规划信息维护结果dto
 *
 */

public class FinProductPlanningDto extends BaseEntity {
    /**
     * 按钮Id
     */
    private  String elementId;
    /**
     * 是否创新
     */
    private  String innovation;
    /**
     * 产品到期份额
     */
    private String productsDue;
    /**
     * 有效状态
     */
    private String validStatus;
    /**
     * 规划系列编号
     */
    private String  planningSeriesNo;

    /**
     * 规划系列名称
     */
    private  String planningSeriesName;
    /**
     * 基准日期  默认为平台日
     */
    private Date baseDate;
    /**
     * 规划编号
     */
    private String planningNo;
    /**
     * 规划开始日
     */
    private  Date  planningStartDate;

    /**
     * 规划结束日
     */
    private  Date  planningEndDate;
    /**
     * 规划到期日
     */
    private  Date actDate;
    /**
     * 规划初始值
     */

    protected BigDecimal initialPlanningValue;
    /**
     * 规划变动值
     */
    private BigDecimal planningChangeValue;
    /**
     * 规划目标值
     */
    private BigDecimal planningTargetValue;
    /**
     * 备注
     */
    private String remarks;

    public String getValidStatus() {
        return validStatus;
    }

    public void setValidStatus(String validStatus) {
        this.validStatus = validStatus;
    }

    public String getPlanningSeriesNo() {
        return planningSeriesNo;
    }

    public void setPlanningSeriesNo(String planningSeriesNo) {
        this.planningSeriesNo = planningSeriesNo;
    }

    public String getBaseDate() {
        return DateUtil.format(baseDate);
    }

    public void setBaseDate(Date baseDate) {
        this.baseDate = baseDate;
    }

    public String getPlanningStartDate() {
        return DateUtil.format(planningStartDate) ;
    }

    public void setPlanningStartDate(Date planningStartDate) {
        this.planningStartDate = planningStartDate;
    }

    public String getPlanningEndDate() {
        return DateUtil.format(planningEndDate);
    }

    public void setPlanningEndDate(Date planningEndDate) {
        this.planningEndDate = planningEndDate;
    }

    public String getPlanningSeriesName() {
        return planningSeriesName;
    }

    public void setPlanningSeriesName(String planningSeriesName) {
        this.planningSeriesName = planningSeriesName;
    }

    public Date getActDate() {
        return actDate;
    }

    public void setActDate(Date actDate) {
        this.actDate = actDate;
    }
    public BigDecimal getInitialPlanningValue() {
        if( StringUtil.isNotEmpty(initialPlanningValue)){
            return initialPlanningValue;
        }else{
            return  new BigDecimal("0");
        }
    }

    public String getElementId() {
        return elementId;
    }

    public void setElementId(String elementId) {
        this.elementId = elementId;
    }

    public String getPlanningNo() {
        return planningNo;
    }

    public void setPlanningNo(String planningNo) {
        this.planningNo = planningNo;
    }

    public void setInitialPlanningValue(BigDecimal initialPlanningValue) {
        this.initialPlanningValue = initialPlanningValue;
    }

    public BigDecimal getPlanningChangeValue() {
        return planningChangeValue;
    }

    public void setPlanningChangeValue(BigDecimal planningChangeValue) {
        this.planningChangeValue = planningChangeValue;
    }

    public BigDecimal getPlanningTargetValue() {
        return planningTargetValue;
    }

    public void setPlanningTargetValue(BigDecimal planningTargetValue) {
        this.planningTargetValue = planningTargetValue;
    }

    public String getInnovation() {
        return innovation;
    }

    public void setInnovation(String innovation) {
        this.innovation = innovation;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getProductsDue() {
        return productsDue;
    }

    public void setProductsDue(String productsDue) {
        this.productsDue = productsDue;
    }
}
