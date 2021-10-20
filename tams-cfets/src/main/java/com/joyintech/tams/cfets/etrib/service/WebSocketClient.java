package com.joyintech.tams.cfets.etrib.service;

import com.joyintech.tams.business.common.util.WebSocketClientUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class WebSocketClient {
    
    private static String webSocketUrl;
    private static String webSocketKey;
    @Value("${webSocketUrl}")
    public void setWebSocketUrl(String webSocketUrl) {
        WebSocketClient.webSocketUrl = webSocketUrl;
        if (WebSocketClient.webSocketKey != null){
            startWebSocket();
        }
    }
    
    @Value("${webSocketKey}")
    public void setWebSocketKey(String webSocketKey) {
        WebSocketClient.webSocketKey = webSocketKey;
        if (WebSocketClient.webSocketUrl != null){
            startWebSocket();
        }
    }

    private static WebSocketClientUtil util = null;
    public static void startWebSocket(){
        /**
         * 头寸监听余额变化，更新投管数据库流水余额表
         */
        try {
            if(util == null){
                util = new WebSocketClientUtil();
            }
            start();
            util.send("{\"table\":\"ETR_SEC_POSITION_RULE\"}");
        } catch (Exception e) {
            try {
                util.close();
                
                TimeUnit.SECONDS.sleep(10L);
            } catch (Exception e1) {
                e.printStackTrace();
            }
            // 连接失败后10秒重连
            startWebSocket();
        }
    }
    
    private static void start() throws Exception {
        util.start(webSocketUrl, new WebSocketClientUtil.WebSocketListener() {
            @Override
            public void onMessage(String message) {
                System.out.println("收到消息:"+message);
            }

            @Override
            public void onClose() {
                // TODO Auto-generated method stub

            }
        });
    }
}
