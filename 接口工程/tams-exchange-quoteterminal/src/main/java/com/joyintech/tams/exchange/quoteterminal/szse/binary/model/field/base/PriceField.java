package com.joyintech.tams.exchange.quoteterminal.szse.binary.model.field.base;


import com.joyintech.tams.exchange.quoteterminal.szse.binary.util.BinaryUtil;

public class PriceField implements Field<Long> {

    private long value;

    public PriceField(long value) {
        if(value == Long.MAX_VALUE){
            value = 0;
        }
        this.value = value;
    }

    @Override
    public byte[] toBytes() {
        byte[] longByte = new byte[8];
        longByte[0] = (byte) ((byte) (value >>> 56) & 0xFF);
        longByte[1] = (byte) ((byte) (value >>> 48) & 0xFF);
        longByte[2] = (byte) ((byte) (value >>> 40) & 0xFF);
        longByte[3] = (byte) ((byte) (value >>> 32) & 0xFF);
        longByte[4] = (byte) ((byte) (value >>> 24) & 0xFF);
        longByte[5] = (byte) ((byte) (value >>> 16) & 0xFF);
        longByte[6] = (byte) ((byte) (value >>> 8) & 0xFF);
        longByte[7] = (byte) ((byte) (value) & 0xFF);
        return longByte;
    }

    @Override
    public void resumeFrom(byte[] bytes) {
        this.value = BinaryUtil.bytesToLong(bytes);
    }

    public double getDoubleValue() {
        return value;
    }

    @Override
    public String toString() {
        return "PriceField{" +
                "value=" + value +
                '}';
    }
}
