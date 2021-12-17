package com.joyintech.tams.exchange.common.constant;

import java.util.*;

/**
 * @author liujianping
 * @ClassName DerivativeOcFlag
 * @Description
 * @date 2020/11/13 9:38
 * @Version
 **/

public enum DerivativeOcFlag {
    Open("开仓", "01", "0"),
    Close("平仓", "02", "1");

    public final String value;
    public final String name;
    public final String offset;

    DerivativeOcFlag(String name, String value, String offset) {
        this.name = name;
        this.value = value;
        this.offset = offset;
    }

    private static Map<String, DerivativeOcFlag> ocFlagMap = new HashMap<>();

    static {
        ocFlagMap.put(Open.value, Open);
        ocFlagMap.put(Close.value, Close);
    }

    public static String getOffsetFlagByDerivativeOcFlag(String derivativeOcFlag) {
        DerivativeOcFlag item = ocFlagMap.get(derivativeOcFlag);
        if (Objects.nonNull(item)) {
            return item.offset;
        } else {
            return null;
        }
    }
}
