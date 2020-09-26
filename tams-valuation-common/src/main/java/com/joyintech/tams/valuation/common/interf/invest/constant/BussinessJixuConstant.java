package com.joyintech.tams.valuation.common.interf.invest.constant;

/**
 * 几许接口常量
 * @author admin
 *
 */
public interface BussinessJixuConstant {

    /**
     * 交易类型  即期换汇
     */
    String 交易类型_即期 = "300";
    
    /**
     * 交易类型  即期换汇
     */
    String 交易类型_掉期 = "760";
    
    /**
     * 交易类型  远期开仓
     */
    String 交易类型_远期开仓 = "407";
    
    /**
     * 交易类型  远期轧差交割
     */
    String 交易类型_远期轧差交割 = "409";
    
    /**
     * 交易类型  远期合约到期
     */
    String 交易类型_远期合约到期 = "410";
    
    /**
     * 消息头 外呼即期
     */
    String 消息头_外汇即期 = "jxFXOpenDtoMsg";
    
    /**
     * 消息头 外汇掉期 
     */
    String 消息头_外汇掉期 = "jxSwapFXOpenDtoMsg";
    
    /**
     * 消息头  外汇远期开仓交易 
     */
    String 消息头_外汇远期开仓交易 = "jxForwardCurrencyOpenDtoMsg";
    
    /**
     * 消息头  消息头_远期换汇合约到期 
     */
    String 消息头_远期换汇合约到期 = "jxForwardCurrencyMaurityDtoMsg";
    
    /**
     * 消息头  远期换汇轧差交割 
     */
    String 消息头_远期换汇轧差交割 = "jxForwardCurrencyCuttingDtoMsg";
    
    /**
     * 消息头 股票标的信息
     */
    String 消息头_股票标的信息 = "MsgSMFOperation";
    
    /**
     * 消息头 股票_现金分红
     */
    String 消息头_股票_现金分红 = "jxCashDividendDtoMsg";
    
    /**
     * 消息头 股票_红利再投 
     */
    String 消息头_股票_红利再投 = "jxStockDividendDtoMsg";
    
    /**
     * 消息头 利率互换开仓交易
     */
    String 消息头_利率互换开仓交易 = "";
    
    /**
     * 投资经理组  默认1
     */
    String 投资经理组_默认 = "1";
    
    /**
     * 资产类型-外汇掉期
     */
    String 资产类型_外汇掉期 = "114";
    

    /**
     * 消息头 股票交易_买卖
     */
    String 消息头_股票交易_买卖 = "jixuStockTranDtoMsg";
    
    /**
     * 消息头 股票交易_买卖
     */
    String 消息头_股票交易_新股中签 = "jixuStockNewReceiveDtoMsg";
    
    /**
     * 几许费用类型_即期手续费
     */
    int 费用类型_即期手续费 = 5034;
    
    /**
     * 几许费用类型_掉期手续费
     */
    int 费用类型_掉期手续费 = 5040;
    
    /**
     * 几许费用类型_远期手续费
     */
    int 费用类型_远期手续费 = 5000;
    
    /**
     * 掉期成交类型-远期/远期
     */
    int 成交类型_远期_远期  = 0;
    
    /**
     * 掉期成交类型-即期/远期
     */
    int 成交类型_即期_远期  = 1;
    
    /**
     * 掉期成交类型-即期/即期
     */
    int 成交类型_即期_即期  = 3;
    
    /**
     * 5002（默认券商）
     */
    String 券商_默认 = "5002";
    
    /**
     * 估值方法 1(默认市价法)  
     */
    String 估值方法_默认 = "1";
    
    /**
     * 估值方向 1(默认多方估值)  
     */
    String 估值方向_默认 = "1";

    /**
     * 差额收付方向_应收 0
     */
    int 差额收付方向_应收  = 0;
    
    /**
     * 差额收付方向_应付 1
     */
    int 差额收付方向_应付  = 1;
    

    /**
     * 数据源配置_默认 0
     */
    int 数据源配置_默认  = 0;
    
    /**
     * 总帐组_外汇远期
     */
    String 总帐组_外汇远期  = "172";
    
    /**
     * 分支序号
     */
    String NOBRANCHNOVALUE = "noBranchNoValue";
    
}
