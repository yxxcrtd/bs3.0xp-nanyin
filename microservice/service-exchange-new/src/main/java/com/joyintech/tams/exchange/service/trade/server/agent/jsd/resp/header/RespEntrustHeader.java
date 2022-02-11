package com.joyintech.tams.exchange.service.trade.server.agent.jsd.resp.header;

/**
 * 委托下单返回头-3
 *
 * @author lifangpeng
 * @date 2021/11/8
 */
public class RespEntrustHeader implements IRespHeader {
    /**
     * 委托序号
     */
    private Integer entrustId;
    /**
     * 委托批号
     */
    private Integer entrustBatch;
    /**
     * 成功数量
     */
    private Integer successVolume;
    /**
     * 冻结数量
     */
    private double frozenVolume;
    /**
     * 冻结金额
     */
    private double frozenAmount;

    @Override
    public int getResponseCount() {
        return 0;
    }

    public Integer getEntrustId() {
        return entrustId;
    }

    public void setEntrustId(Integer entrustId) {
        this.entrustId = entrustId;
    }

    public Integer getEntrustBatch() {
        return entrustBatch;
    }

    public void setEntrustBatch(Integer entrustBatch) {
        this.entrustBatch = entrustBatch;
    }

    public Integer getSuccessVolume() {
        return successVolume;
    }

    public void setSuccessVolume(Integer successVolume) {
        this.successVolume = successVolume;
    }

    public double getFrozenVolume() {
        return frozenVolume;
    }

    public void setFrozenVolume(double frozenVolume) {
        this.frozenVolume = frozenVolume;
    }

    public double getFrozenAmount() {
        return frozenAmount;
    }

    public void setFrozenAmount(double frozenAmount) {
        this.frozenAmount = frozenAmount;
    }
}
