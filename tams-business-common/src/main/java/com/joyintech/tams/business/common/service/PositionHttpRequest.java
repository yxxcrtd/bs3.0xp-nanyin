package com.joyintech.tams.business.common.service;

import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.business.common.exception.PositionException;
import com.joyintech.tams.business.common.util.HttpUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.Map;

@PropertySource("classpath:field.properties")
@Component
public class PositionHttpRequest {

    public final static String CODE_TIMEOUT = "410";        // 超时，需冲正
    public final static String CODE_SEND_ERROR = "420";     // 发送异常
    public final static String CODE_BUSI_REFUSE = "200";    // 返回业务信息拒绝，需前端确认

    private static String positionUrl;
    
    
    @Value("${positionUrl}")
    public void setQueuePositionCallback(String url) {
        PositionHttpRequest.positionUrl = url;
    }

    public static void send(Object req) throws PositionException{
        // 
        String res = sendPrivate(req);
        Map message = JsonUtils.json2Object(res, Map.class);
        if (message == null || !StringUtil.equals("success", message.get("code"))){
            if(StringUtil.equals("FL0007", message.get("code"))){
                throw new PositionException(CODE_BUSI_REFUSE + "|" + (message==null?"":message.get("message")));
            }else{
                throw new PositionException(CODE_SEND_ERROR + "|" + (message==null?"":message.get("message")));
            }
        }
    }

    private static String sendPrivate(Object param) {
        return httpPostRequest(positionUrl + "/position/endpoint/updatePosition", param);
    }

    public static String query(Object param) {
        return httpPostRequest(positionUrl + "/position/search/balance/etrSecPositionRule", param);
    }
    
    private static String httpPostRequest(String url, Object param){
        try {
            return HttpUtil.doPost(url, JsonUtils.object2Json(param));
        } catch (Exception e) {
            throw new PositionException(e.getMessage());
        }
    }
}
