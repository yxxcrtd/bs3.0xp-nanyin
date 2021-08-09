package com.joyintech.tams.svreport.pbc.common.annotation;


import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Retention(RUNTIME)
@Target({FIELD})
public @interface ExcelCell {
    //列号
    int columnNumber() default -1;
    //报表propertyCode 由开发人员指定
    boolean isPropertyCode() default false;
    //报表类型 由开发人员指定
    boolean isReportType() default false;
    //报表分组类型 由开发人员指定
    boolean isGroupType() default false;

}
