package com.joyintech.tams.valuation.component.batch.dao;

import com.joyintech.tams.valuation.common.dto.modelDto.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ModAssetInfoMapper {
	//获取资产信息集合
	List<ModAssetInfoDto> selectAssetInfo(ModAssetInfoParamsDto paraMap);

	//获取减值模型资产信息集合
	List<ModAssetInfoDto>  selectImpairmentAssetInfo(ModAssetInfoParamsDto paraMap);
	
	//获取资产现金流计划集合
	List<ModCashFlowDto> getCashFlowPlanInfoByFinprodId(String finprodId);
	
	//现金流计算规则查询
	List<ModCashFlowCalRuleDto>  getCashFlowCalRulesByFinprodId(String finprodId);
	
	//现金流支付规则查询
	List<ModCashFlowPayRulesDto> getCashFlowPayRulesByFinprodId(String finprodId);
		
	//股票行情查询/区间查询
	List<ModStockMarketInfoDto> getStockMarketInfo(ModStockMarketParamsDto paraMap);
	
	//曲线行情查询 /区间查询
	List<ModYieldCurveInfoDto> selectYieldCurveInfo(ModYeildCurveParamsDto dto);
	
	//获取评级数据信息
	ModRateDataDto selectRateDataByMap(Map<String, Object> paraMap);

	// 获取减值评级数据信息
	ModRateDataDto selectModImpairRateData(ModImpairRateParamsDto param);

	//查询评级主体发行的所有债券
	List<String> getUnissueSecIdList(Map<String, Object> param);

	//非标外部评级查询-减值模型
	ModRateDataDto selectOutRateData(Map<String, Object> paraMap);

}
