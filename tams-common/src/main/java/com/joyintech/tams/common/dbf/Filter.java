package com.joyintech.tams.common.dbf;

/**
 * @author 何阳阳
 * @date 2020/7/1
 * @see Filter
 * @since 1.0
 */
@FunctionalInterface
public interface Filter {
    boolean accept(DBFRow field);
}
