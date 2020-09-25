package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class InterfCbondspecialconditions extends BaseEntity {
    /**
     * 对象 ID
     */
    private String objectId;

    /**
     * Wind 代码
     */
    private String sInfoWindcode;

    /**
     * 条款类型
     */
    private String bInfoProvisiontype;

    /**
     * 赎回价/回售价
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal bInfoCallbkorputbkprice;

    /**
     * 赎回/回售日期
     */
    private String bInfoCallbkorputbkdate;

    /**
     * 赎回/回售告知截止日期
     */
    private String bInfoRedemporrepurcdate;

    /**
     * 含权期限说明
     */
    private String bInfoMaturityembedded;

    /**
     * 行权期限
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal bInfoExecmaturityembedded;

    /**
     * 票面利率调整上限
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal bInfoCouponadjMax;

    /**
     * 票面利率调整下限
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal bInfoCouponadjMin;

    /**
     * 条款内容
     */
    private String bInfoContent;

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
     * 取得 条款类型
     * @return 条款类型
     */
    public String getbInfoProvisiontype() {
        return bInfoProvisiontype;
    }

    /**
     * 设置 条款类型
     * @param bInfoProvisiontype 条款类型
     */
    public void setbInfoProvisiontype(String bInfoProvisiontype) {
        this.bInfoProvisiontype = bInfoProvisiontype == null ? null : bInfoProvisiontype.trim();
    }

    /**
     * 取得 赎回价/回售价
     * @return 赎回价/回售价
     */
    public BigDecimal getbInfoCallbkorputbkprice() {
        return bInfoCallbkorputbkprice;
    }

    /**
     * 设置 赎回价/回售价
     * @param bInfoCallbkorputbkprice 赎回价/回售价
     */
    public void setbInfoCallbkorputbkprice(BigDecimal bInfoCallbkorputbkprice) {
        this.bInfoCallbkorputbkprice = bInfoCallbkorputbkprice;
    }

    /**
     * 取得 赎回/回售日期
     * @return 赎回/回售日期
     */
    public String getbInfoCallbkorputbkdate() {
        return bInfoCallbkorputbkdate;
    }

    /**
     * 设置 赎回/回售日期
     * @param bInfoCallbkorputbkdate 赎回/回售日期
     */
    public void setbInfoCallbkorputbkdate(String bInfoCallbkorputbkdate) {
        this.bInfoCallbkorputbkdate = bInfoCallbkorputbkdate == null ? null : bInfoCallbkorputbkdate.trim();
    }

    /**
     * 取得 赎回/回售告知截止日期
     * @return 赎回/回售告知截止日期
     */
    public String getbInfoRedemporrepurcdate() {
        return bInfoRedemporrepurcdate;
    }

    /**
     * 设置 赎回/回售告知截止日期
     * @param bInfoRedemporrepurcdate 赎回/回售告知截止日期
     */
    public void setbInfoRedemporrepurcdate(String bInfoRedemporrepurcdate) {
        this.bInfoRedemporrepurcdate = bInfoRedemporrepurcdate == null ? null : bInfoRedemporrepurcdate.trim();
    }

    /**
     * 取得 含权期限说明
     * @return 含权期限说明
     */
    public String getbInfoMaturityembedded() {
        return bInfoMaturityembedded;
    }

    /**
     * 设置 含权期限说明
     * @param bInfoMaturityembedded 含权期限说明
     */
    public void setbInfoMaturityembedded(String bInfoMaturityembedded) {
        this.bInfoMaturityembedded = bInfoMaturityembedded == null ? null : bInfoMaturityembedded.trim();
    }

    /**
     * 取得 行权期限
     * @return 行权期限
     */
    public BigDecimal getbInfoExecmaturityembedded() {
        return bInfoExecmaturityembedded;
    }

    /**
     * 设置 行权期限
     * @param bInfoExecmaturityembedded 行权期限
     */
    public void setbInfoExecmaturityembedded(BigDecimal bInfoExecmaturityembedded) {
        this.bInfoExecmaturityembedded = bInfoExecmaturityembedded;
    }

    /**
     * 取得 票面利率调整上限
     * @return 票面利率调整上限
     */
    public BigDecimal getbInfoCouponadjMax() {
        return bInfoCouponadjMax;
    }

    /**
     * 设置 票面利率调整上限
     * @param bInfoCouponadjMax 票面利率调整上限
     */
    public void setbInfoCouponadjMax(BigDecimal bInfoCouponadjMax) {
        this.bInfoCouponadjMax = bInfoCouponadjMax;
    }

    /**
     * 取得 票面利率调整下限
     * @return 票面利率调整下限
     */
    public BigDecimal getbInfoCouponadjMin() {
        return bInfoCouponadjMin;
    }

    /**
     * 设置 票面利率调整下限
     * @param bInfoCouponadjMin 票面利率调整下限
     */
    public void setbInfoCouponadjMin(BigDecimal bInfoCouponadjMin) {
        this.bInfoCouponadjMin = bInfoCouponadjMin;
    }

    /**
     * 取得 条款内容
     * @return 条款内容
     */
    public String getbInfoContent() {
        return bInfoContent;
    }

    /**
     * 设置 条款内容
     * @param bInfoContent 条款内容
     */
    public void setbInfoContent(String bInfoContent) {
        this.bInfoContent = bInfoContent == null ? null : bInfoContent.trim();
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