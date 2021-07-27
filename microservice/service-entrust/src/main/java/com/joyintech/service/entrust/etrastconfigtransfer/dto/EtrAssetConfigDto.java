package com.joyintech.service.entrust.etrastconfigtransfer.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.tams.common.base.entity.EtrEntrust;

import java.math.BigDecimal;
import java.util.Date;


public class EtrAssetConfigDto extends EtrEntrust {

    private static final long serialVersionUID = 9222570679406156110L;

    /**
     * 金融产品简称
     */
    private String finprodAbbr;

    /**
     * 买入成本
     */
    private BigDecimal buyCost;

    /**
     * 估价净价
     */
    private BigDecimal netCprice;

    /**
     * 行权日期
     */
    private Date exerciseDate;

    /**
     * 券商
     */
    private String acctName;

    /**
     * 债券一级分类
     */
    private String type6;


    /**
     * 收益率(债券票面利率)
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal rate;

    /**
     * 持仓券面面额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal amount;


    /**
     * 价格偏离度
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal deviation;

    /**
     * 占比
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal radio;

    /**
     * 证券账户
     */
    private String secAcctId;

    public String getFinprodAbbr() {
        return finprodAbbr;
    }

    public void setFinprodAbbr(String finprodAbbr) {
        this.finprodAbbr = finprodAbbr;
    }

    public BigDecimal getBuyCost() {
        return buyCost;
    }

    public void setBuyCost(BigDecimal buyCost) {
        this.buyCost = buyCost;
    }

    public BigDecimal getNetCprice() {
        return netCprice;
    }

    public void setNetCprice(BigDecimal netCprice) {
        this.netCprice = netCprice;
    }

    public Date getExerciseDate() {
        return exerciseDate;
    }

    public void setExerciseDate(Date exerciseDate) {
        this.exerciseDate = exerciseDate;
    }

    public String getAcctName() {
        return acctName;
    }

    public void setAcctName(String acctName) {
        this.acctName = acctName;
    }

    public String getType6() {
        return type6;
    }

    public void setType6(String type6) {
        this.type6 = type6;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getDeviation() {
        return deviation;
    }

    public void setDeviation(BigDecimal deviation) {
        this.deviation = deviation;
    }

    public BigDecimal getRadio() {
        return radio;
    }

    public void setRadio(BigDecimal radio) {
        this.radio = radio;
    }

    public String getSecAcctId() {
        return secAcctId;
    }

    public void setSecAcctId(String secAcctId) {
        this.secAcctId = secAcctId;
    }

}
