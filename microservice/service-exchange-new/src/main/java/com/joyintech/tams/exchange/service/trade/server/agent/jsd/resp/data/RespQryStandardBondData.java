package com.joyintech.tams.exchange.service.trade.server.agent.jsd.resp.data;

/**
 * 标准券(回购融资汇总)查询返回消息体-395
 *
 * @author lifangpeng
 * @date 2021/11/8
 */
public class RespQryStandardBondData {
    /**
     * 请求应答标志
     */
    public char flag;
    /**
     * 请求源地址
     */
    public String source;
    /**
     * 请求编号
     */
    public String reqId;
    /**
     * 客户号
     */
    public String accountNum;
    /**
     * 市场代码
     */
    public char market;
    /**
     * 证券账户
     */
    public String gddm;
    /**
     * 回购金额
     */
    public double backBalance;
    /**
     * 净资产
     */
    public double netAssets;
    /**
     * 回购放大倍数（杠杆率）
     */
    public double leverageRatio;
    /**
     * 客户放大倍数上线
     */
    public int multiple;
    /**
     * 当前标准券数量
     */
    public int standardBondVolume;
    /**
     * 当前标准券使用率
     */
    public double useRatio;
    /**
     * 标准券使用上限
     */
    public double standardBondUpperLimit;

}
