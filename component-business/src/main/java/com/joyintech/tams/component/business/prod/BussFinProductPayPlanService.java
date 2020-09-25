/*
 * 文件名：BussFinProductPayPlanService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：何阳阳
 * 修改时间：2018年9月3日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.component.business.prod;

import java.util.List;

import com.joyintech.tams.common.base.entity.FinCashPlan;
import com.joyintech.tams.component.business.prod.dto.BussFinProductPayPlanDto;

/**
 * 付息计划组件接口
 * 
 * @author 何阳阳
 * @version 1.0 2018年9月3日
 * @see BussFinProductPayPlanService
 * @since 1.0
 */
public interface BussFinProductPayPlanService {

    /**
     * 默认实体名
     */
    String DEFAULT_ENTITY_NAME = "bussFinProductPayPlanDto";

    /**
     * 默认调用方式（产品）
     */
    String DEFAULT_USE_PLACE = "FinProductBuild002";

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
    List<FinCashPlan> getProductInterestPlanData(BussFinProductPayPlanDto inputDto);
    
    /**
     * 付息计划信息插入
     * @param payPlanDto 产品付息计划信息
     */
    void insert(BussFinProductPayPlanDto payPlanDto);

    /**
     * 付息计划信息更新
     * @param payPlanDto 产品付息计划信息
     */
    void update(BussFinProductPayPlanDto payPlanDto);

    /**
     * 付息计划信息删除
     * @param payPlanDto 产品付息计划信息
     */
    void delete(BussFinProductPayPlanDto payPlanDto);
}
