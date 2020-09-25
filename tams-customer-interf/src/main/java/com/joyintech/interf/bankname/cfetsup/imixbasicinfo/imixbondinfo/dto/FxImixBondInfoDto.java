package com.joyintech.interf.bankname.cfetsup.imixbasicinfo.imixbondinfo.dto;

import com.joyintech.framework.base.BaseEntity;

import java.util.Date;

public class FxImixBondInfoDto extends BaseEntity {

    private static final long serialVersionUID = 1L;

    //流水号
    private String seqno;

    //债券代码
    private String secid;

    //是否特殊债
    private String specialdebt;

    //创建人
    private String creatorid;

    //创建时间
    private Date createdtime;

    //更新人
    private String lastmodifierid;

    //更新时间
    private Date lastmodifiedtime;

    public String getSeqno() {
        return seqno;
    }

    public void setSeqno(String seqno) {
        this.seqno = seqno;
    }

    public String getSecid() {
        return secid;
    }

    public void setSecid(String secid) {
        this.secid = secid;
    }
    public String getSpecialdebt() {
        return specialdebt;
    }

    public void setSpecialdebt(String specialdebt) {
        this.specialdebt = specialdebt;
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


}
