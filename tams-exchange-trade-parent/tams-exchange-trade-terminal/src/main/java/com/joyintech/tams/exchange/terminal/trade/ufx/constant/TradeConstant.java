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
    /**
     * entrust_type
     */
    public static String entrustTypeEntrust = "0";//委托
    public static String entrustTypeQry = "1";//查询
    public static String entrustTypeCancel = "2";//撤单
    public static String entrustTypeSup = "3";//补单
    public static String entrustTypeConfirm = "4";//确认
    public static String entrustTypeBig = "5";//大宗
    public static String entrustTypeStock = "6";//信用融资
    public static String entrustTypeBond = "7";//信用融券
    public static String entrustTypePC = "8";//信用平仓
    public static String entrustTypeXYJY = "9";//信用交易
    public static String entrustTypeCDBD = "A";//撤单补单
    public static String entrustTypeGD = "B";//改单
    public static String entrustTypeZHMM = "C";//组合买卖
    public static String entrustTypeZHZH = "D";//组合转换
    public static String entrustTypeZHCX = "E";//组合撤销
    public static String entrustTypeQQQZPC = "G";//期权强制平仓
    public static String entrustTypeQZPC = "H";//强制平仓
    public static String entrustTypeGPZYDZWYMC = "I";//股票质押大宗违约卖出


}
