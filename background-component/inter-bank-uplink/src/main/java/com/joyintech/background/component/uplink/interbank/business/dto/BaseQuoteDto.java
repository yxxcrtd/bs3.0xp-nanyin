/*
 * 文件名：baseQuoteDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：caoguo
 * 修改时间：2020年5月7日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.background.component.uplink.interbank.business.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.background.component.common.pack.common.json.BigDecimalSerializer;

/**
 * 基础报价信息，做为处理结果方法返回 〈功能详细描述〉
 * 
 * @author caoguo
 * @version 1.0 2020年5月7日
 * @see BaseQuoteDto
 * @since 1.0
 */
public class BaseQuoteDto {

    /**
     * 报价编号（内部代码）
     */
    private String quoteId;

    /**
     * 报价类型，对话报价/请求报价/质押式回购报价
     */
    private String quoteType;

    /**
     * 本方角色
     */
    private String quoteRoleType;

    /**
     * 外部报价编号
     */
    private String pQuoteId;

    /**
     * 金融产品代码
     */
    private String finprodId;

    /**
     * 金融产品类型（投管分类），债券、理财产品、基金、理财产品模板、理财产品分层
     */
    private String finprodType2;

    /**
     * 分支序号
     */
    private Long branch;

    /**
     * 组合代码
     */
    private String portfolioId;

    /**
     * 委托代码
     */
    private String entrustId;

    /**
     * 报价日期
     */
    private Date quoteDate;

    /**
     * 结算币种
     */
    private String settCcy;

    /**
     * 报价状态，新增中/已新增/修改中/已修改/撤回中/已撤回/成交中/已成交/终止中/已终止/待回复/已撤销/
     */
    private String tradeStatus;

    /**
     * 发送状态，待发送/成功/失败/发送中
     */
    private String sendStatus;

    /**
     * 有效时间
     */
    private Date effTime;

    /**
     * 本方银行间机构，6位编号
     */
    private String ouPartyId;

    /**
     * 本方银行间交易员
     */
    private String ouTraderId;

    /**
     * 交易份额，债券数量等
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal shareAmt;

    /**
     * 交易本金，交易成本等
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal prinAmt;

    /**
     * 净价总额
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal cpriceAmt;

    /**
     * 利息总额
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal intAmt;

    /**
     * 全价总额
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal fpriceAmt;

    /**
     * 交易方向，买入、卖出、收息、还本、申购、赎回等
     */
    private String ps;

    /**
     * 到期收益率
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal yield;

    /**
     * 行权收益率
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal exerYield;

    /**
     * 获取 quoteId
     * 
     * @return quoteId.
     */
    public String getQuoteId() {
        return quoteId;
    }

    /**
     * 设置 quoteId
     * 
     * @param quoteId quoteId
     */
    public void setQuoteId(String quoteId) {
        this.quoteId = quoteId;
    }

    /**
     * 获取 quoteType
     * 
     * @return quoteType.
     */
    public String getQuoteType() {
        return quoteType;
    }

    /**
     * 设置 quoteType
     * 
     * @param quoteType quoteType
     */
    public void setQuoteType(String quoteType) {
        this.quoteType = quoteType;
    }

    /**
     * 获取 quoteRoleType
     * 
     * @return quoteRoleType.
     */
    public String getQuoteRoleType() {
        return quoteRoleType;
    }

    /**
     * 设置 quoteRoleType
     * 
     * @param quoteRoleType quoteRoleType
     */
    public void setQuoteRoleType(String quoteRoleType) {
        this.quoteRoleType = quoteRoleType;
    }

    /**
     * 获取 pQuoteId
     * 
     * @return pQuoteId.
     */
    public String getpQuoteId() {
        return pQuoteId;
    }

    /**
     * 设置 pQuoteId
     * 
     * @param pQuoteId pQuoteId
     */
    public void setpQuoteId(String pQuoteId) {
        this.pQuoteId = pQuoteId;
    }

    /**
     * 获取 finprodId
     * 
     * @return finprodId.
     */
    public String getFinprodId() {
        return finprodId;
    }

    /**
     * 设置 finprodId
     * 
     * @param finprodId finprodId
     */
    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
    }

    /**
     * 获取 finprodType2
     * 
     * @return finprodType2.
     */
    public String getFinprodType2() {
        return finprodType2;
    }

    /**
     * 设置 finprodType2
     * 
     * @param finprodType2 finprodType2
     */
    public void setFinprodType2(String finprodType2) {
        this.finprodType2 = finprodType2;
    }

    /**
     * 获取 branch
     * 
     * @return branch.
     */
    public Long getBranch() {
        return branch;
    }

    /**
     * 设置 branch
     * 
     * @param branch branch
     */
    public void setBranch(Long branch) {
        this.branch = branch;
    }

    /**
     * 获取 portfolioId
     * 
     * @return portfolioId.
     */
    public String getPortfolioId() {
        return portfolioId;
    }

    /**
     * 设置 portfolioId
     * 
     * @param portfolioId portfolioId
     */
    public void setPortfolioId(String portfolioId) {
        this.portfolioId = portfolioId;
    }

    /**
     * 获取 entrustId
     * 
     * @return entrustId.
     */
    public String getEntrustId() {
        return entrustId;
    }

    /**
     * 设置 entrustId
     * 
     * @param entrustId entrustId
     */
    public void setEntrustId(String entrustId) {
        this.entrustId = entrustId;
    }

    /**
     * 获取 quoteDate
     * 
     * @return quoteDate.
     */
    public Date getQuoteDate() {
        return quoteDate;
    }

    /**
     * 设置 quoteDate
     * 
     * @param quoteDate quoteDate
     */
    public void setQuoteDate(Date quoteDate) {
        this.quoteDate = quoteDate;
    }

    /**
     * 获取 settCcy
     * 
     * @return settCcy.
     */
    public String getSettCcy() {
        return settCcy;
    }

    /**
     * 设置 settCcy
     * 
     * @param settCcy settCcy
     */
    public void setSettCcy(String settCcy) {
        this.settCcy = settCcy;
    }

    /**
     * 获取 tradeStatus
     * 
     * @return tradeStatus.
     */
    public String getTradeStatus() {
        return tradeStatus;
    }

    /**
     * 设置 tradeStatus
     * 
     * @param tradeStatus tradeStatus
     */
    public void setTradeStatus(String tradeStatus) {
        this.tradeStatus = tradeStatus;
    }

    /**
     * 获取 sendStatus
     * 
     * @return sendStatus.
     */
    public String getSendStatus() {
        return sendStatus;
    }

    /**
     * 设置 sendStatus
     * 
     * @param sendStatus sendStatus
     */
    public void setSendStatus(String sendStatus) {
        this.sendStatus = sendStatus;
    }

    /**
     * 获取 effTime
     * 
     * @return effTime.
     */
    public Date getEffTime() {
        return effTime;
    }

    /**
     * 设置 effTime
     * 
     * @param effTime effTime
     */
    public void setEffTime(Date effTime) {
        this.effTime = effTime;
    }

    /**
     * 获取 ouPartyId
     * 
     * @return ouPartyId.
     */
    public String getOuPartyId() {
        return ouPartyId;
    }

    /**
     * 设置 ouPartyId
     * 
     * @param ouPartyId ouPartyId
     */
    public void setOuPartyId(String ouPartyId) {
        this.ouPartyId = ouPartyId;
    }

    /**
     * 获取 ouTraderId
     * 
     * @return ouTraderId.
     */
    public String getOuTraderId() {
        return ouTraderId;
    }

    /**
     * 设置 ouTraderId
     * 
     * @param ouTraderId ouTraderId
     */
    public void setOuTraderId(String ouTraderId) {
        this.ouTraderId = ouTraderId;
    }

    /**
     * 获取 shareAmt
     * 
     * @return shareAmt.
     */
    public BigDecimal getShareAmt() {
        return shareAmt;
    }

    /**
     * 设置 shareAmt
     * 
     * @param shareAmt shareAmt
     */
    public void setShareAmt(BigDecimal shareAmt) {
        this.shareAmt = shareAmt;
    }

    /**
     * 获取 prinAmt
     * 
     * @return prinAmt.
     */
    public BigDecimal getPrinAmt() {
        return prinAmt;
    }

    /**
     * 设置 prinAmt
     * 
     * @param prinAmt prinAmt
     */
    public void setPrinAmt(BigDecimal prinAmt) {
        this.prinAmt = prinAmt;
    }

    /**
     * 获取 cpriceAmt
     * 
     * @return cpriceAmt.
     */
    public BigDecimal getCpriceAmt() {
        return cpriceAmt;
    }

    /**
     * 设置 cpriceAmt
     * 
     * @param cpriceAmt cpriceAmt
     */
    public void setCpriceAmt(BigDecimal cpriceAmt) {
        this.cpriceAmt = cpriceAmt;
    }

    /**
     * 获取 intAmt
     * 
     * @return intAmt.
     */
    public BigDecimal getIntAmt() {
        return intAmt;
    }

    /**
     * 设置 intAmt
     * 
     * @param intAmt intAmt
     */
    public void setIntAmt(BigDecimal intAmt) {
        this.intAmt = intAmt;
    }

    /**
     * 获取 fpriceAmt
     * 
     * @return fpriceAmt.
     */
    public BigDecimal getFpriceAmt() {
        return fpriceAmt;
    }

    /**
     * 设置 fpriceAmt
     * 
     * @param fpriceAmt fpriceAmt
     */
    public void setFpriceAmt(BigDecimal fpriceAmt) {
        this.fpriceAmt = fpriceAmt;
    }

    /**
     * 获取 ps
     * 
     * @return ps.
     */
    public String getPs() {
        return ps;
    }

    /**
     * 设置 ps
     * 
     * @param ps ps
     */
    public void setPs(String ps) {
        this.ps = ps;
    }

    /**
     * 获取 yield
     * 
     * @return yield.
     */
    public BigDecimal getYield() {
        return yield;
    }

    /**
     * 设置 yield
     * 
     * @param yield yield
     */
    public void setYield(BigDecimal yield) {
        this.yield = yield;
    }

    /**
     * 获取 exerYield
     * 
     * @return exerYield.
     */
    public BigDecimal getExerYield() {
        return exerYield;
    }

    /**
     * 设置 exerYield
     * 
     * @param exerYield exerYield
     */
    public void setExerYield(BigDecimal exerYield) {
        this.exerYield = exerYield;
    }

}
