package com.joyintech.tams.exchange.quoteterminal.szse.binary.util;

import com.joyintech.tams.exchange.common.constant.Market;
import com.joyintech.tams.exchange.common.proto.QuoteProto;
import com.joyintech.tams.exchange.common.utils.StockCodeUtils;
import com.joyintech.tams.exchange.quoteterminal.szse.binary.model.message.SZSEBinaryLevel1MarketSnapshot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SZSEBinaryConverter {

    private static final Logger logger = LoggerFactory.getLogger(SZSEBinaryConverter.class);

    public static void convertToTick(SZSEBinaryLevel1MarketSnapshot  data, QuoteProto.TickBean.Builder builder) {
        builder.clear();
        builder.setExchange(Market.SZSE.value);
        String stockCode = StockCodeUtils.formatStockCode(data.getSecurityId().getValue(), Market.SZSE.value);
        builder.setInstrumentID(stockCode);
        builder.setLastPrice(data.getLastPx());
        builder.setOpenPrice(data.getOpenPx());
        builder.setHighPrice(data.getHighPx());
        builder.setLowPrice(data.getLowPx());
        builder.setVolume(data.getTotalVolumeTrade());
        builder.setTurnover(0); //nsq 没有换手率
        builder.setTradeBalance(data.getTotalValueTrade());
        builder.setUpperLimitPrice(data.getHighLimitPx());
        builder.setLowerLimitPrice(data.getLowLimitPx());
        builder.setPreClosePrice(data.getPrevClosePx().getDoubleValue());
        builder.setSettlePrice(data.getClearingPrice());
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
        builder.setUpdateTime(data.getOrigTime());
    }
}
