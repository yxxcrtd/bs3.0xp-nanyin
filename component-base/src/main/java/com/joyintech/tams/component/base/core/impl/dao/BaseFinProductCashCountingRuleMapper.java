/*
 * 文件名：CashFlowCountingulateMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：huwenbin
 * 修改时间：2018年8月29日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.component.base.core.impl.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.FinFloatCountingRule;
import com.joyintech.tams.component.base.core.dto.BaseFinProductCashCountingRuleDto;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author huwenbin
 * @version 1.0 2018年8月29日
 * @see BaseFinProductCashCountingRuleMapper
 * @since 1.0
 */
@Repository
public interface BaseFinProductCashCountingRuleMapper {
    /**
     * 
     * 现金流计算新建
     * <功能详细描述>
     * @param finFloatCountingRule finFloatCountingRule
     * @return int
     * @see
     * @since 1.0
     */
    int insert(FinFloatCountingRule finFloatCountingRule);
    
    /**
     * 
     * 现金流计划查询
     * <功能详细描述>
     * @param baseFinProductCashCountingRuleDto BaseFinProductCashCountingRuleDto
     * @return List<BaseFinProductCashCountingRuleDto>
     * @see
     * @since 1.0
     */
    List<FinFloatCountingRule> selectByBaseFinProductCashCountingRuleDto(BaseFinProductCashCountingRuleDto baseFinProductCashCountingRuleDto);
    
    /**
     * 
     * 现金流计划删除
     * <功能详细描述>
     * @param baseFinProductCashCountingRuleDto BaseFinProductCashCountingRuleDto
     * @return int
     * @see
     * @since 1.0
     */
    int deleteByBaseFinProductCashCountingRuleDto(BaseFinProductCashCountingRuleDto baseFinProductCashCountingRuleDto);
}
