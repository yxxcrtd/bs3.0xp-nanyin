package com.joyintech.tams.exchange.common.utils;

import com.joyintech.tams.exchange.common.bean.TickBean;
import com.joyintech.tams.exchange.common.constant.Market;
import com.joyintech.tams.exchange.common.proto.QuoteProto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

public class TickConverter {
    private static final Logger logger = LoggerFactory.getLogger(TickConverter.class);

    public static TickBean convertTick(QuoteProto.TickBean tickBean) {
        if (Objects.isNull(tickBean)) return null;
        TickBean tick = new TickBean();
        String exchange = tickBean.getExchange();
        tick.setExchange(exchange);
        String instrumentID = tickBean.getInstrumentID();
        if (Market.isFutures(exchange)) {
            instrumentID = StockCodeUtils.getStockCode(instrumentID);
        }
        tick.setInstrumentID(instrumentID);
        tick.setLastPrice(tickBean.getLastPrice());
        tick.setOpenPrice(tickBean.getOpenPrice());
        tick.setHighPrice(tickBean.getHighPrice());
        tick.setLowPrice(tickBean.getLastPrice());
        tick.setVolume(tickBean.getVolume());
        tick.setTurnover(0); //nsq 没有换手率
        tick.setTradeBalance(tickBean.getTradeBalance());
        tick.setUpperLimitPrice(tickBean.getUpperLimitPrice());
        tick.setLowerLimitPrice(tickBean.getLowerLimitPrice());
        tick.setSettlePrice(tickBean.getSettlePrice());
        tick.setPreClosePrice(tickBean.getPreClosePrice());
        tick.setUpdateTime(tickBean.getUpdateTime());
        tick.setAskPrice(tickBean.getAskPriceList());
        tick.setAskVolume(tickBean.getAskVolumeList());
        tick.setBidPrice(tickBean.getBidPriceList());
        tick.setBidVolume(tickBean.getBidVolumeList());
        return tick;
    }

    public static void convertToQuoteProtoTickBean(TickBean tickBean, QuoteProto.TickBean.Builder builder) {
        builder.clear();
        builder.setExchange(tickBean.getExchange());
        builder.setInstrumentID(tickBean.getInstrumentID());
        builder.setLastPrice(tickBean.getLastPrice());
        builder.setOpenPrice(tickBean.getOpenPrice());
        builder.setHighPrice(tickBean.getHighPrice());
        builder.setLowPrice(tickBean.getLastPrice());
        builder.setVolume(tickBean.getVolume());
        builder.setTurnover(0); //nsq 没有换手率
        builder.setTradeBalance(tickBean.getTradeBalance());
        builder.setUpperLimitPrice(tickBean.getUpperLimitPrice());
        builder.setLowerLimitPrice(tickBean.getLowerLimitPrice());
        builder.setSettlePrice(tickBean.getSettlePrice());
        builder.setPreClosePrice(tickBean.getPreClosePrice());

        for (int i = 0; i < tickBean.getAskPrice().size(); i++) {
            builder.addAskPrice(tickBean.getAskPrice().get(i));
        }

        for (int i = 0; i < tickBean.getAskVolume().size(); i++) {
            builder.addAskVolume(tickBean.getAskVolume().get(i));
        }

        for (int i = 0; i < tickBean.getBidPrice().size(); i++) {
            builder.addBidPrice(tickBean.getBidPrice().get(i));
        }

        for (int i = 0; i < tickBean.getBidVolume().size(); i++) {
            builder.addBidVolume(tickBean.getBidVolume().get(i));
        }
        builder.setUpdateTime(tickBean.getUpdateTime());
    }


}
