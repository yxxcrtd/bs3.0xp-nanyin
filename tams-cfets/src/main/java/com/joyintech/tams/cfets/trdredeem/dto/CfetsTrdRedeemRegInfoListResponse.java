/*
 * 文件名：CfetsTrdQueryRegInfoListResponse.java
 * 版权：Copyright 2020-2022 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管系统V2.0
 * 修改人：
 * 修改时间：2021年01月25日
 * 修改内容：新建
 * 系统名称：理财资管系统V2.0
 */

package com.joyintech.tams.cfets.trdredeem.dto;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.common.constant.BussinessDictCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 银行间赎回登记
 * 查询交易登记列表返回数据DTO
 * @author 1
 * @version 1.0 2021年10月26日
 * @see CfetsTrdRedeemRegInfoListResponse
 * @since 1.0
 */
@ApiModel(value="赎回登记查询列表返回数据",description="赎回登记查询列表返回数据")
public class CfetsTrdRedeemRegInfoListResponse {

    @ApiModelProperty(name="effectiveStatus",value="有效状态Code",example="A1")
    private String effectiveStatus;

    @ApiModelProperty(name="effectiveStatusStr",value="有效状态",example="A1")
    private String effectiveStatusStr;

    @ApiModelProperty(name="busiCode",value="业务编号",example="1")
    private String busiCode;

    @ApiModelProperty(name="finprodId",value="资产代码Id",example="ZC1")
    private String finprodId;

    @ApiModelProperty(name="finprodMarketId",value="资产代码",example="ZC1")
    private String finprodMarketId;

    @ApiModelProperty(name="finprodAbbr",value="资产简称",example="ZC名")
    private String finprodAbbr;

    @ApiModelProperty(name="ps",value="交易方向Code",example="T01")
    private String ps;

    @ApiModelProperty(name="psStr",value="交易方向",example="T01")
    private String psStr;

    @ApiModelProperty(name="exercisePrice",value="行权净价",example="字符串1")
    private BigDecimal exercisePrice;

    @ApiModelProperty(name="redeemRate",value="赎回比例",example="1000000")
    private BigDecimal redeemRate;

    @ApiModelProperty(name="accountPosition",value="账户持仓",example="99.99")
    private BigDecimal accountPosition;

    @ApiModelProperty(name="regTradeDate",value="登记日期Date",example="admin")
    private Date regTradeDate;

    @ApiModelProperty(name="exerciseDate",value="理论行权日",example="admin")
    private Date exerciseDate;

    @ApiModelProperty(name="actExerciseDate",value="实际行权日",example="admin")
    private Date actExerciseDate;

    @ApiModelProperty(name="secManageAcctId",value="证券账户ID",example="1")
    private String secManageAcctId;

    @ApiModelProperty(name="secManageAcctIdStr",value="证券账户",example="1")
    private String secManageAcctIdStr;

    @ApiModelProperty(name="portfolioIds",value="组合代码",example="字符串1")
    private String portfolioIds;

    @ApiModelProperty(name="portfolioNames",value="组合名称",example="字符串1")
    private String portfolioNames;

    @ApiModelProperty(name="ccy",value="币种",example="字符串1")
    private String ccy;

    @ApiModelProperty(name="ccyStr",value="币种Str",example="字符串1")
    private String ccyStr;

    @ApiModelProperty(name="remark",value="备注",example="字符串1")
    private String remark;

    public String getEffectiveStatus() {
        return effectiveStatus;
    }

    public void setEffectiveStatus(String effectiveStatus) {
        this.effectiveStatus = effectiveStatus;
    }

    public String getEffectiveStatusStr() {
        return effectiveStatusStr;
    }

    public void setEffectiveStatusStr(String effectiveStatusStr) {
        this.effectiveStatusStr = effectiveStatusStr;
    }

    public String getBusiCode() {
        return busiCode;
    }

    public void setBusiCode(String busiCode) {
        this.busiCode = busiCode;
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

    public String getPsStr() {
        return SysDataUtil.getNameFromDataDict(BussinessDictCode.交易方向, this.getPs());
    }

    public void setPsStr(String psStr) {
        this.psStr = psStr;
    }

    public BigDecimal getExercisePrice() {
        return exercisePrice;
    }

    public void setExercisePrice(BigDecimal exercisePrice) {
        this.exercisePrice = exercisePrice;
    }

    public BigDecimal getRedeemRate() {
        return redeemRate;
    }

    public void setRedeemRate(BigDecimal redeemRate) {
        this.redeemRate = redeemRate;
    }

    public BigDecimal getAccountPosition() {
        return accountPosition;
    }

    public void setAccountPosition(BigDecimal accountPosition) {
        this.accountPosition = accountPosition;
    }

    public Date getRegTradeDate() {
        return regTradeDate;
    }

    public void setRegTradeDate(Date regTradeDate) {
        this.regTradeDate = regTradeDate;
    }

    public Date getExerciseDate() {
        return exerciseDate;
    }

    public void setExerciseDate(Date exerciseDate) {
        this.exerciseDate = exerciseDate;
    }

    public Date getActExerciseDate() {
        return actExerciseDate;
    }

    public void setActExerciseDate(Date actExerciseDate) {
        this.actExerciseDate = actExerciseDate;
    }

    public String getSecManageAcctId() {
        return secManageAcctId;
    }

    public void setSecManageAcctId(String secManageAcctId) {
        this.secManageAcctId = secManageAcctId;
    }

    public String getSecManageAcctIdStr() {
        return secManageAcctIdStr;
    }

    public void setSecManageAcctIdStr(String secManageAcctIdStr) {
        this.secManageAcctIdStr = secManageAcctIdStr;
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

    public String getCcy() {
        return ccy;
    }

    public void setCcy(String ccy) {
        this.ccy = ccy;
    }

    public String getCcyStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.币种, this.getCcy());
    }

    public void setCcyStr(String ccyStr) {
        this.ccyStr = ccyStr;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
