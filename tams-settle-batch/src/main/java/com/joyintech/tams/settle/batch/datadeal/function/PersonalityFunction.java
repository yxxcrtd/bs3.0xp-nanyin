package com.joyintech.tams.settle.batch.datadeal.function;

import java.text.ParseException;

/**
 * @author 何阳阳
 * @date 2020/7/11
 * @see PersonalityFunction
 * @since 1.0
 */
@FunctionalInterface
public interface PersonalityFunction<T, R, X> {
    boolean apply(T t, R r, X x) throws ParseException;
}
