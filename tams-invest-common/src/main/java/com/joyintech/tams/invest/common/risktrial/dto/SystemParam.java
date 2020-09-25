package com.joyintech.tams.invest.common.risktrial.dto;

import com.joyintech.tams.common.base.entity.TrdProductDeal;

public class SystemParam {

    private String valDate;

    private String portfolioCode;

    private String assetCode;

    private String tradeCode;

    /**
     * ???????/????
     */
    private String assetType;
    /**
     * ???????????/?????
     */
    private String assetSecType;
    /**
     * ???????????/?????
     */
    private String assetThrType;
    /**
     * ???????/?????
     */
    private String investProporty;

    /**
     * ???????dto
     * @return
     */
    private TrdProductDeal trdProductDeal;

    public String getValDate() {
        return valDate;
    }

    public void setValDate(String valDate) {
        this.valDate = valDate;
    }

    public String getPortfolioCode() {
        return portfolioCode;
    }

    public void setPortfolioCode(String portfolioCode) {
        this.portfolioCode = portfolioCode;
    }

    public String getAssetCode() {
        return assetCode;
    }

    public void setAssetCode(String assetCode) {
        this.assetCode = assetCode;
    }

    public String getTradeCode() {
        return tradeCode;
    }

    public void setTradeCode(String tradeCode) {
        this.tradeCode = tradeCode;
    }

    public TrdProductDeal getTrdProductDeal() {
        return trdProductDeal;
    }

    public void setTrdProductDeal(TrdProductDeal trdProductDeal) {
        this.trdProductDeal = trdProductDeal;
    }

    public String getAssetType() {
        return assetType;
    }

    public void setAssetType(String assetType) {
        this.assetType = assetType;
    }

    public String getAssetSecType() {
        return assetSecType;
    }

    public void setAssetSecType(String assetSecType) {
        this.assetSecType = assetSecType;
    }

    public String getAssetThrType() {
        return assetThrType;
    }

    public void setAssetThrType(String assetThrType) {
        this.assetThrType = assetThrType;
    }

    public String getInvestProporty() {
        return investProporty;
    }

    public void setInvestProporty(String investProporty) {
        this.investProporty = investProporty;
    }

    public String replaceForString(String sql) {
        return sql.replaceAll("\\$\\{valDate\\}", valDate).replaceAll("\\$\\{portfolioCode\\}",
            portfolioCode).replaceAll("\\$\\{assetCode\\}", "'" + assetCode + "'").replaceAll(
                "\\$\\{tradeCode\\}", tradeCode).replaceAll(
                "\\$\\{assetType\\}", "'" +assetType+ "'").replaceAll(
                        "\\$\\{investProporty\\}", "'" +investProporty+ "'").replaceAll(
                "\\$\\{ps\\}",trdProductDeal.getPs()!=null?"'" + trdProductDeal.getPs()+ "'":"").replaceAll(
                "\\$\\{tradeAmt\\}",trdProductDeal.getTradeAmt()!=null?trdProductDeal.getTradeAmt().toString():"").replaceAll(
                "\\$\\{fpriceAmt\\}",trdProductDeal.getFpriceAmt()!=null?trdProductDeal.getFpriceAmt().toString():"").replaceAll(
                "\\$\\{prinAmt\\}",trdProductDeal.getPrinAmt()!=null?trdProductDeal.getPrinAmt().toString():"").replaceAll(
                "\\$\\{shareAmt\\}",trdProductDeal.getShareAmt()!=null?trdProductDeal.getShareAmt().toString():"").replaceAll(
                "\\$\\{unitCprice\\}",trdProductDeal.getUnitCprice()!=null?trdProductDeal.getUnitCprice().toString():"").replaceAll(
                "\\$\\{unitFprice\\}",trdProductDeal.getUnitFprice()!=null?trdProductDeal.getUnitFprice().toString():"").replaceAll(
                "\\$\\{cpriceAmt\\}",trdProductDeal.getCpriceAmt()!=null?trdProductDeal.getCpriceAmt().toString():"");
    }
}