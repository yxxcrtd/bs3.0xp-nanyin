/*
 * 文件名：CashFlowCountingulateMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：huwenbin
 * 修改时间：2018年8月29日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.valuation.component.base.core.impl.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.joyintech.service.valuation.component.base.core.dto.ValBaseFinProductCashCountingRuleDto;
import com.joyintech.tams.common.base.entity.FinFloatCountingRule;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author huwenbin
 * @version 1.0 2018年8月29日
 * @see ValBaseFinProductCashCountingRuleMapper
 * @since 1.0
 */
@Repository
public interface ValBaseFinProductCashCountingRuleMapper {
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
    List<FinFloatCountingRule> selectByBaseFinProductCashCountingRuleDto(ValBaseFinProductCashCountingRuleDto baseFinProductCashCountingRuleDto);
    
    /**
     * 
     * 现金流计划删除
     * <功能详细描述>
     * @param baseFinProductCashCountingRuleDto BaseFinProductCashCountingRuleDto
     * @return int
     * @see
     * @since 1.0
     */
    int deleteByBaseFinProductCashCountingRuleDto(ValBaseFinProductCashCountingRuleDto baseFinProductCashCountingRuleDto);
}
