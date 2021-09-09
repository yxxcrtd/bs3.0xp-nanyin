package com.joyintech.tams.exchange.quoteterminal.szse.binary.model.field;


import com.joyintech.tams.exchange.quoteterminal.szse.binary.enums.SZSEBinaryExchangeStatus;
import com.joyintech.tams.exchange.quoteterminal.szse.binary.enums.SZSEBinarySecurityStatus;
import com.joyintech.tams.exchange.quoteterminal.szse.binary.model.field.base.StringField;

/**
 * 产品所处的交易阶段代码
 * char[8]
 */
public class TradePhaseCode extends StringField {

    public static int FIELD_LENGTH = 8;

    private final SZSEBinaryExchangeStatus exchangeStatus;

    private final SZSEBinarySecurityStatus securityStatus;

    public TradePhaseCode(String code) {
        super(code, FIELD_LENGTH);
        this.exchangeStatus = SZSEBinaryExchangeStatus.lookup(value.substring(0, 1)).orElse(SZSEBinaryExchangeStatus.UNKNOWN);
        this.securityStatus = SZSEBinarySecurityStatus.lookup(value.substring(1));
    }

    public TradePhaseCode(SZSEBinarySecurityStatus securityStatus, SZSEBinaryExchangeStatus exchangeStatus) {
        super(exchangeStatus.getStatus() + securityStatus.getStatusCode(), FIELD_LENGTH);
        this.exchangeStatus = exchangeStatus;
        this.securityStatus = securityStatus;
    }

    public SZSEBinarySecurityStatus getSecurityStatus() {
        return securityStatus;
    }

    public SZSEBinaryExchangeStatus getExchangeStatus() {
        return exchangeStatus;
    }
}
