package com.joyintech.tams.exchange.datacommon.db.service.impl;

import com.joyintech.tams.exchange.common.db.domain.Bar;
import com.joyintech.tams.exchange.datacommon.db.mapper.BarMapper;
import com.joyintech.tams.exchange.datacommon.db.service.BarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BarServiceImpl implements BarService {
    @Autowired
    private BarMapper barMapper;

    @Override
    public List<Bar> queryLastDaysBar(String instrumentId, int days, String tradingDay) {
        return barMapper.queryLastDaysBar(instrumentId, days, tradingDay);
    }
}
