/*
 * 文件名：BaseFinProductExercisePlanDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangjun
 * 修改时间：2018年9月3日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.valuation.component.base.core.dto;

import java.util.List;

import com.joyintech.tams.common.base.entity.FinExercisePlan;

/**
 * 金融产品现金流行权计划组件Dto
 * 〈功能详细描述〉
 * @author zhangjun
 * @version 1.0 2018年9月3日
 * @see ValBaseFinProductExercisePlanDto
 * @since 1.0
 */
public class ValBaseFinProductExercisePlanDto extends FinExercisePlan{

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;
    /**
     * 行权计划表的dto的list
     */
    private List<FinExercisePlan> finExercisePlanList;
    
    /**
     * 获取 行权计划表的dto的list
     * @return finExercisePlanList.
     */
    public List<FinExercisePlan> getFinExercisePlanList() {
        return finExercisePlanList;
    }
    
    /**
     * 设置 行权计划表的dto的list
     * @param finExercisePlanList finExercisePlanList
     */
    public void setFinExercisePlanList(List<FinExercisePlan> finExercisePlanList) {
        this.finExercisePlanList = finExercisePlanList;
    }
    
   
}
