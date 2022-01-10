package com.joyintech.tams.exchange.quoteterminal.ctp;

import com.alibaba.fastjson.JSONArray;
import net.jctp.MdApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * create By MAXX on 2020/12/28
 */
public class CTPMdApi {
    private static final Logger logger = LoggerFactory.getLogger(CTPMdApi.class);
    private MdApi mdApi;

    public CTPMdApi(MdApi mdApi) {
        this.mdApi = mdApi;
    }

    public void init(String pszNsAddress, String brokerId, String userId, String password) {
        try {
            mdApi.RegisterFront(pszNsAddress);
            mdApi.SyncConnect(pszNsAddress, brokerId, userId, password);
            logger.info("syncConnect mdApi");
        } catch (Exception e) {
            logger.error("CTPMdApi初始化异常:{}", e);
        }
    }

    public void subscribeMarketData(String[] instruments) {
        logger.debug("订阅行情:{}", JSONArray.toJSONString(instruments));
        try {
            mdApi.SubscribeMarketData(instruments);
        } catch (Exception e) {
            logger.error("订阅行情异常:{}", e);
        }
    }

    public void unSubscribeMarketData(String[] instruments) {
        logger.debug("取消订阅行情:{}", JSONArray.toJSONString(instruments));
        try {
            mdApi.UnSubscribeMarketData(instruments);
        } catch (Exception e) {
            logger.error("取消订阅行情异常:{}", e);
        }
    }
}
