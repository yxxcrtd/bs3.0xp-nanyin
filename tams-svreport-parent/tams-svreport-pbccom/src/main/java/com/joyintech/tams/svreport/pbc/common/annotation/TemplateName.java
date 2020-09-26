package com.joyintech.tams.svreport.pbc.common.annotation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * 设置使用的模板
 * @author zhangzhongliang
 * @version 1.0 2019年3月04日
 * @see TemplateName
 * @since 1.0
 */


@Documented
@Retention(RUNTIME)
@Target({TYPE})
public @interface TemplateName {
	    String value();

}
