package com.joyintech.tams.exchange.quoteterminal.szse.binary.annotation;


import com.joyintech.tams.exchange.quoteterminal.szse.binary.enums.SZSEBinaryMsgType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SZSEBinaryMessage {
    /**
     * 消息类型
     * @return SZSEBinaryMsgType
     */
    SZSEBinaryMsgType msgType();
}
