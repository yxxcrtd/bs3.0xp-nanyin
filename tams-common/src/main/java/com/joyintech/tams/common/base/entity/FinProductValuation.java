package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class FinProductValuation extends FinProductValuationKey {
    /**
     * 金融产品类型（估值核算），债券、理财产品、基金、理财产品模板、理财产品分层
     */
    private String finprodType;

    /**
     * 金融产品类型（投管分类），债券、理财产品、基金、理财产品模板、理财产品分层
     */
    private String finprodType2;

    /**
     * 公布日期
     */
    private Date publishDate;

    /**
     * 每日估值，净值、万份收益等
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal valuation;

    /**
     * 七日年化收益率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal day7YearYield;

    /**
     * 年化收益率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal yearYield;

    /**
     * 累计净值
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal totalValue;

    /**
     * 录入方式，手工维护、接口导入
     */
    private String inputType;

    /**
     * 备注
     */
    private String remark;

    /**
     * 复权单位净值
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal restNetValue;

    /**
     * 基金总份额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal cShares;

    /**
     * 取得 金融产品类型（估值核算），债券、理财产品、基金、理财产品模板、理财产品分层
     * @return 金融产品类型（估值核算），债券、理财产品、基金、理财产品模板、理财产品分层
     */
    public String getFinprodType() {
        return finprodType;
    }

    /**
     * 设置 金融产品类型（估值核算），债券、理财产品、基金、理财产品模板、理财产品分层
     * @param finprodType 金融产品类型（估值核算），债券、理财产品、基金、理财产品模板、理财产品分层
     */
    public void setFinprodType(String finprodType) {
        this.finprodType = finprodType == null ? null : finprodType.trim();
    }

    /**
     * 取得 金融产品类型（投管分类），债券、理财产品、基金、理财产品模板、理财产品分层
     * @return 金融产品类型（投管分类），债券、理财产品、基金、理财产品模板、理财产品分层
     */
    public String getFinprodType2() {
        return finprodType2;
    }

    /**
     * 设置 金融产品类型（投管分类），债券、理财产品、基金、理财产品模板、理财产品分层
     * @param finprodType2 金融产品类型（投管分类），债券、理财产品、基金、理财产品模板、理财产品分层
     */
    public void setFinprodType2(String finprodType2) {
        this.finprodType2 = finprodType2 == null ? null : finprodType2.trim();
    }

    /**
     * 取得 公布日期
     * @return 公布日期
     */
    public Date getPublishDate() {
        return publishDate;
    }

    /**
     * 设置 公布日期
     * @param publishDate 公布日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    /**
     * 取得 每日估值，净值、万份收益等
     * @return 每日估值，净值、万份收益等
     */
    public BigDecimal getValuation() {
        return valuation;
    }

    /**
     * 设置 每日估值，净值、万份收益等
     * @param valuation 每日估值，净值、万份收益等
     */
    public void setValuation(BigDecimal valuation) {
        this.valuation = valuation;
    }

    /**
     * 取得 七日年化收益率
     * @return 七日年化收益率
     */
    public BigDecimal getDay7YearYield() {
        return day7YearYield;
    }

    /**
     * 设置 七日年化收益率
     * @param day7YearYield 七日年化收益率
     */
    public void setDay7YearYield(BigDecimal day7YearYield) {
        this.day7YearYield = day7YearYield;
    }

    /**
     * 取得 年化收益率
     * @return 年化收益率
     */
    public BigDecimal getYearYield() {
        return yearYield;
    }

    /**
     * 设置 年化收益率
     * @param yearYield 年化收益率
     */
    public void setYearYield(BigDecimal yearYield) {
        this.yearYield = yearYield;
    }

    /**
     * 取得 累计净值
     * @return 累计净值
     */
    public BigDecimal getTotalValue() {
        return totalValue;
    }

    /**
     * 设置 累计净值
     * @param totalValue 累计净值
     */
    public void setTotalValue(BigDecimal totalValue) {
        this.totalValue = totalValue;
    }

    /**
     * 取得 录入方式，手工维护、接口导入
     * @return 录入方式，手工维护、接口导入
     */
    public String getInputType() {
        return inputType;
    }

    /**
     * 设置 录入方式，手工维护、接口导入
     * @param inputType 录入方式，手工维护、接口导入
     */
    public void setInputType(String inputType) {
        this.inputType = inputType == null ? null : inputType.trim();
    }

    /**
     * 取得 备注
     * @return 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置 备注
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * 取得 复权单位净值
     * @return 复权单位净值
     */
    public BigDecimal getRestNetValue() {
        return restNetValue;
    }

    /**
     * 设置 复权单位净值
     * @param restNetValue 复权单位净值
     */
    public void setRestNetValue(BigDecimal restNetValue) {
        this.restNetValue = restNetValue;
    }

    /**
     * 取得 基金总份额
     * @return 基金总份额
     */
    public BigDecimal getcShares() {
        return cShares;
    }

    /**
     * 设置 基金总份额
     * @param cShares 基金总份额
     */
    public void setcShares(BigDecimal cShares) {
        this.cShares = cShares;
    }
}