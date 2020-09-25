/*
 * 文件名：BaseFinProductOpenPlanService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：huwenbin
 * 修改时间：2018年9月2日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.component.base.core;

import java.util.List;

import com.joyintech.tams.common.base.entity.FinOpenPlan;
import com.joyintech.tams.component.base.core.dto.BaseFinProductOpenPlanDto;

/**
 * 开放计划组件
 * 〈功能详细描述〉
 * @author huwenbin
 * @version 1.0 2018年9月2日
 * @see BaseFinProductOpenPlanService
 * @since 1.0
 */
public interface BaseFinProductOpenPlanService {
    
    /**
     * 
     * 开放计划新建
     * <功能详细描述>
     * @param finOpenPlanList List<FinOpenPlan>
     * @return boolean
     * @see
     * @since 1.0
     */
    boolean insert(List<FinOpenPlan>  finOpenPlanList);
    /**
     * 删除开放计划
     * <一句话功能简述>
     * <功能详细描述>
     * @param baseFinProductOpenPlanDto BaseFinProductOpenPlanDto
     * @return boolean
     * @see
     * @since 1.0
     */
    boolean deleteByFinOpenPlan(BaseFinProductOpenPlanDto baseFinProductOpenPlanDto);
    /**
     * 
     * 查询开放计划
     * <功能详细描述>
     * @param baseFinProductOpenPlanDto BaseFinProductOpenPlanDto
     * @return List<FinOpenPlan>
     * @see
     * @since 1.0
     */
    List<FinOpenPlan> selectByFinOpenPlan(BaseFinProductOpenPlanDto baseFinProductOpenPlanDto);
    
    /**
     * 修改开放计划
     * <一句话功能简述>
     * <功能详细描述>
     * @param baseFinProductOpenPlanDto BaseFinProductOpenPlanDto
     * @return boolean
     * @see
     * @since 1.0
     */
    boolean updataFinOpenPlan(BaseFinProductOpenPlanDto baseFinProductOpenPlanDto);
    
    /**
     * 修改浮动管理费计划
     * <一句话功能简述>
     * <功能详细描述>
     * @param baseFinProductOpenPlanDto BaseFinProductOpenPlanDto
     * @return boolean
     * @see
     * @since 1.0
     */
    boolean updateFinFloatAccrualSche(BaseFinProductOpenPlanDto baseFinProductOpenPlanDto, List<FinOpenPlan> finOpenPlanList);
    
}
