package com.joyintech.tams.exchange.common.bean;

import com.joyintech.tams.exchange.common.db.domain.KeyMethodInterface;

import java.io.Serializable;

public class TickByte implements Serializable, KeyMethodInterface<String> {
    private byte[] bytes;

    public TickByte() {
    }

    public TickByte(byte[] bytes) {
        this.bytes = bytes;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

    @Override
    public String getId() {
        return null;
    }

    @Override
    public void setId(String id) {

    }
}
