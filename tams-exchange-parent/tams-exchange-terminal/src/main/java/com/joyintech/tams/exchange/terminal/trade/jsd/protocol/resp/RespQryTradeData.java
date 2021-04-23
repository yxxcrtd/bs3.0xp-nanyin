package com.joyintech.tams.exchange.terminal.trade.jsd.protocol.resp;

//成交查询返回消息体
public class RespQryTradeData {
    public char flag;
    public String source;
    public String reqId;
    public String date;
    public int tradeId; // 流水号
    public char market;
    public String gddm;  // 股东代码
    public char direction;
    public String bsName;  // 买卖名称
    public char tradeKind; // 成交类别
    public String tradeName;
    public char tradeType; //成交类型
    public String tradeTypeName; //成交类型名
    public String entrustCode;
    public String entrustName;

    public double tradedVolume;//成交数量
    public double tradedPrice;//成交价格
    public double tradedAmount;//成交金额
    public int entrustId;   // 委托编号
    public int tradeNum;    // 成交笔数
    public String  tradeTime;//成交时间
    public String positionStr;  // 定位串
    public String reportId; // 申报编号
    public String tradedId;  // 成交编号 交易所
    public String entrustType;  // 委托方式
    public double unfrozenAmount;  // 解冻资金金额
    public double unfrozenVolume;   // 解冻证券数量


}
