package com.joyintech.tams.invest.trade.exchangetransmatch.manualmatch.dto;

import com.joyintech.background.component.common.pack.common.base.BaseEntity;
import com.joyintech.framework.export.paginator.domain.ITableRow;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 匹配明细查看页面-成交数据列表
 */
public class ExchangeTransMatch005Dto extends BaseEntity implements ITableRow {
    //匹配状态
    private String matchStatus;
    //匹配进度
    private String matchProgress;
    //交易场所
    private String tradeMarket;
    //申请编号
    private String applyTradeId;
    //证券账户代码
    private String secAcctId;
    //证券账户
    private String secAcctName;
    //资产类型
    private String secType;
    //资产代码
    private String finprodMarketId;
    //资产简称
    private String finprodAbbr;
    //席位号
    private String seatNumber;
    //交易平台
    private String tradePlat;
    //交易方向
    private String ps;
    //剩余成交金额
    private BigDecimal reTradeAmt;
    //剩余成交数量
    private BigDecimal reShareAmt;
    //成交价格
    private BigDecimal unitFprice;
    //单位净值
    private BigDecimal unitCprice;
    //币种
    private String ccy;
    //成交时间
    private Date tradeDate;
    //资金账号
    private String acctName;

    public String getMatchStatus() {
        return matchStatus;
    }

    public void setMatchStatus(String matchStatus) {
        this.matchStatus = matchStatus;
    }

    public String getMatchProgress() {
        return matchProgress;
    }

    public void setMatchProgress(String matchProgress) {
        this.matchProgress = matchProgress;
    }

    public String getTradeMarket() {
        return tradeMarket;
    }

    public void setTradeMarket(String tradeMarket) {
        this.tradeMarket = tradeMarket;
    }

    public String getApplyTradeId() {
        return applyTradeId;
    }

    public void setApplyTradeId(String applyTradeId) {
        this.applyTradeId = applyTradeId;
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

    public String getSecType() {
        return secType;
    }

    public void setSecType(String secType) {
        this.secType = secType;
    }

    public String getFinprodMarketId() {
        return finprodMarketId;
    }

    public void setFinprodMarketId(String finprodMarketId) {
        this.finprodMarketId = finprodMarketId;
    }

    public String getFinprodAbbr() {
        return finprodAbbr;
    }

    public void setFinprodAbbr(String finprodAbbr) {
        this.finprodAbbr = finprodAbbr;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String getTradePlat() {
        return tradePlat;
    }

    public void setTradePlat(String tradePlat) {
        this.tradePlat = tradePlat;
    }

    public String getPs() {
        return ps;
    }

    public void setPs(String ps) {
        this.ps = ps;
    }

    public BigDecimal getReTradeAmt() {
        return reTradeAmt;
    }

    public void setReTradeAmt(BigDecimal reTradeAmt) {
        this.reTradeAmt = reTradeAmt;
    }

    public BigDecimal getReShareAmt() {
        return reShareAmt;
    }

    public void setReShareAmt(BigDecimal reShareAmt) {
        this.reShareAmt = reShareAmt;
    }

    public BigDecimal getUnitFprice() {
        return unitFprice;
    }

    public void setUnitFprice(BigDecimal unitFprice) {
        this.unitFprice = unitFprice;
    }

    public BigDecimal getUnitCprice() {
        return unitCprice;
    }

    public void setUnitCprice(BigDecimal unitCprice) {
        this.unitCprice = unitCprice;
    }

    public String getCcy() {
        return ccy;
    }

    public void setCcy(String ccy) {
        this.ccy = ccy;
    }

    public Date getTradeDate() {
        return tradeDate;
    }

    public void setTradeDate(Date tradeDate) {
        this.tradeDate = tradeDate;
    }

    public String getAcctName() {
        return acctName;
    }

    public void setAcctName(String acctName) {
        this.acctName = acctName;
    }
}
