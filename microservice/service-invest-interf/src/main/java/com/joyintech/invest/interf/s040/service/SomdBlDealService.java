package com.joyintech.invest.interf.s040.service;

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
 * 	债券借贷交易接口
 * 
 * @author cxq
 * @version 1.0 2020年2月5日
 * @see SomdBlDealService
 * @since 1.0
 */
@Service
@INF("S040")
public class SomdBlDealService extends InterfSendBaseService<String> {

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
                trdProductDeal.get("ps"), null,request.get("isInnerTrade")));
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
        // 标的表Map
        Map<String, Object> finProduct = (Map<String, Object>)request.get("finProduct");
        if (finProduct != null) {
            // 资产名称
            body.put("FINPROD_NAME", finProduct.get("finprodName"));
        }
        // 交易日
        body.put("TRADE_DATE", DateUtil.format(new Date((Long)trdProductDeal.get("tradeDate"))));
        // 交割日
        if (trdProductDeal.get("settleDate") != null) {
            body.put("SETTLE_DATE",
                DateUtil.format(new Date((Long)trdProductDeal.get("settleDate"))));
        }
        // 起息日
        if (trdProductDeal.get("vdate") != null) {
            body.put("VDATE",
                DateUtil.format(new Date((Long)trdProductDeal.get("vdate"))));
        }
        // 到期日
        if (trdProductDeal.get("mdate") != null) {
            body.put("MDATE",
                DateUtil.format(new Date((Long)trdProductDeal.get("mdate"))));
        }
        // 清算速度
        body.put("SETTLE_SPEED", trdProductDeal.get("settleSpeed"));
        //finTradeProduct
        Map<String, Object> finTradeProduct = (Map<String, Object>)request.get("finTradeProduct");
        //期限品种
        body.put("TERM_TYPE", finTradeProduct.get("termType"));
        //实际占券天数
        body.put("ACT_CAP_DAYS", finTradeProduct.get("actCapDays"));
        //现金流计算规则表
        Map<String, Object> finCashCalcRule = (Map<String, Object>)request.get("finCashCalcRule");
        //计息基础
        body.put("BASIS", finCashCalcRule.get("basis"));
        //借贷费率
        body.put("RATE", finCashCalcRule.get("yield"));
        //标的券券面总额
        body.put("PRIN_AMT", finTradeProduct.get("shareAmt"));

        // 指令表Map
        Map<String, Object> stlPayOrders = (Map<String, Object>)request.get("stlPayOrders");
        if (stlPayOrders != null) {
            // 收付方向
            body.put("PS", stlPayOrders.get("ps"));
            // 结算金额
            body.put("TRADE_AMT", stlPayOrders.get("settleAmt"));
            // 结算币种
            body.put("CCY", stlPayOrders.get("ccy"));
        }
        //借贷费用
        body.put("M_TRADE_AMT",finTradeProduct.get("mTradeAmt"));
        // 交易状态
        body.put("TRADE_STATUS", request.get("tradeStatus"));
        // 原交易编号
        body.put("P_TRADE_ID", trdProductDeal.get("pTradeId"));
        // 撤销说明
        body.put("CANCEL_REMARK", request.get("revokeReason"));
        // 通道代码
        body.put("CHL_ID", trdProductDeal.get("chlId"));
        // 通道名称
        // body.put("CHL_NAME", request.get("actPortfolioId"));
        //质押劵置换安排
        Map<String, Object> trdProductDealAdd = (Map<String, Object>)request.get(
                "trdProductDealAdd");
        body.put("EXCHANGE_PLEDGE", trdProductDealAdd.get("exchangePledge"));
        //争议解决方式
        body.put("CONFLICT_SOLVE_WAY", finTradeProduct.get("conflictSolveWay"));
        // 备注
        body.put("REMARK", trdProductDeal.get("remark"));
        
        Map<String, Object> targetInfo = new HashMap<>();
        //资产代码
        targetInfo.put("FINPROD_ID", trdProductDeal.get("finprodId"));
        if (finProduct != null) {
            // 市场代码
        	targetInfo.put("FINPROD_MARKET_ID", finProduct.get("finprodMarketId"));
            // 资产名称
        	targetInfo.put("FINPROD_NAME", finProduct.get("finprodName"));
        }
        //券面总额
        targetInfo.put("PRIN_AMT", finTradeProduct.get("shareAmt"));     
        //首期结算方式
        targetInfo.put("V_DELIVERY_TYPE", "01");     
        //到期结算方式
        targetInfo.put("M_DELIVERY_TYPE", finTradeProduct.get("mDeliveryType"));     
        //标的券信息
        body.put("TARGET_INFO", targetInfo);
        //押品信息
        if (request.get("pledgeBondListDetailed") != null) {
            List<Map<String, Object>> pledgeBondListDetailed = (List<Map<String, Object>>)request.get(
                "pledgeBondListDetailed");
            List<Map<String, Object>> PledgeInfo = new ArrayList<>();
            for (Map<String, Object> item : pledgeBondListDetailed) {
                Map<String, Object> pledgeInfoMap = new HashMap<>();
                //资产代码
                pledgeInfoMap.put("FINPROD_ID", trdProductDeal.get("finprodId"));
                if (finProduct != null) {
                    // 市场代码
                	pledgeInfoMap.put("FINPROD_MARKET_ID", finProduct.get("finprodMarketId"));
                    // 资产名称
                	pledgeInfoMap.put("FINPROD_NAME", finProduct.get("finprodName"));
                }
                // 质押面额
                pledgeInfoMap.put("PRIN_AMT", item.get("pledgePrin"));
                // 折算比例
                pledgeInfoMap.put("SCALE", item.get("pledgeRatio"));
                PledgeInfo.add(pledgeInfoMap);
            }
            body.put("PLEDGE_INFO", PledgeInfo);
        }
        // 参与人信息
        List<Map<String, Object>> partyInfo = new ArrayList<>();
        body.put("PARTY_INFO", partyInfo);
        // 本方交易员
        Map<String, Object> partyInfoMap = new HashMap<>();
        partyInfo.add(partyInfoMap);
        // 参与人角色
        partyInfoMap.put("PARTY_ROLE", InfDictCodeList.参与人角色_本方交易员.getValue());
        // 参与人名称
        partyInfoMap.put("PARTY_NAME", request.get("traderName"));
        // 参与人联系方式
        partyInfoMap.put("PARTY_CONTACT_INFO", request.get("traderContactInfo"));
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
        // 划款信息
        if (stlPayOrders != null) {
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

    }

    /**
     * 接口预留后置处理操作 {@inheritDoc}
     */
    @Override
    protected void afterHandle(Map<String, Object> request, ClientData response) {

    }
}
