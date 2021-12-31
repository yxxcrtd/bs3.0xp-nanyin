package com.joyintech.tams.exchange.common.constant;

public enum EntrustBsProp {
    /**
     * 竞价交易
     */
    BUY("T01", "买入", EntrustProp.NORMAL),
    SELL("T02", "卖出", EntrustProp.NORMAL),
    APPLY("T04", "申购", EntrustProp.APPLY),
    REDEEM("T06", "赎回", EntrustProp.REPURCHASE),
    ISSUE("T47", "增发（配售）", EntrustProp.PS),
    RESALE("T03", "行权（回售）", EntrustProp.HS),
    ALLOCATE_SHARE("T46", "配股", EntrustProp.ALLOTMENT),
    BOND_STOCK("T49", "债转股", EntrustProp.CHANGE_TO_STOCK),
    SUBSCRIBE("T52", "认购", EntrustProp.APPLY),
    POSITIVE_REPO("03", "正回购", EntrustProp.REPURCHASE),
    REVERSED_REPO("04", "逆回购", EntrustProp.REPURCHASE),
    SEAL("T68", "质押入库", EntrustProp.ZYCRK),
    UNSEAL("T69", "质押出库", EntrustProp.ZYCRK),
    allocate_bond("T97", "配债", EntrustProp.PS),
    EXERCISE("T99", "行权"),
    open_long("TF01", "开多"),
    close_short("TF02", "平空"),
    CLOSE_TODAY_SHORT("TF03", "平今空"),
    OPENS_HORT("TF04", "开空"),
    CLOSE_LONG("TF05", "平多"),
    CLOSE_TODAY_LONG("TF06", "平今多"),
    VOTE("T107", "网络投票"),
    BONUS("T110", "分红设置"),
    FINANCE_BUY("T104", "融资买入"),
    SECURITIES_LOAN_SELL("T105", "融券卖出"),

    /**
     * 大宗交易申报类型
     */
    INTENTION("a", "意向委托", EntrustProp.INTENTION),
    SET_PRICE("b", "定价委托", EntrustProp.SET_PRICE),
    CONFIRM("c", "确认委托", EntrustProp.CONFIRM),
    LIMIT_PRICE("d", "限价委托", EntrustProp.LIMIT_PRICE),
    MUTUAL_TRADE("e", "互报成交确认委托", EntrustProp.MUTUAL_TRADE),
    LARGE_AFTER_CLOSING("ABT", "盘后大宗委托", EntrustProp.LARGE_AFTER_CLOSING),
    LARGE_AFTER_CLOSING_PRICE("AFC", "盘后大宗收盘价委托", EntrustProp.LARGE_AFTER_CLOSING_PRICE),
    LARGE_AFTER_CLOSING_WEIGHT_PRICE("AFW", "盘后大宗成交量加权平均价委托", EntrustProp.LARGE_AFTER_CLOSING_WEIGHT_PRICE),

    /**
     * 上海固收平台申报类型
     */
    SH_FIXEDINCOME_BAOJIA_CONFIRM("FI1", "确定报价", EntrustProp.SH_FIXEDINCOME_BAOJIA_CONFIRM),
    SH_FIXEDINCOME_TRADE_CLICK("FI3", "点击成交", EntrustProp.SH_FIXEDINCOME_TRADE_CLICK),
    SH_FIXEDINCOME_BAOJIA_OPPOSITE("FI9", "指定对手方报价", EntrustProp.SH_FIXEDINCOME_BAOJIA_OPPOSITE),
    SH_FIXEDINCOME_KZHCJSB("FI100JY", "可转换成交申报", EntrustProp.SH_FIXEDINCOME_KZHCJSB),
    SH_FIXEDINCOME_ZYJCJSB("FI101JY", "最优价成交申报", EntrustProp.SH_FIXEDINCOME_ZYJCJSB),
    ;

    String code;
    String mean;
    EntrustProp entrustProp;

    EntrustBsProp(String code, String mean) {
        this.code = code;
        this.mean = mean;
    }

    EntrustBsProp(String code, String mean, EntrustProp entrustProp) {
        this.code = code;
        this.mean = mean;
        this.entrustProp = entrustProp;
    }

    public static EntrustProp getEntrustProp(String code) {
        for (EntrustBsProp entrustPropBsFlag : values()) {
            if (entrustPropBsFlag.code.equals(code)) {
                return entrustPropBsFlag.entrustProp;
            }
        }
        return null;
    }
}
