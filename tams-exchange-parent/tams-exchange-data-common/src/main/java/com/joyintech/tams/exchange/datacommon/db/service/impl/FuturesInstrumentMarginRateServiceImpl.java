package com.joyintech.tams.exchange.datacommon.db.service.impl;

import com.google.common.base.Strings;
import com.joyintech.tams.exchange.common.db.domain.Entrust;
import com.joyintech.tams.exchange.common.db.domain.FuturesInstrument;
import com.joyintech.tams.exchange.common.db.domain.FuturesInstrumentMarginRateBean;
import com.joyintech.tams.exchange.common.db.domain.FuturesInstrumentMarginRateBeanExample;
import com.joyintech.tams.exchange.common.utils.JsonUtils;
import com.joyintech.tams.exchange.datacommon.cache.impl.AsyncServiceImpl;
import com.joyintech.tams.exchange.datacommon.db.mapper.FuturesInstrumentMarginRateMapper;
import com.joyintech.tams.exchange.datacommon.db.mapper.IMybatisMapper;
import com.joyintech.tams.exchange.datacommon.db.service.FuturesInstrumentMarginRateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author liujianping
 * @ClassName FuturesInstrumentMarginRateServiceImpl
 * @Description
 * @date 2020/11/24 17:12
 * @Version
 **/
@Service
public class FuturesInstrumentMarginRateServiceImpl extends AsyncServiceImpl<String, FuturesInstrumentMarginRateBean> implements FuturesInstrumentMarginRateService {
    @Autowired
    FuturesInstrumentMarginRateMapper futuresInstrumentMarginRateMapper;
    private Map<String, Map<String, FuturesInstrumentMarginRateBean>> marginRateMap = new ConcurrentHashMap<>();

    @Override
    public List<FuturesInstrumentMarginRateBean> queryAllByAccountId(String accountId) {
        Map<String, FuturesInstrumentMarginRateBean> mp = marginRateMap.getOrDefault(accountId, new ConcurrentHashMap<>());
        return new ArrayList<>(mp.values());
    }

    @Override
    public FuturesInstrumentMarginRateBean queryByAccountIdStockCodeAndHedgeFlag(String accountId, String stockCode, String hedgeFlag) {
        Map<String, FuturesInstrumentMarginRateBean> mp = marginRateMap.getOrDefault(accountId, null);
        if (CollectionUtils.isEmpty(mp)) {
            return null;
        }
        String key = createKey(stockCode, hedgeFlag);
        mp.forEach(
                (k, item) -> {
                    logger.info("k:{},v:{}", k, JsonUtils.getJsonFromObject(item));
                }
        );
        FuturesInstrumentMarginRateBean x = mp.getOrDefault(key, null);
        return mp.getOrDefault(key, null);
    }

    @Override
    public void clearCache() {
        memory_cache.clear();
        marginRateMap.clear();
    }

    @Override
    protected void update(FuturesInstrumentMarginRateBean instrumentMarginRateBean) {
        save(instrumentMarginRateBean);
    }

    @Override
    protected void save(FuturesInstrumentMarginRateBean instrumentMarginRateBean) {
        saveMemoryCache(instrumentMarginRateBean);
    }

    private void saveMemoryCache(FuturesInstrumentMarginRateBean instrumentMarginRateBean) {
        Map<String, FuturesInstrumentMarginRateBean> mp = marginRateMap.getOrDefault(instrumentMarginRateBean.getAccountId(), new ConcurrentHashMap<>());
        String key = createKey(instrumentMarginRateBean.getStockCode(), instrumentMarginRateBean.getHedgeFlag());
        mp.put(key, instrumentMarginRateBean);
        marginRateMap.put(instrumentMarginRateBean.getAccountId(), mp);
    }

    @Override
    protected void delete(String id) {
        FuturesInstrumentMarginRateBean instrumentMarginRateBean = memory_cache.get(id);
        if (Objects.nonNull(instrumentMarginRateBean)) {
            deleteMemoryCache(instrumentMarginRateBean);
        }
    }

    @Override
    public IMybatisMapper<FuturesInstrumentMarginRateBean, String, FuturesInstrumentMarginRateBeanExample> getMapper() {
        return futuresInstrumentMarginRateMapper;
    }

    @Override
    protected String genDbId() {
        //1s处理不能超过3w条
        short tempOffSet = (short) Math.abs(offset.incrementAndGet());
        if (tempOffSet < 0) {
            tempOffSet = 0;
            offset.set(0);
        }
        Calendar ca = Calendar.getInstance();
        return String.format("%s%05d", formatThreadLocal.get().format(ca.getTime()), tempOffSet);
    }

    private void deleteMemoryCache(FuturesInstrumentMarginRateBean instrumentMarginRateBean) {
        if (!Strings.isNullOrEmpty(instrumentMarginRateBean.getAccountId())) {
            String key = createKey(instrumentMarginRateBean.getStockCode(), instrumentMarginRateBean.getHedgeFlag());
            marginRateMap.get(instrumentMarginRateBean.getAccountId()).remove(key);
        }
    }

    private String createKey(String stockCode, String hedgeFlag) {
        String key = stockCode + "|" + hedgeFlag;
        return key;
    }
}
