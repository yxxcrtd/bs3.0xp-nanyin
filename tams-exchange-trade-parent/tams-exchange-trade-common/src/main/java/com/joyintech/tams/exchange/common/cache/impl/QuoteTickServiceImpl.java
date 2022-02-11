package com.joyintech.tams.exchange.common.cache.impl;


import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import com.joyintech.tams.exchange.common.bean.TickByte;
import com.joyintech.tams.exchange.common.cache.QuoteTickService;
import com.joyintech.tams.exchange.common.cache.config.QuoteServiceNodeConfig;
import com.joyintech.tams.exchange.common.proto.QuoteProto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


@Service
public class QuoteTickServiceImpl extends RedisCacheServiceImpl<String, TickByte> implements QuoteTickService {

    private Map<String, QuoteProto.TickBean> tickMap = Maps.newConcurrentMap();

    @Autowired
    private QuoteServiceNodeConfig quoteServiceNodeConfig;

    @Override
    protected void setKeyField(TickByte tickByte) {
        if (Objects.nonNull(className)) return;
        className = getClassNameByConf(null);
    }

    private String getClassNameByConf(String serviceNodeName) {
        return TickByte.class.getName() + "_" + (Strings.isNullOrEmpty(serviceNodeName) ? quoteServiceNodeConfig.getServiceNodeName() : serviceNodeName);
    }

    @Override
    public void coverCache(boolean fromRedis) {
        className = getClassNameByConf(null);
        clearCache();
        Map<String, TickByte> byteMap = new HashMap<>();
        getMapFromRedis(className, byteMap);
        byteMap.values().forEach(this::save);
    }

    @Override
    public void clearCache() {
        tickMap.clear();
    }

    @Override
    protected void update(TickByte tickBean) {
        save(tickBean);
    }

    @Override
    protected void save(TickByte tickBean) {
        try {
            QuoteProto.TickBean bean = QuoteProto.TickBean.parseFrom(tickBean.getBytes());
            tickMap.put(bean.getInstrumentID(), bean);
        } catch (Exception e) {
            logger.info("QuoteProto.TickBean parse error:{}", e);
        }
    }

    @Override
    protected void delete(String PK) {

    }

    @Override
    public void saveTicks(Map<String, TickByte> tickBeans) {
        try {
            Map<String, Map<String, TickByte>> redisMap = new HashMap<>();
            redisMap.put(className, tickBeans);
            pipeWriteService.writeList(redisMap);
        } catch (Exception e) {
            logger.error("save ticks error: {}", e);
        }
    }

    public QuoteProto.TickBean findFromRedisById(String stockCode) {
        try {
            TickByte bytes = findFromRedis(stockCode);
            if (Objects.nonNull(bytes)) {
                return QuoteProto.TickBean.parseFrom(bytes.getBytes());
            }
        } catch (Exception e) {
            logger.info("find TickBean fromRedis error:{}", e);
        }
        return null;
    }

    @Override
    public Map<String, QuoteProto.TickBean> getAll() {
        Map<String, TickByte> byteMap = new HashMap<>();
        getMapFromRedis(className, byteMap);
        Map<String, QuoteProto.TickBean> tickBeanMap = new HashMap<>();
        byteMap.values().forEach(bytes -> {
            try {
                if (Objects.nonNull(bytes) && Objects.nonNull(bytes.getBytes())) {
                    QuoteProto.TickBean bean = QuoteProto.TickBean.parseFrom(bytes.getBytes());
                    tickBeanMap.put(bean.getInstrumentID(), bean);
                }
            } catch (Exception e) {
                logger.info("find TickBean fromRedis error:{}", e);
            }
        });
        return tickBeanMap;
    }

    @Override
    public void setClassName(String serviceNodeName) {
        className = getClassNameByConf(serviceNodeName);
    }

    @Override
    public QuoteProto.TickBean getTickFromCache(String stockCode) {
        return tickMap.get(stockCode);
    }


}
