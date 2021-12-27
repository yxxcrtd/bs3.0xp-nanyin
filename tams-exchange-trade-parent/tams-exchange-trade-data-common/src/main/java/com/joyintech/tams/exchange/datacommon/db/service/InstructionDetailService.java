package com.joyintech.tams.exchange.datacommon.db.service;

import com.joyintech.tams.exchange.common.db.domain.InstructionDetail;
import com.joyintech.tams.exchange.datacommon.cache.AsyncService;

import java.util.List;
import java.util.Set;

public interface InstructionDetailService extends AsyncService<String, InstructionDetail> {

    List<InstructionDetail> queryInstructionDetailsByInstructionId(String instructionId);

    void deleteInstructionDetailsByInstructionsIds(Set<String> instructionIds);

}
