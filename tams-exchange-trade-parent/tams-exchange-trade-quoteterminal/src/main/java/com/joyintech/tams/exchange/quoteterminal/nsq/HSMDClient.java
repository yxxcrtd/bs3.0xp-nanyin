package com.joyintech.tams.exchange.quoteterminal.nsq;


import com.gtn.nsq.javasdk.HSDataType;
import com.gtn.nsq.javasdk.HSMdApi;
import com.gtn.nsq.javasdk.HSMdApiImpl;
import com.gtn.nsq.javasdk.hsstruct.HSReqOptDepthMarketDataField;
import com.gtn.nsq.javasdk.hsstruct.HSReqSecuDepthMarketDataField;
import com.joyintech.tams.exchange.common.proto.QuoteProto;
import com.joyintech.tams.exchange.quoteterminal.service.QuoteClient;
import com.joyintech.tams.exchange.quoteterminal.service.QuoteManager;
import com.joyintech.tams.exchange.quoteterminal.service.QuoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

//@Component
public class HSMDClient extends QuoteClient {
    private static final Logger logger = LoggerFactory.getLogger(HSMDClient.class);

    private final HSMdApi mdApi = HSMdApiImpl.NewMdApi("/log/quote/nsq");

    private String userName = "nsq_job2";
    private String password = "nsq_job2";

    @Autowired
    private QuoteService quoteService;
    @Autowired
    private QuoteManager quoteManager;

    private AtomicInteger requestID = new AtomicInteger(0);

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public HSMdApi getMdApi() {
        return mdApi;
    }

    @Override
    public String getQuoteType() {
        return "stock";
    }

    public void addClient() {
        logger.info("addClient: nsq");
        quoteManager.addClient("nsq", this);
    }

    public void init(){
        mdApi.RegisterSpi(new HSMDSpiImpl(this));
        mdApi.Init("", "", "", "", "");
//        mdApi.Join();
    }

    public void addTick(QuoteProto.TickBean tickBean){
        quoteService.addTick(tickBean);
    }

    public void subscribeOption(){
        HSReqOptDepthMarketDataField[] optReqFields = new HSReqOptDepthMarketDataField[2];
        optReqFields[0] = new HSReqOptDepthMarketDataField(HSDataType.HS_EI_SSE, "");
        optReqFields[1] = new HSReqOptDepthMarketDataField(HSDataType.HS_EI_SZSE, "");
        mdApi.ReqOptDepthMarketDataSubscribe(optReqFields, 0, requestID.addAndGet(1));
    }

    public void subscribeSecurity() {
        HSReqSecuDepthMarketDataField[] reqFields = new HSReqSecuDepthMarketDataField[2];
        reqFields[0] = new HSReqSecuDepthMarketDataField(HSDataType.HS_EI_SSE, "");
        reqFields[1] = new HSReqSecuDepthMarketDataField(HSDataType.HS_EI_SZSE, "");
        mdApi.ReqSecuDepthMarketDataSubscribe(reqFields, 0, requestID.addAndGet(1));
    }

    public void unSubscribeOption() {
        logger.info("unSubscribeOption");
        HSReqOptDepthMarketDataField[] optReqFields = new HSReqOptDepthMarketDataField[2];
        optReqFields[0] = new HSReqOptDepthMarketDataField(HSDataType.HS_EI_SSE, "");
        optReqFields[1] = new HSReqOptDepthMarketDataField(HSDataType.HS_EI_SZSE, "");
        mdApi.ReqOptDepthMarketDataCancel(optReqFields, 0, requestID.addAndGet(1));
    }

    public void unSubscribeSecurity() {
        logger.info("unSubscribeSecurity");
        HSReqSecuDepthMarketDataField[] reqFields = new HSReqSecuDepthMarketDataField[2];
        reqFields[0] = new HSReqSecuDepthMarketDataField(HSDataType.HS_EI_SSE, "");
        reqFields[1] = new HSReqSecuDepthMarketDataField(HSDataType.HS_EI_SZSE, "");
        mdApi.ReqSecuDepthMarketDataCancel(reqFields, 0, requestID.addAndGet(1));
    }

    public void release() {
        unSubscribeOption();
        unSubscribeSecurity();
        mdApi.ReleaseApi();
    }
}
