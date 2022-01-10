package com.joyintech.tams.exchange.service.trade.management.service;

import com.google.common.base.Strings;
import com.joyintech.tams.exchange.common.bean.ResultBean;
import com.joyintech.tams.exchange.common.constant.ErrorCode;
import com.joyintech.tams.exchange.common.db.domain.Terminal;
import com.joyintech.tams.exchange.common.db.domain.TerminalMapping;
import com.joyintech.tams.exchange.datacommon.db.service.TerminalMappingService;
import com.joyintech.tams.exchange.datacommon.db.service.TerminalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class TerminalManagement {
    @Autowired
    private TerminalService terminalService;

    @Autowired
    private TerminalMappingService terminalMappingService;

    public ResultBean queryTerminalInfo(String terminalName) {
        if (!Strings.isNullOrEmpty(terminalName)) {
            return new ResultBean(ErrorCode.SUCCESS, terminalService.findByTerminalName(terminalName));
        } else {
            return new ResultBean(ErrorCode.SUCCESS, terminalService.findAll());
        }
    }

    public ResultBean addTerminalInfo(Terminal terminal) {
        if (Objects.isNull(terminal)) return new ResultBean(ErrorCode.REQ_PARAMETER_ERROR);
        return new ResultBean(ErrorCode.SUCCESS, terminalService.insertTerminalInfo(terminal));
    }

    public ResultBean updateTerminalInfo(String terminalName, Terminal terminal) {
        if (Objects.isNull(terminal) || Strings.isNullOrEmpty(terminalName))
            return new ResultBean(ErrorCode.REQ_PARAMETER_ERROR);
        return new ResultBean(ErrorCode.SUCCESS, terminalService.updateTerminalInfo(terminalName, terminal));
    }

    public ResultBean deleteTerminalInfo(String terminalName) {
        if (Strings.isNullOrEmpty(terminalName)) return new ResultBean(ErrorCode.REQ_PARAMETER_ERROR);
        List<TerminalMapping> terminalMappings = terminalMappingService.findByTerminalName(terminalName);
        if (Objects.nonNull(terminalMappings) && terminalMappings.size() > 0)
            return new ResultBean(ErrorCode.TERMINAL_DELETE_ERROR);
        else return new ResultBean(ErrorCode.SUCCESS, terminalService.deleteTerminalInfoByNodeName(terminalName));
    }
}
