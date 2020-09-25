package com.joyintech.invest.interf.r001.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.invest.interf.business.BusinessUtil;
import com.joyintech.invest.interf.common.core.annotation.INF;
import com.joyintech.invest.interf.common.core.client.ClientData;
import com.joyintech.invest.interf.common.core.contants.InfDictCodeList;
import com.joyintech.invest.interf.common.core.service.InterfRecvBaseService;

/**
 * 其他现金流交易信息
 * 
 * @author renping
 * @version 1.0 2020年2月24日
 * @see RomdCashDealService
 * @since 1.0
 */
@Service
@INF("R001")
public class RomdCashDealService extends InterfRecvBaseService<String> {

    /**
     * 条件check {@inheritDoc}
     */
    @Override
    protected boolean checkWithCond(Map<String, Object> obj, ClientData response) {
        Map<String, Object> header = response.getHead();
        String responseInfoId = AAAAAAA;
        String responseInfo = "";
        // 接收数据check
        // 组合代码
        if (!StringUtil.isNotEmpty(obj.get("PORTFOLIO_ID"))) {
            responseInfoId = E000001;
            responseInfo += "组合代码为空。";
        }
        //交易日期
        if (!StringUtil.isNotEmpty(obj.get("TRADE_DATE"))) {
            responseInfoId = E000001;
            responseInfo += "交易日期为空。";
        }
        //支付日
        if (!StringUtil.isNotEmpty(obj.get("SETTLE_DATE"))) {
            responseInfoId = E000001;
            responseInfo += "支付日为空。";
        }
        //币种
        if (!StringUtil.isNotEmpty(obj.get("CCY"))) {
            responseInfoId = E000001;
            responseInfo += "币种为空。";
        }
        //金额
        if (!StringUtil.isNotEmpty(obj.get("TRADE_AMT"))) {
            responseInfoId = E000001;
            responseInfo += "金额为空。";
        }
        //收付方向
        if (!StringUtil.isNotEmpty(obj.get("TRADE_TYPE"))) {
            responseInfoId = E000001;
            responseInfo += "收付方向为空。";
        }
        //划款信息
        if (!StringUtil.isNotEmpty(obj.get("SETTLE_INFO"))) {
            responseInfoId = E000001;
            responseInfo += "划款信息为空。";
        } else {
            List<Map<String, Object>> settleInfo = (List<Map<String, Object>>)obj.get(
                "SETTLE_INFO");
            for (Map<String, Object> item : settleInfo) {
                // 收款账户
                if (StringUtil.equals(obj.get("TRADE_TYPE"), InfDictCodeList.收付方向_收取.getValue())
                    && !StringUtil.isNotEmpty(item.get("REC_ACCT_NO"))) {
                    responseInfoId = E000001;
                    responseInfo += "收款账户为空。";
                }
                // 付款账户
                if (StringUtil.equals(obj.get("TRADE_TYPE"), InfDictCodeList.收付方向_支付.getValue())
                    && !StringUtil.isNotEmpty(item.get("PAY_ACCT_NO"))) {
                    responseInfoId = E000001;
                    responseInfo += "付款账户为空。";
                }
                // 划款金额
                if (!StringUtil.isNotEmpty(item.get("SETTLE_AMT"))) {
                    responseInfoId = E000001;
                    responseInfo += "划款金额为空。";
                }
                // 币种
                if (!StringUtil.isNotEmpty(obj.get("CCY"))) {
                    responseInfoId = E000001;
                    responseInfo += "币种为空。";
                }
            }
        }
        if (responseInfoId != AAAAAAA) {
            header.put("RESPONSE_INFO_ID", responseInfoId);
            header.put("RESPONSE_INFO", responseInfo);
            return true;
        }
        return false;
    }

    /**
     * 转换为标准输出报文 {@inheritDoc}
     */
    @Override
    protected ClientData resolveData(Map<String, Object> request, ClientData response) {
        // 消息头
        BusinessUtil.createRecInterfHeader(request, response, getInfId());

        // 微服务用参数Map
        Map<String, Object> paraMap = new HashMap<>();

        // 组合代码
        paraMap.put("portfolioId", request.get("PORTFOLIO_ID"));
        // 组合代码
        paraMap.put("portfolioId", request.get("PORTFOLIO_ID"));
        // 交易日期
        paraMap.put("tradeDate", request.get("TRADE_DATE"));
        // 支付日
        paraMap.put("settleDate", request.get("SETTLE_DATE"));
        // 币种
        paraMap.put("ccy", request.get("CCY"));
        // 金额
        paraMap.put("tradeAmt", request.get("TRADE_AMT"));
        // 收付方向
        paraMap.put("ps", request.get("TRADE_TYPE"));
        List<Map<String, Object>> settleInfo = (List<Map<String, Object>>)request.get(
            "SETTLE_INFO");
        List<Map<String, Object>> bussTrdTransferOrder = new ArrayList<Map<String, Object>>();
        for (Map<String, Object> item : settleInfo) {
            // 收款账户
            Map<String, Object> order = new HashMap<>();
            order.put("recAcctNo", item.get("REC_ACCT_NO"));
            // 付款账户
            order.put("payAcctNo", item.get("PAY_ACCT_NO"));
            // 划款金额
            order.put("settleAmt", item.get("SETTLE_AMT"));
            // 币种
            order.put("ccy", item.get("CCY"));
            // 收付方向
            order.put("ps", request.get("TRADE_TYPE"));
            // 组合代码
            order.put("portfolioId", request.get("PORTFOLIO_ID"));
            // 结算日期
            order.put("settleDate", request.get("SETTLE_DATE"));
            bussTrdTransferOrder.add(order);
        }
        paraMap.put("bussTrdTransferOrder", bussTrdTransferOrder);
        Object object = interfCommonService.writeToMicroService(paraMap, getInfId());
        if (object != null) {
            // 消息体目前先空着
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
