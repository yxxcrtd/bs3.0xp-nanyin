package com.joyintech.tams.exchange.datacommon.db.service;

import com.joyintech.tams.exchange.common.db.domain.InstructionPledge;
import com.joyintech.tams.exchange.datacommon.cache.AsyncService;

import java.util.List;
import java.util.Set;

public interface InstructionPledgeService extends AsyncService<String, InstructionPledge> {

    List<InstructionPledge> queryInstructionPledgesByInstructionId(String instructionId);

    void deleteInstructionPledgesByInstructionsIds(Set<String> instructionIds);

    InstructionPledge queryInstructionPledgesByInstructionIdAndStockCode(String instructionId, String stockCode);
}
