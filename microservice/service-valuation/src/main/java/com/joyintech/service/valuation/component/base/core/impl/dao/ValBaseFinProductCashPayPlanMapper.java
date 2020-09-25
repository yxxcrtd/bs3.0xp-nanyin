/*
 * 文件名：BaseMstFinCashPlanMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangjun
 * 修改时间：2018年8月28日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.valuation.component.base.core.impl.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.FinCashPlan;

/**
 * 金融产品现金流支付计划组件mapper
 * 〈功能详细描述〉
 * @author zhangjun
 * @version 1.0 2018年8月28日
 * @see ValBaseFinProductCashPayPlanMapper
 * @since 1.0
 */
@Repository
public interface ValBaseFinProductCashPayPlanMapper {
    /**
     * 
     * 删除金融产品现金流支付计划信息
     * <功能详细描述>
     * @param  finCashPlan FinCashPlan
     * @return int
     * @see
     * @since 1.0
     */
    int deleteByPrimaryKey(FinCashPlan finCashPlan);
    /**
     * 
     * 查询金融产品现金流支付计划信息
     * <功能详细描述>
     * @param dto BaseFinProductCashPayPlanDto
     * @return List
     * @see
     * @since 1.0
     */
    List<FinCashPlan> selectDetail(FinCashPlan finCashPlan);
    //新增数据前根据现金流代码删除已有数据
    Boolean deleteByCashId(String cashId);
}
