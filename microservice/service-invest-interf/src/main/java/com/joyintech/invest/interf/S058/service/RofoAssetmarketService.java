package com.joyintech.invest.interf.S058.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.joyintech.invest.interf.business.BusinessUtil;
import com.joyintech.invest.interf.common.core.annotation.INF;
import com.joyintech.invest.interf.common.core.client.ClientData;
import com.joyintech.invest.interf.common.core.service.InterfSendBaseService;

/**
 *	资产行情信息 
 */
@Service
@INF("S058")
public class RofoAssetmarketService extends InterfSendBaseService<String> {
	
    @Override
    protected boolean checkWithCond(String obj, ClientData response) {
        return false;
    }

	@Override
	protected ClientData resolveData(Map<String, Object> request, ClientData response) {
		 // 消息头
        BusinessUtil.createSendInterfHeader(request, response, getInfId());

        // 消息体
        Map<String, Object> body = response.getBody();

        // 市场代码
        body.put("SEC_ID", request.get("portfolioId"));
        // 场外金融产品估值Map
        Map<String, Object> finProductValuation = (Map<String, Object>)request.get("finProductValuation");
        // 估值日 
        body.put("VALUE_DATE", finProductValuation.get("valDate"));
        //估值来源
        body.put("VALUE_SOURCE", finProductValuation.get("valueSource"));
        //七日年化收益率 
        body.put("DAY7_YEAR_YIELD", finProductValuation.get("day7YearYield"));
        //年化收益率 
        body.put("YEAR_YIELD", finProductValuation.get("yearYield"));
        //累计净值 
        body.put("TOTAL_VALUE", finProductValuation.get("totalValue"));
        //复权单位净值 
        body.put("REST_NET_VALUE", finProductValuation.get("restNetValue"));
        return response;
	}

	@Override
	protected void beforeHandle(Map<String, Object> request, ClientData response) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void afterHandle(Map<String, Object> request, ClientData response) {
		// TODO Auto-generated method stub
		
	}
}

