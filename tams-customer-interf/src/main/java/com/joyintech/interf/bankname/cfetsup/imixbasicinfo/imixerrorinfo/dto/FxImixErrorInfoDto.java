package com.joyintech.interf.bankname.cfetsup.imixbasicinfo.imixerrorinfo.dto;

import com.joyintech.framework.base.BaseEntity;

import java.util.Date;

/**
 * 描述:FX_IMIX_ERROR_INFO表的实体类
 * @version
 * @author:  Administrator
 * @创建时间: 2020-01-08
 */
public class FxImixErrorInfoDto extends BaseEntity {
    /**
     * 流水号
     */
    private String seqno;

    /**
     * 市场描述
     */
    private String marketindicator;

    /**
     * 错误代码
     */
    private String applerrorcode;

    /**
     * 错误描述
     */
    private String applerrordesc;

    /**
     * 创建人员
     */
    private String creatorid;

    /**
     * 创建时间
     */
    private Date createdtime;

    /**
     * 更新人员
     */
    private String lastmodifierid;

    /**
     * 更新时间
     */
    private Date lastmodifiedtime;

    /**
     * 流水号
     * @return SEQNO 流水号
     */
    public String getSeqno() {
        return seqno;
    }

    /**
     * 流水号
     * @param seqno 流水号
     */
    public void setSeqno(String seqno) {
        this.seqno = seqno == null ? null : seqno.trim();
    }

    /**
     * 市场描述
     * @return MARKETINDICATOR 市场描述
     */
    public String getMarketindicator() {
        return marketindicator;
    }

    /**
     * 市场描述
     * @param marketindicator 市场描述
     */
    public void setMarketindicator(String marketindicator) {
        this.marketindicator = marketindicator == null ? null : marketindicator.trim();
    }

    /**
     * 错误代码
     * @return APPLERRORCODE 错误代码
     */
    public String getApplerrorcode() {
        return applerrorcode;
    }

    /**
     * 错误代码
     * @param applerrorcode 错误代码
     */
    public void setApplerrorcode(String applerrorcode) {
        this.applerrorcode = applerrorcode == null ? null : applerrorcode.trim();
    }

    /**
     * 错误描述
     * @return APPLERRORDESC 错误描述
     */
    public String getApplerrordesc() {
        return applerrordesc;
    }

    /**
     * 错误描述
     * @param applerrordesc 错误描述
     */
    public void setApplerrordesc(String applerrordesc) {
        this.applerrordesc = applerrordesc == null ? null : applerrordesc.trim();
    }

    /**
     * 创建人员
     * @return CREATORID 创建人员
     */
    public String getCreatorid() {
        return creatorid;
    }

    /**
     * 创建人员
     * @param creatorid 创建人员
     */
    public void setCreatorid(String creatorid) {
        this.creatorid = creatorid == null ? null : creatorid.trim();
    }

    /**
     * 创建时间
     * @return CREATEDTIME 创建时间
     */
    public Date getCreatedtime() {
        return createdtime;
    }

    /**
     * 创建时间
     * @param createdtime 创建时间
     */
    public void setCreatedtime(Date createdtime) {
        this.createdtime = createdtime;
    }

    /**
     * 更新人员
     * @return LASTMODIFIERID 更新人员
     */
    public String getLastmodifierid() {
        return lastmodifierid;
    }

    /**
     * 更新人员
     * @param lastmodifierid 更新人员
     */
    public void setLastmodifierid(String lastmodifierid) {
        this.lastmodifierid = lastmodifierid == null ? null : lastmodifierid.trim();
    }

    /**
     * 更新时间
     * @return LASTMODIFIEDTIME 更新时间
     */
    public Date getLastmodifiedtime() {
        return lastmodifiedtime;
    }

    /**
     * 更新时间
     * @param lastmodifiedtime 更新时间
     */
    public void setLastmodifiedtime(Date lastmodifiedtime) {
        this.lastmodifiedtime = lastmodifiedtime;
    }
}