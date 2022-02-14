package com.joyintech.tams.exchange.datacommon.db.service;

import com.joyintech.tams.exchange.common.db.domain.Algorithm;
import com.joyintech.tams.exchange.datacommon.cache.AsyncService;

import java.util.List;

public interface AlgorithmService extends AsyncService<String, Algorithm> {
    List<Algorithm> findByInstruction(String instructionId);
}
