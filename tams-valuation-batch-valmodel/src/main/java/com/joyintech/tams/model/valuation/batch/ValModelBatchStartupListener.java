package com.joyintech.tams.model.valuation.batch;

import com.joyintech.framework.convert.DateConverter;
import com.joyintech.framework.listener.AppStartupListener;
import org.apache.commons.beanutils.ConvertUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 批处理启动后操作
 * @author zznn
 */
@Component
public class ValModelBatchStartupListener  implements AppStartupListener {

    /**
     * 日志
     */
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void afterStartup(ApplicationContext applicationContext) {
        startBatchUnistartListener();
    }
    @Override
    public void onApplicationStartup(ContextRefreshedEvent event) {
        registerConverter();
        afterStartup(event.getApplicationContext());
    }

    private void registerConverter() {
        ConvertUtils.register(new DateConverter(), Date.class);
    }

    private void startBatchUnistartListener() {
        Thread t = new Thread();
        t.setDaemon(true);
        t.start();
        logger.info("批处理统一调度监听启动。");
    }
}
