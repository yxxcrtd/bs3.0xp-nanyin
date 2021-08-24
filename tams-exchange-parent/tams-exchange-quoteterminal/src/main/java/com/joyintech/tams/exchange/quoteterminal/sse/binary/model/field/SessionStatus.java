package com.joyintech.tams.exchange.quoteterminal.sse.binary.model.field;

import com.joyintech.tams.exchange.quoteterminal.sse.binary.model.field.base.UInt32Field;

/**
 * Logout 时的会话状态
 * uint32
 */
public class SessionStatus extends UInt32Field {

    public SessionStatus(long value) {
        super(value);
    }
}
