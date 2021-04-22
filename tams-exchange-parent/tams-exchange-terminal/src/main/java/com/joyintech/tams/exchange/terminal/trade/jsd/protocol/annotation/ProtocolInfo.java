package com.joyintech.tams.exchange.terminal.trade.jsd.protocol.annotation;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface ProtocolInfo {
    String functionCode();

    Class respHeaderClass();

    Class respDataClass() default Object.class;

    Class respTotalClass() default Object.class;
}
