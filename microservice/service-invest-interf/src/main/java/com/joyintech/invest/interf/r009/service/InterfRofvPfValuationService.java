package com.joyintech.invest.interf.r009.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.invest.interf.business.BusinessUtil;
import com.joyintech.invest.interf.common.core.annotation.INF;
import com.joyintech.invest.interf.common.core.client.ClientData;
import com.joyintech.invest.interf.common.core.service.InterfRecvBaseService;

/**
 *	组合估值信息
 */
@Service
@INF("R009")
public class InterfRofvPfValuationService extends InterfRecvBaseService<String> {

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
		// 估值币种
		paraMap.put("ccy", request.get("CCY"));
		// 估值日期
		paraMap.put("cdate", request.get("CDATE"));
		// 当日资产净值
		paraMap.put("tdyAssetNetvalue", request.get("TDY_ASSET_NETVALUE"));
		// 当日资产总值
		paraMap.put("tdyAssetValue", request.get("TDY_ASSET_VALUE"));
		// 当前份额
		paraMap.put("tdyShareAmt", request.get("TDY_SHARE_AMT"));
		// 当日单位净值
		paraMap.put("tdyUnitValue", request.get("TDY_UNIT_VALUE"));
		// 当日累计净值
		paraMap.put("tdyUnitValueSum", request.get("TDY_UNIT_VALUE_SUM"));
		// 当日未分配利润
		paraMap.put("tdyUnpaidIntincexp", request.get("TDY_UNPAID_INTINCEXP"));
		// 7日年化收益率
		paraMap.put("yield7", request.get("YIELD_7"));
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
		// 估值日期
		if (!StringUtil.isNotEmpty(obj.get("CDATE"))) {
			responseInfoId = E000001;
			responseInfo += "日期为空。";
		}
		// 币种
		if (!StringUtil.isNotEmpty(obj.get("CCY"))) {
			responseInfoId = E000001;
			responseInfo += "币种为空。";
		}
//		// 当日资产净值
//		if (!BusinessUtil.isNotNumeric(obj.get("TDY_ASSET_NETVALUE"))) {
//			responseInfoId = E000001;
//			responseInfo += "当日资产净值数据格式不正确。";
//		}
//		// 当日资产总值
//		if (!BusinessUtil.isNotNumeric(obj.get("TDY_ASSET_VALUE"))) {
//			responseInfoId = E000001;
//			responseInfo += "当日资产总值数据格式不正确。";
//		}
//		// 当前份额
//		if (!BusinessUtil.isNotNumeric(obj.get("TDY_SHARE_AMT"))) {
//			responseInfoId = E000001;
//			responseInfo += "当前份额数据格式不正确。";
//		}
//		// 当日单位净值
//		if (!BusinessUtil.isNotNumeric(obj.get("TDY_UNIT_VALUE"))) {
//			responseInfoId = E000001;
//			responseInfo += "当日单位净值数据格式不正确。";
//		}
//		// 当日累计净值
//		if (!BusinessUtil.isNotNumeric(obj.get("TDY_UNIT_VALUE_SUM"))) {
//			responseInfoId = E000001;
//			responseInfo += "当日累计净值数据格式不正确。";
//		}
//		// 当日未分配利润
//		if (!BusinessUtil.isNotNumeric(obj.get("TDY_UNPAID_INTINCEXP"))) {
//			responseInfoId = E000001;
//			responseInfo += "当日未分配利润数据格式不正确。";
//		}
//		// 7日年化收益率
//		if (!BusinessUtil.isNotNumeric(obj.get("YIELD_7"))) {
//			responseInfoId = E000001;
//			responseInfo += "7日年化收益率数据格式不正确。";
//		}
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
