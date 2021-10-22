package com.joyintech.tams.cfets.etrib.service;

import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.business.common.pojo.BaseMap;
import com.joyintech.tams.business.common.util.WebSocketClientUtil;
import com.joyintech.tams.cfets.etrib.dao.WebSocketMapper;
import org.codehaus.groovy.runtime.ConvertedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
@Lazy(value=false)
public class WebSocketClient {
    private static final Logger logger = LoggerFactory.getLogger(WebSocketClient.class);
    
    @Autowired
    private WebSocketMapper webSocketMapper;
    
    @Value("${webSocketUrl}")
    private String webSocketUrl;
    
    @Value("${webSocketKey}")
    private String webSocketKey;
    
    @PostConstruct
    public void init(){
        startWb();
    }
    
    private WebSocketClientUtil util = null;
    
    public boolean startWb(){
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
                        List<String> list = JsonUtils.json2Object(message, List.class);
                        System.out.println("收到消息:"+message);
                        if (list != null && list.size() == 10){
                            
                            // 转换成map
                            BaseMap map = convertedMap(list);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        logger.error("头寸数据同步解析失败！");
                    }
                }

                private void executeETR_CASH_POSITION(List<String> list) {
                    String tableName = list.get(0);
                    BigDecimal amount = new BigDecimal(list.get(8));
                    webSocketMapper.select(BaseMap.newMap().put("tableName",tableName));
                }

                @Override
                public void onClose() {
                    // 10秒启动
                    logger.info("头寸服务关闭。。。10秒后重连");
                    try {util.close();} catch (IOException e) {e.printStackTrace();}
                    
                    while (!startWb()){
                        // 间隔时间再次发送
                        try {TimeUnit.SECONDS.sleep(10l);} catch (InterruptedException e) {e.printStackTrace();}
                    }
                    logger.info("头寸服务启动成功。。。");
                }
            });
            
            // 启动时发送一次
            send();
        } catch (Exception e) {
            e.printStackTrace();
            rs = false;
        }
        return rs;
    }

    private BaseMap convertedMap(List<String> list) {
         String tableName = list.get(0);
        if (StringUtil.equals(tableName, "ETR_CASH_POSITION")){
            BaseMap.newMap()
                    .put("PORTFOLIO_ID",list.get(2))
                    .put("ACCT_ID",list.get(3))
                    .put("HODING_TYPE", list.get(4))
                    .put("CDATE", list.get(5))
            
            ;
        }
        return null;
    }

    public void send(){
        // [{"table":"ETR_CASH_POSITION_RULE", "hodingTypes":[]},{"table":"ETR_SEC_POSITION_RULE", "hodingTypes":[]},{"table":"PTL_CASH_POSITION_RULE", "hodingTypes":[]},{"table":"PTL_SEC_POSITION_RULE", "hodingTypes":[]}]
        util.send(webSocketKey);
        logger.info("头寸服务发送成功。。。");
    }
}
