package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.util.Date;

public class InterfCbondindustrywind extends BaseEntity {
    /**
     * 对象 ID
     */
    private String objectId;

    /**
     * Wind 代码
     */
    private String sInfoWindcode;

    /**
     * 一级板块代码
     */
    private String sInfoIndustrycode;

    /**
     * 一级板块名称
     */
    private String sInfoIndustryname;

    /**
     * 二级板块代码
     */
    private String sInfoIndustrycode2;

    /**
     * 二级板块名称
     */
    private String sInfoIndustryname2;

    /**
     * null
     */
    private Date opdate;

    /**
     * null
     */
    private String opmode;

    /**
     * 取得 对象 ID
     * @return 对象 ID
     */
    public String getObjectId() {
        return objectId;
    }

    /**
     * 设置 对象 ID
     * @param objectId 对象 ID
     */
    public void setObjectId(String objectId) {
        this.objectId = objectId == null ? null : objectId.trim();
    }

    /**
     * 取得 Wind 代码
     * @return Wind 代码
     */
    public String getsInfoWindcode() {
        return sInfoWindcode;
    }

    /**
     * 设置 Wind 代码
     * @param sInfoWindcode Wind 代码
     */
    public void setsInfoWindcode(String sInfoWindcode) {
        this.sInfoWindcode = sInfoWindcode == null ? null : sInfoWindcode.trim();
    }

    /**
     * 取得 一级板块代码
     * @return 一级板块代码
     */
    public String getsInfoIndustrycode() {
        return sInfoIndustrycode;
    }

    /**
     * 设置 一级板块代码
     * @param sInfoIndustrycode 一级板块代码
     */
    public void setsInfoIndustrycode(String sInfoIndustrycode) {
        this.sInfoIndustrycode = sInfoIndustrycode == null ? null : sInfoIndustrycode.trim();
    }

    /**
     * 取得 一级板块名称
     * @return 一级板块名称
     */
    public String getsInfoIndustryname() {
        return sInfoIndustryname;
    }

    /**
     * 设置 一级板块名称
     * @param sInfoIndustryname 一级板块名称
     */
    public void setsInfoIndustryname(String sInfoIndustryname) {
        this.sInfoIndustryname = sInfoIndustryname == null ? null : sInfoIndustryname.trim();
    }

    /**
     * 取得 二级板块代码
     * @return 二级板块代码
     */
    public String getsInfoIndustrycode2() {
        return sInfoIndustrycode2;
    }

    /**
     * 设置 二级板块代码
     * @param sInfoIndustrycode2 二级板块代码
     */
    public void setsInfoIndustrycode2(String sInfoIndustrycode2) {
        this.sInfoIndustrycode2 = sInfoIndustrycode2 == null ? null : sInfoIndustrycode2.trim();
    }

    /**
     * 取得 二级板块名称
     * @return 二级板块名称
     */
    public String getsInfoIndustryname2() {
        return sInfoIndustryname2;
    }

    /**
     * 设置 二级板块名称
     * @param sInfoIndustryname2 二级板块名称
     */
    public void setsInfoIndustryname2(String sInfoIndustryname2) {
        this.sInfoIndustryname2 = sInfoIndustryname2 == null ? null : sInfoIndustryname2.trim();
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