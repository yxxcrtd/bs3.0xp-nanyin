package com.joyintech.tams.valuation.assets.posit.dao;

import java.util.List;
import java.util.Map;

import com.joyintech.tams.valuation.assets.posit.dto.DateTableDto;
import com.joyintech.tams.valuation.assets.posit.dto.ValDespositAssetQuery002Dto;
import com.joyintech.tams.common.base.entity.FinCashCalcRule;
import com.joyintech.tams.common.base.entity.FinCashInfo;
import com.joyintech.tams.common.base.entity.FinCashPayRule;
import com.joyintech.tams.common.base.entity.FinCashPlan;
import com.joyintech.tams.common.base.entity.FinProduct;
import com.joyintech.tams.common.base.entity.FinProductType;

/**
 * 存款类资产查询
 * 
 * @author wangtingting
 * @version 1.0 2018年7月19日
 * @see ValDespositAssetQuery002Mapper
 * @since 1.0
 */
public interface ValDespositAssetQuery002Mapper {

	/**
	 * 根据查询finprodId单个详细信息
	 * 
	 * @param map
	 *            Map<String, Object>
	 * @return ValDespositAssetQuery002Dto
	 * @see
	 * @since 1.0
	 */
	ValDespositAssetQuery002Dto selectByfinprodId(Map<String, Object> map);

	/**
	 * 根据查询finprodId 利率调整
	 * 
	 * @param map
	 * @return
	 * @see
	 * @since 1.0
	 */
	// List<ValIntRateAdjustmentDto>
	// selectIntRateAdjustmentByFinprodId(Map<String, Object> map);
	/**
	 * 根据查询finprodId 付息计划表
	 * 
	 * @param map
	 * @return
	 * @see
	 * @since 1.0
	 */

	// List<ValPayIntPlanDto> selectValPayIntPlanDtoByFinprodId(Map<String,
	// Object> map);
	/**
	 * 初始化利率表
	 * 
	 * @param finprodId
	 *            finprodId
	 * @return List<DateTableDto>
	 * @see
	 * @since 1.0
	 */
	List<DateTableDto> getTable1(String finprodId);

	/**
	 * 初始化付息计划表
	 * 
	 * @param finprodId
	 *            finprodId
	 * @return List<DateTableDto>
	 * @see
	 * @since 1.0
	 */
	List<DateTableDto> getTable2(Map<String, Object> map);// modify by dongjun
															// on 20181203

	/**
	 * 初始化还本计划表
	 * 
	 * @param finprodId
	 *            finprodId
	 * @return List<DateTableDto>
	 * @see
	 * @since 1.0
	 */
	List<DateTableDto> getTable3(Map<String, Object> map);// modify by dongjun
															// on 20181203

	/**
	 * checkFinprodId
	 * 
	 * @param map
	 *            Map<String,Object>
	 * @return boolean
	 * @see
	 * @since 1.0
	 */
	boolean checkFinprodId(Map<String, Object> map);

	/**
	 * 更新FinProduct
	 * 
	 * @param dto
	 *            FinProduct
	 * @return int
	 * @see
	 * @since 1.0
	 */
	// 标的类金融产品表到期赎回价字段删除
	// int updateFinProduct(FinProduct dto);

	/**
	 * 更新FinProductType
	 * 
	 * @param dto
	 *            FinProductType
	 * @return int
	 * @see
	 * @since 1.0
	 */
	int updateFinProductType(FinProductType dto);

	/**
	 * 更新FinCashInfo
	 * 
	 * @param dto
	 *            FinCashInfo
	 * @return int
	 * @see
	 * @since 1.0
	 */
	int updateFinCashInfo(FinCashInfo dto);

	/**
	 * 更新FinCashCalcRule
	 * 
	 * @param dto
	 *            FinCashCalcRule
	 * @return int
	 * @see
	 * @since 1.0
	 */
	int updateFinCashCalcRule(FinCashCalcRule dto);

	/**
	 * 更新FinCashPayRule
	 * 
	 * @param dto
	 *            FinCashPayRule
	 * @return int
	 * @see
	 * @since 1.0
	 */
	int updateFinCashPayRule(FinCashPayRule dto);

	/**
	 * 更新FinCashPlan
	 * 
	 * @param dto
	 *            FinCashPlan
	 * @return int
	 * @see
	 * @since 1.0
	 */
	int updateFinCashPlan(FinCashPlan dto);

	/**
	 * 
	 * 根据现金流代码查询现金流支付规则
	 * 
	 * @param cashId
	 * @return
	 * @see
	 * @since 1.0
	 */
	int queryFinCashPayRuleByCashId(String cashId);

	/**
	 * 
	 * 根据现金流代码查询现金流信息
	 * 
	 * @param cashId
	 * @return
	 * @see
	 * @since 1.0
	 */
	int queryFinCashInfoeByCashId(String cashId);
}
