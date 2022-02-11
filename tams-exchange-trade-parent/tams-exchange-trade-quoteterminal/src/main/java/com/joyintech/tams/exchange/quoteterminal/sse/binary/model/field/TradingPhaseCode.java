package com.joyintech.tams.exchange.quoteterminal.sse.binary.model.field;


import com.joyintech.tams.exchange.quoteterminal.sse.binary.enums.SSEBinaryExchangeStatus;
import com.joyintech.tams.exchange.quoteterminal.sse.binary.enums.SSEBinarySecurityStatus;
import com.joyintech.tams.exchange.quoteterminal.sse.binary.model.field.base.StringField;

/**
 * 产品所处的交易阶段代码
 * char[8]
 */
public class TradingPhaseCode extends StringField {

    public static int FIELD_LENGTH = 8;

    private final SSEBinaryExchangeStatus exchangeStatus;

    private final SSEBinarySecurityStatus securityStatus;

    public TradingPhaseCode(String code) {
        super(code, FIELD_LENGTH);
        this.exchangeStatus = SSEBinaryExchangeStatus.lookup(value.substring(0, 1)).orElse(SSEBinaryExchangeStatus.UNKNOWN);
        this.securityStatus = SSEBinarySecurityStatus.lookup(value.substring(1));
    }

    public TradingPhaseCode(SSEBinarySecurityStatus securityStatus, SSEBinaryExchangeStatus exchangeStatus) {
        super(exchangeStatus.getStatus() + securityStatus.getStatusCode(), FIELD_LENGTH);
        this.exchangeStatus = exchangeStatus;
        this.securityStatus = securityStatus;
    }

    public SSEBinarySecurityStatus getSecurityStatus() {
        return securityStatus;
    }

    public SSEBinaryExchangeStatus getExchangeStatus() {
        return exchangeStatus;
    }
}
