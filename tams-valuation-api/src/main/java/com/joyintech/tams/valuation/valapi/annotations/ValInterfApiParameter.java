package com.joyintech.tams.valuation.valapi.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author yanya
 *
 * @create 2021-02-02 9:17
 */
@Target(ElementType.PARAMETER)//参数
@Retention(RetentionPolicy.RUNTIME)
public @interface ValInterfApiParameter {
    //作用：表示该参数不能为空
}
