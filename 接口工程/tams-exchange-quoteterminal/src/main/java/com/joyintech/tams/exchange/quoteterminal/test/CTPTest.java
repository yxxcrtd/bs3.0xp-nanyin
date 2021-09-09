package com.joyintech.tams.exchange.quoteterminal.test;

import com.alibaba.fastjson.JSONArray;
import com.google.common.base.Splitter;
import com.joyintech.tams.exchange.quoteterminal.ctp.CTPMdApi;
import com.joyintech.tams.exchange.quoteterminal.ctp.CTPMdApiListener;
import com.joyintech.tams.exchange.quoteterminal.ctp.CTPTradeApi;
import com.joyintech.tams.exchange.quoteterminal.service.QuoteService;
import net.jctp.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * create By MAXX on 2020/12/28
 */
public class CTPTest {
    private static final Logger logger = LoggerFactory.getLogger(CTPTest.class);
    private static String pszNsAddress = "tcp://180.168.146.187:10110";
    private static String pszNsAddress2 = "tcp://180.168.146.187:10111";

    public static void init() throws Exception {
        String brokerId = "9999";
        String userId = "159887";
        String password = "891026Ljp";
        String traderFrontUrl = "tcp://180.168.146.187:10101";
        String exchanges = "SHFE";
        String authCode = "";
        String appId = "";
        MdApi mdApi = new MdApi();
        logger.info("CTP mdApi VERSION: " + mdApi.GetApiVersion());
        int requestID = 1;
        int syncReqTimeout = 30;
        mdApi.setListener(new CTPMdApiListener(new QuoteService()));
        mdApi.setFlowControl(true);
        mdApi.setSyncReqTimeout(syncReqTimeout);
        mdApi.RegisterFront(pszNsAddress);
        mdApi.SyncConnect(pszNsAddress, brokerId, userId, password);
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
        }

        /*CThostFtdcReqUserLoginField cthostFtdcReqUserLoginField = new CThostFtdcReqUserLoginField();
        cthostFtdcReqUserLoginField.BrokerID = brokerId;
        cthostFtdcReqUserLoginField.UserID = userId;
        cthostFtdcReqUserLoginField.Password = password;
        cthostFtdcReqUserLoginField.MacAddress = mac;
        cthostFtdcReqUserLoginField.ClientIPAddress = ipAddress;
        mdApi.ReqUserLogin(cthostFtdcReqUserLoginField, requestID);
        try {
            Thread.sleep(2000);
        } catch (Exception e) {

        }*/
        TraderApi traderApi = new TraderApi();
        CTPMdApi ctpMdApi = new CTPMdApi(mdApi);
        CTPTradeApi ctpTradeApi = new CTPTradeApi(ctpMdApi, traderApi, authCode, appId, brokerId, userId, password, exchanges);
        traderApi.setFlowControl(true);
        traderApi.SubscribePrivateTopic(JctpConstants.THOST_TERT_QUICK);
        traderApi.SubscribePublicTopic(JctpConstants.THOST_TERT_QUICK);
        logger.info("连接 TraderApi");
        traderApi.SyncConnect(traderFrontUrl);
        try {
            Thread.sleep(2000);
        } catch (Exception e) {
        }
        logger.info("=======ReqAuthenticate========");
        CThostFtdcReqAuthenticateField reqAuthenticateField = new CThostFtdcReqAuthenticateField();
        reqAuthenticateField.AuthCode = authCode;
        reqAuthenticateField.AppID = appId;
        reqAuthenticateField.BrokerID = brokerId;
        reqAuthenticateField.UserID = userId;
        traderApi.SyncReqAuthenticate(reqAuthenticateField);
        try {
            Thread.sleep(2000);
        } catch (Exception e) {
        }
        logger.info("=======ReqUserLogin========");
        CThostFtdcReqUserLoginField loginField = new CThostFtdcReqUserLoginField();
        loginField.BrokerID = brokerId;
        loginField.UserID = userId;
        loginField.Password = password;
        traderApi.SyncReqUserLogin(loginField);
        try {
            Thread.sleep(2000);
        } catch (Exception e) {
        }
        logger.info("=======SyncAllReqQryInstrument========");
        Iterator<String> iterator = Splitter.on(",").split(exchanges).iterator();
        List<String> list = new ArrayList<String>();
        CThostFtdcQryInstrumentField cThostFtdcQryInstrumentField = new CThostFtdcQryInstrumentField();
        while (iterator.hasNext()) {
            cThostFtdcQryInstrumentField.ExchangeID = iterator.next();
            try {
                CThostFtdcInstrumentField[] instrumentFields = traderApi.SyncAllReqQryInstrument(cThostFtdcQryInstrumentField);
                for (CThostFtdcInstrumentField instrument : instrumentFields) {
                    list.add(instrument.InstrumentID);
                }
            } catch (Exception e) {
            }
        }
        String[] instrumentList = list.toArray(new String[0]);
        logger.info(JSONArray.toJSONString(instrumentList));
        try {
            Thread.sleep(2000);
        } catch (Exception e) {
        }
        mdApi.SubscribeMarketData(instrumentList);
        try {
            Thread.sleep(7000);
        } catch (Exception e) {
        }
        logger.info("UnSubscribeMarketData");
        mdApi.UnSubscribeMarketData(instrumentList);
        try {
            Thread.sleep(2000);
        } catch (Exception e) {
        }
        CThostFtdcUserLogoutField logoutField = new CThostFtdcUserLogoutField();
        logoutField.BrokerID = brokerId;
        logoutField.UserID = userId;
        traderApi.SyncReqUserLogout(logoutField);
        try {
            Thread.sleep(2000);
        } catch (Exception e) {
        }
    }

    public static void main(String[] args) {
        try {
            init();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
