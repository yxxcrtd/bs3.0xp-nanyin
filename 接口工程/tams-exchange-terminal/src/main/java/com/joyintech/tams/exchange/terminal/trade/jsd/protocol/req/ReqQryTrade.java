package com.joyintech.tams.exchange.terminal.trade.jsd.protocol.req;


import com.joyintech.tams.exchange.terminal.trade.jsd.protocol.annotation.ProtocolInfo;
import com.joyintech.tams.exchange.terminal.trade.jsd.protocol.resp.RespQryHeader;
import com.joyintech.tams.exchange.terminal.trade.jsd.protocol.resp.RespQryTradeData;

//成交查询
@ProtocolInfo(functionCode = "122", respHeaderClass = RespQryHeader.class, respDataClass = RespQryTradeData.class)
public class ReqQryTrade extends ReqHeader {
    public char market;
    public String placeHolder;
    public String accountNum;
    public String gddm; // 股东代码N
    public String entrustCode;    // 股票代码
    public String searchDirection;  // 查询方向 1 后翻，0 前翻
    public Integer maxRetNum; // 最大返回记录 N
    public String queryMode;    // 查询模式 Y 0明细 1汇总
    public String positionStr;  //定位串 N
    public String entrustType;  // 委托方式 N
    public char queryTYpe;    // 查询模式 空不输出撤单记录 1输出撤单记录 N
    public char rzrqFlag;    // 融资融券标志 N
    public String ywlb;         // 业务类别 N

}
