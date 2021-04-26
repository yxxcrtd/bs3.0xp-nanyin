package com.joyintech.tams.common.dbf;

/**
 * @author 何阳阳
 * @date 2020/7/1
 * @see Converter
 * @since 1.0
 */
@FunctionalInterface
public interface Converter {
    Object converter(String fieldName, Object value);
}
