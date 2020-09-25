/**
 * 
 */
package com.joyintech.invest.interf.s052.service;

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
 * 	接口名称 ：	债券质押式回购交易 
 * 	功能描述： 投资管理系统发送交易数据 							
 */
@Service
@INF("S052")
public class SomdFeeDealService extends InterfSendBaseService<String> {

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
        Map<String, Object> paTrdProductDeal = (Map<String, Object>) request.get("paTrdProductDeal");
        // 交易编号
        body.put("TRADE_ID", trdProductDeal.get("tradeId"));
        // 交易类型
        body.put("TRADE_TYPE",
                BusinessUtil.getInferfTradeType(trdProductDeal.get("finprodType2"),
                  trdProductDeal.get("ps"), null,request.get("isInnerTrade")));
        // 组合代码
        body.put("PORTFOLIO_ID", request.get("portfolioId"));
        // 组合名称
        // body.put("PORTFOLIO_NAME", value);
        // 交易日
        body.put("VDATE", DateUtil.format(new Date((Long)trdProductDeal.get("vdate"))));
        // 支付日
        body.put("SETTLE_DATE", DateUtil.format(new Date((Long)trdProductDeal.get("settleDate"))));
        //费用区间开始日 
        body.put("MDATE", DateUtil.format(new Date((Long)trdProductDeal.get("mdate"))));
        //费用区间结束日 
        body.put("VDATE", DateUtil.format(new Date((Long)trdProductDeal.get("vdate"))));
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
        // 备注
        body.put("REMARK", trdProductDeal.get("remark"));
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
