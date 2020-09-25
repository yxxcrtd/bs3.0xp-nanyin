/*
 * 文件名：CashFlowCalculate.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：huwenbin
 * 修改时间：2018年8月29日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.valuation.component.base.core;

import java.util.List;

import com.joyintech.service.valuation.component.base.core.dto.ValBaseFinProductCashCalcRuleDto;
import com.joyintech.tams.common.base.entity.FinCashCalcRule;


/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author huwenbin
 * @version 1.0 2018年8月29日
 * @see ValBaseFinProductCashCalcRuleService
 * @since 1.0
 */
public interface ValBaseFinProductCashCalcRuleService {
    
    /**
     * 
     * 现金流计算新建
     * <功能详细描述>
     * @param baseFinProductCashCalcRuleList List<BaseFinProductCashCalcRuleDto>
     * @return boolean
     * @see
     * @since 1.0
     */
    boolean insert(List<FinCashCalcRule> finCashCalcRuleList);
    
    /**
     * 
     * 现金流计划查询
     * <功能详细描述>
     * @param baseFinProductCashCalcRuleDto BaseFinProductCashCalcRuleDto
     * @return List<FinCashCalcRule>
     * @see
     * @since 1.0
     */
    List<FinCashCalcRule> selectByBaseFinProductCashCalcRuleDto(ValBaseFinProductCashCalcRuleDto baseFinProductCashCalcRuleDto);
    
    /**
     * 
     * 现金流计划删除
     * <功能详细描述>
     * @param baseFinProductCashCalcRuleDto BaseFinProductCashCalcRuleDto
     * @return boolean
     * @see
     * @since 1.0
     */
    boolean deleteByBaseFinProductCashCalcRuleDto(ValBaseFinProductCashCalcRuleDto baseFinProductCashCalcRuleDto);
    
    /**
     * 
     * 现金流计划修改
     * <功能详细描述>
     * @param baseFinProductCashCalcRuleDto BaseFinProductCashCalcRuleDto
     * @return boolean
     * @see
     * @since 1.0
     */
    boolean updateBaseFinProductCashCalcRuleDto(ValBaseFinProductCashCalcRuleDto baseFinProductCashCalcRuleDto);
}
