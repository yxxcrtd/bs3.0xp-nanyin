/*
 * 文件名：CashFlowPaymentRules.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：xiexinting
 * 修改时间：2018年8月29日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.component.base.core;

import java.util.List;

import com.joyintech.tams.common.base.entity.FinCashPayRule;
import com.joyintech.tams.component.base.core.dto.BaseFinProductPayRuleDto;

/**
 * 金融产品现金流支付规则组件
 * 〈功能详细描述〉
 * @author xiexinting
 * @version 1.0 2018年8月29日
 * @see BaseFinProductPayRuleService
 * @since 1.0
 */
public interface BaseFinProductPayRuleService {

    /**
     * 
     * 新建金融产品支付规则
     * <功能详细描述>
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    boolean insert(List<FinCashPayRule> list);
    /**
     * 
     * 删除金融产品支付规则
     * <功能详细描述>
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    boolean delete(FinCashPayRule dto);
    /**
     * 
     * 修改金融产品支付规则
     * <功能详细描述>
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    boolean update(BaseFinProductPayRuleDto dto);
    /**
     * 
     * 查询金融产品支付规则
     * <功能详细描述>
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    List<FinCashPayRule> select(FinCashPayRule dto);
}
