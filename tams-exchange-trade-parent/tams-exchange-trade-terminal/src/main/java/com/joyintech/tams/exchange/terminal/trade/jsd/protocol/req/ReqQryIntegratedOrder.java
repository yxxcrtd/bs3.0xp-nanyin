package com.joyintech.tams.exchange.terminal.trade.jsd.protocol.req;


import com.joyintech.tams.exchange.terminal.trade.jsd.protocol.annotation.ProtocolInfo;
import com.joyintech.tams.exchange.terminal.trade.jsd.protocol.resp.RespQryHeader;
import com.joyintech.tams.exchange.terminal.trade.jsd.protocol.resp.RespQryIntegratedOrder;

//大宗交易委托主动拉取
@ProtocolInfo(functionCode = "343", respHeaderClass = RespQryHeader.class, respDataClass = RespQryIntegratedOrder.class)
public class ReqQryIntegratedOrder extends ReqHeader {
    public char market;
    public String placeholder;// 暂时不用
    public String accountNum;//客户号
    public Integer contractId;      // 合同号 N
    public String entrustCode; // 证券代码 N
    public char orderType;          // 排序方式Y
    public char bsFlag;         // 查询买卖标志 N
    public int revocableDelegateMark;//可撤委托标志
    public Integer maxRetLen;       // 最多返回数量 N
    public Integer indexContractId;  // 索引合同号 N
    public char indexMarketCode;    // 索引市场代码 N
    public String entrustType;  // 委托方式

}
