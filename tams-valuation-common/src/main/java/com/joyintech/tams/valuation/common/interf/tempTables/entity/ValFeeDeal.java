package com.joyintech.tams.valuation.common.interf.tempTables.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class ValFeeDeal extends BaseEntity {
    /**
     * 产品代码
     */
    private String prodId;

    /**
     * 数据动作标志
     */
    private String dataActionSign;

    /**
     * 产品名称
     */
    private String prodName;

    /**
     * 成交编号
     */
    private String tradeId;

    /**
     * 费用类型
     */
    private String feeType;

    /**
     * 交易方向
     */
    private String ps;

    /**
     * 交易日
     */
    private Date tradeDate;

    /**
     * 交割日
     */
    private Date settdateDate;

    /**
     * 交易币种
     */
    private String ccy;

    /**
     * 实付费用
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal dealAmt;

    /**
     * 有无清算指令
     */
    private String isClean;

    /**
     * 登记托管机构
     */
    private String registOrg;

    /**
     * 付费截止日
     */
    private Date mdate;

    /**
     * 通道代码
     */
    private String channelId;

    /**
     * 是否作为待摊
     */
    private String isPrepaid;

    /**
     * 待摊结束日
     */
    private Date prepaidMdate;

    /**
     * 待摊开始日
     */
    private Date prepaidVdate;

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
     * 取得 产品名称
     * @return 产品名称
     */
    public String getProdName() {
        return prodName;
    }

    /**
     * 设置 产品名称
     * @param prodName 产品名称
     */
    public void setProdName(String prodName) {
        this.prodName = prodName == null ? null : prodName.trim();
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
     * 取得 费用类型
     * @return 费用类型
     */
    public String getFeeType() {
        return feeType;
    }

    /**
     * 设置 费用类型
     * @param feeType 费用类型
     */
    public void setFeeType(String feeType) {
        this.feeType = feeType == null ? null : feeType.trim();
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
     * 取得 交易日
     * @return 交易日
     */
    public Date getTradeDate() {
        return tradeDate;
    }

    /**
     * 设置 交易日
     * @param tradeDate 交易日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setTradeDate(Date tradeDate) {
        this.tradeDate = tradeDate;
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
     * 取得 交易币种
     * @return 交易币种
     */
    public String getCcy() {
        return ccy;
    }

    /**
     * 设置 交易币种
     * @param ccy 交易币种
     */
    public void setCcy(String ccy) {
        this.ccy = ccy == null ? null : ccy.trim();
    }

    /**
     * 取得 实付费用
     * @return 实付费用
     */
    public BigDecimal getDealAmt() {
        return dealAmt;
    }

    /**
     * 设置 实付费用
     * @param dealAmt 实付费用
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
     * 取得 登记托管机构
     * @return 登记托管机构
     */
    public String getRegistOrg() {
        return registOrg;
    }

    /**
     * 设置 登记托管机构
     * @param registOrg 登记托管机构
     */
    public void setRegistOrg(String registOrg) {
        this.registOrg = registOrg == null ? null : registOrg.trim();
    }

    /**
     * 取得 付费截止日
     * @return 付费截止日
     */
    public Date getMdate() {
        return mdate;
    }

    /**
     * 设置 付费截止日
     * @param mdate 付费截止日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setMdate(Date mdate) {
        this.mdate = mdate;
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
     * 取得 是否作为待摊
     * @return 是否作为待摊
     */
    public String getIsPrepaid() {
        return isPrepaid;
    }

    /**
     * 设置 是否作为待摊
     * @param isPrepaid 是否作为待摊
     */
    public void setIsPrepaid(String isPrepaid) {
        this.isPrepaid = isPrepaid == null ? null : isPrepaid.trim();
    }

    /**
     * 取得 待摊结束日
     * @return 待摊结束日
     */
    public Date getPrepaidMdate() {
        return prepaidMdate;
    }

    /**
     * 设置 待摊结束日
     * @param prepaidMdate 待摊结束日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setPrepaidMdate(Date prepaidMdate) {
        this.prepaidMdate = prepaidMdate;
    }

    /**
     * 取得 待摊开始日
     * @return 待摊开始日
     */
    public Date getPrepaidVdate() {
        return prepaidVdate;
    }

    /**
     * 设置 待摊开始日
     * @param prepaidVdate 待摊开始日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setPrepaidVdate(Date prepaidVdate) {
        this.prepaidVdate = prepaidVdate;
    }
}