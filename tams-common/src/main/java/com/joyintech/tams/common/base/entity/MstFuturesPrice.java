package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;
import org.apache.poi.hpsf.Decimal;

import java.util.Date;

public class MstFuturesPrice extends BaseEntity {

    private String futuresId;
    private Date tradeDate;
    private String WindCode;
    private Decimal presettlePrice;
    private Decimal openPrice;
    private Decimal highPrice;
    private Decimal lowPrice;
    private Decimal closePrice;
    private Decimal settlePrice;
    private Decimal volumeNum;
    private Decimal volumeAmount;
    private Decimal qiNum;
    private Decimal changePrice;
    private Decimal futuresType;
    private String inputType;
    private String createUser;
    private String createDept;
    private Date createTime;
    private String updateUser;
    private Date updateTime;

    public String getFuturesId() {
        return futuresId;
    }

    public void setFuturesId(String futuresId) {
        this.futuresId = futuresId;
    }

    public Date getTradeDate() {
        return tradeDate;
    }

    public void setTradeDate(Date tradeDate) {
        this.tradeDate = tradeDate;
    }

    public String getWindCode() {
        return WindCode;
    }

    public void setWindCode(String windCode) {
        WindCode = windCode;
    }

    public Decimal getPresettlePrice() {
        return presettlePrice;
    }

    public void setPresettlePrice(Decimal presettlePrice) {
        this.presettlePrice = presettlePrice;
    }

    public Decimal getOpenPrice() {
        return openPrice;
    }

    public void setOpenPrice(Decimal openPrice) {
        this.openPrice = openPrice;
    }

    public Decimal getHighPrice() {
        return highPrice;
    }

    public void setHighPrice(Decimal highPrice) {
        this.highPrice = highPrice;
    }

    public Decimal getLowPrice() {
        return lowPrice;
    }

    public void setLowPrice(Decimal lowPrice) {
        this.lowPrice = lowPrice;
    }

    public Decimal getClosePrice() {
        return closePrice;
    }

    public void setClosePrice(Decimal closePrice) {
        this.closePrice = closePrice;
    }

    public Decimal getSettlePrice() {
        return settlePrice;
    }

    public void setSettlePrice(Decimal settlePrice) {
        this.settlePrice = settlePrice;
    }

    public Decimal getVolumeNum() {
        return volumeNum;
    }

    public void setVolumeNum(Decimal volumeNum) {
        this.volumeNum = volumeNum;
    }

    public Decimal getVolumeAmount() {
        return volumeAmount;
    }

    public void setVolumeAmount(Decimal volumeAmount) {
        this.volumeAmount = volumeAmount;
    }

    public Decimal getQiNum() {
        return qiNum;
    }

    public void setQiNum(Decimal qiNum) {
        this.qiNum = qiNum;
    }

    public Decimal getChangePrice() {
        return changePrice;
    }

    public void setChangePrice(Decimal changePrice) {
        this.changePrice = changePrice;
    }

    public Decimal getFuturesType() {
        return futuresType;
    }

    public void setFuturesType(Decimal futuresType) {
        this.futuresType = futuresType;
    }

    public String getInputType() {
        return inputType;
    }

    public void setInputType(String inputType) {
        this.inputType = inputType == null ? null : inputType.trim();
    }

    @Override
    public String getCreateUser() {
        return createUser;
    }

    @Override
    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    @Override
    public String getCreateDept() {
        return createDept;
    }

    @Override
    public void setCreateDept(String createDept) {
        this.createDept = createDept;
    }

    @Override
    public Date getCreateTime() {
        return createTime;
    }

    @Override
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String getUpdateUser() {
        return updateUser;
    }

    @Override
    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    @Override
    public Date getUpdateTime() {
        return updateTime;
    }

    @Override
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
