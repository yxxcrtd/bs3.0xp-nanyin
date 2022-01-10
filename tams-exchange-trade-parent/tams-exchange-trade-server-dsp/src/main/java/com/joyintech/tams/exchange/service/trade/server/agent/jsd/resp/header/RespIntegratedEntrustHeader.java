package com.joyintech.tams.exchange.service.trade.server.agent.jsd.resp.header;

/**
 * 大宗交易委托返回头：综合业务委托确认 固收 债券质押式协议回购
 *
 * @author lifangpeng
 * @date 2021/11/8
 */
public class RespIntegratedEntrustHeader implements IRespHeader {
    /**
     * 委托序号
     */
    public int entrustId;
    /**
     * 委托批号
     */
    public int entrustBatch;


    @Override
    public int getResponseCount() {
        return 0;
    }
}
