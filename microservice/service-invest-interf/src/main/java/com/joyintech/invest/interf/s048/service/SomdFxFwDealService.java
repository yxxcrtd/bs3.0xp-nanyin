package com.joyintech.invest.interf.s048.service;

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
 * 外汇远期交易
 * 
 * @author wangqidong
 *
 */
@Service
@INF("S048")
public class SomdFxFwDealService extends InterfSendBaseService<String> {

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
				 trdProductDeal.get("ps"), request.get("otherTypeForConvert"), request.get("isInnerTrade")));
		// 组合代码
		body.put("PORTFOLIO_ID", request.get("portfolioId"));
		// 组合名称
//		        body.put("PORTFOLIO_NAME", value);
		// 交易对手代码
		body.put("COUNTER_ID", trdProductDeal.get("counterId"));
		// 交易对手名称
//		        body.put("COUNTER_NAME", value);
		// 资产代码
		body.put("FINPROD_ID", trdProductDeal.get("tradeId"));
		// 资产名称
		body.put("FINPROD_NAME", "");
		// 交易日
		body.put("TRADE_DATE", DateUtil.format(new Date((Long) trdProductDeal.get("tradeDate"))));
		// 远期交割日
		body.put("MDATE", DateUtil.format(new Date((Long) trdProductDeal.get("mdate"))));
		// 清算速度
		body.put("SETTLE_SPEED", trdProductDeal.get("settleSpeed"));
		// 即期汇率
		body.put("UNIT_CPRICE", trdProductDeal.get("unitCprice"));
		// 远期点
		body.put("UNIT_INT", trdProductDeal.get("unitInt"));
		// 远期全价
		body.put("UNIT_FPRICE", trdProductDeal.get("unitFprice"));
		// 投资目的
		body.put("INV_AIM", trdProductDeal.get("invAim"));
		// 清算模式
		body.put("DELIVERY_TYPE", trdProductDeal.get("deliveryType"));
		// 交易币种金额
		body.put("PRIN_AMT", trdProductDeal.get("prinAmt"));
		// 对应币种收付方向
		body.put("PS", trdProductDeal.get("ps"));
		// 对应币种金额
		body.put("TRADE_AMT", trdProductDeal.get("tradeAmt"));
		// 对应币种
		body.put("CCY", trdProductDeal.get("ccy"));
		// 交易状态
		body.put("TRADE_STATUS", request.get("tradeStatus"));
		// 原交易编号
		body.put("P_TRADE_ID", trdProductDeal.get("pTradeId"));
		// 撤销说明
		body.put("CANCEL_REMARK", request.get("revokeReason"));
		// 备注
		body.put("REMARK", trdProductDeal.get("remark"));
		// 交易币种
		body.put("PRIN_CCY", "");
		// 交易表AddMap
		@SuppressWarnings("unchecked")
		Map<String, Object> trdProductDealAdd = (Map<String, Object>) request.get("trdProductDealAdd");
		// 期限品种
		body.put("TERM_TYPE", trdProductDealAdd.get("termType"));
		// 货币对
		body.put("CUR_PAIR", trdProductDealAdd.get("curPair"));
		// 定价日
		body.put("PRICED_DATE", trdProductDealAdd.get("pricedDate"));
		// 差额交割参考汇率
		body.put("DIF_SETTLE_REF_RATE", trdProductDealAdd.get("refRate"));
		// 折美元金额
		body.put("USD_AMT", trdProductDealAdd.get("usdAmt"));
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
		if (request.get("stlPayOrderslist") != null) {
			@SuppressWarnings("unchecked")
			List<Map<String, Object>> stlPayOrderslist = (List<Map<String, Object>>) request.get("stlPayOrderslist");
			List<Map<String, Object>> acctInfo = new ArrayList<>();
			for (Map<String, Object> item : stlPayOrderslist) {
				Map<String, Object> acctInfoMap = new HashMap<>();
				if (StringUtil.isNotEmpty(item.get("myAcctNo"))) {
					// 账户类型
					acctInfoMap.put("ACCT_TYPE", InfDictCodeList.标的账户类型_本方账户.getValue());
					// 账号
					acctInfoMap.put("ACCT_NO", item.get("myAcctNo"));
					// 币种
					acctInfoMap.put("CCY", item.get("ccy"));
				} else {
					// 账户类型
					acctInfoMap.put("ACCT_TYPE", InfDictCodeList.标的账户类型_对手方账户.getValue());
					// 账号
					acctInfoMap.put("ACCT_NO", item.get("counterAcctNo"));
					// 币种
					acctInfoMap.put("CCY", item.get("ccy"));
				}
				acctInfo.add(acctInfoMap);
			}
			body.put("ACCT_INFO", acctInfo);
		}
		// 费用明细信息
		if (request.get("tradeFeeDetail") != null) {
			@SuppressWarnings("unchecked")
			Map<String, Object> tradeFeeDetail = (Map<String, Object>) request.get("tradeFeeDetail");
			List<Map<String, Object>> feeInfo = new ArrayList<>();
			Map<String, Object> feeTypeMap = new HashMap<>();
			// 费用类型
			feeTypeMap.put("FEE_TYPE", tradeFeeDetail.get("feeType"));
			// 费用币种
			feeTypeMap.put("CCY", tradeFeeDetail.get("ccy"));
			// 费用金额
			feeTypeMap.put("FEE_AMT", tradeFeeDetail.get("feeAmt"));
			// 费用收付方向
			feeTypeMap.put("PS", request.get("feePs"));
			// 是否随交易支付
			feeTypeMap.put("IS_PAY_WITH_TRADE", tradeFeeDetail.get("isPayWithTrade"));
			feeInfo.add(feeTypeMap);
			body.put("FEE_INFO", feeInfo);
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
