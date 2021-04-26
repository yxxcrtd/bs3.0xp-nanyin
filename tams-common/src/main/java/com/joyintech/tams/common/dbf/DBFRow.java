package com.joyintech.tams.common.dbf;

import java.util.HashMap;

/**
 * @author 何阳阳
 * @date 2020/7/1
 * @see DBFRow
 * @since 1.0
 */
public class DBFRow extends HashMap<String, Field> {

    public DBFRow(int length) {
        super(length);
    }
}
