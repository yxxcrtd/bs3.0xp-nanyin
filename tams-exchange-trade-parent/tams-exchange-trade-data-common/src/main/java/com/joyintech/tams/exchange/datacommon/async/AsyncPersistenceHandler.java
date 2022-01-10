package com.joyintech.tams.exchange.datacommon.async;

import com.joyintech.tams.exchange.common.utils.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by yking on 2017/12/13 0013. God bless you!
 */
@Component("asyncPersistenceHandler")
public class AsyncPersistenceHandler {
    private static final Logger logger = LoggerFactory.getLogger(AsyncPersistenceHandler.class);

    @Transactional
    public void doTransaction(List<AsyncPersistenceBean> asyncPersistenceBeans) {
        asyncPersistenceBeans.forEach(asyncPersistenceBean -> asyncData(asyncPersistenceBean));
    }

    private void asyncData(AsyncPersistenceBean asyncPersistenceBean) {
        try {
            asyncPersistenceBean.getAsync().async();
            AsyncPersistenceWorker.consumerOffset.incrementAndGet();
        } catch (Exception e) {
            logger.error("async data: {} error: {}", JsonUtils.getJsonFromObject(asyncPersistenceBean), e.getMessage());
            AsyncPersistenceWorker.exceptionOffset.incrementAndGet();
        }
    }
}
