package com.joyintech.tams.exchange.quoteterminal.sse.binary;

import com.joyintech.tams.exchange.quoteterminal.service.QuoteClient;
import com.joyintech.tams.exchange.quoteterminal.service.QuoteManager;
import com.joyintech.tams.exchange.quoteterminal.sse.binary.config.SSEExchangeConfigure;
import com.joyintech.tams.exchange.quoteterminal.sse.binary.gateway.SSEBinaryMarketBootstrap;
import com.joyintech.tams.exchange.quoteterminal.sse.binary.gateway.SSEBinaryMessageFacade;
import com.joyintech.tams.exchange.quoteterminal.sse.binary.resolver.UpperLowerLimitPrice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(value = "sse.binary.active", havingValue = "true")
public class SSEBinaryClient extends QuoteClient {
    private static final Logger logger = LoggerFactory.getLogger(SSEBinaryClient.class);
    @Value("${sse.binary.filePath:E:/test/}")
    private String path;
    @Autowired
    private QuoteManager quoteManager;

    private SSEBinaryMarketBootstrap bootstrap;

    @Autowired
    private SSEExchangeConfigure configure;

    @Autowired
    private SSEBinaryMessageFacade facade;

    @Override
    public void addClient() {
        logger.info("addClient: SSEBinary");
        quoteManager.addClient("SSEBinary", this);
    }

    @Override
    public String getQuoteType() {
        return "stock";
    }

    @Override
    public void init() {
        UpperLowerLimitPrice.upperLowerLimitPrice(path);
        bootstrap = SSEBinaryMarketBootstrap.getInstance(configure, facade);
        boolean connect = bootstrap.connect();
        if (connect) {
            bootstrap.login();
        }
    }

    @Override
    public void release() {
        bootstrap.logout();
    }
}
