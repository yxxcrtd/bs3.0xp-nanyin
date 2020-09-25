package com.joyintech.tams.invest.common.risktrialrds.dao;

import com.joyintech.tams.common.base.entity.PtlPortfolioValuation;
import com.joyintech.tams.common.base.entity.RskIndividAssetDel;
import com.joyintech.tams.common.base.entity.RskIndividSpecialStage;
import com.joyintech.tams.common.base.entity.RskIndividUnite;
import com.joyintech.tams.invest.common.risktrialrds.dto.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 风险试算
 * @author 裴家俊
 * @date 2018年11月27日
 */
@Repository("riskTrialCommMapperRds")
public interface RiskTrialCommMapper {

	List<SupervisionTargetDto> getPortfIndTask(InvestTradeTrialInput inputDto);
	
	SupervisionParameterDto getIndicatorParam(@Param("code") String code, @Param("granularity") String granularity);
	
	Date getMaxSupDate(@Param("portfolioId") String portfolioId, @Param("indId") String indId);

	int getCountWithBasicPortf(Map<String, Object> param);

	List<String> getPortfolioIdInFinProduct(Map<String, Object> paramMap);

	public int selectAllPortfolioWithRisk();

	List<RiskIndividConditionDto> getRiskIndividCondition();

	List<PtlPortfolioValuation> getPortfValuation(@Param(value = "valDate") String valDate);

	List<RskIndividSpecialStage> getRiskIndividSpecialStage(@Param(value = "valDate") String valDate, @Param(value = "uniteType") String uniteType);

	List<RskIndividUnite> getRiskIndividUnite();

	List<RskIndividAssetDel> getRiskIndividAstDel(@Param(value = "valDate") String valDate);

	List<SupervisionParameterDto>  getIndicatorParamAll(@Param(value = "valDate") String valDate);

	List<RiskTrialRetDto> getRiskTrialRetInfo(@Param(value = "supFrequency") String supFrequency, @Param(value = "valDate") String valDate);

	void deleteMxPointSupervisionBatch(RiskTrialRetDto list);

	void deleteMxPointWithoutInd(RiskTrialRetDto list);

	void insertMxPointSupervisionBatch(List<RiskTrialRetDetailDto> list);
	
	String selectWorkflowActionType(@Param(value = "seqNo") String seqNo);

	String getPortfolioInstCode(@Param(value = "portfolioId") String portfolioId);
}
