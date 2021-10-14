package com.joyintech.tams.business.common.service;

import com.joyintech.framework.JoyinRestTemplate;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.business.common.exception.PositionException;
import com.joyintech.tams.business.common.util.HttpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@PropertySource("classpath:field.properties")
@Component
public class PositionHttpRequest {

    public final static String CODE_TIMEOUT = "410";        // mq超时，需冲正
    public final static String CODE_SEND_ERROR = "420";     // mq发送异常
    public final static String CODE_BUSI_ERROR = "430";     // mq返回业务异常
    public final static String CODE_BUSI_REFUSE = "200";    // mq返回业务信息拒绝，需前端确认

    private static String positionUrl;
    
    
    @Value("${positionUrl}")
    public void setQueuePositionCallback(String url) {
        PositionHttpRequest.positionUrl = url;
    }

    public static void send(Map req){
        // 
        Map<String, Object> message = sendPrivate(req);

        if (message == null || !StringUtil.equals("success", message.get("code"))){
            if(StringUtil.equals("FL0007", message.get("code"))){
                throw new PositionException(CODE_BUSI_REFUSE + "|" + (message==null?"":message.get("message")));
            }else{
                throw new PositionException(CODE_BUSI_ERROR + "|" + (message==null?"":message.get("message")));
            }
        }
    }

    private static Map<String, Object> sendPrivate(Map param) {
//        return restTemplate.postForObject(positionUrl + "/send", param, Map.class);
        return null;
    }

    public static String query(Map req) {
        String obj = null;
        try {
            obj = HttpUtil.doPost(positionUrl + "/etrSecPositionRule", JsonUtils.object2Json(req));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }
    
}
