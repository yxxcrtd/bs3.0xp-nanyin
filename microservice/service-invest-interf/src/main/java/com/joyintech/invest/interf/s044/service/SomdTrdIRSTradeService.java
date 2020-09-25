package com.joyintech.invest.interf.s044.service;

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
 * 利率互换交易接口
 * 
 * @author wm
 * @version 1.0 2020年2月5日
 * @see SomdTrdIRSTradeService
 * @since 1.0
 */
@Service
@INF("S044")
public class SomdTrdIRSTradeService extends InterfSendBaseService<String> {

    /**
     * 条件check {@inheritDoc}
     */
    @Override
    protected boolean checkWithCond(String obj, ClientData response) {
        return false;
    }

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
        Map<String, Object> trdProductDeal = (Map<String, Object>)request.get("trdProductDeal");
        // 交易编号
        body.put("TRADE_ID", trdProductDeal.get("tradeId"));
        // 成交编号/合同编号
        body.put("F_TRADE_ID", trdProductDeal.get("fTradeId"));
        // 交易类型
        body.put("TRADE_TYPE",
            BusinessUtil.getInferfTradeType(trdProductDeal.get("finprodType2"),
                trdProductDeal.get("ps"), request.get("otherTypeForConvert"),request.get("isInnerTrade")));
        // 组合代码
        body.put("PORTFOLIO_ID", request.get("portfolioId"));
        // 组合名称
        // body.put("PORTFOLIO_NAME", value);
        // 交易对手代码
        body.put("COUNTER_ID", trdProductDeal.get("counterId"));
        // 交易对手名称
        // body.put("COUNTER_NAME", value);
        // 交易场所
        body.put("TRADE_MARKET", trdProductDeal.get("tradeMarket"));
        // 资产代码
        body.put("FINPROD_ID", trdProductDeal.get("finprodId"));
        // 交易市场
        body.put("TRADE_MARKET_MODE", trdProductDeal.get("tradeMarketMode"));
        // 交易平台
        body.put("TRADE_PLAT", trdProductDeal.get("tradePlat"));
        // 交易日
        body.put("TRADE_DATE", DateUtil.format(new Date((Long)trdProductDeal.get("tradeDate"))));
        // 交割日
        body.put("SETTLE_DATE", DateUtil.format(new Date((Long)trdProductDeal.get("settleDate"))));
        // 起息日
        body.put("VDATE", DateUtil.format(new Date((Long)trdProductDeal.get("vdate"))));
        // 到期日
        body.put("MDATE", DateUtil.format(new Date((Long)trdProductDeal.get("mdate"))));
        // 交易类金融产品标的Map
        Map<String, Object> finTradeProduct = (Map<String, Object>)request.get("finTradeProduct");
        // 期限品种
        body.put("TERM_TYPE", finTradeProduct.get("termType"));
        // 名义本金
        body.put("PRIN_AMT", trdProductDeal.get("prin"));
        // 名义本金币种
        body.put("PRIN_CCY", trdProductDeal.get("ccy"));
        //其他信息
        List<Map<String, Object>> finRateInfoList = (List<Map<String, Object>>)request.get("finRateInfoList");
        if(!StringUtil.isEmptyList(finRateInfoList)){
        	for (Map<String, Object> map : finRateInfoList) {
        		//固定端信息
				if(StringUtil.equals(InfDictCodeList.利率类型_固定利率.getValue(), map.get("intType"))){
		        	//固定端信息
		        	Map<String, Object> fixInfoMap = new HashMap<>();
					//计息基础 
		        	fixInfoMap.put("BASIS", map.get("basis"));
		        	//初始利率 
		        	fixInfoMap.put("RATE", map.get("rate"));

		        	if (request.get("finCashPayRuleList") != null) {
		        		 List<Map<String, Object>> finCashPayRuleList = (List<Map<String, Object>>)request.get(
		        	                "finCashPayRuleList");
		        		 for (Map<String, Object> map2 : finCashPayRuleList) {
							if(StringUtil.equals(map.get("cashId"), map2.get("cashId"))){
					        	//付息频率
					        	fixInfoMap.put("PAY_CYCLE", map2.get("payCycle"));
								//付息频率
				        		fixInfoMap.put("PAY_TDAY_RULE", map2.get("payTdayRule"));
				             	//计息营业日准则 
				        		fixInfoMap.put("END_TDAY_RULE", map2.get("endTdayRule"));
							}
						}
		        		
		        	}
		        	body.put("FIX_INFO", fixInfoMap);
				}
				//浮动端信息
				else{
					//浮动端信息
		        	Map<String, Object> floatInfoMap = new HashMap<>();
		        	//计息基础  
		        	floatInfoMap.put("BASIS", map.get("basis"));
		        	//首期利率确定日 
		        	floatInfoMap.put("FIRST_CONFIRM_DATE", map.get("FirstConfirmDate"));
		        	// 挂钩利率代码 
		        	floatInfoMap.put("BENCHMARK_ID", map.get("benchmarkId"));
		        	//利率系数 
		        	floatInfoMap.put("COEFFICIENT", map.get("coefficient"));
		        	//利差 
		        	floatInfoMap.put("SPREAD_RATE", map.get("spreadRate"));
		        	//利率重置频率 
		        	floatInfoMap.put("RESET_FREQ", map.get("resetFreq"));
		        	if (request.get("finCashPayRuleList") != null) {
		        		 List<Map<String, Object>> finCashPayRuleList = (List<Map<String, Object>>)request.get(
		        	                "finCashPayRuleList");
		        		 for (Map<String, Object> map2 : finCashPayRuleList) {
							if(StringUtil.equals(map.get("cashId"), map2.get("cashId"))){
					        	//付息频率
								floatInfoMap.put("PAY_CYCLE", map2.get("payCycle"));
								//付息频率
								floatInfoMap.put("PAY_TDAY_RULE", map2.get("payTdayRule"));
				             	//计息营业日准则 
								floatInfoMap.put("END_TDAY_RULE", map2.get("endTdayRule"));
							}
						}		        		
		        	}
		        	body.put("FLOAT_INFO", floatInfoMap);
				}
			}
        }
        // 原交易编号
        body.put("P_TRADE_ID", trdProductDeal.get("pTradeId"));
        // 撤销说明
        body.put("CANCEL_REMARK", request.get("revokeReason"));
        // 交易状态
        body.put("TRADE_STATUS", request.get("tradeStatus"));
        // 交易状态
        body.put("RAMARK", trdProductDeal.get("remark"));
        // 费用明细信息
        if (request.get("tradeFeeDetaillist") != null) {
            List<Map<String, Object>> tradeFeeDetaillist = (List<Map<String, Object>>)request.get(
                "tradeFeeDetaillist");
            List<Map<String, Object>> feeType = new ArrayList<>();
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
                feeType.add(feeTypeMap);
            }
            body.put("FEE_TYPE", feeType);
        }

        // 证券账户代码
        body.put("SEC_MANAGE_ACCT_ID", request.get("secManageAcctId"));
        // 证券账户名称
        // body.put("SEC_MANAGE_ACCT_NAME", value);
        // 通道代码
        body.put("CHL_ID", trdProductDeal.get("chlId"));
        // 通道名称
        // body.put("CHL_NAME", request.get("actPortfolioId"));
        // 备注
        body.put("REMARK", trdProductDeal.get("remark"));
        // 转换信息
        // 转换资产代码
        body.put("FINPROD_ID2", request.get("actPortfolioId"));
        // 转换市场代码
        body.put("FINPROD_MARKET_ID2", request.get("actPortfolioId"));
        // 转换资产名称
        body.put("FINPROD_NAME2", request.get("actPortfolioId"));
        // 转换价格
        body.put("PRICE2", request.get("actPortfolioId"));
        // 转换数量
        body.put("SHARE2", request.get("actPortfolioId"));
        // 零头折现金额
        body.put("CONVERT_CASH_AMT", request.get("actPortfolioId"));
        // 保证品信息
        // 保证金金额
        // 保证金收付方向
        // 参与人信息
        List<Map<String, Object>> partyInfo = new ArrayList<>();
        body.put("PARTY_INFO", partyInfo);
        // 本方交易员
        Map<String, Object> partyInfoMap = new HashMap<>();
        partyInfo.add(partyInfoMap);
        // 参与人角色
        partyInfoMap.put("PARTY_ROLE", InfDictCodeList.参与人角色_本方交易员.getValue());
        // 参与人名称
        partyInfoMap.put("PARTY_NAME", request.get("counterId"));
        // 参与人联系方式
//        partyInfoMap.put("PARTY_CONTACT_INFO", request.get("traderContactInfo"));
        // 对手方交易员
        if (StringUtil.isNotEmpty(trdProductDeal.get("counterTrader"))) {
            partyInfoMap = new HashMap<>();
            partyInfo.add(partyInfoMap);
            // 参与人角色
            partyInfoMap.put("PARTY_ROLE", InfDictCodeList.参与人角色_对手方交易员.getValue());
            // 参与人名称
            partyInfoMap.put("PARTY_NAME", trdProductDeal.get("counterTrader"));
            // 参与人联系方式
            partyInfoMap.put("PARTY_CONTACT_INFO", trdProductDeal.get("counterContact"));
        }
        // 账户信息
        if (request.get("stlFinProductAcctList") != null) {
            List<Map<String, Object>> stlFinProductAcctList = (List<Map<String, Object>>)request.get(
                "stlFinProductAcctList");
            List<Map<String, Object>> acctInfo = new ArrayList<>();
            for (Map<String, Object> item : stlFinProductAcctList) {
                Map<String, Object> acctInfoMap = new HashMap<>();
                if(StringUtil.equals(item.get("acctType"), InfDictCodeList.标的账户类型_本方账户.getValue())){
                	
                }
                // 账户类型
                acctInfoMap.put("ACCT_TYPE", item.get("acctType"));
                // 账号
                acctInfoMap.put("ACCT_NO", item.get("acctNo"));
                // 币种
                acctInfoMap.put("CCY", item.get("ccy"));
            
                acctInfo.add(acctInfoMap);
            }
            body.put("FEE_TYPE", acctInfo);
        }
        // 费用明细信息
        if (request.get("tradeFeeDetaillist") != null) {
            List<Map<String, Object>> tradeFeeDetaillist = (List<Map<String, Object>>)request.get(
                "tradeFeeDetaillist");
            List<Map<String, Object>> feeType = new ArrayList<>();
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
                feeType.add(feeTypeMap);
            }
            body.put("FEE_TYPE", feeType);
        }
        return response;
    }

    /**
     * 接口预留先决处理操作 {@inheritDoc}
     */
    @Override
    protected void beforeHandle(Map<String, Object> request, ClientData response) {

    }

    /**
     * 接口预留后置处理操作 {@inheritDoc}
     */
    @Override
    protected void afterHandle(Map<String, Object> request, ClientData response) {

    }
}
