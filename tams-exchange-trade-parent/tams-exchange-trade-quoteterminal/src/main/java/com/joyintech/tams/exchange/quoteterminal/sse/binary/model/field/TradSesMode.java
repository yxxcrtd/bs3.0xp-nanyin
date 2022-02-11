package com.joyintech.tams.exchange.quoteterminal.sse.binary.model.field;

import com.joyintech.tams.exchange.quoteterminal.sse.binary.enums.SSEBinarySecurityTypeEnum;
import com.joyintech.tams.exchange.quoteterminal.sse.binary.enums.SSEBinaryTradSesModeEnum;
import com.joyintech.tams.exchange.quoteterminal.sse.binary.model.field.base.UInt8Field;

/**
 * 证券类型
 * uint8
 */
public class TradSesMode extends UInt8Field {

    public TradSesMode(int type) {
        super(type);
    }

    private SSEBinaryTradSesModeEnum tradSesModeEnum;

    public TradSesMode(SSEBinaryTradSesModeEnum tradSesModeEnum) {
        super(tradSesModeEnum.getCode());
        this.tradSesModeEnum = tradSesModeEnum;
    }

    public SSEBinaryTradSesModeEnum getEnumValue(){
        return tradSesModeEnum;
    }
}
