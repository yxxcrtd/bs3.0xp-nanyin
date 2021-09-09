package com.joyintech.tams.exchange.quoteterminal.job;

import com.joyintech.tams.exchange.common.bean.cache.QuoteServiceNode;
import com.joyintech.tams.exchange.common.cache.QuoteServiceNodeService;
import com.joyintech.tams.exchange.common.cache.config.QuoteServiceNodeConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @Author: think
 * @Description:
 * @Date: Created in 15:50 2021/8/16
 */
@Component
public class TokenJob {
    private static final Logger logger = LoggerFactory.getLogger(TokenJob.class);

    @Autowired
    private QuoteServiceNodeConfig quoteServiceNodeConfig;

    @Autowired
    private QuoteServiceNodeService quoteServiceNodeService;

    @Value("${quote.node.interval:50000}")
    private int interval;

    @Scheduled(fixedRateString = "${quote.node.interval}")
    public void tokenCache() {
        if (!quoteServiceNodeConfig.isEnable()) return;
        String serviceNodeName = quoteServiceNodeConfig.getServiceNodeName();
        QuoteServiceNode quoteServiceNode = quoteServiceNodeService.queryQuoteSerNodeFromRedisByName(serviceNodeName);
        if (!Objects.nonNull(quoteServiceNode)) {
            quoteServiceNode = new QuoteServiceNode();
            quoteServiceNode.setNodeName(serviceNodeName);
            quoteServiceNode.setNodeWeight(quoteServiceNodeConfig.getServiceWeight());
            quoteServiceNode.setInterval(interval);
        }
        long currentTime = System.currentTimeMillis();
        quoteServiceNode.setLastLiveTime(currentTime);
        logger.info("update [{}]  quote terminal live time [{}]", serviceNodeName, currentTime);
        quoteServiceNodeService.saveOrUpdateQuoteServiceNode(quoteServiceNode);
    }
}
