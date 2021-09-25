package com.joyintech.tams.exchange.terminal.trade.jsd.protocol.req;


import com.joyintech.tams.exchange.terminal.trade.jsd.protocol.annotation.ProtocolInfo;
import com.joyintech.tams.exchange.terminal.trade.jsd.protocol.resp.RespQryHeader;
import com.joyintech.tams.exchange.terminal.trade.jsd.protocol.resp.RespQryIntegratedQuotes;

/*** 
 * @Description: 综合行情查询
 * @Param:
 * @return:
 * @Author: MAXX
 * @Date: 2020/7/1
 */
@ProtocolInfo(functionCode = "345", respHeaderClass = RespQryHeader.class, respDataClass = RespQryIntegratedQuotes.class)
public class ReqQryIntegratedQuotes extends ReqHeader {
    public char market;     // 市场代码
    public String placeholder;// 暂时不用
    public String accountNum;//客户号
    public String stockCode;//证券代码
    public String instructionSet;//指令类别集合
    public String contractNo;//约定号
    public char recordStatus;//记录状态
    public String businessClass;//业务类别

}
