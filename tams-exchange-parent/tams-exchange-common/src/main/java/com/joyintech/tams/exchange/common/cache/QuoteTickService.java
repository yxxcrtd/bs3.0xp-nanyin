package com.joyintech.tams.exchange.common.cache;


import com.joyintech.tams.exchange.common.bean.TickByte;
import com.joyintech.tams.exchange.common.proto.QuoteProto;

import java.util.Map;


public interface QuoteTickService extends CacheService<String, TickByte> {
    void saveTicks(Map<String, TickByte> tickBeans);

    QuoteProto.TickBean getTickFromCache(String stockCode);

    QuoteProto.TickBean findFromRedisById(String stockCode);

    Map<String, QuoteProto.TickBean> getAll();
}
