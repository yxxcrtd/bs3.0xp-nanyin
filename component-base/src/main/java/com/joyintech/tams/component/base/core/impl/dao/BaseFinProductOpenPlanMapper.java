/*
 * 文件名：BaseFinProductOpenPlanMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：huwenbin
 * 修改时间：2018年9月2日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.component.base.core.impl.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.FinFloatCountingRule;
import com.joyintech.tams.common.base.entity.FinOpenPlan;
import com.joyintech.tams.component.base.core.dto.BaseFinProductOpenPlanDto;

/**
 * 开放计划组件
 * 〈功能详细描述〉
 * @author huwenbin
 * @version 1.0 2018年9月2日
 * @see BaseFinProductOpenPlanMapper
 * @since 1.0
 */
@Repository
public interface BaseFinProductOpenPlanMapper {
    /**
     * 
     * 开放计划批量新建
     * <功能详细描述>
     * @param item List<FinOpenPlan
     * @return int
     * @see
     * @since 1.0
     */
    int insertList(List<FinOpenPlan> item);
    /**
     * 
     * 开放计划单个新建
     * <功能详细描述>
     * @param finOpenPlan FinOpenPlan 
     * @return int
     * @see
     * @since 1.0
     */
    int insert(FinOpenPlan finOpenPlan);
    /**
     * 删除开放计划
     * <一句话功能简述>
     * <功能详细描述>
     * @param baseFinProductOpenPlanDto BaseFinProductOpenPlanDto
     * @return int
     * @see
     * @since 1.0
     */
    int deleteByFinOpenPlan(BaseFinProductOpenPlanDto baseFinProductOpenPlanDto);
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
    
    String selectfinCashInfo(Map<String,Object> map);
    
    int deleteFinFloatAccrualSche(Map<String,Object> map);
    
    List<FinFloatCountingRule> selectFinFloatCountingRule(Map<String,Object> map);
}
