package com.joyintech.tams.exchange.terminal.trade.jsd.protocol.resp;

//大宗交易当日成交查返回消息体
public class RespQryProtocolBackTrade {
    public char flag;
    public String source;
    public String reqId;
    public String accountNum;//客户号
    public char market;  // 市场代码
    public String gddm;  // 股东代码
    public String bsFlagName;    //买卖标志名称
    public String stockCode;//证券代码
    public String stockName;//证券名称
    public int tradedVolume;//成交数量
    public String overrunTradedVolume;// 超限成交数量
    public double tradedPrice;//成交价格
    public double tradedAmount;//成交金额
    public String date;//成交日期
    public String tradeTime;//成交时间
    public String contractId;  // 合同编号
    public String dealNo;          // 成交编号
    public char businessClass;//业务类别
    public String entrustPropName;// 委托属性


}
