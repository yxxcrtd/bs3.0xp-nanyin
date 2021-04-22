package com.joyintech.tams.exchange.terminal.trade.jsd.protocol.req;

import com.joyintech.tams.exchange.terminal.trade.jsd.protocol.annotation.ProtocolInfo;
import com.joyintech.tams.exchange.terminal.trade.jsd.protocol.resp.RespQryHeader;
import com.joyintech.tams.exchange.terminal.trade.jsd.protocol.resp.RespQryStandardBondData;

//成交查询
@ProtocolInfo(functionCode = "395", respHeaderClass = RespQryHeader.class, respDataClass = RespQryStandardBondData.class)
public class ReqQryStandardBond extends ReqHeader {
    public char market;     // 市场代码
    public String ubDefined;  // 暂不使用，填写空值
    public String accountNum;//客户号
    public String entrustType;  // 委托方式
}
