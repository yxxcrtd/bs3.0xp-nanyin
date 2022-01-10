package com.joyintech.tams.exchange.quoteterminal.nsq;

import com.gtn.nsq.javasdk.HSDataType;
import com.gtn.nsq.javasdk.hsstruct.HSOptDepthMarketDataField;
import com.gtn.nsq.javasdk.hsstruct.HSSecuDepthMarketDataField;
import com.joyintech.tams.exchange.common.constant.Market;
import com.joyintech.tams.exchange.common.proto.QuoteProto;
import com.joyintech.tams.exchange.common.utils.StockCodeUtils;

public class HSNSQConverter {
    public static void convertToTick(HSOptDepthMarketDataField marketDataField, QuoteProto.TickBean.Builder builder){
        builder.clear();

    }

    public static void convertToTick(HSSecuDepthMarketDataField marketDataField, QuoteProto.TickBean.Builder builder){
        builder.clear();
        if (marketDataField.getExchangeID().equals(HSDataType.HS_EI_SSE)) {
            builder.setExchange(Market.SHSE.value);
        } else if (marketDataField.getExchangeID().equals(HSDataType.HS_EI_SZSE)) {
            builder.setExchange(Market.SZSE.value);
        }
        builder.setInstrumentID(StockCodeUtils.formatStockCode(marketDataField.getInstrumentID(), builder.getExchange()));
        builder.setLastPrice(marketDataField.getLastPrice());
        builder.setOpenPrice(marketDataField.getOpenPrice());
        builder.setHighPrice(marketDataField.getHighPrice());
        builder.setLowPrice(marketDataField.getLastPrice());
        builder.setVolume(marketDataField.getTradeVolume());
        builder.setTurnover(0); //nsq 没有换手率
        builder.setTradeBalance(marketDataField.getTradeBalance());
        builder.setUpperLimitPrice(marketDataField.getUpperLimitPrice());
        builder.setLowerLimitPrice(marketDataField.getLowerLimitPrice());
        builder.setSettlePrice(marketDataField.getClosePrice());
        builder.setPreClosePrice(marketDataField.getPreClosePrice());

        for (int i =0; i < marketDataField.getAskPrice().length;i++) {
            builder.addAskPrice(marketDataField.getAskPrice()[i]);
        }

        for (int i =0; i < marketDataField.getAskVolume().length;i++) {
            builder.addAskVolume((int)marketDataField.getAskVolume()[i]);
        }

        for (int i =0; i < marketDataField.getBidPrice().length;i++) {
            builder.addBidPrice(marketDataField.getBidPrice()[i]);
        }

        for (int i =0; i < marketDataField.getBidVolume().length;i++) {
            builder.addBidVolume((int)marketDataField.getBidVolume()[i]);
        }
        builder.setUpdateTime(marketDataField.getUpdateTime());
    }
}
