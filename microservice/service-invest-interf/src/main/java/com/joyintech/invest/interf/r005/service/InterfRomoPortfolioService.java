package com.joyintech.invest.interf.r005.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.invest.interf.business.BusinessUtil;
import com.joyintech.invest.interf.common.core.annotation.INF;
import com.joyintech.invest.interf.common.core.client.ClientData;
import com.joyintech.invest.interf.common.core.service.InterfRecvBaseService;

/**
 * 组合信息
 * 
 * @author renping
 * @version 1.0 2020年2月5日
 * @see InterfRomoPortfolioService
 * @since 1.0
 */
@Service
@INF("R005")
public class InterfRomoPortfolioService extends InterfRecvBaseService<String> {

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
        // 组合开始日
        if (!StringUtil.isNotEmpty(obj.get("VDATE"))) {
            responseInfoId = E000001;
            responseInfo += "组合开始日为空。";
        }
        // 组合结束日
        if (!StringUtil.isNotEmpty(obj.get("MDATE"))) {
            responseInfoId = E000001;
            responseInfo += "组合结束日为空。";
        }
        // 收益类型
        if (!StringUtil.isNotEmpty(obj.get("PROFIT_TYPE"))) {
            responseInfoId = E000001;
            responseInfo += "收益类型为空。";
        }
        // 组合类型
        if (!StringUtil.isNotEmpty(obj.get("PORTFOLIO_TYPE"))) {
            responseInfoId = E000001;
            responseInfo += "组合类型为空。";
        }
        // 是否保本
        if (!StringUtil.isNotEmpty(obj.get("INCOME_FLAG"))) {
            responseInfoId = E000001;
            responseInfo += "是否保本为空。";
        }
        // 开放类型
        if (!StringUtil.isNotEmpty(obj.get("OPEN_TYPE"))) {
            responseInfoId = E000001;
            responseInfo += "开放类型为空。";
        }
        // 募集方式
        if (!StringUtil.isNotEmpty(obj.get("RAISE_TYPE"))) {
            responseInfoId = E000001;
            responseInfo += "募集方式为空。";
        }
        // 投资性质
        if (!StringUtil.isNotEmpty(obj.get("INVEST_NATURE"))) {
            responseInfoId = E000001;
            responseInfo += "投资性质为空。";
        }
        // 产品经理
        if (!StringUtil.isNotEmpty(obj.get("PROD_MANAGER"))) {
            responseInfoId = E000001;
            responseInfo += "产品经理为空。";
        }
        // 风险等级
        if (!StringUtil.isNotEmpty(obj.get("RISK_LEVEL"))) {
            responseInfoId = E000001;
            responseInfo += "风险等级为空。";
        }
        // 组合开始日
        if (BusinessUtil.isErrorDate(obj.get("VDATE"))) {
            responseInfoId = E000001;
            responseInfo += "组合开始日格式不正确。";
        }
        // 组合结束日
        if (BusinessUtil.isErrorDate(obj.get("MDATE"))) {
            responseInfoId = E000001;
            responseInfo += "组合结束日格式不正确。";
        }
        // 收益类型
        if (BusinessUtil.isErrorDictCode("PROFIT_TYPE", obj.get("PROFIT_TYPE"))) {
            responseInfoId = E000001;
            responseInfo += "收益类型数据不正确。";
        }
        // 组合类型
        if (BusinessUtil.isErrorDictCode("PORTFOLIO_TYPE", obj.get("PORTFOLIO_TYPE"))) {
            responseInfoId = E000001;
            responseInfo += "组合类型数据不正确。";
        }
        // 是否保本
        if (BusinessUtil.isErrorDictCode("YES_OR_NO", obj.get("INCOME_FLAG"))) {
            responseInfoId = E000001;
            responseInfo += "是否保本数据不正确。";
        }
        // 开放类型
        if (BusinessUtil.isErrorDictCode("ISSUE_MODE", obj.get("OPEN_TYPE"))) {
            responseInfoId = E000001;
            responseInfo += "开放类型数据不正确。";
        }
        // 募集方式
        if (BusinessUtil.isErrorDictCode("RAISE_TYPE", obj.get("RAISE_TYPE"))) {
            responseInfoId = E000001;
            responseInfo += "募集方式数据不正确。";
        }
        // 投资性质
        if (BusinessUtil.isErrorDictCode("INVEST_TYPE", obj.get("INVEST_NATURE"))) {
            responseInfoId = E000001;
            responseInfo += "投资性质数据不正确。";
        }
        // // 风险等级
        // if (BusinessUtil.isErrorDictCode("", obj.get("RISK_LEVEL"))) {
        // responseInfoId = E000001;
        // responseInfo += "风险等级数据不正确。";
        // }
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
        // 组合开始日
        paraMap.put("vdate", request.get("VDATE"));
        // 组合结束日
        paraMap.put("mdate", request.get("MDATE"));
        // 收益类型
        paraMap.put("profitType", request.get("PROFIT_TYPE"));
        // 组合类型
        paraMap.put("portfolioType", request.get("PORTFOLIO_TYPE"));
        // 是否保本
        paraMap.put("incomeFlag", request.get("INCOME_FLAG"));
        // 开放类型
        paraMap.put("openType", request.get("OPEN_TYPE"));
        // 募集方式
        paraMap.put("raiseType", request.get("RAISE_TYPE"));
        // 投资性质
        paraMap.put("investNature", request.get("INVEST_NATURE"));
        // 产品经理
        paraMap.put("prodManager", request.get("PROD_MANAGER"));
        // 风险等级
        paraMap.put("riskLevel", request.get("RISK_LEVEL"));
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
