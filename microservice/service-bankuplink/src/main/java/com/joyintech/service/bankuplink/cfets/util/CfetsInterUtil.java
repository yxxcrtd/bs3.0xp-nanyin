package com.joyintech.service.bankuplink.cfets.util;

import java.util.ArrayList;

import com.alibaba.fastjson.JSONObject;
import com.joyintech.background.component.uplink.interbank.business.dto.GateWayReturnMsgDto;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.service.bankuplink.cfets.dto.CfetsInterfResultDto;
import com.joyintech.service.bankuplink.cfets.dto.cashtransaction.dialogquotation.QuoteBody;
import com.joyintech.service.bankuplink.cfets.dto.cashtransaction.dialogquotation.QuoteCancelBody;
import com.joyintech.service.bankuplink.cfets.dto.cashtransaction.dialogquotation.QuoteResponseBody;
import com.joyintech.service.bankuplink.cfets.dto.cashtransaction.requestquotation.QuoteRequestBody;
import com.joyintech.service.bankuplink.cfets.dto.cashtransaction.requestquotation.QuoteRequestCancelBody;

/**
 * @Classname CfetsInterUtil
 * @Description TODO
 * @author zhuhanlu
 * @date 2020/12/21 19:09
 * @version 1.0
 */
public class CfetsInterUtil {

    private static String quoteUrl = "/cashtransaction/dialogQuotation/quote";

    private static String quoteCancleUrl = "/cashtransaction/dialogQuotation/quoteCancel";

    private static String quoteResponseUrl = "/cashtransaction/dialogQuotation/quoteResponse";

    private static String quoteRequestUrl = "/cashtransactionQDM/requestQuotation/quoteRequest";

    private static String quoteRequestCancleUrl = "/cashtransactionQDM/requestQuotation/quoteRequestCancel";

    private static String quoteRequestResponseUrl = "/cashtransactionQDM/requestQuotation/quoteResponse";

    private static String repoQuoteUrl = "/collateralisedRepo/dialogquotation/quote";

    private static String repoQuoteCancleUrl = "/collateralisedRepo/dialogquotation/quoteCancel";

    private static String repoQuoteResponseUrl = "/collateralisedRepo/dialogquotation/quoteResponse";

    private static String serverUrl = CacheUtil.getSysParam("cfets_server_url");

    /**
     * @Description //对话报价
     * @Param [quoteBody]
     * @return java.lang.String
     * @version 1.0
     * @see
     **/
    public static GateWayReturnMsgDto sendQuote(QuoteBody quoteBody) {
        HttpClientUtil httpClientUtil = new HttpClientUtil();
        String requestStr = JsonUtils.object2Json(quoteBody);
        String returnJson = httpClientUtil.sendHttpPost(serverUrl + quoteUrl, requestStr);
        return convertResultDto(JsonUtils.json2Object(returnJson, CfetsInterfResultDto.class));
    }

    /**
     * @Description //对话撤销报价
     * @Param [quoteCancelBody]
     * @return java.lang.String
     * @version 1.0
     * @see
     **/
    public static GateWayReturnMsgDto sendQuoteCancle(QuoteCancelBody quoteCancelBody) {
        HttpClientUtil httpClientUtil = new HttpClientUtil();
        String requestStr = JsonUtils.object2Json(quoteCancelBody);
        String returnJson = httpClientUtil.sendHttpPost(serverUrl + quoteCancleUrl, requestStr);
        return convertResultDto(JsonUtils.json2Object(returnJson, CfetsInterfResultDto.class));
    }

    /**
     * @Description //对话确认成交报价
     * @Param [quoteResponseBody]
     * @return java.lang.String
     * @version 1.0
     * @see
     **/
    public static GateWayReturnMsgDto sendQuoteResponse(QuoteResponseBody quoteResponseBody) {
        HttpClientUtil httpClientUtil = new HttpClientUtil();
        String requestStr = JsonUtils.object2Json(quoteResponseBody);
        String returnJson = httpClientUtil.sendHttpPost(serverUrl + quoteResponseUrl, requestStr);
        return convertResultDto(JsonUtils.json2Object(returnJson, CfetsInterfResultDto.class));
    }

    /**
     * @Description //请求报价
     * @Param [quoteBody]
     * @return java.lang.String
     * @version 1.0
     * @see
     **/
    public static GateWayReturnMsgDto sendQuoteRequest(QuoteRequestBody quoteBody) {
        HttpClientUtil httpClientUtil = new HttpClientUtil();
        String requestStr = JsonUtils.object2Json(quoteBody);
        String returnJson = httpClientUtil.sendHttpPost(serverUrl + quoteRequestUrl, requestStr);
        return convertResultDto(JsonUtils.json2Object(returnJson, CfetsInterfResultDto.class));
    }

    /**
     * @Description //请求撤销报价
     * @Param [quickCancelBody]
     * @return java.lang.String
     * @version 1.0
     * @see
     **/
    public static GateWayReturnMsgDto sendQuoteRequestCancle(QuoteRequestCancelBody quoteRequestCancelBody) {
        HttpClientUtil httpClientUtil = new HttpClientUtil();
        String requestStr = JsonUtils.object2Json(quoteRequestCancelBody);
        String returnJson = httpClientUtil.sendHttpPost(serverUrl + quoteRequestCancleUrl,
            requestStr);
        return convertResultDto(JsonUtils.json2Object(returnJson, CfetsInterfResultDto.class));
    }

    /**
     * @Description //请求确认报价
     * @Param [quoteBody]
     * @return java.lang.String
     * @version 1.0
     * @see
     **/
    public static GateWayReturnMsgDto sendQuoteRequestResponse(com.joyintech.service.bankuplink.cfets.dto.cashtransaction.requestquotation.QuoteResponseBody quoteResponseBody) {
        HttpClientUtil httpClientUtil = new HttpClientUtil();
        String requestStr = JsonUtils.object2Json(quoteResponseBody);
        String returnJson = httpClientUtil.sendHttpPost(serverUrl + quoteRequestResponseUrl,
            requestStr);
        return convertResultDto(JsonUtils.json2Object(returnJson, CfetsInterfResultDto.class));
    }

    /**
     * @Description //质押式回购请求报价
     * @Param [quoteBody]
     * @return java.lang.String
     * @version 1.0
     * @see
     **/
    public static GateWayReturnMsgDto sendRepoQuote(com.joyintech.service.bankuplink.cfets.dto.collateralisedrepo.dialogquotation.QuoteBody quoteBody) {
        HttpClientUtil httpClientUtil = new HttpClientUtil();
        String requestStr = JsonUtils.object2Json(quoteBody);
        String returnJson = httpClientUtil.sendHttpPost(serverUrl + repoQuoteUrl, requestStr);
        return convertResultDto(JsonUtils.json2Object(returnJson, CfetsInterfResultDto.class));
    }

    /**
     * @Description //质押式回购撤销报价
     * @Param [quoteCancelBody]
     * @return java.lang.String
     * @version 1.0
     * @see
     **/
    public static GateWayReturnMsgDto sendRepoQuoteCancle(com.joyintech.service.bankuplink.cfets.dto.collateralisedrepo.dialogquotation.QuoteCancelBody quoteCancelBody) {
        HttpClientUtil httpClientUtil = new HttpClientUtil();
        String requestStr = JsonUtils.object2Json(quoteCancelBody);
        String returnJson = httpClientUtil.sendHttpPost(serverUrl + repoQuoteCancleUrl,
            requestStr);
        return convertResultDto(JsonUtils.json2Object(returnJson, CfetsInterfResultDto.class));
    }

    /**
     * @Description //质押式回购确认成交报价
     * @Param [quoteResponseBody]
     * @return java.lang.String
     * @version 1.0
     * @see
     **/
    public static GateWayReturnMsgDto sendRepoQuoteResponse(com.joyintech.service.bankuplink.cfets.dto.collateralisedrepo.dialogquotation.QuoteResponseBody quoteResponseBody) {
        HttpClientUtil httpClientUtil = new HttpClientUtil();
        String requestStr = JsonUtils.object2Json(quoteResponseBody);
        String returnJson = httpClientUtil.sendHttpPost(serverUrl + repoQuoteResponseUrl,
            requestStr);
        return convertResultDto(JsonUtils.json2Object(returnJson, CfetsInterfResultDto.class));
    }

    /**
     * 将Cfets接口处理结果dto转化为系统使用结果dto
     * 
     * @param cfetsInterfResultDto Cfets接口处理结果dto
     * @return 系统使用结果dto
     */
    private static GateWayReturnMsgDto convertResultDto(CfetsInterfResultDto cfetsInterfResultDto) {
        GateWayReturnMsgDto gateWayReturnMsgDto = new GateWayReturnMsgDto();
        if (cfetsInterfResultDto != null) {
            gateWayReturnMsgDto.setStatus(cfetsInterfResultDto.isSuccess());
            if(!cfetsInterfResultDto.isSuccess()){
            	gateWayReturnMsgDto.setErrCode(cfetsInterfResultDto.getCode());
                gateWayReturnMsgDto.setErrMsg(cfetsInterfResultDto.getMessage());	
            }
        }

        return gateWayReturnMsgDto;
    }
}
