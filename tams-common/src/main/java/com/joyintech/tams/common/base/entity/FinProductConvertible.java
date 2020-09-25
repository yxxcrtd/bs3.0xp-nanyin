package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;

import java.math.BigDecimal;
import java.util.Date;

public class FinProductConvertible extends BaseEntity {
    /**
     * 金融产品代码
     */
    private String finprodId;

    /**
     * 转股产品代码
     */
    private String conFinprodId;

    /**
     * 转股代码
     */
    private String conShareId;

    /**
     * 转股起始日
     */
    private Date conVdate;

    /**
     * 转股价格
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal conPrice;

    /**
     * 回售触发价
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal resalePrice;

    /**
     * 赎回触发价
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal redeemPrice;

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
     * 取得 转股产品代码
     * @return 转股产品代码
     */
    public String getConFinprodId() {
        return conFinprodId;
    }

    /**
     * 设置 转股产品代码
     * @param conFinprodId 转股产品代码
     */
    public void setConFinprodId(String conFinprodId) {
        this.conFinprodId = conFinprodId == null ? null : conFinprodId.trim();
    }

    /**
     * 取得 转股代码
     * @return 转股代码
     */
    public String getConShareId() {
        return conShareId;
    }

    /**
     * 设置 转股代码
     * @param conShareId 转股代码
     */
    public void setConShareId(String conShareId) {
        this.conShareId = conShareId == null ? null : conShareId.trim();
    }

    /**
     * 取得 转股起始日
     * @return 转股起始日
     */
    public Date getConVdate() {
        return conVdate;
    }

    /**
     * 设置 转股起始日
     * @param conVdate 转股起始日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setConVdate(Date conVdate) {
        this.conVdate = conVdate;
    }

    /**
     * 取得 转股价格
     * @return 转股价格
     */
    public BigDecimal getConPrice() {
        return conPrice;
    }

    /**
     * 设置 转股价格
     * @param conPrice 转股价格
     */
    public void setConPrice(BigDecimal conPrice) {
        this.conPrice = conPrice;
    }

    /**
     * 取得 回售触发价
     * @return 回售触发价
     */
    public BigDecimal getResalePrice() {
        return resalePrice;
    }

    /**
     * 设置 回售触发价
     * @param resalePrice 回售触发价
     */
    public void setResalePrice(BigDecimal resalePrice) {
        this.resalePrice = resalePrice;
    }

    /**
     * 取得 赎回触发价
     * @return 赎回触发价
     */
    public BigDecimal getRedeemPrice() {
        return redeemPrice;
    }

    /**
     * 设置 赎回触发价
     * @param redeemPrice 赎回触发价
     */
    public void setRedeemPrice(BigDecimal redeemPrice) {
        this.redeemPrice = redeemPrice;
    }
}