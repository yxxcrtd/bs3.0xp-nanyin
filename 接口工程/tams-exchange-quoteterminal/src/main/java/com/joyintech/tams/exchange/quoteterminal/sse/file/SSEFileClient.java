package com.joyintech.tams.exchange.quoteterminal.sse.file;

import com.joyintech.tams.exchange.quoteterminal.service.QuoteClient;
import com.joyintech.tams.exchange.quoteterminal.service.QuoteManager;
import com.joyintech.tams.exchange.quoteterminal.sse.file.config.SSEFileConfig;
import com.joyintech.tams.exchange.quoteterminal.sse.file.helper.FileHandleHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author: think
 * @Description:
 * @Date: Created in 14:25 2021/8/9
 */
@Component
@ConditionalOnProperty(value = "sse.file.active", havingValue = "true")
public class SSEFileClient extends QuoteClient {
    private static final Logger logger = LoggerFactory.getLogger(SSEFileClient.class);

    @Autowired
    private QuoteManager quoteManager;

    @Autowired
    private SSEFileConfig sseFileConfig;

    @Autowired
    private FileHandleHelper fileHandleHelper;

    private ExecutorService executorService;

    @Override
    public void addClient() {
        logger.info("addClient SSEFile");
        quoteManager.addClient("SSEFile", this);
    }

    @Override
    public String getQuoteType() {
        return "stock";
    }

    @Override
    public void init() {
        try {
            fileHandleHelper.cacheLimitPriceByCpxxFile();
            fileHandleHelper.cacheQuoteByFile();
            executorService = Executors.newSingleThreadExecutor();
            executorService.submit(() -> fileHandleHelper.watchFile(sseFileConfig.getQuoteDir()));
        } catch (Exception e) {
            logger.error("SSEFileClient init exception {}", e);
        }
    }

    @Override
    public void release() {
        executorService.shutdown();
    }
}
