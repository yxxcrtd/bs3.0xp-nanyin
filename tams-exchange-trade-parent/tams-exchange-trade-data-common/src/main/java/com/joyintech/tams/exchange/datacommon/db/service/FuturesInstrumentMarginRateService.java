package com.joyintech.tams.exchange.datacommon.db.service;

import com.joyintech.tams.exchange.common.db.domain.FuturesInstrument;
import com.joyintech.tams.exchange.common.db.domain.FuturesInstrumentMarginRateBean;
import com.joyintech.tams.exchange.datacommon.cache.AsyncService;

import java.util.List;


/**
 * @author liujianping
 * @ClassName FuturesInstrumentMarginRateService
 * @Description
 * @date 2020/11/24 17:10
 * @Version
 **/

public interface FuturesInstrumentMarginRateService extends AsyncService<String, FuturesInstrumentMarginRateBean> {
     List<FuturesInstrumentMarginRateBean> queryAllByAccountId(String accountId);

     FuturesInstrumentMarginRateBean queryByAccountIdStockCodeAndHedgeFlag(String accountId, String stockCode, String hedgeFlag);
}
