package com.joyintech.tams.exchange.terminal.trade.jsd.protocol.req;


import com.joyintech.tams.exchange.terminal.trade.jsd.protocol.annotation.ProtocolInfo;
import com.joyintech.tams.exchange.terminal.trade.jsd.protocol.resp.RespQryEntrustData;
import com.joyintech.tams.exchange.terminal.trade.jsd.protocol.resp.RespQryHeader;

//委托查询
@ProtocolInfo(functionCode = "14", respHeaderClass = RespQryHeader.class, respDataClass = RespQryEntrustData.class)
public class ReqQryEntrust extends ReqHeader {
    public char market;
    public String placeHolder;
    public String accountNum;
    public String startDate;    // 起始日期
    public String stopDate;     // 终止日期
    public Integer contractId;      // 合同号 N
    public String entrustCode; // 证券代码 N
    public Integer maxRetLen;       // 最多返回数量 N
    public String indexDate;    // 索引日期 N
    public String indexContractId;  // 索引合同号 N
    public String entrustType;  // 委托方式
    public char indexMarketCode;    // 索引市场代码 N
    public char orderType;          // 排序方式Y
    public char bsFlag;         // 查询买卖标志 N
    public Double orderPrice;   // 查询委托价格 N
}
