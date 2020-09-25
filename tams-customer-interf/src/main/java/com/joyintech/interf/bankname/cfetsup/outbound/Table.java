package com.joyintech.interf.bankname.cfetsup.outbound;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Table {
    String value();
}
