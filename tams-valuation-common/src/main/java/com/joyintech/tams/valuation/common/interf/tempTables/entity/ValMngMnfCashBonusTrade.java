package com.joyintech.tams.valuation.common.interf.tempTables.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class ValMngMnfCashBonusTrade extends BaseEntity {
    /**
     * 产品代码
     */
    private String prodId;

    /**
     * 数据动作标志
     */
    private String dataActionSign;

    /**
     * 交易编号
     */
    private String tradeId;

    /**
     * 交易方向
     */
    private String ps;

    /**
     * 资产代码
     */
    private String assetId;

    /**
     * 资产名称
     */
    private String assetName;

    /**
     * 现金分红为现金分红日，红利再投为红利再投日，到期为发生日
     */
    private Date bonusDate;

    /**
     * 分红截止日
     */
    private Date mdate;

    /**
     * 交易金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal actBounsAmt;

    /**
     * 交易份额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal cfmShare;

    /**
     * 单位分红
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal bonusNet;

    /**
     * 币种
     */
    private String ccy;

    /**
     * 有无清算指令
     */
    private String isClean;

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
     * 通道代码
     */
    private String channelId;

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
     * 取得 交易编号
     * @return 交易编号
     */
    public String getTradeId() {
        return tradeId;
    }

    /**
     * 设置 交易编号
     * @param tradeId 交易编号
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
     * 取得 资产代码
     * @return 资产代码
     */
    public String getAssetId() {
        return assetId;
    }

    /**
     * 设置 资产代码
     * @param assetId 资产代码
     */
    public void setAssetId(String assetId) {
        this.assetId = assetId == null ? null : assetId.trim();
    }

    /**
     * 取得 资产名称
     * @return 资产名称
     */
    public String getAssetName() {
        return assetName;
    }

    /**
     * 设置 资产名称
     * @param assetName 资产名称
     */
    public void setAssetName(String assetName) {
        this.assetName = assetName == null ? null : assetName.trim();
    }

    /**
     * 取得 现金分红为现金分红日，红利再投为红利再投日，到期为发生日
     * @return 现金分红为现金分红日，红利再投为红利再投日，到期为发生日
     */
    public Date getBonusDate() {
        return bonusDate;
    }

    /**
     * 设置 现金分红为现金分红日，红利再投为红利再投日，到期为发生日
     * @param bonusDate 现金分红为现金分红日，红利再投为红利再投日，到期为发生日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setBonusDate(Date bonusDate) {
        this.bonusDate = bonusDate;
    }

    /**
     * 取得 分红截止日
     * @return 分红截止日
     */
    public Date getMdate() {
        return mdate;
    }

    /**
     * 设置 分红截止日
     * @param mdate 分红截止日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setMdate(Date mdate) {
        this.mdate = mdate;
    }

    /**
     * 取得 交易金额
     * @return 交易金额
     */
    public BigDecimal getActBounsAmt() {
        return actBounsAmt;
    }

    /**
     * 设置 交易金额
     * @param actBounsAmt 交易金额
     */
    public void setActBounsAmt(BigDecimal actBounsAmt) {
        this.actBounsAmt = actBounsAmt;
    }

    /**
     * 取得 交易份额
     * @return 交易份额
     */
    public BigDecimal getCfmShare() {
        return cfmShare;
    }

    /**
     * 设置 交易份额
     * @param cfmShare 交易份额
     */
    public void setCfmShare(BigDecimal cfmShare) {
        this.cfmShare = cfmShare;
    }

    /**
     * 取得 单位分红
     * @return 单位分红
     */
    public BigDecimal getBonusNet() {
        return bonusNet;
    }

    /**
     * 设置 单位分红
     * @param bonusNet 单位分红
     */
    public void setBonusNet(BigDecimal bonusNet) {
        this.bonusNet = bonusNet;
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
}