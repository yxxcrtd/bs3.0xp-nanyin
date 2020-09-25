package com.joyintech.tams.common.base.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.json.BigDecimalSerializer;
import com.joyintech.framework.json.CustomDateDeserialize;
import java.math.BigDecimal;
import java.util.Date;

public class InterfPayhsrcinterestrate extends BaseEntity {
    /**
     * 对象ID
     */
    private String objectId;

    /**
     * 发布日
     */
    private Date publishdate;

    /**
     * 利率类型
     */
    private String interestratekind;

    /**
     * 利率code
     */
    private String currencycode;

    /**
     * 币种
     */
    private String currency;

    /**
     * 利率值
     */
    @JsonSerialize(using=BigDecimalSerializer.class)
    private BigDecimal interestrate;

    /**
     * 生效日
     */
    private Date effectivedate;

    /**
     * ISEFFECTORNOT
     */
    private String iseffectornot;

    /**
     * INNAME
     */
    private String inname;

    /**
     * RELATDATA
     */
    private String relatdata;

    /**
     * PUBDATE
     */
    private Date pubdate;

    /**
     * CHANGEHIS
     */
    private String changehis;

    /**
     * DATASRC
     */
    private String datasrc;

    /**
     * 变化百分比
     */
    private String upordown;

    /**
     * 期限
     */
    private String term;

    /**
     * 创建时间
     */
    private Date createdate;

    /**
     * 创建人
     */
    private String createuser;

    /**
     * 上次修改时间
     */
    private Date lstmntdate;

    /**
     * 上次修改人
     */
    private String lstmntuser;

    /**
     * 是否下发
     */
    private String needrefresh;

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
     * 取得 发布日
     * @return 发布日
     */
    public Date getPublishdate() {
        return publishdate;
    }

    /**
     * 设置 发布日
     * @param publishdate 发布日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setPublishdate(Date publishdate) {
        this.publishdate = publishdate;
    }

    /**
     * 取得 利率类型
     * @return 利率类型
     */
    public String getInterestratekind() {
        return interestratekind;
    }

    /**
     * 设置 利率类型
     * @param interestratekind 利率类型
     */
    public void setInterestratekind(String interestratekind) {
        this.interestratekind = interestratekind == null ? null : interestratekind.trim();
    }

    /**
     * 取得 利率code
     * @return 利率code
     */
    public String getCurrencycode() {
        return currencycode;
    }

    /**
     * 设置 利率code
     * @param currencycode 利率code
     */
    public void setCurrencycode(String currencycode) {
        this.currencycode = currencycode == null ? null : currencycode.trim();
    }

    /**
     * 取得 币种
     * @return 币种
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * 设置 币种
     * @param currency 币种
     */
    public void setCurrency(String currency) {
        this.currency = currency == null ? null : currency.trim();
    }

    /**
     * 取得 利率值
     * @return 利率值
     */
    public BigDecimal getInterestrate() {
        return interestrate;
    }

    /**
     * 设置 利率值
     * @param interestrate 利率值
     */
    public void setInterestrate(BigDecimal interestrate) {
        this.interestrate = interestrate;
    }

    /**
     * 取得 生效日
     * @return 生效日
     */
    public Date getEffectivedate() {
        return effectivedate;
    }

    /**
     * 设置 生效日
     * @param effectivedate 生效日
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setEffectivedate(Date effectivedate) {
        this.effectivedate = effectivedate;
    }

    /**
     * 取得 ISEFFECTORNOT
     * @return ISEFFECTORNOT
     */
    public String getIseffectornot() {
        return iseffectornot;
    }

    /**
     * 设置 ISEFFECTORNOT
     * @param iseffectornot ISEFFECTORNOT
     */
    public void setIseffectornot(String iseffectornot) {
        this.iseffectornot = iseffectornot == null ? null : iseffectornot.trim();
    }

    /**
     * 取得 INNAME
     * @return INNAME
     */
    public String getInname() {
        return inname;
    }

    /**
     * 设置 INNAME
     * @param inname INNAME
     */
    public void setInname(String inname) {
        this.inname = inname == null ? null : inname.trim();
    }

    /**
     * 取得 RELATDATA
     * @return RELATDATA
     */
    public String getRelatdata() {
        return relatdata;
    }

    /**
     * 设置 RELATDATA
     * @param relatdata RELATDATA
     */
    public void setRelatdata(String relatdata) {
        this.relatdata = relatdata == null ? null : relatdata.trim();
    }

    /**
     * 取得 PUBDATE
     * @return PUBDATE
     */
    public Date getPubdate() {
        return pubdate;
    }

    /**
     * 设置 PUBDATE
     * @param pubdate PUBDATE
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setPubdate(Date pubdate) {
        this.pubdate = pubdate;
    }

    /**
     * 取得 CHANGEHIS
     * @return CHANGEHIS
     */
    public String getChangehis() {
        return changehis;
    }

    /**
     * 设置 CHANGEHIS
     * @param changehis CHANGEHIS
     */
    public void setChangehis(String changehis) {
        this.changehis = changehis == null ? null : changehis.trim();
    }

    /**
     * 取得 DATASRC
     * @return DATASRC
     */
    public String getDatasrc() {
        return datasrc;
    }

    /**
     * 设置 DATASRC
     * @param datasrc DATASRC
     */
    public void setDatasrc(String datasrc) {
        this.datasrc = datasrc == null ? null : datasrc.trim();
    }

    /**
     * 取得 变化百分比
     * @return 变化百分比
     */
    public String getUpordown() {
        return upordown;
    }

    /**
     * 设置 变化百分比
     * @param upordown 变化百分比
     */
    public void setUpordown(String upordown) {
        this.upordown = upordown == null ? null : upordown.trim();
    }

    /**
     * 取得 期限
     * @return 期限
     */
    public String getTerm() {
        return term;
    }

    /**
     * 设置 期限
     * @param term 期限
     */
    public void setTerm(String term) {
        this.term = term == null ? null : term.trim();
    }

    /**
     * 取得 创建时间
     * @return 创建时间
     */
    public Date getCreatedate() {
        return createdate;
    }

    /**
     * 设置 创建时间
     * @param createdate 创建时间
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    /**
     * 取得 创建人
     * @return 创建人
     */
    public String getCreateuser() {
        return createuser;
    }

    /**
     * 设置 创建人
     * @param createuser 创建人
     */
    public void setCreateuser(String createuser) {
        this.createuser = createuser == null ? null : createuser.trim();
    }

    /**
     * 取得 上次修改时间
     * @return 上次修改时间
     */
    public Date getLstmntdate() {
        return lstmntdate;
    }

    /**
     * 设置 上次修改时间
     * @param lstmntdate 上次修改时间
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setLstmntdate(Date lstmntdate) {
        this.lstmntdate = lstmntdate;
    }

    /**
     * 取得 上次修改人
     * @return 上次修改人
     */
    public String getLstmntuser() {
        return lstmntuser;
    }

    /**
     * 设置 上次修改人
     * @param lstmntuser 上次修改人
     */
    public void setLstmntuser(String lstmntuser) {
        this.lstmntuser = lstmntuser == null ? null : lstmntuser.trim();
    }

    /**
     * 取得 是否下发
     * @return 是否下发
     */
    public String getNeedrefresh() {
        return needrefresh;
    }

    /**
     * 设置 是否下发
     * @param needrefresh 是否下发
     */
    public void setNeedrefresh(String needrefresh) {
        this.needrefresh = needrefresh == null ? null : needrefresh.trim();
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