package com.joyintech.component.svrreport.chb.contant.annotate;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;



/**
 * 标记该字段需要除以100
 * @author lingxq
 * @version 1.0 2020年12月24日
 * @see RateFormat
 * @since 1.0
 */
@Retention(RUNTIME)
@Target(FIELD)
public @interface RateFormat {
	
}
