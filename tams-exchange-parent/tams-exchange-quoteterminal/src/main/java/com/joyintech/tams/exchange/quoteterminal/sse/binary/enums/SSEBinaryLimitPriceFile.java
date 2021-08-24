package com.joyintech.tams.exchange.quoteterminal.sse.binary.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * create By MAXX on 2021/1/22
 */
public enum SSEBinaryLimitPriceFile {
    cpxx("cpxx", "第一版第一批次"),
    cpxx0102("cpxx0102", "第一版第二批次"),
    cpxx0201("cpxx0201", "第二版第一批次"),
    cpxx0202("cpxx0202", "第二版第二批次");

    public final String value;
    public final String name;

    SSEBinaryLimitPriceFile(String value, String name) {
        this.value = value;
        this.name = name;
    }

    private static Map<Integer, String> fileMap = new HashMap<>();

    public static String getFileName(Integer n) {
        return fileMap.get(n);
    }

    public static int getFileLen() {
        return fileMap.size();
    }

    static {
        int n = 0;
        fileMap.put(n++, SSEBinaryLimitPriceFile.cpxx0202.value);
        fileMap.put(n++, SSEBinaryLimitPriceFile.cpxx0201.value);
        fileMap.put(n++, SSEBinaryLimitPriceFile.cpxx0102.value);
        fileMap.put(n++, SSEBinaryLimitPriceFile.cpxx.value);
    }

}
