package com.joyintech.tams.exchange.datacommon.db.service;

import com.joyintech.tams.exchange.common.db.domain.Instruction;
import com.joyintech.tams.exchange.datacommon.cache.AsyncService;

import java.util.List;

public interface InstructionService extends AsyncService<String, Instruction> {
    List<Instruction> queryInstructionByUserIdAndStockCode(String userId, String stockCode);

    void deleteTraderCache(String tradeId, String finprodMarketId, String entrustId);
}
