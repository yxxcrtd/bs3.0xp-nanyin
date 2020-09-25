package com.joyintech.invest.interf.s061.service;

import com.joyintech.invest.interf.business.BusinessUtil;
import com.joyintech.invest.interf.common.core.annotation.INF;
import com.joyintech.invest.interf.common.core.client.ClientData;
import com.joyintech.invest.interf.common.core.service.InterfSendBaseService;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 自定义现金流 Service
 */
@Service
@INF("S061")
public class RofvCustomerDealService extends InterfSendBaseService<String> {

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
		request.keySet().removeIf(key-> key.equals("platDate") ||key.equals("pageId"));
		body=request;
		response.setBody(body);
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
