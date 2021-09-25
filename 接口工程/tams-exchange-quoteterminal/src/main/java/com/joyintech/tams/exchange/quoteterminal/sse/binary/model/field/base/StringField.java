package com.joyintech.tams.exchange.quoteterminal.sse.binary.model.field.base;

import com.joyintech.tams.exchange.quoteterminal.sse.binary.util.SSEBinaryUtil;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

public class StringField implements Field {

    public static final Charset GBK = Charset.forName("GBK");

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
        allocate.put(SSEBinaryUtil.rightPad(value, byteSize, ' ').getBytes(GBK));
        return allocate.array();
    }

    @Override
    public void resumeFrom(byte[] bytes) {
        value = new String(bytes, GBK).trim();
    }

    @Override
    public String toString() {
        return value;
    }

    public String getValue() {
        return value;
    }
}
