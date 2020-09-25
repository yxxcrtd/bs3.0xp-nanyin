/*
 * 文件名：FinAssetConfigPlanMapper.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhouyongjun
 * 修改时间：2019年12月26日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.service.product.productplanning.asset.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import com.joyintech.service.product.productplanning.asset.dto.FinAssetClassDto;
import com.joyintech.service.product.productplanning.asset.dto.FinAssetConfigPlanDto;
import com.joyintech.service.product.productplanning.asset.dto.FinTacticConfigOptDto;
import com.joyintech.tams.common.base.entity.AssetConfigDetail;
import com.joyintech.tams.common.base.entity.AssetConfigMonDetail;
import com.joyintech.tams.common.base.entity.AssetConfigPlan;

/**
 * 资产配置
 * 
 * @author zhouyongjun
 * @version 1.0 2019年12月26日
 * @see FinAssetConfigPlanMapper
 * @since 1.0
 */
@Repository
public interface FinAssetConfigPlanMapper {
    
    /**
     * 得到资产配置计划列表
     *
     * @param Map<String, Object> pageParam
     * @return List<FinAssetConfigPlanDto>结果
     * @see
     * @since 1.0
     */
    List<FinAssetConfigPlanDto> queryAssetConfigPlanList(Map<String, Object> map);

    /**
     * 调用【资产分类查询】
     *
     * @return List<FinAssetClassDto>结果
     * @see
     * @since 1.0
     */
	List<FinAssetClassDto> queryAssetClassList();

    /**
     * 得到资产分类明细
     *
     * @param Map<String, Object> param
     * @return List<AssetConfigDetail>结果
     * @see
     * @since 1.0
     */
	List<AssetConfigDetail> queryAssetConfigDetails(Map<String, Object> map);
	
    /**
     * 得到资产配置计划列表
     *
     * @param Map<String, Object> param
     * @return List<AssetConfigPlan>结果
     * @see
     * @since 1.0
     */
	List<AssetConfigPlan> queryAssetConfigPlan(Map<String, Object> map);
    /**
     * 得到资产配置计划列表
     *
     * @param Map<String, Object> pageParam
     * @return List<FinAssetConfigPlanDto>结果
     * @see
     * @since 1.0
     */
	List<AssetConfigMonDetail> queryAssetConfigMons(Map<String, Object> map);
	
    /**
     * 得到战术配置列表
     *
     * @param Map<String, Object> param
     * @return List<FinTacticConfigOptDto>结果
     * @see
     * @since 1.0
     */
	List<FinTacticConfigOptDto> queryTactics(Map<String, Object> map);
}
