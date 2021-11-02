package com.joyintech.tams.invest.trade.exchangetransmatch.manualmatch.dto;

import com.joyintech.background.component.common.pack.common.base.BaseEntity;
import com.joyintech.framework.export.paginator.domain.ITableRow;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.constant.DictCode;

import java.util.Date;

public class ExchangeTransMatch004Dto extends BaseEntity implements ITableRow {
    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 4449595953418685312L;

    /**
     * 匹配方式
     */
    private String matchType;

    /**
     * 业务编号
     */
    private String busiCode;

    /**
     * 交易场所
     */
    private String tradeMarket;

    /**
     * 交易平台
     */
    private String tradePlat;


    /**
     * 申请编号
     */
    private String applyNo;

    /**
     * 组合名称
     */
    private String portfolioId;

    /**
     * 组合代码
     */
    private String portfolioName;

    /**
     * 资产代码
     */
    private String finprodId;

    /**
     * 资产类型
     */
    private String finprodType2;

    /**
     * 资产市场代码
     */
    private String finprodMarketId;

    /**
     * 交易方向
     */
    private String ps;

    /**
     * 交易金额
     */
    private String tradeAmout;

    /**
     * 交易数量
     */
    private String tradeNum;

    /**
     * 交易价格
     */
    private String tradePrice;

    /**
     * 交易日期
     */
    private Date tradeDate;

    /**
     * 币种
     */
    private String ccy;

    /**
     * 流水号
     */
    private String seqNo;


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

    public String getTradeMarket() {
        return tradeMarket;
    }

    public void setTradeMarket(String tradeMarket) {
        this.tradeMarket = tradeMarket;
    }

    public String getApplyNo() {
        return applyNo;
    }

    public void setApplyNo(String applyNo) {
        this.applyNo = applyNo;
    }

    public String getPortfolioId() {
        return portfolioId;
    }

    public void setPortfolioId(String portfolioId) {
        this.portfolioId = portfolioId;
    }

    public String getPortfolioName() {
        return portfolioName;
    }

    public void setPortfolioName(String portfolioName) {
        this.portfolioName = portfolioName;
    }

    public String getFinprodId() {
        return finprodId;
    }

    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
    }

    public String getFinprodType2() {
        return finprodType2;
    }

    public void setFinprodType2(String finprodType2) {
        this.finprodType2 = finprodType2;
    }

    public String getPs() {
        return ps;
    }

    public void setPs(String ps) {
        this.ps = ps;
    }

    public String getTradeAmout() {
        return tradeAmout;
    }

    public void setTradeAmout(String tradeAmout) {
        this.tradeAmout = tradeAmout;
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

    public Date getTradeDate() {
        return tradeDate;
    }

    public void setTradeDate(Date tradeDate) {
        this.tradeDate = tradeDate;
    }

    public String getCcy() {
        return ccy;
    }

    public void setCcy(String ccy) {
        this.ccy = ccy;
    }

    public String getFinprodMarketId() {
        return finprodMarketId;
    }

    public void setFinprodMarketId(String finprodMarketId) {
        this.finprodMarketId = finprodMarketId;
    }

    public String getTradePlat() {
        return tradePlat;
    }

    public String getTradePlatStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.交易平台, this.getTradePlat());
    }

    public void setTradePlat(String tradePlat) {
        this.tradePlat = tradePlat;
    }

    public String getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo;
    }
}