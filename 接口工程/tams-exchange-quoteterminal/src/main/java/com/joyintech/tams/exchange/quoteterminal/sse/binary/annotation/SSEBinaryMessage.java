package com.joyintech.tams.exchange.quoteterminal.sse.binary.annotation;


import com.joyintech.tams.exchange.quoteterminal.sse.binary.enums.SSEBinaryMsgTypeEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SSEBinaryMessage {
    /**
     * 消息类型
     *
     * @return SSEBinaryMessage
     */
    SSEBinaryMsgTypeEnum msgType();
}
