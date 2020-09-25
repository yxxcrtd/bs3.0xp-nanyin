package com.joyintech.invest.interf.r013.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.invest.interf.business.BusinessUtil;
import com.joyintech.invest.interf.common.core.annotation.INF;
import com.joyintech.invest.interf.common.core.client.ClientData;
import com.joyintech.invest.interf.common.core.service.InterfRecvBaseService;

/**
 * 组合资金账户信息
 * 
 * @author renping
 * @version 1.0 2020年2月5日
 * @see InterfSomoPfCashAccount
 * @since 1.0
 */
@Service
@INF("R013")
public class InterfSomoPfCashAccount extends InterfRecvBaseService<String> {

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
        // 账户类型
        if (!StringUtil.isNotEmpty(obj.get("ACCT_TYPE"))) {
            responseInfoId = E000001;
            responseInfo += "账户类型为空。";
        }
        // 账号
        if (!StringUtil.isNotEmpty(obj.get("ACCT_NO"))) {
            responseInfoId = E000001;
            responseInfo += "账号为空。";
        }
        // 币种
        if (!StringUtil.isNotEmpty(obj.get("CCY"))) {
            responseInfoId = E000001;
            responseInfo += "币种为空。";
        }
        // 大额支付系统号/网点号
        if (!StringUtil.isNotEmpty(obj.get("LARGE_PAYNO"))) {
            responseInfoId = E000001;
            responseInfo += "大额支付系统号/网点号为空。";
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
        // 组合名称
        paraMap.put("portfolioName", request.get("PORTFOLIO_NAME"));
        // 账户类型
        paraMap.put("acctType", request.get("ACCT_TYPE"));
        // 账号
        paraMap.put("acctNo", request.get("ACCT_NO"));
        // 币种
        paraMap.put("cCY", request.get("CCY"));
        // 户名
        paraMap.put("acctName", request.get("ACCT_NAME"));
        // 开户机构
        paraMap.put("bankDep", request.get("BANK_DEP"));
        // 联行行号
        paraMap.put("corrNo", request.get("CORR_NO"));
        // 大额支付系统号/网点号
        paraMap.put("largePayno", request.get("LARGE_PAYNO"));
        // 银行国际代码（Swift Code）
        paraMap.put("swiftCode", request.get("SWIFT_CODE"));
        // 境内境外
        paraMap.put("blnArea", request.get("BLN_AREA"));
        // 行内行外
        paraMap.put("inOutBank", request.get("IN_OUT_BANK"));
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
