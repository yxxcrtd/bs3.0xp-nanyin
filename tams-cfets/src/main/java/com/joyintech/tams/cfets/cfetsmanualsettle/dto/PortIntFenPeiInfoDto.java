package com.joyintech.tams.cfets.cfetsmanualsettle.dto;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

//组合分配信息共通DTO
public class PortIntFenPeiInfoDto {

    @ApiModelProperty(name="portfolioId",value="组合代码",example="A1")
    private String portfolioId;

    @ApiModelProperty(name="portfolioName",value="组合名称",example="A1")
    private String portfolioName;

    @ApiModelProperty(name="qtyd",value="持有面额（元）",example="A1")
    private BigDecimal qtyd;

    @ApiModelProperty(name="prinAmtTheoryd",value="还本金额（元）",example="A1")
    private BigDecimal prinAmtTheoryd;

    @ApiModelProperty(name="intAmtTheoryd",value="理论应收利息（元）",example="A1")
    private BigDecimal intAmtTheoryd;

    public String getPortfolioId() {
        return portfolioId;
    }

    public void setPortfolioId(String portfolioId) {
        this.portfolioId = portfolioId;
    }

    public String getPortfolioName() {
        return portfolioName;
    }

    public void setPortfolioName(String portfolioName) {
        this.portfolioName = portfolioName;
    }

    public BigDecimal getQtyd() {
        return qtyd;
    }

    public void setQtyd(BigDecimal qtyd) {
        this.qtyd = qtyd;
    }

    public BigDecimal getPrinAmtTheoryd() {
        return prinAmtTheoryd;
    }

    public void setPrinAmtTheoryd(BigDecimal prinAmtTheoryd) {
        this.prinAmtTheoryd = prinAmtTheoryd;
    }

    public BigDecimal getIntAmtTheoryd() {
        return intAmtTheoryd;
    }

    public void setIntAmtTheoryd(BigDecimal intAmtTheoryd) {
        this.intAmtTheoryd = intAmtTheoryd;
    }
}
