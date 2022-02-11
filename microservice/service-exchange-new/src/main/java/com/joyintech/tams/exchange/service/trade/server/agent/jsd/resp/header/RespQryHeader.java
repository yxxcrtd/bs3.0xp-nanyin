package com.joyintech.tams.exchange.service.trade.server.agent.jsd.resp.header;

/**
 * 委托查询、成交查询 返回头
 *
 * @author lifangpeng
 * @date 2021/11/8
 */
public class RespQryHeader implements IRespHeader {
    /**
     * 记录个数
     */
    private int count;

    @Override
    public int getResponseCount() {
        return count;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
