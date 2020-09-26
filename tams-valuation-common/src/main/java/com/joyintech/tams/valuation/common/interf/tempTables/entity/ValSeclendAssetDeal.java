package com.joyintech.tams.valuation.common.interf.tempTables.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class ValSeclendAssetDeal extends BaseEntity {
    /**
     * 产品代码
     */
    private String prodId;

    /**
     * 金融产品代码
     */
    private String finprodId;

    /**
     * 金融产品类型
     */
    private String finprodType;

    /**
     * 债券代码
     */
    private String secId;

    /**
     * 数据动作标志
     */
    private String dataActionSign;

    /**
     * 交易场所
     */
    private String tradeMarket;

    /**
     * 交易日
     */
    private Date dealDate;

    /**
     * 交割日
     */
    private Date settdateDate;

    /**
     * 交易方向
     */
    private String ps;

    /**
     * 结算方式
     */
    private String stlForm;

    /**
     * 成交数量
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal dealNum;

    /**
     * 单位面值
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal parValue;

    /**
     * 未随交易支付费用
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal feeAmtUnpay;

    /**
     * 交易对手
     */
    private String counterId;

    /**
     * 交易对手类型
     */
    private String counterType;

    /**
     * 通道代码
     */
    private String chlId;

    /**
     * 净价
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal cprice;

    /**
     * 全价
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal fprice;

    /**
     * 应收利息
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal unitInt;

    /**
     * 应收利息总额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal intAmt;

    /**
     * 净价总额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal cpriceAmt;

    /**
     * 全价总额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal fpriceAmt;

    /**
     * 币种
     */
    private String ccy;

    /**
     * 成交编号
     */
    private String tradeId;

    /**
     * 投资目的
     */
    private String investAim;

    /**
     * 印花税
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal stampDuty;

    /**
     * 印花税是否随交易支付
     */
    private String isStampDuty;

    /**
     * 经手费
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal brokerage;

    /**
     * 经手费是否随交易支付
     */
    private String isBrokerage;

    /**
     * 证管费
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal certfFee;

    /**
     * 证管费是否随交易支付
     */
    private String isCertfFee;

    /**
     * 结算费
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal stlAmt;

    /**
     * 结算费是否随交易支付
     */
    private String isStlAmt;

    /**
     * 佣金
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal commission;

    /**
     * 佣金是否随交易支付
     */
    private String isCommission;

    /**
     * 交易手续费
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal dealFeeAmt;

    /**
     * 交易手续费是否随交易支付
     */
    private String isDealFeeAmt;

    /**
     * 结算手续费
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal stlFeeAmt;

    /**
     * 结算手续费是否随交易支付
     */
    private String isStlFeeAmt;

    /**
     * 通道费
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal chlFeeAmt;

    /**
     * 通道费是否随交易支付
     */
    private String isChlFeeAmt;

    /**
     * 交易金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal tradeAmt;

    /**
     * 登记结算机构
     */
    private String registAgency;

    /**
     * 有无清算指令
     */
    private String isClean;

    /**
     * 付款账户
     */
    private String myAcctNo;

    /**
     * 收款账户
     */
    private String counterAcctNo;

    /**
     * 资金交收日
     */
    private Date fundSettleDate;

    /**
     * 取得 产品代码
     * @return 产品代码
     */
    public String getProdId() {
        return prodId;
    }

    /**
     * 设置 产品代码
     * @param prodId 产品代码
     */
    public void setProdId(String prodId) {
        this.prodId = prodId == null ? null : prodId.trim();
    }

    /**
     * 取得 金融产品代码
     * @return 金融产品代码
     */
    public String getFinprodId() {
        return finprodId;
    }

    /**
     * 设置 金融产品代码
     * @param finprodId 金融产品代码
     */
    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId == null ? null : finprodId.trim();
    }

    /**
     * 取得 金融产品类型
     * @return 金融产品类型
     */
    public String getFinprodType() {
        return finprodType;
    }

    /**
     * 设置 金融产品类型
     * @param finprodType 金融产品类型
     */
    public void setFinprodType(String finprodType) {
        this.finprodType = finprodType == null ? null : finprodType.trim();
    }

    /**
     * 取得 债券代码
     * @return 债券代码
     */
    public String getSecId() {
        return secId;
    }

    /**
     * 设置 债券代码
     * @param secId 债券代码
     */
    public void setSecId(String secId) {
        this.secId = secId == null ? null : secId.trim();
    }

    /**
     * 取得 数据动作标志
     * @return 数据动作标志
     */
    public String getDataActionSign() {
        return dataActionSign;
    }

    /**
     * 设置 数据动作标志
     * @param dataActionSign 数据动作标志
     */
    public void setDataActionSign(String dataActionSign) {
        this.dataActionSign = dataActionSign == null ? null : dataActionSign.trim();
    }

    /**
     * 取得 交易场所
     * @return 交易场所
     */
    public String getTradeMarket() {
        return tradeMarket;
    }

    /**
     * 设置 交易场所
     * @param tradeMarket 交易场所
     */
    public void setTradeMarket(String tradeMarket) {
        this.tradeMarket = tradeMarket == null ? null : tradeMarket.trim();
    }

    /**
     * 取得 交易日
     * @return 交易日
     */
    public Date getDealDate() {
        return dealDate;
    }

    /**
     * 设置 交易日
     * @param dealDate 交易日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setDealDate(Date dealDate) {
        this.dealDate = dealDate;
    }

    /**
     * 取得 交割日
     * @return 交割日
     */
    public Date getSettdateDate() {
        return settdateDate;
    }

    /**
     * 设置 交割日
     * @param settdateDate 交割日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setSettdateDate(Date settdateDate) {
        this.settdateDate = settdateDate;
    }

    /**
     * 取得 交易方向
     * @return 交易方向
     */
    public String getPs() {
        return ps;
    }

    /**
     * 设置 交易方向
     * @param ps 交易方向
     */
    public void setPs(String ps) {
        this.ps = ps == null ? null : ps.trim();
    }

    /**
     * 取得 结算方式
     * @return 结算方式
     */
    public String getStlForm() {
        return stlForm;
    }

    /**
     * 设置 结算方式
     * @param stlForm 结算方式
     */
    public void setStlForm(String stlForm) {
        this.stlForm = stlForm == null ? null : stlForm.trim();
    }

    /**
     * 取得 成交数量
     * @return 成交数量
     */
    public BigDecimal getDealNum() {
        return dealNum;
    }

    /**
     * 设置 成交数量
     * @param dealNum 成交数量
     */
    public void setDealNum(BigDecimal dealNum) {
        this.dealNum = dealNum;
    }

    /**
     * 取得 单位面值
     * @return 单位面值
     */
    public BigDecimal getParValue() {
        return parValue;
    }

    /**
     * 设置 单位面值
     * @param parValue 单位面值
     */
    public void setParValue(BigDecimal parValue) {
        this.parValue = parValue;
    }

    /**
     * 取得 未随交易支付费用
     * @return 未随交易支付费用
     */
    public BigDecimal getFeeAmtUnpay() {
        return feeAmtUnpay;
    }

    /**
     * 设置 未随交易支付费用
     * @param feeAmtUnpay 未随交易支付费用
     */
    public void setFeeAmtUnpay(BigDecimal feeAmtUnpay) {
        this.feeAmtUnpay = feeAmtUnpay;
    }

    /**
     * 取得 交易对手
     * @return 交易对手
     */
    public String getCounterId() {
        return counterId;
    }

    /**
     * 设置 交易对手
     * @param counterId 交易对手
     */
    public void setCounterId(String counterId) {
        this.counterId = counterId == null ? null : counterId.trim();
    }

    /**
     * 取得 交易对手类型
     * @return 交易对手类型
     */
    public String getCounterType() {
        return counterType;
    }

    /**
     * 设置 交易对手类型
     * @param counterType 交易对手类型
     */
    public void setCounterType(String counterType) {
        this.counterType = counterType == null ? null : counterType.trim();
    }

    /**
     * 取得 通道代码
     * @return 通道代码
     */
    public String getChlId() {
        return chlId;
    }

    /**
     * 设置 通道代码
     * @param chlId 通道代码
     */
    public void setChlId(String chlId) {
        this.chlId = chlId == null ? null : chlId.trim();
    }

    /**
     * 取得 净价
     * @return 净价
     */
    public BigDecimal getCprice() {
        return cprice;
    }

    /**
     * 设置 净价
     * @param cprice 净价
     */
    public void setCprice(BigDecimal cprice) {
        this.cprice = cprice;
    }

    /**
     * 取得 全价
     * @return 全价
     */
    public BigDecimal getFprice() {
        return fprice;
    }

    /**
     * 设置 全价
     * @param fprice 全价
     */
    public void setFprice(BigDecimal fprice) {
        this.fprice = fprice;
    }

    /**
     * 取得 应收利息
     * @return 应收利息
     */
    public BigDecimal getUnitInt() {
        return unitInt;
    }

    /**
     * 设置 应收利息
     * @param unitInt 应收利息
     */
    public void setUnitInt(BigDecimal unitInt) {
        this.unitInt = unitInt;
    }

    /**
     * 取得 应收利息总额
     * @return 应收利息总额
     */
    public BigDecimal getIntAmt() {
        return intAmt;
    }

    /**
     * 设置 应收利息总额
     * @param intAmt 应收利息总额
     */
    public void setIntAmt(BigDecimal intAmt) {
        this.intAmt = intAmt;
    }

    /**
     * 取得 净价总额
     * @return 净价总额
     */
    public BigDecimal getCpriceAmt() {
        return cpriceAmt;
    }

    /**
     * 设置 净价总额
     * @param cpriceAmt 净价总额
     */
    public void setCpriceAmt(BigDecimal cpriceAmt) {
        this.cpriceAmt = cpriceAmt;
    }

    /**
     * 取得 全价总额
     * @return 全价总额
     */
    public BigDecimal getFpriceAmt() {
        return fpriceAmt;
    }

    /**
     * 设置 全价总额
     * @param fpriceAmt 全价总额
     */
    public void setFpriceAmt(BigDecimal fpriceAmt) {
        this.fpriceAmt = fpriceAmt;
    }

    /**
     * 取得 币种
     * @return 币种
     */
    public String getCcy() {
        return ccy;
    }

    /**
     * 设置 币种
     * @param ccy 币种
     */
    public void setCcy(String ccy) {
        this.ccy = ccy == null ? null : ccy.trim();
    }

    /**
     * 取得 成交编号
     * @return 成交编号
     */
    public String getTradeId() {
        return tradeId;
    }

    /**
     * 设置 成交编号
     * @param tradeId 成交编号
     */
    public void setTradeId(String tradeId) {
        this.tradeId = tradeId == null ? null : tradeId.trim();
    }

    /**
     * 取得 投资目的
     * @return 投资目的
     */
    public String getInvestAim() {
        return investAim;
    }

    /**
     * 设置 投资目的
     * @param investAim 投资目的
     */
    public void setInvestAim(String investAim) {
        this.investAim = investAim == null ? null : investAim.trim();
    }

    /**
     * 取得 印花税
     * @return 印花税
     */
    public BigDecimal getStampDuty() {
        return stampDuty;
    }

    /**
     * 设置 印花税
     * @param stampDuty 印花税
     */
    public void setStampDuty(BigDecimal stampDuty) {
        this.stampDuty = stampDuty;
    }

    /**
     * 取得 印花税是否随交易支付
     * @return 印花税是否随交易支付
     */
    public String getIsStampDuty() {
        return isStampDuty;
    }

    /**
     * 设置 印花税是否随交易支付
     * @param isStampDuty 印花税是否随交易支付
     */
    public void setIsStampDuty(String isStampDuty) {
        this.isStampDuty = isStampDuty == null ? null : isStampDuty.trim();
    }

    /**
     * 取得 经手费
     * @return 经手费
     */
    public BigDecimal getBrokerage() {
        return brokerage;
    }

    /**
     * 设置 经手费
     * @param brokerage 经手费
     */
    public void setBrokerage(BigDecimal brokerage) {
        this.brokerage = brokerage;
    }

    /**
     * 取得 经手费是否随交易支付
     * @return 经手费是否随交易支付
     */
    public String getIsBrokerage() {
        return isBrokerage;
    }

    /**
     * 设置 经手费是否随交易支付
     * @param isBrokerage 经手费是否随交易支付
     */
    public void setIsBrokerage(String isBrokerage) {
        this.isBrokerage = isBrokerage == null ? null : isBrokerage.trim();
    }

    /**
     * 取得 证管费
     * @return 证管费
     */
    public BigDecimal getCertfFee() {
        return certfFee;
    }

    /**
     * 设置 证管费
     * @param certfFee 证管费
     */
    public void setCertfFee(BigDecimal certfFee) {
        this.certfFee = certfFee;
    }

    /**
     * 取得 证管费是否随交易支付
     * @return 证管费是否随交易支付
     */
    public String getIsCertfFee() {
        return isCertfFee;
    }

    /**
     * 设置 证管费是否随交易支付
     * @param isCertfFee 证管费是否随交易支付
     */
    public void setIsCertfFee(String isCertfFee) {
        this.isCertfFee = isCertfFee == null ? null : isCertfFee.trim();
    }

    /**
     * 取得 结算费
     * @return 结算费
     */
    public BigDecimal getStlAmt() {
        return stlAmt;
    }

    /**
     * 设置 结算费
     * @param stlAmt 结算费
     */
    public void setStlAmt(BigDecimal stlAmt) {
        this.stlAmt = stlAmt;
    }

    /**
     * 取得 结算费是否随交易支付
     * @return 结算费是否随交易支付
     */
    public String getIsStlAmt() {
        return isStlAmt;
    }

    /**
     * 设置 结算费是否随交易支付
     * @param isStlAmt 结算费是否随交易支付
     */
    public void setIsStlAmt(String isStlAmt) {
        this.isStlAmt = isStlAmt == null ? null : isStlAmt.trim();
    }

    /**
     * 取得 佣金
     * @return 佣金
     */
    public BigDecimal getCommission() {
        return commission;
    }

    /**
     * 设置 佣金
     * @param commission 佣金
     */
    public void setCommission(BigDecimal commission) {
        this.commission = commission;
    }

    /**
     * 取得 佣金是否随交易支付
     * @return 佣金是否随交易支付
     */
    public String getIsCommission() {
        return isCommission;
    }

    /**
     * 设置 佣金是否随交易支付
     * @param isCommission 佣金是否随交易支付
     */
    public void setIsCommission(String isCommission) {
        this.isCommission = isCommission == null ? null : isCommission.trim();
    }

    /**
     * 取得 交易手续费
     * @return 交易手续费
     */
    public BigDecimal getDealFeeAmt() {
        return dealFeeAmt;
    }

    /**
     * 设置 交易手续费
     * @param dealFeeAmt 交易手续费
     */
    public void setDealFeeAmt(BigDecimal dealFeeAmt) {
        this.dealFeeAmt = dealFeeAmt;
    }

    /**
     * 取得 交易手续费是否随交易支付
     * @return 交易手续费是否随交易支付
     */
    public String getIsDealFeeAmt() {
        return isDealFeeAmt;
    }

    /**
     * 设置 交易手续费是否随交易支付
     * @param isDealFeeAmt 交易手续费是否随交易支付
     */
    public void setIsDealFeeAmt(String isDealFeeAmt) {
        this.isDealFeeAmt = isDealFeeAmt == null ? null : isDealFeeAmt.trim();
    }

    /**
     * 取得 结算手续费
     * @return 结算手续费
     */
    public BigDecimal getStlFeeAmt() {
        return stlFeeAmt;
    }

    /**
     * 设置 结算手续费
     * @param stlFeeAmt 结算手续费
     */
    public void setStlFeeAmt(BigDecimal stlFeeAmt) {
        this.stlFeeAmt = stlFeeAmt;
    }

    /**
     * 取得 结算手续费是否随交易支付
     * @return 结算手续费是否随交易支付
     */
    public String getIsStlFeeAmt() {
        return isStlFeeAmt;
    }

    /**
     * 设置 结算手续费是否随交易支付
     * @param isStlFeeAmt 结算手续费是否随交易支付
     */
    public void setIsStlFeeAmt(String isStlFeeAmt) {
        this.isStlFeeAmt = isStlFeeAmt == null ? null : isStlFeeAmt.trim();
    }

    /**
     * 取得 通道费
     * @return 通道费
     */
    public BigDecimal getChlFeeAmt() {
        return chlFeeAmt;
    }

    /**
     * 设置 通道费
     * @param chlFeeAmt 通道费
     */
    public void setChlFeeAmt(BigDecimal chlFeeAmt) {
        this.chlFeeAmt = chlFeeAmt;
    }

    /**
     * 取得 通道费是否随交易支付
     * @return 通道费是否随交易支付
     */
    public String getIsChlFeeAmt() {
        return isChlFeeAmt;
    }

    /**
     * 设置 通道费是否随交易支付
     * @param isChlFeeAmt 通道费是否随交易支付
     */
    public void setIsChlFeeAmt(String isChlFeeAmt) {
        this.isChlFeeAmt = isChlFeeAmt == null ? null : isChlFeeAmt.trim();
    }

    /**
     * 取得 交易金额
     * @return 交易金额
     */
    public BigDecimal getTradeAmt() {
        return tradeAmt;
    }

    /**
     * 设置 交易金额
     * @param tradeAmt 交易金额
     */
    public void setTradeAmt(BigDecimal tradeAmt) {
        this.tradeAmt = tradeAmt;
    }

    /**
     * 取得 登记结算机构
     * @return 登记结算机构
     */
    public String getRegistAgency() {
        return registAgency;
    }

    /**
     * 设置 登记结算机构
     * @param registAgency 登记结算机构
     */
    public void setRegistAgency(String registAgency) {
        this.registAgency = registAgency == null ? null : registAgency.trim();
    }

    /**
     * 取得 有无清算指令
     * @return 有无清算指令
     */
    public String getIsClean() {
        return isClean;
    }

    /**
     * 设置 有无清算指令
     * @param isClean 有无清算指令
     */
    public void setIsClean(String isClean) {
        this.isClean = isClean == null ? null : isClean.trim();
    }

    /**
     * 取得 付款账户
     * @return 付款账户
     */
    public String getMyAcctNo() {
        return myAcctNo;
    }

    /**
     * 设置 付款账户
     * @param myAcctNo 付款账户
     */
    public void setMyAcctNo(String myAcctNo) {
        this.myAcctNo = myAcctNo == null ? null : myAcctNo.trim();
    }

    /**
     * 取得 收款账户
     * @return 收款账户
     */
    public String getCounterAcctNo() {
        return counterAcctNo;
    }

    /**
     * 设置 收款账户
     * @param counterAcctNo 收款账户
     */
    public void setCounterAcctNo(String counterAcctNo) {
        this.counterAcctNo = counterAcctNo == null ? null : counterAcctNo.trim();
    }

    /**
     * 取得 资金交收日
     * @return 资金交收日
     */
    public Date getFundSettleDate() {
        return fundSettleDate;
    }

    /**
     * 设置 资金交收日
     * @param fundSettleDate 资金交收日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setFundSettleDate(Date fundSettleDate) {
        this.fundSettleDate = fundSettleDate;
    }
}