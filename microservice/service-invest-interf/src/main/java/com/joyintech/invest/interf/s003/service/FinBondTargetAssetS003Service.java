package com.joyintech.invest.interf.s003.service;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.invest.interf.business.BusinessUtil;
import com.joyintech.invest.interf.common.core.annotation.INF;
import com.joyintech.invest.interf.common.core.client.ClientData;
import com.joyintech.invest.interf.common.core.service.InterfSendBaseService;
import com.joyintech.invest.interf.common.core.util.InfMapValUtil;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 债券现金流计划 Service
 */
@Service
@INF("S003")
public class FinBondTargetAssetS003Service extends InterfSendBaseService<String> {
	
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
		// 资产代码
		body.put("FINPROD_ID", request.get("finprodId"));
		// 市场代码
		body.put("FINPROD_MARKET_ID", request.get("finprodMarketId"));

		// 资产名称
		body.put("FINPROD_NAME", request.get("finprodName"));

		//现金流计划
		List<Map<String, Object>> cashPlanList = new ArrayList<>();
		if (request.get("baseFinProductBaseInfoList") != null) {

			List<Map<String, Object>> baseFinProductBaseInfoList = (List<Map<String, Object>>) request.get(
					"baseFinProductBaseInfoList");
			for (Map<String, Object> item : baseFinProductBaseInfoList) {

				if (item.get("finCashPlanList") != null) {
					List<Map<String, Object>> finCashPlanList = (List<Map<String, Object>>) item.get(
							"finCashPlanList");
					for (Map<String, Object> cashItem : finCashPlanList) {
						Map<String, Object> cashMap = new HashMap<>();
						//   现金流类型
						cashMap.put("CASH_TYPE", cashItem.get("cashType"));
						//  区间开始日
						cashMap.put("VDATE",DateUtil.format(new Date((Long)cashItem.get("vdate")),
								DateUtil.DEFAULT_OTHER_FORMAT) );
						//   区间结束日
						cashMap.put("MDATE",DateUtil.format(new Date((Long)cashItem.get("mdate")),
								DateUtil.DEFAULT_OTHER_FORMAT) );
						//     预计支付日
						cashMap.put("PAY_DATE",DateUtil.format(new Date((Long) cashItem.get("payDate")),
								DateUtil.DEFAULT_OTHER_FORMAT));
						//      计划支付本金（百元）
						cashMap.put("PRIN_AMT", cashItem.get("prinAmt"));
						//     计划支付利息（百元）
						cashMap.put("INT_AMT", cashItem.get("intAmt"));
						//       计划支付现金流（百元）
						cashMap.put("CASH_AMT", cashItem.get("cashAmt"));
						cashPlanList.add(cashMap);
					}
				}
             }
		}
		body.put("CASH_PLAN", cashPlanList);
		return  response;
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
