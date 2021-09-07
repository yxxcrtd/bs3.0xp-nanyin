package com.joyintech.tams.svreport.pbc.common.annotation;


import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Retention(RUNTIME)
@Target({TYPE})
public @interface ReportGroup {
    //分组
    String group() default "";
    //需不需要除以10000
    boolean isDivide() default true;

}
