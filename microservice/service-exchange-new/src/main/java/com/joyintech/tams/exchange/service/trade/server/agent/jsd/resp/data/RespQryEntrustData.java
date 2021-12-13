package com.joyintech.tams.exchange.service.trade.server.agent.jsd.resp.data;

/**
 * 委托查询返回消息体-14
 *
 * @author lifangpeng
 * @date 2021/11/8
 */
public class RespQryEntrustData {
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
     * 股东账号
     */
    public String gdAccount;
    /**
     * 买卖类别名称
     */
    public String bsName;
    /**
     * 证券代码
     */
    public String entrustCode;
    /**
     * 证券名称
     */
    public String entrustName;
    /**
     * 委托数量
     */
    public double entrustVolume;
    /**
     * 委托价格
     */
    public double entrustPrice;
    /**
     * 合同号
     */
    public int contractId;
    /**
     * 成交数量
     */
    public double tradedVolume;
    /**
     * 成交价格
     */
    public double tradedPrice;
    /**
     * 委托状态 固定为 0
     */
    public char entrustStatusNoUsed;
    /**
     * 成交状态名称
     */
    public String tradeStatusName;
    /**
     * 委托时间
     */
    public String tradeTime;
    /**
     * 日期
     */
    public String date;
    /**
     * 出错信息
     */
    public String errorMsg;
    /**
     * 成交状态
     */
    public char tradeStatus;
    /**
     * 市场代码
     */
    public char market;
    /**
     * 买卖类别标志
     */
    public char sellTypeFlag;
    /**
     * 委托状态
     */
    public char entrustStatus;
    /**
     * 买卖类别名称
     */
    public String sellTypeName;
    /**
     * 错误代码
     */
    public String errorCode;
    /**
     * 撤单数量
     */
    public double cancelNum;
    /**
     * 委托属性
     */
    public String entrustPropName;
    /**
     * 成交金额
     */
    public double tradeMoney;
    /**
     * 平仓标志
     */
    public char unwindTag;
    /**
     * 平仓标志名称
     */
    public String unwindTagName;
    /**
     * 委托子业务
     */
    public String entrustChildBusiness;
    /**
     * 约定号
     */
    public String contractNo;
    /**
     * 对方交易单元
     */
    public String reciprocalTradeUnit;
    /**
     * 还券模式
     */
    public int alsoNotesMode;
    /**
     * 委托方式
     */
    public String entrustType;
    /**
     * 申报编号
     */
    public String declareNum;
    /**
     * 撤单状态
     */
    public String cancelType;
    /**
     * 冻结金额
     */
    public double frozenMoney;
    /**
     * 冻结数量
     */
    public int frozenNum;
    /**
     * 对方证券帐户
     */
    public String adverseSecuritiesAccount;
    /**
     * 参考汇率
     */
    public double referenceRate;
    /**
     * 结算币种
     */
    public String settlementCurrency;
    /**
     * 交易币种
     */
    public String dealCurrency;
    /**
     * 证券中文名称
     */
    public String securitiesName;
    /**
     * 委托业务类型
     */
    public String businessType;
    /**
     * 特定股东管理系统流水号
     */
    public String serialNum;
    /**
     * 委托备注
     */
    public String EntrustRemark;
    /**
     * 证券类别
     */
    public String securityType;
}
