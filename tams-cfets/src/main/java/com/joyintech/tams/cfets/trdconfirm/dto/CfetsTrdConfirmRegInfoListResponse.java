/*
 * 文件名：CfetsTrdConfirmRegInfoListResponse.java
 * 版权：Copyright 2020-2022 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管系统V2.0
 * 修改人：
 * 修改时间：2021年01月25日
 * 修改内容：新建
 * 系统名称：理财资管系统V2.0
 */

package com.joyintech.tams.cfets.trdconfirm.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 银行间交易成交确认
 * 查询交易登记列表返回数据DTO
 * @author 1
 * @version 1.0 2021年10月26日
 * @see CfetsTrdConfirmRegInfoListResponse
 * @since 1.0
 */
@ApiModel(value="查询交易登记列表返回数据",description="查询交易登记列表返回数据")
public class CfetsTrdConfirmRegInfoListResponse{

    @ApiModelProperty(name="effectiveStatus",value="有效状态Code",example="A1")
    private String effectiveStatus;

    @ApiModelProperty(name="effectiveStatusName",value="有效状态",example="A1")
    private String effectiveStatusName;

    @ApiModelProperty(name="finprodType2",value="资产类型Code",example="A1")
    private String  finprodType2;

    @ApiModelProperty(name="finprodType2Name",value="资产类型",example="A1")
    private String  finprodType2Name;

    @ApiModelProperty(name="finprodId",value="资产代码Id",example="ZC1")
    private String finprodId;

    @ApiModelProperty(name="finprodMarketId",value="资产代码",example="ZC1")
    private String finprodMarketId;

    @ApiModelProperty(name="finprodAbbr",value="资产简称",example="ZC名")
    private String finprodAbbr;

    @ApiModelProperty(name="ps",value="交易方向Code",example="T01")
    private String ps;

    @ApiModelProperty(name="psName",value="交易方向",example="T01")
    private String psName;

    @ApiModelProperty(name="tradeMarket",value="交易场所Code",example="T01")
    private String tradeMarket;

    @ApiModelProperty(name="psName",value="交易场所",example="T01")
    private String tradeMarketName;

    @ApiModelProperty(name="settleSpeed",value="清算速度Code",example="1")
    private String settleSpeed;

    @ApiModelProperty(name="settleSpeedName",value="清算速度",example="1")
    private String settleSpeedName;

    @ApiModelProperty(name="secManageAcctId",value="证券账户ID",example="1")
    private String secManageAcctId;

    @ApiModelProperty(name="secManageAcctIdName",value="证券账户",example="1")
    private String secManageAcctIdName;

    @ApiModelProperty(name="faceValue",value="券面总额",example="1")
    private BigDecimal faceValue;

    @ApiModelProperty(name="tradeYield",value="成交价",example="1")
    private BigDecimal tradeYield;

    @ApiModelProperty(name="termDays",value="期限（天）",example="1")
    private BigDecimal termDays;

    @ApiModelProperty(name="tradeAmt",value="交易金额",example="1")
    private BigDecimal tradeAmt;

    @ApiModelProperty(name="busiCode",value="业务编号",example="1")
    private String busiCode;

    @ApiModelProperty(name="trader",value="交易员",example="admin")
    private String trader;

    @ApiModelProperty(name="tradeDate",value="登记日期",example="admin")
    private Date tradeDate;

    @ApiModelProperty(name="counterTrader",value="对手方",example="admin")
    private String counterTrader;

    @ApiModelProperty(name="portfolioIds",value="组合代码",example="字符串1")
    private String portfolioIds;

    @ApiModelProperty(name="portfolioNames",value="组合名称",example="字符串1")
    private String portfolioNames;

    @ApiModelProperty(name="quoteType",value="报价方式",example="字符串1")
    private String quoteType;

    @ApiModelProperty(name="exportNum",value="导出次数",example="1")
    private BigDecimal exportNum;

    @ApiModelProperty(name="remark",value="备注",example="字符串1")
    private String remark;

    @ApiModelProperty(name="totleTradeId",value="汇总登记编号",example="字符串1")
    private String totleTradeId;

    public String getEffectiveStatus() {
        return effectiveStatus;
    }

    public void setEffectiveStatus(String effectiveStatus) {
        this.effectiveStatus = effectiveStatus;
    }

    public String getEffectiveStatusName() {
        return effectiveStatusName;
    }

    public void setEffectiveStatusName(String effectiveStatusName) {
        this.effectiveStatusName = effectiveStatusName;
    }

    public String getFinprodType2() {
        return finprodType2;
    }

    public void setFinprodType2(String finprodType2) {
        this.finprodType2 = finprodType2;
    }

    public String getFinprodType2Name() {
        return finprodType2Name;
    }

    public void setFinprodType2Name(String finprodType2Name) {
        this.finprodType2Name = finprodType2Name;
    }

    public String getFinprodId() {
        return finprodId;
    }

    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
    }

    public String getFinprodMarketId() {
        return finprodMarketId;
    }

    public void setFinprodMarketId(String finprodMarketId) {
        this.finprodMarketId = finprodMarketId;
    }

    public String getFinprodAbbr() {
        return finprodAbbr;
    }

    public void setFinprodAbbr(String finprodAbbr) {
        this.finprodAbbr = finprodAbbr;
    }

    public String getPs() {
        return ps;
    }

    public void setPs(String ps) {
        this.ps = ps;
    }

    public String getPsName() {
        return psName;
    }

    public void setPsName(String psName) {
        this.psName = psName;
    }

    public String getTradeMarket() {
        return tradeMarket;
    }

    public void setTradeMarket(String tradeMarket) {
        this.tradeMarket = tradeMarket;
    }

    public String getTradeMarketName() {
        return tradeMarketName;
    }

    public void setTradeMarketName(String tradeMarketName) {
        this.tradeMarketName = tradeMarketName;
    }

    public String getSettleSpeed() {
        return settleSpeed;
    }

    public void setSettleSpeed(String settleSpeed) {
        this.settleSpeed = settleSpeed;
    }

    public String getSettleSpeedName() {
        return settleSpeedName;
    }

    public void setSettleSpeedName(String settleSpeedName) {
        this.settleSpeedName = settleSpeedName;
    }

    public String getSecManageAcctId() {
        return secManageAcctId;
    }

    public void setSecManageAcctId(String secManageAcctId) {
        this.secManageAcctId = secManageAcctId;
    }

    public String getSecManageAcctIdName() {
        return secManageAcctIdName;
    }

    public void setSecManageAcctIdName(String secManageAcctIdName) {
        this.secManageAcctIdName = secManageAcctIdName;
    }

    public BigDecimal getFaceValue() {
        return faceValue;
    }

    public void setFaceValue(BigDecimal faceValue) {
        this.faceValue = faceValue;
    }

    public BigDecimal getTradeYield() {
        return tradeYield;
    }

    public void setTradeYield(BigDecimal tradeYield) {
        this.tradeYield = tradeYield;
    }

    public BigDecimal getTermDays() {
        return termDays;
    }

    public void setTermDays(BigDecimal termDays) {
        this.termDays = termDays;
    }

    public BigDecimal getTradeAmt() {
        return tradeAmt;
    }

    public void setTradeAmt(BigDecimal tradeAmt) {
        this.tradeAmt = tradeAmt;
    }

    public String getBusiCode() {
        return busiCode;
    }

    public void setBusiCode(String busiCode) {
        this.busiCode = busiCode;
    }

    public String getTrader() {
        return trader;
    }

    public void setTrader(String trader) {
        this.trader = trader;
    }

    public Date getTradeDate() {
        return tradeDate;
    }

    public void setTradeDate(Date tradeDate) {
        this.tradeDate = tradeDate;
    }

    public String getCounterTrader() {
        return counterTrader;
    }

    public void setCounterTrader(String counterTrader) {
        this.counterTrader = counterTrader;
    }

    public String getPortfolioIds() {
        return portfolioIds;
    }

    public void setPortfolioIds(String portfolioIds) {
        this.portfolioIds = portfolioIds;
    }

    public String getPortfolioNames() {
        return portfolioNames;
    }

    public void setPortfolioNames(String portfolioNames) {
        this.portfolioNames = portfolioNames;
    }

    public String getQuoteType() {
        return quoteType;
    }

    public void setQuoteType(String quoteType) {
        this.quoteType = quoteType;
    }

    public BigDecimal getExportNum() {
        return exportNum;
    }

    public void setExportNum(BigDecimal exportNum) {
        this.exportNum = exportNum;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }


    public String getTotleTradeId() {
        return totleTradeId;
    }

    public void setTotleTradeId(String totleTradeId) {
        this.totleTradeId = totleTradeId;
    }
}
