package com.joyintech.invest.interf.s030.service;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.invest.interf.business.BusinessUtil;
import com.joyintech.invest.interf.common.core.annotation.INF;
import com.joyintech.invest.interf.common.core.client.ClientData;
import com.joyintech.invest.interf.common.core.contants.InfDictCodeList;
import com.joyintech.invest.interf.common.core.service.InterfRecvBaseService;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 存款类存续期
 *
 * @author qcx
 * @version 1.0 2020年2月12日
 * @see SomdDepDurService
 * @since 1.0
 */
@Service
@INF("S030")
public class SomdDepDurService extends InterfRecvBaseService<String> {

    /**
     * 条件check {@inheritDoc}
     */
    @Override
    protected boolean checkWithCond(Map<String, Object> obj, ClientData response) {
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
        Map<String, Object> paTrdProductDeal = (Map<String, Object>) request.get("paTrdProductDeal");
        // 交易编号v
        body.put("TRADE_ID", trdProductDeal.get("tradeId"));
        // 交易类型
        body.put("TRADE_TYPE", BusinessUtil.getInferfTradeType(
               trdProductDeal.get("finprodType2"), paTrdProductDeal.get("paBussType"), null,null));
        // 交易附加表Map
        Map<String, Object> trdProductDealAdd = (Map<String, Object>) request.get(
                "trdProductDealAdd");
        if (trdProductDealAdd != null) {
            // 组合代码
            body.put("PORTFOLIO_ID", trdProductDealAdd.get("portfolioId"));
            // 组合名称
            // body.put("PORTFOLIO_NAME", value);
        }
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
        // 通道代码
        body.put("CHL_ID", trdProductDeal.get("chlId"));
        // 通道名称
        // body.put("CHL_NAME", request.get("actPortfolioId"));

        // 交易日
        body.put("TRADE_DATE", DateUtil.format(new Date((Long) trdProductDeal.get("tradeDate"))));
        // 交割日
        body.put("SETTLE_DATE", DateUtil.format(new Date((Long) trdProductDeal.get("settleDate"))));
        // 计息区间开始日
        body.put("VDATE", DateUtil.format(new Date((Long) trdProductDeal.get("vdate"))));
        // 计息区间结束日
        body.put("MDATE", DateUtil.format(new Date((Long) trdProductDeal.get("mdate"))));
        //  本金金额
        body.put("PRIN_AMT", trdProductDeal.get("prinAmt"));
        // 应计利息总额
        body.put("INT_AMT", trdProductDeal.get("intAmt"));
        // 结算金额
        body.put("TRADE_AMT", trdProductDeal.get("tradeAmt"));
        // 指令表Map
        Map<String, Object> stlPayOrders = (Map<String, Object>) request.get("stlPayOrders");
        if (stlPayOrders != null) {
            // 收付方向
            body.put("PS", stlPayOrders.get("ps"));
            // 结算币种
            body.put("CCY", stlPayOrders.get("ccy"));
        }
        // 交易状态
        body.put("TRADE_STATUS", request.get("tradeStatus"));
        // 原交易编号
        body.put("P_TRADE_ID", trdProductDeal.get("pTradeId"));
        // 撤销说明
        body.put("CANCEL_REMARK", request.get("revokeReason"));
        // 备注
        body.put("REMARK", trdProductDeal.get("remark"));
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
