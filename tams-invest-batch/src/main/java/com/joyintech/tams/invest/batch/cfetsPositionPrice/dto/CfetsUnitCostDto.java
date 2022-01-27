package com.joyintech.tams.invest.batch.cfetsPositionPrice.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 单位成本价dto
 */
public class CfetsUnitCostDto {

    public CfetsUnitCostDto(){
        
    }

    public CfetsUnitCostDto(BigDecimal unitCost, BigDecimal buyCostAmt, BigDecimal shareAmt){
        this.unitCost=unitCost;
        this.buyCostAmt=buyCostAmt;
        this.shareAmt=shareAmt;
    }
    private String portfolioId;
    
    private String finprodId;
    
    private Date cdate;
    /**
     * 单位成本
     */
    private BigDecimal unitCost;
    /**
     * 成本
     */
    private BigDecimal buyCostAmt;
    /**
     * 数量
     */
    private BigDecimal shareAmt;

    public Date getCdate() {
        return cdate;
    }

    public void setCdate(Date cdate) {
        this.cdate = cdate;
    }

    public String getPortfolioId() {
        return portfolioId;
    }

    public void setPortfolioId(String portfolioId) {
        this.portfolioId = portfolioId;
    }

    public String getFinprodId() {
        return finprodId;
    }

    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
    }

    public BigDecimal getUnitCost() {
        return unitCost;
    }

    public void setUnitCost(BigDecimal unitCost) {
        this.unitCost = unitCost;
    }

    public BigDecimal getBuyCostAmt() {
        return buyCostAmt;
    }

    public void setBuyCostAmt(BigDecimal buyCostAmt) {
        this.buyCostAmt = buyCostAmt;
    }

    public BigDecimal getShareAmt() {
        return shareAmt;
    }

    public void setShareAmt(BigDecimal shareAmt) {
        this.shareAmt = shareAmt;
    }
}
