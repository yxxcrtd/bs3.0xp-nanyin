package com.joyintech.tams.invest.risk.stresstestmanage.marketrisksstresstest.service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.risk.stresstestmanage.marketrisksstresstest.dto.MarketRiskStressTest002Dto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class MarketRiskStressTest002Service extends CommonExportService<MarketRiskStressTest002Dto>
		implements BaseProcessService<MarketRiskStressTest002Dto> {


	/**
	 * 产品规模查询
	 * <一句话功能简述>
	 * <功能详细描述>
	 * @param param
	 * @return
	 * @see
	 * @since 1.0
	 */
	public Page<MarketRiskStressTest002Dto> selectProdForPage(Map<String,Object> param){
        return restTemplate.postForPage(
            ServiceApplication.RISK_SERVICE_NAME +"/MarketRiskStressTest002/selectProdForPage", param,
            MarketRiskStressTest002Dto.class);
    }
	
	/**
	 * 未准备因子查询
	 * <一句话功能简述>
	 * <功能详细描述>
	 * @param param
	 * @return
	 * @see
	 * @since 1.0
	 */
	public Page<MarketRiskStressTest002Dto> selectFacForPage(Map<String,Object> param){
        return restTemplate.postForPage(
            ServiceApplication.RISK_SERVICE_NAME +"/MarketRiskStressTest002/selectFacForPage", param,
            MarketRiskStressTest002Dto.class);
    }
	
	
	@Override
	public void doComplete(MarketRiskStressTest002Dto arg0, OperationType arg1) {
		// TODO Auto-generated method stub
		
	}
	 
	 
    
}
