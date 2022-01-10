package com.joyintech.tams.exchange.service.trade.scheduled.futures;

import com.joyintech.tams.exchange.service.trade.management.service.FuturesManagement;
import com.joyintech.tams.exchange.service.trade.management.service.InstrumentManagement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author liujianping
 * @ClassName FuturesJob
 * @Description
 * @date 2020/11/26 15:01
 * @Version
 **/
@Component
@EnableScheduling
public class FuturesJob {
    private static final Logger logger = LoggerFactory.getLogger(FuturesJob.class);

    @Autowired
    private InstrumentManagement instrumentManagement;
    @Autowired
    FuturesManagement futuresManagement;

    @Scheduled(cron = "${scheduled.futures.flushData:0 30 8,20 ? * MON-FRI}")
    public void flushFuturesInstrument() {
        logger.info("flushFuturesInstrumentData begin...");
        instrumentManagement.flushFuturesInstrumentData();
        futuresManagement.flushFuturesInstrumentMarginRate();
    }
}
