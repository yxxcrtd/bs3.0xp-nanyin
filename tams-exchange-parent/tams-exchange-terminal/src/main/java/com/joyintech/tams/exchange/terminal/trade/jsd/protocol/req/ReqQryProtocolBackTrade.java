package com.joyintech.tams.exchange.terminal.trade.jsd.protocol.req;


import com.joyintech.tams.exchange.terminal.trade.jsd.protocol.annotation.ProtocolInfo;
import com.joyintech.tams.exchange.terminal.trade.jsd.protocol.resp.RespQryHeader;
import com.joyintech.tams.exchange.terminal.trade.jsd.protocol.resp.RespQryProtocolBackTrade;

//大宗交易当日成交查
@ProtocolInfo(functionCode = "344", respHeaderClass = RespQryHeader.class, respDataClass = RespQryProtocolBackTrade.class)
public class ReqQryProtocolBackTrade extends ReqHeader {
    public char market;
    public String placeHolder;
    public String accountNum;//客户号
    public Integer maxNum;//最多返回数量
    public String indexEntrustCode;  //索引证券代码
    public String indexGddm;  // 索引股东帐号
    public String indexDealNo;        //索引成交编号
    public String entrustType;  // 委托方式
    public String keepDomain;//保留域
    public String tradeStatusSet;//成交状态集合
    public String qryEntrustCode;  //查询证券代码

}
