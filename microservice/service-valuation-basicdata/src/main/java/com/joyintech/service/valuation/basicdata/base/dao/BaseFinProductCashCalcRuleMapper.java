/*
 * 文件名：CashFlowCalculateMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：huwenbin
 * 修改时间：2018年8月29日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.valuation.basicdata.base.dao;

import com.joyintech.service.valuation.basicdata.base.dto.BaseFinProductCashCalcRuleDto;
import com.joyintech.tams.common.base.entity.FinCashCalcRule;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author huwenbin
 * @version 1.0 2018年8月29日
 * @see BaseFinProductCashCalcRuleMapper
 * @since 1.0
 */
@Repository
public interface BaseFinProductCashCalcRuleMapper {
    /**
     * 
     * 现金流计算新建
     * <功能详细描述>
     * @param finCashCalcRule FinCashCalcRule
     * @return int
     * @see
     * @since 1.0
     */
    int insert(FinCashCalcRule finCashCalcRule);
    
    /**
     * 
     * 现金流计划查询
     * <功能详细描述>
     * @param baseFinProductCashCalcRuleDto BaseFinProductCashCalcRuleDto
     * @return List<BaseFinProductCashCalcRuleDto>
     * @see
     * @since 1.0
     */
    List<FinCashCalcRule> selectByBaseFinProductCashCalcRuleDto(BaseFinProductCashCalcRuleDto baseFinProductCashCalcRuleDto);
    
    /**
     * 
     * 现金流计划删除
     * <功能详细描述>
     * @param baseFinProductCashCalcRuleDto BaseFinProductCashCalcRuleDto
     * @return int
     * @see
     * @since 1.0
     */
    int deleteByBaseFinProductCashCalcRuleDto(BaseFinProductCashCalcRuleDto baseFinProductCashCalcRuleDto);

    /**
     * 根据现金流ID及产品代码删除初始利率
     * @param baseFinProductCashCalcRuleDto
     * @return
     */
    int deleteBaseFinProductCashCalcRuleByCondition(BaseFinProductCashCalcRuleDto baseFinProductCashCalcRuleDto);
}
