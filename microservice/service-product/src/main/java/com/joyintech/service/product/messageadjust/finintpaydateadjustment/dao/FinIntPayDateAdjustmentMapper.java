/*
 * 文件名：FinIntPayDateAdjustmentMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：huwenbin
 * 修改时间：2018年10月23日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.product.messageadjust.finintpaydateadjustment.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.FinCashPlan;
import com.joyintech.tams.common.base.entity.FinProduct;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.product.messageadjust.finintpaydateadjustment.dto.FinIntPayDateAdjustmentDto;

/**
 * 信息调整管理_付息日调整 
 * 〈功能详细描述〉
 * @author huwenbin
 * @version 1.0 2018年10月23日
 * @see FinIntPayDateAdjustmentMapper
 * @since 1.0
 */
@Repository
public interface FinIntPayDateAdjustmentMapper {
    
    /**
     * 
     * 产品代码下拉框
     * <功能详细描述>
     * @param param Map<String, Object>
     * @return List<SelectOptionDto>
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> getCashId(Map<String, Object> param);
    
    /**
     * 
     * 页面查询
     * <功能详细描述>
     * @param param Map<String, Object>
     * @return List<FinIntPayDateAdjustmentDto>
     * @see
     * @since 1.0
     */
    List<FinIntPayDateAdjustmentDto> selectForPage(Map<String, Object> param);
    
    /**
     * 
     * 获得起息日与到期日
     * <功能详细描述>
     * @param param Map<String, Object>
     * @return FinProduct
     * @see
     * @since 1.0
     */
    FinProduct getVdateMdate(Map<String, Object> param);
    
    /**
     * 
     * 获取付息日计划详情
     * <功能详细描述>
     * @param param Map<String, Object>
     * @return List<FinCashPlan>
     * @see
     * @since 1.0
     */
    List<FinCashPlan> getFinCashPlan(Map<String, Object> param);

	String queryProductIsSus(Map<String, Object> param);

}
