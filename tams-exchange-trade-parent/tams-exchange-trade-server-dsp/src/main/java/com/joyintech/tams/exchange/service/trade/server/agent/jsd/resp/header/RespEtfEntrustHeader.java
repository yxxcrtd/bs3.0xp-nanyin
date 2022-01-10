package com.joyintech.tams.exchange.service.trade.server.agent.jsd.resp.header;

/**
 * ETF基金申购/赎回返回消息体-202
 *
 * @author lifangpeng
 * @date 2021/11/8
 */
public class RespEtfEntrustHeader implements IRespHeader {
    /**
     * 委托序号
     */
    public String entrustId;

    @Override
    public int getResponseCount() {
        return 0;
    }
}
