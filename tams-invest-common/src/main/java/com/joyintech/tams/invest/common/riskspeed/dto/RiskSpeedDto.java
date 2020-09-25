package com.joyintech.tams.invest.common.riskspeed.dto;

public class RiskSpeedDto {
    private String portfolioId; //组合代码
    private String valDate; //日期
    private  String assetType;//资产分类
    private String assetCode;// 资产代码
    private String indType;// 指标类别
    private String indName;// 指标名称
    private String indCode;// 指标代码
    private String foulCode; // 违规
    private Object totalAmt;//可投总金额
    private Object hasAmt;// 已投金额
    private  String packageName; // 组合包

    public String getPortfolioId() {
        return portfolioId;
    }

    public void setPortfolioId(String portfolioId) {
        this.portfolioId = portfolioId;
    }

    public String getValDate() {
        return valDate;
    }

    public void setValDate(String valDate) {
        this.valDate = valDate;
    }

    public String getAssetType() {
        return assetType;
    }

    public void setAssetType(String assetType) {
        this.assetType = assetType;
    }

    public String getAssetCode() {
        return assetCode;
    }

    public void setAssetCode(String assetCode) {
        this.assetCode = assetCode;
    }

    public String getIndType() {
        return indType;
    }

    public void setIndType(String indType) {
        this.indType = indType;
    }

    public String getIndName() {
        return indName;
    }

    public void setIndName(String indName) {
        this.indName = indName;
    }

    public String getIndCode() {
        return indCode;
    }

    public void setIndCode(String indCode) {
        this.indCode = indCode;
    }

    public String getFoulCode() {
        return foulCode;
    }

    public void setFoulCode(String foulCode) {
        this.foulCode = foulCode;
    }

    public Object getTotalAmt() {
        return totalAmt;
    }

    public void setTotalAmt(Object totalAmt) {
        this.totalAmt = totalAmt;
    }

    public Object getHasAmt() {
        return hasAmt;
    }

    public void setHasAmt(Object hasAmt) {
        this.hasAmt = hasAmt;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }
}
