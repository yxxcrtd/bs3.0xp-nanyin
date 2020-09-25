package com.joyintech.invest.interf.r010.service;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.invest.interf.business.BusinessUtil;
import com.joyintech.invest.interf.common.core.client.ClientData;
import com.joyintech.invest.interf.common.core.service.InterfRecvBaseService;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 姚轶文
 * 组合费用估值信息
 * @create 2020- 02-27 14:18
 */
public class CostValuationService extends InterfRecvBaseService<String> {
    @Override
    protected ClientData resolveData(Map<String, Object> request, ClientData response) {
        // 消息头
        BusinessUtil.createRecInterfHeader(request, response, getInfId());
        // 微服务用参数Map
        Map<String, Object> paraMap = new HashMap<>();

        paraMap.put("portfolioId", request.get("PORTFOLIO_ID")); //组合代码
        paraMap.put("portfolioName", request.get("PORTFOLIO_NAME")); //组合名称
        paraMap.put("oCcy", request.get("O_CCY")); //原币种
        paraMap.put("B_CCY", request.get("B_CCY")); //估值本位币
        paraMap.put("ptlFeeType", request.get("PTL_FEE_TYPE")); //费用类型
        paraMap.put("ptlFeePlace", request.get("PTL_FEE_PLACE")); //费用场所
        paraMap.put("tdyIntervalPayFee", request.get("TDY_INTERVAL_PAY_FEE")); //当日区间应付费用_原币
        paraMap.put("tdyAccumulPayFee", request.get("TDY_ACCUMUL_PAY_FEE")); //当日累计应付费用_原币
        paraMap.put("tdyIntervalPayFeeB", request.get("TDY_INTERVAL_PAY_FEE_B")); //当日区间应付费用_本位币
        paraMap.put("tdyAccumulPayFeeB", request.get("TDY_ACCUMUL_PAY_FEE_B")); //当日累计应付费用_本位币


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

        // 原币种
        if (!StringUtil.isNotEmpty(obj.get("O_CCY"))) {
            responseInfoId = E000001;
            responseInfo += "原币种为空。";
        }

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
