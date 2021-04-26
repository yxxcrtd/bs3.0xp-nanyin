package com.joyintech.tams.valuation.valapi.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author yanya
 * @create 2021-02-02 9:17
 */
@Target(ElementType.METHOD)//方法
@Retention(RetentionPolicy.RUNTIME)
public @interface ValInterfApiMethod {
    //作用：表示该api方法需要进行拦截，并进行参数验证
}
