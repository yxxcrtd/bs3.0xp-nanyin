package com.joyintech.tams.exchange.quoteterminal.szse.binary;

import com.joyintech.tams.exchange.quoteterminal.service.QuoteClient;
import com.joyintech.tams.exchange.quoteterminal.service.QuoteManager;
import com.joyintech.tams.exchange.quoteterminal.szse.binary.config.SZExchangeConfigure;
import com.joyintech.tams.exchange.quoteterminal.szse.binary.gateway.SZSEBinaryMarketBootstrap;
import com.joyintech.tams.exchange.quoteterminal.szse.binary.gateway.SZSEBinaryMessageFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(value = "szse.binary.active", havingValue = "true")
public class SZSEBinaryClient extends QuoteClient {
    private static final Logger logger = LoggerFactory.getLogger(SZSEBinaryClient.class);

    @Autowired
    private QuoteManager quoteManager;

    private SZSEBinaryMarketBootstrap bootstrap;

    @Autowired
    private SZExchangeConfigure configure;

    @Autowired
    private SZSEBinaryMessageFacade facade;


    @Override
    public void addClient() {
        logger.info("addClient: SZSEBinary");
        quoteManager.addClient("SZSEBinary", this);
    }

    @Override
    public String getQuoteType() {
        return "stock";
    }

    @Override
    public void init() {
        bootstrap = SZSEBinaryMarketBootstrap.getInstance(configure, facade);
        bootstrap.init();
        bootstrap.connect();
    }

    @Override
    public void release() {
        try {
            bootstrap.logout();
        } catch (Exception e) {
            logger.error("SZSEBinaryClient release exception {}", e);
        }
    }
}
