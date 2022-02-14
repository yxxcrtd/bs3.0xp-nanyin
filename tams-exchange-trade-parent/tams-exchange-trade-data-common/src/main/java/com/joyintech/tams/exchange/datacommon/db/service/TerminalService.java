package com.joyintech.tams.exchange.datacommon.db.service;

import com.joyintech.tams.exchange.common.db.domain.Terminal;

import java.util.List;

public interface TerminalService {
    List<Terminal> findAll();

    Terminal findByTerminalName(String name);

    int insertTerminalInfo(Terminal terminal);

    int updateTerminalInfo(String terminalName, Terminal terminal);

    int deleteTerminalInfoByNodeName(String terminalName);
}
