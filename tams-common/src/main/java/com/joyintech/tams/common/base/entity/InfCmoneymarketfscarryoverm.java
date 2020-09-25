package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.util.Date;

public class InfCmoneymarketfscarryoverm extends BaseEntity {
    /**
     * 对象ID
     */
    private String objectId;

    /**
     * 基金Wind代码
     */
    private String sInfoWindcode;

    /**
     * 收益分配份额结转方式
     */
    private String fInfoIncomescarryoverm;

    /**
     * 收益分配份额结转日期类型代码
     */
    private Integer fInfoIncomescarryoverdtcode;

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
     * 取得 基金Wind代码
     * @return 基金Wind代码
     */
    public String getsInfoWindcode() {
        return sInfoWindcode;
    }

    /**
     * 设置 基金Wind代码
     * @param sInfoWindcode 基金Wind代码
     */
    public void setsInfoWindcode(String sInfoWindcode) {
        this.sInfoWindcode = sInfoWindcode == null ? null : sInfoWindcode.trim();
    }

    /**
     * 取得 收益分配份额结转方式
     * @return 收益分配份额结转方式
     */
    public String getfInfoIncomescarryoverm() {
        return fInfoIncomescarryoverm;
    }

    /**
     * 设置 收益分配份额结转方式
     * @param fInfoIncomescarryoverm 收益分配份额结转方式
     */
    public void setfInfoIncomescarryoverm(String fInfoIncomescarryoverm) {
        this.fInfoIncomescarryoverm = fInfoIncomescarryoverm == null ? null : fInfoIncomescarryoverm.trim();
    }

    /**
     * 取得 收益分配份额结转日期类型代码
     * @return 收益分配份额结转日期类型代码
     */
    public Integer getfInfoIncomescarryoverdtcode() {
        return fInfoIncomescarryoverdtcode;
    }

    /**
     * 设置 收益分配份额结转日期类型代码
     * @param fInfoIncomescarryoverdtcode 收益分配份额结转日期类型代码
     */
    public void setfInfoIncomescarryoverdtcode(Integer fInfoIncomescarryoverdtcode) {
        this.fInfoIncomescarryoverdtcode = fInfoIncomescarryoverdtcode;
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