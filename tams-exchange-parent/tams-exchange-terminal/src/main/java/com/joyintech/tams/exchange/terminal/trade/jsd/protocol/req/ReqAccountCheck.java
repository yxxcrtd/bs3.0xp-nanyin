package com.joyintech.tams.exchange.terminal.trade.jsd.protocol.req;

import com.joyintech.tams.exchange.terminal.trade.jsd.protocol.annotation.ProtocolInfo;
import com.joyintech.tams.exchange.terminal.trade.jsd.protocol.resp.RespAccountCheckHeader;

/**
 * create By MAXX on 2020/6/23
 */
@ProtocolInfo(functionCode = "1", respHeaderClass = RespAccountCheckHeader.class)
public class ReqAccountCheck extends ReqHeader {
    public char accountType;
    public String placeHolder;
    public String account;
    public String entrustType;  // 委托方式
}
