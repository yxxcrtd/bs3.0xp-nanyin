package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class InterfCbondcurvecnbd extends BaseEntity {
    /**
     * null
     */
    private String objectId;

    /**
     * null
     */
    private String tradeDt;

    /**
     * null
     */
    private Long bAnalCurvenumber;

    /**
     * null
     */
    private String bAnalCurvename;

    /**
     * null
     */
    private String bAnalCurvetype;

    /**
     * null
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal bAnalCurveterm;

    /**
     * null
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal bAnalYield;

    /**
     * null
     */
    private Date opdate;

    /**
     * null
     */
    private String opmode;

    /**
     * 取得 null
     * @return null
     */
    public String getObjectId() {
        return objectId;
    }

    /**
     * 设置 null
     * @param objectId null
     */
    public void setObjectId(String objectId) {
        this.objectId = objectId == null ? null : objectId.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public String getTradeDt() {
        return tradeDt;
    }

    /**
     * 设置 null
     * @param tradeDt null
     */
    public void setTradeDt(String tradeDt) {
        this.tradeDt = tradeDt == null ? null : tradeDt.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public Long getbAnalCurvenumber() {
        return bAnalCurvenumber;
    }

    /**
     * 设置 null
     * @param bAnalCurvenumber null
     */
    public void setbAnalCurvenumber(Long bAnalCurvenumber) {
        this.bAnalCurvenumber = bAnalCurvenumber;
    }

    /**
     * 取得 null
     * @return null
     */
    public String getbAnalCurvename() {
        return bAnalCurvename;
    }

    /**
     * 设置 null
     * @param bAnalCurvename null
     */
    public void setbAnalCurvename(String bAnalCurvename) {
        this.bAnalCurvename = bAnalCurvename == null ? null : bAnalCurvename.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public String getbAnalCurvetype() {
        return bAnalCurvetype;
    }

    /**
     * 设置 null
     * @param bAnalCurvetype null
     */
    public void setbAnalCurvetype(String bAnalCurvetype) {
        this.bAnalCurvetype = bAnalCurvetype == null ? null : bAnalCurvetype.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public BigDecimal getbAnalCurveterm() {
        return bAnalCurveterm;
    }

    /**
     * 设置 null
     * @param bAnalCurveterm null
     */
    public void setbAnalCurveterm(BigDecimal bAnalCurveterm) {
        this.bAnalCurveterm = bAnalCurveterm;
    }

    /**
     * 取得 null
     * @return null
     */
    public BigDecimal getbAnalYield() {
        return bAnalYield;
    }

    /**
     * 设置 null
     * @param bAnalYield null
     */
    public void setbAnalYield(BigDecimal bAnalYield) {
        this.bAnalYield = bAnalYield;
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