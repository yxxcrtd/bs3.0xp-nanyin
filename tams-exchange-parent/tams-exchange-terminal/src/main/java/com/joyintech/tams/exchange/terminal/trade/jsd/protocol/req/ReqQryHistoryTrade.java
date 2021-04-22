package com.joyintech.tams.exchange.terminal.trade.jsd.protocol.req;


import com.joyintech.tams.exchange.terminal.trade.jsd.protocol.annotation.ProtocolInfo;
import com.joyintech.tams.exchange.terminal.trade.jsd.protocol.resp.RespQryHeader;
import com.joyintech.tams.exchange.terminal.trade.jsd.protocol.resp.RespQryHistoryTrade;

/*** 
 * @Description: 成交历史查询
 * @Param:
 * @return:
 * @Author: MAXX
 * @Date: 2020/7/2
 */
@ProtocolInfo(functionCode = "62", respHeaderClass = RespQryHeader.class, respDataClass = RespQryHistoryTrade.class)
public class ReqQryHistoryTrade extends ReqHeader {
    public char currencyCode;//货币代码
    public String placeholder;// 暂时不用
    public String accountNum;//客户号
    public String startDate;//起始日期
    public String endDate;//结束日期
    public String serialNumStr;//索引流水号
    public Integer maxNum;//最多返回数量
    public String entrustType;  // 委托方式
    public char market;     // 市场代码
    public String stockCode;//查询证券代码
    public String gddm;  //查询股东代码


}
