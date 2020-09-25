package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.util.Date;

public class InterfPayhsrcholiday extends BaseEntity {
    /**
     * 对象ID
     */
    private String objectId;

    /**
     * HOLIDATE
     */
    private String holidate;

    /**
     * MARKET_CODE
     */
    private String marketCode;

    /**
     * W 非节假日 H 节假日
     */
    private String effectflag;

    /**
     * LSTMNTDATE
     */
    private Date lstmntdate;

    /**
     * LSTMNTUSER
     */
    private String lstmntuser;

    /**
     * 操作日期
     */
    private Date opdate;

    /**
     * 操作方式
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
     * 取得 HOLIDATE
     * @return HOLIDATE
     */
    public String getHolidate() {
        return holidate;
    }

    /**
     * 设置 HOLIDATE
     * @param holidate HOLIDATE
     */
    public void setHolidate(String holidate) {
        this.holidate = holidate == null ? null : holidate.trim();
    }

    /**
     * 取得 MARKET_CODE
     * @return MARKET_CODE
     */
    public String getMarketCode() {
        return marketCode;
    }

    /**
     * 设置 MARKET_CODE
     * @param marketCode MARKET_CODE
     */
    public void setMarketCode(String marketCode) {
        this.marketCode = marketCode == null ? null : marketCode.trim();
    }

    /**
     * 取得 W 非节假日 H 节假日
     * @return W 非节假日 H 节假日
     */
    public String getEffectflag() {
        return effectflag;
    }

    /**
     * 设置 W 非节假日 H 节假日
     * @param effectflag W 非节假日 H 节假日
     */
    public void setEffectflag(String effectflag) {
        this.effectflag = effectflag == null ? null : effectflag.trim();
    }

    /**
     * 取得 LSTMNTDATE
     * @return LSTMNTDATE
     */
    public Date getLstmntdate() {
        return lstmntdate;
    }

    /**
     * 设置 LSTMNTDATE
     * @param lstmntdate LSTMNTDATE
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setLstmntdate(Date lstmntdate) {
        this.lstmntdate = lstmntdate;
    }

    /**
     * 取得 LSTMNTUSER
     * @return LSTMNTUSER
     */
    public String getLstmntuser() {
        return lstmntuser;
    }

    /**
     * 设置 LSTMNTUSER
     * @param lstmntuser LSTMNTUSER
     */
    public void setLstmntuser(String lstmntuser) {
        this.lstmntuser = lstmntuser == null ? null : lstmntuser.trim();
    }

    /**
     * 取得 操作日期
     * @return 操作日期
     */
    public Date getOpdate() {
        return opdate;
    }

    /**
     * 设置 操作日期
     * @param opdate 操作日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setOpdate(Date opdate) {
        this.opdate = opdate;
    }

    /**
     * 取得 操作方式
     * @return 操作方式
     */
    public String getOpmode() {
        return opmode;
    }

    /**
     * 设置 操作方式
     * @param opmode 操作方式
     */
    public void setOpmode(String opmode) {
        this.opmode = opmode == null ? null : opmode.trim();
    }
}