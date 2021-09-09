package com.joyintech.tams.exchange.terminal.trade.jsd.protocol.req;


import com.joyintech.tams.exchange.terminal.trade.jsd.protocol.annotation.ProtocolInfo;
import com.joyintech.tams.exchange.terminal.trade.jsd.protocol.resp.RespQryHeader;
import com.joyintech.tams.exchange.terminal.trade.jsd.protocol.resp.RespQryPositionData;
import com.joyintech.tams.exchange.terminal.trade.jsd.protocol.resp.RespQryPositionTotal;

//持仓查询
@ProtocolInfo(functionCode = "10", respHeaderClass = RespQryHeader.class, respDataClass = RespQryPositionData.class, respTotalClass = RespQryPositionTotal.class)
public class ReqQryPosition extends ReqHeader {
    public char market;//市场代码
    public String placeHolder;//  暂时不用
    public String accountNum;//客户号
    public char reqMarket;//查询市场代码
    public String reqStockCode;  //查询证券代码
    public String gddm;  // 股东代码
    public char indexMarketCode;    //索引市场代码
    public String indexStockCode;  //索引证券代码
    public String indexGddm;  // 索引股东帐号
    public Integer maxRetLen;       //最多返回数量
    public String off;// 操作功能 1 明细、2 汇总
    public String entrustType;  // 委托方式
    public char currencyCode;//货币代码
    public char isTotal;//是否返回合计值
}
