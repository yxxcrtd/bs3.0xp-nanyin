package com.joyintech.tams.exchange.quoteterminal.sse.binary.model.field.base;


public interface Field<T> {
    /**
     * 转成字节数组
     *
     * @return byte array
     */
    byte[] toBytes();

    /**
     * 从字节中读取
     * @param bytes 字节数据
     */
     void resumeFrom(byte[] bytes);
}
