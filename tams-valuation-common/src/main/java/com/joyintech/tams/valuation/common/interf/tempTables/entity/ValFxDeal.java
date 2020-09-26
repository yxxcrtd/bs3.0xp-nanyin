package com.joyintech.tams.valuation.common.interf.tempTables.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class ValFxDeal extends BaseEntity {
    /**
     * 产品代码
     */
    private String prodId;

    /**
     * 通道代码
     */
    private String channelId;

    /**
     * 数据动作标志
     */
    private String dataActionSign;

    /**
     * 逾期资产代码
     */
    private String assetCode;

    /**
     * 逾期资产类型
     */
    private String finprodType;

    /**
     * 是否结清
     */
    private String isClean2;

    /**
     * 投资目的
     */
    private String investAim;

    /**
     * 罚息金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal tradeAmt;

    /**
     * 分支序号
     */
    private Long branch;

    /**
     * 付息截止日
     */
    private Date mdate;

    /**
     * 交易日
     */
    private Date dealDate;

    /**
     * 交割日期
     */
    private Date effDate;

    /**
     * 币种
     */
    private String ccy;

    /**
     * 成交编号
     */
    private String tradeId;

    /**
     * 交易方向
     */
    private String ps;

    /**
     * 通道费
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal chlFeeAmt;

    /**
     * 有无清算指令
     */
    private String isClean;

    /**
     * 通道费是否随交易支付
     */
    private String isChlFeeAmt;

    /**
     * 交易场所
     */
    private String tradeMarket;

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
    private Date settleDate;

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
     * 取得 通道代码
     * @return 通道代码
     */
    public String getChannelId() {
        return channelId;
    }

    /**
     * 设置 通道代码
     * @param channelId 通道代码
     */
    public void setChannelId(String channelId) {
        this.channelId = channelId == null ? null : channelId.trim();
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
     * 取得 逾期资产代码
     * @return 逾期资产代码
     */
    public String getAssetCode() {
        return assetCode;
    }

    /**
     * 设置 逾期资产代码
     * @param assetCode 逾期资产代码
     */
    public void setAssetCode(String assetCode) {
        this.assetCode = assetCode == null ? null : assetCode.trim();
    }

    /**
     * 取得 逾期资产类型
     * @return 逾期资产类型
     */
    public String getFinprodType() {
        return finprodType;
    }

    /**
     * 设置 逾期资产类型
     * @param finprodType 逾期资产类型
     */
    public void setFinprodType(String finprodType) {
        this.finprodType = finprodType == null ? null : finprodType.trim();
    }

    /**
     * 取得 是否结清
     * @return 是否结清
     */
    public String getIsClean2() {
        return isClean2;
    }

    /**
     * 设置 是否结清
     * @param isClean2 是否结清
     */
    public void setIsClean2(String isClean2) {
        this.isClean2 = isClean2 == null ? null : isClean2.trim();
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
     * 取得 罚息金额
     * @return 罚息金额
     */
    public BigDecimal getTradeAmt() {
        return tradeAmt;
    }

    /**
     * 设置 罚息金额
     * @param tradeAmt 罚息金额
     */
    public void setTradeAmt(BigDecimal tradeAmt) {
        this.tradeAmt = tradeAmt;
    }

    /**
     * 取得 分支序号
     * @return 分支序号
     */
    public Long getBranch() {
        return branch;
    }

    /**
     * 设置 分支序号
     * @param branch 分支序号
     */
    public void setBranch(Long branch) {
        this.branch = branch;
    }

    /**
     * 取得 付息截止日
     * @return 付息截止日
     */
    public Date getMdate() {
        return mdate;
    }

    /**
     * 设置 付息截止日
     * @param mdate 付息截止日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setMdate(Date mdate) {
        this.mdate = mdate;
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
     * 取得 交割日期
     * @return 交割日期
     */
    public Date getEffDate() {
        return effDate;
    }

    /**
     * 设置 交割日期
     * @param effDate 交割日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setEffDate(Date effDate) {
        this.effDate = effDate;
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
    public Date getSettleDate() {
        return settleDate;
    }

    /**
     * 设置 资金交收日
     * @param settleDate 资金交收日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setSettleDate(Date settleDate) {
        this.settleDate = settleDate;
    }
}