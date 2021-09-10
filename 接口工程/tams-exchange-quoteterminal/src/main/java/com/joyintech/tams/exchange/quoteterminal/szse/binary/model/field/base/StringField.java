package com.joyintech.tams.exchange.quoteterminal.szse.binary.model.field.base;

import com.joyintech.tams.exchange.quoteterminal.szse.binary.util.SZSEBinaryUtil;
import com.joyintech.tams.exchange.quoteterminal.szse.binary.util.SZSEBinaryUtil;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class StringField implements Field {

    private int byteSize;

    protected String value;

    public StringField(String value, int byteSize) {
        this.value = value.trim();
        this.byteSize = byteSize;
    }

    public StringField() {
    }

    @Override
    public byte[] toBytes() {
        ByteBuffer allocate = ByteBuffer.allocate(byteSize);
        allocate.put(SZSEBinaryUtil.rightPad(value, byteSize, ' ').getBytes(StandardCharsets.UTF_8));
        return allocate.array();
    }

    @Override
    public void resumeFrom(byte[] bytes) {
        value = new String(bytes, StandardCharsets.UTF_8).trim();
    }

    @Override
    public String toString() {
        return value;
    }

    public String getValue() {
        return value;
    }
}
