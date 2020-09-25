package com.joyintech.invest.interf.r014.service;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.invest.interf.business.BusinessUtil;
import com.joyintech.invest.interf.common.core.annotation.INF;
import com.joyintech.invest.interf.common.core.client.ClientData;
import com.joyintech.invest.interf.common.core.service.InterfRecvBaseService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 *	组合业绩基准信息
 */
@Service
@INF("R014")
public class RomoPortfolioBenchmarkService extends InterfRecvBaseService<String> {

	/**
	 * 转换为标准输出报文
	 */
	@Override
	protected ClientData resolveData(Map<String, Object> request, ClientData response) {
		// 消息头
		BusinessUtil.createRecInterfHeader(request, response, getInfId());
		// 微服务用参数Map
		Map<String, Object> paraMap = new HashMap<>();
		// 组合代码
		paraMap.put("portfolioId", request.get("PORTFOLIO_ID"));
		// 组合名称
		paraMap.put("portfolioName", request.get("PORTFOLIO_NAME"));
		// 生效日
		paraMap.put("effDate", request.get("EFF_DATE"));
		// 利率类型
		paraMap.put("rateType", request.get("RATE_TYPE"));
		// 初始利率
		paraMap.put("rate", request.get("RATE"));
		// 挂钩标的编号
		paraMap.put("indexId", request.get("INDEX_ID"));
		// 挂钩标的类型
		paraMap.put("indexSource", request.get("INDEX_SOURCE"));
		// 系数
		paraMap.put("coefficient", request.get("COEFFICIENT"));
		// 利差
		paraMap.put("spreadRate", request.get("SPREAD_RATE"));
		// 利率上限
		paraMap.put("rateLower", request.get("RATE_LOWER"));
		// 利率下限
		paraMap.put("rateLimit", request.get("RATE_LIMIT"));
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
		// 数据check
		// 组合代码
		if (!StringUtil.isNotEmpty(obj.get("PORTFOLIO_ID"))) {
			responseInfoId = E000001;
			responseInfo += "组合代码为空。";
		}
		// 生效日
		if (!StringUtil.isNotEmpty(obj.get("EFF_DATE"))) {
			responseInfoId = E000001;
			responseInfo += "生效日为空。";
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
