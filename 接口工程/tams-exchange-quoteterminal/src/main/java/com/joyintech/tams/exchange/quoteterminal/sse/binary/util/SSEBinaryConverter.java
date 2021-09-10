package com.joyintech.tams.exchange.quoteterminal.sse.binary.util;

import com.joyintech.tams.exchange.common.proto.QuoteProto;
import com.joyintech.tams.exchange.quoteterminal.sse.binary.model.message.SSEBinaryLevel1MarketSnapshot;
import com.joyintech.tams.exchange.quoteterminal.sse.binary.resolver.UpperLowerLimitPrice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SSEBinaryConverter {

    private static final Logger logger = LoggerFactory.getLogger(SSEBinaryConverter.class);

    public static void convertToTick(SSEBinaryLevel1MarketSnapshot data, QuoteProto.TickBean.Builder builder) {
        builder.clear();
        builder.setExchange("sh");
        builder.setInstrumentID(data.getSecurityId().getValue());
        builder.setLastPrice(data.getLastPx());
        builder.setOpenPrice(data.getOpenPx());
        builder.setHighPrice(data.getHighPx());
        builder.setLowPrice(data.getLowPx());
        builder.setVolume(data.getTotalVolumeTraded());
        builder.setTurnover(0); //nsq 没有换手率
        builder.setTradeBalance(data.getTotalValueTraded());
        builder.setUpperLimitPrice(UpperLowerLimitPrice.getUpperPrice(data.getSecurityId().getValue()));
        builder.setLowerLimitPrice(UpperLowerLimitPrice.getLowerPrice(data.getSecurityId().getValue()));
        builder.setPreClosePrice(data.getPreClosePx().getDoubleValue());
        for (int i = 0; i < data.getOfferPrices().size(); i++) {
            builder.addAskPrice(data.getOfferPrices().get(i));
        }
        for (int i = 0; i < data.getOfferVolumes().size(); i++) {
            builder.addAskVolume(data.getOfferVolumes().get(i).intValue());
        }
        for (int i = 0; i < data.getBidPrices().size(); i++) {
            builder.addBidPrice(data.getBidPrices().get(i));
        }
        for (int i = 0; i < data.getBidVolumes().size(); i++) {
            builder.addBidVolume(data.getBidVolumes().get(i).intValue());
        }
        builder.setUpdateTime(data.getTradeDate() * 1000000000 + data.getLastUpdateTime());
    }
}
