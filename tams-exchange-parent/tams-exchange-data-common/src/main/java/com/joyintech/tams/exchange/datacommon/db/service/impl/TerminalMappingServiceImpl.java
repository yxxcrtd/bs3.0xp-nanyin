package com.joyintech.tams.exchange.datacommon.db.service.impl;

import com.google.common.base.Strings;
import com.joyintech.tams.exchange.common.db.domain.TerminalMapping;
import com.joyintech.tams.exchange.common.db.domain.TerminalMappingExample;
import com.joyintech.tams.exchange.datacommon.db.mapper.TerminalMappingMapper;
import com.joyintech.tams.exchange.datacommon.db.service.TerminalMappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class TerminalMappingServiceImpl implements TerminalMappingService {
    @Autowired
    private TerminalMappingMapper terminalMappingMapper;

    @Override
    public List<TerminalMapping> findAll() {
        List<TerminalMapping> terminalMappings = terminalMappingMapper.selectByExample(null);
        if (Objects.isNull(terminalMappings)) return new ArrayList<>();
        else return terminalMappings;
    }

    @Override
    public List<TerminalMapping> findByTerminalName(String name) {
        return findTerminalMapping(null, name);
    }

    @Override
    public List<TerminalMapping> findByAccountId(String accountId) {
        return findTerminalMapping(accountId, null);
    }

    @Override
    public TerminalMapping findByAccountIdAndTerminalName(String accountId, String terminalName) {
        List<TerminalMapping> list = findTerminalMapping(accountId, terminalName);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public int deleteTerminalMappingInfoById(String id) {
        return terminalMappingMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int deleteTerminalMappingInfoByAccountId(String accountId) {
        TerminalMappingExample terminalMappingExample = new TerminalMappingExample();
        TerminalMappingExample.Criteria criteria = terminalMappingExample.createCriteria();
        criteria.andAccountIdEqualTo(accountId);
        return terminalMappingMapper.deleteByExample(terminalMappingExample);
    }

    @Override
    public int updateTerminalMappingInfo(String id, TerminalMapping terminalMapping) {
        TerminalMappingExample terminalMappingExample = new TerminalMappingExample();
        TerminalMappingExample.Criteria criteria = terminalMappingExample.createCriteria();
        if (!Strings.isNullOrEmpty(id))
            criteria.andIdEqualTo(id);
        return terminalMappingMapper.updateByExampleSelective(terminalMapping, terminalMappingExample);
    }

    @Override
    public int insertTerminalMappingInfo(List<TerminalMapping> terminalMappings) {
        return terminalMappings.stream().mapToInt(terminalMapping -> terminalMappingMapper.insertSelective(terminalMapping)).sum();
    }

    private List<TerminalMapping> findTerminalMapping(String accountId, String terminalName) {
        TerminalMappingExample example = new TerminalMappingExample();
        TerminalMappingExample.Criteria criteria = example.createCriteria();
        if (!Strings.isNullOrEmpty(accountId)) {
            criteria.andAccountIdEqualTo(accountId);
        }
        if (!Strings.isNullOrEmpty(terminalName)) {
            criteria.andTerminalNameEqualTo(terminalName);
        }
        return terminalMappingMapper.selectByExample(example);
    }
}
