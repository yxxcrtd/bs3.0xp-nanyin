package com.joyintech.tams.business.common.service;

import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.business.common.exception.PositionException;
import com.joyintech.tams.business.common.util.HttpUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.Map;

//@PropertySource("classpath:config.properties")
@Component
@Lazy(value=false)
public class PositionHttpRequest {

    public final static String CODE_BUSI_REFUSE = "300";    // 返回业务信息拒绝，余额不足，需前端确认

    private static String positionUrl;
    
    @Value("${positionUrl}")
    public void setPositionUrl(String url) {
        PositionHttpRequest.positionUrl = url;
    }

    /**
     * 头寸调用方法，需处理异常，可使用PositionUtil.ajaxErrorReturn解析异常
     * 返回码 300|。。。  表示余额不足，需前端确认
     * 返回码 410|。。。  表示通信息或网络超时
     * 返回码 420|。。。  头寸引擎返回不成功，具体参考返回信息
     * @param req
     * @throws PositionException
     */
    public static void send(Object req) throws PositionException{
        // 
        String res = sendPrivate(req);
        Map message = JsonUtils.json2Object(res, Map.class);
        if (message == null || !StringUtil.equals("success", message.get("code"))){
            if(StringUtil.equals("FL0007", message.get("code"))){
                throw new PositionException(CODE_BUSI_REFUSE + "|" + (message==null?"":message.get("message")));
            }
        }
    }

    private static String sendPrivate(Object param) {
        return httpPostRequest(positionUrl + "/position/endpoint/updatePosition", param);
    }

    public static String cancel(Object param) {
        return httpPostRequest(positionUrl + "/position/endpoint/cancel", param);
    }
    
    /**
     * 从头寸引擎查询头寸余额
     * @param param
     * @return
     */
    @Deprecated
    public static String query(Object param) {
        return httpPostRequest(positionUrl + "/position/search/balance/etrSecPositionRule", param);
    }

    /**
     * 转换为json
     * @param url
     * @param param
     * @return
     */
    private static String httpPostRequest(String url, Object param){
        return HttpUtil.doPost(url, JsonUtils.object2Json(param));
    }
}
