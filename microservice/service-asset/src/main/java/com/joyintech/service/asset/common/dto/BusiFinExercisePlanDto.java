/*
 * 文件名：BusiFinExercisePlanDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年10月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.asset.common.dto;

import java.util.List;

import com.joyintech.tams.common.base.entity.FinExercisePlan;
import com.joyintech.framework.base.BaseEntity;

/**
 *  行权计划
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2018年10月11日
 * @see BusiFinExercisePlanDto
 * @since 1.0
 */
public class BusiFinExercisePlanDto extends BaseEntity{

   /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;
    
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
   
}
