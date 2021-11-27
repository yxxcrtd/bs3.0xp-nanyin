package com.joyintech.tams.cfets.bankuplink.bond.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * @author zznn
 * @date 2021/11/11
 */
@ApiModel(value="组合分配",description="组合分配")
public class PtlAllocationDto {

    @ApiModelProperty(name="portfolioId",value="指令代码")
    private String entrustId;

    @ApiModelProperty(name="portfolioId",value="组合代码")
    private String portfolioId;

    @ApiModelProperty(name="portfolioName",value="组合名称")
    private String portfolioName;


    @ApiModelProperty(name="avaNum",value="剩余指令额度(万元)")
    private BigDecimal avaNum;

    @ApiModelProperty(name="shareAmt",value="券面总额(万元)")
    private BigDecimal shareAmt;

    @ApiModelProperty(name="outPartySec",value="本方券券面总额(万元)")
    private BigDecimal ourEntNum;

    @ApiModelProperty(name="inSec",value="融入券券面总额(万元)")
    private BigDecimal lendinEntNum;

    @ApiModelProperty(name="sellRepurchaseSec",value="卖空购回券面总额(万元)")
    private BigDecimal sellAbleNum;

    @ApiModelProperty(name="dealFeeAmt",value="交易手续费(元)")
    private BigDecimal dealFeeAmt;

    @ApiModelProperty(name="settFeeamt",value="结算服务费(元)")
    private BigDecimal settFeeamt;

    @ApiModelProperty(name="cpriceAmt",value="净价总额(元)")
    private BigDecimal cpriceAmt;

    @ApiModelProperty(name="intAmt",value="应计利息总额(元)")
    private BigDecimal intAmt;

    @ApiModelProperty(name="fpriceAmt",value="全价总额(元)")
    private BigDecimal fpriceAmt;

    @ApiModelProperty(name="priceModel",value="价格模式")
    private String priceModel;

    @ApiModelProperty(name="businessModel",value="业务模式")
    private String businessModel;

    @ApiModelProperty(name="secManageAcctId",value="证券账户")
    private String secManageAcctId;

    @ApiModelProperty(name="price",value="净价(元)")
    private BigDecimal price;

    @ApiModelProperty(name="unitFprice",value="全价(元)")
    private BigDecimal unitFprice;

    @ApiModelProperty(name="matureYield",value="到期收益率(%)")
    private BigDecimal matureYield;

    @ApiModelProperty(name="exerYield",value="行权收益率(%)")
    private BigDecimal exerYield;

    public String getEntrustId() {
        return entrustId;
    }

    public void setEntrustId(String entrustId) {
        this.entrustId = entrustId;
    }

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

    public BigDecimal getAvaNum() {
        return avaNum;
    }

    public void setAvaNum(BigDecimal avaNum) {
        this.avaNum = avaNum;
    }

    public BigDecimal getShareAmt() {
        return shareAmt;
    }

    public void setShareAmt(BigDecimal shareAmt) {
        this.shareAmt = shareAmt;
    }

    public BigDecimal getOurEntNum() {
        return ourEntNum;
    }

    public void setOurEntNum(BigDecimal ourEntNum) {
        this.ourEntNum = ourEntNum;
    }

    public BigDecimal getLendinEntNum() {
        return lendinEntNum;
    }

    public void setLendinEntNum(BigDecimal lendinEntNum) {
        this.lendinEntNum = lendinEntNum;
    }

    public BigDecimal getSellAbleNum() {
        return sellAbleNum;
    }

    public void setSellAbleNum(BigDecimal sellAbleNum) {
        this.sellAbleNum = sellAbleNum;
    }

    public BigDecimal getDealFeeAmt() {
        return dealFeeAmt;
    }

    public void setDealFeeAmt(BigDecimal dealFeeAmt) {
        this.dealFeeAmt = dealFeeAmt;
    }

    public BigDecimal getSettFeeamt() {
        return settFeeamt;
    }

    public void setSettFeeamt(BigDecimal settFeeamt) {
        this.settFeeamt = settFeeamt;
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

    public BigDecimal getFpriceAmt() {
        return fpriceAmt;
    }

    public void setFpriceAmt(BigDecimal fpriceAmt) {
        this.fpriceAmt = fpriceAmt;
    }

    public String getPriceModel() {
        return priceModel;
    }

    public void setPriceModel(String priceModel) {
        this.priceModel = priceModel;
    }

    public String getBusinessModel() {
        return businessModel;
    }

    public void setBusinessModel(String businessModel) {
        this.businessModel = businessModel;
    }

    public String getSecManageAcctId() {
        return secManageAcctId;
    }

    public void setSecManageAcctId(String secManageAcctId) {
        this.secManageAcctId = secManageAcctId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getMatureYield() {
        return matureYield;
    }

    public void setMatureYield(BigDecimal matureYield) {
        this.matureYield = matureYield;
    }

    public BigDecimal getExerYield() {
        return exerYield;
    }

    public void setExerYield(BigDecimal exerYield) {
        this.exerYield = exerYield;
    }

    public BigDecimal getUnitFprice() {
        return unitFprice;
    }

    public void setUnitFprice(BigDecimal unitFprice) {
        this.unitFprice = unitFprice;
    }
}

