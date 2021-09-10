package com.joyintech.tams.exchange.terminal.trade.jsd.protocol.req;


import com.joyintech.tams.exchange.terminal.trade.jsd.protocol.annotation.ProtocolInfo;
import com.joyintech.tams.exchange.terminal.trade.jsd.protocol.resp.RespLoginData;
import com.joyintech.tams.exchange.terminal.trade.jsd.protocol.resp.RespLoginHeader;

//登录
@ProtocolInfo(functionCode = "61", respHeaderClass = RespLoginHeader.class, respDataClass = RespLoginData.class)
public class ReqLogin extends ReqHeader {

    public String accountType;
    public String placeholder;
    public String account;
    public String password;
    public String entrustType;

}
