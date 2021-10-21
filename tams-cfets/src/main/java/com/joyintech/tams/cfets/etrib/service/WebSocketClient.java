package com.joyintech.tams.cfets.etrib.service;

import com.joyintech.framework.util.JsonUtils;
import com.joyintech.tams.business.common.pojo.BaseMap;
import com.joyintech.tams.business.common.util.WebSocketClientUtil;
import com.joyintech.tams.cfets.etrib.controller.CfetsEtrIbBondinfoController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Component
@Lazy(value=false)
public class WebSocketClient {
    private static final Logger logger = LoggerFactory.getLogger(WebSocketClient.class);
    
    @Value("${webSocketUrl}")
    private String webSocketUrl;
    
    @Value("${webSocketKey}")
    private String webSocketKey;
    
    @PostConstruct
    public void init(){
        start();
    }
    
    private WebSocketClientUtil util = null;
    
    public boolean start(){
        boolean rs = true;
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
                    try {
                        BaseMap bm = JsonUtils.json2Object(message, BaseMap.class);
                        System.out.println("收到消息:"+message);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onClose() {
                    // 10秒启动
                    logger.info("头寸服务关闭。。。10秒后重连");
                    try {util.close();} catch (IOException e) {e.printStackTrace();}
                    
                    while (!start()){
                        // 间隔时间再次发送
                        try {TimeUnit.SECONDS.sleep(2l);} catch (InterruptedException e) {e.printStackTrace();}
                    }

                    logger.info("头寸服务启动成功。。。");
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            rs = false;
        }
        return rs;
    }
    
    public void send(){
        // [{"table":"ETR_CASH_POSITION_RULE", "hodingTypes":[]},{"table":"ETR_SEC_POSITION_RULE", "hodingTypes":[]},{"table":"PTL_CASH_POSITION_RULE", "hodingTypes":[]},{"table":"PTL_SEC_POSITION_RULE", "hodingTypes":[]}]
        util.send(webSocketKey);
    }
}
