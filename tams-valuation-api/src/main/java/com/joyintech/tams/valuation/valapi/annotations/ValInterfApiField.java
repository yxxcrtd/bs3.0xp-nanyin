package com.joyintech.tams.valuation.valapi.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author yanya
 * @create 2021-02-02 9:35
 */
@Retention(RUNTIME)
@Target(FIELD)//字段
public @interface ValInterfApiField {
    //作用：表示该字段不能为空
    public String type() default "01";//类型为默认
    public String appointField() default "";//当类型为02条件时，指定的字段
    public String appointVal() default "";//当类型为02条件时，指定的字段的值，可以是多个，以指定字符分割
    public String splitStr() default ",";//当类型为02条件时，指定的字段值的分割字符，默认以【,】分割
    public String dictCode() default "";//字段的值必须包含在指定的数据字典中，可以是多个，已指定的分割符分割
    public String fieldVal() default "";//字段值必须在指定范围值内
    //public String colName() default "";//字段中文名称
}
