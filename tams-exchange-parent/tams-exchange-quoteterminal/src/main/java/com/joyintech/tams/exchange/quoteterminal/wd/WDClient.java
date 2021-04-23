package com.joyintech.tams.exchange.quoteterminal.wd;

import cn.com.wind.td.tdf.*;
import com.joyintech.tams.exchange.common.proto.QuoteProto;
import com.joyintech.tams.exchange.quoteterminal.service.QuoteClient;
import com.joyintech.tams.exchange.quoteterminal.service.QuoteManager;
import com.joyintech.tams.exchange.quoteterminal.service.QuoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

//@Component
public class WDClient extends QuoteClient {
    private static final Logger logger = LoggerFactory.getLogger(WDClient.class);

    @Autowired
    private QuoteService quoteService;
    @Autowired
    private QuoteManager quoteManager;

    private TDFClient client = new TDFClient();
    private final int HEART_BEAT_INTERVAL = 0;//Heart Beat
    private final int MISSED_BEART_COUNT = 0;
    private final int OPEN_TIME_OUT = 0;
    @Value("${wd.login.ip:10.100.3.163}")
    private String ip;
    @Value("${wd.login.port:6221}")
    private int port;
    @Value("${wd.login.username:dev_test}")
    private String username;
    @Value("${wd.login.password:dev_test}")
    private String password;
    protected Boolean quitFlag;
    private volatile Boolean loginFlag = false;

    public Boolean getLoginFlag() {
        return loginFlag;
    }

    public void setLoginFlag(Boolean loginFlag) {
        this.loginFlag = loginFlag;
    }

    public Boolean getQuitFlag() {
        return quitFlag;
    }

    public void setQuitFlag(Boolean para) {
        this.quitFlag = para;
    }

    @Override
    public String getQuoteType() {
        return "stock";
    }

    public void addClient() {
        logger.info("addClient: wd");
        quoteManager.addClient("wd", this);
    }

    public void init() {
        logger.info("==init==");
        setEnv(TDF_ENVIRON_SETTING.TDF_ENVIRON_HEART_BEAT_INTERVAL, HEART_BEAT_INTERVAL);
        setEnv(TDF_ENVIRON_SETTING.TDF_ENVIRON_MISSED_BEART_COUNT, MISSED_BEART_COUNT);
        setEnv(TDF_ENVIRON_SETTING.TDF_ENVIRON_OPEN_TIME_OUT, OPEN_TIME_OUT);
        setEnv(TDF_ENVIRON_SETTING.TDF_ENVIRON_OUT_LOG, 1);
        while (!loginFlag) {
            try {
                login();
                Thread.sleep(3000);
            } catch (Exception e) {
                logger.error("init error",e);
            }
        }
    }

    public void login() {
        logger.info("==login==");
        this.quitFlag = false;
        TDF_OPEN_SETTING setting = new TDF_OPEN_SETTING();
        setting.setIp(ip);
        setting.setPort(Integer.toString(port));
        setting.setUser(username);
        setting.setPwd(password);
        setting.setMarkets("");
        setting.setSubScriptions("");
        setting.setTypeFlags(DATA_TYPE_FLAG.DATA_TYPE_NONE);
        setting.setTime(0);
        setting.setConnectionID(0);
        int reBack = client.open(setting);
        logger.info("login reBack:{}", reBack);
        if (reBack == TDF_ERR.TDF_ERR_SUCCESS) {
            WDHandler wdHandler = new WDHandler(client, this);
            Thread thread = new Thread(wdHandler);
            thread.start();
            //setSusbScription("", SUBSCRIPTION_STYLE.SUBSCRIPTION_SET);
        }
    }

    public void addTick(QuoteProto.TickBean tickBean) {
        quoteService.addTick(tickBean);
    }

    @Override
    public void release() {
        loginFlag = false;
        quitFlag = true;
        client.close();
    }

    public int setEnv(int Env, int value) {
        return client.setEnv(Env, value);
    }

    public void setSusbScription(String szWindCode, int style) {
        int reBack = client.setSubscription(szWindCode, style);
        logger.info("setSusbScription reBack:{}", reBack);
        if (reBack == TDF_ERR.TDF_ERR_SUCCESS) {
            WDHandler wdHandler = new WDHandler(client, this);
            Thread thread = new Thread(wdHandler);
            thread.start();
        }
    }
}
