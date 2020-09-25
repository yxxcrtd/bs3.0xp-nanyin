package com.joyintech.tams.invest.entrust.etrmoneycalculation.dto;

import com.joyintech.tams.invest.common.riskspeed.dto.IRiskSpeed;

import java.util.List;

public class IRiskSpeedDto implements IRiskSpeed {
    private String portfolioId;
    private String valDate;
    private  String assetType;
    private String assetCode;
    private String indType;
    private String indName;
    private String indCode;
    private String foulCode;
    private Object totalAmt;
    private Object hasAmt;
    private  String packageName;
    @Override
    public String getAssetCode() {
        return assetCode;
    }
    public void setAssetCode(String assetCode) {
        this.assetCode = assetCode;
    }
    @Override
    public String getPortfolioId() {
        return portfolioId;
    }
    public void setPortfolioId(String portfolioId) {
        this.portfolioId = portfolioId;
    }
    @Override
    public String getValDate() {
        return valDate;
    }
    public void setValDate(String valDate) {
        this.valDate = valDate;
    }
    @Override
    public String getAssetType() {
        return assetType;
    }
    public void setAssetType(String assetType) {
        this.assetType = assetType;
    }
    @Override
    public String getAssetSecType() {
        return null;
    }

    @Override
    public String getAssetTrdType() {
        return null;
    }

    @Override
    public String getThreshDirection() {
        return null;
    }

    @Override
    public List<IRiskSpeed> getResultList() {
        return null;
    }

    @Override
    public void setResultList(List<IRiskSpeed> mapList) {

    }

    @Override
    public String getIndName() {
        return indName;
    }

    @Override
    public String getPackageName() {
        return packageName;
    }

    @Override
    public void setPackageName(String packageName) {
    this.packageName=packageName;
    }

    @Override
    public String getIndType() {
        return indType;
    }
    public void setIndType(String indType) {
        this.indType = indType;
    }
    @Override
    public void setIndName(String indName) {
        this.indName=indName;
    }

    @Override
    public String getIndCode() {
        return indCode;
    }

    @Override
    public void setIndCode(String indCode) {
     this.indCode=indCode;
    }

    @Override
    public String getFoulCond() {
        return foulCode;
    }

    @Override
    public void setFoulCond(String foulCode) {
    this.foulCode=foulCode;
    }

    @Override
    public Object getTotalAmt() {
        return totalAmt;
    }

    @Override
    public void setTotalAmt(Object totalAmt) {

      this.totalAmt=totalAmt;
    }

    @Override
    public Object getHasAmt() {
        return hasAmt;
    }

    @Override
    public void setHasAmt(Object hasAmt) {
     this.hasAmt=hasAmt;
    }
}
