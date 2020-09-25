package com.joyintech.invest.interf.s057.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.invest.interf.business.BusinessUtil;
import com.joyintech.invest.interf.common.core.annotation.INF;
import com.joyintech.invest.interf.common.core.client.ClientData;
import com.joyintech.invest.interf.common.core.service.InterfSendBaseService;

/**
 * 应收款转让交易
 * 
 * @author wangqidong
 *
 */
@Service
@INF("S057")
public class SomoRecableDealService extends InterfSendBaseService<String> {

	/**
	 * 转换为标准输出报文 {@inheritDoc}
	 */
	@Override
	protected ClientData resolveData(Map<String, Object> request, ClientData response) {
		// 消息头
		BusinessUtil.createSendInterfHeader(request, response, getInfId());

		// 消息体
		Map<String, Object> body = response.getBody();
		// 交易表Map
		@SuppressWarnings("unchecked")
		Map<String, Object> trdProductDeal = (Map<String, Object>) request.get("trdProductDeal");
		// 交易编号
		body.put("TRADE_ID", trdProductDeal.get("tradeId"));
		// 交易类型
		body.put("TRADE_TYPE", BusinessUtil.getInferfTradeType(trdProductDeal.get("finprodType2"),
			 trdProductDeal.get("ps"), null, request.get("isInnerTrade")));
		// 组合代码
		body.put("PORTFOLIO_ID", request.get("portfolioId"));
		// 组合名称
//								        body.put("PORTFOLIO_NAME", value);
		// 交易对手代码
		body.put("COUNTER_ID", trdProductDeal.get("counterId"));
		// 交易对手名称
//								        body.put("COUNTER_NAME", value);
		// 资产代码
		body.put("FINPROD_ID", trdProductDeal.get("finprodId"));
		// 资产名称
		body.put("FINPROD_NAME", "");
		// 应收款期次
		body.put("BRANCH", trdProductDeal.get("branch"));
		// 交易日
		body.put("TRADE_DATE", DateUtil.format(new Date((Long) trdProductDeal.get("tradeDate"))));
		// 交割日
		body.put("SETTLE_DATE", DateUtil.format(new Date((Long) trdProductDeal.get("settleDate"))));
		// 收付方向
		body.put("PS", trdProductDeal.get("ps"));
		// 金额
		body.put("TRADE_AMT", trdProductDeal.get("tradeAmt"));
		// 币种
		body.put("CCY", trdProductDeal.get("ccy"));
		// 划款信息
		if (request.get("stlPayOrderslist") != null) {
			@SuppressWarnings("unchecked")
			List<Map<String, Object>> stlPayOrderslist = (List<Map<String, Object>>) request.get("stlPayOrderslist");
			List<Map<String, Object>> settleInfo = new ArrayList<>();
			for (Map<String, Object> item : stlPayOrderslist) {
				Map<String, Object> settleInfoMap = new HashMap<>();
				// 收款账户
				settleInfoMap.put("REC_ACCT_NO", item.get("recAcctNo"));
				// 付款账户
				settleInfoMap.put("PAY_ACCT_NO", item.get("payAcctNo"));
				// 划款金额
				settleInfoMap.put("SETTLE_AMT", item.get("settleAmt"));
				// 币种
				settleInfoMap.put("CCY", item.get("ccy"));
				settleInfo.add(settleInfoMap);
			}
			body.put("SETTLE_INFO", settleInfo);
		}
		return response;
	}

	/**
	 * 接口预留先决处理操作 {@inheritDoc}
	 */
	@Override
	protected void beforeHandle(Map<String, Object> request, ClientData response) {
		// TODO Auto-generated method stub

	}

	/**
	 * 条件check {@inheritDoc}
	 */
	@Override
	protected boolean checkWithCond(String obj, ClientData response) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * 接口预留后置处理操作 {@inheritDoc}
	 */
	@Override
	protected void afterHandle(Map<String, Object> request, ClientData response) {
		// TODO Auto-generated method stub

	}

}
