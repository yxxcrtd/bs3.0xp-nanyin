package com.joyintech.tams.exchange.datacommon.db.service.impl;

import com.google.common.collect.Maps;
import com.joyintech.tams.exchange.common.db.domain.Algorithm;
import com.joyintech.tams.exchange.common.db.domain.AlgorithmExample;
import com.joyintech.tams.exchange.datacommon.cache.impl.AsyncServiceImpl;
import com.joyintech.tams.exchange.datacommon.db.mapper.AlgorithmMapper;
import com.joyintech.tams.exchange.datacommon.db.mapper.IMybatisMapper;
import com.joyintech.tams.exchange.datacommon.db.service.AlgorithmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class AlgorithmServiceImpl extends AsyncServiceImpl<String, Algorithm> implements AlgorithmService {
    @Autowired
    private AlgorithmMapper algorithmMapper;
    private Map<String, Map<String, Algorithm>> instructionMap = Maps.newConcurrentMap();

    @Override
    public IMybatisMapper<Algorithm, String, AlgorithmExample> getMapper() {
        return algorithmMapper;
    }

    @Override
    public void clearCache() {
        memory_cache.clear();
        instructionMap.clear();
    }

    @Override
    protected void update(Algorithm algorithm) {
        Map<String, Algorithm> map = instructionMap.get(algorithm.getInstructionId());
        if (Objects.isNull(map)) {
            map = Maps.newConcurrentMap();
            instructionMap.put(algorithm.getInstructionId(), map);
        }
        map.put(algorithm.getId(), algorithm);
    }

    @Override
    protected void save(Algorithm algorithm) {
        update(algorithm);
    }

    @Override
    protected void delete(String PK) {
        Map<String, Algorithm> map = instructionMap.get(PK);
        if (Objects.nonNull(map)) {
            map.remove(PK);
        }
    }

    @Override
    public List<Algorithm> findByInstruction(String instructionId) {
        Map<String, Algorithm> map = instructionMap.get(instructionId);
        if (Objects.nonNull(map)) return new ArrayList<>(map.values());
        else return new ArrayList<>();
    }
}
