package com.joyintech.invest.interf.r004.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.invest.interf.business.BusinessUtil;
import com.joyintech.invest.interf.common.core.annotation.INF;
import com.joyintech.invest.interf.common.core.client.ClientData;
import com.joyintech.invest.interf.common.core.service.InterfRecvBaseService;

/**
 * 到账确认信息接口 
 * 基金：赎回到账、分红到账
 * 净值型计划：赎回到账、分红到账
 * 
 * @author renping
 * @version 1.0 2020年2月5日
 * @see InterfToAccountService
 * @since 1.0
 */
@Service
@INF("R004")
public class InterfToAccountService extends InterfRecvBaseService<String> {

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
        // 到账日期
        if (!StringUtil.isNotEmpty(obj.get("SETTLE_DATE"))) {
            responseInfoId = E000001;
            responseInfo += "到账日期为空。";
        }
        // 到账金额
        if (!StringUtil.isNotEmpty(obj.get("TRADE_AMT"))) {
            responseInfoId = E000001;
            responseInfo += "到账金额为空。";
        }
        // 到账日期格式
        if (BusinessUtil.isErrorDate(obj.get("SETTLE_DATE"))) {
            responseInfoId = E000001;
            responseInfo += "到账日期格式不正确。";
        }
        // 到账金额格式
        if (BusinessUtil.isNotNumeric(obj.get("TRADE_AMT"))) {
            responseInfoId = E000001;
            responseInfo += "到账金额格式不正确。";
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
        // 到账日期
        paraMap.put("settleDate", request.get("SETTLE_DATE"));
        // 到账金额
        paraMap.put("tradeAmt", request.get("TRADE_AMT"));
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
