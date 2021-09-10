package com.joyintech.tams.exchange.terminal.trade.kdgateway;

import com.joyintech.tams.exchange.terminal.utils.AddressUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(value = "KDGateway.active", matchIfMissing = false, havingValue = "true")
public class KDGTerminalInfo {

    private final static Logger logger = LoggerFactory.getLogger(KDGTerminalInfo.class);

    @Value("${KDGateway.client.mark}")
    private String mark;

    @Bean("terminalInfo")
    public String getTerminalInfo() {
        return AddressUtils.genOpStation(mark);
    }
}
