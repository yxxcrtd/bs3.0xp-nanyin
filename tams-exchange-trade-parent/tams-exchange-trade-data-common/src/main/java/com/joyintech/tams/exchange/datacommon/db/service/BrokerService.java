package com.joyintech.tams.exchange.datacommon.db.service;

import com.joyintech.tams.exchange.common.bean.ServerExtendInfo;
import com.joyintech.tams.exchange.datacommon.cache.AsyncService;
import com.joyintech.tams.exchange.common.db.domain.Broker;

import java.util.List;

public interface BrokerService extends AsyncService<String, Broker> {

    List<ServerExtendInfo> getServerExtendInfos(String brokerId);

    Broker findByBrokerId(String brokerId);
}
