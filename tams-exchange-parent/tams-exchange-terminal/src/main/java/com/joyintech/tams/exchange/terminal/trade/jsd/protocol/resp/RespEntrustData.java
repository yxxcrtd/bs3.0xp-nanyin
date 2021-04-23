package com.joyintech.tams.exchange.terminal.trade.jsd.protocol.resp;

//委托返回头
public class RespEntrustData implements IRespHeader {
    public int entrustId;       // 委托序号
    public int entrustBatch;    // 委托批号
    public double successVolume;    // 成功数量
    public double frozenVolume;  // 冻结数量
    public double frozenAmount;  // 冻结金额

    @Override
    public int getResponseCount() {
        return 0;
    }
}
