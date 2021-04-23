package com.joyintech.tams.exchange.common.utils;

import com.joyintech.tams.exchange.common.constant.CbptransType;
import com.joyintech.tams.exchange.common.constant.EntrustBs;
import com.joyintech.tams.exchange.common.constant.EntrustProp;

/**
 * create By MAXX on 2020/10/15
 */
public class CbptransTypeUtils {

    public static String getCbptransTypeByEntrustBsAndEntrustProp(String entrustBs, String entrustProp) {
        String cbptransType = "";
        if (EntrustBs.BUY.value.equals(entrustBs) && EntrustProp.INTENTION.value.equals(entrustProp)) {
            cbptransType = CbptransType.YXMR.name;
        } else if (EntrustBs.SELL.value.equals(entrustBs) && EntrustProp.INTENTION.value.equals(entrustProp)) {
            cbptransType = CbptransType.YXMC.name;
        } else if (EntrustBs.BUY.value.equals(entrustBs) && EntrustProp.SET_PRICE.value.equals(entrustProp)) {
            cbptransType = CbptransType.DJMR.name;
        } else if (EntrustBs.SELL.value.equals(entrustBs) && EntrustProp.SET_PRICE.value.equals(entrustProp)) {
            cbptransType = CbptransType.DJMC.name;
        }
        return cbptransType;
    }

    public static String getEntrustBsByCbptransType(String cbptransType) {
        String entrustBs = "";
        if (CbptransType.YXMR.name.equals(cbptransType) || CbptransType.DJMR.name.equals(cbptransType)) {
            entrustBs = EntrustBs.BUY.value;
        } else if (CbptransType.YXMC.name.equals(cbptransType) || CbptransType.DJMC.name.equals(cbptransType)) {
            entrustBs = EntrustBs.SELL.value;
        }
        return entrustBs;
    }

    public static String getEntrustPropByCbptransType(String cbptransType) {
        String entrustProp = "";
        if (CbptransType.YXMR.name.equals(cbptransType) || CbptransType.YXMC.name.equals(cbptransType)) {
            entrustProp = EntrustProp.INTENTION.value;
        } else if (CbptransType.DJMR.name.equals(cbptransType) || CbptransType.DJMC.name.equals(cbptransType)) {
            entrustProp = EntrustProp.SET_PRICE.value;
        }
        return entrustProp;
    }
}
