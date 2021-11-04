package com.joyintech.service.trade.exchangetransmatch.dto;

import com.joyintech.background.component.common.pack.common.base.BaseEntity;
import com.joyintech.framework.export.paginator.domain.ITableRow;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.common.base.entity.SettleTransDataTotal;
import com.joyintech.tams.constant.DictCode;

import java.util.Date;

public class ExchangeTransMatch004Dto extends SettleTransDataTotal implements ITableRow {
    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 8516463571197086088L;

    /**
     * 匹配方式
     */
    private String matchType;

    /**
     * 业务编号
     */
    private String busiCode;

    /**
     * 组合代码
     */
    private String portfolioName;

    /**
     * 资产市场代码
     */
    private String finprodMarketId;

    /**
     * 交易数量
     */
    private String tradeNum;

    /**
     * 交易价格
     */
    private String tradePrice;

    public String getMatchType() {
        return matchType;
    }

    public void setMatchType(String matchType) {
        this.matchType = matchType;
    }

    public String getBusiCode() {
        return busiCode;
    }

    public void setBusiCode(String busiCode) {
        this.busiCode = busiCode;
    }

    public String getPortfolioName() {
        return portfolioName;
    }

    public void setPortfolioName(String portfolioName) {
        this.portfolioName = portfolioName;
    }

    public String getFinprodMarketId() {
        return finprodMarketId;
    }

    public void setFinprodMarketId(String finprodMarketId) {
        this.finprodMarketId = finprodMarketId;
    }

    public String getTradeNum() {
        return tradeNum;
    }

    public void setTradeNum(String tradeNum) {
        this.tradeNum = tradeNum;
    }

    public String getTradePrice() {
        return tradePrice;
    }

    public void setTradePrice(String tradePrice) {
        this.tradePrice = tradePrice;
    }

}