package com.joyintech.service.product.productplanning.informationMaintenance.dto;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.tams.common.util.BigDecimalSerializer;

import java.math.BigDecimal;
import java.util.Date;

/**
 *产品规划信息维护结果dto
 *
 */
public class FinProductPlanning2DetailDto extends BaseEntity{
    /**
     * 产品到期份额
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private  BigDecimal productsDue;
    /**
     * 是否创新
     */
    private  String innovation;
    /**
     * 实际到期日
     */
    private String actDate;
    /**
     * 是否创新
     */
    private  String innovationText;
    /**
     * 规划编号
     */
    private String planningNo;
    /**
     * 规划系列编号
     */
    private String  planningSeriesNo;
    /**
     * 规划设计名称
     */
    private  String planningSeriesName;
    /**
     * 规划初始值
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal initialPlanningValue;
    /**
     * 规划变动值
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal planningChangeValue;
    /**
     * 规划目标值
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal planningTargetValue;
    /**
     * 备注
     */
//    private String remarks;
    /**
     * 创建人
     */
    private String createUser;
    /**
     * 创建部门
     */
    private String createDept;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新人
     */
    private String updateUser;
    /**
     * 更新时间
     */
    private Date updateTime;

    public String getActDate() {
        return actDate;
    }

    public void setActDate(String actDate) {
        this.actDate = actDate;
    }

    public BigDecimal getProductsDue() {
        return productsDue;
    }

    public void setProductsDue(BigDecimal productsDue) {
        this.productsDue = productsDue;
    }

    public String getInnovation() {
        return innovation;
    }

    public void setInnovation(String innovation) {
        this.innovation = innovation;
    }

    public String getInnovationText() {
        return innovationText;
    }

    public void setInnovationText(String innovationText) {
        this.innovationText = innovationText;
    }

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

    public BigDecimal getInitialPlanningValue() {
        return initialPlanningValue;
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

//    public String getRemarks() {
//        return remarks;
//    }
//
//    public void setRemarks(String remarks) {
//        this.remarks = remarks;
//    }

    public String getPlanningNo() {
        return planningNo;
    }

    public void setPlanningNo(String planningNo) {
        this.planningNo = planningNo;
    }

    @Override
    public String getCreateUser() {
        return createUser;
    }

    @Override
    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    @Override
    public String getCreateDept() {
        return createDept;
    }

    @Override
    public void setCreateDept(String createDept) {
        this.createDept = createDept;
    }

    @Override
    public Date getCreateTime() {
        return createTime;
    }

    @Override
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String getUpdateUser() {
        return updateUser;
    }

    @Override
    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    @Override
    public Date getUpdateTime() {
        return updateTime;
    }

    @Override
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
