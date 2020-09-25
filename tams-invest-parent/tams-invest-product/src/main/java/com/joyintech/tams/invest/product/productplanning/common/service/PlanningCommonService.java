package com.joyintech.tams.invest.product.productplanning.common.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.common.base.entity.AssetClass;
import com.joyintech.tams.common.base.entity.AssetConfigMonDetail;
import com.joyintech.tams.common.base.entity.PrdPlanDetail;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.product.productplanning.common.dto.BondRepaymentAmountDto;
import com.joyintech.tams.invest.product.productplanning.common.dto.HoldPositionDto;
import com.joyintech.tams.invest.product.productplanning.common.dto.PlanningCommonDto;
import com.joyintech.tams.invest.product.productplanning.common.dto.TacticsOptimizeDto;
import com.joyintech.tams.invest.product.rddesign.feasibilityanalysis.dto.FinProductFeaAna002Dto;

/**
 * 产品规划共通Service
 */
@Service
public class PlanningCommonService extends BaseService<FinProductFeaAna002Dto> {

	// 系统已有设计系列
	public Page<PlanningCommonDto> existingDesignSeries(Map<String, Object> map) {
		Page<PlanningCommonDto> page = restTemplate.postForPage(
				ServiceApplication.PRODUCT_SERVICE_NAME + "/PlanningCommon/findPage", map,
				PlanningCommonDto.class);
		return page;
	}
	/**
	 * 持仓查询
	 * @param params :cdate 参数.基准日
	 */
	public List<HoldPositionDto> queryHoldPosition(Map<String,Object> params){
		return restTemplate.postForList(
				ServiceApplication.PRODUCT_SERVICE_NAME + "/PlanningCommon/queryHoldPosition",
				params,
				HoldPositionDto.class
		);
	}
	/**
	 * 资产分类查询
	 */
	public List<AssetClass> queryAssetType(){
		return restTemplate.postForList(
				ServiceApplication.PRODUCT_SERVICE_NAME + "/PlanningCommon/queryAssetType",
				null,
				AssetClass.class
		);
	}

	/**
	 * 战术配置优化查询
	 * @param params
	 * @return
	 */
	public List<TacticsOptimizeDto> queryTacticsOptimize(Map<String,Object> params){
		return restTemplate.postForList(
				ServiceApplication.PRODUCT_SERVICE_NAME + "/PlanningCommon/queryTacticsOptimize",
				params,
				TacticsOptimizeDto.class
		);
	}

	/**
	 * 债券还本到期金额
	 * @param paramMap
	 * @return
	 */
	public List<BondRepaymentAmountDto> getBondRepaymentAmount(Map<String, Object> paramMap){
		return restTemplate.postForList(
				ServiceApplication.PRODUCT_SERVICE_NAME + "/PlanningCommon/getBondRepaymentAmount",
				paramMap,
				BondRepaymentAmountDto.class
		);
	}

	/**
	 * 资产还本到期金额
	 * @param paramMap
	 * @return
	 */
	public List<AssetConfigMonDetail> getCurEndAmt(Map<String, Object> paramMap){
		return restTemplate.postForList(
				ServiceApplication.PRODUCT_SERVICE_NAME + "/PlanningCommon/getCurEndAmt",
				paramMap,
				AssetConfigMonDetail.class
		);
	}
	
	/**
	 * 存续产品系列规划信息查询
	 * @param paramMap
	 * @return
	 */
	public List<PrdPlanDetail> getPrdPlanInfoList(Map<String, Object> paramMap){
		return restTemplate.postForList(
				ServiceApplication.PRODUCT_SERVICE_NAME + "/PlanningCommon/getPrdPlanDetail",
				paramMap,
				PrdPlanDetail.class
		);
	}
	
	/**
	 * 投资策略查询
	 * @param paramMap
	 * @return
	 */
	public List<SelectOptionDto> getPtlInvStrategyList(Map<String, Object> paramMap){
		return restTemplate.postForList(
				ServiceApplication.PRODUCT_SERVICE_NAME + "/PlanningCommon/getPtlInvStrategyList",
				paramMap,
				SelectOptionDto.class
		);
	}
}
