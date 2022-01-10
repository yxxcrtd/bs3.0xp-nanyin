package com.joyintech.tams.exchange.quoteterminal.sse.binary.model.field.base;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.nio.ByteBuffer;


public class UInt16Field implements Field {

    protected int value;

    public UInt16Field(int value) {
        if(value == Integer.MAX_VALUE){
            value = 0;
        }
        this.value = value;
    }

    @Override
    public byte[] toBytes() {
        ByteBuffer buffer = ByteBuffer.allocate(2);
        buffer.putShort((short) value);
        return buffer.array();
    }

    @Override
    public void resumeFrom(byte[] bytes) {
        ByteBuf byteBuf = Unpooled.wrappedBuffer(bytes);
        this.value = byteBuf.readUnsignedShort();
    }

    public UInt16Field() {
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "UInt16Field{" +
                "value=" + value +
                '}';
    }
}
