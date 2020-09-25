package com.joyintech.invest.interf.s042.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.invest.interf.business.BusinessUtil;
import com.joyintech.invest.interf.common.core.annotation.INF;
import com.joyintech.invest.interf.common.core.client.ClientData;
import com.joyintech.invest.interf.common.core.contants.InfDictCodeList;
import com.joyintech.invest.interf.common.core.service.InterfSendBaseService;

/**
 * 债券交易接口
 * 
 * @author renping
 * @version 1.0 2020年2月5日
 * @see
 * @since 1.0
 */
@Service
@INF("S042")
public class SomdOptDealService extends InterfSendBaseService<String> {

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
        body.put("FINPROD_ID", trdProductDeal.get("tradeId"));
        // 市场代码
        // 标的表Map
        Map<String, Object> finProduct = (Map<String, Object>)request.get("finProduct");
        if (finProduct != null) {
            body.put("FINPROD_MARKET_ID", finProduct.get("finprodMarketId"));
            // 资产名称
            // body.put("FINPROD_NAME", request.get("actPortfolioId"));
        }
        // 交易日
        body.put("TRADE_DATE", DateUtil.format(new Date((Long)trdProductDeal.get("tradeDate"))));
        // 交割日
        body.put("SETTLE_DATE", DateUtil.format(new Date((Long)trdProductDeal.get("settleDate"))));
        // 起息日
        body.put("VDATE", DateUtil.format(new Date((Long)trdProductDeal.get("vdate"))));
        // 到期日
        body.put("MDATE", DateUtil.format(new Date((Long)trdProductDeal.get("mdate"))));
        //汇总交易表
        Map<String, Object> trdFinProductDealTotal = (Map<String, Object>)request.get("trdFinProductDealTotal");
        //名义本金 
        body.put("PRIN_AMT", trdFinProductDealTotal.get("prinAmt"));
        //收付方向
        body.put("PS", trdFinProductDealTotal.get("ps"));
        //交易金额
        body.put("TRADE_AMT", trdFinProductDealTotal.get("tradeAmt"));
        //交易币种
        body.put("CCY", trdFinProductDealTotal.get("ccy"));
        //交易类金融产品附属表
        Map<String, Object> finTradeProductAdd = (Map<String, Object>)request.get("finTradeProductAdd");
        //期权费支付类型
        body.put("OPT_FEE_PAY_TYPE", finTradeProductAdd.get("optFeePayType"));
        //期权费
        body.put("OPT_FEE", finTradeProductAdd.get("optFee"));
        //期权费率
        body.put("RATE", finTradeProductAdd.get("optFeeRate"));
        //行权方式
        Map<String, Object> exercisePlan = (Map<String, Object>)request.get("exercisePlan");
        body.put("EXEC_MODE", exercisePlan.get("exerciseType"));
        // 交易状态
        body.put("TRADE_STATUS", request.get("tradeStatus"));
        // 原交易编号
        body.put("P_TRADE_ID", trdProductDeal.get("pTradeId"));
        // 撤销说明
        body.put("CANCEL_REMARK", request.get("revokeReason"));
        //结构类型
        Map<String, Object> finRateInfoStructure = (Map<String, Object>)request.get("finRateInfoStructure");
        body.put("STRUCTURE_TYPE", finRateInfoStructure.get("structureType"));
        //高障碍价 
        body.put("HIGH_HIND_PRICE", finRateInfoStructure.get("highHindPrice"));
        //低障碍价
        body.put("LOW_HIND_PRICE", finRateInfoStructure.get("lowHindPrice"));
        //高行权价 
        body.put("HIGH_EXERC_PRICE", finRateInfoStructure.get("highExercPrice"));
        // 低行权价 
        body.put("LOW_EXERC_PRICE", finRateInfoStructure.get("lowExercPrice"));
        //执行价 
        body.put("EXE_PRICE", finRateInfoStructure.get("exePrice"));
        //敲出价 
        body.put("KNOCK_PRICE", finRateInfoStructure.get("knockPrice"));
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
        // 划款信息
        // 指令表Map
        Map<String, Object> stlPayOrders = (Map<String, Object>)request.get("stlPayOrders");
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
