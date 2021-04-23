package com.joyintech.tams.exchange.quoteterminal.service;

import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class QuoteManager implements ApplicationListener<ContextRefreshedEvent> {
    private static final Logger logger = LoggerFactory.getLogger(QuoteManager.class);

    private Map<String, IQuoteClient> quoteClientMap = Maps.newConcurrentMap();

    public void addClient(String key, IQuoteClient quoteClient) {
        quoteClientMap.put(key, quoteClient);
    }

    public void removeClient(String key) {
        quoteClientMap.remove(key);
    }

    @Value("${scheduled.quote.stock.time}")
    private String stockTime;

    @Scheduled(cron = "${scheduled.quote.stock.start}")
    public void startStockQuote() {
        quoteClientMap.forEach((key, quoteClient) -> {
            if (quoteClient.getQuoteType().equals("stock") && !quoteClient.isInit()) {
                logger.info("quoteClient [{}] init...", key);
                quoteClient.init();
                quoteClient.setInitStatus(true);
                logger.info("quoteClient [{}] init success", key);
            }
        });
    }

    @Scheduled(cron = "${scheduled.quote.stock.end}")
    public void endStockQuote() {
        quoteClientMap.forEach((key, quoteClient) -> {
            if (quoteClient.getQuoteType().equals("stock") && quoteClient.isInit()) {
                logger.info("quoteClient [{}] release...", key);
                quoteClient.release();
                quoteClient.setInitStatus(false);
                logger.info("quoteClient [{}] release success", key);
            }
        });
        System.exit(0);
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
//        String[] stockQuoteTime = stockTime.split("-");
//        int currentTime = Integer.parseInt(new SimpleDateFormat("HHmm").format(new Date()));
//        logger.info("start time:[{}]", currentTime);
//        logger.info("quoteClientMap:[{}]", JsonUtils.getJsonFromObject(quoteClientMap));
//        if (currentTime >= Integer.parseInt(stockQuoteTime[0]) && currentTime < Integer.parseInt(stockQuoteTime[1])){
//            startStockQuote();
//        }
    }
}
