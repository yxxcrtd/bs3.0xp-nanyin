package com.joyintech.tams.exchange.service.trade.server.agent.jsd.resp.header;

/**
 * 最大委托数查询返回头-17
 *
 * @author lifangpeng
 * @date 2021/11/8
 */
public class RespQryEnableHeader implements IRespHeader {
    /**
     * 委托数量
     */
    public double volume;
    /**
     * 交易单位名称
     */
    public String tradeUnitName;
    /**
     * 可用资金
     */
    public double expendableFund;
    /**
     * 应还数量
     */
    public int repayNum;
    /**
     * 理论最大可委托数
     */
    public double maxNum;
    /**
     * 申报单位
     */
    public int applyUnit;
    /**
     * 存放单位
     */
    public int storageUnit;

    @Override
    public int getResponseCount() {
        return 0;
    }
}
