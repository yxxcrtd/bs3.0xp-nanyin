package com.joyintech.tams.exchange.datacommon.db.service;

import com.joyintech.tams.exchange.datacommon.cache.AsyncService;
import com.joyintech.tams.exchange.common.db.domain.BatchInfo;

public interface BatchInfoService extends AsyncService<String, BatchInfo> {

    BatchInfo findByOrderId(String orderId);

}
