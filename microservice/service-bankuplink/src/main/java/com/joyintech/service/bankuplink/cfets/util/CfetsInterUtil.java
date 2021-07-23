package com.joyintech.service.bankuplink.cfets.util;


import com.joyintech.background.component.uplink.interbank.business.dto.GateWayReturnMsgDto;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.service.bankuplink.cfets.dto.CfetsInterfResultDto;
import com.joyintech.service.bankuplink.cfets.dto.cashtransaction.dialogquotation.QuoteBody;
import com.joyintech.service.bankuplink.cfets.dto.cashtransaction.dialogquotation.QuoteCancelBody;
import com.joyintech.service.bankuplink.cfets.dto.cashtransaction.dialogquotation.QuoteResponseBody;
import com.joyintech.service.bankuplink.cfets.dto.cashtransaction.requestquotation.QuoteRequestBody;
import com.joyintech.service.bankuplink.cfets.dto.cashtransaction.requestquotation.QuoteRequestCancelBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Classname CfetsInterUtil
 * @Description
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

//    private static String serverUrl = CacheUtil.getSysParam("cfets_server_url");

    private static Logger logger = LoggerFactory.getLogger(CfetsInterUtil.class);

    /**
     * 统一对外发送方法
     * @param url 接口系统对应报价url（不含服务地址）
     * @param quoteTypeStr 报价具体操作
     * @param requestStr 报价发送具体报文内容
     * @return 实时返回结果
     */
    private static GateWayReturnMsgDto sendQuoteToInterf(String url, String quoteTypeStr, String requestStr) {
        String serverUrl = CacheUtil.getSysParam("cfets_server_url") + url;

        logger.info("银行间上行报价发送-" + quoteTypeStr + "-接口地址：" + serverUrl);
        logger.info("银行间上行报价发送-" + quoteTypeStr + "-发送报文：" + requestStr);
        HttpClientUtil httpClientUtil = new HttpClientUtil();
        String returnJson = httpClientUtil.sendHttpPost(serverUrl, requestStr);
        logger.info("银行间上行报价发送-" + quoteTypeStr + "-实时返回报文：" + returnJson);
        return convertResultDto(JsonUtils.json2Object(returnJson, CfetsInterfResultDto.class));
    }

    /**
     * @Description //对话报价
     * @Param [quoteBody]
     * @return java.lang.String
     * @version 1.0
     * @see
     **/
    public static GateWayReturnMsgDto sendQuote(QuoteBody quoteBody) {
        return sendQuoteToInterf(quoteUrl, "现券对话报价", JsonUtils.object2Json(quoteBody));
    }

    /**
     * @Description //对话撤销报价
     * @Param [quoteCancelBody]
     * @return java.lang.String
     * @version 1.0
     * @see
     **/
    public static GateWayReturnMsgDto sendQuoteCancle(QuoteCancelBody quoteCancelBody) {
        return sendQuoteToInterf(quoteCancleUrl, "现券对话报价撤销", JsonUtils.object2Json(quoteCancelBody));
    }

    /**
     * @Description //对话确认成交报价
     * @Param [quoteResponseBody]
     * @return java.lang.String
     * @version 1.0
     * @see
     **/
    public static GateWayReturnMsgDto sendQuoteResponse(QuoteResponseBody quoteResponseBody) {
        return sendQuoteToInterf(quoteResponseUrl, "现券对话报价确认成交", JsonUtils.object2Json(quoteResponseBody));
    }

    /**
     * @Description //请求报价
     * @Param [quoteBody]
     * @return java.lang.String
     * @version 1.0
     * @see
     **/
    public static GateWayReturnMsgDto sendQuoteRequest(QuoteRequestBody quoteBody) {
        return sendQuoteToInterf(quoteRequestUrl, "现券请求报价", JsonUtils.object2Json(quoteBody));
    }

    /**
     * @Description //请求撤销报价
     * @Param [quickCancelBody]
     * @return java.lang.String
     * @version 1.0
     * @see
     **/
    public static GateWayReturnMsgDto sendQuoteRequestCancle(QuoteRequestCancelBody quoteRequestCancelBody) {
        return sendQuoteToInterf(quoteRequestCancleUrl, "现券请求报价撤销", JsonUtils.object2Json(quoteRequestCancelBody));
    }

    /**
     * @Description //请求确认报价
     * @Param [quoteBody]
     * @return java.lang.String
     * @version 1.0
     * @see
     **/
    public static GateWayReturnMsgDto sendQuoteRequestResponse(com.joyintech.service.bankuplink.cfets.dto.cashtransaction.requestquotation.QuoteResponseBody quoteResponseBody) {
        return sendQuoteToInterf(quoteRequestResponseUrl, "现券请求报价确认", JsonUtils.object2Json(quoteResponseBody));
    }

    /**
     * @Description //质押式回购请求报价
     * @Param [quoteBody]
     * @return java.lang.String
     * @version 1.0
     * @see
     **/
    public static GateWayReturnMsgDto sendRepoQuote(com.joyintech.service.bankuplink.cfets.dto.collateralisedrepo.dialogquotation.QuoteBody quoteBody) {
        return sendQuoteToInterf(repoQuoteUrl, "质押式回购对话报价", JsonUtils.object2Json(quoteBody));
    }

    /**
     * @Description //质押式回购撤销报价
     * @Param [quoteCancelBody]
     * @return java.lang.String
     * @version 1.0
     * @see
     **/
    public static GateWayReturnMsgDto sendRepoQuoteCancle(com.joyintech.service.bankuplink.cfets.dto.collateralisedrepo.dialogquotation.QuoteCancelBody quoteCancelBody) {
        return sendQuoteToInterf(repoQuoteCancleUrl, "质押式回购对话报价撤销", JsonUtils.object2Json(quoteCancelBody));
    }

    /**
     * @Description //质押式回购确认成交报价
     * @Param [quoteResponseBody]
     * @return java.lang.String
     * @version 1.0
     * @see
     **/
    public static GateWayReturnMsgDto sendRepoQuoteResponse(com.joyintech.service.bankuplink.cfets.dto.collateralisedrepo.dialogquotation.QuoteResponseBody quoteResponseBody) {
        return sendQuoteToInterf(repoQuoteResponseUrl, "质押式回购对话报价确认成交", JsonUtils.object2Json(quoteResponseBody));
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
