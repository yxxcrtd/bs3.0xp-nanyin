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
            cbptransType = CbptransType.YXMR.value;
        } else if (EntrustBs.SELL.value.equals(entrustBs) && EntrustProp.INTENTION.value.equals(entrustProp)) {
            cbptransType = CbptransType.YXMC.value;
        } else if (EntrustBs.BUY.value.equals(entrustBs) && EntrustProp.SET_PRICE.value.equals(entrustProp)) {
            cbptransType = CbptransType.DJMR.value;
        } else if (EntrustBs.SELL.value.equals(entrustBs) && EntrustProp.SET_PRICE.value.equals(entrustProp)) {
            cbptransType = CbptransType.DJMC.value;
        }
        return cbptransType;
    }

    public static String getEntrustBsByCbptransType(String cbptransType) {
        String entrustBs = "";
        if (CbptransType.YXMR.value.equals(cbptransType) || CbptransType.DJMR.value.equals(cbptransType)) {
            entrustBs = EntrustBs.BUY.value;
        } else if (CbptransType.YXMC.value.equals(cbptransType) || CbptransType.DJMC.value.equals(cbptransType)) {
            entrustBs = EntrustBs.SELL.value;
        }
        return entrustBs;
    }

    public static String getEntrustPropByCbptransType(String cbptransType) {
        String entrustProp = "";
        if (CbptransType.YXMR.value.equals(cbptransType) || CbptransType.YXMC.value.equals(cbptransType)) {
            entrustProp = EntrustProp.INTENTION.value;
        } else if (CbptransType.DJMR.value.equals(cbptransType) || CbptransType.DJMC.value.equals(cbptransType)) {
            entrustProp = EntrustProp.SET_PRICE.value;
        }
        return entrustProp;
    }
}
