package com.joyintech.tams.cfets.etrib.service;

import com.joyintech.tams.business.common.util.WebSocketClientUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Component
@Lazy(value=false)
public class WebSocketClient {

    @Value("${webSocketUrl}")
    private String webSocketUrl;
    
    @Value("${webSocketKey}")
    private String webSocketKey;
    
    @PostConstruct
    public void init(){
        start();
    }
    
    private WebSocketClientUtil util = null;
    
    public void start(){
        /**
         * 头寸监听余额变化，更新投管数据库流水余额表
         */
        try {
            if (util == null){
                util = new WebSocketClientUtil();
            }
            util.start(webSocketUrl, new WebSocketClientUtil.WebSocketListener() {
                @Override
                public void onMessage(String message) {
                    System.out.println("收到消息:"+message);
                }

				@Override
				public void onClose() {
					// TODO Auto-generated method stub
					// 10秒启动
                    System.out.println("头寸服务关闭");
                    try {
                        util.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        TimeUnit.SECONDS.sleep(2l);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    // 间隔时间再次发送
                    start();
                }
            });
            // [{"table":"ETR_CASH_POSITION_RULE", "hodingTypes":[]},{"table":"ETR_SEC_POSITION_RULE", "hodingTypes":[]},{"table":"PTL_CASH_POSITION_RULE", "hodingTypes":[]},{"table":"PTL_SEC_POSITION_RULE", "hodingTypes":[]}]
            util.send(webSocketKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
