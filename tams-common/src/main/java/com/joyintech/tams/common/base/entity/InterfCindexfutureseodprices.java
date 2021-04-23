package com.joyintech.tams.common.base.entity;

import com.joyintech.framework.base.BaseEntity;
import org.apache.poi.hpsf.Decimal;

import java.util.Date;

public class InterfCindexfutureseodprices extends BaseEntity {

    private String objectId;
    private String sInfoWindcode;
    private Date tradeDt;
    private Decimal sDqPresettle;
    private Decimal sDqopen;
    private Decimal sDqHigh;
    private Decimal sDqLow;
    private Decimal sDqClose;
    private Decimal sDqSettle;
    private Decimal sDqVolume;
    private Decimal sDqAmount;
    private Decimal sDqQi;
    private Decimal sDqChange;
    private String fsInfoType;
    private String createUser;
    private String createDept;
    private Date createTime;
    private String updateUser;
    private Date updateTime;

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getsInfoWindcode() {
        return sInfoWindcode;
    }

    public void setsInfoWindcode(String sInfoWindcode) {
        this.sInfoWindcode = sInfoWindcode;
    }

    public Date getTradeDt() {
        return tradeDt;
    }

    public void setTradeDt(Date tradeDt) {
        this.tradeDt = tradeDt;
    }

    public Decimal getsDqPresettle() {
        return sDqPresettle;
    }

    public void setsDqPresettle(Decimal sDqPresettle) {
        this.sDqPresettle = sDqPresettle;
    }

    public Decimal getsDqopen() {
        return sDqopen;
    }

    public void setsDqopen(Decimal sDqopen) {
        this.sDqopen = sDqopen;
    }

    public Decimal getsDqHigh() {
        return sDqHigh;
    }

    public void setsDqHigh(Decimal sDqHigh) {
        this.sDqHigh = sDqHigh;
    }

    public Decimal getsDqLow() {
        return sDqLow;
    }

    public void setsDqLow(Decimal sDqLow) {
        this.sDqLow = sDqLow;
    }

    public Decimal getsDqClose() {
        return sDqClose;
    }

    public void setsDqClose(Decimal sDqClose) {
        this.sDqClose = sDqClose;
    }

    public Decimal getsDqSettle() {
        return sDqSettle;
    }

    public void setsDqSettle(Decimal sDqSettle) {
        this.sDqSettle = sDqSettle;
    }

    public Decimal getsDqVolume() {
        return sDqVolume;
    }

    public void setsDqVolume(Decimal sDqVolume) {
        this.sDqVolume = sDqVolume;
    }

    public Decimal getsDqAmount() {
        return sDqAmount;
    }

    public void setsDqAmount(Decimal sDqAmount) {
        this.sDqAmount = sDqAmount;
    }

    public Decimal getsDqQi() {
        return sDqQi;
    }

    public void setsDqQi(Decimal sDqQi) {
        this.sDqQi = sDqQi;
    }

    public Decimal getsDqChange() {
        return sDqChange;
    }

    public void setsDqChange(Decimal sDqChange) {
        this.sDqChange = sDqChange;
    }

    public String getFsInfoType() {
        return fsInfoType;
    }

    public void setFsInfoType(String fsInfoType) {
        this.fsInfoType = fsInfoType;
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
