package com.joyintech.tams.exchange.quoteterminal.sse.binary.model.field;

import com.joyintech.tams.exchange.quoteterminal.sse.binary.enums.SSEBinarySecurityTypeEnum;
import com.joyintech.tams.exchange.quoteterminal.sse.binary.model.field.base.UInt8Field;

/**
 * 证券类型
 * uint8
 */
public class SecurityType extends UInt8Field {

    private SSEBinarySecurityTypeEnum securityTypeEnum;

    public SecurityType(int type) {
        super(type);
    }

    public SecurityType(SSEBinarySecurityTypeEnum securityTypeEnum) {
        super(securityTypeEnum.getCode());
        this.securityTypeEnum = securityTypeEnum;
    }

    public SSEBinarySecurityTypeEnum getEnumValue() {
        return securityTypeEnum;
    }
}
