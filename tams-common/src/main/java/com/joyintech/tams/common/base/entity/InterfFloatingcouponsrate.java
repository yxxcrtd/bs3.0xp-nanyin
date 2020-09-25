package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class InterfFloatingcouponsrate extends BaseEntity {
    /**
     * 对象ID
     */
    private String objectId;

    /**
     * Wind代码
     */
    private String sInfoWindcode;

    /**
     * 变动日期
     */
    private String changeDt;

    /**
     * 利率(%)
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal rate;

    /**
     * OPDATE
     */
    private Date opdate;

    /**
     * OPMODE
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
     * 取得 变动日期
     * @return 变动日期
     */
    public String getChangeDt() {
        return changeDt;
    }

    /**
     * 设置 变动日期
     * @param changeDt 变动日期
     */
    public void setChangeDt(String changeDt) {
        this.changeDt = changeDt == null ? null : changeDt.trim();
    }

    /**
     * 取得 利率(%)
     * @return 利率(%)
     */
    public BigDecimal getRate() {
        return rate;
    }

    /**
     * 设置 利率(%)
     * @param rate 利率(%)
     */
    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    /**
     * 取得 OPDATE
     * @return OPDATE
     */
    public Date getOpdate() {
        return opdate;
    }

    /**
     * 设置 OPDATE
     * @param opdate OPDATE
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setOpdate(Date opdate) {
        this.opdate = opdate;
    }

    /**
     * 取得 OPMODE
     * @return OPMODE
     */
    public String getOpmode() {
        return opmode;
    }

    /**
     * 设置 OPMODE
     * @param opmode OPMODE
     */
    public void setOpmode(String opmode) {
        this.opmode = opmode == null ? null : opmode.trim();
    }
}