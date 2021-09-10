package com.joyintech.tams.exchange.terminal.trade.jsd.protocol.resp;

//最大委托数查询返回头
public class RespQryEnable implements IRespHeader {
    public double volume;       // 委托数量
    public String tradeUnitName;//交易单位名称
    public double expendableFund;//可用资金
    public int repayNum;//应还数量
    public double maxNum;//理论最大可委托数
    public int applyUnit;//申报单位
    public int storageUnit;//存放单位

    @Override
    public int getResponseCount() {
        return 0;
    }
}
