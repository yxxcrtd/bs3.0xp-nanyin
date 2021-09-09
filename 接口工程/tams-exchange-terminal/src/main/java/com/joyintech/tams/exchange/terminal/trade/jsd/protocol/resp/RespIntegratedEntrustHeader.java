package com.joyintech.tams.exchange.terminal.trade.jsd.protocol.resp;

//综合业务委托确认 固收 债券质押式协议回购返回头
public class RespIntegratedEntrustHeader implements IRespHeader {
    public int entrustId;       // 委托序号
    public int entrustBatch;    // 委托批号


    @Override
    public int getResponseCount() {
        return 0;
    }
}
