/*
 * 文件名：BaseFinProductCashResetPlanService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：admin
 * 修改时间：2019年7月18日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.component.base.core;

import java.util.List;

import com.joyintech.tams.common.base.entity.FinCashResetPlan;

/**
 * 现金流利率重置计划业务信息组件
 * 〈功能详细描述〉
 * @author qiaominghui
 * @version 1.0 2019年7月18日
 * @see BaseFinProductCashResetPlanService
 * @since 1.0
 */
public interface BaseFinProductCashResetPlanService {
	/**
     * 
     * 现金流利率重重计划新建
     * <功能详细描述>
     * @param finCashResetPlanList List<FinCashResetPlan>
     * @return boolean
     * @see
     * @since 1.0
     */
    boolean insertList(List<FinCashResetPlan> finCashResetPlanList);
    
}
