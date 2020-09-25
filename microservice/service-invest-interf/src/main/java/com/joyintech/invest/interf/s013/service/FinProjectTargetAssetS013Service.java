package com.joyintech.invest.interf.s013.service;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.invest.interf.business.BusinessUtil;
import com.joyintech.invest.interf.common.core.annotation.INF;
import com.joyintech.invest.interf.common.core.client.ClientData;
import com.joyintech.invest.interf.common.core.service.InterfSendBaseService;
import com.joyintech.invest.interf.common.core.util.InfMapValUtil;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 项目利率信息 Service
 */
@Service
@INF("S013")
public class FinProjectTargetAssetS013Service extends InterfSendBaseService<String> {

    @Override
    protected boolean checkWithCond(String obj, ClientData response) {
        return false;
    }

	@Override
	protected ClientData resolveData(Map<String, Object> request, ClientData response) {
		// TODO Auto-generated method stub
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

		//利率信息YIELD_INFO
		List<Map<String, Object>> yieldList = new ArrayList<>();
		if (request.get("baseFinProductBaseInfoList") != null) {

			List<Map<String, Object>> baseFinProductBaseInfoList = (List<Map<String, Object>>) request.get(
					"baseFinProductBaseInfoList");
			for (Map<String, Object> item : baseFinProductBaseInfoList) {

				if (item.get("finRateInfoList") != null) {
					List<Map<String, Object>> rateInfoList = (List<Map<String, Object>>) item.get(
							"finRateInfoList");
					for (Map<String, Object> rateItem : rateInfoList) {
						Map<String, Object> yieldMap = new HashMap<>();
						// 生效日
						yieldMap.put("EFF_DATE", DateUtil.format(new Date((Long)rateItem.get("effDate")),
								DateUtil.DEFAULT_OTHER_FORMAT) );
						// 计息基础
						yieldMap.put("BASIS", rateItem.get("basis"));
						//   票面利率
						yieldMap.put("YIELD", rateItem.get("rate"));
						//   税后利率
						yieldMap.put("BUYOUT_RATE", rateItem.get("highestRate"));

						yieldList.add(yieldMap);
					}
				}
			}
		}
		body.put("YIELD_INFO", yieldList);
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
