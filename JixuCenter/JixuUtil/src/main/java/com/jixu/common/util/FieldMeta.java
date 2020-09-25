package com.jixu.common.util;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/** 
 * @ClassName 		FieldMeta
 * @author  		Pan Wei 
 * @Date 			2017年4月29日 下午10:16:47 	
 * @Description		[]  
 * @return  
 */

@Retention(RetentionPolicy.RUNTIME) // 注解会在class字节码文件中存在，在运行时可以通过反射获取到  
@Target({ElementType.FIELD})//定义注解的作用目标**作用范围字段、枚举的常量/方法  
@Documented//说明该注解将被包含在javadoc中  
public @interface FieldMeta {  
	
	/**
	 * 字段名称
	 * @return
	 */
	String name() default "";

	String desc() default "";
}  
