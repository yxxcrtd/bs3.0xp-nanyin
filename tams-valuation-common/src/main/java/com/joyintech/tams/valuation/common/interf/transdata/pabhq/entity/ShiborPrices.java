package com.joyintech.tams.valuation.common.interf.transdata.pabhq.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;

public class ShiborPrices extends BaseEntity {
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
     * 利率(%)
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal bInfoRate;

//    /**
//     * 期限
//     */
//    private String bInfoTerm;
//
//    /**
//     * 涨跌(BP)
//     */
//    @JsonSerialize(using=BigDecimalSerializer.class)
//    private BigDecimal pctChg;
    
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
     * 取得 利率(%)
     * @return 利率(%)
     */
    public BigDecimal getbInfoRate() {
        return bInfoRate;
    }

    /**
     * 设置 利率(%)
     * @param bInfoRate 利率(%)
     */
    public void setbInfoRate(BigDecimal bInfoRate) {
        this.bInfoRate = bInfoRate;
    }

//    /**
//     * 取得 期限
//     * @return 期限
//     */
//    public String getbInfoTerm() {
//        return bInfoTerm;
//    }
//
//    /**
//     * 设置 期限
//     * @param bInfoTerm 期限
//     */
//    public void setbInfoTerm(String bInfoTerm) {
//        this.bInfoTerm = bInfoTerm == null ? null : bInfoTerm.trim();
//    }
//
//    /**
//     * 取得 涨跌(BP)
//     * @return 涨跌(BP)
//     */
//    public BigDecimal getPctChg() {
//        return pctChg;
//    }
//
//    /**
//     * 设置 涨跌(BP)
//     * @param pctChg 涨跌(BP)
//     */
//    public void setPctChg(BigDecimal pctChg) {
//        this.pctChg = pctChg;
//    }
    
    /**
     * 取得 更新时间
     * @return 更新时间
     */
    public Date getOpdate() {
        return opdate;
    }

    /**
     * 设置 更新时间
     * @param opdate 更新时间
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setOpdate(Date opdate) {
        this.opdate = opdate;
    }

    /**
     * 取得 更新模式
     * @return 更新模式
     */
    public String getOpmode() {
        return opmode;
    }

    /**
     * 设置 更新模式
     * @param opmode 更新模式
     */
    public void setOpmode(String opmode) {
        this.opmode = opmode == null ? null : opmode.trim();
    }
}