/**
 * 
 */
package com.joyintech.tams.common.anno;

import static java.lang.annotation.ElementType.FIELD;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(FIELD)
/**
 * 标注数据字典值和名称在一起的字段
 * 
 * @author dubaojun
 * @since 2020-10-16
 */
public @interface ItemAndNameDictCode {

    /**
     * 字典值与名称分割符
     */
    String separator() default " ";

    /**
     * 多个数据字典之间分割符
     */
    String dictSeparator() default ",";
}
