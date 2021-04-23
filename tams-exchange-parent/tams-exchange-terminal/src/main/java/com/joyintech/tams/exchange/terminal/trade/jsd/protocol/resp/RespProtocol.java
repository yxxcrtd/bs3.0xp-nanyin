package com.joyintech.tams.exchange.terminal.trade.jsd.protocol.resp;


import java.util.List;

public class RespProtocol {
    public Integer reqId;

    public String errorCode;
    public String errorMsg;
    public IRespHeader header;
    public List<Object> dataMessages;
    public Object totalMessage;

    public RespProtocol() {

    }

    public RespProtocol(String errorCode, String errorMsg) {
        this.errorMsg = errorMsg;
        this.errorCode = errorCode;
    }
}
