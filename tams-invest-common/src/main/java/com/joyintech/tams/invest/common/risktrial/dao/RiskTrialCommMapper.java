package com.joyintech.tams.invest.common.risktrial.dao;

import com.joyintech.tams.invest.common.risktrial.dto.InvestTradeTrialInput;
import com.joyintech.tams.invest.common.risktrial.dto.SupervisionParameterDto;
import com.joyintech.tams.invest.common.risktrial.dto.SupervisionTargetDto;
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
@Repository
public interface RiskTrialCommMapper {

	List<SupervisionTargetDto> getPortfIndTask(InvestTradeTrialInput inputDto);
	
	SupervisionParameterDto getIndicatorParam(@Param("code") String code, @Param("granularity") String granularity);
	
	Date getMaxSupDate(@Param("portfolioId") String portfolioId, @Param("indId") String indId);

	int getCountWithBasicPortf(Map<String,Object> param);

	List<String> getPortfolioIdInFinProduct(Map<String,Object> paramMap);

	public int selectAllBooksetsWithRisk();
	//获取组合包名称
	List<String>   selectPageName(Map<String,Object> paramMap);

}
