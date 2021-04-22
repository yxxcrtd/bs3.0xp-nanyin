package com.joyintech.tams.exchange.datacommon.db.service;

import com.joyintech.tams.exchange.common.db.domain.Bar;

import java.util.List;

public interface BarService {
    List<Bar> queryLastDaysBar(String instrumentId, int days, String tradingDay);
}
