package com.joyintech.tams.exchange.datacommon.db.service;

import com.joyintech.tams.exchange.common.db.domain.TerminalMapping;

import java.util.List;

public interface TerminalMappingService {
    List<TerminalMapping> findAll();

    List<TerminalMapping> findByTerminalName(String name);

    List<TerminalMapping> findByAccountId(String accountId);

    TerminalMapping findByAccountIdAndTerminalName(String accountId, String terminalName);

    int deleteTerminalMappingInfoById(String id);

    int deleteTerminalMappingInfoByAccountId(String accountId);

    int updateTerminalMappingInfo(String id, TerminalMapping terminalMapping);

    int insertTerminalMappingInfo(List<TerminalMapping> terminalMappings);
}
