package com.joyintech.service.product.productplanning.common.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.product.productplanning.common.dto.BondRepaymentAmountDto;
import com.joyintech.service.product.productplanning.common.dto.HoldPositionDto;
import com.joyintech.service.product.productplanning.common.dto.PlanningCommonDto;
import com.joyintech.service.product.productplanning.common.dto.TacticsOptimizeDto;
import com.joyintech.tams.common.base.entity.AssetClass;
import com.joyintech.tams.common.base.entity.AssetConfigMonDetail;
import com.joyintech.tams.common.base.entity.AssetPlanInfo;
import com.joyintech.tams.common.base.entity.PrdPlanDetail;
import com.joyintech.tams.common.base.entity.PrdPlanInfo;
import com.joyintech.tams.common.base.entity.PtlInvStrategy;

@Repository
public interface PlanningCommonMapper {
    /**
     * 持仓查询
     * @param params :cdate 参数.基准日
     * @return
     */
    List<HoldPositionDto> queryHoldPosition(Map<String,Object> params);

    /**
     * 资产分类查询
     * @return
     */
    List<AssetClass>selectAllAssetClass();

    /**
     * 战术配置优化查询
     * @param params :vdate 参数.开始日
     *               :edate 参数.结束日
     * @return
     */
    List<TacticsOptimizeDto>queryTacticsOptimize(Map<String,Object> params);

    /**
     * 产品规划信息查询
     * @param params :vdate 参数.开始日
     *               :edate 参数.结束日
     * @return
     */
    List<PrdPlanInfo>queryProductPlan(Map<String,Object> params);

    /**
     * 资产规划信息查询
     * @param params :vdate 参数.开始日
     *               :edate 参数.结束日
     * @return
     */
    List<AssetPlanInfo>quertAssetPlan(Map<String,Object> params);

    /**
     * 规划策略查询
     * @param params
     * @return
     */
    List<PtlInvStrategy>queryPlanStrategy(Map<String,Object> params);

    /**
     * 债券还本到期金额
     * @return
     */
    List<BondRepaymentAmountDto> getBondRepaymentAmount(Map<String,Object> params);


	List<PlanningCommonDto> planningCommonQuery(Map<String, Object> paramMap);

    /**
     * 资产还本到期金额
     * @param map
     * @return
     */
	List<AssetConfigMonDetail> getCurEndAmt(Map<String, Object> map);
	/**
     * 存续产品系列规划信息查询
     * @param map
     * @return
     */
	List<PrdPlanDetail> getPrdPlanDetail(Map<String, Object> map);
	
	/**
     * 存续产品系列规划信息查询
     * @param map
     * @return
     */
	List<SelectOptionDto> getPtlInvStrategyList(Map<String, Object> map);
}
