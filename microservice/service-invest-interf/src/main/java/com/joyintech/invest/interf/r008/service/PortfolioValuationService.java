package com.joyintech.invest.interf.r008.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.invest.interf.business.BusinessUtil;
import com.joyintech.invest.interf.common.core.annotation.INF;
import com.joyintech.invest.interf.common.core.client.ClientData;
import com.joyintech.invest.interf.common.core.service.InterfRecvBaseService;

/**
 * @author 姚轶文
 * 组合资产估值信息
 * @create 2020- 02-25 13:39
 */
@Service
@INF("R008")
public class PortfolioValuationService extends InterfRecvBaseService<String> {

    /**
     * 接收csv文件时处理
     * 
     * @param date
     * @param file
     * @see
     * @since 1.0
     */
    @Override
    public ClientData resolveDataByRecvFile(String date, String file, ClientData response) {
        return response;
    }

    @Override
    protected ClientData resolveData(Map<String, Object> request, ClientData response) {
        // 消息头
        BusinessUtil.createSendInterfHeader(request, response, getInfId());
        // 微服务用参数Map
        Map<String, Object> paraMap = new HashMap<>();

        //
        paraMap.put("portfolioId", request.get("PORTFOLIO_ID")); //组合代码
        paraMap.put("portfolioName", request.get("PORTFOLIO_NAME")); //组合名称
        paraMap.put("secAcctId", request.get("SEC_ACCT_ID")); //证券账户代码
        paraMap.put("finprodId", request.get("FINPROD_ID")); //资产代码
        paraMap.put("invAim", request.get("INV_AIM")); //投资目的
        paraMap.put("ccy", request.get("CCY")); //原币种
        paraMap.put("shareAmt", request.get("SHARE_AMT")); //数量
        paraMap.put("tdyFloatIngpl", request.get("TDY_FLOAT_INGPL")); //公允价值变动_原币
        paraMap.put("dscCostAmt", request.get("DSC_COST_AMT")); //摊销总成本_原币
        paraMap.put("dscCleanPrice", request.get("DSC_CLEAN_PRICE")); //摊销成本净价_原币
        paraMap.put("tdyIntincexp", request.get("TDY_INTINCEXP")); //当日应计利息总额_原币
        paraMap.put("delayPayAmt", request.get("DELAY_PAY_AMT")); //待清算资金_原币
        paraMap.put("actDYield", request.get("ACT_D_YIELD")); //实际日利率
        paraMap.put("bCcy", request.get("B_CCY")); //估值本位币
        paraMap.put("tdyFloatIngplB", request.get("TDY_FLOAT_INGPL_B")); //公允价值变动_本位币
        paraMap.put("dscCostAmtB", request.get("DSC_COST_AMT_B")); //摊销总成本_本位币
        paraMap.put("dscCleanPriceB", request.get("DSC_CLEAN_PRICE_B")); //摊销成本净价_本位币
        paraMap.put("tdyIntincexpB", request.get("TDY_INTINCEXP_B")); //当日应计利息总额_本位币
        paraMap.put("delayPayAmtB", request.get("DELAY_PAY_AMT_B")); //待清算资金_本位币
        paraMap.put("endDays_1", request.get("END_DAYS_1")); //剩余期限
        paraMap.put("endDays_2", request.get("END_DAYS_2")); //剩余存续期限
        paraMap.put("intRate", request.get("INT_RATE")); //计提利率
        paraMap.put("inTdyIntincexp", request.get("IN_TDY_INTINCEXP")); //收入端应计利息_原币
        paraMap.put("outTdyIntincexp", request.get("OUT_TDY_INTINCEXP")); //支出端应计利息_原币
        paraMap.put("tdyDsclossAddB", request.get("TDY_DSCLOSS_ADD_B")); //当日价差收入_本币

        Object object = interfCommonService.writeToMicroService(paraMap, getInfId());
        if (object != null) {
            // 消息体目前先空着
        }
        return response;
    }

    @Override
    protected void beforeHandle(Map<String, Object> request, ClientData response) {

    }

    @Override
    protected boolean checkWithCond(Map<String, Object> obj, ClientData response) {
        Map<String, Object> header = response.getHead();
        String responseInfoId = AAAAAAA;
        String responseInfo = "";

        // 组合代码
        if (!StringUtil.isNotEmpty(obj.get("PORTFOLIO_ID"))) {
            responseInfoId = E000001;
            responseInfo += "组合代码为空。";
        }

//        // 组合名称
//        if (!StringUtil.isNotEmpty(obj.get("PORTFOLIO_NAME"))) {
//            responseInfoId = E000001;
//            responseInfo += "组合名称为空。";
//        }

        // 证券账户代码
        if (!StringUtil.isNotEmpty(obj.get("SEC_ACCT_ID"))) {
            responseInfoId = E000001;
            responseInfo += "证券账户代码为空。";
        }

        // 资产代码
        if (!StringUtil.isNotEmpty(obj.get("FINPROD_ID"))) {
            responseInfoId = E000001;
            responseInfo += "资产代码为空。";
        }

//        // 投资目的
//        if (!StringUtil.isNotEmpty(obj.get("INV_AIM"))) {
//            responseInfoId = E000001;
//            responseInfo += "投资目的为空。";
//        }
//
//        // 投资目的
//        if (!StringUtil.isNotEmpty(obj.get("CCY"))) {
//            responseInfoId = E000001;
//            responseInfo += "投资目的为空。";
//        }

        if (responseInfoId != AAAAAAA) {
            header.put("RESPONSE_INFO_ID", responseInfoId);
            header.put("RESPONSE_INFO", responseInfo);
            return true;
        }
        return false;
    }

    @Override
    protected void afterHandle(Map<String, Object> request, ClientData response) {

    }
}
