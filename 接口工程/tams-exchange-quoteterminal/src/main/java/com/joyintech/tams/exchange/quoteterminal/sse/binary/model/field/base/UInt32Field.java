package com.joyintech.tams.exchange.quoteterminal.sse.binary.model.field.base;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.nio.ByteBuffer;


public class UInt32Field implements Field {

    protected long value;

    public UInt32Field(long value) {
        if(value == Long.MAX_VALUE){
            value = 0;
        }
        this.value = value;
    }

    @Override
    public byte[] toBytes() {
        ByteBuffer buffer = ByteBuffer.allocate(4);
        buffer.putInt((int) value);
        return buffer.array();
    }

    @Override
    public void resumeFrom(byte[] bytes) {
        ByteBuf byteBuf = Unpooled.wrappedBuffer(bytes);
        this.value = byteBuf.readUnsignedInt();
    }

    public UInt32Field() {
    }

    public long getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "UInt32Field{" +
                "value=" + value +
                '}';
    }
}
