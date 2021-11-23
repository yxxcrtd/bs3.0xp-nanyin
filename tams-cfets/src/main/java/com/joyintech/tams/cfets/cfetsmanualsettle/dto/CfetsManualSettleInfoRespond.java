package com.joyintech.tams.cfets.cfetsmanualsettle.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 银行间交收信息DTO
 * @author sjj
 * @version 1.0 2021年11月18日
 * @see CfetsManualSettleInfoRespond
 * @since 1.0
 */

@ApiModel(value="银行间手工交收信息返回参数",description="银行间手工交收信息返回参数")
public class CfetsManualSettleInfoRespond {

    @ApiModelProperty(name="finprodMarketId",value="资产代码",example="字符串1")
    private String finprodMarketId;

    @ApiModelProperty(name="finprodAbbr",value="资产简称",example="字符串1")
    private String finprodAbbr;

    @ApiModelProperty(name="ps",value="交易方向CODE",example="A1")
    private String ps;

    @ApiModelProperty(name="psStr",value="交易方向",example="A1")
    private String psStr;

    @ApiModelProperty(name="vDate",value="计息起始日",example="A1")
    private Date vDate;

    @ApiModelProperty(name="mDate",value="计息截止日",example="A1")
    private Date mDate;

    @ApiModelProperty(name="payDate",value="理论支付日",example="A1")
    private Date payDate;

    @ApiModelProperty(name="yield",value="区间票面利率",example="A1")
    private BigDecimal yield;

    @ApiModelProperty(name="unitIntAmt",value="单位应计利息",example="A1")
    private BigDecimal unitIntAmt;

    @ApiModelProperty(name="intPrinAmt",value="单位本金",example="A1")
    private BigDecimal intPrinAmt;

    @ApiModelProperty(name="prinAmt",value="还本面额",example="A1")
    private BigDecimal prinAmt;

    //理论付息信息
    @ApiModelProperty(name="qty",value="券面总额",example="A1")
    private BigDecimal qty;

    @ApiModelProperty(name="intAmtTheory",value="理论应计利息",example="A1")
    private BigDecimal intAmtTheory;

    @ApiModelProperty(name="prinAmtTheory",value="理论还本金额",example="A1")
    private BigDecimal prinAmtTheory;

    //组合分配
    List<PortIntFenPeiInfoDto> portIntFenPeiInfoDtoList;

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

    public String getPsStr() {
        return psStr;
    }

    public void setPsStr(String psStr) {
        this.psStr = psStr;
    }

    public Date getvDate() {
        return vDate;
    }

    public void setvDate(Date vDate) {
        this.vDate = vDate;
    }

    public Date getmDate() {
        return mDate;
    }

    public void setmDate(Date mDate) {
        this.mDate = mDate;
    }

    public Date getPayDate() {
        return payDate;
    }

    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    public BigDecimal getYield() {
        return yield;
    }

    public void setYield(BigDecimal yield) {
        this.yield = yield;
    }

    public BigDecimal getUnitIntAmt() {
        return unitIntAmt;
    }

    public void setUnitIntAmt(BigDecimal unitIntAmt) {
        this.unitIntAmt = unitIntAmt;
    }

    public BigDecimal getIntPrinAmt() {
        return intPrinAmt;
    }

    public void setIntPrinAmt(BigDecimal intPrinAmt) {
        this.intPrinAmt = intPrinAmt;
    }

    public BigDecimal getPrinAmt() {
        return prinAmt;
    }

    public void setPrinAmt(BigDecimal prinAmt) {
        this.prinAmt = prinAmt;
    }

    public BigDecimal getQty() {
        return qty;
    }

    public void setQty(BigDecimal qty) {
        this.qty = qty;
    }

    public BigDecimal getIntAmtTheory() {
        return intAmtTheory;
    }

    public void setIntAmtTheory(BigDecimal intAmtTheory) {
        this.intAmtTheory = intAmtTheory;
    }

    public BigDecimal getPrinAmtTheory() {
        return prinAmtTheory;
    }

    public void setPrinAmtTheory(BigDecimal prinAmtTheory) {
        this.prinAmtTheory = prinAmtTheory;
    }

    public List<PortIntFenPeiInfoDto> getPortIntFenPeiInfoDtoList() {
        return portIntFenPeiInfoDtoList;
    }

    public void setPortIntFenPeiInfoDtoList(List<PortIntFenPeiInfoDto> portIntFenPeiInfoDtoList) {
        this.portIntFenPeiInfoDtoList = portIntFenPeiInfoDtoList;
    }
}
