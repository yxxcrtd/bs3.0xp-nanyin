package com.joyintech.tams.exchange.terminal.trade.jsd.protocol.resp;

//委托返回头
public class RespETFEntrustData implements IRespHeader {
    public String entrustId;       // 委托序号

    @Override
    public int getResponseCount() {
        return 0;
    }
}
