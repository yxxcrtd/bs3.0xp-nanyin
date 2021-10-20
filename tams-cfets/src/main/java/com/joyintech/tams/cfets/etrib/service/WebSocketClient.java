package com.joyintech.tams.cfets.etrib.service;

import com.joyintech.tams.business.common.util.WebSocketClientUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class WebSocketClient {

    @Value("${webSocketUrl}")
    private String webSocketUrl;
    
    @Value("${webSocketKey}")
    private String webSocketKey;
    
    @PostConstruct
    public void init(){
        startWebSocket();
    }
    
    public void startWebSocket(){
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

				@Override
				public void onClose() {
					// TODO Auto-generated method stub
					// 10秒启动
				}
            });
            // [{"table":"ETR_CASH_POSITION_RULE", "hodingTypes":[]},{"table":"ETR_SEC_POSITION_RULE", "hodingTypes":[]},{"table":"PTL_CASH_POSITION_RULE", "hodingTypes":[]},{"table":"PTL_SEC_POSITION_RULE", "hodingTypes":[]}]
            util.send(webSocketKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
