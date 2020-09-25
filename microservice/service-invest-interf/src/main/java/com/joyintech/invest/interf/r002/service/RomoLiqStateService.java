package com.joyintech.invest.interf.r002.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.invest.interf.business.BusinessUtil;
import com.joyintech.invest.interf.common.core.annotation.INF;
import com.joyintech.invest.interf.common.core.client.ClientData;
import com.joyintech.invest.interf.common.core.service.InterfRecvBaseService;

/**
 * 交易清算状态
 * 
 * @author cxq
 * @version 1.0 2020年2月21日
 * @see RomoLiqStateService
 * @since 1.0
 */
@Service
@INF("R002")
public class RomoLiqStateService extends InterfRecvBaseService<String> {

    /**
     * 条件check {@inheritDoc}
     */
    @Override
    protected boolean checkWithCond(Map<String, Object> obj, ClientData response) {
        Map<String, Object> header = response.getHead();
        String responseInfoId = AAAAAAA;
        String responseInfo = "";
        // 接收数据check
        // 交易编号
        if (!StringUtil.isNotEmpty(obj.get("TRADE_ID"))) {
            responseInfoId = E000001;
            responseInfo += "交易编号为空。";
        }
        //清算状态
        if (!StringUtil.isNotEmpty(obj.get("SETTLE_STATUS"))) {
            responseInfoId = E000001;
            responseInfo += "清算状态为空。";
        }
        //清算状态
        if (BusinessUtil.isErrorDictCode("COMMAND_DEAL_STATUS", obj.get("SETTLE_STATUS"))) {
            responseInfoId = E000001;
            responseInfo += "清算状态数据不正确。";
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

        // 交易编号
        paraMap.put("tradeId", request.get("TRADE_ID"));
        // 清算状态
        paraMap.put("settleStatus", request.get("SETTLE_STATUS"));
        // 备注
        paraMap.put("remark", request.get("REMARK"));
        
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
