package com.joyintech.tams.batch;

import com.joyintech.system.config.StartupConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * 批处理启动后操作
 * @author 王波
 * @version 1.0 2018年8月16日
 * @see TamsBatchStartupListener
 * @since 1.0
 */
@Component
public class TamsBatchStartupListener extends StartupConfiguration {

    /**
     * 日志
     */
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void afterStartup(ApplicationContext applicationContext) {
    }
}
