package com.joyintech.tams.exchange.terminal.trade.jsd.protocol.req;


import com.joyintech.tams.exchange.terminal.trade.jsd.protocol.annotation.ProtocolInfo;
import com.joyintech.tams.exchange.terminal.trade.jsd.protocol.resp.RespCancelEntrustHeader;

/**
 * create By MAXX on 2020/6/17
 * 撤单
 */
@ProtocolInfo(functionCode = "4", respHeaderClass = RespCancelEntrustHeader.class)
public class ReqCancelEntrust extends ReqHeader {

    public char market;     // 市场代码
    public String placeholder;// 暂时不用
    public String accountNum;//客户号

    public Integer contractId;  // 合同号
    public String entrustType;  // 委托方式
}
