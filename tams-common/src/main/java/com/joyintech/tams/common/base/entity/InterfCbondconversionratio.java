package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class InterfCbondconversionratio extends BaseEntity {
    /**
     * 对象ID
     */
    private String objectId;

    /**
     * Wind代码
     */
    private String sInfoWindcode;

    /**
     * 开始适用日
     */
    private String bCvnStartdate;

    /**
     * 结束适用日
     */
    private String bCvnEnddate;

    /**
     * 折算比例
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal bCvnRateofstdbnd;

    /**
     * 折合标准券
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal bCvnCvntperhundred;

    /**
     * 报价式回购折算率
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal bCvnRateofstdbndcsi;

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
     * 取得 开始适用日
     * @return 开始适用日
     */
    public String getbCvnStartdate() {
        return bCvnStartdate;
    }

    /**
     * 设置 开始适用日
     * @param bCvnStartdate 开始适用日
     */
    public void setbCvnStartdate(String bCvnStartdate) {
        this.bCvnStartdate = bCvnStartdate == null ? null : bCvnStartdate.trim();
    }

    /**
     * 取得 结束适用日
     * @return 结束适用日
     */
    public String getbCvnEnddate() {
        return bCvnEnddate;
    }

    /**
     * 设置 结束适用日
     * @param bCvnEnddate 结束适用日
     */
    public void setbCvnEnddate(String bCvnEnddate) {
        this.bCvnEnddate = bCvnEnddate == null ? null : bCvnEnddate.trim();
    }

    /**
     * 取得 折算比例
     * @return 折算比例
     */
    public BigDecimal getbCvnRateofstdbnd() {
        return bCvnRateofstdbnd;
    }

    /**
     * 设置 折算比例
     * @param bCvnRateofstdbnd 折算比例
     */
    public void setbCvnRateofstdbnd(BigDecimal bCvnRateofstdbnd) {
        this.bCvnRateofstdbnd = bCvnRateofstdbnd;
    }

    /**
     * 取得 折合标准券
     * @return 折合标准券
     */
    public BigDecimal getbCvnCvntperhundred() {
        return bCvnCvntperhundred;
    }

    /**
     * 设置 折合标准券
     * @param bCvnCvntperhundred 折合标准券
     */
    public void setbCvnCvntperhundred(BigDecimal bCvnCvntperhundred) {
        this.bCvnCvntperhundred = bCvnCvntperhundred;
    }

    /**
     * 取得 报价式回购折算率
     * @return 报价式回购折算率
     */
    public BigDecimal getbCvnRateofstdbndcsi() {
        return bCvnRateofstdbndcsi;
    }

    /**
     * 设置 报价式回购折算率
     * @param bCvnRateofstdbndcsi 报价式回购折算率
     */
    public void setbCvnRateofstdbndcsi(BigDecimal bCvnRateofstdbndcsi) {
        this.bCvnRateofstdbndcsi = bCvnRateofstdbndcsi;
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