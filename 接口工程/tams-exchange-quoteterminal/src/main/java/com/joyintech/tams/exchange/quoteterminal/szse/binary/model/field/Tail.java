package com.joyintech.tams.exchange.quoteterminal.szse.binary.model.field;


import com.joyintech.tams.exchange.quoteterminal.szse.binary.model.field.base.UInt32Field;

/**
 * 消息尾
 * uInt32
 */
public class Tail extends UInt32Field {

    public Tail(int checksum) {
        super(checksum);
    }

    // 校验和
    public static Tail generateChecksum(byte[] data){
        int idx = 0;
        int cks = 0;
        //此处需要减去末尾所占的4个字节的checksum值
        while(idx < data.length - 4){
            cks += data[idx++];
        }
        return new Tail(cks % 256);
    }
}
