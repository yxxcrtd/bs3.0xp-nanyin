package com.joyintech.tams.exchange.datacommon.db.service;

import com.joyintech.tams.exchange.common.bean.instrument.MinPriceAndMinTrdUnitLimitBean;
import com.joyintech.tams.exchange.common.db.domain.FuturesInstrument;
import com.joyintech.tams.exchange.common.db.domain.Instruction;
import com.joyintech.tams.exchange.datacommon.cache.AsyncService;

import java.util.List;

public interface FuturesInstrumentService extends AsyncService<String, FuturesInstrument> {
    FuturesInstrument queryByInnerCode(String innerCode);

    FuturesInstrument queryByStockCode(String stockCode);

    List<FuturesInstrument> queryByMarket(String market);

    MinPriceAndMinTrdUnitLimitBean queryMinPriceAndMinTrdUnitLimitByFuture(Instruction instruction);

    void clearMinPriceAndMinTrdUnitLimitCache();
}
