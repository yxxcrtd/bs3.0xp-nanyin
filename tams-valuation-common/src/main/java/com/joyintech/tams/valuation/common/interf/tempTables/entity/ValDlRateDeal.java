package com.joyintech.tams.valuation.common.interf.tempTables.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class ValDlRateDeal extends BaseEntity {
    /**
     * 产品代码
     */
    private String prodId;

    /**
     * 拆借标的代码
     */
    private String dlId;

    /**
     * 新增、撤销
     */
    private String dataActionSign;

    /**
     * 交易方向
     */
    private String ps;

    /**
     * 业务类型为拆入到期、拆出到期时，必填
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal actInt;

    /**
     * 交易金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal dealAmt;

    /**
     * 计息开始日
     */
    private Date vdate;

    /**
     * 计息结束日
     */
    private Date mdate;

    /**
     * 交割日
     */
    private Date settleDate;

    /**
     * 交易日
     */
    private Date dealDate;

    /**
     * 是否结清
     */
    private String isClean2;

    /**
     * 币种
     */
    private String ccy;

    /**
     * 资管系统的交易编号
     */
    private String tradeId;

    /**
     * 有无清算指令
     */
    private String isClean;

    /**
     * 业务类型
     */
    private String busiType;

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
     * 取得 拆借标的代码
     * @return 拆借标的代码
     */
    public String getDlId() {
        return dlId;
    }

    /**
     * 设置 拆借标的代码
     * @param dlId 拆借标的代码
     */
    public void setDlId(String dlId) {
        this.dlId = dlId == null ? null : dlId.trim();
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
     * 取得 业务类型为拆入到期、拆出到期时，必填
     * @return 业务类型为拆入到期、拆出到期时，必填
     */
    public BigDecimal getActInt() {
        return actInt;
    }

    /**
     * 设置 业务类型为拆入到期、拆出到期时，必填
     * @param actInt 业务类型为拆入到期、拆出到期时，必填
     */
    public void setActInt(BigDecimal actInt) {
        this.actInt = actInt;
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
     * 取得 计息开始日
     * @return 计息开始日
     */
    public Date getVdate() {
        return vdate;
    }

    /**
     * 设置 计息开始日
     * @param vdate 计息开始日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setVdate(Date vdate) {
        this.vdate = vdate;
    }

    /**
     * 取得 计息结束日
     * @return 计息结束日
     */
    public Date getMdate() {
        return mdate;
    }

    /**
     * 设置 计息结束日
     * @param mdate 计息结束日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setMdate(Date mdate) {
        this.mdate = mdate;
    }

    /**
     * 取得 交割日
     * @return 交割日
     */
    public Date getSettleDate() {
        return settleDate;
    }

    /**
     * 设置 交割日
     * @param settleDate 交割日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setSettleDate(Date settleDate) {
        this.settleDate = settleDate;
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
     * 取得 资管系统的交易编号
     * @return 资管系统的交易编号
     */
    public String getTradeId() {
        return tradeId;
    }

    /**
     * 设置 资管系统的交易编号
     * @param tradeId 资管系统的交易编号
     */
    public void setTradeId(String tradeId) {
        this.tradeId = tradeId == null ? null : tradeId.trim();
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
     * 取得 业务类型
     * @return 业务类型
     */
    public String getBusiType() {
        return busiType;
    }

    /**
     * 设置 业务类型
     * @param busiType 业务类型
     */
    public void setBusiType(String busiType) {
        this.busiType = busiType == null ? null : busiType.trim();
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