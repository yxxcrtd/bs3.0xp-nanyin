package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.util.Date;

public class BokShadowPriceDetail extends BaseEntity {
    /**
     * 方案明细代码
     */
    private String schemeDetailId;

    /**
     * 方案代码
     */
    private String schemeId;

    /**
     * 交易市场
     */
    private String tradeMarket;

    /**
     * 证券品种
     */
    private String secType;

    /**
     * 证券细分类
     */
    private String secDetailType;

    /**
     * 估值市价
     */
    private String valuationPrice;

    /**
     * 估值第二市价
     */
    private String valuationPrice2;

    /**
     * 行情位数
     */
    private Long quotaDigit;

    /**
     * 行情尾数处理
     */
    private String quotaDecType;

    /**
     * 行情日期
     */
    private String valuationDate;

    /**
     * 开始日
     */
    private Date vdate;

    /**
     * 结束日
     */
    private Date mdate;

    /**
     * 价格类型
     */
    private String priceClass;

    /**
     * 取得 方案明细代码
     * @return 方案明细代码
     */
    public String getSchemeDetailId() {
        return schemeDetailId;
    }

    /**
     * 设置 方案明细代码
     * @param schemeDetailId 方案明细代码
     */
    public void setSchemeDetailId(String schemeDetailId) {
        this.schemeDetailId = schemeDetailId == null ? null : schemeDetailId.trim();
    }

    /**
     * 取得 方案代码
     * @return 方案代码
     */
    public String getSchemeId() {
        return schemeId;
    }

    /**
     * 设置 方案代码
     * @param schemeId 方案代码
     */
    public void setSchemeId(String schemeId) {
        this.schemeId = schemeId == null ? null : schemeId.trim();
    }

    /**
     * 取得 交易市场
     * @return 交易市场
     */
    public String getTradeMarket() {
        return tradeMarket;
    }

    /**
     * 设置 交易市场
     * @param tradeMarket 交易市场
     */
    public void setTradeMarket(String tradeMarket) {
        this.tradeMarket = tradeMarket == null ? null : tradeMarket.trim();
    }

    /**
     * 取得 证券品种
     * @return 证券品种
     */
    public String getSecType() {
        return secType;
    }

    /**
     * 设置 证券品种
     * @param secType 证券品种
     */
    public void setSecType(String secType) {
        this.secType = secType == null ? null : secType.trim();
    }

    /**
     * 取得 证券细分类
     * @return 证券细分类
     */
    public String getSecDetailType() {
        return secDetailType;
    }

    /**
     * 设置 证券细分类
     * @param secDetailType 证券细分类
     */
    public void setSecDetailType(String secDetailType) {
        this.secDetailType = secDetailType == null ? null : secDetailType.trim();
    }

    /**
     * 取得 估值市价
     * @return 估值市价
     */
    public String getValuationPrice() {
        return valuationPrice;
    }

    /**
     * 设置 估值市价
     * @param valuationPrice 估值市价
     */
    public void setValuationPrice(String valuationPrice) {
        this.valuationPrice = valuationPrice == null ? null : valuationPrice.trim();
    }

    /**
     * 取得 估值第二市价
     * @return 估值第二市价
     */
    public String getValuationPrice2() {
        return valuationPrice2;
    }

    /**
     * 设置 估值第二市价
     * @param valuationPrice2 估值第二市价
     */
    public void setValuationPrice2(String valuationPrice2) {
        this.valuationPrice2 = valuationPrice2 == null ? null : valuationPrice2.trim();
    }

    /**
     * 取得 行情位数
     * @return 行情位数
     */
    public Long getQuotaDigit() {
        return quotaDigit;
    }

    /**
     * 设置 行情位数
     * @param quotaDigit 行情位数
     */
    public void setQuotaDigit(Long quotaDigit) {
        this.quotaDigit = quotaDigit;
    }

    /**
     * 取得 行情尾数处理
     * @return 行情尾数处理
     */
    public String getQuotaDecType() {
        return quotaDecType;
    }

    /**
     * 设置 行情尾数处理
     * @param quotaDecType 行情尾数处理
     */
    public void setQuotaDecType(String quotaDecType) {
        this.quotaDecType = quotaDecType == null ? null : quotaDecType.trim();
    }

    /**
     * 取得 行情日期
     * @return 行情日期
     */
    public String getValuationDate() {
        return valuationDate;
    }

    /**
     * 设置 行情日期
     * @param valuationDate 行情日期
     */
    public void setValuationDate(String valuationDate) {
        this.valuationDate = valuationDate == null ? null : valuationDate.trim();
    }

    /**
     * 取得 开始日
     * @return 开始日
     */
    public Date getVdate() {
        return vdate;
    }

    /**
     * 设置 开始日
     * @param vdate 开始日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setVdate(Date vdate) {
        this.vdate = vdate;
    }

    /**
     * 取得 结束日
     * @return 结束日
     */
    public Date getMdate() {
        return mdate;
    }

    /**
     * 设置 结束日
     * @param mdate 结束日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setMdate(Date mdate) {
        this.mdate = mdate;
    }

    /**
     * 取得 价格类型
     * @return 价格类型
     */
    public String getPriceClass() {
        return priceClass;
    }

    /**
     * 设置 价格类型
     * @param priceClass 价格类型
     */
    public void setPriceClass(String priceClass) {
        this.priceClass = priceClass == null ? null : priceClass.trim();
    }
}