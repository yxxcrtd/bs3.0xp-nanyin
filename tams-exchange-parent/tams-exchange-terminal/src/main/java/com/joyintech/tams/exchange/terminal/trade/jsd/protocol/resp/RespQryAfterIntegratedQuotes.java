package com.joyintech.tams.exchange.terminal.trade.jsd.protocol.resp;

//盘后大宗交易行情查询返回消息体
public class RespQryAfterIntegratedQuotes {
    public char flag;//请求应答标志
    public String source;//请求源地址
    public String reqId;//请求编号
    public char market;  // 市场代码
    public String stockCode;//证券代码
    public String stockName;//证券简称
    public String instruction;//指令类别
    public int recordNum;//记录号
    public String orderType1;//报价类型1
    public double price1;// 价格1 收盘价格
    public double dealVolume1;      //成交数量1
    public double dealMoney1;//  成交金额1
    public int dealCount1;//  成交笔数1
    public double buyRemainQuantity1;//买盘剩余数量1
    public double sellRemainQuantity1;//卖盘剩余数量1
    public char quotationType2;//报价类型 2
    public double price2;//  价格2加权平均价格
    public double dealVolume2;      //成交数量2
    public double dealMoney2;//  成交金额2
    public int dealCount2;//  成交笔数2
    public double buyRemainQuantity2;//买盘剩余数量2
    public double sellRemainQuantity2;//卖盘剩余数量2
    public String updateTime;//更新时间
    public String remark;// 备用文本

}
