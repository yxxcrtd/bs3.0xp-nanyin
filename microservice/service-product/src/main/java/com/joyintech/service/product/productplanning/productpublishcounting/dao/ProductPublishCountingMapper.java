package com.joyintech.service.product.productplanning.productpublishcounting.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.product.productplanning.productpublishcounting.dto.ProductPublishCounting001Dto;
import com.joyintech.tams.common.base.entity.AssetPlanDetail;
/**
 * 产品发行规划试算mapper
 * @version 1.0 2018年12月18日
 * @see ProductPublishCountingMapper
 * @since 1.0
 */
@Repository
public interface ProductPublishCountingMapper {
	/**
     * 产品系列列表查询
     * @return
     */
    List<ProductPublishCounting001Dto> getPrdPage(Map<String,Object> params);
	/**
     * 资产大类列表查询
     * @return
     */
    List<ProductPublishCounting001Dto> getAssetPage(Map<String,Object> params);
	/**
     * 资产大类查询
     * @return
     */
    List<ProductPublishCounting001Dto> getAssetPlanList(Map<String,Object> params);
    /**
     * 查询策略明细
     * @return
     */
    List<ProductPublishCounting001Dto> getInvStrategyDetailList(String strategyId);
    /**
     * 查询产品发行规划信息
     * @return
     */
    ProductPublishCounting001Dto getPrdIssuePlanInfoByCode(String code);
    /**
     * 发行策略查询
     * @return
     */
    List<ProductPublishCounting001Dto> getPrdPlanStrategyListByCode(String code);
    /**
     * 发行规划资产大类查询
     * @return
     */
    List<ProductPublishCounting001Dto> getPrdPlanAssetListByCode(String code);
    /**
     * 
     * @return
     */
    int deleteIssueplanStrategyByCode(String code);
    /**
     * 
     * @return
     */
    int deleteStrategyDetailByCode(String code);
    /**
     * 
     * @return
     */
    int deleteIssueplanAssetByCode(String code);
    /**
     * 
     * @return
     */
    List<SelectOptionDto> getPrdIssueplanCode(Map<String,Object> params);
}
