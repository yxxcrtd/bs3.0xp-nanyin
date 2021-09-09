package com.joyintech.tams.exchange.terminal.trade.jsd.protocol;

public class MsgType {
    public final static int REQ_LOGIN = 1;//登录
    public final static int REQ_ENTRUST = 2;//证券委托查询
    public final static int REQ_TRADE = 3;//证券成交查询
    public final static int ENTRUST = 4;//普通委托
    public final static int INTEGRATED_ENTRUST = 5;//综合业务委托确认
    public final static int CANCEL_ENTRUST = 6;//委托撤单
    public final static int CANCEL_INTEGRATED_ENTRUST = 7;//综合业务委托撤单
    public final static int REQ_INTEGRATED_QUOTES = 8;//大宗交易行情查询
    public final static int REQ_ENABLE = 9;//最大交易数量
    public final static int REQ_HISTORY_TRADE = 10;//历史证券成交查询
    public final static int REQ_CUSTOMER_FINANCE = 11;//客户资金快速查询
    public final static int QRY_ENTRUST = 12;//委托主动获取
    public final static int QRY_TRADE = 13;//成交主动获取
    public final static int QRY_INTEGRATED_ENTRUST = 14; //大宗委托主动拉取
    public final static int QRY_INTEGRATED_TRADE = 15;//大宗成交主动获取
    public final static int QRY_INTEGRATED_BULK_QUOTE = 16;//大宗交易行情查询
    public final static int QRY_INTEGRATED_BULK_CLOSE_QUOTE = 17;//盘后大宗交易行情查询
    public final static int QRY_STANDARD_BONDS = 18;//查询标准券信息
    public final static int QRY_ACCOUNT_POSITION = 19;//持仓查询
    public final static int REQ_REISSUE_ENTRUST = 20;//补传委托
    public final static int REQ_REISSUE_TRADE = 21;//补传成交
}
