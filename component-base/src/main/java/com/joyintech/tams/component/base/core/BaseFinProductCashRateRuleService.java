/*
 * 文件名：BaseFinProductCashRateRuleService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：huwenbin
 * 修改时间：2018年9月2日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.component.base.core;

import java.util.List;

import com.joyintech.tams.common.base.entity.FinRateInfo;
import com.joyintech.tams.component.base.core.dto.BaseFinProductCashRateRuleDto;
import com.joyintech.tams.common.base.entity.FinCashInfo;


/**
 * 现金流业务信息组件
 * 〈功能详细描述〉
 * @author huwenbin
 * @version 1.0 2018年9月2日
 * @see BaseFinProductCashRateRuleService
 * @since 1.0
 */
public interface BaseFinProductCashRateRuleService {
    
    /**
     * 
     * 现金流利率信息新建
     * <功能详细描述>
     * @param finRateInfoList List<FinCashInfo>
     * @return boolean
     * @see
     * @since 1.0
     */
    boolean insert(List<FinRateInfo> finRateInfoList);
    
    /**
     * 
     * 现金流利率信息查询
     * <功能详细描述>
     * @param baseFinProductCashRateRuleDto BaseFinProductCashRateRuleDto
     * @return List<BaseFinProductCashRateRuleDto>
     * @see
     * @since 1.0
     */
    List<FinRateInfo> selectByBaseFinProductCashRateRuleDto(BaseFinProductCashRateRuleDto baseFinProductCashRateRuleDto);
    
    /**
     * 
     * 现金流利率信息删除
     * <功能详细描述>
     * @param baseFinProductCashRateRuleDto BaseFinProductCashRateRuleDto
     * @return int
     * @see
     * @since 1.0
     */
    boolean deleteByBaseFinProductCashRateRuleDto(BaseFinProductCashRateRuleDto baseFinProductCashRateRuleDto);
    
    /**
     * 
     * <一句话功能简述>
     * <功能详细描述>
     * @param baseFinProductCashRateRuleDto BaseFinProductCashRateRuleDto
     * @return boolean
     * @see
     * @since 1.0
     */
    boolean updateBaseFinProductCashRateRuleDto(BaseFinProductCashRateRuleDto baseFinProductCashRateRuleDto);
}
