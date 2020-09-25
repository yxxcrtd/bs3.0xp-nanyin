package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class InterfCmoneymarketfincome extends BaseEntity {
    /**
     * null
     */
    private String objectId;

    /**
     * null
     */
    private String sInfoWindcode;

    /**
     * null
     */
    private String fInfoBgndate;

    /**
     * null
     */
    private String fInfoEnddate;

    /**
     * null
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal fInfoUnityield;

    /**
     * null
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal fInfoYearlyroe;

    /**
     * null
     */
    private String annDate;

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
    public String getsInfoWindcode() {
        return sInfoWindcode;
    }

    /**
     * 设置 null
     * @param sInfoWindcode null
     */
    public void setsInfoWindcode(String sInfoWindcode) {
        this.sInfoWindcode = sInfoWindcode == null ? null : sInfoWindcode.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public String getfInfoBgndate() {
        return fInfoBgndate;
    }

    /**
     * 设置 null
     * @param fInfoBgndate null
     */
    public void setfInfoBgndate(String fInfoBgndate) {
        this.fInfoBgndate = fInfoBgndate == null ? null : fInfoBgndate.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public String getfInfoEnddate() {
        return fInfoEnddate;
    }

    /**
     * 设置 null
     * @param fInfoEnddate null
     */
    public void setfInfoEnddate(String fInfoEnddate) {
        this.fInfoEnddate = fInfoEnddate == null ? null : fInfoEnddate.trim();
    }

    /**
     * 取得 null
     * @return null
     */
    public BigDecimal getfInfoUnityield() {
        return fInfoUnityield;
    }

    /**
     * 设置 null
     * @param fInfoUnityield null
     */
    public void setfInfoUnityield(BigDecimal fInfoUnityield) {
        this.fInfoUnityield = fInfoUnityield;
    }

    /**
     * 取得 null
     * @return null
     */
    public BigDecimal getfInfoYearlyroe() {
        return fInfoYearlyroe;
    }

    /**
     * 设置 null
     * @param fInfoYearlyroe null
     */
    public void setfInfoYearlyroe(BigDecimal fInfoYearlyroe) {
        this.fInfoYearlyroe = fInfoYearlyroe;
    }

    /**
     * 取得 null
     * @return null
     */
    public String getAnnDate() {
        return annDate;
    }

    /**
     * 设置 null
     * @param annDate null
     */
    public void setAnnDate(String annDate) {
        this.annDate = annDate == null ? null : annDate.trim();
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