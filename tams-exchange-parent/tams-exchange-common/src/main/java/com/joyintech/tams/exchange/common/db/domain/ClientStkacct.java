package com.joyintech.tams.exchange.common.db.domain;

import java.io.Serializable;

/**
 * create By MAXX on 2020/3/18
 */
public class ClientStkacct implements Serializable {
    private String positionStr;//定位串
    private String exchangeType;//交易类别
    private String stockAccount;//证券账号
    private String mainFlag;//主账标志
    private String holderKind;//账户类别
    private String holderStatus;//股东状态
    private String holderRights;//股东权限
    private String register;//指定标志
    private String seatNo;//席位编号
    private String acodeAccount;//一码通账户号码
    private String holderName;//账户姓名
    private String fundAccount;//资产账户
    private String assetProp;//资产属性
    private Integer openDate;//开户日期
    private String csdcOptacctUseage;//中登合约账户用途

    public String getPositionStr() {
        return positionStr;
    }

    public String getExchangeType() {
        return exchangeType;
    }

    public String getStockAccount() {
        return stockAccount;
    }

    public String getMainFlag() {
        return mainFlag;
    }

    public String getHolderKind() {
        return holderKind;
    }

    public String getHolderStatus() {
        return holderStatus;
    }

    public String getHolderRights() {
        return holderRights;
    }

    public String getRegister() {
        return register;
    }

    public String getSeatNo() {
        return seatNo;
    }

    public String getAcodeAccount() {
        return acodeAccount;
    }

    public String getHolderName() {
        return holderName;
    }

    public String getFundAccount() {
        return fundAccount;
    }

    public String getAssetProp() {
        return assetProp;
    }

    public void setPositionStr(String positionStr) {
        this.positionStr = positionStr;
    }

    public void setExchangeType(String exchangeType) {
        this.exchangeType = exchangeType;
    }

    public void setStockAccount(String stockAccount) {
        this.stockAccount = stockAccount;
    }

    public void setMainFlag(String mainFlag) {
        this.mainFlag = mainFlag;
    }

    public void setHolderKind(String holderKind) {
        this.holderKind = holderKind;
    }

    public void setHolderStatus(String holderStatus) {
        this.holderStatus = holderStatus;
    }

    public void setHolderRights(String holderRights) {
        this.holderRights = holderRights;
    }

    public void setRegister(String register) {
        this.register = register;
    }

    public void setSeatNo(String seatNo) {
        this.seatNo = seatNo;
    }

    public void setAcodeAccount(String acodeAccount) {
        this.acodeAccount = acodeAccount;
    }

    public void setHolderName(String holderName) {
        this.holderName = holderName;
    }

    public void setFundAccount(String fundAccount) {
        this.fundAccount = fundAccount;
    }

    public void setAssetProp(String assetProp) {
        this.assetProp = assetProp;
    }

    public Integer getOpenDate() {
        return openDate;
    }

    public void setOpenDate(Integer openDate) {
        this.openDate = openDate;
    }

    public void setCsdcOptacctUseage(String csdcOptacctUseage) {
        this.csdcOptacctUseage = csdcOptacctUseage;
    }

    public String getCsdcOptacctUseage() {
        return csdcOptacctUseage;
    }
}
