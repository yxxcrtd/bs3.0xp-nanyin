package com.joyintech.tams.exchange.service.trade.scheduled.tradetime;

import com.joyintech.tams.exchange.service.trade.management.service.CommonManagement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @Author: think
 * @Description: 交易时间段缓存任务
 * @Date: Created in 16:35 2020/10/20
 */
@Component
public class TradeTimeJob {
    private static final Logger logger = LoggerFactory.getLogger(TradeTimeJob.class);

    @Autowired
    private CommonManagement commonManagement;


    @Scheduled(cron = "${scheduled.instrument.flushData:0 0 9 ? * MON-FRI}")
    public void tradeTimeCache() {
        //从投管更新交易时间段到缓存
        commonManagement.tradeTimeCache();
    }
}
