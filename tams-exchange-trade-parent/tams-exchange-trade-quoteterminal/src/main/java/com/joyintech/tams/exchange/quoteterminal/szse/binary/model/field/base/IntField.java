package com.joyintech.tams.exchange.quoteterminal.szse.binary.model.field.base;


import com.joyintech.tams.exchange.quoteterminal.szse.binary.util.BinaryUtil;

import java.nio.ByteBuffer;


public class IntField implements Field<Integer> {

    protected int value;

    public IntField(int value) {
        if (value == Integer.MAX_VALUE) {
            value = 0;
        }
        this.value = value;
    }

    public IntField() {
    }

    @Override
    public byte[] toBytes() {
        ByteBuffer buffer = ByteBuffer.allocate(4);
        buffer.putInt(value);
        return buffer.array();
    }

    @Override
    public void resumeFrom(byte[] bytes) {
        this.value = BinaryUtil.bytesToInt(bytes);
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "IntField{" +
                "value=" + value +
                '}';
    }
}
