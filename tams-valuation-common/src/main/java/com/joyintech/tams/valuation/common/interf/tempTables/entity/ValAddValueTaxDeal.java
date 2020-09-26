package com.joyintech.tams.valuation.common.interf.tempTables.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class ValAddValueTaxDeal extends BaseEntity {
    /**
     * 产品代码
     */
    private String prodId;

    /**
     * 新增、撤销
     */
    private String dataActionSign;

    /**
     * 交易编号
     */
    private String tradeId;

    /**
     * 交易类型
     */
    private String busiType;

    /**
     * null
     */
    private String ps;

    /**
     * 业务日期
     */
    private Date tradeDate;

    /**
     * null
     */
    private Date settdateDate;

    /**
     * null
     */
    private Date vdate;

    /**
     * null
     */
    private Date mdate;

    /**
     * 默认：人民币
     */
    private String ccy;

    /**
     * null
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal intTaxAmt;

    /**
     * null
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal divTaxAmt;

    /**
     * null
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal sprTaxAmt;

    /**
     * null
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal addTaxAmt;

    /**
     * null
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal eduTaxAmt;

    /**
     * null
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal aleTaxAmt;

    /**
     * 交易金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal dealAmt;

    /**
     * 有无清算指令
     */
    private String isClean;

    /**
     * 通道代码
     */
    private String channelId;

    /**
     * 水利基金附加
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal wcpTaxAmt;

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
     * 取得 新增、撤销
     * @return 新增、撤销
     */
    public String getDataActionSign() {
        return dataActionSign;
    }

    /**
     * 设置 新增、撤销
     * @param dataActionSign 新增、撤销
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
     * 取得 交易类型
     * @return 交易类型
     */
    public String getBusiType() {
        return busiType;
    }

    /**
     * 设置 交易类型
     * @param busiType 交易类型
     */
    public void setBusiType(String busiType) {
        this.busiType = busiType == null ? null : busiType.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public String getPs() {
        return ps;
    }

    /**
     * 设置 null
     * @param ps null
     */
    public void setPs(String ps) {
        this.ps = ps == null ? null : ps.trim();
    }

    /**
     * 取得 业务日期
     * @return 业务日期
     */
    public Date getTradeDate() {
        return tradeDate;
    }

    /**
     * 设置 业务日期
     * @param tradeDate 业务日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setTradeDate(Date tradeDate) {
        this.tradeDate = tradeDate;
    }

    /**
     * 取得 null
     * @return null
     */
    public Date getSettdateDate() {
        return settdateDate;
    }

    /**
     * 设置 null
     * @param settdateDate null
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setSettdateDate(Date settdateDate) {
        this.settdateDate = settdateDate;
    }

    /**
     * 取得 null
     * @return null
     */
    public Date getVdate() {
        return vdate;
    }

    /**
     * 设置 null
     * @param vdate null
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setVdate(Date vdate) {
        this.vdate = vdate;
    }

    /**
     * 取得 null
     * @return null
     */
    public Date getMdate() {
        return mdate;
    }

    /**
     * 设置 null
     * @param mdate null
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setMdate(Date mdate) {
        this.mdate = mdate;
    }

    /**
     * 取得 默认：人民币
     * @return 默认：人民币
     */
    public String getCcy() {
        return ccy;
    }

    /**
     * 设置 默认：人民币
     * @param ccy 默认：人民币
     */
    public void setCcy(String ccy) {
        this.ccy = ccy == null ? null : ccy.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public BigDecimal getIntTaxAmt() {
        return intTaxAmt;
    }

    /**
     * 设置 null
     * @param intTaxAmt null
     */
    public void setIntTaxAmt(BigDecimal intTaxAmt) {
        this.intTaxAmt = intTaxAmt;
    }

    /**
     * 取得 null
     * @return null
     */
    public BigDecimal getDivTaxAmt() {
        return divTaxAmt;
    }

    /**
     * 设置 null
     * @param divTaxAmt null
     */
    public void setDivTaxAmt(BigDecimal divTaxAmt) {
        this.divTaxAmt = divTaxAmt;
    }

    /**
     * 取得 null
     * @return null
     */
    public BigDecimal getSprTaxAmt() {
        return sprTaxAmt;
    }

    /**
     * 设置 null
     * @param sprTaxAmt null
     */
    public void setSprTaxAmt(BigDecimal sprTaxAmt) {
        this.sprTaxAmt = sprTaxAmt;
    }

    /**
     * 取得 null
     * @return null
     */
    public BigDecimal getAddTaxAmt() {
        return addTaxAmt;
    }

    /**
     * 设置 null
     * @param addTaxAmt null
     */
    public void setAddTaxAmt(BigDecimal addTaxAmt) {
        this.addTaxAmt = addTaxAmt;
    }

    /**
     * 取得 null
     * @return null
     */
    public BigDecimal getEduTaxAmt() {
        return eduTaxAmt;
    }

    /**
     * 设置 null
     * @param eduTaxAmt null
     */
    public void setEduTaxAmt(BigDecimal eduTaxAmt) {
        this.eduTaxAmt = eduTaxAmt;
    }

    /**
     * 取得 null
     * @return null
     */
    public BigDecimal getAleTaxAmt() {
        return aleTaxAmt;
    }

    /**
     * 设置 null
     * @param aleTaxAmt null
     */
    public void setAleTaxAmt(BigDecimal aleTaxAmt) {
        this.aleTaxAmt = aleTaxAmt;
    }

    /**
     * 取得 交易金额
     * @return 交易金额
     */
    public BigDecimal getDealAmt() {
        return dealAmt;
    }

    /**
     * 设置 交易金额
     * @param dealAmt 交易金额
     */
    public void setDealAmt(BigDecimal dealAmt) {
        this.dealAmt = dealAmt;
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
     * 取得 水利基金附加
     * @return 水利基金附加
     */
    public BigDecimal getWcpTaxAmt() {
        return wcpTaxAmt;
    }

    /**
     * 设置 水利基金附加
     * @param wcpTaxAmt 水利基金附加
     */
    public void setWcpTaxAmt(BigDecimal wcpTaxAmt) {
        this.wcpTaxAmt = wcpTaxAmt;
    }
}