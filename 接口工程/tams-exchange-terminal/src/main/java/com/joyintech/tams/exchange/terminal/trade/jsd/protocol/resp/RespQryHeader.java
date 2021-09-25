package com.joyintech.tams.exchange.terminal.trade.jsd.protocol.resp;

//委托查询、成交查询 返回头
public class RespQryHeader implements IRespHeader {
    public int count;

    @Override
    public int getResponseCount() {
        return count;
    }
}
