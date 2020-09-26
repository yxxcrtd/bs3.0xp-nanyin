package com.joyintech.tams.valuation.common.interf.transdata.pabhq.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;

public class Cbondprices extends BaseEntity {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
     * 开盘价(元)
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal bDqOpen;

    /**
     * 最高价(元)
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal bDqHigh;

    /**
     * 最低价(元)
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal bDqLow;

    /**
     * 收盘价(元)
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal bDqOriginclose;

    /**
     * 成交量(手)
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal bDqVolume;

    /**
     * 成交金额(千元)
     */
    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal bDqAmount;

    /**
     * 更新时间
     */
    private Date opdate;

    /**
     * 更新模式
     */
    private String opmode;

    /**
     * 取得 对象ID
     * 
     * @return 对象ID
     */
    public String getObjectId() {
        return objectId;
    }

    /**
     * 设置 对象ID
     * 
     * @param objectId 对象ID
     */
    public void setObjectId(String objectId) {
        this.objectId = objectId == null ? null : objectId.trim();
    }

    /**
     * 取得 Wind代码
     * 
     * @return Wind代码
     */
    public String getsInfoWindcode() {
        return sInfoWindcode;
    }

    /**
     * 设置 Wind代码
     * 
     * @param sInfoWindcode Wind代码
     */
    public void setsInfoWindcode(String sInfoWindcode) {
        this.sInfoWindcode = sInfoWindcode == null ? null : sInfoWindcode.trim();
    }

    /**
     * 取得 交易日期
     * 
     * @return 交易日期
     */
    public String getTradeDt() {
        return tradeDt;
    }

    /**
     * 设置 交易日期
     * 
     * @param tradeDt 交易日期
     */
    public void setTradeDt(String tradeDt) {
        this.tradeDt = tradeDt == null ? null : tradeDt.trim();
    }

    /**
     * 取得 开盘价(元)
     * 
     * @return 开盘价(元)
     */
    public BigDecimal getbDqOpen() {
        return bDqOpen;
    }

    /**
     * 设置 开盘价(元)
     * 
     * @param bDqOpen 开盘价(元)
     */
    public void setbDqOpen(BigDecimal bDqOpen) {
        this.bDqOpen = bDqOpen;
    }

    /**
     * 取得 最高价(元)
     * 
     * @return 最高价(元)
     */
    public BigDecimal getbDqHigh() {
        return bDqHigh;
    }

    /**
     * 设置 最高价(元)
     * 
     * @param bDqHigh 最高价(元)
     */
    public void setbDqHigh(BigDecimal bDqHigh) {
        this.bDqHigh = bDqHigh;
    }

    /**
     * 取得 最低价(元)
     * 
     * @return 最低价(元)
     */
    public BigDecimal getbDqLow() {
        return bDqLow;
    }

    /**
     * 设置 最低价(元)
     * 
     * @param bDqLow 最低价(元)
     */
    public void setbDqLow(BigDecimal bDqLow) {
        this.bDqLow = bDqLow;
    }

    /**
     * 取得 收盘价(元)
     * 
     * @return 收盘价(元)
     */
    public BigDecimal getbDqOriginclose() {
        return bDqOriginclose;
    }

    /**
     * 设置 收盘价(元)
     * 
     * @param bDqOriginclose 收盘价(元)
     */
    public void setbDqOriginclose(BigDecimal bDqOriginclose) {
        this.bDqOriginclose = bDqOriginclose;
    }

    /**
     * 取得 成交量(手)
     * 
     * @return 成交量(手)
     */
    public BigDecimal getbDqVolume() {
        return bDqVolume;
    }

    /**
     * 设置 成交量(手)
     * 
     * @param bDqVolume 成交量(手)
     */
    public void setbDqVolume(BigDecimal bDqVolume) {
        this.bDqVolume = bDqVolume;
    }

    /**
     * 取得 成交金额(千元)
     * 
     * @return 成交金额(千元)
     */
    public BigDecimal getbDqAmount() {
        return bDqAmount;
    }

    /**
     * 设置 成交金额(千元)
     * 
     * @param bDqAmount 成交金额(千元)
     */
    public void setbDqAmount(BigDecimal bDqAmount) {
        this.bDqAmount = bDqAmount;
    }

    /**
     * 取得 更新时间
     * 
     * @return 更新时间
     */
    public Date getOpdate() {
        return opdate;
    }

    /**
     * 设置 更新时间
     * 
     * @param opdate 更新时间
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setOpdate(Date opdate) {
        this.opdate = opdate;
    }

    /**
     * 取得 更新模式
     * 
     * @return 更新模式
     */
    public String getOpmode() {
        return opmode;
    }

    /**
     * 设置 更新模式
     * 
     * @param opmode 更新模式
     */
    public void setOpmode(String opmode) {
        this.opmode = opmode == null ? null : opmode.trim();
    }
}