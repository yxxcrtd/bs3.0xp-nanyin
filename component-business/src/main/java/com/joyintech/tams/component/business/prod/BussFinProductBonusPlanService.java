/*
 * 文件名：BussFinProductBonusPlan.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：陈卓
 * 修改时间：2018年9月3日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.component.business.prod;

import java.util.List;

import com.joyintech.tams.common.base.entity.FinCashPlan;
import com.joyintech.tams.component.business.prod.dto.BussFinProductBonusPlanDto;
import com.joyintech.tams.component.business.prod.dto.BussFinProductPayPlanDto;

/**
 * 分红计划组件接口
 * @author 陈卓
 * @version 1.0 2018年9月3日
 * @see BussFinProductBonusPlanService
 * @since 1.0
 */
public interface BussFinProductBonusPlanService {
    
    /**
     * 默认entity名称
     */
    String DEFAULT_ENTITY_NAME = "bussFinProductBonusPlanDto";
    
    /**
     * 根据金融产品代码和生效日查询分红计划相关信息
     * 
     * @param finprodId 金融产品代码
     * @param effDate 生效日
     * @return 分红计划
     * @see
     * @since 1.0
     */
    BussFinProductBonusPlanDto queryBonusPlanInfoByFinprodId(String finprodId, String effDate);
   
    /**
     * 根据金融产品代码查询付息计划
     * 
     * @param finprodId 金融产品代码
     * @return 付息计划
     * @see
     * @since 1.0
     */
    List<FinCashPlan> queryPayPlanListByFinprodId(String finprodId);
    
    /**
     * 付息计划计算
     *
     * @param inputDto 付息计划计算
     * @return 付息计划结果list
     */
    List<FinCashPlan> getProductInterestPlanData(BussFinProductBonusPlanDto inputDto);
    
    /**
     * 根据金融产品代码，取得产品付息计划信息数据
     * @param finprodId 金融产品代码
     * @return {@link BussFinProductPayPlanDto}
     */
    /**
     * 
     * @param finprodId 金融产品代码
     * @param effDate 生效日
     * @return 付息计划信息数据
     * @see
     * @since 1.0
     */
    BussFinProductPayPlanDto queryPayPlanInfoByFinprodId(String finprodId, String effDate);
    


}
