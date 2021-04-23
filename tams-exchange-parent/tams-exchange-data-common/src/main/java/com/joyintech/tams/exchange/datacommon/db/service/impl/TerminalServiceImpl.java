package com.joyintech.tams.exchange.datacommon.db.service.impl;

import com.google.common.base.Strings;
import com.joyintech.tams.exchange.common.db.domain.Terminal;
import com.joyintech.tams.exchange.common.db.domain.TerminalExample;
import com.joyintech.tams.exchange.datacommon.db.mapper.TerminalMapper;
import com.joyintech.tams.exchange.datacommon.db.service.TerminalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TerminalServiceImpl implements TerminalService {
    @Autowired
    private TerminalMapper terminalMapper;

    @Override
    public List<Terminal> findAll() {
        return terminalMapper.selectByExample(null);
    }

    @Override
    public Terminal findByTerminalName(String name) {
        return terminalMapper.selectByPrimaryKey(name);
    }

    @Override
    public int insertTerminalInfo(Terminal terminal) {
        return terminalMapper.insertSelective(terminal);
    }

    @Override
    public int updateTerminalInfo(String terminalName, Terminal terminal) {
        TerminalExample terminalExample = new TerminalExample();
        TerminalExample.Criteria criteria = terminalExample.createCriteria();
        if (!Strings.isNullOrEmpty(terminalName))
            criteria.andTerminalNameEqualTo(terminalName);
        return terminalMapper.updateByExampleSelective(terminal, terminalExample);
    }

    @Override
    public int deleteTerminalInfoByNodeName(String terminalName) {
        return terminalMapper.deleteByPrimaryKey(terminalName);
    }
}
