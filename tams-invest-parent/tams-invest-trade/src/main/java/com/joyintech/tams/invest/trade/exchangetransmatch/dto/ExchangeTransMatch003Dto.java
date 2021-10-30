package com.joyintech.tams.invest.trade.exchangetransmatch.dto;

import com.joyintech.framework.export.paginator.domain.ITableRow;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.common.base.entity.EtrEntrust;
import com.joyintech.tams.constant.DictCode;

public class ExchangeTransMatch003Dto extends EtrEntrust  implements ITableRow {
    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 5277303178311341685L;

    /**
     * 目标类型
     */
    private String targetType;

    /**
     * 交易类型
     */
    private String tradeType;

    /**
     * 组合名称
     */
    private String portfolioName;

    /**
     * 证券品种
     */
    private String secType;

    /**
     * 证券账户
     */
    private String secAcctId;

    /**
     * 证券账户名称
     */
    private String secAcctName;

    /**
     * 资金账户
     */
    private String acctNo;


    /**
     * 资金账户名称
     */
    private String acctName;

    public String getTargetType() {
        return targetType;
    }

    public void setTargetType(String targetType) {
        this.targetType = targetType;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    public String getPortfolioName() {
        return portfolioName;
    }

    public void setPortfolioName(String portfolioName) {
        this.portfolioName = portfolioName;
    }

    public String getSecType() {
        return secType;
    }

    public void setSecType(String secType) {
        this.secType = secType;
    }

    public String getSecAcctId() {
        return secAcctId;
    }

    public void setSecAcctId(String secAcctId) {
        this.secAcctId = secAcctId;
    }

    public String getSecAcctName() {
        return secAcctName;
    }

    public void setSecAcctName(String secAcctName) {
        this.secAcctName = secAcctName;
    }

    public String getAcctNo() {
        return acctNo;
    }

    public void setAcctNo(String acctNo) {
        this.acctNo = acctNo;
    }

    public String getAcctName() {
        return acctName;
    }

    public void setAcctName(String acctName) {
        this.acctName = acctName;
    }

    public String getEntrustTypeStr() {
        return  SysDataUtil.getNameFromDataDict(DictCode.投资指令类型, this.getTradeType());
    }

    public String getTradeMarketStr() {
        return  SysDataUtil.getNameFromDataDict(DictCode.交易场所, this.getTradeMarket());
    }

    public String getTradePlatStr() {
        return  SysDataUtil.getNameFromDataDict(DictCode.交易平台, this.getTradePlat());
    }
}
