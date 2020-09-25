package com.joyintech.invest.interf.s053.service;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.invest.interf.business.BusinessUtil;
import com.joyintech.invest.interf.common.core.annotation.INF;
import com.joyintech.invest.interf.common.core.client.ClientData;
import com.joyintech.invest.interf.common.core.service.InterfSendBaseService;

/**
 * @author chenxiaoqiu
 * 	接口名称 ：	 资金划拨交易 
 * 	功能描述： 投资管理系统发送交易数据 							
 */
@Service
@INF("S053")
public class SomdOrdersDealService extends InterfSendBaseService<String> {

    /**
     * 	条件check {@inheritDoc}
     */
    @Override
    protected boolean checkWithCond(String obj, ClientData response) {
        return false;
    }
    /**
     * 	转换为标准输出报文 {@inheritDoc}
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
        // 交易类型
        body.put("TRADE_TYPE",
            BusinessUtil.getInferfTradeType(trdProductDeal.get("finprodType2"),
                trdProductDeal.get("ps"), null,request.get("isInnerTrade")));
       // 交易附加表Map
        Map<String, Object> trdProductDealAdd = (Map<String, Object>)request.get(
            "trdProductDealAdd");
        if (trdProductDealAdd != null) {
            // 组合代码
            body.put("PORTFOLIO_ID", trdProductDealAdd.get("portfolioId"));
            // 组合名称
            // body.put("PORTFOLIO_NAME", value);
        } 
        // 交易日
        body.put("TRADE_DATE", DateUtil.format(new Date((Long)trdProductDeal.get("tradeDate"))));
        // 支付日
        body.put("SETTLE_DATE", DateUtil.format(new Date((Long)trdProductDeal.get("settleDate"))));
        
        Map<String, Object> stlPayOrders = (Map<String, Object>)request.get("stlPayOrders");
        if (stlPayOrders != null) {
            // 收付方向
            body.put("PS", stlPayOrders.get("ps"));
            // 交易金额
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
        // body.put("CHL_NAME", request.get("actPortfolioId"))
        //通道本金
        body.put("CHL_PRIN_AMT", trdProductDeal.get("prinAmt"));
        //通道收益
        body.put("CHL_INT_AMT", trdProductDeal.get("intAmt"));
        // 备注
        body.put("REMARK", trdProductDeal.get("remark"));
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
     * 	接口预留先决处理操作 {@inheritDoc}
     */
    @Override
    protected void beforeHandle(Map<String, Object> request, ClientData response) {

    }

    /**
     *	 接口预留后置处理操作 {@inheritDoc}
     */
    @Override
    protected void afterHandle(Map<String, Object> request, ClientData response) {

    }

}

