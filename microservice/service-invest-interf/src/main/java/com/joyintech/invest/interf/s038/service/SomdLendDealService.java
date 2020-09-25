package com.joyintech.invest.interf.s038.service;


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
 *  拆借交易 接口
 * 	功能描述投资管理系统发送交易数据 							
 * @author cxq
 * @version 1.0 2020年2月12日
 * @since 1.0
 */
@Service
@INF("S038")
public class SomdLendDealService extends InterfSendBaseService<String> {

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
    @SuppressWarnings("unchecked")
	@Override
    protected ClientData resolveData(Map<String, Object> request, ClientData response) {
        // 消息头
        BusinessUtil.createSendInterfHeader(request, response, getInfId());

        // 消息体
        Map<String, Object> body = response.getBody();
        // 交易表Map
        Map<String, Object> trdProductDeal = (Map<String, Object>)request.get("trdProductDeal");
        Map<String, Object> paTrdProductDeal = (Map<String, Object>)request.get("paTrdProductDeal");
        Map<String, Object> finTradeProduct = (Map<String, Object>)request.get("finTradeProduct");
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
        // 资产代码
        body.put("FINPROD_ID", trdProductDeal.get("finprodId"));
        // 资产名称
        // body.put("FINPROD_NAME", request.get("actPortfolioId"));
        // 交易日
        body.put("TRADE_DATE", DateUtil.format(new Date((Long)trdProductDeal.get("tradeDate"))));
        // 交割日
        body.put("SETTLE_DATE", DateUtil.format(new Date((Long)trdProductDeal.get("settleDate"))));
        // 起息日
        body.put("VDATE", DateUtil.format(new Date((Long)trdProductDeal.get("vdate"))));
        // 到期日
        body.put("MDATE", DateUtil.format(new Date((Long)trdProductDeal.get("mdate"))));
        //拆借类型 
        body.put("DL_TYPE", paTrdProductDeal.get("paBussType"));
        //实际占款日
        body.put("ACT_CAP_DAYS", paTrdProductDeal.get("term"));
        // 计息基础
        body.put("BASIS", paTrdProductDeal.get("basis"));
        //  拆借利率 
        body.put("RATE", paTrdProductDeal.get("intRate"));
        // 交易本金
        body.put("PRIN_AMT", paTrdProductDeal.get("mPrinAmt"));
        // 交易应计利息
        body.put("INT_AMT", "");
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
