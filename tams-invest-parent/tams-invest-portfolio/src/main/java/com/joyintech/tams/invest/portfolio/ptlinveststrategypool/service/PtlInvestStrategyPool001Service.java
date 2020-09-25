package com.joyintech.tams.invest.portfolio.ptlinveststrategypool.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.portfolio.ptlinveststrategypool.dto.PtlInvestStrategyPool001Dto;

@Service("PtlInvestStrategyPool001Service")
@Transactional(rollbackFor = Exception.class)
public class PtlInvestStrategyPool001Service extends CommonExportService<PtlInvestStrategyPool001Dto>{

	/**
	 * 分页查询
	 * 
	 * @param parameters
	 * @return
	 */
	public Page<PtlInvestStrategyPool001Dto> selectForPage(Map<String, Object> pageParam) {
	    Page<PtlInvestStrategyPool001Dto> page =  restTemplate.postForPage(
				ServiceApplication.PORTFOLIO_SERVICE_NAME + "/PtlInvestStrategyPool/selectForPage", pageParam,
				PtlInvestStrategyPool001Dto.class);
	    return page;
	}
	
	public Integer selectIsPtlPortfolioCount(String strategyId){
		Map<String,Object> pageParam = new HashMap<String,Object>();
		pageParam.put("strategyId", strategyId);
		return restTemplate.postForObject(
				ServiceApplication.PORTFOLIO_SERVICE_NAME + "/PtlInvestStrategyPool/selectIsPtlPortfolioCount", pageParam,
				Integer.class);
	}
	public Integer delete(PtlInvestStrategyPool001Dto entity){
		return restTemplate.postForObject(
				ServiceApplication.PORTFOLIO_SERVICE_NAME + "/PtlInvestStrategyPool/delete", entity,
				Integer.class);
	}
	public List<PtlInvestStrategyPool001Dto>  selectForExport(Map<String, Object> pageParam){
		return restTemplate.postForList(
				ServiceApplication.PORTFOLIO_SERVICE_NAME + "/PtlInvestStrategyPool/selectForExport", pageParam,
				PtlInvestStrategyPool001Dto.class);
	}
}
