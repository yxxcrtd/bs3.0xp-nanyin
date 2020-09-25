/*
 * 文件名：FinProductPayPlanMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangliang
 * 修改时间：2018年10月25日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.product.common.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.FinCashPlan;
import com.joyintech.service.product.common.dto.BussFinProductPayPlanDto;

/**
 * 付息信息组件
 * 
 * @author zhangliang
 * @version 1.0 2018年10月25日
 * @see FinProductPayPlanMapper
 * @since 1.0
 */
@Repository
public interface FinProductPayPlanMapper {

    
    /**
     * 查询付息计划相关信息
     * @param map 参数
     * @return 付息计划相关信息
     */
    BussFinProductPayPlanDto queryPayPlanInfoByFinprodId(Map<String, Object> map);
    
    /**
     * 查询付息计划相关信息
     * @param map 参数
     * @return 付息计划相关信息
     */
    List<FinCashPlan> queryPayPlanListByFinprodId(Map<String, Object> map);
    
}
