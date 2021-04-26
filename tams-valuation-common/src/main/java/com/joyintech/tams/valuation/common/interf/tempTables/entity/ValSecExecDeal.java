package com.joyintech.tams.valuation.common.interf.tempTables.entity;

import com.joyintech.framework.base.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName ValSecExecDeal
 * @Description: 债券行权交易
 * @Author yanya
 * @Date 2021/3/9 0009 
 * @Version V1.0
 **/
public class ValSecExecDeal extends BaseEntity {

    private static final long serialVersionUID = -5417658389588820922L;

    /**成交编号**/
    private String tradeId;
    /**数据动作标志**/
    private String dataActionSign;
    /**产品代码**/
    private String prodId;
    /**债券代码**/
    private String assetId;
    /**债券简称**/
    private String secAbbr;
    /**投资目的**/
    private String investAim;
    /**交易场所**/
    private String tradeMarket;
    /**币种**/
    private String ccy;
    /**交易方向**/
    private String ps;
    /**交易日期**/
    private Date dealDate;
    /**预计/实际资金到账日**/
    private Date settleDate;
    /**行权申请开始日**/
    private Date vdate;
    /**行权日**/
    private Date execDate;
    /**交易对手**/
    private String counterId;
    /**交易对手类型**/
    private String counterType;
    /**交易员**/
    private String trader;
    /**证券托管户**/
    private String secManageAcctId;
    /**通道代码**/
    private String chlId;
    /**券面总额**/
    private BigDecimal dealNum;
    /**单位面值**/
    private BigDecimal parValue;
    /**净价**/
    private BigDecimal cprice;
    /**应收利息**/
    private BigDecimal unitInt;
    /**全价**/
    private BigDecimal fprice;
    /**净价总额**/
    private BigDecimal cpriceAmt;
    /**应收利息总额**/
    private BigDecimal intAmt;
    /**全价总额**/
    private BigDecimal fpriceAmt;
    /**印花税**/
    private BigDecimal stampDuty;
    /**印花税是否随交易支付**/
    private String isStampDuty;
    /**经手费**/
    private BigDecimal brokerage;
    /**经手费是否随交易支付**/
    private String isBrokerage;
    /**证管费**/
    private BigDecimal certfFee;
    /**证管费是否随交易支付**/
    private String isCertfFee;
    /**结算费**/
    private BigDecimal stlAmt;
    /**结算费是否随交易支付**/
    private String isStlAmt;
    /**佣金**/
    private BigDecimal commission;
    /**佣金是否随交易支付**/
    private String isCommission;
    /**交易手续费**/
    private BigDecimal dealFeeAmt;
    /**交易手续费是否随交易支付**/
    private String isDealFeeAmt;
    /**结算手续费**/
    private BigDecimal stlFeeAmt;
    /**结算手续费是否随交易支付**/
    private String isStlFeeAmt;
    /**通道费**/
    private BigDecimal chlFeeAmt;
    /**通道费是否随交易支付**/
    private String isChlFeeAmt;
    /**交易金额**/
    private BigDecimal tradeAmt;
    /**登记结算机构**/
    private String registAgency;
    /**付款账户**/
    private String myAcctNo;
    /**收款账户**/
    private String counterAcctNo;
    /**资金交收日**/
    private Date fundSettleDate;

    public String getTradeId() {
        return tradeId;
    }

    public void setTradeId(String tradeId) {
        this.tradeId = tradeId;
    }

    public String getDataActionSign() {
        return dataActionSign;
    }

    public void setDataActionSign(String dataActionSign) {
        this.dataActionSign = dataActionSign;
    }

    public String getProdId() {
        return prodId;
    }

    public void setProdId(String prodId) {
        this.prodId = prodId;
    }

    public String getAssetId() {
        return assetId;
    }

    public void setAssetId(String assetId) {
        this.assetId = assetId;
    }

    public String getSecAbbr() {
        return secAbbr;
    }

    public void setSecAbbr(String secAbbr) {
        this.secAbbr = secAbbr;
    }

    public String getInvestAim() {
        return investAim;
    }

    public void setInvestAim(String investAim) {
        this.investAim = investAim;
    }

    public String getTradeMarket() {
        return tradeMarket;
    }

    public void setTradeMarket(String tradeMarket) {
        this.tradeMarket = tradeMarket;
    }

    public String getCcy() {
        return ccy;
    }

    public void setCcy(String ccy) {
        this.ccy = ccy;
    }

    public String getPs() {
        return ps;
    }

    public void setPs(String ps) {
        this.ps = ps;
    }

    public Date getDealDate() {
        return dealDate;
    }

    public void setDealDate(Date dealDate) {
        this.dealDate = dealDate;
    }

    public Date getSettleDate() {
        return settleDate;
    }

    public void setSettleDate(Date settleDate) {
        this.settleDate = settleDate;
    }

    public Date getVdate() {
        return vdate;
    }

    public void setVdate(Date vdate) {
        this.vdate = vdate;
    }

    public Date getExecDate() {
        return execDate;
    }

    public void setExecDate(Date execDate) {
        this.execDate = execDate;
    }

    public String getCounterId() {
        return counterId;
    }

    public void setCounterId(String counterId) {
        this.counterId = counterId;
    }

    public String getCounterType() {
        return counterType;
    }

    public void setCounterType(String counterType) {
        this.counterType = counterType;
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

    public String getChlId() {
        return chlId;
    }

    public void setChlId(String chlId) {
        this.chlId = chlId;
    }

    public BigDecimal getDealNum() {
        return dealNum;
    }

    public void setDealNum(BigDecimal dealNum) {
        this.dealNum = dealNum;
    }

    public BigDecimal getParValue() {
        return parValue;
    }

    public void setParValue(BigDecimal parValue) {
        this.parValue = parValue;
    }

    public BigDecimal getCprice() {
        return cprice;
    }

    public void setCprice(BigDecimal cprice) {
        this.cprice = cprice;
    }

    public BigDecimal getUnitInt() {
        return unitInt;
    }

    public void setUnitInt(BigDecimal unitInt) {
        this.unitInt = unitInt;
    }

    public BigDecimal getFprice() {
        return fprice;
    }

    public void setFprice(BigDecimal fprice) {
        this.fprice = fprice;
    }

    public BigDecimal getCpriceAmt() {
        return cpriceAmt;
    }

    public void setCpriceAmt(BigDecimal cpriceAmt) {
        this.cpriceAmt = cpriceAmt;
    }

    public BigDecimal getIntAmt() {
        return intAmt;
    }

    public void setIntAmt(BigDecimal intAmt) {
        this.intAmt = intAmt;
    }

    public BigDecimal getFpriceAmt() {
        return fpriceAmt;
    }

    public void setFpriceAmt(BigDecimal fpriceAmt) {
        this.fpriceAmt = fpriceAmt;
    }

    public BigDecimal getStampDuty() {
        return stampDuty;
    }

    public void setStampDuty(BigDecimal stampDuty) {
        this.stampDuty = stampDuty;
    }

    public String getIsStampDuty() {
        return isStampDuty;
    }

    public void setIsStampDuty(String isStampDuty) {
        this.isStampDuty = isStampDuty;
    }

    public BigDecimal getBrokerage() {
        return brokerage;
    }

    public void setBrokerage(BigDecimal brokerage) {
        this.brokerage = brokerage;
    }

    public String getIsBrokerage() {
        return isBrokerage;
    }

    public void setIsBrokerage(String isBrokerage) {
        this.isBrokerage = isBrokerage;
    }

    public BigDecimal getCertfFee() {
        return certfFee;
    }

    public void setCertfFee(BigDecimal certfFee) {
        this.certfFee = certfFee;
    }

    public String getIsCertfFee() {
        return isCertfFee;
    }

    public void setIsCertfFee(String isCertfFee) {
        this.isCertfFee = isCertfFee;
    }

    public BigDecimal getStlAmt() {
        return stlAmt;
    }

    public void setStlAmt(BigDecimal stlAmt) {
        this.stlAmt = stlAmt;
    }

    public String getIsStlAmt() {
        return isStlAmt;
    }

    public void setIsStlAmt(String isStlAmt) {
        this.isStlAmt = isStlAmt;
    }

    public BigDecimal getCommission() {
        return commission;
    }

    public void setCommission(BigDecimal commission) {
        this.commission = commission;
    }

    public String getIsCommission() {
        return isCommission;
    }

    public void setIsCommission(String isCommission) {
        this.isCommission = isCommission;
    }

    public BigDecimal getDealFeeAmt() {
        return dealFeeAmt;
    }

    public void setDealFeeAmt(BigDecimal dealFeeAmt) {
        this.dealFeeAmt = dealFeeAmt;
    }

    public String getIsDealFeeAmt() {
        return isDealFeeAmt;
    }

    public void setIsDealFeeAmt(String isDealFeeAmt) {
        this.isDealFeeAmt = isDealFeeAmt;
    }

    public BigDecimal getStlFeeAmt() {
        return stlFeeAmt;
    }

    public void setStlFeeAmt(BigDecimal stlFeeAmt) {
        this.stlFeeAmt = stlFeeAmt;
    }

    public String getIsStlFeeAmt() {
        return isStlFeeAmt;
    }

    public void setIsStlFeeAmt(String isStlFeeAmt) {
        this.isStlFeeAmt = isStlFeeAmt;
    }

    public BigDecimal getChlFeeAmt() {
        return chlFeeAmt;
    }

    public void setChlFeeAmt(BigDecimal chlFeeAmt) {
        this.chlFeeAmt = chlFeeAmt;
    }

    public String getIsChlFeeAmt() {
        return isChlFeeAmt;
    }

    public void setIsChlFeeAmt(String isChlFeeAmt) {
        this.isChlFeeAmt = isChlFeeAmt;
    }

    public BigDecimal getTradeAmt() {
        return tradeAmt;
    }

    public void setTradeAmt(BigDecimal tradeAmt) {
        this.tradeAmt = tradeAmt;
    }

    public String getRegistAgency() {
        return registAgency;
    }

    public void setRegistAgency(String registAgency) {
        this.registAgency = registAgency;
    }

    public String getMyAcctNo() {
        return myAcctNo;
    }

    public void setMyAcctNo(String myAcctNo) {
        this.myAcctNo = myAcctNo;
    }

    public String getCounterAcctNo() {
        return counterAcctNo;
    }

    public void setCounterAcctNo(String counterAcctNo) {
        this.counterAcctNo = counterAcctNo;
    }

    public Date getFundSettleDate() {
        return fundSettleDate;
    }

    public void setFundSettleDate(Date fundSettleDate) {
        this.fundSettleDate = fundSettleDate;
    }
}
