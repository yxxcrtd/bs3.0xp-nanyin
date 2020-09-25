/*
 * 文件名：BaseMstFinExercisePlanService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangjun
 * 修改时间：2018年8月28日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.component.base.core;

import java.util.List;

import com.joyintech.tams.common.base.entity.FinExercisePlan;
import com.joyintech.tams.component.base.core.dto.BaseFinProductExercisePlanDto;


/**
 * 金融产品现金流行权计划组件Seivice
 * 〈功能详细描述〉
 * @author zhangjun
 * @version 1.0 2018年8月28日
 * @see BaseFinProductExercisePlanService
 * @since 1.0
 */
public interface BaseFinProductExercisePlanService {

   
    /**
     * 
     * 删除
     * <功能详细描述>
     * @param finExercisePlan FinExercisePlan
     * @return boolean
     * @see
     * @since 1.0
     */
    boolean deleteByKey(FinExercisePlan finExercisePlan);
    /**
     * 
     * 新增
     * <功能详细描述>
     * @param list List<FinExercisePlan>
     * @return boolean
     * @see
     * @since 1.0
     */
    boolean insert(List<FinExercisePlan> list);
    /**
     * 
     * 查询
     * <功能详细描述>
     * @param finExercisePlan FinExercisePlan
     * @return List
     * @see
     * @since 1.0
     */
    List<FinExercisePlan> select(FinExercisePlan finExercisePlan);
    /**
     * 修改
     * <功能详细描述>
     * @param dto BaseFinProductExercisePlanDto
     * @return boolean
     * @see
     * @since 1.0
     */
    boolean update(BaseFinProductExercisePlanDto dto);


}
