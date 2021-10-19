package com.joyintech.tams.cfets.etrib.service;

import com.joyintech.tams.business.common.util.WebSocketClientUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class WebSocketService {
    
    private static String webSocketUrl;
    private static String webSocketKey;
    @Value("${webSocketUrl}")
    public void setWebSocketUrl(String webSocketUrl) {
        WebSocketService.webSocketUrl = webSocketUrl;
        if (WebSocketService.webSocketKey != null){
            startWebSocket();
        }
    }
    
    @Value("${webSocketKey}")
    public void setWebSocketKey(String webSocketKey) {
        WebSocketService.webSocketKey = webSocketKey;
        if (WebSocketService.webSocketUrl != null){
            startWebSocket();
        }
    }

    public static void startWebSocket(){
        /**
         * 头寸监听余额变化，更新投管数据库流水余额表
         */
        try {
            WebSocketClientUtil util = new WebSocketClientUtil();
            util.start(webSocketUrl, new WebSocketClientUtil.WebSocketListener() {
                @Override
                public void onMessage(String message) {
                    System.out.println("收到消息:"+message);
                }
            });
            util.send(webSocketKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
