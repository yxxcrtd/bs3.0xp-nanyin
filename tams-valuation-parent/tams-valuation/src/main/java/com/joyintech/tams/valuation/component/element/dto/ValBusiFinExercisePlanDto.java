/*
 * 文件名：ValBusiFinExercisePlanDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年10月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.valuation.component.element.dto;

import java.util.Date;
import java.util.List;

import com.joyintech.tams.common.base.entity.FinCashPlan;
import com.joyintech.tams.common.base.entity.FinExercisePlan;
import com.joyintech.tams.valuation.component.element.validator.ValBusiFinExercisePlanValidator;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;

/**
 *  行权计划
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2018年10月11日
 * @see ValBusiFinExercisePlanDto
 * @since 1.0
 */
@ValidLogic(targetClass = ValBusiFinExercisePlanValidator.class,groups = {ValidateGroupEdit.class,ValidateGroupDelete.class})
public class ValBusiFinExercisePlanDto extends BaseEntity{

   /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;
    /**
     *资产到期日
     */
    private Date mdate;
    
    /**
     * 支付计划
     */
    private List<FinCashPlan> finCashPlanList;
    private List<FinExercisePlan> finExercisePlanList;
    
    
    /**
     * 获取 finExercisePlanList
     * @return finExercisePlanList.
     */
    public List<FinExercisePlan> getFinExercisePlanList() {
        return finExercisePlanList;
    }
    
    
    /**
     * 设置 finExercisePlanList
     * @param finExercisePlanList finExercisePlanList
     */
    public void setFinExercisePlanList(List<FinExercisePlan> finExercisePlanList) {
        this.finExercisePlanList = finExercisePlanList;
    }


    
    /**
     * 获取 mdate
     * @return mdate.
     */
    public Date getMdate() {
        return mdate;
    }


    
    /**
     * 设置 mdate
     * @param mdate mdate
     */
    public void setMdate(Date mdate) {
        this.mdate = mdate;
    }


    
    /**
     * 获取 finCashPlanList
     * @return finCashPlanList.
     */
    public List<FinCashPlan> getFinCashPlanList() {
        return finCashPlanList;
    }


    
    /**
     * 设置 finCashPlanList
     * @param finCashPlanList finCashPlanList
     */
    public void setFinCashPlanList(List<FinCashPlan> finCashPlanList) {
        this.finCashPlanList = finCashPlanList;
    }
   
}
