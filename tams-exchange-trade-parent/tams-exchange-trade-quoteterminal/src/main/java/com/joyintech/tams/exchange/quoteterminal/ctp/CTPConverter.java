package com.joyintech.tams.exchange.quoteterminal.ctp;

import com.google.common.base.Strings;
import com.joyintech.tams.exchange.common.proto.QuoteProto;
import com.joyintech.tams.exchange.common.utils.StockCodeUtils;
import net.jctp.CThostFtdcDepthMarketDataField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * create By MAXX on 2021/1/4
 */
public class CTPConverter {
    private static final Logger logger = LoggerFactory.getLogger(CTPConverter.class);

    public static void convertToTick(CThostFtdcDepthMarketDataField marketDataField, QuoteProto.TickBean.Builder builder) {
        builder.clear();
        if (Strings.isNullOrEmpty(marketDataField.ExchangeID)) {
            marketDataField.ExchangeID = CTPTradeApi.getInstrumentMap(marketDataField.InstrumentID);
        }
        builder.setExchange(marketDataField.ExchangeID);
        builder.setInstrumentID(StockCodeUtils.formatStockCode(marketDataField.InstrumentID, builder.getExchange()));
        builder.setLastPrice(marketDataField.LastPrice);
        builder.setOpenPrice(marketDataField.OpenPrice);
        builder.setHighPrice(marketDataField.HighestPrice);
        builder.setLowPrice(marketDataField.LowestPrice);
        builder.setVolume(marketDataField.Volume);
        builder.setTurnover(new Double(marketDataField.Turnover).longValue()); //换手率
        builder.setUpperLimitPrice(marketDataField.UpperLimitPrice);
        builder.setLowerLimitPrice(marketDataField.LowerLimitPrice);
        builder.setSettlePrice(marketDataField.ClosePrice);
        builder.setPreClosePrice(marketDataField.PreClosePrice);
        builder.addAskPrice(marketDataField.AskPrice1);
        builder.addAskPrice(marketDataField.AskPrice2);
        builder.addAskPrice(marketDataField.AskPrice3);
        builder.addAskPrice(marketDataField.AskPrice4);
        builder.addAskPrice(marketDataField.AskPrice5);
        builder.addAskVolume(marketDataField.AskVolume1);
        builder.addAskVolume(marketDataField.AskVolume2);
        builder.addAskVolume(marketDataField.AskVolume3);
        builder.addAskVolume(marketDataField.AskVolume4);
        builder.addAskVolume(marketDataField.AskVolume5);
        builder.addBidPrice(marketDataField.BidPrice1);
        builder.addBidPrice(marketDataField.BidPrice2);
        builder.addBidPrice(marketDataField.BidPrice3);
        builder.addBidPrice(marketDataField.BidPrice4);
        builder.addBidPrice(marketDataField.BidPrice5);
        builder.addBidVolume(marketDataField.BidVolume1);
        builder.addBidVolume(marketDataField.BidVolume2);
        builder.addBidVolume(marketDataField.BidVolume3);
        builder.addBidVolume(marketDataField.BidVolume4);
        builder.addBidVolume(marketDataField.BidVolume5);
        builder.setTradeBalance(0);
        builder.setUpdateTime(Double.parseDouble(marketDataField.UpdateTime.replaceAll(":", "")));
    }
}
