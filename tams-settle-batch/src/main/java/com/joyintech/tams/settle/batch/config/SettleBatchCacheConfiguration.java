package com.joyintech.tams.settle.batch.config;

import com.joyintech.batch.batchbase.service.BatchCacheService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

/**
 * @author 何阳阳
 * @date 2020/7/7
 * @see SettleBatchCacheConfiguration
 * @since 1.0
 */
@Configuration
@ConditionalOnMissingBean(BatchCacheService.class)
public class SettleBatchCacheConfiguration {

    @Service
    public class BatchCache extends BatchCacheService {

    }
}
