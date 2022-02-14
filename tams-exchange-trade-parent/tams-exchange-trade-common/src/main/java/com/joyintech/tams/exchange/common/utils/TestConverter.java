package com.joyintech.tams.exchange.common.utils;

import com.joyintech.tams.exchange.common.bean.QuoteDataProto;
import com.joyintech.tams.exchange.common.constant.Market;
import com.joyintech.tams.exchange.common.proto.QuoteProto;

public class TestConverter {

    public static QuoteProto.TickBean convertTick(QuoteDataProto.TickBean queryTick, QuoteProto.TickBean.Builder builder) {
        return convertTick(queryTick, builder, false);
    }

    private static String marketConvert(String testMarket) {
        if (testMarket.equals("SHSE")) {
            return Market.SHSE.value;
        } else if (testMarket.equals("SZSE")) {
            return Market.SZSE.value;
        } else {
            return testMarket;
        }
    }

    public static QuoteProto.TickBean convertTick(QuoteDataProto.TickBean queryTick, QuoteProto.TickBean.Builder builder, boolean bond) {
        builder.setExchange(marketConvert(queryTick.getExchange()));
        //xtrade测试行情的代码格式CFFEX.1912，与场内交易相反
        builder.setInstrumentID(StockCodeUtils.formatStockCode(StockCodeUtils.getMarket(queryTick.getInstrumentID()), builder.getExchange()));
        builder.setLastPrice(queryTick.getLastPrice());
        builder.setOpenPrice(queryTick.getOpenPrice());
        builder.setHighPrice(queryTick.getHighPrice());
        builder.setLowPrice(queryTick.getLastPrice());
        builder.setVolume(queryTick.getVolume());
        builder.setTurnover(0); //nsq 没有换手率
        builder.setTradeBalance(0);
        builder.setUpperLimitPrice(queryTick.getUpperLimitPrice());
        builder.setLowerLimitPrice(queryTick.getLowerLimitPrice());
        builder.setSettlePrice(queryTick.getSettlePrice());
        builder.setPreClosePrice(queryTick.getPreClosePrice());

        for (int i = 0; i < queryTick.getAskPriceCount(); i++) {
            builder.addAskPrice(queryTick.getAskPrice(i));
        }

        for (int i = 0; i < queryTick.getAskVolumeCount(); i++) {
            if (bond && queryTick.getExchange().equals("SHSE")) {
                builder.addAskVolume(queryTick.getAskVolume(i) * 10);
            } else {
                builder.addAskVolume(queryTick.getAskVolume(i));
            }
        }

        for (int i = 0; i < queryTick.getBidPriceCount(); i++) {
            builder.addBidPrice(queryTick.getBidPrice(i));
        }

        for (int i = 0; i < queryTick.getBidVolumeCount(); i++) {
            if (bond && queryTick.getExchange().equals("SHSE")) {
                builder.addBidVolume(queryTick.getBidVolume(i) * 10);
            } else {
                builder.addBidVolume(queryTick.getBidVolume(i));
            }
        }
        builder.setUpdateTime(queryTick.getUpdateTime());

        return builder.build();
    }


}
