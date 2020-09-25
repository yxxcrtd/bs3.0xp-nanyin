package com.joyintech.interf.bankname.cfetsup.imixbasicinfo.imixaccountcfets.dto;

import com.joyintech.framework.base.BaseEntity;

import java.io.Serializable;
import java.util.Date;

public class FxImixAccountInfo001Dto extends
        BaseEntity implements Serializable {

    /**
     * 序列化
     */
    private static final long serialVersionUID = -5139291676022166935L;
    /**
     * 流水号
     */
    private String seqno;
    /**
     * 交易场所
     */
    private String jycs;
    /**
     * 产品代码
     */
    private String accountid;
    /**
     * 6位机构码
     */
    private String cstpid;
    /**
     * 21位机构码
     */
    private String cfetsid;
    /**
     * 机构名称
     */
    private String cfetsname;
    /**
     * 有效标志
     */
    private String effectflag;
    /**
     * 创建人
     */
    private String creatorid;
    /**|
     * 创建时间
     */
    private Date createdtime;
    /**
     * 更新人
     */
    private String lastmodifierid;
    /**
     * 更新时间
     */
    private Date lastmodifiedtime;

    public String getSeqno() {
        return seqno;
    }

    public void setSeqno(String seqno) {
        this.seqno = seqno;
    }

    public String getAccountid() {
        return accountid;
    }

    public void setAccountid(String accountid) {
        this.accountid = accountid;
    }

    public String getCstpid() {
        return cstpid;
    }

    public void setCstpid(String cstpid) {
        this.cstpid = cstpid;
    }

    public String getCfetsid() {
        return cfetsid;
    }

    public void setCfetsid(String cfetsid) {
        this.cfetsid = cfetsid;
    }

    public String getCfetsname() {
        return cfetsname;
    }

    public void setCfetsname(String cfetsname) {
        this.cfetsname = cfetsname;
    }

    public String getEffectflag() {
        return effectflag;
    }

    public void setEffectflag(String effectflag) {
        this.effectflag = effectflag;
    }

    public String getCreatorid() {
        return creatorid;
    }

    public void setCreatorid(String creatorid) {
        this.creatorid = creatorid;
    }

    public Date getCreatedtime() {
        return createdtime;
    }

    public void setCreatedtime(Date createdtime) {
        this.createdtime = createdtime;
    }

    public String getLastmodifierid() {
        return lastmodifierid;
    }

    public void setLastmodifierid(String lastmodifierid) {
        this.lastmodifierid = lastmodifierid;
    }

    public Date getLastmodifiedtime() {
        return lastmodifiedtime;
    }

    public void setLastmodifiedtime(Date lastmodifiedtime) {
        this.lastmodifiedtime = lastmodifiedtime;
    }

    public String getJycs() {
        return jycs;
    }

    public void setJycs(String jycs) {
        this.jycs = jycs;
    }
}
