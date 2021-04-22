package com.joyintech.tams.exchange.terminal.trade.ufx;

import com.hundsun.t2sdk.interfaces.share.dataset.IDataset;
import com.joyintech.tams.exchange.common.db.domain.Entrust;
import com.joyintech.tams.exchange.common.db.domain.Trade;
import com.joyintech.tams.exchange.terminal.trade.TradeResponseImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * create By MAXX on 2020/4/7
 */
@Component
@ConditionalOnProperty(value = "ufx.active", matchIfMissing = false, havingValue = "true")
public class UfxMCHandler {
    protected final static Logger logger = LoggerFactory.getLogger(UfxMCHandler.class);

    //TODO:: 赋值
    private static UfxTradeApi ufxTradeApi;

    private static UfxMCHandler ufxMCHandler;
    @Autowired
    private TradeResponseImpl ufxTradeResponse;

    public static UfxMCHandler getUfxMCHandler() {
        return ufxMCHandler;
    }

    public static void setUfxMCHandler(UfxMCHandler ufxMCHandler) {
        UfxMCHandler.ufxMCHandler = ufxMCHandler;
    }

    @PostConstruct
    public void init() {
        ufxMCHandler = this;
        ufxMCHandler.ufxTradeResponse = this.ufxTradeResponse;
    }

    public static void mcReceived(String topicName, IDataset ds) {
        if ("secu.deal_return".equals(topicName)) {//证券成交回报
            Trade trade = UfxConverter.dealReturn(ds, ufxTradeApi);
            ufxMCHandler.ufxTradeResponse.dealReturn(trade);
            return;
        }
        if ("secu.entrustwriteback".equals(topicName)) {//证券委托回报
            Entrust entrust = UfxConverter.entrustWriteBack(ds, ufxTradeApi);
            ufxMCHandler.ufxTradeResponse.entrustWriteBack(entrust);
            return;
        }
    }
}
