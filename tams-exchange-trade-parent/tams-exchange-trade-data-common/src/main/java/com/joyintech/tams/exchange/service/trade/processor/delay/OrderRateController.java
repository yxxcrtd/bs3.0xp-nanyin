package com.joyintech.tams.exchange.service.trade.processor.delay;

import com.joyintech.tams.exchange.common.db.domain.AccountRiskParameter;
import com.joyintech.tams.exchange.common.db.domain.Entrust;
import com.joyintech.tams.exchange.common.utils.JsonUtils;
import com.joyintech.tams.exchange.datacommon.db.service.AccountRiskParameterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Executors;

@Component
@Scope("singleton")
public class OrderRateController {
    private static final Logger logger = LoggerFactory.getLogger(OrderRateController.class);

    @Autowired
    private AccountRiskParameterService accountRiskParameterService;

    protected final static long TIME = 1000;

    private DelayQueue<DelayMsg> delayQueue = new DelayQueue<>();

    private Map<String, Integer> orderRateMap = new ConcurrentHashMap<>();

    public boolean put(Entrust entrust, AccountRiskParameter accountRiskParameter) {
        if (orderRateMap.getOrDefault(entrust.getAccountId(), 0) >= accountRiskParameter.getFlow()) return false;
        DelayMsg<Entrust> delayedMsg = new DelayMsg<>(entrust, TIME);
        delayQueue.put(delayedMsg);
        orderRateMap.put(entrust.getAccountId(), orderRateMap.getOrDefault(entrust.getAccountId(), 0) + 1);
        return true;
    }

    public void clear() {
        logger.info("orderRate delayQueue clear: {}", JsonUtils.getJsonFromObject(delayQueue.toArray()));
        delayQueue.clear();
    }


    public OrderRateController() {
        Executors.newCachedThreadPool().submit(() -> {
            while (true) {
                try {
                    //队列为空时take方法会阻塞
                    DelayMsg delayMsg = delayQueue.take();
                    Object o = delayMsg.getItem();
                    if (o instanceof Entrust) {
                        Entrust entrust = (Entrust) o;
                        orderRateMap.put(entrust.getAccountId(), orderRateMap.getOrDefault(entrust.getAccountId(), 0) - 1);
                    } else {
                        logger.warn("DelayMessage type not found", JsonUtils.getJsonFromObject(o));
                    }
                } catch (Exception e) {
                    logger.info(e.getMessage());
                }
            }
        });
    }
}
