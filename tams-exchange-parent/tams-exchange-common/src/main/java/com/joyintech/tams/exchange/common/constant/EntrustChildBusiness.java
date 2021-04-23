package com.joyintech.tams.exchange.common.constant;

/*** 
 * @Description: 委托子业务
 * @Param:
 * @return:
 * @Author: MAXX
 * @Date: 2020/6/30
 */
public enum EntrustChildBusiness {
    NORMAL("正常委托", "000"),
    SDSFZYJGWT("深对手方最优价格委托（市价 1）", "002"),
    SBFZYJGWT("深本方最优价格委托（市价 2）", "003"),
    SJSCJSYCXWT("深即时成交剩余撤销委托（市价 3）", "004"),
    SHZYWDBCXWT("深沪最优五档并撤销委托(市价 4)", "005"),
    SQECJHCXWT("深全额成交或撤销委托（市价 5）", "006"),
    HZYWDBZXJWT("沪最优五档并转限价委托（市价 6）", "007"),

    SET_PRICE("定价委托", "z10"),
    INTENTION("意向申报", "z20"),
    MUTUAL_TRADE("配对成交", "z30"),
    CONFIRM("成交确认", "z40"),
    SPECIAL_TRANSFER("特殊股份转托", "z50"),
    LARGE_AFTER_CLOSING_PRICE("收盘价委托", "z70"),
    LARGE_AFTER_CLOSING_WEIGHT_PRICE("加权平均价委托", "z80"),
    STOCKS_TURNED_CONFIRM("股转大宗成交申报", "z90"),

    SH_REPURCHASE_TRADE("质押式协议回购成交申报", "X31"),
    SH_REPURCHASE_RENEWAL("质押式协议回购到期续做申报", "X32"),
    SH_REPURCHASE_TERMINATE("质押式协议回购提前终止申报", "X33"),
    SH_REPURCHASE_TRADE_CONFIRM("质押式协议回购成交申报确认", "X40"),
    SH_REPURCHASE_RENEWAL_CONFIRM("质押式协议回购到期续做申报确认", "X41"),
    SH_REPURCHASE_TERMINATE_CONFIRM("质押式协议回购提前终止申报确认", "X42"),
    SH_FIXEDINCOME_BAOJIA_OPPOSITE("指定对手方交易", "g10");

    public final String value;
    public final String name;

    EntrustChildBusiness(String name, String value) {
        this.name = name;
        this.value = value;
    }
}
