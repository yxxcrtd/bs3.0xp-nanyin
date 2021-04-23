package com.joyintech.tams.exchange.service.trade.util;

import com.google.common.base.Strings;
import com.joyintech.tams.exchange.datacommon.db.service.EntrustService;
import com.joyintech.tams.exchange.datacommon.db.service.IntegratedEntrustService;
import com.joyintech.tams.exchange.datacommon.db.service.TradeService;
import com.joyintech.tams.exchange.service.trade.management.service.CommonManagement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class RelayUtils {
    private static final Logger logger = LoggerFactory.getLogger(RelayUtils.class);

    @Autowired
    private CommonManagement commonManagement;
    @Autowired
    private EntrustService entrustService;
    @Autowired
    private TradeService tradeService;
    @Autowired
    private IntegratedEntrustService integratedEntrustService;

    private String startDate;

    @Scheduled(fixedRateString = "${scheduled.token.fixedRate}")
    public void fillPlatDate() {
        try {
            startDate = commonManagement.queryPlatDate();
            logger.info("查询到系统平台日为 :{}", startDate);
            if (!Strings.isNullOrEmpty(startDate)) {
                String platDate = startDate.replace("-", "");
                entrustService.fillPlatDate(platDate);
                tradeService.fillPlatDate(platDate);
                integratedEntrustService.fillPlatDate(platDate);
            }
        } catch (Exception e) {
            logger.info("查询系统平台日失败：{}", e);
        }
    }
}
