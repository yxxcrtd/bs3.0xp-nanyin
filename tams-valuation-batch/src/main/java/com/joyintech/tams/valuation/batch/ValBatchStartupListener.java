package com.joyintech.tams.valuation.batch;

import com.joyintech.tams.valuation.batch.batchunistart.dao.BatchUnistartMapper;
import com.joyintech.tams.valuation.batch.batchunistart.service.BatchUnistartExecutor;
import com.joyintech.tams.valuation.batch.unitfycaccountant.service.UnitfycaccountantExecutor;
import com.joyintech.system.config.StartupConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * 批处理启动后操作
 * @author 王波
 * @version 1.0 2018年8月16日
 * @see ValBatchStartupListener
 * @since 1.0
 */
@Component
public class ValBatchStartupListener extends StartupConfiguration {
//    @Autowired
//    private UnitfycaccountantExecutor unitfycaccountantExecutor;
    @Autowired
    private BatchUnistartExecutor batchUnistartExecutor;

    @Autowired
    private BatchUnistartMapper batchUnistartMapper;

    /**
     * 日志
     */
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void afterStartup(ApplicationContext applicationContext) {
        // 单估值时请使用此监听
//        Thread t = new Thread(unitfycaccountantExecutor);
//        t.setDaemon(true);
//        t.start();
//        logger.info("估值核算系统批处理启动完成!");
        // 一体化请使用此监听
        // startBatchUnistartListener();
    }

    private void startBatchUnistartListener() {
        batchUnistartMapper.updateSysUnistartBreak();
        batchUnistartMapper.updateSysUnistartDetailBreak();
        Thread t = new Thread(batchUnistartExecutor);
        t.setDaemon(true);
        t.start();
        logger.info("批处理统一调度监听启动。");
    }
}
