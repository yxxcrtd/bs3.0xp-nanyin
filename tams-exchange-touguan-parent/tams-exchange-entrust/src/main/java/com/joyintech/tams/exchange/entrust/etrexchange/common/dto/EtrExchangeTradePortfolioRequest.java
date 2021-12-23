/*
 * 文件名：EtrExchangeTradeDto001.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：chendijun
 * 修改时间：2020年7月2日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.exchange.entrust.etrexchange.common.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 *  交易信息 （左边下）
 * @author meifaxin
 * @version 1.0 2021年12月21日
 * @see EtrExchangeTradePortfolioRequest
 * @since 1.0
 */
@ApiModel(value="竞价指令下单组合信息",description="竞价指令下单组合信息")
public class EtrExchangeTradePortfolioRequest {



    @ApiModelProperty(name="portfolioName",value="组合名称",example="submit/save", required = true)
    private String portfolioName;

    @ApiModelProperty(name="portfolioId",value="组合代码",example="submit/save", required = true)
    private String portfolioId;

    @ApiModelProperty(name="acctNo",value="资金账户",example="submit/save", required = true)
    private String acctNo;

    @ApiModelProperty(name="entrustAmt",value="指令金额",example="")
    private BigDecimal entrustAmt;

    @ApiModelProperty(name="entrustNum",value="指令数量",example="")
    private BigDecimal entrustNum;

    @ApiModelProperty(name="standardBondVolume",value="标准券数量",example="")
    private Integer standardBondVolume;

    @ApiModelProperty(name="availableStandardBondVolume",value="标准券可用数量",example="")
    private Integer availableStandardBondVolume;

    @ApiModelProperty(name="accAvailableCash",value="可用现金",example="")
    private BigDecimal accAvailableCash;

    @ApiModelProperty(name="accAvailablePos",value="可用持仓",example="")
    private BigDecimal accAvailablePos;


    public String getPortfolioName() {
        return portfolioName;
    }

    public void setPortfolioName(String portfolioName) {
        this.portfolioName = portfolioName;
    }

    public String getPortfolioId() {
        return portfolioId;
    }

    public void setPortfolioId(String portfolioId) {
        this.portfolioId = portfolioId;
    }

    public String getAcctNo() {
        return acctNo;
    }

    public void setAcctNo(String acctNo) {
        this.acctNo = acctNo;
    }

    public BigDecimal getEntrustAmt() {
        return entrustAmt;
    }

    public void setEntrustAmt(BigDecimal entrustAmt) {
        this.entrustAmt = entrustAmt;
    }

    public BigDecimal getEntrustNum() {
        return entrustNum;
    }

    public void setEntrustNum(BigDecimal entrustNum) {
        this.entrustNum = entrustNum;
    }

    public BigDecimal getAccAvailableCash() {
        return accAvailableCash;
    }

    public void setAccAvailableCash(BigDecimal accAvailableCash) {
        this.accAvailableCash = accAvailableCash;
    }

    public Integer getStandardBondVolume() {
        return standardBondVolume;
    }

    public void setStandardBondVolume(Integer standardBondVolume) {
        this.standardBondVolume = standardBondVolume;
    }

    public Integer getAvailableStandardBondVolume() {
        return availableStandardBondVolume;
    }

    public void setAvailableStandardBondVolume(Integer availableStandardBondVolume) {
        this.availableStandardBondVolume = availableStandardBondVolume;
    }

    public BigDecimal getAccAvailablePos() {
        return accAvailablePos;
    }

    public void setAccAvailablePos(BigDecimal accAvailablePos) {
        this.accAvailablePos = accAvailablePos;
    }
}
