/*
 * 文件名：BaseMstFinCashPlanService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangjun
 * 修改时间：2018年8月28日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.component.base.core;

import java.util.List;
import com.joyintech.tams.common.base.entity.FinCashPlan;
import com.joyintech.tams.component.base.core.dto.BaseFinProductCashPayPlanDto;


/**
 * 金融产品现金流支付计划组件Service
 * 〈功能详细描述〉
 * @author zhangjun
 * @version 1.0 2018年8月28日
 * @see BaseFinProductCashPayPlanService
 * @since 1.0
 */
public interface BaseFinProductCashPayPlanService {

    /**
     * 
     * 删除金融产品现金流支付计划信息
     * <功能详细描述>
     * @param finCashPlan FinCashPlan
     * @return boolean
     * @see
     * @since 1.0
     */
    boolean deleteByPrimaryKey(FinCashPlan finCashPlan);
    /**
     * 
     * 新增金融产品现金流支付计划信息
     * <功能详细描述>
     * @param list List<FinCashPlan>
     * @return boolean
     * @see 
     * @since 1.0
     */

    boolean insertList(List<FinCashPlan> list);
    /**
     * 修改
     * <功能详细描述>
     * @param dto BaseFinProductCashPayPlanDto
     * @return boolean
     * @see
     * @since 1.0
     */
    boolean update(BaseFinProductCashPayPlanDto dto);
    /**
     * 查询金融产品现金流支付计划信息
     * <功能详细描述>
     * @param finCashPlan FinCashPlan
     * @return List<FinCashPlan>
     * @see
     * @since 1.0
     */
    List<FinCashPlan> select(FinCashPlan finCashPlan);
    //新增数据前根据现金流代码删除已有数据
    Boolean deleteByCashId(String cashId);
}
