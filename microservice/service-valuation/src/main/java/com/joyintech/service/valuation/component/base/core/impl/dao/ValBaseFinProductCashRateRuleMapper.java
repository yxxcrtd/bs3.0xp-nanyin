/*
 * 文件名：BaseFinProductCashRateRuleMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：huwenbin
 * 修改时间：2018年9月2日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.valuation.component.base.core.impl.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.joyintech.service.valuation.component.base.core.dto.ValBaseFinProductCashRateRuleDto;
import com.joyintech.tams.common.base.entity.FinRateInfo;


/**
 * 现金流利率规则信息组件
 * 〈功能详细描述〉
 * @author huwenbin
 * @version 1.0 2018年9月2日
 * @see ValBaseFinProductCashRateRuleMapper
 * @since 1.0
 */
@Repository
public interface ValBaseFinProductCashRateRuleMapper {
    
    /**
     * 
     * 现金流利率信息新建
     * <功能详细描述>
     * @param baseFinProductCashRateRuleDto BaseFinProductCashRateRuleDto
     * @return int
     * @see
     * @since 1.0
     */
    int insert(FinRateInfo finRateInfo);
    
    /**
     * 
     * 现金流利率信息查询
     * <功能详细描述>
     * @param baseFinProductCashRateRuleDto BaseFinProductCashRateRuleDto
     * @return List<BaseFinProductCashRateRuleDto>
     * @see
     * @since 1.0
     */
    List<FinRateInfo> selectByBaseFinProductCashRateRuleDto(ValBaseFinProductCashRateRuleDto baseFinProductCashRateRuleDto);
    
    /**
     * 
     * 现金流利率信息删除
     * <功能详细描述>
     * @param baseFinProductCashRateRuleDto BaseFinProductCashRateRuleDto
     * @return int
     * @see
     * @since 1.0
     */
    int deleteByBaseFinProductCashRateRuleDto(ValBaseFinProductCashRateRuleDto baseFinProductCashRateRuleDto);

}
