package com.joyintech.tams.exchange.service.trade.server.agent.jsd.resp.data;

/**
 * 综合行情查询返回消息体-345
 *
 * @author lifangpeng
 * @date 2021/11/8
 */
public class RespQryIntegratedQuotes {
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
     * 记录号
     */
    public int recordNum;
    /**
     * 市场代码
     */
    public char market;
    /**
     * 证券代码
     */
    public String stockCode;
    /**
     * 申报数量
     */
    public double declarationQuantity;
    /**
     * 申报价格
     */
    public double declarationPrice;
    /**
     * 业务类别
     */
    public String businessClass;
    /**
     * 指令类别
     */
    public String instruction;
    /**
     * 申报合同序号
     */
    public String declarationOrderId;
    /**
     * 交易单元
     */
    public String tradeUnit;
    /**
     * 申报日期
     */
    public String declarationDate;
    /**
     * 流水号
     */
    public String tradeId;
    /**
     * 撤单编号
     */
    public String cancellationsNo;
    /**
     * 联系人
     */
    public String relationName;
    /**
     * 联系方式
     */
    public String relationTel;
    /**
     * 结算机构代码
     */
    public String clearInstitutionCode;
    /**
     * 申报时间
     */
    public String declarationTime;
    /**
     * 约定号
     */
    public String contractNo;
    /**
     * 参考收益率
     */
    public double referenceRate;
    /**
     * 记录状态
     */
    public char recordStatus;
    /**
     * 备注
     */
    public String remark;
    /**
     * 期限类型
     */
    public char termType;
    /**
     * 购回期限
     */
    public int backTerm;
    /**
     * 变动时间戳
     */
    public String timestamp;
    /**
     * 产品集编号
     */
    public String productSetNum;
    /**
     * 产品集序号
     */
    public String productSetNo;
    /**
     * 申报数量2
     */
    public double declarationQuantity2;
    /**
     * 申报价格2
     */
    public double declarationPrice2;
    /**
     * 参考收益率2
     */
    public double referenceRate2;
    /**
     * 备用标志
     */
    public String standbyTag;


}
