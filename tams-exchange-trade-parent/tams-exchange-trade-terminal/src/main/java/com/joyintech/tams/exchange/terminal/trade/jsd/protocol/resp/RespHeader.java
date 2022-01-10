package com.joyintech.tams.exchange.terminal.trade.jsd.protocol.resp;

import java.util.List;

public class RespHeader {
    public char flag;
    public String source;
    public String reqId;
    public boolean isSuccess;
    public String errorMsg;
    public String errorCode;

    public static RespHeader initHeader(List<String> strings){
        if(strings.size() < 4) return null;
        RespHeader respHeader = new RespHeader();
        respHeader.flag = strings.get(0).charAt(0);//请求应答标志
        respHeader.source = strings.get(1);//请求源地址
        respHeader.reqId = strings.get(2);//请求编号
        respHeader.isSuccess = strings.get(3).charAt(0) != 'N';//成功失败标志
        if(!respHeader.isSuccess){
            if(strings.size() < 6) return null;
            respHeader.errorMsg = strings.get(4);
            respHeader.errorCode = strings.get(5);
        }
        return respHeader;
    }
}
