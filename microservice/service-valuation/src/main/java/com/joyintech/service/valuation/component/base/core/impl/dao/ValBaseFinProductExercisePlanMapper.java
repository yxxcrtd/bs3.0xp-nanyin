/*
 * 文件名：BaseMstFinExercisePlanMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangjun
 * 修改时间：2018年8月28日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.valuation.component.base.core.impl.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.FinExercisePlan;

/**
 * 金融产品现金流行权计划组件mapper
 * 〈功能详细描述〉
 * @author zhangjun
 * @version 1.0 2018年8月28日
 * @see ValBaseFinProductExercisePlanMapper
 * @since 1.0
 */
@Repository
public interface ValBaseFinProductExercisePlanMapper {
    /**
     * 
     * 根据金融产品代码等四个条件删除金融产品现金流行权计划组件信息
     * <功能详细描述>
     * @param finExercisePlan FinExercisePlan
     * @return int
     * @see
     * @since 1.0
     */
    int deleteByKey(FinExercisePlan finExercisePlan);
    /**
     * 
     * 查询金融产品现金流行权计划组件信息
     * <功能详细描述>
     * @param finExercisePlan FinExercisePlan
     * @return List
     * @see
     * @since 1.0
     */
    List<FinExercisePlan> selectDetail(FinExercisePlan finExercisePlan);

}
