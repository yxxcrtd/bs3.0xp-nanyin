package com.joyintech.tams.valuation.valapi.dto;

import com.joyintech.tams.valuation.valapi.annotations.ValInterfApiField;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName DurationTrade001Dto
 * @Description: 存续期交易信息dto
 * @Author yanya
 * @Date 2021/3/1 0001 
 * @Version V1.0
 **/
public class DurationTrade001Dto implements Serializable {
    private static final long serialVersionUID = 4186444923498090466L;

    /**金融产品代码**/
    @ValInterfApiField
    private String finprodId;
    /**金融产品类型**/
    @ValInterfApiField(dictCode = "FINPROD_TYPE")
    private String finprodType;
    /**金融产品类型（投管）**/
    private String finprodType2;
    /**业务类型**/
    @ValInterfApiField(dictCode = "BUSI_TYPE")
    private String busiType;
    /**起息日**/
    private Date vdate;
    /**到期日**/
    private Date mdate;
    /**交易方向**/
    @ValInterfApiField(dictCode = "PS")
    private String ps;
    /**交易日**/
    @ValInterfApiField
    private Date tradeDate;
    /**交割日**/
    @ValInterfApiField
    private Date settleDate;
    /**币种**/
    @ValInterfApiField(dictCode = "CCY")
    private String ccy;
    /**交易金额**/
    private BigDecimal tradeAmt;
    /**交易份额，债券数量等**/
    private BigDecimal shareAmt;
    /**交易本金，债券券面等**/
    private BigDecimal prinAmt;
    /**利息总额**/
    private BigDecimal intAmt;
    /**汇总交易编号**/
    private String totleTradeId;
    /**交易对手**/
    private String counterId;
    /**交易员**/
    private String trader;
    /**证券管理户代码**/
    private String secManageAcctId;
    /**现金流代码**/
    private String cashId;
    /**交易场所**/
    private String tradeMarket;
    /**交易市场**/
    private String tradeMarketMode;
    /**交易平台**/
    private String tradePlat;
    /**交易状态**/
    @ValInterfApiField(dictCode = "TRADE_STATUS")
    private String tradeStatus;
    /**通道代码**/
    private String chlId;

    public String getFinprodId() {
        return finprodId;
    }

    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
    }

    public String getFinprodType() {
        return finprodType;
    }

    public void setFinprodType(String finprodType) {
        this.finprodType = finprodType;
    }

    public String getFinprodType2() {
        return finprodType2;
    }

    public void setFinprodType2(String finprodType2) {
        this.finprodType2 = finprodType2;
    }

    public String getBusiType() {
        return busiType;
    }

    public void setBusiType(String busiType) {
        this.busiType = busiType;
    }

    public Date getVdate() {
        return vdate;
    }

    public void setVdate(Date vdate) {
        this.vdate = vdate;
    }

    public Date getMdate() {
        return mdate;
    }

    public void setMdate(Date mdate) {
        this.mdate = mdate;
    }

    public String getPs() {
        return ps;
    }

    public void setPs(String ps) {
        this.ps = ps;
    }

    public Date getTradeDate() {
        return tradeDate;
    }

    public void setTradeDate(Date tradeDate) {
        this.tradeDate = tradeDate;
    }

    public Date getSettleDate() {
        return settleDate;
    }

    public void setSettleDate(Date settleDate) {
        this.settleDate = settleDate;
    }

    public String getCcy() {
        return ccy;
    }

    public void setCcy(String ccy) {
        this.ccy = ccy;
    }

    public BigDecimal getTradeAmt() {
        return tradeAmt;
    }

    public void setTradeAmt(BigDecimal tradeAmt) {
        this.tradeAmt = tradeAmt;
    }

    public BigDecimal getShareAmt() {
        return shareAmt;
    }

    public void setShareAmt(BigDecimal shareAmt) {
        this.shareAmt = shareAmt;
    }

    public BigDecimal getPrinAmt() {
        return prinAmt;
    }

    public void setPrinAmt(BigDecimal prinAmt) {
        this.prinAmt = prinAmt;
    }

    public BigDecimal getIntAmt() {
        return intAmt;
    }

    public void setIntAmt(BigDecimal intAmt) {
        this.intAmt = intAmt;
    }

    public String getTotleTradeId() {
        return totleTradeId;
    }

    public void setTotleTradeId(String totleTradeId) {
        this.totleTradeId = totleTradeId;
    }

    public String getCounterId() {
        return counterId;
    }

    public void setCounterId(String counterId) {
        this.counterId = counterId;
    }

    public String getTrader() {
        return trader;
    }

    public void setTrader(String trader) {
        this.trader = trader;
    }

    public String getSecManageAcctId() {
        return secManageAcctId;
    }

    public void setSecManageAcctId(String secManageAcctId) {
        this.secManageAcctId = secManageAcctId;
    }

    public String getCashId() {
        return cashId;
    }

    public void setCashId(String cashId) {
        this.cashId = cashId;
    }

    public String getTradeMarket() {
        return tradeMarket;
    }

    public void setTradeMarket(String tradeMarket) {
        this.tradeMarket = tradeMarket;
    }

    public String getTradeMarketMode() {
        return tradeMarketMode;
    }

    public void setTradeMarketMode(String tradeMarketMode) {
        this.tradeMarketMode = tradeMarketMode;
    }

    public String getTradePlat() {
        return tradePlat;
    }

    public void setTradePlat(String tradePlat) {
        this.tradePlat = tradePlat;
    }

    public String getTradeStatus() {
        return tradeStatus;
    }

    public void setTradeStatus(String tradeStatus) {
        this.tradeStatus = tradeStatus;
    }

    public String getChlId() {
        return chlId;
    }

    public void setChlId(String chlId) {
        this.chlId = chlId;
    }

    @Override
    public String toString() {
        return "DurationTrade001Dto{" +
                "finprodId='" + finprodId + '\'' +
                ", finprodType='" + finprodType + '\'' +
                ", finprodType2='" + finprodType2 + '\'' +
                ", busiType='" + busiType + '\'' +
                ", vdate=" + vdate +
                ", mdate=" + mdate +
                ", ps='" + ps + '\'' +
                ", tradeDate=" + tradeDate +
                ", settleDate=" + settleDate +
                ", ccy='" + ccy + '\'' +
                ", tradeAmt=" + tradeAmt +
                ", shareAmt=" + shareAmt +
                ", prinAmt=" + prinAmt +
                ", intAmt=" + intAmt +
                ", totleTradeId='" + totleTradeId + '\'' +
                ", counterId='" + counterId + '\'' +
                ", trader='" + trader + '\'' +
                ", secManageAcctId='" + secManageAcctId + '\'' +
                ", cashId='" + cashId + '\'' +
                ", tradeMarket='" + tradeMarket + '\'' +
                ", tradeMarketMode='" + tradeMarketMode + '\'' +
                ", tradePlat='" + tradePlat + '\'' +
                ", tradeStatus='" + tradeStatus + '\'' +
                ", chlId='" + chlId + '\'' +
                '}';
    }
}
