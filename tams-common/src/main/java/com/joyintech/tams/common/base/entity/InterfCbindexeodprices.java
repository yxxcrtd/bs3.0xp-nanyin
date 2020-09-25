package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class InterfCbindexeodprices extends BaseEntity {
    /**
     * 对象ID
     */
    private String objectId;

    /**
     * Wind代码
     */
    private String sInfoWindcode;

    /**
     * 交易日期
     */
    private String tradeDt;

    /**
     * 货币代码
     */
    private String crncyCode;

    /**
     * 昨收盘价(点)
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal sDqPreclose;

    /**
     * 开盘价(点)
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal sDqOpen;

    /**
     * 最高价(点)
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal sDqHigh;

    /**
     * 最低价(点)
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal sDqLow;

    /**
     * 收盘价(点)
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal sDqClose;

    /**
     * 涨跌(点)
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal sDqChange;

    /**
     * 涨跌幅(%)
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal sDqPctchange;

    /**
     * 成交量(手)
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal sDqVolume;

    /**
     * 成交金额(千元)
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal sDqAmount;

    /**
     * 证券ID
     */
    private String secId;

    /**
     * null
     */
    private Date opdate;

    /**
     * null
     */
    private String opmode;

    /**
     * 取得 对象ID
     * @return 对象ID
     */
    public String getObjectId() {
        return objectId;
    }

    /**
     * 设置 对象ID
     * @param objectId 对象ID
     */
    public void setObjectId(String objectId) {
        this.objectId = objectId == null ? null : objectId.trim();
    }

    /**
     * 取得 Wind代码
     * @return Wind代码
     */
    public String getsInfoWindcode() {
        return sInfoWindcode;
    }

    /**
     * 设置 Wind代码
     * @param sInfoWindcode Wind代码
     */
    public void setsInfoWindcode(String sInfoWindcode) {
        this.sInfoWindcode = sInfoWindcode == null ? null : sInfoWindcode.trim();
    }

    /**
     * 取得 交易日期
     * @return 交易日期
     */
    public String getTradeDt() {
        return tradeDt;
    }

    /**
     * 设置 交易日期
     * @param tradeDt 交易日期
     */
    public void setTradeDt(String tradeDt) {
        this.tradeDt = tradeDt == null ? null : tradeDt.trim();
    }

    /**
     * 取得 货币代码
     * @return 货币代码
     */
    public String getCrncyCode() {
        return crncyCode;
    }

    /**
     * 设置 货币代码
     * @param crncyCode 货币代码
     */
    public void setCrncyCode(String crncyCode) {
        this.crncyCode = crncyCode == null ? null : crncyCode.trim();
    }

    /**
     * 取得 昨收盘价(点)
     * @return 昨收盘价(点)
     */
    public BigDecimal getsDqPreclose() {
        return sDqPreclose;
    }

    /**
     * 设置 昨收盘价(点)
     * @param sDqPreclose 昨收盘价(点)
     */
    public void setsDqPreclose(BigDecimal sDqPreclose) {
        this.sDqPreclose = sDqPreclose;
    }

    /**
     * 取得 开盘价(点)
     * @return 开盘价(点)
     */
    public BigDecimal getsDqOpen() {
        return sDqOpen;
    }

    /**
     * 设置 开盘价(点)
     * @param sDqOpen 开盘价(点)
     */
    public void setsDqOpen(BigDecimal sDqOpen) {
        this.sDqOpen = sDqOpen;
    }

    /**
     * 取得 最高价(点)
     * @return 最高价(点)
     */
    public BigDecimal getsDqHigh() {
        return sDqHigh;
    }

    /**
     * 设置 最高价(点)
     * @param sDqHigh 最高价(点)
     */
    public void setsDqHigh(BigDecimal sDqHigh) {
        this.sDqHigh = sDqHigh;
    }

    /**
     * 取得 最低价(点)
     * @return 最低价(点)
     */
    public BigDecimal getsDqLow() {
        return sDqLow;
    }

    /**
     * 设置 最低价(点)
     * @param sDqLow 最低价(点)
     */
    public void setsDqLow(BigDecimal sDqLow) {
        this.sDqLow = sDqLow;
    }

    /**
     * 取得 收盘价(点)
     * @return 收盘价(点)
     */
    public BigDecimal getsDqClose() {
        return sDqClose;
    }

    /**
     * 设置 收盘价(点)
     * @param sDqClose 收盘价(点)
     */
    public void setsDqClose(BigDecimal sDqClose) {
        this.sDqClose = sDqClose;
    }

    /**
     * 取得 涨跌(点)
     * @return 涨跌(点)
     */
    public BigDecimal getsDqChange() {
        return sDqChange;
    }

    /**
     * 设置 涨跌(点)
     * @param sDqChange 涨跌(点)
     */
    public void setsDqChange(BigDecimal sDqChange) {
        this.sDqChange = sDqChange;
    }

    /**
     * 取得 涨跌幅(%)
     * @return 涨跌幅(%)
     */
    public BigDecimal getsDqPctchange() {
        return sDqPctchange;
    }

    /**
     * 设置 涨跌幅(%)
     * @param sDqPctchange 涨跌幅(%)
     */
    public void setsDqPctchange(BigDecimal sDqPctchange) {
        this.sDqPctchange = sDqPctchange;
    }

    /**
     * 取得 成交量(手)
     * @return 成交量(手)
     */
    public BigDecimal getsDqVolume() {
        return sDqVolume;
    }

    /**
     * 设置 成交量(手)
     * @param sDqVolume 成交量(手)
     */
    public void setsDqVolume(BigDecimal sDqVolume) {
        this.sDqVolume = sDqVolume;
    }

    /**
     * 取得 成交金额(千元)
     * @return 成交金额(千元)
     */
    public BigDecimal getsDqAmount() {
        return sDqAmount;
    }

    /**
     * 设置 成交金额(千元)
     * @param sDqAmount 成交金额(千元)
     */
    public void setsDqAmount(BigDecimal sDqAmount) {
        this.sDqAmount = sDqAmount;
    }

    /**
     * 取得 证券ID
     * @return 证券ID
     */
    public String getSecId() {
        return secId;
    }

    /**
     * 设置 证券ID
     * @param secId 证券ID
     */
    public void setSecId(String secId) {
        this.secId = secId == null ? null : secId.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public Date getOpdate() {
        return opdate;
    }

    /**
     * 设置 null
     * @param opdate null
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setOpdate(Date opdate) {
        this.opdate = opdate;
    }

    /**
     * 取得 null
     * @return null
     */
    public String getOpmode() {
        return opmode;
    }

    /**
     * 设置 null
     * @param opmode null
     */
    public void setOpmode(String opmode) {
        this.opmode = opmode == null ? null : opmode.trim();
    }
}