package com.joyintech.tams.exchange.common.constant.joyin;

import com.joyintech.tams.exchange.common.constant.EntrustProp;

/**
 * @Author: think
 * @Description: 投管申报类型
 * @Date: Created in 16:17 2020/10/30
 */
public enum DeclarationType {
    INTENTION("PRM06", "意向申报"),
    SET_PRICE("PRM07", "定价申报"),
    MUTUAL_TRADE("PRM08", "点击成交申报"),
    CONFIRM("PRM09", "成交申报"),
    LARGE_AFTER_CLOSING_PRICE("PRM10", "收盘价固定价格申报"),
    LARGE_AFTER_CLOSING_WEIGHT_PRICE("PRM11", "日均价固定价格申报"),
    SH_FIXEDINCOME_ZYJCJSB("PRM12", "最优价成交申报"),
    SH_FIXEDINCOME_KZHCJSB("PRM13", "可转换成交申报"),
    SH_FIXEDINCOME_BAOJIA_OPPOSITE("PRM14", "指定对手方成交申报");

    public final String value;
    public final String name;

    DeclarationType(String value, String name) {
        this.name = name;
        this.value = value;
    }

    public static String convertEntrustPropToDeclarationType(String entrustProp) {
        if (EntrustProp.INTENTION.value.equals(entrustProp)) {
            return DeclarationType.INTENTION.value;
        } else if (EntrustProp.SET_PRICE.value.equals(entrustProp)) {
            return DeclarationType.SET_PRICE.value;
        } else if (EntrustProp.MUTUAL_TRADE.value.equals(entrustProp)) {
            return DeclarationType.MUTUAL_TRADE.value;
        } else if (EntrustProp.CONFIRM.value.equals(entrustProp)) {
            return DeclarationType.CONFIRM.value;
        } else if (EntrustProp.LARGE_AFTER_CLOSING_PRICE.value.equals(entrustProp)) {
            return DeclarationType.LARGE_AFTER_CLOSING_PRICE.value;
        } else if (EntrustProp.LARGE_AFTER_CLOSING_WEIGHT_PRICE.value.equals(entrustProp)) {
            return DeclarationType.LARGE_AFTER_CLOSING_WEIGHT_PRICE.value;
        } else if (EntrustProp.SH_FIXEDINCOME_ZYJCJSB.value.equals(entrustProp)) {
            return DeclarationType.SH_FIXEDINCOME_ZYJCJSB.value;
        } else if (EntrustProp.SH_FIXEDINCOME_KZHCJSB.value.equals(entrustProp)) {
            return DeclarationType.SH_FIXEDINCOME_KZHCJSB.value;
        } else if (EntrustProp.SH_FIXEDINCOME_BAOJIA_OPPOSITE.value.equals(entrustProp)) {
            return DeclarationType.SH_FIXEDINCOME_BAOJIA_OPPOSITE.value;
        }
        return null;
    }
}
