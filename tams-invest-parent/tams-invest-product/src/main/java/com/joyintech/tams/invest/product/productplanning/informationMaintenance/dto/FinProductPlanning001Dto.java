package com.joyintech.tams.invest.product.productplanning.informationMaintenance.dto;


import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.dto.ProcessEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 *产品规划信息维护结果dto
 *
 */

public class FinProductPlanning001Dto extends BaseEntity implements ProcessEntity {
    /**
     * 按钮Id
     */
    private  String elementId;
    /**
     * 产品编号
     */
    private String planningNo;
    /**
     * 产品到期份额
     */
    private String productsDue;
    /**
     * 是否创新
     */
    private  String innovation;
    /**
     * 是否创新
     */
    private  String innovationText;
    /**
     * 有效状态
     */
    private String validStatus;
    /**
     * 有效状态
     */
    private  String validStatusText;

    /**
     * 规划系列编号
     */
    private String  planningSeriesNo;
    /**
     * 规划设计名称
     */
    private  String planningSeriesName;
    /**
     * 基准日期  默认为平台日
     */
    private Date baseDate;

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
    private BigDecimal initialPlanningValue;
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
    /**
     * 操作类型
     */
    private String operateType;

    public String getElementId() {
        return elementId;
    }

    public void setElementId(String elementId) {
        this.elementId = elementId;
    }

    public String getInnovation() {
        return innovation;
    }

    public void setInnovation(String innovation) {
        this.innovation = innovation;
    }

    public String getValidStatus() {
        return validStatus;
    }

    public void setValidStatus(String validStatus) {
        this.validStatus = validStatus;
    }
    @PrimaryKey(PrimaryKey.Field.PK_2)
    public String getPlanningSeriesNo() {
        return planningSeriesNo;
    }

    public void setPlanningSeriesNo(String planningSeriesNo) {
        this.planningSeriesNo = planningSeriesNo;
    }

    public String getPlanningSeriesName() {
        return planningSeriesName;
    }

    public void setPlanningSeriesName(String planningSeriesName) {
        this.planningSeriesName = planningSeriesName;
    }

    public String getBaseDateStr() {
        return DateUtil.format(baseDate);
    }

    public void setBaseDate(Date baseDate) {
        this.baseDate = baseDate;
    }
    public Date getBaseDate() {
        return baseDate;
    }

    public String getPlanningStartDateStr() {
        return DateUtil.format(planningStartDate) ;
    }
    public Date getPlanningStartDate() {
        return planningStartDate ;
    }

    public void setPlanningStartDate(Date planningStartDate) {
        this.planningStartDate = planningStartDate;
    }

    public String getPlanningEndDateStr() {
        return DateUtil.format(planningEndDate);
    }

    public Date getPlanningEndDate() {
        return planningEndDate;
    }

    public void setPlanningEndDate(Date planningEndDate) {
        this.planningEndDate = planningEndDate;
    }

    public BigDecimal getInitialPlanningValue() {
        if( StringUtil.isNotEmpty(initialPlanningValue)){
            return initialPlanningValue;
        }else{
            return  new BigDecimal("0");
        }
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

    public String getProductsDue() {
        return productsDue;
    }

    public void setProductsDue(String productsDue) {
        this.productsDue = productsDue;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }
    /**
     * 获取 validStatusText
     *
     * @return validStatusText.
     */
    public String getValidStatusText() {
        validStatusText = SysDataUtil.getNameFromDataDict(DictCode.有效状态, validStatus);
        return validStatusText;
    }
    /**
     * 设置 validStatusText
     *
     * @param validStatusText validStatusText
     */
    public void setValidStatusText(String validStatusText) {
        this.validStatusText = validStatusText;
    }

    public String getPlanningNo() {
        return planningNo;
    }

    public void setPlanningNo(String planningNo) {
        this.planningNo = planningNo;
    }

    /**
     * 获取innovationText
     * @return
     */
    public String getInnovationText(){
        if("N".equals(innovation)){
            innovationText="否";
        }
        if("Y".equals(innovation)){
            innovationText="是";
        }
        return innovationText;
    }

    /**
     * 设置innovationText
     * @param innovationText
     */
    public void setInnovationText(String innovationText ){
        this.innovationText=innovationText;
    }

    public Date getActDate() {
        return actDate;
    }

    public void setActDate(Date actDate) {
        this.actDate = actDate;
    }
}
