package com.joyintech.invest.interf.s004.service;

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
 * 债券行权计划 Service
 */
@Service
@INF("S004")
public class FinBondTargetAssetS004Service extends InterfSendBaseService<String> {
	
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

		//行权计划
		List<Map<String, Object>> exercisePlanList = new ArrayList<>();
		if (request.get("baseFinProductBaseInfoList") != null) {

			List<Map<String, Object>> baseFinProductBaseInfoList = (List<Map<String, Object>>) request.get(
					"baseFinProductBaseInfoList");
			for (Map<String, Object> item : baseFinProductBaseInfoList) {

				if (item.get("finExercisePlanList") != null) {
					List<Map<String, Object>> finExercisePlanList = (List<Map<String, Object>>) item.get(
							"finExercisePlanList");
					for (Map<String, Object> exercisePlanItem : finExercisePlanList) {
						Map<String, Object> exercisePlanMap = new HashMap<>();
						//  行权方式
						exercisePlanMap.put("EXERCISE_TYPE", exercisePlanItem.get("exerciseStatus"));
						//申请开始日
						exercisePlanMap.put("VDATE", exercisePlanItem.get("vdate"));
						//  申请结束日
						exercisePlanMap.put("MDATE", exercisePlanItem.get("mdate"));
						// 计划行权日
						exercisePlanMap.put("EXERCISE_DATE", exercisePlanItem.get("exerciseDate"));
						// 实际行权日
						exercisePlanMap.put("ACT_EXERCISE_DATE", exercisePlanItem.get("actExerciseDate"));
						// 行权价
						exercisePlanMap.put("EXERCISE_PRICE", exercisePlanItem.get("exercisePrice"));
						//行权条款
						exercisePlanMap.put("EXERCISE_REMARK", exercisePlanItem.get("exerciseRemark"));
						exercisePlanList.add(exercisePlanMap);
					}
				}
			}
		}
		body.put("EXERCISE_PLAN", exercisePlanList);
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
