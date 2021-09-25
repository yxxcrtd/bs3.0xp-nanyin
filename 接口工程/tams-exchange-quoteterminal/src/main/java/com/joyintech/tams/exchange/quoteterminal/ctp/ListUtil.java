package com.joyintech.tams.exchange.quoteterminal.ctp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * create By MAXX on 2020/12/30
 */
public class ListUtil {
    private static int MAX_LENGTH = 1000;

    public static List<String[]> getList(List<String> list) {
        List<String[]> reList = new ArrayList<>();
        String[] instrumentList = null;
        if (list.size() <= MAX_LENGTH) {
            instrumentList = list.toArray(new String[0]);
        } else {
            instrumentList = new String[MAX_LENGTH];
            int i = 0;
            for (String instrument : list) {
                instrumentList[i] = instrument;
                i++;
                if (i >= MAX_LENGTH) {
                    reList.add(instrumentList);
                    i = 0;
                }
            }
            instrumentList = Arrays.copyOf(instrumentList, i);
        }
        reList.add(instrumentList);
        return reList;

    }
}
