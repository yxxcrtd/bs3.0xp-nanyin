package com.joyintech.tams.exchange.common.utils;

import com.google.common.base.Strings;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Objects;

public class ErrorMsgUtils {

    public static String subErrorMsg(String errorMsg) {
        if (!Strings.isNullOrEmpty(errorMsg)) {
            byte[] errorMsgs = errorMsg.getBytes();
            if (errorMsgs.length > 126) {
                return new String(Arrays.copyOfRange(errorMsgs, 0, 126), StandardCharsets.UTF_8);
            }
        }
        if (Objects.isNull(errorMsg)) return "";
        return errorMsg;
    }
}
