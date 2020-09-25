package com.joyintech.invest.interf.s060.service;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.invest.interf.business.BusinessUtil;
import com.joyintech.invest.interf.common.core.annotation.INF;
import com.joyintech.invest.interf.common.core.client.ClientData;
import com.joyintech.invest.interf.common.core.service.InterfRecvBaseService;
import com.joyintech.invest.interf.common.core.service.InterfSendBaseService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 底层资产估值信息
 * 
 * @author xkni
 * @version 1.0 2020年2月25日
 * @see RofvBottomValuationService
 * @since 1.0
 */
@Service
@INF("S060")
public class RofvBottomValuationService  extends InterfSendBaseService<String> {



    /**
     * 转换为标准输出报文 {@inheritDoc}
     */
    @Override
    protected ClientData resolveData(Map<String, Object> request, ClientData response) {
        // 消息头
        BusinessUtil.createSendInterfHeader(request, response, getInfId());
        // 消息体
        Map<String, Object> body = response.getBody();
        // 微服务用参数Map
        List<Map<String, Object>> resultRepList = (List<Map<String, Object>>)request.get(
                "resultRep");
        List<Map<String, Object>> mapList = new ArrayList<>();
        for (Map<String, Object> item : resultRepList) {
            Map<String, Object> paraMap = new HashMap<>();
            // 组合代码
            paraMap.put("PORTFOLIO_ID", request.get("portfolioId"));
            // 日期
            paraMap.put("CDATE", request.get("cdate"));
            // 底层资产代码
            paraMap.put("FINPROD_ID", request.get("finprodId"));
            // 底层市场代码
            paraMap.put("FINPROD_MARKET_ID", request.get("finprodMarketId"));
            // 币种
            paraMap.put("CCY", request.get("ccy"));
            // 数量
            paraMap.put("SHARE_AMT", request.get("shareAmt"));
            // 摊销总成本
            paraMap.put("DSC_COST_AMT", request.get("dscCostAmt"));
            // 摊销成本净价
            paraMap.put("DSC_CLEAN_PRICE", request.get("dscCleanPrice"));
            // 当日应计利息
            paraMap.put("TDY_INTINCEXP", request.get("tayIntincexp"));
            // 市值
            paraMap.put("MARKET_VALUE", request.get("marketValue"));
            mapList.add(paraMap);
        }
        body.put("list", mapList);
        return response;
    }

    /**
     * 接口预留先决处理操作 {@inheritDoc}
     */
    @Override
    protected void beforeHandle(Map<String, Object> request, ClientData response) {

    }

    @Override
    protected boolean checkWithCond(String obj, ClientData response) {
        return false;
    }

    /**
     * 接口预留后置处理操作 {@inheritDoc}
     */
    @Override
    protected void afterHandle(Map<String, Object> request, ClientData response) {

    }
}
