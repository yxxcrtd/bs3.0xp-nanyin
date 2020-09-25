/*
 * 文件名：BaseFinProductOpenPlanService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：huwenbin
 * 修改时间：2018年9月2日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.valuation.component.base.core;

import java.util.List;

import com.joyintech.service.valuation.component.base.core.dto.ValBaseFinProductOpenPlanDto;
import com.joyintech.tams.common.base.entity.FinOpenPlan;


/**
 * 开放计划组件
 * 〈功能详细描述〉
 * @author huwenbin
 * @version 1.0 2018年9月2日
 * @see ValBaseFinProductOpenPlanService
 * @since 1.0
 */
public interface ValBaseFinProductOpenPlanService {
    
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
    boolean deleteByFinOpenPlan(ValBaseFinProductOpenPlanDto baseFinProductOpenPlanDto);
    /**
     * 
     * 查询开放计划
     * <功能详细描述>
     * @param baseFinProductOpenPlanDto BaseFinProductOpenPlanDto
     * @return List<FinOpenPlan>
     * @see
     * @since 1.0
     */
    List<FinOpenPlan> selectByFinOpenPlan(ValBaseFinProductOpenPlanDto baseFinProductOpenPlanDto);
    
    /**
     * 修改开放计划
     * <一句话功能简述>
     * <功能详细描述>
     * @param baseFinProductOpenPlanDto BaseFinProductOpenPlanDto
     * @return boolean
     * @see
     * @since 1.0
     */
    boolean updataFinOpenPlan(ValBaseFinProductOpenPlanDto baseFinProductOpenPlanDto);
    
    /**
     * 修改浮动管理费计划
     * <一句话功能简述>
     * <功能详细描述>
     * @param baseFinProductOpenPlanDto BaseFinProductOpenPlanDto
     * @return boolean
     * @see
     * @since 1.0
     */
    boolean updateFinFloatAccrualSche(ValBaseFinProductOpenPlanDto baseFinProductOpenPlanDto, List<FinOpenPlan> finOpenPlanList);
    
}
