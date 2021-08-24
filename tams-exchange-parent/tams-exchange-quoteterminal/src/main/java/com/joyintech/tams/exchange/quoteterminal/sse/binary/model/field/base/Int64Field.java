package com.joyintech.tams.exchange.quoteterminal.sse.binary.model.field.base;


import com.joyintech.tams.exchange.quoteterminal.sse.binary.util.BinaryUtil;

import java.nio.ByteBuffer;


public class Int64Field implements Field<Integer> {

    protected long value;

    public Int64Field(long value) {
        if (value == Long.MAX_VALUE) {
            value = 0;
        }
        this.value = value;
    }

    public Int64Field() {
    }

    @Override
    public byte[] toBytes() {
        ByteBuffer buffer = ByteBuffer.allocate(8);
        buffer.putLong(value);
        return buffer.array();
    }

    @Override
    public void resumeFrom(byte[] bytes) {
        this.value = BinaryUtil.bytesToLong(bytes);
    }

    public long getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Int64Field{" +
                "value=" + value +
                '}';
    }
}
