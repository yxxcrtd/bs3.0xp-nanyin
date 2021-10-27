package com.joyintech.tams.cfets.etrib.socket;

import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.business.common.pojo.BaseMap;
import com.joyintech.tams.business.common.util.WebSocketClientUtil;
import com.joyintech.tams.cfets.etrib.service.PositionSocketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
@Lazy(value=false)
public class PositionSocketClient {
    private static final Logger logger = LoggerFactory.getLogger(PositionSocketClient.class);
    
    @Autowired
    private PositionSocketService webSocketService;
    
    @Value("${webSocketUrl}")
    private String webSocketUrl;
    
    @Value("${webSocketKey}")
    private String webSocketKey;

    boolean isStart = false;  // 是否启动开关
    boolean loopFlag = false; // 10秒重连循环开关
    
    @PostConstruct
    public void init(){
        logger.info("WebSocketClient初始化一次...");
        
        new Thread(new Runnable() {
            @Override
            public void run() {
                loopStart();
            }
        }).start();
    }

    private WebSocketClientUtil util = null;
    
    public boolean start(){
        
        if (isStart){
            return true;
        }
        
        boolean rs = true;
        /**
         * 头寸监听余额变化，更新投管数据库流水余额表
         */
        try {
            util = new WebSocketClientUtil();
            
            util.start(webSocketUrl, new WebSocketClientUtil.WebSocketListener() {
                @Override
                public void onMessage(String message) {
                    try {
                        List<String> list = JsonUtils.json2Object(message, List.class);
                        
                        logger.info("收到webSocket消息:"+message);
                        
                        if (list != null && list.size() > 0 ){
                            // 转换成map
                            BaseMap dataMap = convertedMap(list);
                            // long转换成日期格式
                            dataMap.put("CDATE", dataMap.getDate("CDATE"));

                            webSocketService.insertOrUpdate(dataMap);
                            
                            logger.info("socket同步数据结束：" + dataMap);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        logger.error("头寸数据同步解析失败！");
                    }
                }
                
                @Override
                public void onClose() {
                    // 重连
                    logger.info("头寸服务关闭，开始重连");
                    
                    try {
                        util.close();} catch (IOException e) {e.printStackTrace();}
                    isStart = false;
                    
                    // 每间隔10秒重连
                    loopStart();

                    isStart = true; // 启动成功
                    logger.info("头寸服务启动成功。。。");
                }
            });

            // 启动成功
            isStart = true; // 服务启动成功
            
            // 启动时发送一次
            send();
        } catch (Exception e) {
            e.printStackTrace();
            rs = false;
            isStart = false; // 服务启动失败
        }
        return rs;
    }


    public void loopStart(){
        if(loopFlag){
            return;
        }
        loopFlag = true;
        synchronized (this){
            while (!start()){
                logger.error(webSocketUrl + ":头寸通信失败，10秒后重连");
                try {TimeUnit.SECONDS.sleep(10l);} catch (InterruptedException e) {e.printStackTrace();}
            }
        }
        loopFlag =false;
    }


    /**
     * 转换map
     * @param list
     * @return
     */
    private BaseMap convertedMap(List<String> list) {
        String tableName = list.get(0);
        if (StringUtil.equals(tableName, "ETR_CASH_POSITION")){
            return BaseMap.newMap()
                    .put("tableName", "ETR_CASH_POSITION_RULE")
                    .put("PORTFOLIO_ID",list.get(2))
                    .put("ACCT_ID",list.get(3))
                    .put("HODING_TYPE", list.get(4))
                    .put("CDATE", list.get(5))
                    .put("DATE_TYPE", list.get(6))
                    .put("CCY", list.get(7))
                    .put("AMOUNT", list.get(8))
                    ;
        }else if (StringUtil.equals(tableName, "ETR_SEC_POSITION")){
            return BaseMap.newMap()
                    .put("tableName", "ETR_SEC_POSITION_RULE")
                    .put("PORTFOLIO_ID",list.get(2))
                    .put("SEC_ACCT_ID",list.get(3))
                    .put("FINPROD_ID", list.get(4))
                    .put("INV_AIM", list.get(5))
                    .put("HODING_TYPE", list.get(6))
                    .put("CDATE", list.get(7))
                    .put("DATE_TYPE", list.get(8))
                    .put("CCY", list.get(9))
                    .put("AMOUNT", list.get(10))
                    ;
        }else if (StringUtil.equals(tableName, "PTL_SEC_POSITION")){
            return BaseMap.newMap()
                    .put("tableName", "PTL_SEC_POSITION_RULE")
                    .put("PORTFOLIO_ID",list.get(2))
                    .put("SEC_ACCT_ID",list.get(3))
                    .put("FINPROD_ID", list.get(4))
                    .put("INV_AIM", list.get(5))
                    .put("HODING_TYPE", list.get(6))
                    .put("CDATE", list.get(7))
                    .put("DATE_TYPE", list.get(8))
                    .put("CCY", list.get(9))
                    .put("AMOUNT", list.get(10))
                    ;
        }else if (StringUtil.equals(tableName, "PTL_CASH_POSITION")){
            return BaseMap.newMap()
                    .put("tableName", "PTL_CASH_POSITION_RULE")
                    .put("PORTFOLIO_ID",list.get(2))
                    .put("ACCT_ID",list.get(3))
                    .put("HODING_TYPE", list.get(4))
                    .put("CDATE", list.get(5))
                    .put("DATE_TYPE", list.get(6))
                    .put("CCY", list.get(7))
                    .put("AMOUNT", list.get(8))
                    ;
        }
        //
        return null;
    }

    public void send(){
        // [{"table":"ETR_CASH_POSITION_RULE", "hodingTypes":[]},{"table":"ETR_SEC_POSITION_RULE", "hodingTypes":[]},{"table":"PTL_CASH_POSITION_RULE", "hodingTypes":[]},{"table":"PTL_SEC_POSITION_RULE", "hodingTypes":[]}]
        util.send(webSocketKey);
        logger.info("头寸服务发送成功。。。");
    }
}
