package com.joyintech.tams.exchange.quoteterminal.service;

import com.google.common.collect.Maps;
import com.joyintech.tams.exchange.common.bean.TickByte;
import com.joyintech.tams.exchange.common.cache.QuoteTickService;
import com.joyintech.tams.exchange.common.proto.QuoteProto;
import com.joyintech.tams.exchange.quoteterminal.sse.file.SSEFileClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class QuoteService {
    private static final Logger logger = LoggerFactory.getLogger(QuoteService.class);

    private Map<String, TickByte> tickBytesMap = Maps.newConcurrentMap();

    private Map<String, TickByte> SZSETickBytesMap = Maps.newConcurrentMap();

    private Map<String, TickByte> SSETickBytesMap = Maps.newConcurrentMap();

    private Map<String, QuoteProto.TickBean> tickBeanMap = Maps.newConcurrentMap();

    private Map<String, TickByte> updateTickBeanMap = Maps.newConcurrentMap();

    @Autowired
    private QuoteTickService quoteTickService;

    @Value("${sse.file.active:false}")
    private boolean isSSEFileClientActive;

    @Value("${szse.binary.active:false}")
    private boolean isSZSEBinaryClientActive;

    @Autowired
    private ApplicationContext applicationContext;

    private boolean isUpdate = false;

    public void addTick(QuoteProto.TickBean tickBean) {
        tickBytesMap.put(tickBean.getInstrumentID(), new TickByte(tickBean.toByteArray()));
        tickBeanMap.put(tickBean.getInstrumentID(), tickBean);
    }

    public void addSZSETick(QuoteProto.TickBean tickBean) {
        SZSETickBytesMap.put(tickBean.getInstrumentID(), new TickByte(tickBean.toByteArray()));
        tickBeanMap.put(tickBean.getInstrumentID(), tickBean);
    }


    public void addSSETick(QuoteProto.TickBean tickBean) {
        SSETickBytesMap.put(tickBean.getInstrumentID(), new TickByte(tickBean.toByteArray()));
        tickBeanMap.put(tickBean.getInstrumentID(), tickBean);
    }


    // 上交所为文件行情 --- 更新时做变更的更新，不更新全部。 第一次除外
    public void updateTick(QuoteProto.TickBean tickBean) {
        updateTickBeanMap.put(tickBean.getInstrumentID(), new TickByte(tickBean.toByteArray()));
        tickBeanMap.put(tickBean.getInstrumentID(), tickBean);
        quoteTickService.saveTicks(updateTickBeanMap);
        updateTickBeanMap.remove(tickBean.getInstrumentID());
    }

    public double getUpdateTime(String instrumentID) {
        QuoteProto.TickBean tickBean = tickBeanMap.get(instrumentID);
        return tickBeanMap.get(instrumentID) == null ? 0 : tickBean.getUpdateTime();
    }

    @Scheduled(fixedRateString = "${scheduled.quote.stock.flush}")
    public void flushTickToCache() {
        try {
            // 深交所直接更新全部行情信息
            if (isSZSEBinaryClientActive) {
                if (SZSETickBytesMap.size() > 0) quoteTickService.saveTicks(SZSETickBytesMap);
            }
            // 上交所为文件行情 --- 更新时做变更的更新，不更新全部。 第一次除外
            if (isSSEFileClientActive) {
                SSEFileClient client = applicationContext.getBean(SSEFileClient.class);
                if (client.isInit() && !isUpdate) {
                    logger.info("更新全量上海行情信息...");
                    if (SSETickBytesMap.size() > 0) quoteTickService.saveTicks(SSETickBytesMap);
                    isUpdate = true;
                    logger.info("更新全量上海行情信息完成");
                }
            } else {
                if (tickBytesMap.size() > 0) quoteTickService.saveTicks(tickBytesMap);
            }
        } catch (Exception e) {
            logger.error("flush ticks error:{}", e);
        }
    }
}
