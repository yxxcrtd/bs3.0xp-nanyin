package com.joyintech.tams.exchange.quoteterminal.ctp;

import com.alibaba.fastjson.JSONArray;
import com.google.common.base.Splitter;
import com.google.common.collect.Maps;
import com.joyintech.tams.exchange.common.utils.JsonUtils;
import net.jctp.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * create By MAXX on 2020/12/29
 */
public class CTPTradeApi {
    private static final Logger logger = LoggerFactory.getLogger(CTPTradeApi.class);
    private TraderApi traderApi;
    private CTPMdApi ctpMdApi;
    private String authCode;
    private String appId;
    private String brokerId;
    private String userId;
    private String password;
    private String exchanges;
    private static Map<String, String> instrumentMap = Maps.newConcurrentMap();

    public static String getInstrumentMap(String instrumentID) {
        return instrumentMap.get(instrumentID);
    }

    public CTPTradeApi(CTPMdApi ctpMdApi, TraderApi traderApi, String authCode, String appId, String brokerId, String userId, String password, String exchanges) {
        this.ctpMdApi = ctpMdApi;
        this.traderApi = traderApi;
        this.authCode = authCode;
        this.appId = appId;
        this.brokerId = brokerId;
        this.userId = userId;
        this.password = password;
        this.exchanges = exchanges;
    }

    public void init(String traderFrontUrl) {
        try {
            traderApi.SubscribePrivateTopic(JctpConstants.THOST_TERT_QUICK);
            traderApi.SubscribePublicTopic(JctpConstants.THOST_TERT_QUICK);
            traderApi.SyncConnect(traderFrontUrl);
            reAuthenticate();
        } catch (Exception e) {
            logger.error("CTPTradeApi初始化异常:{}", e);
        }
    }

    public void reAuthenticate() {
        logger.info("客户端认证请求");
        CThostFtdcReqAuthenticateField reqAuthenticateField = new CThostFtdcReqAuthenticateField();
        reqAuthenticateField.AuthCode = authCode;
        reqAuthenticateField.AppID = appId;
        reqAuthenticateField.BrokerID = brokerId;
        reqAuthenticateField.UserID = userId;
        try {
            CThostFtdcRspAuthenticateField rspAuthenticateField = traderApi.SyncReqAuthenticate(reqAuthenticateField);
            logger.debug("req reAuthenticate: {}", JsonUtils.getJsonFromObject(rspAuthenticateField));
            login();
        } catch (Exception e) {
            logger.error("客户端认证请求异常:{}", e);
        }
    }

    public void login() {
        logger.info("用户登录请求");
        CThostFtdcReqUserLoginField loginField = new CThostFtdcReqUserLoginField();
        loginField.BrokerID = brokerId;
        loginField.UserID = userId;
        loginField.Password = password;
        try {
            CThostFtdcRspUserLoginField rspUserLoginField = traderApi.SyncReqUserLogin(loginField);
            logger.debug("req login: {}", JsonUtils.getJsonFromObject(rspUserLoginField));
            List<String> list = getAllInstrument();
            List<String[]> reList = ListUtil.getList(list);
            reList.forEach(instrumentList -> {
                logger.debug("合约resp:{}", JSONArray.toJSONString(instrumentList));
                ctpMdApi.subscribeMarketData(instrumentList);
            });
        } catch (Exception e) {
            logger.error("用户登录请求异常:{}", e);
        }
    }

    public void logout() {
        CThostFtdcUserLogoutField logoutField = new CThostFtdcUserLogoutField();
        logoutField.BrokerID = brokerId;
        logoutField.UserID = userId;
        try {
            traderApi.SyncReqUserLogout(logoutField);
        } catch (Exception e) {
            logger.error("用户登出请求异常:{}", e);
        }
    }

    public List<String> getAllInstrument() {
        logger.info("请求查询合约:{}", exchanges);
        Iterator<String> iterator = Splitter.on(",").split(exchanges).iterator();
        List<String> list = new ArrayList<String>();
        CThostFtdcQryInstrumentField instrumentField = new CThostFtdcQryInstrumentField();
        try {
            while (iterator.hasNext()) {
                instrumentField.ExchangeID = iterator.next();
                CThostFtdcInstrumentField[] instrumentFields = traderApi.SyncAllReqQryInstrument(instrumentField);
                logger.debug("请求查询返回:{}", JSONArray.toJSONString(instrumentFields));
                for (CThostFtdcInstrumentField instrument : instrumentFields) {
                    list.add(instrument.InstrumentID);
                    instrumentMap.put(instrument.InstrumentID, instrument.ExchangeID);
                }
            }
        } catch (Exception e) {
            logger.error("请求查询合约异常:{}", e);
        }
        return list;
    }
}
