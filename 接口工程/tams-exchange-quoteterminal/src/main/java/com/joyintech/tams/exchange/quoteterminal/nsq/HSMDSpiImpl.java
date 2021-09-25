package com.joyintech.tams.exchange.quoteterminal.nsq;

import com.gtn.nsq.javasdk.HSDataType;
import com.gtn.nsq.javasdk.HSMdSpi;
import com.gtn.nsq.javasdk.hsstruct.*;
import com.joyintech.tams.exchange.common.proto.QuoteProto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class HSMDSpiImpl extends HSMdSpi {

    private static final Logger logger = LoggerFactory.getLogger(HSMDSpiImpl.class);

    private HSMDClient hsmdClient;

    private ThreadLocal<QuoteProto.TickBean.Builder> threadLocalBuilder = ThreadLocal.withInitial(QuoteProto.TickBean::newBuilder);

    public HSMDSpiImpl(HSMDClient hsmdClient) {
        this.hsmdClient = hsmdClient;
    }

    @Override
    public void OnFrontConnected() {
        // 登录
        HSReqUserLoginField loginField = new HSReqUserLoginField(hsmdClient.getUserName(), hsmdClient.getPassword(),
                '\0', "", "", "" );
        this.hsmdClient.getMdApi().ReqUserLogin(loginField, 0);
    }

    @Override
    public void OnFrontDisconnected(int result) {

    }

    @Override
    public void OnRspUserLogin(HSRspUserLoginField rspUserLogin, HSRspInfoField rspInfo, int requestID, boolean isLast) {

        //以下是期权
        HSReqOptDepthMarketDataField[] optReqFields = new HSReqOptDepthMarketDataField[1];
        optReqFields[0] = new HSReqOptDepthMarketDataField(HSDataType.HS_EI_SSE, "");

        hsmdClient.subscribeOption();
        hsmdClient.subscribeSecurity();
    }

    @Override
    public void OnRtnOptDepthMarketData(HSOptDepthMarketDataField optDepthMarketData) {
        QuoteProto.TickBean.Builder builder = threadLocalBuilder.get();
        // todo 转换
        HSNSQConverter.convertToTick(optDepthMarketData, builder);
        hsmdClient.addTick(builder.build());
    }

    @Override
    public void OnRtnSecuDepthMarketData(HSSecuDepthMarketDataField secuDepthMarketData, long[] bid1Volume, int bid1Count,
                                         int maxBid1Count, long[] ask1Volume, int ask1Count, int maxAsk1Count) {
        QuoteProto.TickBean.Builder builder = threadLocalBuilder.get();
        HSNSQConverter.convertToTick(secuDepthMarketData, builder);
        hsmdClient.addTick(builder.build());
    }

    @Override
    public void OnRtnFutuDepthMarketData(HSFutuDepthMarketDataField futuDepthMarketData) {

    }

    @Override
    public void OnRspFutuDepthMarketDataSubscribe(HSRspInfoField rspInfo, int requestID, boolean isLast) {

    }

    @Override
    public void OnRspFutuDepthMarketDataCancel(HSRspInfoField rspInfo, int requestID, boolean isLast) {

    }

    @Override
    public void OnRspQryFutuInstruments(HSFutuInstrumentStaticInfoField futuInstrumentStaticInfo, HSRspInfoField rspInfo, int requestID, boolean isLast) {

    }

    @Override
    public void OnRspQryFutuDepthMarketData(HSFutuDepthMarketDataField futuDepthMarketData, HSRspInfoField rspInfo, int requestID, boolean isLast) {

    }

    @Override
    public void OnRspSecuDepthMarketDataSubscribe(HSRspInfoField rspInfo, int requestID, boolean isLast) {

    }

    @Override
    public void OnRspSecuDepthMarketDataCancel(HSRspInfoField rspInfo, int requestID, boolean isLast) {

    }


    @Override
    public void OnRspSecuTransactionSubscribe(HSRspInfoField rspInfo, int requestID, boolean isLast) {

    }

    @Override
    public void OnRspSecuTransactionCancel(HSRspInfoField rspInfo, int requestID, boolean isLast) {

    }

    @Override
    public void OnRtnSecuTransactionTradeData(HSSecuTransactionTradeDataField secuTransactionTradeData) {

    }

    @Override
    public void OnRtnSecuTransactionEntrustData(HSSecuTransactionEntrustDataField secuTransactionEntrustData) {

    }

    @Override
    public void OnRspQrySecuInstruments(HSSecuInstrumentStaticInfoField secuInstrumentStaticInfo, HSRspInfoField rspInfo, int requestID, boolean isLast) {

    }

    @Override
    public void OnRspQrySecuDepthMarketData(HSSecuDepthMarketDataField secuDepthMarketData, HSRspInfoField rspInfo, int requestID, boolean isLast) {

    }

    @Override
    public void OnRspOptDepthMarketDataSubscribe(HSRspInfoField rspInfo, int requestID, boolean isLast) {

    }

    @Override
    public void OnRspOptDepthMarketDataCancel(HSRspInfoField rspInfo, int requestID, boolean isLast) {

    }

    @Override
    public void OnRspQryOptInstruments(HSOptInstrumentStaticInfoField optInstrumentStaticInfo, HSRspInfoField rspInfo, int requestID, boolean isLast) {

    }

    @Override
    public void OnRspQryOptDepthMarketData(HSOptDepthMarketDataField optDepthMarketData, HSRspInfoField rspInfo, int requestID, boolean isLast) {

    }
}
