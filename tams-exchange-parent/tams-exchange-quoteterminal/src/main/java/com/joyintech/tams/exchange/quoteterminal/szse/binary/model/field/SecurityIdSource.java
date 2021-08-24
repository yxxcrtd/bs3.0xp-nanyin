package com.joyintech.tams.exchange.quoteterminal.szse.binary.model.field;


import com.joyintech.tams.exchange.quoteterminal.szse.binary.model.field.base.StringField;

/**
 * 证券代码源
 *      102=深圳证券交易所
 *      103=香港交易所
 * char[4]
 */

public class SecurityIdSource extends StringField {

    public static int FIELD_LENGTH = 4;

    public SecurityIdSource(String streamId) {
        super(streamId, FIELD_LENGTH);
    }
}
