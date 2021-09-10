package com.joyintech.tams.exchange.terminal.trade.jsd.protocol.req;


import com.joyintech.tams.exchange.terminal.trade.jsd.protocol.annotation.ProtocolInfo;
import com.joyintech.tams.exchange.terminal.trade.jsd.protocol.resp.RespQryAfterIntegratedQuotes;
import com.joyintech.tams.exchange.terminal.trade.jsd.protocol.resp.RespQryHeader;

/*** 
 * @Description: 盘后大宗交易行情查询
 * @Param:
 * @return:
 * @Author: MAXX
 * @Date: 2020/7/1
 */
@ProtocolInfo(functionCode = "346", respHeaderClass = RespQryHeader.class, respDataClass = RespQryAfterIntegratedQuotes.class)
public class ReqQryAfterIntegratedQuotes extends ReqHeader {
    public char market;     // 市场代码
    public String placeholder;// 暂时不用
    public String accountNum;//客户号
    public String stockCode;//证券代码

}
