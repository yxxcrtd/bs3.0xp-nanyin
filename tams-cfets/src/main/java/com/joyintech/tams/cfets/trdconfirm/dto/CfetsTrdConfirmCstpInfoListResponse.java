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
import java.util.List;

/**
 * 银行间交易成交确认
 * 查询Cstp下行列表返回数据DTO
 * @author 1
 * @version 1.0 2021年10月26日
 * @see CfetsTrdConfirmCstpInfoListResponse
 * @since 1.0
 */
@ApiModel(value="查询Cstp下行列表返回数据",description="查询Cstp下行列表返回数据")
public class CfetsTrdConfirmCstpInfoListResponse {

    @ApiModelProperty(name="execid",value="成交编号",example="A1")
    private String execid;

    @ApiModelProperty(name="businesstypeName",value="资产类型",example="A1")
    private String businesstypeName;

    @ApiModelProperty(name="businesstype",value="资产类型Code",example="A1")
    private String  businesstype;


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

    @ApiModelProperty(name="settleSpeed",value="清算速度Code",example="1")
    private String settleSpeed;

    @ApiModelProperty(name="settleSpeedName",value="清算速度",example="1")
    private String settleSpeedName;

    @ApiModelProperty(name="acctNo",value="托管账户",example="1")
    private String acctNo;


    @ApiModelProperty(name="lastqty",value="券面总额",example="1")
    private BigDecimal lastqty;

    @ApiModelProperty(name="tradeYield",value="成交价%",example="1")
    private BigDecimal tradeYield;


    @ApiModelProperty(name="termDays",value="期限（天）",example="1")
    private BigDecimal termDays;

    @ApiModelProperty(name="tradeAmt",value="交易金额",example="1")
    private BigDecimal tradeAmt;

    @ApiModelProperty(name="quoteid",value="报价编号",example="T01")
    private String quoteid;

    @ApiModelProperty(name="traderid",value="本方",example="T01")
    private String traderid;

    @ApiModelProperty(name="trader",value="本方交易员",example="T01")
    private String trader;

    @ApiModelProperty(name="counterTrader",value="对手方",example="T01")
    private String counterTrader;

    @ApiModelProperty(name="settType",value="清算类型",example="T01")
    private String settType;

    @ApiModelProperty(name="tradeDate",value="成交日期",example="T01")
    private Date tradeDate;

    @ApiModelProperty(name="remark",value="备注",example="T01")
    private String  remark;


    @ApiModelProperty(name="settleDate",value="结算日期",example="T01")
    private Date settleDate;

    @ApiModelProperty(name="tradeMarket",value="交易场所Code",example="T01")
    private String tradeMarket;
    @ApiModelProperty(name="vSettleDate",value="首期结算日",example="字符串1")
    private Date   vsettleDate;
    @ApiModelProperty(name="mSettleDate",value="到期结算日",example="字符串1")
    private Date   msettleDate;
    @ApiModelProperty(name="mTradeAmt",value="到期结算金额",example="字符串1")
    private BigDecimal   miradeAmt;

    public String getExecid() {
        return execid;
    }

    public void setExecid(String execid) {
        this.execid = execid;
    }

    public String getBusinesstypeName() {
        return businesstypeName;
    }

    public void setBusinesstypeName(String businesstypeName) {
        this.businesstypeName = businesstypeName;
    }

    public String getBusinesstype() {
        return businesstype;
    }

    public void setBusinesstype(String businesstype) {
        this.businesstype = businesstype;
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

    public String getAcctNo() {
        return acctNo;
    }

    public void setAcctNo(String acctNo) {
        this.acctNo = acctNo;
    }

    public BigDecimal getLastqty() {
        return lastqty;
    }

    public void setLastqty(BigDecimal lastqty) {
        this.lastqty = lastqty;
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

    public String getQuoteid() {
        return quoteid;
    }

    public void setQuoteid(String quoteid) {
        this.quoteid = quoteid;
    }

    public String getTraderid() {
        return traderid;
    }

    public void setTraderid(String traderid) {
        this.traderid = traderid;
    }

    public String getTrader() {
        return trader;
    }

    public void setTrader(String trader) {
        this.trader = trader;
    }

    public String getCounterTrader() {
        return counterTrader;
    }

    public void setCounterTrader(String counterTrader) {
        this.counterTrader = counterTrader;
    }

    public String getSettType() {
        return settType;
    }

    public void setSettType(String settType) {
        this.settType = settType;
    }

    public Date getTradeDate() {
        return tradeDate;
    }

    public void setTradeDate(Date tradeDate) {
        this.tradeDate = tradeDate;
    }


    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getSettleDate() {
        return settleDate;
    }

    public void setSettleDate(Date settleDate) {
        this.settleDate = settleDate;
    }

    public String getTradeMarket() {
        return tradeMarket;
    }

    public void setTradeMarket(String tradeMarket) {
        this.tradeMarket = tradeMarket;
    }

    public Date getVsettleDate() {
        return vsettleDate;
    }

    public void setVsettleDate(Date vsettleDate) {
        this.vsettleDate = vsettleDate;
    }

    public Date getMsettleDate() {
        return msettleDate;
    }

    public void setMsettleDate(Date msettleDate) {
        this.msettleDate = msettleDate;
    }

    public BigDecimal getMiradeAmt() {
        return miradeAmt;
    }

    public void setMiradeAmt(BigDecimal miradeAmt) {
        this.miradeAmt = miradeAmt;
    }
}
