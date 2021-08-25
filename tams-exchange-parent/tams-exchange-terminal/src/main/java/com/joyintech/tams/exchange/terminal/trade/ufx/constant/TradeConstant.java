package com.joyintech.tams.exchange.terminal.trade.ufx.constant;

/**
 * @Author: think
 * @Description:
 * @Date: Created in 16:11 2021/8/2
 */
public class TradeConstant {
    /**
     * 成交状态 real_status
     */
    public static int realStatusTrade = 0;//成交
    public static int realStatusInvalid = 2;//废单
    public static int realStatusConfirm = 4;//确认

    /**
     * 成交类型 real_type
     */
    public static String realTypeTrade = "0";//买卖
    public static String realTypeQry = "1";//查询
    public static String realTypeCancel = "2";//撤单
    public static String realTypeStock = "6";//融资
    public static String realTypeBond = "7";//融券
    public static String realTypeOffset = "8";//平仓
    public static String realTypeCredit = "9";//信用
    public static String realTypeOption = "G";//期权强制平仓

}
