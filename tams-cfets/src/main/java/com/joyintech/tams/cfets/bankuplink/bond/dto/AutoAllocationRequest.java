package com.joyintech.tams.cfets.bankuplink.bond.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author zznn
 * @date 2021/11/12
 */
@ApiModel(value="自动分配请求参数",description="自动分配请求参数")
public class AutoAllocationRequest {

    @ApiModelProperty(required = true,name="distribution",value="分配方式")
    private String distribution;

    @ApiModelProperty(required = true,name="ps",value="交易方向")
    private String ps;

    @ApiModelProperty(required = true,name="shareAmt",value="券面总额(万元)")
    private BigDecimal shareAmt;

    @ApiModelProperty(required = true,name="price",value="净价")
    private BigDecimal price;

    @ApiModelProperty(required = true,name="unitFprice",value="全价")
    private BigDecimal unitFprice;

    @ApiModelProperty(name="dealFeeAmt",value="交易手续费(元)")
    private BigDecimal dealFeeAmt;


    @ApiModelProperty(name="settFeeAmt",value="结算服务费(元)")
    private BigDecimal settFeeAmt;

    @ApiModelProperty(name="cpriceAmt",value="净价总额(元)")
    private BigDecimal cpriceAmt;

    @ApiModelProperty(name="intAmt",value="应计利息总额(元)")
    private BigDecimal intAmt;

    @ApiModelProperty(required = true,name="ptlAllocations",value="组合分配列表")
    private List<PtlAllocationDto>  ptlAllocations;

    public String getDistribution() {
        return distribution;
    }

    public void setDistribution(String distribution) {
        this.distribution = distribution;
    }

    public String getPs() {
        return ps;
    }

    public void setPs(String ps) {
        this.ps = ps;
    }

    public BigDecimal getShareAmt() {
        return shareAmt;
    }

    public void setShareAmt(BigDecimal shareAmt) {
        this.shareAmt = shareAmt;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getUnitFprice() {
        return unitFprice;
    }

    public void setUnitFprice(BigDecimal unitFprice) {
        this.unitFprice = unitFprice;
    }

    public BigDecimal getDealFeeAmt() {
        return dealFeeAmt;
    }

    public void setDealFeeAmt(BigDecimal dealFeeAmt) {
        this.dealFeeAmt = dealFeeAmt;
    }

    public BigDecimal getSettFeeAmt() {
        return settFeeAmt;
    }

    public void setSettFeeAmt(BigDecimal settFeeAmt) {
        this.settFeeAmt = settFeeAmt;
    }

    public BigDecimal getCpriceAmt() {
        return cpriceAmt;
    }

    public void setCpriceAmt(BigDecimal cpriceAmt) {
        this.cpriceAmt = cpriceAmt;
    }

    public BigDecimal getIntAmt() {
        return intAmt;
    }

    public void setIntAmt(BigDecimal intAmt) {
        this.intAmt = intAmt;
    }

    public List<PtlAllocationDto> getPtlAllocations() {
        return ptlAllocations;
    }

    public void setPtlAllocations(List<PtlAllocationDto> ptlAllocations) {
        this.ptlAllocations = ptlAllocations;
    }
}

