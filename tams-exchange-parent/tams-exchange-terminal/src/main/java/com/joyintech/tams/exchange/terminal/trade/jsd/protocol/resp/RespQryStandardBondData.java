package com.joyintech.tams.exchange.terminal.trade.jsd.protocol.resp;

public class RespQryStandardBondData {
    public char flag;
    public String source;
    public String reqId;
    public String accountNum;//客户号
    public char market;  // 市场代码
    public String gddm;  // 证券账户
    public double backBalance;    //回购金额
    public double netAssets;//净资产
    public double leverageRatio;//回购放大倍数（杠杆率）
    public int multiple;//客户放大倍数上线
    public int standardBondVolume;// 当前标准券数量
    public double useRatio;//当前标准券使用率
    public double standardBondUpperLimit;//标准券使用上限
}
