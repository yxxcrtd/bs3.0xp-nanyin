package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class InterfCbondcurveshc extends BaseEntity {
    /**
     * 对象ID
     */
    private String objectId;

    /**
     * 交易日期
     */
    private String tradeDt;

    /**
     * 曲线编号
     */
    private Long bAnalCurvenumber;

    /**
     * 曲线名称
     */
    private String bAnalCurvename;

    /**
     * 曲线类型
     */
    private String bAnalCurvetype;

    /**
     * 年限
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal bAnalCurveterm;

    /**
     * 收益率(%)
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
     * 取得 曲线编号
     * @return 曲线编号
     */
    public Long getbAnalCurvenumber() {
        return bAnalCurvenumber;
    }

    /**
     * 设置 曲线编号
     * @param bAnalCurvenumber 曲线编号
     */
    public void setbAnalCurvenumber(Long bAnalCurvenumber) {
        this.bAnalCurvenumber = bAnalCurvenumber;
    }

    /**
     * 取得 曲线名称
     * @return 曲线名称
     */
    public String getbAnalCurvename() {
        return bAnalCurvename;
    }

    /**
     * 设置 曲线名称
     * @param bAnalCurvename 曲线名称
     */
    public void setbAnalCurvename(String bAnalCurvename) {
        this.bAnalCurvename = bAnalCurvename == null ? null : bAnalCurvename.trim();
    }

    /**
     * 取得 曲线类型
     * @return 曲线类型
     */
    public String getbAnalCurvetype() {
        return bAnalCurvetype;
    }

    /**
     * 设置 曲线类型
     * @param bAnalCurvetype 曲线类型
     */
    public void setbAnalCurvetype(String bAnalCurvetype) {
        this.bAnalCurvetype = bAnalCurvetype == null ? null : bAnalCurvetype.trim();
    }

    /**
     * 取得 年限
     * @return 年限
     */
    public BigDecimal getbAnalCurveterm() {
        return bAnalCurveterm;
    }

    /**
     * 设置 年限
     * @param bAnalCurveterm 年限
     */
    public void setbAnalCurveterm(BigDecimal bAnalCurveterm) {
        this.bAnalCurveterm = bAnalCurveterm;
    }

    /**
     * 取得 收益率(%)
     * @return 收益率(%)
     */
    public BigDecimal getbAnalYield() {
        return bAnalYield;
    }

    /**
     * 设置 收益率(%)
     * @param bAnalYield 收益率(%)
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