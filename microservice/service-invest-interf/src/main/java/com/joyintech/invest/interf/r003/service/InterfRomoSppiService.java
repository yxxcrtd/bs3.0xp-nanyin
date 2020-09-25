package com.joyintech.invest.interf.r003.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.invest.interf.business.BusinessUtil;
import com.joyintech.invest.interf.common.core.annotation.INF;
import com.joyintech.invest.interf.common.core.client.ClientData;
import com.joyintech.invest.interf.common.core.service.InterfRecvBaseService;

/**
 * SPPI测试结果
 *
 * @author cxq
 * @version 1.0 2020年2月21日
 * @see InterfRomoSppiService
 * @since 1.0
 */
@Service
@INF("R003")
public class InterfRomoSppiService extends InterfRecvBaseService<String> {

	/**
	 * 转换为标准输出报文
	 */
	@Override
	protected ClientData resolveData(Map<String, Object> request, ClientData response) {
		// 消息头
		BusinessUtil.createRecInterfHeader(request, response, getInfId());

		// 微服务用参数Map
		Map<String, Object> paraMap = new HashMap<>();
		// 资产代码
		paraMap.put("finprodId", request.get("FINPROD_ID"));
		// 市场代码
		paraMap.put("finprodMarketId", request.get("FINPROD_MARKET_ID"));
		// 测试日期
		paraMap.put("testDate", request.get("TEST_DATE"));
		// 测试结果
		paraMap.put("testResult", request.get("TEST_RESULT"));
		// 备注
		paraMap.put("remark", request.get("REMARK"));
		Object object = interfCommonService.writeToMicroService(paraMap, getInfId());
		if (object != null) {
			// 消息体目前先空着
		}
		return response;
	}

	/**
	 * 接口预留先决处理操作
	 */
	@Override
	protected void beforeHandle(Map<String, Object> request, ClientData response) {
		// TODO Auto-generated method stub

	}

	/**
	 * 条件check
	 */
	@Override
	protected boolean checkWithCond(Map<String, Object> obj, ClientData response) {
		Map<String, Object> header = response.getHead();
		String responseInfoId = AAAAAAA;
		String responseInfo = "";
		// 接收数据check
		// 测试日期
		if (BusinessUtil.isErrorDate(obj.get("TEST_DATE"))) {
			responseInfoId = E000001;
			responseInfo += "测试日期格式不正确。";
		}
		// 测试结果
        if (BusinessUtil.isErrorDictCode("PASS_STATUS", obj.get("TEST_RESULT"))) {
            responseInfoId = E000001;
            responseInfo += "测试结果数据不正确。";
        }
        if(!StringUtil.isNotEmpty(obj.get("FINPROD_ID")) && !StringUtil.isNotEmpty(obj.get("FINPROD_MARKET_ID")) ) {
        	responseInfoId = E000001;
            responseInfo += "资产代码、市场代码不可同时为空。";
        }
		if (responseInfoId != AAAAAAA) {
			header.put("RESPONSE_INFO_ID", responseInfoId);
			header.put("RESPONSE_INFO", responseInfo);
			return true;
		}
		return false;
	}

	/**
	 * 接口预留后置处理操作
	 */
	@Override
	protected void afterHandle(Map<String, Object> request, ClientData response) {
		// TODO Auto-generated method stub

	}

}
