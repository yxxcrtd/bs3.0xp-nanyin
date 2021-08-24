package com.joyintech.tams.exchange.quoteterminal.szse.binary.model.field;


import com.joyintech.tams.exchange.quoteterminal.szse.binary.model.field.base.Int64Field;

/**
 * 最后一条行情消息的记录号
 * int64
 */
public class ApplLastSeqNum extends Int64Field {

    public ApplLastSeqNum(long appLastSeqNum) {
        super(appLastSeqNum);
    }

    public ApplLastSeqNum() {
    }
}
