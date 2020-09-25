package com.joyintech.invest.interf.s034.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.invest.interf.business.BusinessUtil;
import com.joyintech.invest.interf.common.core.annotation.INF;
import com.joyintech.invest.interf.common.core.client.ClientData;
import com.joyintech.invest.interf.common.core.contants.InfDictCodeList;
import com.joyintech.invest.interf.common.core.service.InterfSendBaseService;

/**
 * 债券质押式回购交易
 * 
 * @author wm
 *
 */
@Service
@INF("S034")
public class SomdTrdRepoTradeService extends InterfSendBaseService<String> {

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
		// 成交编号/合同编号
		body.put("F_TRADE_ID", trdProductDeal.get("fTradeId"));
		// 交易类型
		body.put("TRADE_TYPE", BusinessUtil.getInferfTradeType(trdProductDeal.get("finprodType2"),
				 trdProductDeal.get("ps"), null,request.get("isInnerTrade")));
		// 组合代码
		body.put("PORTFOLIO_ID", request.get("portfolioId"));
		// 组合名称
//		        body.put("PORTFOLIO_NAME", value);
		// 交易对手代码
		body.put("COUNTER_ID", trdProductDeal.get("counterId"));
		// 交易对手名称
//      body.put("COUNTER_NAME", value);
		//交易场所
		body.put("TRADE_MARKET", trdProductDeal.get("tradeMarket"));
		//资产代码
		body.put("FINPROD_ID", trdProductDeal.get("finprodId"));
		
		// 交易日
		body.put("TRADE_DATE", DateUtil.format(new Date((Long) trdProductDeal.get("tradeDate"))));
		// 交割日
		body.put("SETTLE_DATE", DateUtil.format(new Date((Long) trdProductDeal.get("settleDate"))));
		// 起息日
		body.put("VDATE", DateUtil.format(new Date((Long) trdProductDeal.get("vdate"))));
		// 到期日
		body.put("MDATE", DateUtil.format(new Date((Long) trdProductDeal.get("mdate"))));
		// 清算速度
		body.put("SETTLE_SPEED", trdProductDeal.get("settleSpeed"));
		//回购类型
		
		// 交易类金融产品标的表Map
		@SuppressWarnings("unchecked")
		Map<String, Object> finTradeProduct = (Map<String, Object>) request.get("finTradeProduct");
		// 期限品种
		body.put("TERM_TYPE", finTradeProduct.get("termType"));
		// 实际占款天数
		body.put("ACT_CAP_DAYS", finTradeProduct.get("actCapDays"));
		// 计息基础
		body.put("BASIS", finTradeProduct.get("basis"));
		
		
		// 交易本金
		body.put("PRIN_AMT", trdProductDeal.get("prinAmt"));
		// 交易应计利息
		body.put("INT_AMT", trdProductDeal.get("intAmt"));
		// 收付方向
		body.put("PS", trdProductDeal.get("ps"));
		// 交易
		body.put("TRADE_AMT", trdProductDeal.get("tradeAmt"));
		// 结算币种
		body.put("CCY", trdProductDeal.get("ccy"));
		//到期应计利息
		body.put("M_INT_AMT", finTradeProduct.get("intAmt"));
		//到期结算金额
		body.put("M_TRADE_AMT", finTradeProduct.get("mTradeAmt"));
		 

		// 交易状态
		body.put("TRADE_STATUS", request.get("tradeStatus"));
		// 原交易编号
		body.put("P_TRADE_ID", trdProductDeal.get("pTradeId"));
		// 撤销说明
		body.put("CANCEL_REMARK", request.get("revokeReason"));
		// 证券账户代码
		body.put("SEC_MANAGE_ACCT_ID", trdProductDeal.get("secManageAcctId"));
		// 证券账户名称
		body.put("SEC_MANAGE_ACCT_NAME", "");
		// 通道代码
		body.put("CHL_ID", trdProductDeal.get("chlId"));
		// 通道名称
		body.put("CHL_NAME", "");
		// 备注
		body.put("REMARK", trdProductDeal.get("remark"));
		
		
		
	
		// 参与人信息
		List<Map<String, Object>> partyInfo = new ArrayList<>();
		// 本方交易员
		Map<String, Object> partyInfoMap = new HashMap<>();
		// 参与人角色
		partyInfoMap.put("PARTY_ROLE", InfDictCodeList.参与人角色_本方交易员.getValue());
		// 参与人名称
		partyInfoMap.put("PARTY_NAME", request.get("traderName"));
		// 参与人联系方式
		partyInfoMap.put("PARTY_CONTACT_INFO", request.get("traderContactInfo"));
		partyInfo.add(partyInfoMap);
		// 对手方交易员
		if (StringUtil.isNotEmpty(trdProductDeal.get("counterTrader"))) {
			partyInfoMap = new HashMap<>();
			// 参与人角色
			partyInfoMap.put("PARTY_ROLE", InfDictCodeList.参与人角色_对手方交易员.getValue());
			// 参与人名称
			partyInfoMap.put("PARTY_NAME", trdProductDeal.get("counterTrader"));
			// 参与人联系方式
			partyInfoMap.put("PARTY_CONTACT_INFO", trdProductDeal.get("counterContact"));
			partyInfo.add(partyInfoMap);
		}
		body.put("PARTY_INFO", partyInfo);
		// 账户信息
		if (request.get("stlPayOrders") != null) {
			@SuppressWarnings("unchecked")
			Map<String, Object> stlPayOrders = (Map<String, Object>) request.get("stlPayOrders");
			List<Map<String, Object>> acctInfo = new ArrayList<>();
			Map<String, Object> acctInfoMap = new HashMap<>();
			if (StringUtil.isNotEmpty(stlPayOrders.get("myAcctNo"))) {
				// 账户类型
				acctInfoMap.put("ACCT_TYPE", InfDictCodeList.标的账户类型_本方账户.getValue());
				// 账号
				acctInfoMap.put("ACCT_NO", stlPayOrders.get("myAcctNo"));
				// 币种
				acctInfoMap.put("CCY", stlPayOrders.get("ccy"));
			} else {
				// 账户类型
				acctInfoMap.put("ACCT_TYPE", InfDictCodeList.标的账户类型_对手方账户.getValue());
				// 账号
				acctInfoMap.put("ACCT_NO", stlPayOrders.get("counterAcctNo"));
				// 币种
				acctInfoMap.put("CCY", stlPayOrders.get("ccy"));
			}
			acctInfo.add(acctInfoMap);
			body.put("ACCT_INFO", acctInfo);
		}
		// 费用明细信息
		if (request.get("tradeFeeDetaillist") != null) {
			@SuppressWarnings("unchecked")
			List<Map<String, Object>> tradeFeeDetaillist = (List<Map<String, Object>>) request
					.get("tradeFeeDetaillist");
			List<Map<String, Object>> feeInfo = new ArrayList<>();
			for (Map<String, Object> item : tradeFeeDetaillist) {
				Map<String, Object> feeTypeMap = new HashMap<>();
				// 费用类型
				feeTypeMap.put("FEE_TYPE", item.get("feeType"));
				// 费用币种
				feeTypeMap.put("CCY", item.get("ccy"));
				// 费用金额
				feeTypeMap.put("FEE_AMT", item.get("feeAmt"));
				// 费用收付方向
				feeTypeMap.put("PS", request.get("feePs"));
				// 是否随交易支付
				feeTypeMap.put("IS_PAY_WITH_TRADE", item.get("isPayWithTrade"));
				feeInfo.add(feeTypeMap);
			}
			body.put("FEE_INFO", feeInfo);
		}
		// 划款信息
		if (request.get("stlPayOrders") != null) {
			@SuppressWarnings("unchecked")
			Map<String, Object> stlPayOrders = (Map<String, Object>) request.get("stlPayOrders");
			List<Map<String, Object>> settleInfo = new ArrayList<>();
			Map<String, Object> settleInfoMap = new HashMap<>();
			// 收款账户
			settleInfoMap.put("REC_ACCT_NO", stlPayOrders.get("recAcctNo"));
			// 付款账户
			settleInfoMap.put("PAY_ACCT_NO", stlPayOrders.get("payAcctNo"));
			// 划款金额
			settleInfoMap.put("SETTLE_AMT", stlPayOrders.get("settleAmt"));
			// 币种
			settleInfoMap.put("CCY", stlPayOrders.get("ccy"));
			settleInfo.add(settleInfoMap);
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
