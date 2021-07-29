package com.joyintech.tams.common.base.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;

public class EtrEntrustWw extends BaseEntity {
    /**
     * 委托代码
     */
    private String entrustId;

    /**
     * 组合代码
     */
    private String portfolioId;

    /**
     * 金融产品代码
     */
    private String finprodId;

    /**
     * 金融产品类型（投管分类）
     */
    private String finprodType2;

    /**
     * 交易方向
     */
    private String ps;

    /**
     * 币种
     */
    private String ccy;

    /**
     * 指令日期
     */
    private Date entrustDate;

    /**
     * 指令金额
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal entrustAmt;

    /**
     * 委托状态
     */
    private String entrustStatus;

    /**
     * 取得 委托代码
     * @return 委托代码
     */
    public String getEntrustId() {
        return entrustId;
    }

    /**
     * 设置 委托代码
     * @param entrustId 委托代码
     */
    public void setEntrustId(String entrustId) {
        this.entrustId = entrustId == null ? null : entrustId.trim();
    }

    /**
     * 取得 组合代码
     * @return 组合代码
     */
    public String getPortfolioId() {
        return portfolioId;
    }

    /**
     * 设置 组合代码
     * @param portfolioId 组合代码
     */
    public void setPortfolioId(String portfolioId) {
        this.portfolioId = portfolioId == null ? null : portfolioId.trim();
    }

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
     * 取得 金融产品类型（投管分类）
     * @return 金融产品类型（投管分类）
     */
    public String getFinprodType2() {
        return finprodType2;
    }

    /**
     * 设置 金融产品类型（投管分类）
     * @param finprodType2 金融产品类型（投管分类）
     */
    public void setFinprodType2(String finprodType2) {
        this.finprodType2 = finprodType2 == null ? null : finprodType2.trim();
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
     * 取得 指令日期
     * @return 指令日期
     */
    public Date getEntrustDate() {
        return entrustDate;
    }

    /**
     * 设置 指令日期
     * @param entrustDate 指令日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setEntrustDate(Date entrustDate) {
        this.entrustDate = entrustDate;
    }

    /**
     * 取得 指令金额
     * @return 指令金额
     */
    public BigDecimal getEntrustAmt() {
        return entrustAmt;
    }

    /**
     * 设置 指令金额
     * @param entrustAmt 指令金额
     */
    public void setEntrustAmt(BigDecimal entrustAmt) {
        this.entrustAmt = entrustAmt;
    }

    /**
     * 取得 委托状态
     * @return 委托状态
     */
    public String getEntrustStatus() {
        return entrustStatus;
    }

    /**
     * 设置 委托状态
     * @param entrustStatus 委托状态
     */
    public void setEntrustStatus(String entrustStatus) {
        this.entrustStatus = entrustStatus == null ? null : entrustStatus.trim();
    }
}