package com.joyintech.tams.valuation.common.interf.tempTables.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class ValPrdPeriodDealN extends BaseEntity {
    /**
     * 产品代码
     */
    private String prodId;

    /**
     * 产品名称
     */
    private String prodName;

    /**
     * 数据动作标志
     */
    private String dataActionSign;

    /**
     * 成交编号
     */
    private String tradeId;

    /**
     * 交易类型
     */
    private String dealType;

    /**
     * 交易日
     */
    private Date dealDate;

    /**
     * 业务日期
     */
    private Date busiDate;

    /**
     * 交易金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal dealAmt;

    /**
     * 交易份额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal tradeQty;

    /**
     * 单位净值
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal unitValue;

    /**
     * 净值日
     */
    private Date netDate;

    /**
     * 费用
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal fee;

    /**
     * 币种
     */
    private String ccy;

    /**
     * 有无清算指令
     */
    private String isClean;

    /**
     * 金融产品类型
     */
    private String finprodType;

    /**
     * 产品起息日
     */
    private Date prodVdate;

    /**
     * 费用留存收入
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal retainFee;

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
     * 取得 交易类型
     * @return 交易类型
     */
    public String getDealType() {
        return dealType;
    }

    /**
     * 设置 交易类型
     * @param dealType 交易类型
     */
    public void setDealType(String dealType) {
        this.dealType = dealType == null ? null : dealType.trim();
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
     * 取得 业务日期
     * @return 业务日期
     */
    public Date getBusiDate() {
        return busiDate;
    }

    /**
     * 设置 业务日期
     * @param busiDate 业务日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setBusiDate(Date busiDate) {
        this.busiDate = busiDate;
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
     * 取得 交易份额
     * @return 交易份额
     */
    public BigDecimal getTradeQty() {
        return tradeQty;
    }

    /**
     * 设置 交易份额
     * @param tradeQty 交易份额
     */
    public void setTradeQty(BigDecimal tradeQty) {
        this.tradeQty = tradeQty;
    }

    /**
     * 取得 单位净值
     * @return 单位净值
     */
    public BigDecimal getUnitValue() {
        return unitValue;
    }

    /**
     * 设置 单位净值
     * @param unitValue 单位净值
     */
    public void setUnitValue(BigDecimal unitValue) {
        this.unitValue = unitValue;
    }

    /**
     * 取得 净值日
     * @return 净值日
     */
    public Date getNetDate() {
        return netDate;
    }

    /**
     * 设置 净值日
     * @param netDate 净值日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setNetDate(Date netDate) {
        this.netDate = netDate;
    }

    /**
     * 取得 费用
     * @return 费用
     */
    public BigDecimal getFee() {
        return fee;
    }

    /**
     * 设置 费用
     * @param fee 费用
     */
    public void setFee(BigDecimal fee) {
        this.fee = fee;
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
     * 取得 产品起息日
     * @return 产品起息日
     */
    public Date getProdVdate() {
        return prodVdate;
    }

    /**
     * 设置 产品起息日
     * @param prodVdate 产品起息日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setProdVdate(Date prodVdate) {
        this.prodVdate = prodVdate;
    }

    /**
     * 取得 费用留存收入
     * @return 费用留存收入
     */
    public BigDecimal getRetainFee() {
        return retainFee;
    }

    /**
     * 设置 费用留存收入
     * @param retainFee 费用留存收入
     */
    public void setRetainFee(BigDecimal retainFee) {
        this.retainFee = retainFee;
    }
}