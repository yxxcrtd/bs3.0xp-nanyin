package com.joyintech.invest.interf.r012.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.invest.interf.business.BusinessUtil;
import com.joyintech.invest.interf.common.core.annotation.INF;
import com.joyintech.invest.interf.common.core.client.ClientData;
import com.joyintech.invest.interf.common.core.service.InterfRecvBaseService;

/**
 * 组合证券账户信息
 * 
 * @author renping
 * @version 1.0 2020年2月5日
 * @see InterfSomoPfSecAccount
 * @since 1.0
 */
@Service
@INF("R012")
public class InterfSomoPfSecAccount extends InterfRecvBaseService<String> {

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
        //  账户类型 
        paraMap.put("acctType", request.get("ACCT_TYPE"));
        // 账号
        paraMap.put("acctNo", request.get("ACCT_NO"));
        // 名称
        paraMap.put("acctName", request.get("ACCT_NAME"));
        // 登记托管机构
        paraMap.put("registOrg", request.get("REGIST_ORG"));
        // 交易场所
        paraMap.put("tradeMarket", request.get("TRADE_MARKET"));
        // 股东代码
        paraMap.put("holderId", request.get("HOLDER_ID"));
        // 券商代码
        paraMap.put("brokerId", request.get("BROKER_ID"));
        // 回款账户
        paraMap.put("recAcctId", request.get("REC_ACCT_ID"));
        // 回款账户币种
        paraMap.put("recAcctCcy", request.get("REC_ACCT_CCY"));
        // 资金结算账户
        paraMap.put("trdAcctId", request.get("TRD_ACCT_ID"));
        // 资金结算账户币种
        paraMap.put("trdAcctCcy", request.get("TRD_ACCT_CCY"));
        // 账户来源
        paraMap.put("secAcctSource", request.get("SEC_ACCT_SOURCE"));
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
