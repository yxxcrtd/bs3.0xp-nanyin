package com.joyintech.tams.exchange.quoteterminal.ctp;

import com.alibaba.fastjson.JSONArray;
import com.joyintech.tams.exchange.quoteterminal.service.QuoteClient;
import com.joyintech.tams.exchange.quoteterminal.service.QuoteManager;
import com.joyintech.tams.exchange.quoteterminal.service.QuoteService;
import net.jctp.MdApi;
import net.jctp.TraderApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * create By MAXX on 2020/12/28
 */
@Component
public class CTPClient extends QuoteClient {
    private static final Logger logger = LoggerFactory.getLogger(CTPClient.class);
    @Value("${ctp.brokerId:9999}")
    private String brokerId;
    @Value("${ctp.pszNsAddress:tcp://180.168.146.187:10110}")
    private String pszNsAddress;
    @Value("${ctp.userId:159887}")
    private String userId;
    @Value("${ctp.password:891026Ljp}")
    private String password;
    @Value("${ctp.syncReqTimeout:30}")
    private int syncReqTimeout;
    @Value("${ctp.traderFrontUrl:tcp://180.168.146.187:10101}")
    private String traderFrontUrl;
    @Value("${ctp.authCode:}")
    private String authCode;
    @Value("${ctp.appId:}")
    private String appId;
    @Value("${ctp.exchanges:SHFE,DCE,CZCE,CFFEX,INE}")
    private String exchanges;
    @Autowired
    private QuoteManager quoteManager;
    @Autowired
    private QuoteService quoteService;
    private CTPTradeApi ctpTradeApi;
    private CTPMdApi ctpMdApi;

    @Override
    public void addClient() {
        logger.info("addClient: ctp");
        quoteManager.addClient("ctp", this);
    }

    @Override
    public String getQuoteType() {
        return "stock";
    }

    @Override
    public void init() {
        logger.info("==init==");
        logger.info("==initMdApi==");
        MdApi mdApi = new MdApi();
        mdApi.setListener(new CTPMdApiListener(quoteService));
        mdApi.setFlowControl(true);
        mdApi.setSyncReqTimeout(syncReqTimeout);
        ctpMdApi = new CTPMdApi(mdApi);
        logger.info("连接mdApi");
        ctpMdApi.init(pszNsAddress, brokerId, userId, password);
        logger.info("==initTraderApi==");
        TraderApi traderApi = new TraderApi();
        traderApi.setSyncReqTimeout(syncReqTimeout);
        traderApi.setFlowControl(true);
        ctpTradeApi = new CTPTradeApi(ctpMdApi, traderApi, authCode, appId, brokerId, userId, password, exchanges);
        ctpTradeApi.init(traderFrontUrl);
    }

    @Override
    public void release() {
        List<String> list = ctpTradeApi.getAllInstrument();
        List<String[]> reList = ListUtil.getList(list);
        reList.forEach(instrumentList -> {
            logger.debug("合约resp:{}", JSONArray.toJSONString(instrumentList));
            ctpMdApi.unSubscribeMarketData(instrumentList);
        });
        ctpTradeApi.logout();
    }
}
