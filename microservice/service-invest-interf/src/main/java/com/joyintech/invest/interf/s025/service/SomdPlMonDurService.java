package com.joyintech.invest.interf.s025.service;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.invest.interf.business.BusinessUtil;
import com.joyintech.invest.interf.common.core.annotation.INF;
import com.joyintech.invest.interf.common.core.client.ClientData;
import com.joyintech.invest.interf.common.core.contants.InfDictCodeList;
import com.joyintech.invest.interf.common.core.service.InterfSendBaseService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@INF("S025")
public class SomdPlMonDurService extends InterfSendBaseService<String> {

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
        Map<String, Object> trdProductDeal = (Map<String, Object>) request.get("trdProductDeal");
        // 交易编号v
        body.put("TRADE_ID", trdProductDeal.get("tradeId"));
        // 成交编号/合同编号
        body.put("F_TRADE_ID", trdProductDeal.get("fTradeId"));
        // 交易类型
        body.put("TRADE_TYPE", BusinessUtil.getInferfTradeType(trdProductDeal.get("finprodType2"),
            trdProductDeal.get("ps"), request.get("otherTypeForConvert"), null));
        // 交易附加表Map
        Map<String, Object> trdProductDealAdd = (Map<String, Object>) request.get(
                "trdProductDealAdd");
        if (trdProductDealAdd != null) {
            // 组合代码
            body.put("PORTFOLIO_ID", trdProductDealAdd.get("portfolioId"));
            // 组合名称
            // body.put("PORTFOLIO_NAME", value);
        }
        // 交易对手代码
        body.put("COUNTER_ID", trdProductDeal.get("counterId"));
        // 交易对手名称
        // body.put("COUNTER_NAME", value);
        // 交易场所
        body.put("TRADE_MARKET", trdProductDeal.get("tradeMarket"));
        // 资产代码
        body.put("FINPROD_ID", trdProductDeal.get("finprodId"));
        // 市场代码
        // 标的表Map
        Map<String, Object> finProduct = (Map<String, Object>) request.get("finProduct");
        if (finProduct != null) {
            body.put("FINPROD_MARKET_ID", finProduct.get("finprodMarketId"));
            // 资产名称
            // body.put("FINPROD_NAME", request.get("actPortfolioId"));
        }
        // 交易市场
        body.put("TRADE_MARKET_MODE", trdProductDeal.get("tradeMarketMode"));
        // 交易平台
        body.put("TRADE_PLAT", trdProductDeal.get("tradePlat"));
        // 交易日
        body.put("TRADE_DATE", DateUtil.format(new Date((Long) trdProductDeal.get("tradeDate"))));
        // 交割日
        body.put("SETTLE_DATE", DateUtil.format(new Date((Long) trdProductDeal.get("settleDate"))));
        // 交付日
        if (trdProductDeal.get("payDate") != null) {
            body.put("TRADE_MARKET_SETTLE_DATE",
                    DateUtil.format(new Date((Long) trdProductDeal.get("payDate"))));
        }
        // 清算速度
        body.put("SETTLE_SPEED", trdProductDeal.get("settleSpeed"));
        // 百元净价
        body.put("UNIT_CPRICE", trdProductDeal.get("unitCprice"));
        // 百元应计利息
        body.put("UNIT_INT", trdProductDeal.get("unitInt"));
        // 百元全价
        body.put("UNIT_FPRICE", trdProductDeal.get("unitFprice"));
        // 净价总额
        body.put("CPRICE_AMT", trdProductDeal.get("cpriceAmt"));
        // 券面总额
        body.put("SHARE_AMT", trdProductDeal.get("shareAmt"));
        // 应计利息总额
        body.put("INT_AMT", trdProductDeal.get("intAmt"));
        // 全价总额
        body.put("FPRICE_AMT", trdProductDeal.get("fpriceAmt"));
        // 到期收益率
        body.put("YIELD", trdProductDeal.get("yield"));
        // 行权收益率
        body.put("EXER_YIELD", trdProductDeal.get("exerYield"));
        // 指令表Map
        Map<String, Object> stlPayOrders = (Map<String, Object>) request.get("stlPayOrders");
        if (stlPayOrders != null) {
            // 收付方向
            body.put("PS", stlPayOrders.get("ps"));
            // 结算金额
            body.put("TRADE_AMT", stlPayOrders.get("settleAmt"));
            // 结算币种
            body.put("CCY", stlPayOrders.get("ccy"));
        }
        // 投资目的
        body.put("INV_AIM", trdProductDeal.get("invAim"));
        // 交易状态
        body.put("TRADE_STATUS", request.get("tradeStatus"));
        // 原交易编号
        body.put("P_TRADE_ID", trdProductDeal.get("pTradeId"));
        // 撤销说明
        body.put("CANCEL_REMARK", request.get("revokeReason"));
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
            List<Map<String, Object>> tradeFeeDetaillist = (List<Map<String, Object>>) request.get(
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
            settleInfoMap.put("SETTLE_AMT", trdProductDeal.get("tradeAmt"));
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
