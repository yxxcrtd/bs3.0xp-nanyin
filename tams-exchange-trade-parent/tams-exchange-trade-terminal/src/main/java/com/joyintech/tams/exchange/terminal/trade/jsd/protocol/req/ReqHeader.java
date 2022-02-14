package com.joyintech.tams.exchange.terminal.trade.jsd.protocol.req;

import com.google.common.base.Strings;

import java.lang.reflect.Field;
import java.util.Objects;

public class ReqHeader {
    public String source;
    public int reqId;
    public String functionCode;
    public String yybId;

    @Override
    public String toString() {

        Field[] fields = this.getClass().getDeclaredFields();
        StringBuilder sb = new StringBuilder().append('R').append("|").append(Objects.isNull(source) ? "" : source).
                append("|").append(reqId).append("|").append(functionCode).append("|").
                append(Strings.isNullOrEmpty(yybId) ? "" : yybId).append("|");
        try {
            for (int i = 0; i < fields.length; i++) {
                Object value = fields[i].get(this);
                if (Objects.nonNull(value)) {
                    if (value instanceof Character) {
                        if ('\0' != (char) value) {
                            sb.append(value.toString());
                        }

                    } else {
                        sb.append(value.toString());
                    }


                }
                sb.append("|");
            }
        } catch (Exception e) {

        }
        return sb.toString();
    }
}
