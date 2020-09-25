package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class InterfFxrmbmidrate extends BaseEntity {
    /**
     * 对象ID
     */
    private String objectId;

    /**
     * 货币代码
     */
    private String crncyCode;

    /**
     * 日期
     */
    private String tradeDt;

    /**
     * 中间价
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal crncyMidrate;

    /**
     * 操作日期
     */
    private Date opdate;

    /**
     * 操作模式
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
     * 取得 日期
     * @return 日期
     */
    public String getTradeDt() {
        return tradeDt;
    }

    /**
     * 设置 日期
     * @param tradeDt 日期
     */
    public void setTradeDt(String tradeDt) {
        this.tradeDt = tradeDt == null ? null : tradeDt.trim();
    }

    /**
     * 取得 中间价
     * @return 中间价
     */
    public BigDecimal getCrncyMidrate() {
        return crncyMidrate;
    }

    /**
     * 设置 中间价
     * @param crncyMidrate 中间价
     */
    public void setCrncyMidrate(BigDecimal crncyMidrate) {
        this.crncyMidrate = crncyMidrate;
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
     * 取得 操作模式
     * @return 操作模式
     */
    public String getOpmode() {
        return opmode;
    }

    /**
     * 设置 操作模式
     * @param opmode 操作模式
     */
    public void setOpmode(String opmode) {
        this.opmode = opmode == null ? null : opmode.trim();
    }
}