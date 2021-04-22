package com.joyintech.tams.exchange.quoteterminal.service;

import com.google.common.collect.Maps;
import com.joyintech.tams.exchange.common.bean.TickByte;
import com.joyintech.tams.exchange.common.cache.QuoteTickService;
import com.joyintech.tams.exchange.common.proto.QuoteProto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class QuoteService {
    private static final Logger logger = LoggerFactory.getLogger(QuoteService.class);

    private Map<String, TickByte> tickBytesMap = Maps.newConcurrentMap();
    private Map<String, QuoteProto.TickBean> tickBeanMap = Maps.newConcurrentMap();
    @Autowired
    private QuoteTickService quoteTickService;

    public void addTick(QuoteProto.TickBean tickBean) {
        tickBytesMap.put(tickBean.getInstrumentID(), new TickByte(tickBean.toByteArray()));
        tickBeanMap.put(tickBean.getInstrumentID(), tickBean);
    }


    @Scheduled(fixedRateString = "${scheduled.quote.stock.flush}")
    public void flushTickToCache() {
        try {
            if (tickBytesMap.size() > 0) quoteTickService.saveTicks(tickBytesMap);
        } catch (Exception e) {
            logger.error("flush ticks error:{}", e);
        }

    }
}
