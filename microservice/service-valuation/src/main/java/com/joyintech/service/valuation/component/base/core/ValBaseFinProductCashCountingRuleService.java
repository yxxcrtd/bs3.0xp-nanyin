/*
 * 文件名：BaseFinProductCashCountingRuleService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangliang
 * 修改时间：2019年1月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.valuation.component.base.core;

import java.util.List;

import com.joyintech.service.valuation.component.base.core.dto.ValBaseFinProductCashCountingRuleDto;
import com.joyintech.tams.common.base.entity.FinFloatCountingRule;


/**
 * 现金流计提规则
 * 
 * @author zhangliang
 * @version 1.0 2019年1月11日
 * @see ValBaseFinProductCashCountingRuleService
 * @since 1.0
 */
public interface ValBaseFinProductCashCountingRuleService {
	
	/**
     * 
     * 现金流计算新建
     * <功能详细描述>
     * @param FinFloatCountingRule
     * @return boolean
     * @see
     * @since 1.0
     */
    boolean insertFinFloatCountingRule(FinFloatCountingRule finFloatCountingRule);

    /**
     * 
     * 现金流计算新建
     * <功能详细描述>
     * @param FinFloatCountingRule List<FinFloatCountingRule>
     * @return boolean
     * @see
     * @since 1.0
     */
    boolean insert(List<FinFloatCountingRule> finFloatCountingRuleList);
    
    /**
     * 
     * 现金流计划查询
     * <功能详细描述>
     * @param baseFinProductCashCountingRuleDto BaseFinProductCashCountingRuleDto
     * @return List<FinFloatCountingRule>
     * @see
     * @since 1.0
     */
    List<FinFloatCountingRule> selectByBaseFinProductCashCountingRuleDto(ValBaseFinProductCashCountingRuleDto baseFinProductCashCountingRuleDto);
    
    /**
     * 
     * 现金流计划删除
     * <功能详细描述>
     * @param baseFinProductCashCountingRuleDto BaseFinProductCashCountingRuleDto
     * @return boolean
     * @see
     * @since 1.0
     */
    boolean deleteByBaseFinProductCashCountingRuleDto(ValBaseFinProductCashCountingRuleDto baseFinProductCashCountingRuleDto);
    
    /**
     * 
     * 现金流计划修改
     * <功能详细描述>
     * @param baseFinProductCashCountingRuleDto BaseFinProductCashCountingRuleDto
     * @return boolean
     * @see
     * @since 1.0
     */
    boolean updateBaseFinProductCashCountingRuleDto(ValBaseFinProductCashCountingRuleDto baseFinProductCashCountingRuleDto);
    
}
